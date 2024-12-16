package com.java.workout.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BubbleSortAlgorithm {

	public static void main(String[] args) {
		List<Integer> unsortedList = new ArrayList<Integer>(Arrays.asList(100,5,2,7,50,8,1,4,75,-1));
		
		System.out.println("Before sorting...\n"+unsortedList);
		unsortedList = doSorting(unsortedList);
		System.out.println("After sorting...\n"+unsortedList);
	}
	
	public static List<Integer> doSorting(List<Integer> unsortedList){
		Boolean swapFlag = false;
		for(int i=0; i<unsortedList.size()-1; i++) {
			int left = unsortedList.get(i);
			int right = unsortedList.get(i+1);
			
			if(right<left) {
				swapFlag = true;
				unsortedList.remove(i);
				unsortedList.add(i, right);
				unsortedList.remove(i+1);
				unsortedList.add(i+1, left);
			}
			System.out.println("After swap: "+unsortedList);
		}
		
		if(!swapFlag)
			return unsortedList;
		else
			doSorting(unsortedList);
		
		return unsortedList;
	}

}
