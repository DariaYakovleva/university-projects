import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.SortedSet;


public class ArraySet<E> extends AbstractSet<E> implements NavigableSet<E> {

	private List<E> arr;
	Comparator<? super E> comp = null;
	final int LOWER = 0;
	final int UPPER = 1;
	final int BEGIN = 0;
	final int END = 1;
	boolean reverse = false;
	int left = 0;
	int right = -1;

	public ArraySet() {
		arr = new ArrayList<E>();
		right = 0;
	}

	private ArraySet(int l, int r, List<E> arr) {
		this.arr = arr;
		this.left = l;
		this.right = r;
	}

	@SuppressWarnings("unchecked")
	private int compareTwo(E o1, E o2) {
		if (comp != null) {
			return comp.compare(o1, o2);
		}
		return ((Comparable<E>)o1).compareTo(o2);
	}

	public ArraySet(Collection<E> arr, Comparator<? super E> comp) {
		this.arr = new ArrayList<E>(arr);
		this.comp = comp;
		if (comp == null) {
			comp = new Comparator<E>() {
				@Override
				public int compare(E o1, E o2) {
					return compareTwo(o1, o2);
				}
			};
		}
		Collections.sort(this.arr, this.comp);
		List<E> nArray = new ArrayList<>();
		for (int i = 0; i < this.arr.size(); i++) {
			if (nArray.size() == 0 || comp.compare(this.arr.get(i), nArray.get(nArray.size() - 1)) != 0) {
				nArray.add(this.arr.get(i));
			}
		}
		this.arr = nArray;
		right = this.arr.size();
	}

	public ArraySet(Collection<E> arr) {
		this(arr, null);
	}

	@Override
	public Comparator<? super E> comparator() {
		return comp;
	}

	@SuppressWarnings("unchecked")
	public boolean contains(Object o) {
		int pos = bs((E)o, LOWER);
		if (pos >= right) {
			return false;
		}
		return compareTwo(arr.get(pos), (E)o) == 0;
	}

	private E getElem(int where) {
		if ((!reverse && where == BEGIN) || (reverse && where == END)) {
			return getFirst();
		}
		return getLast();
	}

	@Override
	public E first() {
		if (arr.isEmpty()) {
			throw new NoSuchElementException();
		}
		return getElem(BEGIN);
	}

	@Override
	public E last() {
		if (arr.isEmpty()) {
			throw new NoSuchElementException();
		}
		return getElem(END);
	}

	private int bs(E e, int type) {
		if (e == null) {
			throw new NullPointerException();
		}
		int pos = Collections.binarySearch(arr, e, comparator());
		if (pos < 0) {
			pos = ~pos;
		} else {
			if (type == UPPER) {
				pos++;
			}
		}
		return pos;
	}

	@Override
	public E ceiling(E e) {
		int pos = bs(e, LOWER);
		if (pos >= right) {
			return null;
		}
		return arr.get(pos);
	}

	@Override
	public Iterator<E> descendingIterator() {
		return descendingSet().iterator();
	}

	@Override
	public NavigableSet<E> descendingSet() {
		reverse = reverse ^ true;
		return new ArraySet<>(left, right, arr);
	}

	@Override
	public E floor(E e) {
		int pos = bs(e, UPPER);
		if (pos <= left) {
			return null;
		}
		return arr.get(pos - 1);
	}

	@Override
	public E higher(E e) {
		int pos = bs(e, UPPER);
		if (pos >= right) {
			return null;
		}
		return arr.get(pos);
	}


	@Override
	public E lower(E e) {
		int pos = bs(e, LOWER);
		if (pos <= left) {
			return null;
		}
		return arr.get(pos - 1);
	}

	@Override
	public E pollFirst() {
		throw new UnsupportedOperationException();
	}

	@Override
	public E pollLast() {
		throw new UnsupportedOperationException();
	}

	@Override
	public SortedSet<E> subSet(E fromElement, E toElement) {
		return subSet(fromElement, true, toElement, false);
	}

	@Override
	public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
		if (compareTwo(fromElement, toElement) > 0) {
			throw new IllegalArgumentException();
		}
		int fromIndex;
		int toIndex;
		if (!fromInclusive) {
			fromIndex = bs(fromElement, UPPER);
		} else {
			fromIndex = bs(fromElement, LOWER);
		}
		if (!toInclusive) {
			toIndex = bs(toElement, LOWER);
		} else {
			toIndex = bs(toElement, UPPER);
		}
		fromIndex = Integer.max(fromIndex, left);
		toIndex = Integer.min(toIndex, right);
		if (fromIndex > toIndex) {
			return new ArraySet<>();
		}
		return new ArraySet<>(fromIndex, toIndex, arr);
	}

	private NavigableSet<E> getSS(int where, E elem, boolean inclusive) {
		if ((!reverse && where == BEGIN) || (reverse && where == END)) {
			if (left >= bs(elem, (inclusive)?UPPER:LOWER)) {
				return new ArraySet<>();
			}
			return subSet(getFirst(), true, elem, inclusive);
		}
		if (right <= bs(elem, (inclusive)?LOWER:UPPER)) {
			return new ArraySet<>();
		}
		return subSet(elem, inclusive, getLast(), true);
	}

	@Override
	public SortedSet<E> headSet(E toElement) {
		return headSet(toElement, reverse?true:false);
	}

	@Override
	public NavigableSet<E> headSet(E toElement, boolean inclusive) {
		if (arr.isEmpty()) {
			return this;
		}
		return getSS(BEGIN, toElement, inclusive);
	}

	@Override
	public SortedSet<E> tailSet(E fromElement) {
		return tailSet(fromElement, reverse?false:true);
	}

	@Override
	public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
		if (isEmpty()) {
			return this;
		}
		return getSS(END, fromElement, inclusive);
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			int cursor = reverse ? right : left - 1;
			@Override
			public boolean hasNext() {
				if (reverse) {
					return cursor - 1 >= left;
				}
				return cursor + 1 < right;
			}

			@Override
			public E next() {
				if (reverse) {
					cursor--;
					return arr.get(cursor);
				}
				cursor++;
				return arr.get(cursor);
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	@Override
	public int size() {
		return right - left; 
	}

	private E getFirst() {
		return arr.get(left);
	}

	private E getLast() {
		return arr.get(right - 1);
	}

}
