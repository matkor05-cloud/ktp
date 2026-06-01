public class Palindrome {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            if (isPalindrome(s)) {
                System.out.println(s + " - палиндром");
            } else {
                System.out.println(s + " - не палиндром");
            }
        }
    }

    public static String reverseString(String s) {
        String reversed_s = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            reversed_s += s.charAt(i);
        }
        return reversed_s;
    }

    public static boolean isPalindrome(String s) {
        String reversed_s = reverseString(s);
        return s.equals(reversed_s);
    }
}