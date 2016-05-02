import java.lang.*;
//default package, java.lang, и откуда импортировали
import java.util.*;

public interface Stack<E> {
	E pop();
	void push(E e);
	int size();
}

public interface Block<T, E extends Exception> {
	T execute() throws E;
}
public interface Stack {
	default public void clear() throws Exception {
		while (!isEmpty()) {
			pop();
		}
	}
}

public class LinkedStack implements Stack {
	public void clear() {
		head = null;
	}
}
public class Lesson1704 {
	void f() {
		Stack s = new Stack() {
			{
				push(1);
				push(2);
				push(3);
			}
		}; //инициализатор
	}
	//java 8
	//в интерфейсах могут быть статические методы
	// есть методы по умолчанию
	//в интерфейсах нельзя определять переменные 
	// >>арифметический сдвиг >>> логический сдвиг}
	//Барбара Лисков
	// принцип подстановки лисков
	// метор контрвариантен по аргументу и ковариантен по результату
	//Generic
	//