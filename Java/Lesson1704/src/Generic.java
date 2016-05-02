
public class Generic {
	String[] s = new String[10];
	Object[] o = (Object[]) s; //
	o[1] = 1;
	s[0] = s[1];
	public interface Stack<E> {
		E pop();
		void push(E e);
		int size();
	}
	public class ArrayStack<E> implements Stack<E> {
		private E[] elements = new E[10];
	}
	public class ArrayStack<E> implements Stack<E> {
		private E[] elements = (E[10]) new Object[10];
		public E peek() {
			return elements[size - 1];
		}
	}
	Stack zz = new ArrayStack();
	zz.push(10);
	zz.push("abc");
	System.out.println(zz.pop());
	System.out.println(zz.pop());
	
	//перечисления
	public enum State {
		NEW,
		RUNNING,
		BLOCKED,
		FINISHED
	}
	public enum Unit {
		KILOMETER(1e3);
		private final double lenght;

	}

}
