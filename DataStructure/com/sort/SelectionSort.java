package com.sort;

public class SelectionSort {
	
	public static void selectionSort(int[] data) {
		for(int i=0;i<data.length;i++) {
			for(int j=i+1;j<data.length;j++) {
				if(data[i]>data[j]) {
					int temp = data[j];
					data[j] = data[i];
					data[i] = temp;
				}
			}
		}
	}
	
	// n square - n + (n-1) + (n-2) + (n-3) ... = n(n+1)/2 = n^2
	public static void sort(int[] arr) {
		for(int i=0;i<arr.length;i++) {
			int smallest = getIndexOfSmallestElement(arr, i);
			swap(arr, smallest, i);
		}
	}
	
	// linear - O(n)
	public static int getIndexOfSmallestElement(int[] arr, int start) {
		int index = start;
		for(int i=start;i<arr.length;i++) {
			if(arr[i]<arr[index]) {
				index = i;
			}
		}
		return index;
	}
	
	// constant - O(1)
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
