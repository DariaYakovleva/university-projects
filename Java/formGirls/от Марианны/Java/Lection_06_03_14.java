publid class ArrayStackADT extends AbstractStack {
//implements Stack нафиг не надо ибо если предок обещал соотв интерфейсу то все
													

	private Object[] elements = new Object[10];
	private int size = 0; 
	// было две ячейки памяти ASADT (size elements) -> сколько экземпляров, столько 

	public static void push(ArrayStackADT stack, Object o) {
		
		ensureCapacity(stack, stack.size +1);
		
		stack.elements[stack.size++] = o;
	}

	private static void ensureCapacity(ArrayStackADT stack, int capacity) {
		
		if (stack.elements.length < capacity) {
			
			Object[] e = new Object[2 * capacity];
			
			for (int i = 0; i < stack.size; i++) {
				e[i] = stack.elements[i]
			}
			
			stack.elements = e;
		}
	}

	public static Object pop(ArrayStackADT stack) {
									// проинициализирован null
									// если элемент не нужен, но сборщик мусора не может его забрать, ибо есть ссылка
									// нет доступа следовательно надо занулить
		assert size > 0;
		Object result = popImpl();

		size--;
		return result;
	}

	protected Object popImpl() {
		
		Object result = stack.elements[stack.size - 1];
		stack.elements[stack.size] = null;
		return result;
	}

	public static Object peek(ArrayStackADT stack) {

		assert stack.size > 0;
		return stack.elements[stack.size - 1];
	}

	public static int size(ArrayStackADT stack) {
		
		return stack.size;
	}

	public static boolean isEmpty() {

		return size == 0;
	}
}

public class StackTest {

	public static void main(String[] args) {

		ArrayStackADT stack1 = new ArrayStackADT();
		ArrayStackADT stack2 = new ArrayStackADT();

		for (int i = 0; i < 5; i++) {
			stack1.push(i);
		}
		stack1.size = -1;
		while (!stack1.isEmpty()) {
			System.out.println(stack1.pop());
		}
	}
}