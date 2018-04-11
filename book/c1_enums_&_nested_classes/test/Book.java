// Chapter 1 - Question 1
// My answer: A - the code compiles.
// Correct answer: A.
public class Book {

    private int ISBN;
    private String title, author;
    private int pageCount;

    @Override
    public int hashCode() {
        return ISBN;
    }

    @Override
    public boolean equals(Object obj) {
        if ( !(obj instanceof Book)) {
            return false;
        }
        Book other = (Book) obj;

        return this.ISBN == other.ISBN;
    }
    
}