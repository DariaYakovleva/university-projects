import java.util.*;
import java.io.*;


public class BST_Treap {

	static Random rnd = new Random(1);
	
	static class Vertex {
		int x;
		int y;
		Vertex parent;
		Vertex left;
		Vertex right;
		Vertex(int a) {
			x = a;
			y = rnd.nextInt();
			parent = null;
			left = null;
			right = null;
		}
	}
	
	static Vertex root = null; 
	
	static Vertex[] Split(Vertex t, int k) {
		if (t == null) {
			return new Vertex[2];
		}
		if (k < t.x) {
			Vertex[] tmp = Split(t.left, k);
			t.left = tmp[1];
			if (tmp[1] != null) {
				tmp[1].parent = t;
			}
			if (tmp[0] != null) {
				tmp[0].parent = null;
			}
			return new Vertex[] {tmp[0], t};
		} else {
			Vertex[] tmp = Split(t.right, k);
			t.right = tmp[0];
			if (tmp[0] != null) {
				tmp[0].parent = t;
			}
			if (tmp[1] != null) {
				tmp[1].parent = null;
			}
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
			if (t1.right != null) {
				t1.right.parent = t1;
			}
			return t1;
		} else {
			t2.left = Merge(t1, t2.left);
			if (t2.left != null) {
				t2.left.parent = t2;
			}
			return t2;
			
		}
	}
	
	static Vertex insert(Vertex t, int k) {
		Vertex nv = new Vertex(k);
		Vertex[] tmp = Split(t, k);
		return Merge(Merge(tmp[0], nv), tmp[1]);
	}
	
	static Vertex deletev(Vertex t, int k) {
		Vertex[] tmp = Split(t, k);
		Vertex[] tmp2 = Split(tmp[0], k - 1);
		return Merge(tmp2[0], tmp[1]);
	}
	
	static Vertex search(Vertex t, int key) {
	    if (t.x == key) {
	        return t;
	    }
	    if (t.x > key) {
	        if (t.left == null) {
	            return t;
	        } else {
	            return search(t.left, key);
	        }
	    } else {
	        if (t.right == null) {
	            return t;
	        } else {
	            return search(t.right, key);
	        }
	    }
	}

	static Vertex next(int a)
	{
	    if (root == null)
	        return null;
	    Vertex t = search(root, a);
	    if (t.x <= a) {
	        if (t.right != null) {
	            t = t.right;
	            while (t.left != null) {
	                t = t.left;
	            }
	            return t;
	        }
	        while (t.parent != null && t == t.parent.right) {
	            t = t.parent;
	        }
	        return t.parent;
	    }
	    return t;
	}

	static Vertex prev(int a)
	{
	    if (root == null)
	        return null;
	    Vertex t = search(root, a);
	    if (t.x >= a) {
	        if (t.left != null) {
	            t = t.left;
	            while (t.right != null) {
	                t = t.right;
	            }
	            return t;
	        }
	        while (t.parent != null && t == t.parent.left) {
	            t = t.parent;
	        }
	        return t.parent;
	    }
	    return t;
	}
	
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("bst.in"));
		PrintWriter out = new PrintWriter("bst.out");

		while (in.hasNext()) {
			String s = in.next();
			int a = in.nextInt();
			if (s.charAt(0) == 'i') {
				if (root == null) {
					root = new Vertex(a);
				} else {
					if (search(root, a).x != a) {
						root = insert(root, a);
					}
				}
	        }
	        if (s.charAt(0) == 'd') {
	            if ((root != null) && (search(root, a).x == a)) {
	                root = deletev(root, a);
	            }
	        }
	        if (s.charAt(0) == 'e') {
	            if ((root != null) && (search(root, a).x == a)) {
	                out.println("true");
	            } else {
	                out.println("false");
	            }
	        }
	        if (s.charAt(0) == 'n') {
	            Vertex res = next(a);
	            if (res == null) {
	            	out.println("none");
	            } else {
	            	out.println(res.x);
	            }
	        }
	        if (s.charAt(0) == 'p') {
	        	Vertex res = prev(a);
	            if (res == null) {
	            	out.println("none");
	            } else {
	            	out.println(res.x);
	            }
	        }
		}
		out.close();
	}
}
