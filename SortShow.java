package Sorting.sample;
	//importing the libraries that will be needed in this program

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Random;

//The class that has all the sorts in it
public class SortShow extends JPanel { 

	
		// An array to hold the lines_lengths to be sorted
		public int[] lines_lengths;
		//The amount of lines needed
		public final int total_number_of_lines = 256;
		 // An array to holds the scrambled lines_lengths
		public int[] scramble_lines;
		//A temp Array that is used later for sorts
		public int[] tempArray;
		
		//the default constructor for the SortShow class
		public SortShow(){
			//assigning the size for the lines_lengths below
			lines_lengths = new int[total_number_of_lines];
			for(int i = 0; i < total_number_of_lines; i++) 
				lines_lengths[i] =  i+5;
			
		}
		

		//A method that scrambles the lines
		public void scramble_the_lines(){
			//A random generator
			Random num = new Random(); 
			//Randomly switching the lines
			for(int i = 0; i < total_number_of_lines; i++){
				//getting a random number using the nextInt method (a number between 0 to i + 1)
				int j = num.nextInt(i + 1); 
				//swapping The element at i and j 
				swap(i, j);
			}
			//assigning the size for the scramble_lines below
			scramble_lines = new int[total_number_of_lines];
			//copying the now scrambled lines_lengths array into the scramble_lines array 
			//to store for reuse for other sort methods
			//so that all sort methods will use the same scrambled lines for fair comparison 
			for (int i = 0; i < total_number_of_lines - 1; i++)
			{
				scramble_lines[i] = lines_lengths[i];
			}
			//Drawing the now scrambled lines_lengths
			paintComponent(this.getGraphics());
		}
		
		//Swapping method that swaps two elements in the lines_lengths array
		public void swap(int i, int j){
			//storing the i element in lines_lengths in temp
			int temp = lines_lengths[i];
			lines_lengths[i] = lines_lengths[j];
			lines_lengths[j] = temp;
			//You have to complete this method
		}
		
		//The selectionSort method
		public void SelectionSort(){
			//getting the date and time when the selection sort starts
			Calendar start = Calendar.getInstance();
			//Using the selection sort to lines_lengths sort the array

			//swaps the index of i with the index of the next smallest number index and paints it
			for (int i = 0; i < total_number_of_lines - 1; i++)
			{
				int indexOfNextSmallest = getIndexOfSmallest(i, total_number_of_lines - 1);
				swap(i, indexOfNextSmallest);
				paintComponent(this.getGraphics());
			}


			//You need to complete this part.

			//getting the date and time when the selection sort ends
			Calendar end = Calendar.getInstance();
			//getting the time it took for the selection sort to execute 
			//subtracting the end time with the start time
	        SortGUI.selectionTime = end.getTime().getTime() - start.getTime().getTime();
		}
		
		//this method gets the smallest element in the array of lines_lengths
		public int getIndexOfSmallest(int first, int last){

			int smallest = first;

			//iterates through the lines_lengths array to find the smallest value
			for (int i = first + 1; i <= last; i++)
			{
				if (lines_lengths[i] < lines_lengths[smallest])
				{
					smallest = i;
				}
			}
			//You need to complete this part.

			return smallest; //modify this line
		}

	///////////////////////////////////////////////////////////////////////////////////
		
		//recursive merge sort method
		public void R_MergeSort(){
			//getting the date and time when the recursive merge sort starts
			Calendar start = Calendar.getInstance();
			//assigning the size for the tempArray below
			tempArray = new int[total_number_of_lines];

			//Call recursive merge sort
			R_MergeSort(0, total_number_of_lines - 1);

			Calendar end = Calendar.getInstance();
	        SortGUI.rmergeTime = end.getTime().getTime() - start.getTime().getTime();
			
		}
		
		//recursive merge sort method
		public void R_MergeSort(int first, int last){
			if(first < last){

				//Recursively call this function
				int mid = (first + last) / 2;
				R_MergeSort(first, mid);
				R_MergeSort(mid + 1, last);

				R_Merge(first, mid, last);

				//Causing a delay for 10ms
				delay(10);
			}
		}

		
		//recursive merge sort method
		public void R_Merge(int first, int mid, int last){

			int beginHalf1 = first;
			int endHalf1 = mid;
			int beginHalf2 = mid+1;
			int endHalf2 = last;
			int index = beginHalf1;

			//Sorts and combines the two individual unsorted sides
			while((beginHalf1 <= endHalf1)&&(beginHalf2 <= endHalf2))
			{
				if(lines_lengths[beginHalf1] < lines_lengths[beginHalf2])
				{
					tempArray[index] = lines_lengths[beginHalf1];
					beginHalf1++;
				}
				else
				{
					tempArray[index] = lines_lengths[beginHalf2];
					beginHalf2++;
				}
				index++;
			}

			//Add the remaining elements of one side of the array if leftover
			while(beginHalf1 <= endHalf1)
			{
				tempArray[index] = lines_lengths[beginHalf1];
				beginHalf1++;
				index++;
			}
			while(beginHalf2 <= endHalf2)
			{
				tempArray[index] = lines_lengths[beginHalf2];
				beginHalf2++;
				index++;
			}

			//Copy the sorted elements in temp array into the permanent array
			for(int i = first; i <= last; i++)
			{
				lines_lengths[i] = tempArray[i];
			}
			paintComponent(this.getGraphics());
				
		}
		
