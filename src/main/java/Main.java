// CSC 406 Banking Project

// Jake Blessing
// Benjamin Oliphant
// Sam Poirer
// Wayne Wertz

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        //LoginScreen initial = new LoginScreen();

Loan a1 = new Loan(1000, 423148894, 3579, 5000,.15,  95.83, LocalDate.of(2019,11,27),
        LocalDate.of(2019,10,1),LocalDate.of(2019,8,20), false, 's');

System.out.println(a1.getLastPaymentDate());
a1.payment(95.83);
System.out.println(a1.getLastPaymentDate());
System.out.println(a1.getBalance());







    }

}

