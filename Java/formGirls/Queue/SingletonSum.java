public class SingletonSum {
    public static void main(String[] args) {
        long sum = 0;

        //inv: args from 1 to i are in queue
        for (int i = 0; i < args.length; i++) {
            String[] a = args[i].split("\\s"); 
            for (int j = 0; j < a.length; j++) {
                if (!a[j].isEmpty()) {  
                    if ((a[j].length() > 1) && (a[j].charAt(0) == '0') && (a[j].charAt(1) == 'x' || a[j].charAt(1) == 'X')) {
                        ArrayQueueSingleton.push(Long.parseLong(a[j].substring(2), 16));
                    } else {
                    	ArrayQueueSingleton.push(Long.parseLong(a[j]));
                    }
                }
            }
        }
        ArrayQueueSingleton.push(0);

        //inv: sum[0, queue.length) queue = sum
        while (!ArrayQueueSingleton.isEmpty()) {            
            long x1 = (long)ArrayQueueSingleton.pop();
            if (!ArrayQueueSingleton.isEmpty()) {
                long x2 = (long)ArrayQueueSingleton.pop();
                ArrayQueueSingleton.push(x1 + x2);
            } else {
             System.out.println(x1);
            }
        }    
    }
}