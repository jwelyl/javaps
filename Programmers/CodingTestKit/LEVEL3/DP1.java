import java.util.*;

class Solution {
	ArrayList<Set<Integer>> dp = new ArrayList<Set<Integer>>();

	int getNs(int N, int cnt) {
		int res = N;

		if (cnt == 0)
			return 0;

		for (int i = 1; i < cnt; i++)
			res += N * Math.pow(10, i);

		return res;
	}

	public int solution(int N, int number) {
		int answer = -1;

		for (int i = 0; i < 9; i++)
			dp.add(new HashSet<Integer>());

		for (int i = 1; i <= 8; i++) {
			dp.get(i).add(getNs(N, i));
			
			for (int j = 1; j < i; j++) {
				for (int k = 1; k < i; k++) {
					if (i != j + k)
						continue;

					for (int a : dp.get(j)) {
						for (int b : dp.get(k)) {
							
							dp.get(i).add(a + b);

							if (a > b)
								dp.get(i).add(a - b);

							dp.get(i).add(a * b);

							if (b != 0 && a >= b)
								dp.get(i).add(a / b);
						}
					}
				}
			}

			if (dp.get(i).contains(number)) {
				answer = i;
				break;
			}
		}

		return answer;
	}
}
