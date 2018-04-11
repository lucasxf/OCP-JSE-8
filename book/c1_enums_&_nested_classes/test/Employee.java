// Chapter 1 - Question 1
// My answer: A - success
// Correct answer: A - success
public class Employee {

    public int employeeId;

    public String firstName, lastName;

    public int yearStarted;

    @Override
    public int hashCode() {
        return employeeId;
    }

    // if line 18 is uncommented, the code won't compile.
    // the next method isn't an override, but and overlod of Object.equals();
    // @Override
    public boolean equals(Employee e) {
        return this.employeeId == e.employeeId;
    }

    public static void main ( String[] args) {
        Employee one = new Employee();
        one.employeeId = 101;

        Employee two = new Employee();
        two.employeeId = 101;
        if ( one.equals(two)) {
            System.out.println("success");
        } else {
            System.out.println("failure");
        }
    }
    
}
