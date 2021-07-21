# 벨만-포드
: 벨만-포드 알고리즘은 한 노드에서 다른 노드로 가는데 최단 거리를 구하는 알고리즘이다. 이 알고리즘의 장점은 다익스트라 알고리즘은 음수 가중치일 때, 사용하기 힘들지만 벨만-포드 알고리즘은 음수 가중치일 때, 사용가능하다.

- 벨만-포드는 다익스트라보다 시간복잡도가 더 걸리고 O(VE)의 시간복잡도가 걸린다.

* 벨만-포드의 전제 조건

  1. 최단 경로는 사이클을 포함하지 못 하므로, 최대 V-1개의 간선만 사용 가능하다.
  2. 최단 거리가 업데이트되는 노드가 없을 때까지 계속 반복되는데 음의 가중치로 업데이트가 계속 똑같은 것이 반복되면 탈출시켜준다.
 
* 벨만-포드의 주의할 점
  
  1. 특정 정점까지 경로가 존재하는 것을 알기 위해, Upper값이 초기에 max인지 아닌지로 구분하게 되면 음수 싸이클이 있다면 최단 거리가 정의되지 않는다.
 
 (마지막 V번째 순회에서 음수 가중치가 없을 때만 최단 거리를 정의할 수 있다!!)
 
 
 ~~~
 static boolean bellmanford(int start){
    dist[start] = 0;
	  // n번 반복 (음수 간선 순환 체크안하려면 n-1번 반복)
		for(int i=0; i<n; i++) {
			// 매 반복마다 모든 간선을 확인 
			for(int j=0; j<m; j++) {
				int cur = e[j].startpoint;
				int next = e[j].endpoint;
				int cost = e[j].cost;
						
				if(dist[e[j].startpoint] == INF) 
					continue;
				// 현재 간선을 거쳐서 다른 노드로 이동하는 거리가 짧은 경우 
				if(dist[next] > (dist[cur] + cost)) {
					dist[next] = dist[cur] + cost;
							
					// n번째 라운드에서 값이 갱신된다면 음수 순환 존재 
					if (i == n-1) {
						return true;
					}
				}
			}
		}
		return false;
	}
 
 ~~~

* 출처
  1. https://www.crocus.co.kr/534
  2. https://m.blog.naver.com/kks227/220796963742
  3. https://loosie.tistory.com/162
