import java.util.*;
import java.io.*;


public class MovetoFront {
	public static void main(String[] args) {
		new Thread(null, new Runnable() {
			@Override
			public void run() {
				try {
					MovetoFrontImplementation.run();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}, "Move", 1 << 27).start();
	}
}

class MovetoFrontImplementation {
	
	static class Vertex {
		int x;
//		int y;
		Vertex parent;
		Vertex left;
		Vertex right;
		int size;
		Vertex(int a) {
			x = a;
//			y = rnd.nextInt();
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
		if (t != null) {
			t.size = sizeOf(t.left) + sizeOf(t.right) + 1;
		}
	}
	
	static Vertex rotate(Vertex parent, Vertex child) {
		Vertex gparent;
		if (parent != null) {
			gparent = parent.parent;
			if (gparent != null) {
				if (gparent.left == parent) {
					gparent.left = child;
				} else {
					gparent.right = child;
				}
			}
		} else {
			gparent = null;
		}
		if (parent != null) {
			if (parent.left == child) {
				parent.left = child.right;
				if (child.right != null) {
					child.right.parent = parent;
				}
				child.right = parent;
			} else {
				parent.right = child.left;
				if (child.left != null) {
					child.left.parent = parent;
				}
				child.left = parent;
			}
			parent.parent = child;
		}
		child.parent = gparent;
		upd_size(parent);
		upd_size(child);
		upd_size(gparent);
		return child;
	}

	static Vertex splay(Vertex t) {
		if (t.parent == null) {
			return t;
		}
		Vertex parent = t.parent;
		Vertex gparent = parent.parent;
		if (gparent == null) {
			t = rotate(parent, t);
		} else {
			if ((t == parent.left) && (parent == gparent.left)) {
				parent = rotate(gparent, parent);
				t = rotate(parent, t);
			} else {
				t = rotate(parent, t);
				t = rotate(gparent, t);
			}
		}
		return splay(t);
	}
	
	
	static Vertex search(Vertex t, int key) {
		if (t == null) {
			return null;
		}
		int cnt = sizeOf(t.left) + 1;
		if (cnt == key) {
			return splay(t);
		}
		if ((cnt > key) && (t.left != null)) {
			return search(t.left, key);
		}
		if ((cnt < key) && (t.right != null)) {
			return search(t.right, key - cnt);
		}
		return splay(t);
	}
	
	static Vertex[] Split(Vertex t, int k) {
		if (t == null) {
			return new Vertex[2];
		}
		t = search(t, k);
		if (k < sizeOf(t.left) + 1) {
			Vertex l = t.left;
			if (l != null) {
				l.parent = null;
			}
			if (t != null) {
				t.left = null;
			}
			upd_size(t);
			upd_size(l);
			return new Vertex[] { l, t };
		} else {

			Vertex r = t.right;
			if (r != null) {
				r.parent = null;
			}
			if (t != null) {
				t.right = null;
			}
			upd_size(t);
			upd_size(r);
			return new Vertex[] { t, r };
		}
	}
	
	static Vertex maximum(Vertex t) {
		while (t.right != null) {
			t = t.right;
		}
		return t;
	}

	static Vertex Merge(Vertex t1, Vertex t2) {
		if (t1 == null) {
			return t2;
		}
		if (t2 == null) {
			return t1;
		}
		t1 = splay(maximum(t1));
		t1.right = t2;
		upd_size(t1);
		t2.parent = t1;
		upd_size(t2);
		return t1;
	}

	
	static Vertex insert(Vertex t, int k) {
		Vertex[] tmp = Split(t, k);
		Vertex newt = new Vertex(k);
		newt.left = tmp[0];
		newt.right = tmp[1];
		upd_size(newt);
		if (tmp[0] != null) {
			tmp[0].parent = newt;
		}
		if (tmp[1] != null) {
			tmp[1].parent = newt;
		}
		return newt;
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
			ans.add(t.x);
			myprint(t.right);
		}
	}
	static int n, m;
	public static void run() throws IOException {
		
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		//Scanner sc= new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		root = new Vertex(0);
		for (int i = 1; i < n; i++) {
			root = insert(root, i);
		}
		for (int i = 0; i < m; i++) {
			int l = in.nextInt() - 1;
			int r = in.nextInt() - 1;
			root = front(root, l, r);
		}
		myprint(root);
		for (int i = 0; i < ans.size(); i++) {
			out.print(ans.get(i) + 1 +" ");
		}
		out.close();
	}
}
