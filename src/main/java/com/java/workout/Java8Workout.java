package com.java.workout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Java8Workout {

	public static void main(String[] args) {

		System.out.println("*****************************Iterator*************************************");
		HashMap<Integer, String> hm = new HashMap<Integer, String>();
		hm.put(1, "aaaa");
		hm.put(2, "bbbb");
		hm.put(3, "cccc");

		HashMap<Integer, String> hm1 = new HashMap<Integer, String>();
		hm1.put(1, "aaaa");
		hm1.put(2, "bbbb");
		hm1.put(3, "cccc");

		HashMap<Integer, String> hm2 = new HashMap<Integer, String>();
		hm2.put(1, "zzzz");
		hm2.put(2, "bbbb");
		hm2.put(3, "cccc");

		Iterator<Integer> it = hm.keySet().iterator();
		while(it.hasNext()) {
			Integer key = it.next();
			System.out.println("Key: "+key+" Value: "+hm.get(key));
		}
		System.out.println("************************Imutable collection************************");
		//		Map<Integer, String> hm2 = Collections.unmodifiableMap(hm);
		//		hm2.put(3, "cccc");
		//		Set<String> set = Set.of("ab","cd","de");
		//		set.add("jj");

		Function<String, Boolean> func = (x) -> x.contains("mk");
		Boolean apply = func.apply("mkyong");   // 6
		System.out.println("Function apply method result: "+apply);

		Function<String, Boolean> func1 = x -> {x.contains("mk"); return false;};
		Boolean apply1 = func1.apply("mkyong");
		System.out.println("Function apply method result: "+apply1);

		Predicate<String> pred = x -> x.contains("mkyong");
		System.out.println("Predicate test method result: "+pred.test("mkyong"));

		Predicate<String> pred1 = x -> {x.contains("mk"); return false;};
		System.out.println("Predicate test method result: "+pred1.test("mkyong"));

		Consumer<String> consume = x -> System.out.println(x);
		consume.accept("Consumer accept method result: "+"uuuuu");

		Consumer<String> consume1 = x -> {String a= x.toUpperCase(); System.out.println(a);};
		consume1.accept("Consumer accept method result: "+"uuuuu");

		Supplier<String> sup = () ->  {hm1.get(1);System.out.println(hm1.get(1));return hm1.get(1);};
		System.out.println("Supplier get method result: "+sup.get());

		Comparator<String> comp = (String a, String b)->a.compareTo(b);
		System.out.println("Comparator it returns first differ char result "
				+ "here c,a difference is: "+comp.compare("mkcyong","mkayong"));

		System.out.println("*****************************Comparing String using ==, equals(), hashcode()***************************");
		//		String s = new String("jeyanth").intern();
		//		String s1 = new String("jeyanth").intern();
		String s = new String("jeyanth");
		String s1 = "jeyanth";
		int reslt = s1.compareTo(s);
		System.out.println(reslt);

		//				if(s==s1) { //It refers the object   o/p: false
		if(s.equals(s1)) { //It refers object value & return type is boolean   o/p: true
			//		if(0==s.compareTo(s1)) { //It refers object value & return type is 0, positive, negative used for sorting   o/p: true

			System.out.println("Strings s & s1 are equal");
		}else {
			System.out.println("Strings s & s1 are NOT equal");
		}

		Book e1 = new Book("1","jk",hm);
		Book e2 = new Book("1","jk",hm1);
		Book e3 = new Book("4","RJ",hm);
		Book e4 = new Book("2","AS",hm);
		Book e5 = new Book("1","jk",hm2);
		Book e6 = new Book("2","rj",hm);
		Book e7 = new Book("2","as",hm);

		//		if(e1==e2) { //It refers the object     o/p: false
		if(e1.equals(e2)) { //It refers object value & return type is boolean    o/p: true
			//		if(e1.equals(e5)) { //It refers object value & return type is boolean    o/p: false
			//		if(0==e1.getBookName().compareTo(e2.getBookName())) { //It refers object value & return type is 0, positive, negative used for sorting   o/p: true
			System.out.println("e1 and e2 are same");
		}else {
			System.out.println("e1 and e2 are different");
		}

		HashSet<Book> book = new HashSet<Book>();
		book.add(e1);
		System.out.println("Hashset contains book: "+book.contains(e2));//If hashcode() method not implemented then it returns false.
		System.out.println("Book.hashCode():  " + e1.hashCode()
		+ "  Book2.hashCode():" + e2.hashCode());

		System.out.println("*************************************String Join************************************");
		List<String> listStr = new ArrayList<String>(Arrays.asList("j","e","y"));
		System.out.println(String.join(",", listStr));
		StringJoiner sj = new StringJoiner(",");
		sj.add("j");
		sj.add("e");
		sj.add("y");
		System.out.println(sj);

		System.out.println("*************************************Collection Sorting************************************");
		List<Book> books = new ArrayList<Book>(Arrays.asList(e1,e2,e3,e4,e5,e6,e7));

		System.out.println("Sorting using functional interface comparator :: "+books.stream().map(b->b.getBookName()).sorted((a, b)->a.compareTo(b)).collect(Collectors.toList()));
		System.out.println("Sorting using without functional interface comparator  with object level comparison :: "+ books.stream().map(b->b.getBookName()).sorted(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		}).collect(Collectors.toList()));
		System.out.println("Sorting using without functional interface comparator  with bookname  level comparison :: "+ books.stream().sorted(new Comparator<Book>() {
			@Override
			public int compare(Book o1, Book o2) {
				return o1.getBookName().compareTo(o2.getBookName());
			}
		}).map(b->b.toString()).collect(Collectors.toList()));

		List<Integer> nums1 = new ArrayList<Integer>(Arrays.asList(2,67,3,1,5));
		System.out.print("Integer sorting :: ");
		nums1.stream().sorted((a,b)->b-a).collect(Collectors.toList()).forEach(e->{if(true) {System.out.print(e+",");System.out.print(" ");}});
		List<String> numList = nums1.stream().sorted((a,b)->a-b).map(a->a.toString()).collect(Collectors.toList());
		System.out.println(numList);
		System.out.println(String.join(",", numList));
		Map<String, Long> mp = books.stream().collect(Collectors.groupingBy(Book::getBookName, Collectors.counting()));
		System.out.println("Get count using groupingBy counting & result of map value :: "+mp);
		Function<Book, String> compositeKey = bookRecord -> bookRecord.getBookName()+","+ bookRecord.getId();
		Map<String, Long> mp3 = books.stream().collect(Collectors.groupingBy(compositeKey, Collectors.counting()));
		System.out.println("Get count using groupingBy counting & result of map value :: "+mp3);
		Optional<Long> max = books.stream().collect(Collectors.groupingBy(Book::getBookName, Collectors.counting())).entrySet().stream().map(e->e.getValue()).reduce(Long::max);
		System.out.println("Get count using groupingBy counting & result of max value :: "+max.orElse(0L));
		Optional<Entry<String, Long>> mp1 = books.stream().collect(Collectors.groupingBy(Book::getBookName, Collectors.counting())).entrySet().stream().sorted((a,b)->b.getValue().intValue()-a.getValue().intValue()).limit(2).skip(1).findFirst();
		System.out.println("Get count using groupingBy counting & result of second max value  of first :: "+mp1.get());
		List<Entry<String, Long>> mp2 = books.stream().collect(Collectors.groupingBy(Book::getBookName, Collectors.counting())).entrySet().stream().sorted((a,b)->b.getValue().intValue()-a.getValue().intValue()).skip(1).collect(Collectors.toList());
		System.out.println("Get count using groupingBy counting & result of second max value  of all :: "+mp2);
		Optional<Integer> sum = books.stream().map(b->Integer.parseInt(b.getId())).collect(Collectors.reducing(Integer::sum));
		Optional<Float> avg = books.stream().map(b->Float.parseFloat(b.getId())).reduce(Book::avg);
		System.out.println("Sum :: "+sum.get());
		System.out.println("Avg :: "+avg.get());
		System.out.println("Get count using groupingBy with map :: "+ books.stream().map(b->b.getId()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
		System.out.println("Get count using groupingBy without map & using method reference :: "+ books.stream().collect(Collectors.groupingBy(Book::getBookName, Collectors.counting())));
		System.out.println("Get object count using groupingBy :: "+ books.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
		System.out.println("Get row count :: "+ books.stream().map(b->b.getBookName()).count());

		Long lg = null;
		Optional<Long> op = Optional.ofNullable(lg);
		//		Optional<Long> op1 = Optional.of(lg); // At this line of execution will get null pointer exception
		System.out.println("Option with OR condition :: "+op.orElse(1111L));
		Supplier<Long> defaultValue = ()->{return 0L;};
		System.out.println("Option with OR condition with logic code :: "+op.orElseGet(defaultValue));

		//		System.out.println( books.stream().flatMap(b->b.getBookName()).collect(Collectors.toList()));

		List<Integer> PrimeNumbers = Arrays.asList(5, 7, 11,13);
		List<Integer> OddNumbers = Arrays.asList(1, 3, 5);
		List<Integer> EvenNumbers = Arrays.asList(2, 4, 6, 8);
		List<List<Integer>> listOfListofInts =
				Arrays.asList(PrimeNumbers, OddNumbers, EvenNumbers);

		System.out.println("The Structure before flattening is : " +
				listOfListofInts);

		// Using flatMap for transformating and flattening.
		List<Integer> listofInts  = listOfListofInts.stream()
				.flatMap(list -> list.stream())
				.collect(Collectors.toList());

		System.out.println("The Structure after flattening is : " +
				listofInts);

		List<Integer> listofIntsWithOrdering  = listOfListofInts.stream()
				.flatMap(list -> list.stream()).sorted((a,b)->a-b)
				.collect(Collectors.toList());

		System.out.println("The Structure after flattening with ordering is : " +
				listofIntsWithOrdering);

		System.out.println("Integer sum using method reference :: "+IntStream.rangeClosed(2, 10).filter(num->num>1).reduce(6, Integer::sum));
		System.out.println("Integer sum using functional interface :: "+IntStream.rangeClosed(2, 10).filter(num->num>1).reduce(0, (a,b)-> a+b));
		System.out.println("String sum using functional interface :: "+listStr.stream().reduce("", (a,b)-> {return a+b+",";}));

		System.out.println("Array to List conversion using java8 & normal");
		Integer[] ints = {1,2,3};
		List<Integer> list = Arrays.asList(ints);
		List<Integer> list1 = Arrays.stream(ints).filter(e->e>1).collect(Collectors.toList());
		list.forEach(e->System.out.print(e+","));
		System.out.println();
		list1.forEach(e->System.out.print(e+","));


		System.out.println("\n\nBefore Remove even element from linked list...");
		LinkedList<Integer> nums  = new LinkedList<>(Arrays.asList(1,2, 3,4, 5,6,8,9));
		nums.forEach(e->System.out.print(e+","));
		for(int i = nums.size() - 1; i >= 0; --i) 
		{
			if((nums.get(i)%2) == 0)
			{
				nums.remove(i);
			}
		}
		System.out.println("\nAfter Remove even element from linked list...");
		nums.forEach(e->System.out.print(e+","));


		System.out.println("\nBefore Remove even element from array list...");
		List<Integer> numsList  = new ArrayList<>(Arrays.asList(1,2, 3,4, 5,6,8,9));
		numsList.forEach(e->System.out.print(e+","));
		for(int i = numsList.size() - 1; i >= 0; --i) 
		{
			if((numsList.get(i)%2) == 0)
			{
				numsList.remove(i);
			}
		}
		System.out.println("\nAfter Remove even element from array list...");
		numsList.forEach(e->System.out.print(e+","));
		System.out.println();


		//		List<String> lStr = new ArrayList<String>();
		//		lStr.add("jk");
		String str = "jteyanthkumar";
		char c[] = str.toCharArray();
		List<Character> lStr = new ArrayList<Character>();


		int i = 0;
		for (char value : c) { 
			lStr.add( Character.valueOf(value)); 
		} 
		Map<Character, Long> hmp = lStr.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println("char count :"+lStr.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
		System.out.println("char count :"+hmp);

		String word = "My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar My another name is JK My name is Jeyanthkumar ";
		String[] wordArr = word.split(" ");
		System.out.println("Single stream Time before: "+new Date().getTime());
		System.out.println("Word count :"+Arrays.stream(wordArr).filter(w->w.contains("n")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
		System.out.println("Single stream Time after: "+new Date().getTime());
		System.out.println("Word count :"+Arrays.stream(wordArr).parallel().filter(w->w.contains("n")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
		System.out.println("Parallel stream Time after: "+new Date().getTime());
		
		TestFunctionalInterface fucInt = new TestFunctionalInterface();
		fucInt.callDefaultMethod();
	}

	
}

class TestFunctionalInterface implements i2,i1{

	@Override
	public String m1() {
		return "1";
	}
	
	public void callDefaultMethod() {
		String res = i2.super.d1();
		System.out.println("After called default response: "+res);
		String res1 = i2.super.d2();
		System.out.println("After called default2 response: "+res1);
		i1.s1();
		
		i1 i = (()->{return "abstruct method 2";});
		System.out.println(i.m1());
		
		callAbstructMethod(()->{return "abstruct method 3";});
		
		Book b = new Book("1", "English", null);
		i1 i1  = b::getBookName;
		System.out.println("Method reference call: "+i1.m1());
	}
	
	public void callAbstructMethod(i1 i) {
		System.out.println(i.m1());
	}
	
}

@FunctionalInterface
interface i1{
	public String m1();
	static void s1() {
		System.out.println("Static method...");
	};
	default String d1() {
		System.out.println("Default method...");
		return "Default";
	};
}

@FunctionalInterface
interface i2 extends i1{
	//	void m2(); -- Not an functional interface
	
	default String d2() {
		System.out.println("Default method 2...");
		return "Default2";
	};
}



class Book{
	String id;
	String BookName;
	HashMap<Integer, String> hm;


	public Book(String id, String BookName,HashMap<Integer, String> hm) {
		super();
		this.id = id;
		this.BookName = BookName;
		this.hm =  hm;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBookName() {
		return BookName;
	}
	public void setBookName(String BookName) {
		this.BookName = BookName;
	}

	public HashMap<Integer, String> getHm() {
		return hm;
	}

	public void setHm(HashMap<Integer, String> hm) {
		this.hm = hm;
	}

	public static int sum(int a, int b) {
		return a + b;
	}

	public static float avg(float a, float b) {
		return (a + b)/2;
	}

	@Override
	public int hashCode() {
		return Objects.hash(BookName, hm, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(BookName, other.BookName) && Objects.equals(hm, other.hm) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", BookName=" + BookName + ", hm=" + hm + "]";
	}


}
