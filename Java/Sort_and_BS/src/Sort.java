import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Sort {
	static int n;
	static int[] heap;
	static int size;
	static void sift_down(int x) {
		int v = x;
		while ((2 * v + 1 < size && heap[v] < heap[2 * v + 1]) || (2 * v + 2 < size && heap[v] < heap[2 * v + 2])) {
			if  (2 * v + 1 < size && heap[v] < heap[2 * v + 1]  && (heap[2 * v + 1] >= heap[2 * v + 2] || (2 * v + 2 >= size))) {
				int t = heap[v];
				heap[v] = heap[2 * v + 1];
				heap[2 * v + 1] = t;
				v = 2 * v + 1;
			} else {
				if  (2 * v + 2 < size && heap[v] < heap[2 * v + 2]) {
					int t = heap[v];
					heap[v] = heap[2 * v + 2];
					heap[2 * v + 2] = t;
					v = 2 * v + 2;
				}
			}
		}
	}

	static void heap_sort() {
		for (int i = n - 1; i >= 0; i--) {
			sift_down(i);			
		}
		for (int i = 0; i < n; i++) {
			int t = heap[0];
			heap[0] = heap[size - 1]; 
			heap[size - 1] = t;
			size--;
			sift_down(0);
		}
	}
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("sort.in"));
		PrintWriter out = new PrintWriter("sort.out");
		n = in.nextInt();
		size = n;
		heap = new int[n + 10];
		for (int i = 0; i < n; i++) {
			heap[i] = in.nextInt();
		}
		heap_sort();
		for (int i = 0; i < n; i++) {
			out.print(heap[i] + " ");
		}
		out.close();
	}

}