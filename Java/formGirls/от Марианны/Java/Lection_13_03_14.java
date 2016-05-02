public class LinkedStack implements Stack {
	private int size;
	private Node head; // инвариант выполняется по дефолту

	// если нет явного констуктора, то дадут конструктор по умолчанию(пустой)

	public void push(Object element) {
		head = new Node(ekement, head); // пустой конструктор уже нельзя
		size++;
	}

	public Object peek() {
		assert size != 0;
		return head.value;
	}

	public Object pop() {
		assert size != 0; // память не надо чистить ибо ссылка в удаленном элементе недостижима
		
		Object result = popImpl();
		
		size--;
		return result;
	}

	protected Object popImpl() {
		Object result = head.value;
		head = head.next;
		return result;
	}


	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

// любое состояние класса удовлетворяет инварианту, надо чтобы начальное тоже удовлетворяло
	private class Node { // почему неизменяемое??
		private final Object value = ; // final определяется только один раз
		private final Node next = ;
		
		// конструктор, для этих грязных целей	
		public static Node(Object v, Node n) {
			value = v; // нельзя инициализировать два раза
			next = n;
			/* head = null; // хранит this для элемента для которого вызывается
				не нужно
				static регулирует достуность this ???
				сделаем статик */
		}

		// можно добавить пустой конструктор
		public Node() {
			value = null; // нельзя инициализировать два раза
			next = null;
		}		

		// private конструктор можно, но нельзя создавать экземпляры класса извне
		//такой конструктор нельзя, без конструктора тоже, ибо не инициализированные неизменяемые переменные 
		/*
				public Node() {
				}
		*/

		// к private можно обращаться из другого класса, но из того же файла но из определенного рядышком нельзя

	}

}