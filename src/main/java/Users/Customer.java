package Users;


/**
 * The Users class for Customer
 */
public class Customer extends User {

    private String ssn, firstName, lastName, address, city, state, zipcode, atmCard, pin;
    /**
     * Constructor for the Customer object.
     */
    public Customer(String ssn, String firstName, String lastName, String address, String city, String state,
             String zipcode, String atmCard, String pin) {

        super(ssn, firstName, lastName);
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.atmCard = atmCard;
        this.pin = pin;

    }


    /**
     * @return toString
     */
    @Override
    public String toString() {
        return
                this.getSsn() + "," +
                this.getFirstName() + "," +
                this.getLastName() + "," +
                address + "," +
                city + "," +
                state + "," +
                zipcode + "," +
                atmCard + "," +
                pin;
    }


    /**
     * The Getters and Setter for the Customer object.
     */
    public String getAddress() {return address;}
    public String getCity() {return city;}
    public String getState() {return state;}
    public String getZipcode() {return zipcode;}
    public String getAtmCard() {return atmCard;}
    public String getPin() {return pin;}
    public void setPin(String pin) {this.pin = pin;}
}
