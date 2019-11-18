package Users;

/**
 * Dates need to be changed from String to localdate
 *
 * I question if things dealing with money should be BigDecimal or ints. Not long or double so we dont have
 * floating point errors
 *
 **/

public class Customer extends User {

    private String ssn, firstName, lastName, address, city, state, zipcode, atmCard;

    public Customer(String ssn, String firstName, String lastName, String address, String city, String state,
             String zipcode, String atmCard) {

        super(ssn, firstName, lastName);
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.atmCard = atmCard;

    }

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
                atmCard;
    }

    //getters
    public String getAddress() {return address;}
    public String getCity() {return city;}
    public String getState() {return state;}
    public String getZipcode() {return zipcode;}
    public String getAtmCard() {return atmCard;}
}
//CD's
//CheckingAccount
//Savings accounts
//CreditCard
//DebitAccount
//Loans