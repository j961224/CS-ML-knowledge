#방법1. 간단한 dijkstra 알고리즘 (시간 복잡도 O(V^2))
import sys
input = sys.stdin.readline
INF = int(1e9) # 10억

n,m = map(int,input().split())

start=int(input())
graph = [[] for i in range(n+1)]

visited = [False]*(n+1)

distance=[INF]*(n+1)

for _ in range(m):
    a,b,c = map(int,input().split())
    graph[a].append((b,c))

def get_smallest_path():
    min_value=INF
    index=0
    for i in range(1,n+1):
        if distance[i]<min_value and not visited[i]:
            min_value=distance[i]
            index=i
    return index

def dijkstra(start):
    distance[start]=0
    visited[start]=True
    for j in graph[start]:
        distance[j[0]]=j[1]
    
    for i in range(n-1):
        now = get_smallest_path()
        visited[now]=True
        for j in graph[now]:
            cost=distance[now]+j[1]
            if cost<distance[j[0]]:
                distance[j[0]]=cost

dijkstra(start)

for i in range(1,n+1):
    if distance[i]==INF:
        print("INF")
    else:
        print(distance[i])

