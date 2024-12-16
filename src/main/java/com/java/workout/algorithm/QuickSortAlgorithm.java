package com.java.workout.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuickSortAlgorithm {

	static Map<Integer,Integer> finalSortedMap = new HashMap<Integer, Integer>();

	public static void main(String[] args) {
		List<Integer> unsortedList = new ArrayList<Integer>(Arrays.asList(100,5,2,8,1,75,-1,50,4,7));

		System.out.println("Before sorting...\n"+unsortedList);
		unsortedList = doSorting(unsortedList, 0, unsortedList.size()-1, unsortedList.size()-1, unsortedList.size()-1);
		System.out.println("After sorting...\n"+unsortedList);

	}

	public static List<Integer> doSorting(List<Integer> unsortedList, int leftIndex, int rightIndex, int pivotIndex, int previousPivousIndex){

		int leftValue = unsortedList.get(leftIndex);
		int rightValue = unsortedList.get(rightIndex);
		int pivotValue = unsortedList.get(pivotIndex);
		//		boolean sortFlag = false;

		System.out.println("Left index: "+leftIndex+" Right index: "+rightIndex+" Pivot index: "+pivotIndex);
		if(pivotIndex==leftIndex && pivotIndex==rightIndex) {
//			finalSortedMap.put(previousPivousIndex, unsortedList.get(pivotIndex));
//			System.out.println("finalSortedMap: "+finalSortedMap);
//			System.out.println("Pivot position finalized..."+unsortedList);

			List<Integer> leftArray = new ArrayList<Integer>();
			List<Integer> rightArray = new ArrayList<Integer>();

			for(int i=0; i<unsortedList.size();i++) {
				if(i<pivotIndex) {
					leftArray.add(unsortedList.get(i));
				}else if(i>pivotIndex) {
					rightArray.add(unsortedList.get(i));
				}
			}
			System.out.println("Left Array: "+leftArray);
			System.out.println("Right Array: "+rightArray);
			if(leftArray.size()>1) {
				finalSortedMap.put(previousPivousIndex, unsortedList.get(pivotIndex));
				System.out.println("finalSortedMap0: "+finalSortedMap);
				System.out.println("Pivot position finalized..."+unsortedList);
				doSorting(leftArray, 0, pivotIndex-1, pivotIndex-1, pivotIndex);
			}else if(leftArray.size()==1) {
				System.out.println("IndexLeft: "+(leftArray.size()-1) +" Value: "+leftArray.get(pivotIndex-1));
				finalSortedMap.put(previousPivousIndex+pivotIndex, leftArray.get(pivotIndex-1));
				System.out.println("finalSortedMap1: "+finalSortedMap);
			}
			if(rightArray.size()>1) {
				finalSortedMap.put(previousPivousIndex, unsortedList.get(pivotIndex));
				System.out.println("finalSortedMap2: "+finalSortedMap);
				System.out.println("Pivot position finalized..."+unsortedList);
				System.out.println("Left Index: "+0+" Right Index: "+(rightArray.size()-1)+" Pivot Index: "+(rightArray.size()-1)+" previousPivousIndex: "+(leftArray.size()-1)+" previousPivousIndex+pivotIndex: "+(previousPivousIndex+rightArray.size()-1));
				doSorting(rightArray, 0, rightArray.size()-1, rightArray.size()-1, ((leftArray.size()-1))+rightArray.size()-1);
			}
			else if(rightArray.size()==1) {
				System.out.println("IndexRight: "+(leftArray.size()+pivotIndex) +" Value: "+rightArray.get(rightArray.size()-1));
				finalSortedMap.put((leftArray.size()+pivotIndex), rightArray.get(rightArray.size()-1));
				System.out.println("finalSortedMap3: "+finalSortedMap);
			}

		}
		else if(pivotIndex==leftIndex) {
			if(unsortedList.get(rightIndex)<unsortedList.get(pivotIndex)) {
				//				sortFlag = true;
				unsortedList.remove(pivotIndex);
				unsortedList.add(pivotIndex, rightValue);
				unsortedList.remove(rightIndex);
				unsortedList.add(rightIndex, pivotValue);
				doSorting(unsortedList, leftIndex, rightIndex, rightIndex, pivotIndex);
			}else {
				doSorting(unsortedList, leftIndex, rightIndex-1, leftIndex, pivotIndex);
			}

		}else if(pivotIndex==rightIndex) {
			if(unsortedList.get(leftIndex)>unsortedList.get(pivotIndex)) {
				//				sortFlag = true;
				unsortedList.remove(pivotIndex);
				unsortedList.add(pivotIndex, leftValue);
				unsortedList.remove(leftIndex);
				unsortedList.add(leftIndex, pivotValue);
				doSorting(unsortedList, leftIndex, rightIndex, leftIndex, pivotIndex);
			}else {
				doSorting(unsortedList, leftIndex+1, rightIndex, rightIndex, pivotIndex);
			}
		}

		return unsortedList;
	}

}
