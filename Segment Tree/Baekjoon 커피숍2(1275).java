import java.util.*;


class Main{
    static long[] arr;
    static int N,K;
    static long[] tree;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        arr = new long[N+1];
        tree = new long[4*N];
        for(int i=1; i<N+1; i++){
            arr[i]=sc.nextInt();
        }
        
        //segment tree를 만들자.
        init(1,N,1);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<K; i++){
            int start = sc.nextInt();
            int end = sc.nextInt();
            if(start>end){
                int tmp = end;
                end=start;
                start=tmp;
            }
            sb.append(sum(1,N,1,start,end)+"\n");
            int changeindex = sc.nextInt();
            int changecost = sc.nextInt();
            long diff = changecost - arr[changeindex];
            arr[changeindex]=changecost;
            update(1,N,1,changeindex,diff);
        }
        System.out.println(sb);
    }
    
    public static long init(int start, int end,int node){
        if(start==end){
            return tree[node]=arr[start];
        }
        
        int mid = (start+end)/2;
        return tree[node] = init(start,mid,node*2)+init(mid+1,end,node*2+1);
    }
    
    public static long sum(int first,int second, int node, int start, int end){
        if(start>second || end<first){
            return 0;
        }
        if(start<=first && second<=end){
            return tree[node];
        }
        
        int mid = (first+second)/2;
        return sum(first,mid,node*2,start,end)+sum(mid+1,second,node*2+1,start,end);
    }
    
    public static void update(int start,int end, int node, int cindex, long cc){
        if(cindex<start || end<cindex){
            return;
        }
        
        tree[node]+=cc;
        if(start==end){
            return;
        }
        int mid = (start+end)/2;
        update(start,mid,node*2,cindex,cc);
        update(mid+1,end,node*2+1,cindex,cc);
    }
}