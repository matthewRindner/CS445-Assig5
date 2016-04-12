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
	public Character test0 = new Character('\0');
	public BinaryNode<Character> rootNode = new BinaryNode<Character>(new Character('\0'));
	public ArrayList<Character> charList = new ArrayList<Character>();
	public Character [] charArray;
	public String str = "";
	
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
		
		//get sorted array of characters
		charArray = new Character[items];
		charList = rootNode.getNodes();
		for(int i = 0; i < charList.size(); i++){
			if(charList.get(i).compareTo(test0)!=0)
				charArray[i] = charList.get(i);
			else{
				charList.remove(i);
				i--;
			}
		}
		Arrays.sort(charArray);
		
		//Create Huffman table
		StringBuilder temp = new StringBuilder();
		String [] huffLetterStrings = new String[items];
		for(int i = 0; i < items; i++){
			getHuffCode(charArray[i], temp, rootNode);
			huffLetterStrings[i] = str;
		}
		
		
		System.out.println("\nThe Huffman Tree has been restored");
		Scanner inScan = new Scanner(System.in);
		do{
			System.out.println("\nPlease choose from the following:");
			System.out.println("1) Encode a text string\n2) Decode a Huffman string\n3) Quit");
			int response = Integer.parseInt(inScan.nextLine());
			if(response == 1){
				System.out.println("Enter a String from the following characters:");
				for(int i = 0; i < items; i++){
					System.out.print(charArray[i]);
				}
				System.out.println();
				
				//get user input
				String input = inScan.nextLine();
				StringBuilder code = new StringBuilder();
				for(int i = 0; i < input.length(); i++){
					Character c = new Character(input.charAt(i));
					for(int j = 0; j < items; j++){
						if(charArray[j].compareTo(c) == 0){
							code.append(huffLetterStrings[j]);
							code.append("\n");
						}
					}
				}
				System.out.println("Huffman String:");
				System.out.print(code);
			}
			else if(response == 2){
				System.out.println("Here is the encoding table:");
				for(int i = 0; i < items; i++){
					System.out.print(charArray[i] + ": " + huffLetterStrings[i] + "\n");
				}
				System.out.println("\nPlease enter a Huffman string (one line, no spaces)");
				String input = inScan.nextLine();
				StringBuilder code = new StringBuilder();
				useHuffCode(code, rootNode, input, 0);
				System.out.println("Text String:");
				System.out.println(code);
				
			}
			else if(response == 3){
				System.out.println("Good-bye");
				break;
			}
		}while(true);
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
	
	public void getHuffCode(Character c, StringBuilder s, BinaryNode<Character> node){
		if(node==null){}
		else if(c.compareTo(node.getData())!=0){
			//check left
			s.append('0');
			getHuffCode(c, s, node.getLeftChild());
			s.deleteCharAt(s.length()-1);
			//check right
			s.append('1');
			getHuffCode(c, s, node.getRightChild());
			s.deleteCharAt(s.length()-1);
		}
		else if(c.compareTo(node.getData())==0){
			str = s.toString();
		}
	}

	public void useHuffCode(StringBuilder s, BinaryNode<Character> node, String input, int it){
		if(it < input.length()){
			char c = input.charAt(it);
			if(node.hasLeftChild() == false && node.hasRightChild() == false){
				s.append(node.getData());
				useHuffCode(s, rootNode, input, it);
			}
			else if(c == '0'){
				useHuffCode(s, node.getLeftChild(), input, it + 1);
			}
			else if(c == '1'){
				useHuffCode(s, node.getRightChild(), input, it + 1);
			}
		}
		else{
			s.append(node.getData());
		}
	}
}