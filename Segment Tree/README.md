# Segment Tree

: 특정 연속되는 구역의 데이터 합을 구할 때, 유용하게 사용되는 자료구조이다. 보통 배열로 할 시 시간복잡도가 O(N)정도가 나오는데 이를 트리로 이용할 시, 트리 구조 상으로 시간복잡도 O(logN)이 측정된다.

## 구간합 구하기

: 앞서 말한 Segment Tree로 원래 데이터의 범위를 반으로 나누며 그 합들을 더해 저장하여 **구간합 트리**를 구성할 수 있다.


그리하여 만든 Segment Tree는 리프 노드와 그와 다른 노드로 구성된다.


  * 리프노드: 배열의 그 수 자체를 뜻한다.
   
  * 다른노드: 왼쪽 자식과 오른쪽 자식의 데이터 합을 뜻한다.


아래의 그림은 각 노드가 저장하고 있는 합의 범위를 보여준다.

![zzzz](https://user-images.githubusercontent.com/59636424/124778408-ee7be300-df7b-11eb-9b4e-847ab5b2c403.png)


이러한 구간 트리에 한해서는 root 인덱스가 1로 시작하고 그 이유는 2를 곱하면 왼쪽 노드를 가르키는 것이 쉽기 때문이다.

아래의 코드는 Segment Tree를 활용한 구간 합을 만드는 코드이다.

~~~
public static long init(int start, int end,int node){
     if(start==end){
            return tree[node]=arr[start];
     }
        
     int mid = (start+end)/2;
     //구간을 반으로 나누어 그 합을 저장한다.
     return tree[node] = init(start,mid,node*2)+init(mid+1,end,node*2+1);
}
~~~
