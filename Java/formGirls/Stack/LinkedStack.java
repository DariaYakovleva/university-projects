//final - что-то, определенное 1 раз (константа для значения) 
//нужно сразу инициализировать, например, конструктором
//если запилить свой size, то будет использоваться именно он (наиболее позднее определение)
//если только нет прямой ссылки super.size();

public class LinkedStack extends AbstractStack implements Stack {
    private Node head;

    public void push(Object element) {
        //Node(); - так нельзя, т. к. есть явный конструктор 
        //LinkedStack() - а так можно, т.к. там нет конструктора и будет объявлен конструктор по умолчанию

        //конструктор можно вызвать только так
        head = new Node(element, head);
        size++;
    }

    protected Object popImpl() {
        Object result = head.value;
        head = head.next;
        return result;
    }
         
    private static class Node {
        private final Object value;
        private final Node next;

        //типа конструктор по умолчаню. он не годится, так как не инициализирует поля 
        //public Node() {                                                              
        //}
        
        //норм констрктор
        public Node(Object v, Node n) {
            value = v;
            next = n;
        }

    }
}