		//

	//////////////////////////////////////////////////////////////////////////////////////////
		
		//iterative merge sort method
		public void I_MergeSort()
		{
		//getting the date and time when the iterative merge sort starts
		Calendar start = Calendar.getInstance();
		//assigning the size for the tempArray below
		tempArray = new int[total_number_of_lines]; 
		//saving the value of total_number_of_lines
		int beginLeftovers = total_number_of_lines;

		
		for (int segmentLength = 1; segmentLength <= total_number_of_lines/2; segmentLength = 2*segmentLength)
		{
			beginLeftovers = I_MergeSegmentPairs(total_number_of_lines, segmentLength);
			int endSegment = beginLeftovers + segmentLength - 1;
			if (endSegment < total_number_of_lines - 1) 
			{
			I_Merge(beginLeftovers, endSegment, total_number_of_lines - 1);
			}
		} 

		// merge the sorted leftovers with the rest of the sorted array
		if (beginLeftovers < total_number_of_lines) {
			I_Merge(0, beginLeftovers-1, total_number_of_lines - 1);
		}
		//getting the date and time when the iterative merge sort ends
		Calendar end = Calendar.getInstance();
		//getting the time it took for the iterative merge sort to execute 
		//subtracting the end time with the start time
	    SortGUI.imergeTime = end.getTime().getTime() - start.getTime().getTime();
	} 

	// Merges segments pairs (certain length) within an array
	//returns the index after the last merged pair
	public int I_MergeSegmentPairs(int l, int segmentLength)
	{
		//The length of the two merged segments 

		//You need to complete this part.
		int mergedPairLength = 2 * segmentLength;
		int numPairs = l / mergedPairLength;

		int beginSegment1 = 0;

		for (int count = 1; count <= numPairs; count++)
		{
			int endSegment1 = beginSegment1 + segmentLength - 1;

			int beginSegment2 = endSegment1 + 1;
			int endSegment2 = beginSegment2 + segmentLength - 1;

			R_Merge(beginSegment1, endSegment1, endSegment2);

			beginSegment1 = endSegment2 + 1;
		}
		// returns index of element after last merged pair
		return beginSegment1;//modify this line
	}

	public void I_Merge(int first, int mid, int last)
	{
		//You need to complete this part.
		int beginHalf1 = first;
		int endHalf1 = mid;
		int beginHalf2 = mid+1;
		int endHalf2 = last;

		//while both arrays are not empty
		//copy smaller item into temp array

		int index = beginHalf1;

		for (; (beginHalf1 <= endHalf1) && (beginHalf2 <= endHalf2); index++)
		{
			if (lines_lengths[beginHalf1] < lines_lengths[beginHalf2])
			{
				tempArray[index] = lines_lengths[beginHalf1];
				beginHalf1++;
			}
			else
			{
				tempArray[index] = lines_lengths[beginHalf2];
				beginHalf2++;
			}
		}

		//finish first subarray
		for (; beginHalf1 <= endHalf1; beginHalf1++, index++)
		{
			tempArray[index] = lines_lengths[beginHalf1];
		}

		//finish second subarray
		for (; beginHalf2 <= endHalf2; beginHalf2++, index++)
		{
			tempArray[index] = lines_lengths[beginHalf2];
		}

		for(index = first; index <= last; index++)
		{
			lines_lengths[index] = tempArray[index];
			//paintComponent(this.getGraphics());
		}

		//redrawing the lines_lengths 
		paintComponent(this.getGraphics());

		//You need to complete this part.
	} 


	//////////////////////////////////////////////////////////////////////	
		
		//This method resets the window to the scrambled lines display
		public void reset(){
			if(scramble_lines != null)
			{
				//copying the old scrambled lines into lines_lengths
				for (int i = 0; i < total_number_of_lines; i++)
				{
					lines_lengths[i] = scramble_lines[i] ;
				}
			//Drawing the now scrambled lines_lengths
			paintComponent(this.getGraphics());
		}
			}
		
	
		//This method colours the lines and prints the lines
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			//A loop to assign a colour to each line
			for(int i = 0; i < total_number_of_lines; i++){
				//using eight colours for the lines
				if(i % 8 == 0){
					g.setColor(Color.green);
				} else if(i % 8 == 1){
					g.setColor(Color.blue);
				} else if(i % 8 == 2){
					g.setColor(Color.yellow);
				} else if(i%8 == 3){
					g.setColor(Color.red);
				} else if(i%8 == 4){
					g.setColor(Color.black);
				} else if(i%8 == 5){
					g.setColor(Color.orange);
				} else if(i%8 == 6){
					g.setColor(Color.magenta);
				} else
					g.setColor(Color.gray);
				
				//Drawing the lines using the x and y-components 
				g.drawLine(4*i + 25, 300, 4*i + 25, 300 - lines_lengths[i]);
			}
			
		}
		
		//A delay method that pauses the execution for the milliseconds time given as a parameter
		public void delay(int time){
			try{
	        	Thread.sleep(time);
	        }catch(InterruptedException ie){
	        	Thread.currentThread().interrupt();
	        }
		}
		
	}

