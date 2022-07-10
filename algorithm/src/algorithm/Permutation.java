package algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Permutation {
	private static void swap(int[] arr, int idx1, int idx2) {	//	arr[idx1], arr[idx2]를 swap
		int tmp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = tmp;
	}
	
	private static boolean nextPermutation(int[] arr) {	
		int i = arr.length - 1;	//	arr의 마지막 index
		while(i > 0 && arr[i - 1] >= arr[i]) i--;	//	내림차순 정렬되어 있는지 확인	
		
		if(i <= 0) return false;	//	내림차순으로 정렬되어 있을 경우 다음 순열 존재하지 않음
		
		//	i : 내림차순으로 정렬되기 시작하는 부분
		//	arr = {1, 2, 3, 4, 8, 7, 6, 5}일 경우 i = 4 (8의 index)
		int j = arr.length - 1;
		
		while(arr[j] <= arr[i - 1]) j--;	//	내림차순 정렬된 부분 중 오름차순 정렬된 부분의 최댓값보다 큰 최솟값 인덱스 찾기
		
		swap(arr, i - 1, j);	//	내림차순 정렬 부분 중 최솟값과 오름차순 정렬 부분 중 최댓값 swap  
		j = arr.length - 1;	
		while(i < j) {
			swap(arr, i, j);
			i++;
			j--;
		}
		
		return true;
	}
	
	private static void reverse(int[] arr, int from, int to) {	//	arr의 from부터 to까지 뒤집는 함수
		int sum = from + to;
		int mid = sum / 2;
		
		for(int i = from; i <= mid; i++) 
			swap(arr, i, sum - i);
	}
	
	private static void printArr(int[] arr, int N) {	//	arr을 0부터 N - 1까지 출력
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
				
				if(!nums.contains(num)) {	//	서로 다른 수가 들어갈 수 있도록 Set에 저장
					nums.add(num);
					arr[i] = num;
					break;
				}
			}
		}
		Arrays.sort(arr);	//	중요! nextPermutation 함수를 사용하기 전에 오름차순으로 배열을 정렬해야 모든 경우의 결과를 얻을 수 있음
		tmp = Arrays.copyOf(arr, N);
		
		System.out.print("정렬된 배열 : ");
		printArr(arr, N);
		System.out.println();
		
		System.out.println("Permutation");	//	N개의 수를 줄 세운 경우 (N!)
		do {
			printArr(arr, N);
		} while(nextPermutation(arr));
		
		arr = Arrays.copyOf(tmp, N);
		
		System.out.print("\n0 <= M <= N인 정수 M(nPm의 m) 입력 : ");
		M = sc.nextInt();
		
		System.out.println("\n" + N + "P" + M);	//	N개의 수 중 M개의 수를 줄 세운 경우 (NPM)
		do {
			printArr(arr, M);
			reverse(arr, M, N - 1);
		} while(nextPermutation(arr));
	}

}
