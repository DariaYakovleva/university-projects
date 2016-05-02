import info.kgeorgiy.java.advanced.concurrent.ListIP;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;


public class IterativeParallelism implements ListIP {
	/**
	 * @version 2.0
	 */

	/**
	 * Constructor create empty IterativeParallelism object
	 */
	public IterativeParallelism() {
	}


	/**
	 * Find minimum value from list, compare it using comparator
	 * Use parallelism with cntThreads threads
	 * @param cntThreads count of threads
	 * @param list list values
	 * @param comparator function which compare two values 
	 * @return minimum value from list
	 * @throws InterruptedException one of thread has interrupt
	 */
	@Override
	public <E> E minimum(int cntThreads, List<? extends E> list, Comparator<? super E> comparator) throws InterruptedException {
		Function<List<? extends E>, E> getMinim = new Function<List<? extends E>, E>(){
			@Override
			public E apply(List<? extends E> t) {
				if (t == null) {
					System.err.println("NULL");
					return null;
				}
				E minim = t.get(0);
				for (E elem : t) {
					if (elem != null) {
						if (minim == null) {
							minim = elem;
						} else {
							if (comparator.compare(minim, elem) > 0) {
								minim = elem;
							}
						}
					}
				}
				return minim;
			}};
			SubFun<E, E> fun = new SubFun<E, E>(cntThreads, list, getMinim);
			fun.go();
			List<E> results = fun.getResult();
			return getMinim.apply(results);
	}

	/**
	 * Find maximum value from list, compare it using comparator
	 * Use parallelism with cntThreads threads
	 * @param cntThreads count of threads
	 * @param list list values
	 * @param comparator function which compare two values
	 * @return maximum value from list 
	 * @throws InterruptedException one of thread has interrupt
	 */
	@Override
	public <E> E maximum(int cntThreads, List<? extends E> list, Comparator<? super E> comparator) throws InterruptedException {
		Function<List<? extends E>, E> getMaxim = new Function< List<? extends E>, E>(){
			@Override
			public E apply(List<? extends E> t) {
				if (t == null) {
					System.err.println("NULL");
					return null;
				}
				E maxim = t.get(0);
				for (E elem : t) {
					if (elem != null) {
						if (maxim == null) {
							maxim = elem;
						} else {
							if (comparator.compare(maxim, elem) < 0) {
								maxim = elem;
							}
						}
					}
				}
				return maxim;
			}};
			SubFun<E, E> fun = new SubFun<E, E>(cntThreads, list, getMaxim);
			fun.go();
			List<E> results = fun.getResult(); 
			return getMaxim.apply(results);
	}

	/**
	 * Say true if all elements from list approach to predicate and false otherwise
	 * Use parallelism with cntThreads threads
	 * @param cntThreads count of threads
	 * @param list list values
	 * @param predicate function which say approach element for template or not
	 * @return true if all elements from list approach to predicate and false otherwise
	 * @throws InterruptedException one of thread has interrupt
	 */
	@Override
	public <E> boolean all(int cntThreads, List<? extends E> list, Predicate<? super E> predicate) throws InterruptedException {
		Function<List<? extends E>, Integer> checkPred = new Function<List<? extends E>, Integer>(){
			@Override
			public Integer apply(List<? extends E> t) {
				if (t == null) return null;
				boolean good = true;
				for (E elem : t) {
					good &= predicate.test(elem);
				}
				if (good) {
					return new Integer(1);
				}
				return new Integer(0);
			}};
			SubFun<E, Integer> fun = new SubFun<E, Integer>(cntThreads, list, checkPred);
			fun.go();
			List<Integer> result = fun.getResult();
			for (Integer elem : result) {
				if (elem != null && elem.intValue() == 0) 
					return false;
			}
			return true;
	}

	/**
	 * Return true if any element from list approach to predicate and false otherwise
	 * Use parallelism with cntThreads threads
	 * @param cntThreads count of threads
	 * @param list list values
	 * @param predicate function which say approach element for template or not
	 * @return true if any element from list approach to predicate and false otherwise  
	 * @throws InterruptedException one of thread has interrupt
	 */
	@Override	
	public <E> boolean any(int cntThreads, List<? extends E> list, Predicate<? super E> predicate) throws InterruptedException {
		Function<List<? extends E>, Integer> checkPred = new Function<List<? extends E>, Integer>(){
			@Override
			public Integer apply(List<? extends E> t) {
				if (t == null) return null;
				boolean good = false;
				for (E elem : t) {
						good |= predicate.test(elem);
				}
				if (good) {
					return new Integer(1);
				}
				return new Integer(0);
			}};
			SubFun<E, Integer> fun = new SubFun<E, Integer>(cntThreads, list, checkPred);
			fun.go();
			List<Integer> result = fun.getResult();
			for (Integer elem : result) {
				if (elem != null && elem.intValue() == 1) 
					return true;
			}
			return false;

	}

