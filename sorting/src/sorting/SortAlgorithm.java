package sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortAlgorithm {

	private static int array[] = {43,3,1,5,7,2};
	
	//Swaping function
	public static void swap(int[] array,int i,int j) {
		if(i==j) {
			return;
		}
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	//Bubble Sort
	public static void bubbleSort(int[] array) {
		System.out.println("Bubble Sort : ");
		for(int i = array.length-1;i>0;i--) {
			for(int j=0;j<i;j++) {
				if(array[j]>array[j+1]) {
					swap(array,j,j+1);
				}
			}
		}
	}
	
	//Selection Sort
	public static void selectionSort(int[] array)
	{
		System.out.println("Selection Sort : ");
		for(int i=array.length-1;i>0;i--) {
			int largest =0;
			for(int j=1;j<-i;j++) {
				if(array[i]>array[largest])
				{
				largest = j;
			}
		}
		swap(array,largest,i);
		}
	}
	
	//Insertion Sort
	public static void insertionSort(int[] array) {
		System.out.println("Insertion Sort : ");
		for(int i  =1;i<array.length;i++) {
			int newElement = array[i];
			int j;
			
			for(j=i; j>0 && array[j-1]>newElement;j--) {
				array[j]=array[j-1];
			}
			array[j] = newElement;
		}
	}
	
	//Shell Sort
	public static void shellSort(int[] array) {
		System.out.println("Shell Sort : ");
		for(int gap=array.length/2;gap>0;gap/=2) {
			
			for(int i =gap; i<array.length;i++) {
				
				int newElement = array[i]; 
				int j=i;
				while(j>=gap && array[j-gap]>newElement) {
					array[j] = array[j-gap];
					j = j - gap;
				}
				array[j] = newElement;
			}
		}
	}
	
	//Merge Sort
	public static void merge(int[] input,int start,int mid,int end) {
		
		if(input[mid-1]<=input[mid]) {
			return;
		}
		int i = start;
		int j = mid;
		int tempI=0;
		
		int[] temp = new int[end-start];
		while(i<mid && j<end) {
			temp[tempI++] = input[i]<=input[j] ? input[i++]: input[j++];
		}
		
		System.arraycopy(input, i, input, start + tempI,mid-i);
		System.arraycopy(temp, 0, input, start,tempI);
	}
	
	public static void mergeSort(int[] input,int start,int end) {
		
		if(end-start<2) {
			return;
		}
		
		int mid= (start+end)/2;
		mergeSort(input,start,mid);
		mergeSort(input,mid,end);
		merge(input,start,mid,end);
	}
	
	//Quick Sort
	static int partition(int[] arr,int lb, int ub) {
		int pivot = arr[ub];
		int i = (lb-1);
		for(int j = lb;j<=ub-1;j++) {
			if(arr[j]<pivot) {
				i++;
				swap(arr,i,j);
			}
		}
		
		swap(arr,i+1,ub);
		return (i+1);
	}
	
	static void quickSort(int[] arr , int lb,int ub) {
		
		if(lb<ub) {
			int pi = partition(arr,lb,ub);
			quickSort(arr,lb,pi-1);
			quickSort(arr,pi+1,ub);
		}
	}
	
	//Count Sort
	public static void countSort(int[] input,int min,int max) {
		System.out.println("Count Sort :");
		int[] countArray = new int[(max-min)+1];
		for(int i = 0; i<input.length;i++) {
			countArray[input[i]-min]++;
		}
		int j =0;
		for(int i =min;i<=max;i++) {
			while(countArray[i-min]>0) {
				input[j++] = i;
				countArray[i-min]--;
			}
		}
	}
	
	//Radix Sort
	static int getMax(int a[],int n) {
		int max = a[0];
		for(int i=1;i<n;i++) {
			if(a[i]>max) {
				max=a[i];
			}
		}
		return max;
	}
	
	public static void radixSort(int a[],int n) {
		System.out.println("Radix Sort :");
		int max = getMax(a,n);
		
		for(int place = 1;max/place>0;place *= 10) {
			countSort(a,n,place);
		}
	}
	
	//Bucket Sort
	public static void bucketSort(int[] input) {
		List[] buckets = new List[10];
		
		for(int i = 0;i<buckets.length;i++) {
			buckets[i] = new ArrayList<Integer>();
		}
		for(int i =0;i<input.length;i++) {
			buckets[hash(input[i])].add(input[i]);
		}
		for(List bucket : buckets) {
			Collections.sort(bucket);
		}
		int j=0;
		
		for(int i=0;i<buckets.length;i++) {
			for(Object value: buckets[i]) {
				input[j++] = (int) value;
			}
			//buckets[i].forEach((int) value-> input[j++] = value);
		}
	}
	
	private static int hash(int value) {
		return value/(int) 10;
	}
	
	//Heap Sort in Descending order
	public static void maxHeapify(int[] a,int n,int i) {
		int largest = i;
		int l = 2*i;
		int r = (2*i)+1;
		//System.out.println(i+" "+largest+" "+l+" "+r);
		
		if(l<=n && a[l]>a[largest]) {
			largest = l;
		}
		
		if(r<=n && a[r]> a[largest]) {
			largest=r;
		}
		if(largest!=i) {
			swap(a,largest,i);
			maxHeapify(a,n,largest);
		}
		
	}
	
	public static void heapSort(int[] a, int n) {
		System.out.println("Heap Sort :");
		for(int i=n/2;i>=1;i--) {
			maxHeapify(a,n,i);
		}
		for(int i=n;i>1;i--)
		{
			swap(a,1,i);
			maxHeapify(a,n,1);
		}	
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//bubbleSort(array);
		
		//selectionSort(array);
		
		//insertionSort(array);
		
		//shellSort(array);
		
		/*System.out.println("Merge Sort : ");
		mergeSort(array, 0, array.length);*/
		
		/*System.out.println("Quick Sort : ");
		mergeSort(array, 0, array.length);*/
		
		heapSort(array,array.length-1);
		
		for(int item:array) {
			System.out.println(item);
		}
		
		
	}

}
