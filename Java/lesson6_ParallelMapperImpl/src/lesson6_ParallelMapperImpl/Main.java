package lesson6_ParallelMapperImpl;

import info.kgeorgiy.java.advanced.mapper.ParallelMapper;

import java.util.ArrayList;
import java.util.Comparator;


public class Main {
	public static void main(String[] args) {
		ParallelMapper pm = new ParallelMapperImpl(5);
		IterativeParallelism it = new IterativeParallelism(pm);
		ArrayList<Integer> arr = new ArrayList<>();
		arr.add(2);
		arr.add(3);
		arr.add(5);
		arr.add(7);
		try {
			System.out.println("min =" + it.minimum(4, arr, Comparator.naturalOrder()));
			pm.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
