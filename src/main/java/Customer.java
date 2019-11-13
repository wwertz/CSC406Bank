import java.time.*;

/**
 * Dates need to be changed from String to localdate
 *
 * I question if things dealing with money should be BigDecimal or ints. Not long or double so we dont have
 * floating point errors
 *
 **/

class Customer {

    private String ssn, firstName, lastname, address, city, state, zipcode, atmCard;

    Customer(String ssn, String firstName, String lastName, String address, String city, String state,
             String zipcode, String atmCard) {

        this.ssn = ssn;
        this.firstName = firstName;
        this.lastname = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.atmCard = atmCard;

    }

    //getters
    public String getSsn() {return ssn;}
    public String getFirstName() {return firstName;}
    public String getLastname() {return lastname;}
    public String getAddress() {return address;}
    public String getCity() {return city;}
    public String getState() {return state;}
    public String getZipcode() {return zipcode;}
    public String getAtmCard() {return atmCard;}
}