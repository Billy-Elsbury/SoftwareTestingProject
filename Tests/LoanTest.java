import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;


public class LoanTest {

        //Testing no argument Constructor
        @Test
        public void testNullConstructor() {
            Loan loan = new Loan();
            assertEquals(0, loan.getAmount());
            assertEquals(0, loan.getPeriod());
            assertEquals(0, loan.getRate());
        }


    //Testing Constructor by creating a new object and then testing that every field of the object has the correct value.
        @Test
        public void testConstructor() {
            Loan loan = new Loan(5000, 2);
            assertEquals(5000, loan.getAmount());
            assertEquals(2, loan.getPeriod());
            assertEquals(10, loan.getRate());
            assertEquals(230.72, loan.getMonthlyPayment(), 0.01);
            assertEquals(5537.39, loan.getTotalPayment(), 0.01);
        }

        //Tests for Get Monthly Payment:

        @Test
        public void testGetMonthlyPaymentUpperAmountUpperPeriod() {
            //interest: 5%
            Loan loan = new Loan(5003, 5);
            assertEquals(94.41, loan.getMonthlyPayment(), 0.01);
        }

        @Test
        public void testGetMonthlyPaymentUpperAmountLowerPeriod() {
            //interest: 8%
            Loan loan = new Loan(5003, 1);
            assertEquals(435.20, loan.getMonthlyPayment(), 0.01);
        }

        @Test
        public void testGetMonthlyPaymentLowerAmountLowerPeriod() {
            //interest: 10%
            Loan loan = new Loan(500, 1);
            assertEquals(43.96, loan.getMonthlyPayment(), 0.01);
        }

        @Test
        public void testGetMonthlyPaymentLowerAmountUpperPeriod() {
            //interest: 6%
            Loan loan = new Loan(500, 5);
            assertEquals(9.67, loan.getMonthlyPayment(), 0.01);
        }

        // Test for getAmount method:
        @Test
        public void testGetAmount() {
            Loan loan = new Loan(5000, 2);
            assertEquals(5000, loan.getAmount());
        }



        // Test for getPeriod method:

        @Test
        public void testGetPeriod() {
            Loan loan = new Loan(5000, 2);
            assertEquals(2, loan.getPeriod());
        }

        // Test for getRate method:

        @Test
        public void testGetInterestRateUpperAmountUpperPeriod() {
            //interest: 5%
            Loan loan = new Loan(5003, 5);
            assertEquals(5, loan.getRate(), 0.01);
        }

        @Test
        public void testGetInterestRateUpperAmountLowerPeriod() {
            //interest: 8%
            Loan loan = new Loan(5003, 1);
            assertEquals(8, loan.getRate(), 0.01);
        }

        @Test
        public void testGetInterestRateLowerAmountLowerPeriod() {
            //interest: 10%
            Loan loan = new Loan(500, 1);
            assertEquals(10, loan.getRate(), 0.01);
        }

        @Test
        public void testGetInterestRateLowerAmountUpperPeriod() {
            //interest: 6%
            Loan loan = new Loan(500, 5);
            assertEquals(6, loan.getRate(), 0.01);
        }

        // Tests for getTotalPayment method:

        @Test
        public void testGetTotalPaymentUpperAmountUpperPeriod() {
            //interest: 5%
            Loan loan = new Loan(5003, 5);
            assertEquals(5664.77, loan.getTotalPayment(), 0.01);
        }

        @Test
        public void testGetTotalPaymentUpperAmountLowerPeriod() {
            //interest: 8%
            Loan loan = new Loan(5003, 1);
            assertEquals(5222.44, loan.getTotalPayment(), 0.01);
        }

        @Test
        public void testGetTotalPaymentLowerAmountLowerPeriod() {
            //interest: 10%
            Loan loan = new Loan(500, 1);
            assertEquals(527.50, loan.getTotalPayment(), 0.01);
        }

