// CS 0445 Spring 2016
// I took the author's BinaryNode class and added the traversal methods to it
// to demonstrate their functionality.  See the added methods at the bottom
// of this file.  Note that this class is within a package.  For information on
// creating and using Java packages, see:
// http://docs.oracle.com/javase/tutorial/java/package/
// Since the class was not meant to be used outside of the package, it was
// declared by the author with "package access", which is the default in Java.
// (i.e. no keyword appeared before the class name).  However, I have changed
// the declaration to public, so that I can use this class in my Example14.java
// handout.
//      Note that the BinaryNode class alone is enough to implement a binary
// tree, if we don't want a separate level of abstraction for the tree class.
// This is demonstrated in Example14.java
//package TreePackage;
/**
   A class that represents nodes in a binary tree.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 4.0
*/
import java.io.*;
import java.util.*;

public class BinaryNode<T>
{
   private T             data;
   private BinaryNode<T> leftChild;  // Reference to left child
   private BinaryNode<T> rightChild; // Reference to right child

   public BinaryNode()
   {
      this(null); // Call next constructor
   } // end default constructor

   public BinaryNode(T dataPortion)
   {
      this(dataPortion, null, null); // Call next constructor
   } // end constructor

   public BinaryNode(T dataPortion, BinaryNode<T> newLeftChild,
                                    BinaryNode<T> newRightChild)
   {
      data = dataPortion;
      leftChild = newLeftChild;
      rightChild = newRightChild;
   } // end constructor

   /** Retrieves the data portion of this node.
       @return  The object in the data portion of the node. */
   public T getData()
   {
      return data;
   } // end getData

   /** Sets the data portion of this node.
       @param newData  The data object. */
   public void setData(T newData)
   {
      data = newData;
   } // end setData

   /** Retrieves the left child of this node.
       @return  The node’s left child. */
   public BinaryNode<T> getLeftChild()
   {
      return leftChild;
   } // end getLeftChild

   /** Sets this node’s left child to a given node.
       @param newLeftChild  A node that will be the left child. */
   public void setLeftChild(BinaryNode<T> newLeftChild)
   {
      leftChild = newLeftChild;
   } // end setLeftChild

   /** Detects whether this node has a left child.
       @return  True if the node has a left child. */
   public boolean hasLeftChild()
   {
      return leftChild != null;
   } // end hasLeftChild

   /** Retrieves the right child of this node.
       @return  The node’s right child. */
   public BinaryNode<T> getRightChild()
   {
      return rightChild;
   } // end getRightChild
   
   /** Sets this node’s right child to a given node.
       @param newRightChild  A node that will be the right child. */
   public void setRightChild(BinaryNode<T> newRightChild)
   {
      rightChild = newRightChild;
   } // end setRightChild
   
   /** Detects whether this node has a right child.
       @return  True if the node has a right child. */
   public boolean hasRightChild()
   {
      return rightChild != null;
   } // end hasRightChild
   
   /** Detects whether this node is a leaf.
       @return  True if the node is a leaf. */
   public boolean isLeaf()
   {
      return (leftChild == null) && (rightChild == null);
   } // end isLeaf

   /** Counts the nodes in the subtree rooted at this node.
       @return  The number of nodes in the subtree rooted at this node. */
   public int getNumberOfNodes()
   {
      int leftNumber = 0;
      int rightNumber = 0;
      
      if (leftChild != null)
         leftNumber = leftChild.getNumberOfNodes();
      
      if (rightChild != null)
         rightNumber = rightChild.getNumberOfNodes();
      
      return 1 + leftNumber + rightNumber;
   } // end getNumberOfNodes
   
   /** Computes the height of the subtree rooted at this node.
       @return  The height of the subtree rooted at this node. */
   public int getHeight()
   {
      return getHeight(this); // Call private getHeight
   } // end getHeight

   private int getHeight(BinaryNode<T> node)
   {
      int height = 0;

      if (node != null)
         height = 1 + Math.max(getHeight(node.getLeftChild()),
                               getHeight(node.getRightChild()));
                             
      return height;
   } // end getHeight

   /** Copies the subtree rooted at this node.
       @return  The root of a copy of the subtree rooted at this node. */
   public BinaryNode<T> copy()
   {
      BinaryNode<T> newRoot = new BinaryNode<>(data);
      
      if (leftChild != null)
         newRoot.setLeftChild(leftChild.copy());
      
      if (rightChild != null)
         newRoot.setRightChild(rightChild.copy());
      
      return newRoot;
   } // end copy
   
   	// *************************
	// My added methods start here.  Note that the only differences in the three
	// traversal methods are the order of the statements.
	// *************************

	public ArrayList<T> getNodes()
	{
		ArrayList<T> list = new ArrayList<T>();
		getNodes(list, this);
		return list;
	}

	private void getNodes(ArrayList<T> list, BinaryNode<T> node)
	{
		if (node != null)
		{
			list.add(node.getData());
			getNodes(list, node.leftChild);
			getNodes(list, node.rightChild);
		}
	}

	
} // end BinaryNode