package nl.bongers.testdome;

/**
 * A palindrome is a word that reads the same backward or forward.
 *
 * Write a function that checks if a given word is a palindrome. Character case should be ignored.
 *
 * For example, isPalindrome("Deleveled") should return true as character case should be ignored, resulting in "deleveled",
 * which is a palindrome since it reads the same backward and forward.
 */
public class Palindrome {

    public static void main(String[] args) {
        final Palindrome palindrome = new Palindrome();
        System.out.println(palindrome.isPalindrome("Deleveled"));
        System.out.println(palindrome.isPalindrome("Samsung"));
    }

    private boolean isPalindrome(String value) {
        final String reversed = new StringBuilder(value).reverse().toString();
        return value.equalsIgnoreCase(reversed);
    }
}