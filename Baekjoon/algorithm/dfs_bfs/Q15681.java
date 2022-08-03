import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/15681
 * @difficuly G5
 * @performance 112412KB   2952ms
 * @category # 깊이우선탐색(DFS), DP??
 * @memo 트리를 입력받은 후 DFS로 각 정점의 깊이, 부모, 자식 개수를 알아냄
 * @memo 정점을 깊이를 기준으로 내림차순으로 정렬한 후 가장 아래의 정점들로부터 거슬러 올라가 각 정점의 서브트리 정점 개수를 DP 방식으로 구함
 * @memo 다른 사람들이 제출한 코드와 비교했을 때 시간 차이가 심각하게 많이 나는 것을 봐서 더 좋은 방법이 있을듯
 * @etc 
 *  
 */

public class Main {
	static int N, R, Q;		//	트리 정점 개수, root 번호, 쿼리 개수
	static int[] depth;		//	각 정점의 깊이(root = 0부터 자식으로 갈 수록 1씩 증가)
	static int[] parents;	//	각 정점의 부모 정점 번호(root의 부모는 -1)
	static int[] nChildren;	//	각 정점의 자식의 개수
	static int[] subtrees;	//	각 정점을 루트로 하는 서브트리의 정점 개수(자기 자신 포함)
	
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
	static List<ArrayList<Integer>> tree = new ArrayList<ArrayList<Integer>>();	//	트리 만들기 위한 인접리스트
	
	static void dfs(int v, int p, int d) {	//	dfs로 트리에서 각 정점의 깊이와 각 정점의 부모, 각 정점의 자식 정점 개수를 알아냄
		depth[v] = d;		//	v의 깊이 설정
		parents[v] = p;		//	v의 부모 설정
		
		for(int i = 0; i < tree.get(v).size(); i++) {
			int child = tree.get(v).get(i);
			
			if(depth[child] != -1)	//	이미 깊이가 정해진 경우, 방문한 적이 있음(자식 정점에서 부모정점을 다시 방문하지 못하도록)
 				continue;
			
			nChildren[v]++;			//	현재 정점 v의 자식 정점 수 1 증가
			dfs(child, v, d + 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		R = Integer.parseInt(tokens.nextToken());
		Q = Integer.parseInt(tokens.nextToken());
	
		depth = new int[N + 1];
		parents = new int[N + 1];
		nChildren = new int[N + 1];
		subtrees = new int[N + 1];
		Arrays.fill(depth, -1);
		Arrays.fill(parents, -1);
		
		for(int i = 0; i <= N; i++)
			tree.add(new ArrayList<Integer>());
		
		//	N - 1개의 트리 간선을 받음
		for(int i = 0; i < N - 1; i++) {
			int a, b;
			tokens = new StringTokenizer(bf.readLine());
			
			a = Integer.parseInt(tokens.nextToken());
			b = Integer.parseInt(tokens.nextToken());
			
			tree.get(a).add(b);
			tree.get(b).add(a);
		}
		
		dfs(R, -1, 0);
		
		Node[] nodes = new Node[N + 1];
		for(int i = 0; i <= N; i++) {
			nodes[i] = new Node(i, depth[i]);
		}
		Arrays.sort(nodes);	//	정점을 트리에서의 깊이에 대하여 내림차순으로 정렬
		
		//	bottom-up 방식으로 부모 노드의 subtree 정점 개수를 갱신함
		for(int i = 0; i < N; i++) {
			Node node = nodes[i];				//	현재 노드
			int num = node.num;					//	현재 정점 번호
			int parent = parents[num];			// 	현재 정점의 부모 정점
			
			subtrees[num] += 1;					//	현재 정점의 subtree의 정점 개수 1 추가(자기 자신 포함)
			if(parent == -1)					
				break;
			
			subtrees[parent] += subtrees[num];	//	부모 정점이 있을 경우 부모 정점의 서브트리에 현재 정점의 서브트리가 포함
		}

		for(int i = 0; i < Q; i++) {
			int num = Integer.parseInt(bf.readLine());
			System.out.println(subtrees[num]);
		}
	}

	
	static class Node implements Comparable<Node> {	//	정점 클래스
		int num;	//	정점 번호
		int depth;	//	깊이
		
		Node(int num, int child) {
			this.num = num;
			this.depth = child;
		}
		
		@Override
		public int compareTo(Node node) {
			// TODO Auto-generated method stub
			return -Integer.compare(this.depth, node.depth);	//	깊이가 깊은 정점이 앞으로 오도록
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "(" + this.num + ", " + this.depth + ")";
		}
	}
}