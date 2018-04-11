// Chapter 1 - Question 3
// My answer: C - "s1.equals..."
// Correct answer: C - "s1.equals..."
public class TestQuestion3 {
    
    public static void main(String[] args) {
        String s1 = "test";
        String s2 = new String(s1);

        if ( s1 == s2 ) {
            System.out.println("s1 == s2");
        }
        if ( s1.equals(s2)) {
            System.out.println("s1.equals(s2)");
        }
    }

}