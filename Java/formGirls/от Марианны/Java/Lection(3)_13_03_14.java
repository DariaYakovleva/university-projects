public abstract class AbstactStack implements Stack {
// можно неопределенные методы, но нельзя создавать экземпляр
//	private int size; // нельзя обращаться к private элементу предка
	protected int size; // видно себе и потомкам, внешним - не видно
	// деталь реализации но для иерархии

	//public int size;
	// int size;
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size == 0;
	} 

	public Object peek() {
		Object result = pop();
		push(result);
		return result;
	}



	public Object pop() { // надо убить такие же методы в потомках
		assert size > 0;

		Object value = popImpl();
		size--;
		return value;
	}
}