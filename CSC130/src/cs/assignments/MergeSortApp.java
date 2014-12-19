/*
 *  A SHORTCUT TO THE HTML VIEW OF THIS CONTENT CAN BE FOUND
 *  IN THIS FILE'S PARENT DIRECTORY. FILE: /GRADER_INFO.html
 */
package cs.assignments;

import java.io.IOException;
import java.util.Arrays;

/**
 * <hr><p><h1>
 * Grader Information: 							</h1></p><p><b>
 * @author Robert Wilk			  				<br>
 * CSc 130 - Section 4 			  				<br>
 * Assignment 1 - Merge Sort	  				<br>
 * 09-26-2014				 	  				<br>
 * 								  				<br>
 * Source File: Assignment1\mergeSortApp.java 	<br>
 * 
 * </b></p>
 * <p><b><i>
 * Running Instructions:		</i></b><br>
 * After opening a command or terminal window, navigate to the top level directory
 * where the mergeSort.jar file is located. You can execute the jar file using 
 * "<b>java.exe -jar mergeSort.jar [args]</b>" on the command line. Any one of the 
 * following ways may be used to demonstrate the merge sort.
 * 
 * <ol>
 * <li> With no arguments -<b> java.exe -jar mergeSort.jar</b>. The program will generate random
 * 		values, sort them, and display its median for both even (10) and odd (11) sized arrays.
 * <li> With one argument -<b> java.exe -jar mergeSort.jar 11</b>. The program will generate
 * 		an array of random elements the size of the integer passed at the command line.
 * <li> With many arguments -<b> java.exe -jar mergeSort.jar 52 5 4 32 13</b>. The program will
 * 		generate an array of the elements passed at the command line to sort.
 * </ol><br><p><b><i>
 * 
 * Purpose:						</i></b>
 * This class is used to demonstrate a merge sort on an array of integers and to display the median
 * of the elements. It prints the elements before and after the sort and is capable of working on 
 * random-generated or explicit input data.
 * </p><br><hr><br>
 */
public class MergeSortApp {

	/**
	 * <p>This method demonstrates the merge sort on sample or
	 * entered data and displays the un/sorted data.</p>
	 * @param args Either not used, the size of data, or the data to be sorted
	 */
	public static void main(String...args) {
		
		int size = 0;
		int times = (args.length == 0) ? 2 : 1;
		int array[];
		for (int i = 0; i < times; ++i, ++size) {
			if (args.length > 1) {
				size = args.length;
				array = new int[size];
				for (int j = 0; j < size; ++ j) {
					array[j] = Integer.parseInt(args[j]);
				}
			} else {
				// If no size is sent from the command line, assign a size.
				size = (args.length == 0) ? (size == 0) ? 10 : 11 : Integer.parseInt(args[0]);
				// Generate an array of 'size' random integers.
				array = 
					    MergeSortApp.generateSampleData(size);
			}
			System.out.println("\nSize: " + size);
			
			// Display the array elements prior to sorting.
			System.out.println("Unsorted:");
			MergeSortApp.displayArray(array);
			
			// Sort array elements using a merge sort.
			array = MergeSortApp.mergeSort(array);
			
			// Display the array elements after sorting.
			System.out.println("Sorted:");
			MergeSortApp.displayArray(array);
			
			// Display median of sample data
			
			System.out.println(
					"Median: " + (((size % 2) != 0) ? 
							Integer.parseInt(String.valueOf((int)(MergeSortApp.getMedian(array)))) : 
							MergeSortApp.getMedian(array))
			);
		}
	}
	
	/**
	 * <p>This method checks the array for a meaningful size and then
	 * makes recursive calls to mergeSort() and finally merges the arrays
	 * returned.</p>
	 * @param array The array to be sorted.
	 * @return The sorted array.
	 */
	public static int[] mergeSort(int[] array) {
		// Base case of an array with a single, sorted element.
		if (array.length < 2)
			return array;
		
		// Divide the array in half and merge the resulting arrays from the helper. 
		return merge(mergeSort(Arrays.copyOfRange(array, 0, array.length / 2)),
			         mergeSort(Arrays.copyOfRange(array, array.length / 2, array.length)));
	}
	
	/**
	 * <p>This method merges two sorted arrays in the merge sort process.</p>
	 * @param first The first array of sorted integers.
	 * @param second The second array of sorted integers.
	 * @return The resulting sorted array from merging the first and second arrays. 
	 */
	private static int[] merge(int[] a, int[] b) {
		int aIndex = 0, // Index for first array
			bIndex = 0, // Index for second array
			cIndex = 0, // Index for third array
			c[] = new int[a.length + b.length]; // Merged array
		
		// Merge the a and b arrays into c until the end of one is reached.
		for (int temp; aIndex < a.length && bIndex < b.length; ++cIndex) {
			if (a[aIndex] < b[bIndex]) {
				temp = a[aIndex++];
			} else {
				temp = b[bIndex++];
			}
			c[cIndex] = temp;
		}
		
		// If a or b have any elements left dump them to c.
		while (aIndex < a.length)
			c[cIndex++] = a[aIndex++];
		while (bIndex < b.length)
			c[cIndex++] = b[bIndex++];
		
		return c;
	}
	
	/**
	 * <p>This method will display an array beginning at index 0
	 * and ending at items.length - 1.</p>
	 * @param items The array to be displayed
	 */
	public static void displayArray(int[] items) {
		
		for (int item : items)
			System.out.print(item + " ");
		
		System.out.println();
	}

	/**
	 * <p>This method generates sample data to be used in demonstrating 
	 * the merge sort in the event explicit data is not given. 
	 * @param size The size of the sample data.</p>
	 * @throws IOException
	 */
	private static int[] generateSampleData(int size) {
		
		// Array of random, unsorted numbers.
		int[] array = new int[size];	
		// Boolean array to ensure no duplicates get written.
		boolean used[] = new boolean[142];
		Arrays.fill(used, false);

		// Generate an initial random number.
		int temp = (int)(Math.random() * 5270) % 142;
		// Fill the array with random values.
		for (int i = 0; i < size; ++i) {
			
			// While temp is a duplicate, generate random numbers.
			while (used[temp]) {
				temp = (int)((Math.random() * 5270) % 142);
			}
			// Assign the random value and remove it from the candidates.
			array[i] = temp;
			used[temp] = true;
		}
		return array;
	}

	/**
	 * <p>This method finds the median of the sorted elements in an integer array.
	 * If odd sized it returns the middle element; if even sized it returns
	 * the average of the elements around the middle.</p>
	 * @param array An integer array of sorted data.
	 * @return An integer representing the median of the sorted data
	 */
	private static Number getMedian(int[] array) {
		int length = array.length;
		// If the array is odd sized return middle element.
		if(length % 2 != 0)
			return new Integer(array[(length / 2)]);
		// Else return the average of the elements around the middle point.
		else 
			return new Double((array[length / 2 - 1] + array[length / 2]) / (double)2);
	}
}
