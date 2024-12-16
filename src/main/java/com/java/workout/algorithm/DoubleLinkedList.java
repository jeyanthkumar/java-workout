package com.java.workout.algorithm;

import java.util.Objects;

public class DoubleLinkedList {

	DoubleNode head; 

	DoubleLinkedList insert(DoubleLinkedList list, String data) {

		DoubleNode temp = list.head;
		DoubleNode newNode = new DoubleNode(data);
		while(temp != null) {
			if(temp.next == null) {
				System.out.println("Next DoubleNode added!!!");
				System.out.println(":::"+data);
				newNode.prev = temp;
				temp.next = newNode;
				break;
			}
			temp =  temp.next;
		}

		if(list.head == null) {
			System.out.println("Head DoubleNode added!!!");
			System.out.println(":::"+data);
			list.head = new DoubleNode(data);
		}
		return list;
	}
	
	DoubleLinkedList insertByPosition(DoubleLinkedList list, String data, int index) {

		DoubleNode last = list.head;
		DoubleNode prev = null;
		int currentIndex = 0;
		DoubleNode newDoubleNode = new DoubleNode(data);
		
		while(last != null) {
			if(currentIndex==index) {
				if(prev!=null) {
					newDoubleNode.next = last.next;
					newDoubleNode.prev = prev;
					last.prev = newDoubleNode;
					prev.next = newDoubleNode;
				}
				else {
					newDoubleNode.next = last;
					last.prev = newDoubleNode;
					list.head = newDoubleNode;
				}
			}
			prev = last;
			last =  last.next;
			currentIndex++;
		}
		
		if(index>=currentIndex) {
			prev.next = newDoubleNode;
		}
		return list;
	}

	DoubleLinkedList delete(DoubleLinkedList list, String data) {
		DoubleNode last = list.head;

		while(last != null) {
			if(last.data.equals(data)) {
				if(last.prev!=null) {
					last.prev.next = last.next;
					last.next.prev = last.prev;
				}
				else {
					list.head = last.next;
				}
			}
			last =  last.next;
		}

		return list;
	}
	
	DoubleLinkedList deleteByPosition(DoubleLinkedList list, int index) {

		DoubleNode last = list.head;
		int currentIndex = 0;

		while(last != null) {
			if(currentIndex==index) {
				if(last.prev!=null) {
					last.prev.next = last.next;
//					last.next.prev = last.prev;
				}
				else {
					list.head = last.next;
				}
			}
			last =  last.next;
			currentIndex++;
		}

		return list;
	}

	public static void main(String[] args) {
		System.out.println("Started...");

		DoubleLinkedList list = new DoubleLinkedList();
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

		DoubleNode temp = list.head;
		System.out.println("current data: "+temp.data+" next Data: "+temp.next.data+" next next Data: "+temp.next.next.data+" prev prev Data: "+temp.next.next.prev.data);

		while(temp != null) {
			System.out.println("Element >> "+temp.data);
			temp =  temp.next;
		}

		list.delete(list, "TA2");
		System.out.println("Deleting element!!!");
		DoubleNode temp1 = list.head;
		while(temp1 != null) {
			System.out.println("Element after delete >> "+temp1.data);
			temp1 =  temp1.next;
		}
		
		list.deleteByPosition(list, 0);
		System.out.println("Deleting element by position!!!");
		DoubleNode temp2 = list.head;
		while(temp2 != null) {
			System.out.println("Element after delete by position >> "+temp2.data);
			temp2 =  temp2.next;
		}
		
//		list = list.deleteByPosition(list, 0);
//		list = list.deleteByPosition(list, 1);
		list = list.deleteByPosition(list, 4);
		System.out.println("Deleting element by position!!!");
		DoubleNode temp4 = list.head;
		while(temp4 != null) {
			System.out.println("Element after delete by position >> "+temp4.data);
			temp4 =  temp4.next;
		}
		
		list.insertByPosition(list, "JK2", 6);
		list.insertByPosition(list, "JK3", 10);
		DoubleNode temp3 = list.head;
		while(temp3 != null) {
			System.out.println("Inserted by position >> "+temp3.data);
			temp3 =  temp3.next;
		}
	}

}


class DoubleNode{
	DoubleNode prev;
	String data;
	DoubleNode next;

	DoubleNode(String data){
		this.prev = null;
		this.data = data;
		this.next = null;
	}

	@Override
	public int hashCode() {
		return Objects.hash(data, next, prev);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DoubleNode other = (DoubleNode) obj;
		return Objects.equals(data, other.data) && Objects.equals(next, other.next) && Objects.equals(prev, other.prev);
	}

	@Override
	public String toString() {
		return "DoubleNode [prev=" + prev + ", data=" + data + ", next=" + next + "]";
	}
	
	
}
