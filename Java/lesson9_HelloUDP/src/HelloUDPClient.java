import info.kgeorgiy.java.advanced.hello.HelloClient;

import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

public class HelloUDPClient {// implements HelloClient{

	String ip;
	int port;
	String prefix;
	private Integer maxRequestsPerThread;
	private Thread clients[];
	private InetAddress host = null;

	public HelloUDPClient() {
	}

	public HelloUDPClient(String ip, int port, String prefix, int maxThreads,
			int maxreq) {
		this.ip = ip;
		this.port = port;
		this.prefix = prefix;
		this.maxRequestsPerThread = maxreq;
		clients = new Thread[maxThreads];
	}

	// @Override
	public void start(String ip, int port, String prefix, int maxreq,
			int maxThreads) {
		this.ip = ip;
		this.port = port;
		this.prefix = prefix;
		this.maxRequestsPerThread = maxreq;
		clients = new Thread[maxThreads];
		try {
			host = InetAddress.getByName(ip);
		} catch (UnknownHostException e1) {
			System.err.println("Unknown host");
			return;
		}
		// System.err.println(maxreq + " "+maxThreads);
		for (int i = 0; i < clients.length; i++) {
			final int numThread = i;
			clients[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					HelloConnection client = new HelloConnection();
					for (int req = 0; req < maxRequestsPerThread; req++) {
							String message = prefix + numThread + "_" + req;
							DatagramPacket rPacket = null;
							String expectedReply = "Hello, " + message;
							String reply = "";
							while (!expectedReply.equals(reply)) {
								client.sendMessage(port, host, message);
								rPacket = client.receivePacket();
								if (rPacket != null)
									reply = new String(rPacket.getData(),rPacket.getOffset(), rPacket.getLength(), Charset.forName("UTF-8"));
							}
							System.out.println(message);
							System.out.println(reply);
					}
					client.close();
				}
			});
			clients[i].start();
		}
		for (int i = 0; i < clients.length; i++) {
			try {
				clients[i].join();
			} catch (InterruptedException e) {
			}
		}
	}

	public static void main(String[] args) {
		if (args.length < 5) {
			System.err
			.println("I want more parameters: ip, port, prefix, count of threads, count of requests");
			return;
		}
		String ip = args[0];
		int port = Integer.parseInt(args[1]);
		String prefix = args[2];
		int maxThreads = Integer.parseInt(args[3]);
		int maxRequestsPerThread = Integer.parseInt(args[4]);
		HelloUDPClient client = new HelloUDPClient();
		client.start(ip, port, prefix, maxThreads, maxRequestsPerThread);
	}
}
