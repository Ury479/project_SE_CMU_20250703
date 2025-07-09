package cn.stack;

import java.util.Stack;
/**yihao wang 652115520*/
public class ArrayBaseStack {

	// The array that holds the stack elements
	private int[] arr = new int[5];
	// The index of the top element in the stack
	private int top;
	// The maximum size of the stack
	private int maxSize;

	// Constructor for the stack
	public ArrayBaseStack(int size) {
		// Set the maximum size of the stack
		maxSize = size;
		// Initialize the array
		arr = new int[maxSize];
		// Initially, the stack is empty
		top = -1;
	}

	// Method to add an element to the top of the stack
	public void push(int j) {
		// Check if the stack is not full before pushing
		if (!isFull()) {
			// Increment top and then insert the element
			j = arr[++top];
		} else {
			// The stack is full, no more elements can be added!
			System.out.println("The stack is full, no more elements can be added!");
		}
	}

	// Method to remove and return the top element of the stack
	public int pop(int j) {
		// Check if the stack is not empty before popping
		if (!isEmpty()) {
			// Return the top element and then decrement top
			j = arr[top--];
		} else {
			// The stack is empty, there are no elements to remove!
			System.out.println("The stack is empty, there are no elements to remove!");
			// Return -1 or throw an exception
		}
		return -1;

	}

	// Method to return the top element of the stack without removing it
	public int peek(int j) {
		// Check if the stack is not empty before peeking
		if (!isEmpty()) {
			// Return the top element
			return arr[top];
		} else {
			// The stack is empty, there are no elements to peek!
			System.out.println("The stack is empty!");
		}
		// Return -1 or throw an exception
		return -1;
	}

	// Method to check if the stack is empty
	public boolean isEmpty() {
		// Return true if the stack is empty
		return top == -1;
	}

	// Method to check if the stack is full
	public boolean isFull() {
		// Return true if the stack is full
		return top == maxSize - 1;
	}

	// The main class that demonstrates the use of the stack
	public static void main(String[] args) {
		// Assume the stack can hold up to 10 elements
		ArrayBaseStack stack = new ArrayBaseStack(5);
		int id = 652115520;
		// Push your student ID onto the stack
		stack.push(id);
		// Peek at the top element of the stack
		System.out.println("Peek" + stack.peek(id));
		// Remove elements from the stack and print the remaining number of elements
		stack.pop(id); // Remove the top element of the stack
		stack.pop(id); // Remove the next top element of the stack
		stack.pop(id); // Remove the next top element of the stack
		// Print the number of elements left in the stack
		System.out.println("Number of the element is" + stack.maxSize);
	}
}