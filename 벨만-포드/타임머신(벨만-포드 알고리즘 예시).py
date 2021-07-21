import sys
import heapq
#음수로 인해 dijsktra를 사용 못 함
#첫 번째 시도: dijsktra를 이용했지만 틀림
#두 번째 시도: 벨만-포드 알고리즘 사용
input = sys.stdin.readline
INF = int(1e9)

N,M = map(int,input().split())
distance = [INF]*(N+1)
graph = [[] for i in range(N+1)]
distance[1]=0


for _ in range(M):
    a,b,cost = map(int,input().split())
    graph[a].append((b,cost))

check = True
for repeat in range(N):
    for i in range(1,N+1):
        for node,cost in graph[i]:
            if distance[node]>distance[i]+cost and distance[i]<INF:
                distance[node]=distance[i]+cost
                #N-1일 때도 distance가 업데이트 된다는 말은 음의 순환이 있다는 증거
                if repeat == N-1:
                    check=False

if check:
    for i in range(2,N+1):
        if distance[i]>=INF:
            print(-1)
        else:
            print(distance[i])
else:
    print(-1)