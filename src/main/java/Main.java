// CSC 406 Banking Project

// Jake Blessing
// Benjamin Oliphant
// Sam Poirer
// Wayne Wertz

import java.time.LocalDate;
//import javafx.*;

public class Main {

    public static void main(String[] args) {


Loan a1 = new Loan(5000, 3579,.15,  95.83, LocalDate.of(2019,10,27),
        LocalDate.of(2019,10,1),LocalDate.of(2019,8,20));
a1.payment(95.83);

System.out.println(a1.getLastPaymentDate());






    }

}

