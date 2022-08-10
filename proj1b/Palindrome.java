public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> ret= new LinkedListDeque<Character>();
        for (int i=0; i<word.length();i++){
            ret.addLast(word.charAt(i));
        }
        return ret;
    }

    public boolean isPalindrome(String word){
        Deque<Character> dequeword =wordToDeque(word);
        while (dequeword.size() > 1){
            char head = dequeword.removeFirst();
            char tail = dequeword.removeLast();
            if(head != tail){
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> dequeword =wordToDeque(word);
        while (dequeword.size() > 1){
            char head = dequeword.removeFirst();
            char tail = dequeword.removeLast();
            if(!cc.equalChars(head,tail)){
                return false;
            }
        }
        return true;
    }
}
