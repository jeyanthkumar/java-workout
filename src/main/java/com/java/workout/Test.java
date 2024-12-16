package com.java.workout;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {

		String input = "14486779";

		char[] chArr = input.toCharArray();
		int sum = 0;

		for(int i=0;i<chArr.length;i++) {
			sum += Integer.parseInt(String.valueOf(chArr[i]));

		}
		int avg = sum/chArr.length;
		System.out.println("Sum: "+sum+", avg: "+avg);
		System.out.println("Number greater than average");

		int near = 0;

		for(int i=0;i<chArr.length;i++) {
			int val = Integer.parseInt(String.valueOf(chArr[i]));
			if(near==0) {
				near = val;
			}
			if((val-avg)<near) {
				near = val;
			}

			if(val>avg) {
				System.out.print(chArr[i]+",");

			}
		}
		System.out.println("Final near maximum: "+near);
		
		Character[] charArr = input.chars().mapToObj(c -> (char)c).toArray(Character[]::new); 
		System.out.println("Final second near maximum:"+Arrays.asList(charArr).stream().sorted((a,b)->Integer.parseInt(String.valueOf(b))-Integer.parseInt(String.valueOf(a))).skip(1).findFirst().get());
		//		String input = "{}{";
		//		
		//		int openCurl = 0;	
		//		
		//		char[] chArr = input.toCharArray();
		//
		//		for(int i=0; i<chArr.length; i++){
		//			if(chArr[i]=='{') {
		//				openCurl++;
		//			}
		//			if(chArr[i]=='}') {
		//				if(openCurl>0) {
		//					openCurl--;
		//				}else {
		//					System.out.println("Expression is invalid!!!!");
		//				}
		//				
		//			}
		//		}
		//		if(openCurl==0)
		//			System.out.println("Expression is valid!!!!");
		//		else
		//			System.out.println("Expression is invalid!!!!");
		//		
		//		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		//		hm.put("{",0);
		//		hm.put("}",0);
		//		hm.put("(",0);
		//		hm.put(")",0);
		//		hm.put("[",0);
		//		hm.put("]",0);
		//
		//		char[] chArr = input.toCharArray();
		//
		//		for(int i=0; i<chArr.length; i++){
		//			hm.put(String.valueOf(chArr[i]), hm.get(String.valueOf(chArr[i])).intValue()+1);
		//		}
		//
		//		if(hm.get("{")!= hm.get("}")){
		//			System.out.println("Expression is invalid!!!");
		//
		//		}else if(hm.get("[")!= hm.get("]")){
		//			System.out.println("Expression is invalid!!!");
		//
		//		} else if(hm.get("(")!= hm.get(")")){
		//			System.out.println("Expression is invalid!!!");
		//		}else {
		//			System.out.println("Expression is valid!!!!!");
		//		}
	}

}
