import heapq
import sys

INF = int(1e9)

N,M,C = map(int,input().split())

graph =[[] for i in range(N+1)]
distance = [INF]*(N+1)

for _ in range(M):
    x,y,z = map(int,input().split())
    
    graph[x].append((y,z))

def dijkstra(C):
    q=[]
    
    heapq.heappush(q,(C,0))
    distance[C]=0
    while q:
        now, dist = heapq.heappop(q)
        if distance[now]<dist:
            continue
        for i in graph[now]:
            cost = i[1]+dist
            if cost<distance[i[0]]:
                distance[i[0]]=cost
                heapq.heappush(q,(i[0],cost))

dijkstra(C)

count=-1
maxdis=0

for i in distance:
    if i != INF:
        count+=1
        maxdis=max(maxdis,i)

print(count,maxdis)