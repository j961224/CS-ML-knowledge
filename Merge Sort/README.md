# Merge Sort
: Merge sort는 어떤 배열을 잘게 자르고 다시 조합해 원래의 문제를 푸는 느낌의 자료구조이다. 이 정렬은 전체를 상대로 수행하지 않고 분할한 뒤 각 부분집합들에 대해 정렬한 후 다시 결합하는 분할 정복하는 개념이다.

 * 분할: 입력 배열과 같은 크기의 2개 부분 배열로 분할한다.
 
 * 정복: 부분 배열을 정렬하는데 크기가 작다면 순환 호출로 다시 분할 정복에 적용한다.
 
 * 결합: 나뉜 부분 배열을 합친다.


아래와 같이 쪼개고 쪼갠 역순으로 정렬하여 하나씩 결합한다.

![zzz](https://user-images.githubusercontent.com/59636424/124783295-ecb41e80-df7f-11eb-9d9f-94479fdd4767.png)


## Merge sort의 알고리즘

### 1. Merge Sort 합병

![zzz](https://user-images.githubusercontent.com/59636424/124784183-a7dcb780-df80-11eb-8495-1030683392d5.png)

(그림 참고: https://gmlwjd9405.github.io/2018/05/08/algorithm-merge-sort.html)

위의 그림과 같이 2개의 배열을 합병하는 과정을 말해보고자 한다.

1. 2개의 리스트를 하나씩 비교하여 작은 값을 새로운 리스트로 옮긴다.
2. 1번 과정을 2개 리스트 중에 하나가 끝날 때까지 시행한다.
3. 하나가 끝나면 다른 하나는 그대로 새로운 리스트에 옮긴다.
4. 그렇게 만든 새로운 리스트를 원래 리스트에 적용한다.

~~~
public static void merge(int a[], int m, int middle, int n) { 
   int i = m;  
   int j = middle+1; 
   int k = m;
   
   while(i<=middle && j<=n) { 
      if(a[i]<=a[j]) { 
         sorted[k] = a[i]; 
         i++; 
       }
       else {
         sorted[k] = a[j]; 
         j++; 
       } 
       k++; 
   } 
   
   if(i>middle) { 
      for(int t=j;t<=n;t++,k++) { 
         sorted[k] = a[t]; 
      } 
   }
   
   else { 
      for(int t=i;t<=middle;t++,k++) { 
         sorted[k] = a[t]; 
      } 
   } 
   
   for(int t=m;t<=n;t++) { 
      a[t] = sorted[t]; 
   } 
}
~~~


### 2. Merge Sort 분할
: 가장 먼저하는 작업으로 중간을 기준으로 분할한다.

~~~
public static void merge(int left, int right){
        //left와 right가 같아졌다는 것은 하나의 원소만 남았다는 것이므로 끝내야한다.
        if(left!=right){
            int mid = (left+right+1)/2;
            merge(left,mid-1);
            merge(mid,right);
            mergesort(left,right);
        }
    }
~~~
