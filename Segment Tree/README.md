# Segment Tree

: 특정 연속되는 구역의 데이터 합을 구할 때, 유용하게 사용되는 자료구조이다. 보통 배열로 할 시 시간복잡도가 O(N)정도가 나오는데 이를 트리로 이용할 시, 트리 구조 상으로 시간복잡도 O(logN)이 측정된다.

## 구간합 트리 생성

: 앞서 말한 Segment Tree로 원래 데이터의 범위를 반으로 나누며 그 합들을 더해 저장하여 **구간합 트리**를 구성할 수 있다.


그리하여 만든 Segment Tree는 리프 노드와 그와 다른 노드로 구성된다.


  * 리프노드: 배열의 그 수 자체를 뜻한다.
   
  * 다른노드: 왼쪽 자식과 오른쪽 자식의 데이터 합을 뜻한다.


아래의 그림은 각 노드가 저장하고 있는 합의 범위를 보여준다.

![zzzz](https://user-images.githubusercontent.com/59636424/124778408-ee7be300-df7b-11eb-9b4e-847ab5b2c403.png)


이러한 구간 트리에 한해서는 root 인덱스가 1로 시작하고 그 이유는 2를 곱하면 왼쪽 노드를 가르키는 것이 쉽기 때문이다.

아래의 코드는 **구간 합 트리**를 만드는 코드이다.

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


## 구간합 구하기

: 이 또한 탐색하는데 트리 구조로 O(logN)이 나온다. 구간합은 범위 안에 있는 경우에만 더해준다.


~~~
public static long sum(int first,int second, int node, int start, int end){
        // 이 경우에는 겹치지 않기 때문에, 더 이상 탐색을 이어나갈 필요가 없다.
        if(start>second || end<first){
            return 0;
        }
        //구간 범위를 모두 포함하고 있으니 더 이상의 호출은 비효율적으로 return한다.
        if(start<=first && second<=end){
            return tree[node];
        }
        
        int mid = (first+second)/2;
        return sum(first,mid,node*2,start,end)+sum(mid+1,second,node*2+1,start,end);
}
~~~


## 특정 원소의 값 변경하기

: 중간에 어떤 수를 변경한다면, 해당 숫자를 포함한 모든 노드를 변경해야한다.

아래의 그림은 만일 5라는 index의 숫자를 변경할 시, 변경해야 하는 노드를 나타내고 있다.

![zzzz](https://user-images.githubusercontent.com/59636424/124781330-474c7b00-df7e-11eb-921c-852ab458a34e.png)


~~~
public static void update(int start,int end, int node, int cindex, long cc){
        //범위 벗어날 시 return
        if(cindex<start || end<cindex){
            return;
        }
        
        //갱신 시키기 (cc는 원래 값과 바꿀 값의 차이를 저장한 변수이다.)
        tree[node]+=cc;
        if(start==end){
            return;
        }
        int mid = (start+end)/2;
        update(start,mid,node*2,cindex,cc);
        update(mid+1,end,node*2+1,cindex,cc);
}
~~~
