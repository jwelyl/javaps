package algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Permutation {
	private static void swap(int[] arr, int idx1, int idx2) {
		int tmp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = tmp;
	}
	
	private static boolean nextPermutation(int[] arr) {
		int i = arr.length - 1;
		while(i > 0 && arr[i - 1] >= arr[i]) i--;
		if(i <= 0) return false;
		
		int j = arr.length - 1;
		
		while(arr[j] <= arr[i - 1]) j--;
		
		swap(arr, i - 1, j);
		j = arr.length - 1;
		while(i < j) {
			swap(arr, i, j);
			i++;
			j--;
		}
		
		return true;
	}
	
	private static void reverse(int[] arr, int from, int to) {
		int sum = from + to;
		int mid = sum / 2;
		
		for(int i = from; i <= mid; i++) 
			swap(arr, i, sum - i);
	}
	
	private static void printArr(int[] arr, int N) {
		for(int i = 0; i < N; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr, tmp;
		int N, M;
		Scanner sc = new Scanner(System.in);
		Set<Integer> nums = new HashSet<Integer>();
		
		System.out.print("음이 아닌 정수 개수 N(nPm의 n) 입력 : ");
		N = sc.nextInt();
		arr = new int[N];
		tmp = new int[N];
		
		for(int i = 0; i < N; i++) {
			while(true) {
				int num = (int)(Math.random() * (3 * N));
				
				if(!nums.contains(num)) {
					nums.add(num);
					arr[i] = num;
					break;
				}
			}
		}
		Arrays.sort(arr);
		tmp = Arrays.copyOf(arr, N);
		
		System.out.print("정렬된 배열 : ");
		printArr(arr, N);
		System.out.println();
		
		System.out.println("Permutation");
		do {
			printArr(arr, N);
		} while(nextPermutation(arr));
		
		arr = Arrays.copyOf(tmp, N);
		
		System.out.print("\n0 <= M <= N인 정수 M(nPm의 m) 입력 : ");
		M = sc.nextInt();
		
		System.out.println("\n" + N + "P" + M);
		do {
			printArr(arr, M);
			reverse(arr, M, N - 1);
		} while(nextPermutation(arr));
	}

}
