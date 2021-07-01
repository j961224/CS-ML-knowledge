import java.util.*;
import java.io.*;

class Main{
    static class Trie{
        public TrieNode rootNode;
        
        public Trie(){
            this.rootNode = new TrieNode();
        }
        
        public void insert(String str){
            TrieNode thisnode = this.rootNode;
            for(int i=0; i<str.length(); i++){
                char c = str.charAt(i);
                // 두 값(단어의 문자와 현재 노드의 KEY값) 이 다르면 새로운 Node 생성
                thisnode = thisnode.getChildNodes().computeIfAbsent(c,key -> new TrieNode());
            }
            thisnode.setIsLastChar(true);
        }
        
        public int contains(String word){
            TrieNode thisnode = this.rootNode;
            int count=1; //첫글자는 무조건 입력
            
            thisnode = thisnode.getChildNodes().get(word.charAt(0));
            
            for(int i=1; i<word.length(); i++){
                if(thisnode.getChildNodes().size()>=2){
                    count++;
                }
                //마지막 문자지만 뒤에 다른 단어들이 있을 때
                else if(thisnode.getChildNodes().size()==1 && thisnode.isLastChar()){
                    count++;
                }
                
                char c1 = word.charAt(i);
                TrieNode node = thisnode.getChildNodes().get(c1);
                // 다음 이어지는 문자에 관한 map 저장
                thisnode = node;
            }
            
            return count;
        }
        
    }
    
    static class TrieNode{
        private Map<Character,TrieNode> childNodes = new HashMap<>();
        private boolean isLastChar;
        
        public Map<Character,TrieNode> getChildNodes(){
            return this.childNodes;
        }
        
        public void setIsLastChar(boolean isLastChar){
            this.isLastChar = isLastChar;
        }
        
        public boolean isLastChar(){
            return this.isLastChar;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String Nm;
        while((Nm = br.readLine()) != null){
            int N=Integer.parseInt(Nm);
            String[] a = new String[N];
            Trie tri = new Trie();
            for(int i=0; i<N; i++){
                String tmp = br.readLine();
                a[i]=tmp;
                tri.insert(tmp);
            }
            
            double sum = 0;
            for(String k : a){
                sum+=tri.contains(k);
            }
            // sb.append(String.format("%.2f",(double)(sum/N)));
            // sb.append("\n");
            bw.write(String.format("%.2f", sum / N) + "\n");
            //Nm = br.readLine();
        }
        //System.out.println(sb.toString());
        bw.flush();
    }
}