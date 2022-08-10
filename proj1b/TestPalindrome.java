import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static OffByOne offbyone = new OffByOne();
    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome() {
        String A = "a";
        String B = "abccba";
        String C = "abboo";
        String D = "oo";
        String E = "";
        String F = "Abcba";
        assertTrue(palindrome.isPalindrome(A));
        assertTrue(palindrome.isPalindrome(B));
        assertTrue(palindrome.isPalindrome(D));
        assertTrue(palindrome.isPalindrome(E));
        assertFalse(palindrome.isPalindrome(C));
        assertFalse(palindrome.isPalindrome(F));

        //test the new isPalindrome below
        assertTrue(palindrome.isPalindrome("abb",offbyone));
        assertTrue(palindrome.isPalindrome("bgha",offbyone));
        assertTrue(palindrome.isPalindrome("a",offbyone));
        assertTrue(palindrome.isPalindrome("",offbyone));
        assertFalse(palindrome.isPalindrome("abc",offbyone));
        assertFalse(palindrome.isPalindrome("gg",offbyone));
    }
}
