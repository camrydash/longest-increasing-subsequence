// =======================================================================
// Author: Danyal Isran
// Title: Longest Increasing Subsequence
// Email: danyal.isran@gmail.com
// Course: COMP620 FALL 2014
// Professor: Lin Chui
// Project: 1
//
// Executable filename: LongestIncreasingSubsequence.java 
//
// Instructions:
// =======================================================================
// Please enter numbers in the array separated by a comma.
// Example input: 11, 17, 5, 8, 6, 4, 7, 12, 3
// Output: "LIS: [5, 6, 7, 12] with Length(4)"
//
// If the output is a single number, it is ignored since it does not form an 'Increasing Sequence'
// Example input: 9, 8, 7, 6, 5
// Output: "[9] with Length(1) No LIS found!"
// =======================================================================

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LongestIncreasingSubsequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.printf("** Please enter numbers in the array separated by a comma. **");
		System.out.printf("\n** Example: 11, 17, 5, 8, 6, 4, 7, 12, 3 **\n");
		List<Integer> arrayValues;
		Scanner s = new Scanner(System.in);
		//Get input from the user until we have one or more numbers
		do
		{
			arrayValues = new ArrayList<Integer>();

			String strInput = s.nextLine();
			String[] tempValues = strInput.split(",");
			for(int i = 0; i < tempValues.length; i++)
			{
				try
				{
					arrayValues.add(Integer.parseInt(tempValues[i].trim()));
				}
				catch(Exception ex0){}
			}
		}
		while(arrayValues.size() <= 0); //number of integers must be > 0
		s.close();
		
		//Populating array from List<Integer>1,2,3,4,5
		int length = arrayValues.size();
		int[] arraySequence = new int[length];
		for(int i=0;i<length;i++)
		{
			arraySequence[i] = arrayValues.get(i);
		}		
		//System.out.printf("Recognized Sequence: %s\n", arrayValues);
		longestIncreasingSubsequence(arraySequence, length);		
	}
	

	private static void longestIncreasingSubsequence(int[] array, int length)
	{
		int[] lis = new int[length]; //lis array to record LIS at index i
		int[] prev = new int[length];//prev array to record sequence of LIS
		int maxLength = 0, bestEnd = 0; 
		//maxLength records length of LIS, 
		//bestEnd tracks the index of i of the last element in our LIS, eg A = {11, 17, 5, 8, 6, 7, 12, 3}, LIS = {5, 6, 7, 12}, last element in our LIS is 12, which has i = 6 in A.
		for(int i = 0; i < length; i++)
		{
			prev[i] = -1;//-1:- new sequence
			lis[i] = 1;//assume starting element has LIS=1
			
			for(int j = i - 1; j>=0; j--)
			{
				//we are going backwards starting from (i-1) to j=0, array[j] < array[i] AND lis[j] + 1 > lis[i]
				if(array[j] < array[i] && lis[j] + 1 > lis[i])
				{
					lis[i] = lis[j] + 1; //record the new LIS at i
					prev[i] = j;	//record the position of j, the index of subproblem in our LIS used to solve our problem
				}
			}
			
			if(lis[i] > maxLength)//we have to keep track of length of LIS
			{
				maxLength = lis[i];
				bestEnd = i;	
			}
		}
		
		int n = bestEnd;	//we use "bestEnd" to recursively create sequence of LIS from "PREV" array
		
		//Backtracking and storing LIS into a separate array from our PREV array
		//here, i want to populate LIS array backwards, from lastIndex to firstIndex, since we are recursively traversing backwards from "PREV" array to create our LIS sequence
		int[] lis_print = new int[maxLength];
		int pos = maxLength - 1; 
		while(n != -1)
		{
			lis_print[pos] = array[n];
			pos--;	//
			n = prev[n];
		}
		
		//Printing Output
		System.out.printf((lis_print.length > 1 ? "LIS: " : "Output: ") + "%s with Length(%d)", Arrays.toString(lis_print),	lis_print.length);
		if (lis_print.length <= 1)
		{
			System.out.printf("\nNo LIS found!");
		}
	}
}
