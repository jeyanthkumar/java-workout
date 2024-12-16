package com.java.workout.algorithm;

public class SingleLinkedList {

	Node head; 

	SingleLinkedList insert(SingleLinkedList list, String data) {

		Node temp = list.head;
		while(temp != null) {
			if(temp.next == null) {
				System.out.println("Next node added!!!");
				System.out.println(":::"+data);
				temp.next = new Node(data);
				break;
			}
			temp =  temp.next;
		}

		if(list.head == null) {
			System.out.println("Head node added!!!");
			System.out.println(":::"+data);
			list.head = new Node(data);
		}
		return list;
	}
	
	SingleLinkedList insertByPosition(SingleLinkedList list, String data, int index) {

		Node last = list.head;
		Node prev = null;
		int currentIndex = 0;
		Node newNode = new Node(data);
		
		while(last != null) {
			if(currentIndex==index) {
				if(prev!=null) {
					newNode.next = last.next;
					prev.next = newNode;
				}
				else {
					newNode.next = last;
					list.head = newNode;
				}
			}
			prev = last;
			last =  last.next;
			currentIndex++;
		}
		
		if(index>=currentIndex) {
			prev.next = newNode;
		}

		return list;
	}

	SingleLinkedList delete(SingleLinkedList list, String data) {

		Node last = list.head;
		Node prev = null;

		while(last != null) {
			if(last.data.equals(data)) {
				if(prev!=null) {
					prev.next = last.next;
					last = prev;
				}
				else {
					list.head = last.next;
				}
			}
			prev = last;
			last =  last.next;
		}

		return list;
	}
	
	SingleLinkedList deleteByPosition(SingleLinkedList list, int index) {

		Node last = list.head;
		Node prev = null;
		int currentIndex = 0;

		while(last != null) {
			if(currentIndex==index) {
				if(prev!=null) {
					prev.next = last.next;
					last = prev;
				}
				else {
					list.head = last.next;
				}
			}
			prev = last;
			last =  last.next;
			currentIndex++;
		}

		return list;
	}

	public static void main(String[] args) {
		System.out.println("Started...");

		SingleLinkedList list = new SingleLinkedList();
		list.insert(list, "JK");
		list.insert(list, "JK");
		list.insert(list, "AS");
		list.insert(list, "TA");
		list.insert(list, "TA2");
		list.insert(list, "TA2");
		list.insert(list, "TA2");
		list.insert(list, "TA2");
		list.insert(list, "TA2");
		list.insert(list, "TA");
		list.insert(list, "AS2");
		list.insert(list, "AS2");
		list.insert(list, "AS23");
		
		list.insertByPosition(list, "JK1", 0);
		list.insertByPosition(list, "JK1", 3);

		Node temp = list.head;
		while(temp != null) {
			System.out.println("Element >> "+temp.data);
			temp =  temp.next;
		}

		list.delete(list, "TA2");
		System.out.println("Deleting element!!!");
		Node temp1 = list.head;
		while(temp1 != null) {
			System.out.println("Element after delete >> "+temp1.data);
			temp1 =  temp1.next;
		}
		
		list.deleteByPosition(list, 0);
		list.deleteByPosition(list, 3);
		list.deleteByPosition(list, 5);
		System.out.println("Deleting element by position!!!");
		Node temp2 = list.head;
		while(temp2 != null) {
			System.out.println("Element after delete by position >> "+temp2.data);
			temp2 =  temp2.next;
		}
		
		list.insertByPosition(list, "JK2", 6);
		list.insertByPosition(list, "JK3", 10);
		Node temp3 = list.head;
		while(temp3 != null) {
			System.out.println("Inserted by position >> "+temp3.data);
			temp3 =  temp3.next;
		}
	}

}


class Node{
	Node next;
	String data;

	Node(String data){
		this.data = data;
		this.next = null;
	}
}