        @Test
        public void testGetTotalPaymentLowerAmountUpperPeriod() {
            //interest: 6%
            Loan loan = new Loan(500, 5);
            assertEquals(579.98, loan.getTotalPayment(), 0.01);
        }



        //Testing illegal Argument Exceptions

        // Negative Test for getAmount method:
        @Test
        public void testGetAmountIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> {
                Loan loan = new Loan(0, 2);
            });

        }

        @Test
        public void testIllegalArgumentExceptionLessThan500() {
             assertThrows(IllegalArgumentException.class, () -> {
                new Loan(499, 2);
            });
        }

        @Test
        public void testIllegalArgumentExceptionGreaterThan10000() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Loan(10001, 2);
            });
        }

        @Test
        public void testIllegalArgumentExceptionLessThan1Year() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Loan(5000, 0);
            });
        }

        @Test
        public void testIllegalArgumentExceptionGreaterThan5Years() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Loan(5000, 6);
            });
        }



        //As setRate is private it must be tested using reflection.

        //test loan, Amount: lower boundary, Period: lower boundary

        @Test
        public void testSetRateLowerAmountLowerPeriod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            Loan loan = new Loan(500, 2);
            Method setRateMethod = Loan.class.getDeclaredMethod("setRate", int.class);
            setRateMethod.setAccessible(true);
            setRateMethod.invoke(loan, 4);
            assertEquals(6, loan.getRate());
        }


        //test loan, Amount: lower boundary, Period: upper boundary

        @Test
        public void testSetRateLowerAmountUpperPeriod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            Loan loan = new Loan(500, 5);
            Method setRateMethod = Loan.class.getDeclaredMethod("setRate", int.class);
            setRateMethod.setAccessible(true);
            setRateMethod.invoke(loan, 3);
            assertEquals(10, loan.getRate());
        }

        //test loan, Amount: Higher boundary, Period: lower boundary

        @Test
        public void testSetRateUpperAmountLowerPeriod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            Loan loan = new Loan(5001, 2);
            Method setRateMethod = Loan.class.getDeclaredMethod("setRate", int.class);
            setRateMethod.setAccessible(true);
            setRateMethod.invoke(loan, 4);
            assertEquals(5, loan.getRate());
        }


        //test loan, Amount: Higher boundary, Period: higher boundary

        @Test
        public void testSetRateUpperAmountUpperPeriod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            Loan loan = new Loan(5001, 5);
            Method setRateMethod = Loan.class.getDeclaredMethod("setRate", int.class);
            setRateMethod.setAccessible(true);
            setRateMethod.invoke(loan, 3);
            assertEquals(8, loan.getRate());
        }




        //To test setRate negatively the amount must be set to below 500

        @Test
        public void testLessThan500Amount() {
            assertThrows(IllegalArgumentException.class, () -> new Loan(499, 1));
        }




        //Parameterized testing invalid values
        @ParameterizedTest
        @CsvSource({
                "499, 3",
                "10001, 3",
                "500, 0",
                "500, 6",

        })
        void testInvalidLoanValues(int amount, int period) {
            assertThrows(IllegalArgumentException.class, () -> new Loan(amount, period));
        }



        //Parameterized testing valid values

        @ParameterizedTest
        @CsvSource({
                "500, 1, 10, 43.96, 527.50",
                "5003, 5, 5, 94.41, 5664.77",
                "5003, 2, 8, 226.27, 5430.53",
                "500, 5, 6, 9.67, 579.98",
                "10000, 5, 5, 188.71, 11322.74"
        })
        public void LoanParam(double loanAmount, int period, double interestRate, double expectedMonthlyPayment, double expectedTotalPayment) {
            Loan loan10 = new Loan(loanAmount, period);

            double actualMonthlyPayment = loan10.getMonthlyPayment();
            assertEquals(expectedMonthlyPayment, actualMonthlyPayment,  0.01);

            double actualTotalPayment = loan10.getTotalPayment();
            assertEquals(expectedTotalPayment, actualTotalPayment, 0.01);
        }



}

