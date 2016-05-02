import java.util.*;
import java.io.*;

public class Movetofront2 {

	static Random rnd = new Random();
	
	static class Vertex {
		int x;
		int y;
		Vertex parent;
		Vertex left;
		Vertex right;
		int size;
		Vertex(int a) {
			x = a;
			y = rnd.nextInt();
			parent = null;
			left = null;
			right = null;
			size = 1;
		}
	}
	
	static Vertex root = null; 
	
	public static int sizeOf(Vertex t)
	{
	    return t == null ? 0 : t.size;
	}

	public static void upd_size(Vertex t)
	{
	    t.size = sizeOf(t.left) + sizeOf(t.right) + 1;
	}
	
	static Vertex[] Split(Vertex t, int k) {
		if (t == null) {
			return new Vertex[2];
		}
		int cur = sizeOf(t.left) + 1;
		if (k < cur) {
			Vertex[] tmp = Split(t.left, k);
			t.left = tmp[1];
			if (tmp[1] != null) {
				tmp[1].parent = t;
			}
			if (tmp[0] != null) {
				tmp[0].parent = null;
			}
			upd_size(t);
			return new Vertex[] {tmp[0], t};
		} else {
			Vertex[] tmp = Split(t.right, k - cur);
			t.right = tmp[0];
			if (tmp[0] != null) {
				tmp[0].parent = t;
			}
			if (tmp[1] != null) {
				tmp[1].parent = null;
			}
			upd_size(t);
			return new Vertex[] {t, tmp[1]};
		}
	}
	
	static Vertex Merge(Vertex t1, Vertex t2) {
		if (t1 == null) {
			return t2;
		}
		if (t2 == null) {
			return t1;
		}
		if (t1.y > t2.y) {
			t1.right = Merge(t1.right, t2);
			upd_size(t1);
			if (t1.right != null) {
				t1.right.parent = t1;
			}
			return t1;
		} else {
			t2.left = Merge(t1, t2.left);
			upd_size(t2);
			if (t2.left != null) {
				t2.left.parent = t2;
			}
			return t2;
			
		}
	}
	
	static Vertex insert(Vertex t, int k) {
		Vertex nv = new Vertex(k);
		return Merge(t, nv);
	}
	
	static Vertex front(Vertex t, int l, int r) {
		Vertex[] tmp1 = Split(t, l);
		Vertex[] tmp2 = Split(tmp1[1], r - l + 1);
		return Merge(tmp2[0], Merge(tmp1[0], tmp2[1]));
	}
	static ArrayList<Integer> ans = new ArrayList<Integer>();
	static void myprint(Vertex t) {
		if (t != null) {
			myprint(t.left);
			//ans.add(t.x);
			System.out.print(t.x + 1+" ");
			myprint(t.right);
		}
	}
	static int n, m;
	public static void main(String[] args) throws IOException {
		//Scanner in = new Scanner(new File("movetofront.in"));
		//PrintWriter out = new PrintWriter("movetofront.out");
		Scanner sc= new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		root = new Vertex(0);
		for (int i = 1; i < n; i++) {
			root = insert(root, i);
		}
		for (int i = 0; i < m; i++) {
			int l = sc.nextInt() - 1;
			int r = sc.nextInt() - 1;
			root = front(root, l, r);
		}
		myprint(root);
		/*for (int i = 0; i < ans.size(); i++) {
			System.out.print(ans.get(i) + 1 +" ");
		}*/
		//out.close();
	}
}
