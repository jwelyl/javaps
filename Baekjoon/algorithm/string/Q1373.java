package boj;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//public class Q1373 {
//	static String binary;
//	static long decimal = 0;
//	static int idx;
//	static Scanner sc = new Scanner(System.in);
//	
//	public static void main(String[] args) {
//		binary = sc.nextLine();
//		idx = binary.length() - 1;
//		
//		for(int i = 0; i < binary.length(); i++) {
//			if(binary.charAt(i) == '1')
//				decimal += Math.pow(2, idx);
//			idx--;
//		}
//		
//		System.out.printf("%o\n", decimal);
//	}
//}

//public class Q1373 {
//	static Map<String, String> binToOct = new HashMap<>();
//	static String binary = "", octal = "";
//	static StringBuilder sb = new StringBuilder();
//	static Scanner sc = new Scanner(System.in);
//	
//	static void init() {
//		binToOct.put("0", "0");		binToOct.put("00", "0");	binToOct.put("000", "0");
//		binToOct.put("1", "1");		binToOct.put("01", "1");	binToOct.put("001", "1");
//		binToOct.put("10", "2");	binToOct.put("010", "2");
//		binToOct.put("11", "3");	binToOct.put("011", "3");
//		binToOct.put("100", "4");	binToOct.put("101", "5");
//		binToOct.put("110", "6");	binToOct.put("111", "7");		
//	}
//	
//	public static void main(String[] args) {
//		int q, r;
//		
//		init();
//		binary = sc.nextLine();
//		r = binary.length() % 3;
//		
//		if(r != 0)
//			sb.append(binToOct.get(binary.substring(0, r)));
//		
//		for(int i = r; i < binary.length(); i += 3) {
//			sb.append(binToOct.get(binary.substring(i, i + 3)));
//		}
//		
//		octal = sb.toString();
//		System.out.println(octal);
//	}
//}

public class Q1373 {
	static String binary = "";
	static StringBuilder sb = new StringBuilder();
	static Scanner sc = new Scanner(System.in);
	
	static String binToOct(String bin) {
		int ret = 0;
		
		for(int i = 0; i < bin.length(); i++) {
			if(bin.charAt(i) == '1')
				ret += Math.pow(2, bin.length() - i - 1);
		}
			
//		System.out.println("bin = " + bin);
//		System.out.println("ret = " + ret);
//		System.out.println();
		return Integer.toString(ret);
	}
	
	public static void main(String[] args) {
		int q, r;
		binary = sc.nextLine();
		r = binary.length() % 3;
		
		if(r != 0)
			sb.append(binToOct(binary.substring(0, r)));
		
		for(int i = r; i < binary.length(); i += 3) {
			sb.append(binToOct(binary.substring(i, i + 3)));
		}
		
		System.out.println(sb.toString());
	}
}
