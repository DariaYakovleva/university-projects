public class Sum {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 0; i < args.length; i++) {
            String[] a = args[i].split("\\s"); 
            for (int j = 0; j < a.length; j++) {
                if (!a[j].isEmpty()) {                    
                    sum += Integer.parseInt(a[j]);
                }
            }
        }
        System.out.println(sum);
    }
}