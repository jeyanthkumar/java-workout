package com.java.workout;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.codehaus.jackson.JsonFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.java.workout.designpattern.BuilderDesignPattern;
import com.java.workout.designpattern.BuilderDesignPattern2;
import com.java.workout.designpattern.Employee;
import com.java.workout.designpattern.Singleton;


public class CoreJavaWorkout {
	static String fileSeparator = File.separator;
	static String path = System.getProperty("user.dir") + fileSeparator +"src"+ fileSeparator +"main"+ fileSeparator +"java"+ fileSeparator +"com"+ fileSeparator +"java"+ fileSeparator +"workout"+ fileSeparator +"testfile.txt";

	public static void main(String [] args) throws Exception
	{
		

		
		List<Integer> array1= new ArrayList<Integer>(Arrays.asList(1,2,4,5,6,7));
		List<Integer> array2= new ArrayList<Integer>(Arrays.asList(1,2,4,8,9,7));
		for(int i=0; i<array2.size();i++) {
			if(array1.contains(array2.get(i))) {
				array1.remove(array2.get(i));
			}else {
				array1.add(array2.get(i));
			}
		}
		System.out.println("Result : "+array1);
	
		
		
		Function<Integer, Integer> fun = (a)->{return a+5;};
		System.out.println(fun.apply(5));


		String str = "i am jayanth. i live in india.";
		List<String> strList = new ArrayList<String>(Arrays.asList(str.split("\\s")));
		System.out.println("No of words : "+ strList.size());
		System.out.println("No of occurance : "+strList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));

		
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(10,15,8,49,25,98,98,32,10,50));

		System.out.println(list.stream().reduce(Integer::max));
		Map<Integer, Long> map = list.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		Iterator<Entry<Integer, Long>> it1 = map.entrySet().iterator();
		while(it1.hasNext()) {
			Entry<Integer, Long> data = it1.next();
			if(data.getValue()>1) {
				System.out.println("Duplicate : "+data.getKey());
			}
		}
		List<Entry<Integer, Long>> li = list.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting())).entrySet().stream().filter(hm->hm.getValue()>1).collect(Collectors.toList());
		System.out.println("Duplicate : "+li);
		
		System.out.println("----------Recursive call to print 1 to 10----------");
		recursive_call(1);
		System.out.println("\n\n----------Recursive call to print string reverse----------");
		String strVal = "abcdefgh";
		int strValLen = strVal.length();
		recursive_call_1(strVal, strValLen-1);
		System.out.println("\n\n----------Recursive call to print int reverse----------");
		recursive_call_2(1234567);

		System.out.println("\n\n----------Read file using FileReader----------");
		FileReader  fr = new FileReader(path);
		while(true) {
			int data = fr.read();
			if(data<=0)
				break;
			System.out.print((char)data);
		}
		System.out.println("\n\n----------Read file using FileInputStream ----------");
		FileInputStream fis = new FileInputStream(path);
		while(true) {
			int data = fis.read();
			if(data<=0)
				break;
			System.out.print((char)data);
		}
		fis = new FileInputStream(path);
		System.out.println(new String(fis.readAllBytes()));
		System.out.println("\n\n----------Read file using BufferReader ----------");
		BufferedReader br = new BufferedReader(new FileReader(path));
		for(String line; (line = br.readLine()) != null; ) {
			System.out.print(line);
		}
		System.out.println("\n\n----------Read file using BufferReader stream ----------");
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
		byte[] data =  bis.readAllBytes();

		System.out.println(data[0]);
		System.out.println((char)data[0]);
		System.out.print(new String(data));
		bis.close();

		System.out.println("\n\n----------Read file using Files readAllLines ----------");
		List<String> wholeData = Files.readAllLines(Paths.get(path));
		wholeData.stream().forEach(System.out::print);

		System.out.println("\n\n----------Read file using Files lines ----------");
		Stream<String> wholeData1 = Files.lines(Paths.get(path));
		wholeData1.forEach(System.out::print);

		System.out.println("\n\n----------Read file using Files lines with collector joining----------");
		String content = Files.lines(Paths.get(path), StandardCharsets.UTF_8)
				.collect(Collectors.joining(System.lineSeparator()));
		System.out.print(content);

		System.out.println("\n\n----------Write file using FileWriter ----------");
		FileWriter fw = new FileWriter(path);
		fw.write("[{'id':1, 'name':'Jeyanth'}]");
		fw.flush(); // clear the data
		fw.close(); // Close the connection

		System.out.println("\n\n----------Write file using FileWriter ----------");
		FileOutputStream fos = new FileOutputStream(path);
		OutputStreamWriter ow = new OutputStreamWriter(fos);
		ow.write("[");

		for (int i = 0; i < 5; i++) {
			if (i != 0) {
				ow.write(",");
			}
			ow.write("{\"id\":1, \"name\":\"Sakthish\"},");
			ow.write("{\"id\":2, \"name\":\"Jeyanth\"}");

		}
		ow.write("]");
		ow.flush();

		System.out.println("\n\n----------Read file using Jackson ObjectMapper----------");
		File jsonFile = new File(path);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(jsonFile);
		for(int i=0; i<root.size();i++) {
			System.out.print("\t "+i+". ID: "+root.get(i).get("id")+", Name: "+root.get(i).get("name"));
		}

		System.out.println("\n\n----------Read file using Jackson JsonFactory----------");
		File jsFile = new File(path);
		JsonFactory jsonfactory = new JsonFactory(); //init factory
		try {
			int numberOfRecords = 0;
			org.codehaus.jackson.JsonParser jsonParser = jsonfactory.createJsonParser(jsFile); //create JSON parser
			Employee empl = new Employee();
			org.codehaus.jackson.JsonToken jsonToken = jsonParser.nextToken();
			while (jsonToken!= org.codehaus.jackson.JsonToken.END_ARRAY){ //Iterate all elements of array
				String fieldname = jsonParser.getCurrentName(); //get current name of token
				if ("id".equals(fieldname)) {
					jsonToken = jsonParser.nextToken(); //read next token
					empl.setId(Integer.parseInt(jsonParser.getText()));
				}
				if ("name".equals(fieldname)) {
					jsonToken = jsonParser.nextToken(); //read next token
					empl.setName(jsonParser.getText());
				}
				if ("phone".equals(fieldname)) {
					jsonToken = jsonParser.nextToken(); //read next token
					empl.setPhone(Long.parseLong(jsonParser.getText()));
				}
				if(jsonToken==org.codehaus.jackson.JsonToken.END_OBJECT){
					//do some processing, Indexing, saving in DB etc..
					System.out.print(empl.toString());
					empl = new Employee();
					numberOfRecords++;
				}
				jsonToken = jsonParser.nextToken();
			}
			System.out.println("\nTotal Records Found : "+numberOfRecords);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("\n\n----------Read huge file using Gson Stream as multiple chuk----------");
		InputStream inputStream = Files.newInputStream(Path.of(path));
		com.google.gson.stream.JsonReader reader = new com.google.gson.stream.JsonReader(new InputStreamReader(inputStream));

		reader.beginArray();
		while (reader.hasNext()) {
			Employee emp = new Gson().fromJson(reader, Employee.class);
			System.out.print(emp.toString());
		}
		reader.endArray();

		System.out.println("\n\n----------Extract JSON Object data----------");
		JSONArray jsArr = new JSONArray("['jeyanth', 'aathi', 'sakthish']");

		JSONObject jsOb = new JSONObject("{'dept':'CSE'}");
		jsOb.put("name", jsArr);
		System.out.println(jsOb);

		Iterator it = jsOb.keys();
		while(it.hasNext()) {
			String key = (String) it.next();
			if(key.equals("name")) {
				JSONArray value  = jsOb.getJSONArray(key);
				System.out.println("Key: "+key +", Value: "+value);
			}else if(key.equals("dept")) {
				System.out.println("Key: "+key +", Value: "+jsOb.getString(key));
			}
		}

		System.out.println("---------Extract JSON Array data----------");
		jsArr.forEach(System.out::print);
		System.out.println();
		List<Object> lsStr = jsArr.toList();
		lsStr.forEach(System.out::print);
		System.out.println();

		System.out.println("---------Singleton pattern classs----------");
		Employee emp1 = Singleton.getEmpInstance();
		Employee emp2 = Singleton.getEmpInstance();
		Employee emp3 = Singleton.getEmpInstance();
		System.out.println("Emp1 name: "+emp1.getName()+", Emp2 name: "+emp2.getName()+", Emp3 name: "+emp3.getName());
		
		System.out.println("---------Builder pattern with multiple constructor----------");
		BuilderDesignPattern bdp = new BuilderDesignPattern(1,"JK","chennai");
		System.out.println(bdp.toString());
		System.out.println("---------Builder pattern with single constructor----------");
		BuilderDesignPattern2 bdp2 = new BuilderDesignPattern2.Builder(1,"JK").setAddr("Tuti").setPhone("123456").build();
		System.out.println(bdp2.toString());

	}

	private static void recursive_call_2(int i) {
		if(i>0) {
//			System.out.println("III"+i);
			System.out.print(i%10);
			recursive_call_2(i/10);
		}
	}

	private static void recursive_call(int val) {
		System.out.print(val+",");
		val++;
		if(val<=10){
			recursive_call(val);
		}
	}

	private static void recursive_call_1(String strVal, int strValLen) {
		System.out.print(strVal.charAt(strValLen));
		strValLen--;
		if(strValLen>=0)
			recursive_call_1(strVal,strValLen);
	}
}

