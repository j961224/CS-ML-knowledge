import java.util.*;
import java.io.*;

class Dot{
    int index;
    int cost;
    public Dot(int index, int cost){
        this.index=index;
        this.cost=cost;
    }
}

//자신보다 앞에 있는 사람 중에 실력이 더 낮은 사람을 찾아내면 된다.
// merge sort와 segment tree를 사용할 수 있다.
class Main{
    static int[] an;
    static Dot[] arr;
    static Dot[] sorted;
    static int N;
    public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
        
        an = new int[N];
        arr = new Dot[N];
        sorted = new Dot[N];
        
        for(int i=0; i<N; i++){
            int tmp = Integer.parseInt(br.readLine());
            
            arr[i]=new Dot(i,tmp); //index와 능력 저장
            an[i]=i+1; // 순위 저장
        }
        
        merge(0,N-1);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            sb.append(an[i]+"\n");
        }
        System.out.println(sb.toString());
    }
    
    public static void merge(int left, int right){
        
        if(left!=right){
            int mid = (left+right+1)/2;
            merge(left,mid-1);
            merge(mid,right);
            mergesort(left,right);
        }
    }
    
    public static void mergesort(int left, int right){
        int mid = (left+right+1)/2;
        
        int leftindex = left;
        int rightindex = mid;
        int cnt = left;
        
        while(leftindex<mid && rightindex<=right){
            //내림차순으로 해야한다!
            if(arr[leftindex].cost>arr[rightindex].cost){
                sorted[cnt]=arr[leftindex];
                cnt+=1;
                leftindex+=1;
            }
            else{
                sorted[cnt]=arr[rightindex];
                
                //오른쪽 배열의 요소가 크다면 왼쪽 배열의 leftindex부터 왼쪽 배열까지는 오른쪽 배열의 요소보다 작다.
                an[arr[rightindex].index]-=mid-leftindex;
                cnt+=1;
                rightindex+=1;
            }
        }
        
        while(leftindex<mid){
            sorted[cnt]=arr[leftindex];
            cnt++;
            leftindex++;
        }
        while(rightindex<=right){
            sorted[cnt]=arr[rightindex];
            cnt++;
            rightindex++;
        }
        
        for(int i=left; i<=right; i++){
            arr[i]=sorted[i];
        }
    }
}