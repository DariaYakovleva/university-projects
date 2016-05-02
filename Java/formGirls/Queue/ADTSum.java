public class ADTSum {
    public static void main(String[] args) {
        long sum = 0;
        ArrayQueueADT queue = new ArrayQueueADT();
        //inv: args from 1 to i are in queue
        for (int i = 0; i < args.length; i++) {
            String[] a = args[i].split("\\s"); 
            for (int j = 0; j < a.length; j++) {
                if (!a[j].isEmpty()) {  
                    if ((a[j].length() > 1) && (a[j].charAt(0) == '0') && (a[j].charAt(1) == 'x' || a[j].charAt(1) == 'X')) {
                        ArrayQueueADT.push(queue, Long.parseLong(a[j].substring(2), 16));
                    } else {
                    	ArrayQueueADT.push(queue, Long.parseLong(a[j]));
                    }
                }
            }
        }
        ArrayQueueADT.push(queue, 0);

	//inv: sum[0, queue.length) queue = sum
        while (!ArrayQueueADT.isEmpty(queue)) {            
            long x1 = (long)ArrayQueueADT.pop(queue);
            if (!ArrayQueueADT.isEmpty(queue)) {
                long x2 = (long)ArrayQueueADT.pop(queue);
                ArrayQueueADT.push(queue, x1 + x2);
            } else {
             System.out.println(x1);
            }
        }    
    }
}