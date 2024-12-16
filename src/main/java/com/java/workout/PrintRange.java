package com.java.workout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

public class PrintRange {
	
	static  dog dog = new streetDog();

	public static void main(String[] args) {
		dog.sound();
		
		List<Long> input = new ArrayList<Long>(Arrays.asList(9994881000L,9994881002L,9994881006L,9994881008L,9994881009L,9994881010L,9994881001L,9994881003L));
//		System.out.println("range 9994881000-1003 ::"+input.stream().map(d->d.substring(6)).filter(e -> Integer.parseInt(e)>=1000 && Integer.parseInt(e)<=1003).collect(Collectors.toList()));
		input = input.stream().sorted().collect(Collectors.toList());
		System.out.println(input);
		
		long prevValue = 0;
		long startRange = input.get(0);
		for(int i=0; i<input.size(); i++) {
			if(i!=0 && !((prevValue+1)==input.get(i))) {
				System.out.println("Range: "+startRange+"-"+String.valueOf(prevValue).substring(6));
				startRange = input.get(i);
			}
			
			if(i==(input.size()-1)) {
				System.out.println("Range: "+startRange+"-"+String.valueOf(input.get(i)).substring(6));
			}
			
			prevValue = input.get(i);
		}
	}

}

class dog{
	void sound() {
		System.out.println("I'm common dog");
	};
}

class streetDog extends dog{
	@Override
	public void sound() {
		System.out.println("I'm street dog");
		
	}
}
class houseDog extends dog{
	@Override
	public void sound() {
		System.out.println("I'm house dog");
		
	}
}