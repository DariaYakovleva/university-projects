public interface Stack {
	public void push(Object e); // надо указывать имя, надо указывать имя для документирования javadoc
	// все работает, так как сравнение происходит по сигнатуре, в которой только типы
	abstract public Object peek(); // такой метод точно есть, но описания его нет
	public Object pop();
	public int size();
	public boolean isEmpty();
}
// должен быть прописан контракт
// семантическую часть 
// из файла TestStack
public void fill (Stack stack) {

}