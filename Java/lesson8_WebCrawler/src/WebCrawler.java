import info.kgeorgiy.java.advanced.crawler.CachingDownloader;
import info.kgeorgiy.java.advanced.crawler.Crawler;
import info.kgeorgiy.java.advanced.crawler.Document;
import info.kgeorgiy.java.advanced.crawler.Downloader;
import info.kgeorgiy.java.advanced.crawler.Result;
import info.kgeorgiy.java.advanced.crawler.URLUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;


public class WebCrawler implements Crawler {

	private ExecutorService executorLinks;
	private ExecutorService executorDocs;
	private Downloader downloader;
	private Integer perHost;

	/**
	 * Constructor create new WebCrawler
	 * @param downloader is current downloader
	 * @param downloaders is maximum number of download pages parallel
	 * @param extractors is maximum number of download links parallel
	 * @param downloaders is maximum number download pages from host parallel
	 **/
	public WebCrawler(Downloader downloader, int downloaders, int extractors, int perHost) {
		executorDocs = Executors.newFixedThreadPool(downloaders);
		executorLinks = Executors.newFixedThreadPool(extractors);
		this.downloader = downloader;
		this.perHost = perHost;
	}

	/**
	 * Download links from url and his tree depth
	 * @param url is current url
	 * @param depth is current depth
	 * @return list of result string 
	 */
	@Override
	public Result download(String url, int depth) {
		RecursiveWalk siteTree = new RecursiveWalk();
		siteTree.startGo(url, depth);
		return siteTree.getResult();
	}

	/**
	 * Close all threads
	 */
	@Override
	public void close() {
		executorDocs.shutdownNow();
		executorLinks.shutdownNow();
	}

	public static void main(String[] args) {
		String url = args[0];
		int downloads = Integer.parseInt(args[1]);
		int extractors = Integer.parseInt(args[2]);
		int pHost = Integer.parseInt(args[3]);
		try {
			WebCrawler web = new WebCrawler(new CachingDownloader(), downloads, extractors, pHost);
			web.download(url, 1);
			web.close();
		} catch (IOException e) {
			System.err.println("I/O exception");
		}
	}

	private class RecursiveWalk {

		Map<String, Future<Document>> documents = new ConcurrentHashMap<>();
		Map<String, Integer> hosts = new ConcurrentHashMap<>();
		Map<String, IOException> errors = new ConcurrentHashMap<>();
		Map<String, Queue<Task>> hostsQueue = new ConcurrentHashMap<>();
		AtomicInteger cntLinks = new AtomicInteger(0);

		public void startGo(String url, int depth) {
			Callable<Document> task = new Callable<Document>() {
				@Override
				public Document call() throws Exception {
					resursiveGo(url, depth);
					return null;
				}
			};
			String linkHost = null;
			try {
				linkHost = URLUtils.getHost(url);
			} catch (MalformedURLException e) {
				System.err.println("URL Exception");
			}
			hosts.putIfAbsent(linkHost, 1);
			hostsQueue.putIfAbsent(linkHost, new ConcurrentLinkedQueue<>());
			cntLinks.incrementAndGet();
			Future<Document> term = executorDocs.submit(task);
			documents.put(url, term);
		}

		public Document resursiveGo(String url, int depth) {
			//			System.out.println(url + " " + depth + " " + cntLinks);
			final Document doc;
			try {
				String urlHost = URLUtils.getHost(url);
				doc = downloader.download(url);
				hosts.compute(urlHost, (k, v) -> v - 1);
				synchronized (hostsQueue) {
					String host = urlHost;
					hostsQueue.putIfAbsent(host, new ConcurrentLinkedQueue<>());
					while (hosts.get(host) < perHost && !hostsQueue.get(host).isEmpty()) {
						Task curTask = hostsQueue.get(host).poll();
						if (!documents.containsKey(curTask.getUrl())) {
							hosts.compute(host, (k, v) -> v + 1);
							Future<Document> term2 = executorDocs.submit(curTask.getTask());
							documents.put(curTask.getUrl(), term2);
						} else {
							cntLinks.decrementAndGet();
						}
					}			
				}

				hostsQueue.putIfAbsent(urlHost, new ConcurrentLinkedQueue<>());	
				if (depth > 1) {
					executorLinks.submit(new Callable<Document>() {
						@Override
						public Document call() throws Exception {
							List<String> links = doc.extractLinks();
							for (String link : links) {
								if (!documents.containsKey(link)) {		
									Callable<Document> task = new Callable<Document>() {
										@Override
										public Document call() throws Exception {
											return resursiveGo(link, depth - 1);
										}
									};
									String linkHost = URLUtils.getHost(link);
									cntLinks.incrementAndGet();
									hostsQueue.putIfAbsent(linkHost, new ConcurrentLinkedQueue<>());
									hostsQueue.get(linkHost).add(new Task(task, link));

									synchronized (hostsQueue) {

										String host = linkHost;
										hosts.putIfAbsent(host, 0);
										while (hosts.get(host) < perHost && !hostsQueue.get(host).isEmpty()) {
											Task curTask = hostsQueue.get(host).poll();
											if (!documents.containsKey(curTask.getUrl())) {
												hosts.compute(host, (k, v) -> v + 1);
												Future<Document> term2 = executorDocs.submit(curTask.getTask());
												documents.put(curTask.getUrl(), term2);
											} else {
												cntLinks.decrementAndGet();
											}
										}
									}

								} else {

								}
							}
							cntLinks.decrementAndGet();
							return null;
						}
					});
				} else {
					cntLinks.decrementAndGet();
				}
				return doc;
			} catch (IOException e) {
				cntLinks.decrementAndGet();
				errors.put(url, e);
				return null;
			}
		}

		public Result getResult() {
			while (cntLinks.get() > 0) {}
			List<String> ans = new ArrayList<>();
			Set<String> ansSet = documents.keySet();
			for (String s : ansSet) {
				if (!errors.containsKey(s)) {
					ans.add(s);
				}
			}
			return new Result(ans, errors);
		}
	}

	private class Task {
		Callable<Document> task;
		String url;
		public Task(Callable<Document> task, String name) {
			this.task = task;
			this.url = name;
		}
		String getUrl() {
			return url;
		}
		Callable<Document> getTask() {
			return task;
		}
	}
}
