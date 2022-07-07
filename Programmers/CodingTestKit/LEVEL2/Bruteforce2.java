import java.util.*;

class Solution {
	List<Integer> nums = new ArrayList<Integer>();
	List<Integer> result = new ArrayList<Integer>();
	Set<Integer> primes = new HashSet<Integer>();
	
	boolean visited[];
	int n, answer = 0;

	void checkPrimeNumber() {
		int mult = 1;
		int res = 0;
		boolean isPrime = true;
	

		for(int i = 0; i < result.size(); i++) {
			res += result.get(i) * mult;
			mult *= 10;
		}
		
		// System.out.println("만들어진 수 = " + res);

		if (res < 2)
			isPrime = false;
		else {
			for (int i = 2; i <= (int) Math.sqrt(res); i++) {
				if (res % i == 0) {
					isPrime = false;
					break;
				}
			}
		}

		if (isPrime && !primes.contains(res)) {
			primes.add(res);
			answer += 1;
		}
	}
	
	void dfs(int idx, int curDepth, int depth) {
		// System.out.println("dfs(" + idx + ", " + curDepth + ", " + depth  + ")");
		result.add(nums.get(idx));
		visited[idx] = true;
		
		if(curDepth == depth) 
			checkPrimeNumber();
		else {
            // System.out.println("Else! n = " + n);
			for(int i = 0; i < n; i++) {
                // System.out.println("i = " + i);
				if(!visited[i])
					dfs(i, curDepth + 1, depth);
			}
		}
		
		visited[idx] = false;
		result.remove(result.size() - 1);
	}

	public int solution(String numbers) {
		n = numbers.length();
        // System.out.println("n = " + n);
        
		visited = new boolean[n];
		Arrays.fill(visited, false);

		for (int i = 0; i < n; i++)
			nums.add(numbers.charAt(i) - '0');
		Collections.sort(nums);
		
		for(int i = 1; i <= n; i++) {
			for(int j = 0; j < n; j++) {
				dfs(j, 1, i);
			}
		}
		
		return answer;
	}
}
