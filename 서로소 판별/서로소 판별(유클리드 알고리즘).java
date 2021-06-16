import java.util.*;

class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> answer = new ArrayList<>();
        
        int N = sc.nextInt();
        int[] arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i]=sc.nextInt();
        }
        int X = sc.nextInt();
        
        //서로소 판별(유클리드 방법)
        for(int i=0; i<N; i++){
            int a = X;
            int b = arr[i];
            int r=0;
            int q=0;
            
            while(true){
                q=a/b;
                r=a%b;
                if(r==0){
                    break;
                }
                a=b;
                b=r;
            }
            
            //서로소가 아니라면 b가 최대공약수가 된다.
            if(b==1){
                answer.add(arr[i]);
            }
        }
        
        int sum=0;
        for(int z : answer){
            sum+=z;
        }
        System.out.println(sum/answer.size());
    }
}