	/**
	 * Return list elements which approach to predicate
	 * Use parallelism with cntThreads threads
	 * @param cntThreads count of threads
	 * @param list list values
	 * @param predicate function which say approach element for template or not
	 * @return list elements which approach to predicate
	 * @throws InterruptedException one of thread has interrupt
	 */
	@Override
	public <E> List<E> filter(int cntThreads, List<? extends E> list, Predicate<? super E> predicate) throws InterruptedException {
		Function<List<? extends E>, List<E>> checkPred = new Function<List<? extends E>, List<E>>(){
			@Override
			public List<E> apply(List<? extends E> t) {
				if (t == null) return null;
				List<E> res = new ArrayList<E>();
				for (E elem : t) {
					if (predicate.test(elem)) {
						res.add(elem);
					}
				}
				return res;
			}};
			SubFun<E, List<E>> fun = new SubFun<E, List<E>>(cntThreads, list, checkPred);
			fun.go();
			List< List<E> > result = fun.getResult();
			List<E> ans = new ArrayList<E>();
			for (List<E> elem : result) {
				if (elem != null) ans.addAll(elem);
			}
			return ans;

	}

	/**
	 * Return list elements which every element replace to value of function from this element
	 * Use parallelism with cntThreads threads
	 * @param cntThreads count of threads
	 * @param list list values
	 * @param function function which take element and apply it
	 * @return list elements which every element replace to value of function from this element
	 * @throws InterruptedException one of thread has interrupt
	 */
	@Override
	public <E, U> List<U> map(int cntThreads, List<? extends E> list, Function<? super E, ? extends U> function) throws InterruptedException {
		Function<List<? extends E>, List<U>> checkPred = new Function<List<? extends E>, List<U>>(){
			@Override
			public List<U> apply(List<? extends E> t) {
				if (t == null) return null;
				List<U> res = new ArrayList<>();
				for (int i = 0; i < t.size(); i++) {
					res.add(function.apply(t.get(i)));
				}
				return res;
			}};
			SubFun<E, List<U>> fun = new SubFun<E, List<U>>(cntThreads, list, checkPred);
			fun.go();
			List< List<U> > result = fun.getResult();
			List<U> ans = new ArrayList<>();
			for (List<U> elem : result) {
				if (elem != null) ans.addAll(elem);
			}
			return ans;

	}

	/**
	 * Create string concatenate all elements from list
	 * Use parallelism with cntThreads threads
	 * @param cntThreads count of threads
	 * @param list list values
	 * @return string which concatenate all elements from list
	 * @throws InterruptedException one of thread has interrupt
	 */
	@Override
	public String concat(int cntThreads, List<?> list) throws InterruptedException {
		Function<List<?>, String> toStr = new Function<List<?>, String>(){
			@Override
			public String apply(List<?> t) {
				if (t == null) return null;
				String res = new String();
				for (int i = 0; i < t.size(); i++) {
					res = res.concat(t.get(i).toString());
				}
				return res;
			}};
			SubFun<Object, String> fun = new SubFun<Object, String>(cntThreads, list, toStr);
			fun.go();
			List<String> result = fun.getResult();
			String ans = new String();
			for (String elem : result) {
				if (elem != null) ans = ans.concat(elem);
			}
			return ans;
	}

}

class SubFun<E, R> {

	/** list first values */
	private List<? extends E> list;
	/** function create from constructor */
	Function<List<? extends E>, R> funct;
	/** array of current threads */
	Thread threads[];
	/** lust result value */
	List<R> results;
	/** length sublist which send to each thread */
	int lenSublist;

	/**
	 * Constructor construct count of threads, list values and function
	 * @param cntThreads count of threads
	 * @param list list values
	 * @param funct function which take element and apply it
	 */
	SubFun(int cntThreads, List<? extends E> list, Function<List<? extends E>, R> funct) {
		this.list = list;
		this.funct = funct;
		threads = new Thread[cntThreads];
		results = new ArrayList<>();
		for (int i = 0; i < cntThreads; i++) results.add(null);
		lenSublist = (list.size() + cntThreads - 1) / cntThreads;
	}
	/**
	 * Run all threads and execute it
	 */
	public void go() {
		for (int i = 0; i < threads.length; i++) {
			if (lenSublist * i < list.size()) {
				final List<? extends E> sl = list.subList(lenSublist * i,  Integer.min(lenSublist * (i + 1), list.size()));
				final int pos = i;
				threads[i] = new Thread(new Runnable() {
					public void run() {
						R res = funct.apply(sl);
						synchronized (IterativeParallelism.class) {
							results.set(pos, res);
						}
					}
				});
				threads[i].start();
			} else {
				threads[i] = null;
			}
		}
		for (int i = 0; i < threads.length; i++) {
			try {
				if (threads[i] != null) threads[i].join();
			} catch (InterruptedException e) {
			}
		}
	}
	/**
	 * Return result list from all threads
	 * @return list of results
	 */
	public List<R> getResult() {
		return results;
	}
}