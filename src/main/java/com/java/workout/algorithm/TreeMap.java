package com.java.workout.algorithm;

public class TreeMap {

	TreeNode head; 
	static TreeNode lastRight;
	static TreeNode lastLeft;

	TreeMap add(TreeMap map, String data) {

		TreeNode newNode = new TreeNode(data);
		if(head == null) {
			this.head = newNode;
		}else {

			String headValue = this.head.data;
			TreeNode node = this.head;
			addByTraverse(headValue,data,newNode,node);
		}


		return map;
	}

	public void addByTraverse(String headValue, String data, TreeNode newNode, TreeNode node) {
		System.out.println("head value: "+headValue+" data value: "+data);
		int compare = headValue.compareTo(data);
		System.out.println(compare);
		if(compare>0) {//insert into right direction
			if(node.right==null) {
				node.right = newNode;
				newNode.parent = node;
			}else {
				headValue = node.right.data;
				node = node.right;
				addByTraverse(headValue,data,newNode,node);
			}
		}else {//insert into right direction
			if(node.left==null) {
				node.left = newNode; 
				newNode.parent = node;
			}else {
				headValue = node.left.data;
				node = node.left;
				addByTraverse(headValue,data,newNode,node);
			}
		}
	}

	public static void printAscending(TreeMap map) {
		printTraverse(map.head, null, null);
	}

	public static void printTraverse(TreeNode head, TreeNode left, TreeNode right) {
		findRightLastNode(head);
		System.out.println("Data in ascending order!!!!");
		//		System.out.println(lastRight.data);
		getTreeData(lastRight);

	}

	private static void getTreeData(TreeNode ele) {
//		if(ele.right!=null)
//			System.out.println(ele.right.data);
		System.out.println(ele.data);
		if(ele.left!=null)
			System.out.println(ele.left.data);
		getTreeData(ele.parent);

	}

	private static void findRightLastNode(TreeNode head) {
		if(head.right != null) {
			System.out.println(">>>>>>>"+head.right.data);
			head = head.right;
			findRightLastNode(head);
		}else {
			lastRight = head;
		}
	}

	public static void main(String[] args) {
		System.out.println("Started...");
		TreeMap map = new TreeMap();

		map.add(map, "J");
		map.add(map, "O");
		map.add(map, "K");
		map.add(map, "Z");
		map.add(map, "L");
		map.add(map, "B");
		map.add(map, "A");
		map.add(map, "C");
		map.add(map, "D");


		//		System.out.println("right: "+map.head.right.data);
		System.out.println("left: "+map.head.left.data);
		//		System.out.println("right: "+map.head.right.right.data);
		System.out.println("left: "+map.head.left.left.data);
		System.out.println("left right: "+map.head.left.right.left.data);
		System.out.println("head: "+map.head.data);


		printAscending(map);
	}

}


class TreeNode{
	TreeNode parent;
	TreeNode left;
	String data;
	TreeNode right;

	TreeNode(String data){
		this.parent = null;
		this.left = null;
		this.data = data;
		this.right = null;
	}

	@Override
	public String toString() {
		return "TreeNode [left=" + left + ", data=" + data + ", right=" + right + "]";
	}	

}
