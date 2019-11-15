package Users;

abstract public class User {
    String ssn, firstName, lastName;

    public User(String ssn, String firstName, String lastName) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;

    }


    public String getSsn() { return ssn; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }

}
