import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello World!");
        Scanner reader = new Scanner(new FileInputStream("input.in"));
        int n;
        double w;
        n = reader.nextInt();
        double[][] matrix = new double[n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                matrix[i][j] = reader.nextDouble();
            }
        }
        w = reader.nextDouble();
        Relaxation_method relax = new Relaxation_method(matrix, w);
        double solution[] = relax.getSolution(0.1);
        for (int i = 0; i < n; i++) {
            System.out.print(solution[i] + " ");
        }
    }
}
