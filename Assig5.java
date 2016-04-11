//Tyler Protivnak
//File Created 4/11/2016
//University of Pittsburgh - CS445
//Prof: John Ramirez, T/Th 9:30-10:45
//TA: Karin Cox, Tues 4-5
//
//Assig 5 file
//This is the main program for the assignment that deals with Huffman Compression

import java.io.*;
import java.util.*;

public class Assig5{
	public int items = 0;
	public Character testI = new Character('I');
	public Character testI = new Character('\0');
	public BinaryNode<Character> rootNode = new BinaryNode<Character>(new Character('\0'));
	public ArrayList<Character> charList = new ArrayList<Character>();
	public Character [] charArray;
	
	public static void main(String [] args){
		new Assig5(args);
	}

	public Assig5(String [] args){
		String fileName = args[0];
		Scanner fReader = null;
		File fName;
		try{
			fName = new File(fileName);
			fReader = new Scanner(fName);
			rootNode = buildTree(fReader);
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		
		charArray = new Character[items];
		charList = rootNode.getNodes();
		for(int i = 0; i < charArray.size(); i++){
			if()
				charArray[i] = charList.get(i);
		}
		Arrays.sort(charArray);
		
		
		System.out.println("\nThe Huffman Tree has been restored\n\nPlease choose from the following:");
		System.out.println("1) Encode a text string\n2) Decode a Huffman string\n3) Quit");
		
		for(int i = 0; i < items; i++){
			System.out.println(charArray[i]);
		}
	}
	
	public BinaryNode<Character> buildTree(Scanner fReader){
		BinaryNode<Character> newNode = null;
		try{
			String [] line = (fReader.nextLine()).split(" ");
			Character first = new Character(line[0].charAt(0));
			if(first.compareTo(testI)==0){
				newNode = new BinaryNode<Character>(new Character('\0'));
				newNode.setLeftChild(buildTree(fReader));
				newNode.setRightChild(buildTree(fReader));
			}
			else{
				items++;
				return new BinaryNode<Character>(new Character(line[1].charAt(0)));
			}
		}
		catch(Exception e){}
		return newNode;
	}
	
}