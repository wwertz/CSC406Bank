package Users;

/**
 * An abstract class for the User object that is the basis of the three User object types.
 */
abstract public class User {
    String ssn, firstName, lastName;

    /**
     * Constructor for the User object.
     */
    public User(String ssn, String firstName, String lastName) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;

    }

    /**
     * Getters
     */
    public String getSsn() { return ssn; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }

}
