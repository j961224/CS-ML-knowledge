import java.util.*;
//Trie를 사용
class Solution {
    class Trie{
        Map<Integer,Integer> lenmap = new HashMap<>();
        Trie[] child = new Trie[26];
        
        void insert(String str){
            Trie node = this;
            int len = str.length();
            lenmap.put(len,lenmap.getOrDefault(len,0)+1); //모두 ?일 경우를 위해서
            
            for(char c : str.toCharArray()){
                int idx = c - 'a';
                if(node.child[idx]==null){
                    node.child[idx]=new Trie();
                }
                
                node = node.child[idx];
                node.lenmap.put(len,node.lenmap.getOrDefault(len,0)+1);
            }
        }
        
        int find(String str, int i){
            if(str.charAt(i)=='?'){
                return lenmap.getOrDefault(str.length(),0);
            }
            
            int idx = str.charAt(i) - 'a';
            if(child[idx]==null){
                return 0;
            }
            else{
                return child[idx].find(str,i+1);
            }
        }
        
    }
    
    public int[] solution(String[] words, String[] queries) {
        Trie front = new Trie();
        Trie back = new Trie();
        
        for(int i=0; i<words.length; i++){
            front.insert(words[i]);
            back.insert(reverse(words[i]));
        }
        
        return Arrays.stream(queries).mapToInt(
            query -> query.charAt(0)=='?'? back.find(reverse(query),0):front.find(query,0)).toArray();

    }
    
    String reverse(String s){
        return new StringBuilder(s).reverse().toString();
    }
}