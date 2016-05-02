/**
 * Created by Daria on 27.03.2016.
 */
public class Relaxation_method {
    public Relaxation_method() {
    }
    public Relaxation_method(double[][] matrix, double w) {
        n = matrix.length;
        this.matrix = new double[n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++) {
                this.matrix[i][j] = -matrix[i][j] / matrix[i][i];
            }
        }
        this.w = w;
        System.err.println("Current matrix");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++) {
                System.err.print(this.matrix[i][j] + " ");
            }
            System.err.println();
        }
        currentSolution = new double[n];
        for (int i = 0; i < n; i++) {
            currentSolution[i] = 0;
        }
    }
    int n;
    double[][] matrix;
    double w;
    double[] currentSolution;

    double[] next() {
        double[] nextSolution = new double[n];
        double[] tmpSolution = new double[n];
        for (int i = 0; i < n; i++) {
            tmpSolution[i] = matrix[i][n];
            for (int j = 0; j < n; j++) {
                tmpSolution[i] += currentSolution[j] * matrix[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            nextSolution[i] = w * tmpSolution[i] + (1 - w) * currentSolution[i];
        }
        System.err.print("Cur solution " + w + " :" );
        for (int i = 0; i < n; i++) {
            System.err.print(nextSolution[i] + " ");
        }
        System.err.println("error=" + getError(nextSolution));

        return nextSolution;
    }

    double getError(double[] solution) {
        double error = 0.0;
        for (int i = 0; i < n; i++) {
            double curError = matrix[i][n];
            for (int j = 0; j < n; j++) {
                curError += solution[j] * matrix[i][j];
            }
            error = Double.max(error, Math.abs(curError));
        }
        return Math.abs(error);
    }

    double[] getSolution(double eps) {
        double error = getError(currentSolution);
        while (error > eps) {
            currentSolution = next();
            error = getError(currentSolution);
        }
        return currentSolution;
    }
}
