import java.util.*;
import java.io.*;

public class Main {

	static class Vertex {
		Vertex parent;
		Vertex left;
		Vertex right;
		int size;
		int x;

		Vertex(int a) {
			x = a;
			parent = null;
			left = null;
			right = null;
			size = 1;
		}
	}

	static Vertex root = null;
	static int n, m;
	
	static int size(Vertex t) {
		return t == null ? 0 : t.size;
	}
	
	static void update_size(Vertex t) {
		t.size = size(t.left) + size(t.right) + 1;
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
					child.right = parent;
				}
				parent.parent = child;
			} else {
				parent.right = child.left;
				if (child.left != null) {
					child.left.parent = parent;
					child.left = parent;
				}
			}
			parent.parent = child;
		}
		child.parent = gparent;
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
		}
		if ((t == parent.left) && (parent == gparent.left)) {
			parent = rotate(gparent, parent);
			t = rotate(parent, t);
		} else {
			t = rotate(parent, t);
			t = rotate(gparent, t);
		}
		return splay(t);
	}

	static Vertex[] Split(Vertex t, int k) {
		if (t == null) {
			return new Vertex[2];
		}
		t = search(t, k);
		if (size(t) <= k) {
			Vertex r = t.right;
			if (r != null) {
				r.parent = null;
			}
			if (t != null) {
				t.right = null;
			}
			update_size(t);
			update_size(r);
			return new Vertex[] { t, r };
		} else {
			Vertex l = t.left;
			if (l != null) {
				l.parent = null;
			}
			if (t != null) {
				t.left = null;
			}
			update_size(t);
			update_size(l);
			return new Vertex[] { l, t };
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
		update_size(t1);
		t2.parent = t1;
		update_size(t2);
		return t1;
	}

	static Vertex insert(Vertex t, int k) {
		Vertex[] tmp = Split(t, k);
		Vertex newt = new Vertex(k);
		newt.left = tmp[0];
		newt.right = tmp[1];
		if (tmp[0] != null) {
			tmp[0].parent = newt;
		}
		if (tmp[1] != null) {
			tmp[1].parent = newt;
		}
		return newt;
	}

	static Vertex search(Vertex t, int key) {
		if (t == null) {
			return null;
		}
		if (size(t) == key) {
			return splay(t);
		}
		if ((size(t) > key) && (t.left != null)) {
			return search(t.left, key);
		}
		if ((size(t) <= key) && (t.right != null)) {
			return search(t.left, key);
		}
		return splay(t);
	}
	
	static Vertex front(Vertex t, int l, int r) {
		Vertex[] tmp1 = Split(t, l - 1);
		Vertex[] tmp2 = Split(tmp1[1], r);
		return Merge(tmp2[0], Merge(tmp1[0], tmp2[1]));
	}
	static ArrayList<Integer> ans = new ArrayList<Integer>();
	static void print(Vertex t) {
		if (t != null) {
			print(t.left);
			print(t.right);
			ans.add(t.x);
		}
	}
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("movetofront.in"));
		PrintWriter out = new PrintWriter("movetofront.out");
		n = in.nextInt();
		m = in.nextInt();
		root = new Vertex(0);
		for (int i = 1; i < n; i++) {
			insert(root, i);
		}
		for (int i = 0; i < m; i++) {
			int l = in.nextInt();
			int r = in.nextInt();
			root = front(root, l, r);
		}
		print(root);
		for (int i = 0; i < ans.size(); i++) {
			out.print(ans.get(i)+" ");
		}
		out.close();
	}
}
