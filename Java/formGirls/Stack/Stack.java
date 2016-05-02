public interface Stack {
//рвньше тут был паблик    
//обозначать всегда буквой e
//public abstract - по умолчанию и нельзя изменить    
    public abstract void push(Object e);
    Object peek();
    Object pop();
    int size();
    boolean isEmpty();
}