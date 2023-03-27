import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoanTest {

    private Loan loan;

    @BeforeEach
    public void setUp() throws Exception {
        loan = new Loan(5000, 3);
    }

    // Test the constructor
    @Test
    public void testConstructor() {
        assertNotNull(loan);
    }

    // Test the getAmount method
    @Test
    public void testGetAmount() {
        assertEquals(5000, loan.getAmount(), 0.0);
    }

    // Test the getPeriod method
    @Test
    public void testGetPeriod() {
        assertEquals(3, loan.getPeriod());
    }

    // Test the getRate method
    @Test
    public void testGetRate() {
        assertEquals(10, loan.getRate(), 0.0);
    }

    // Test the getMonthlyPayment method
    @Test
    public void testGetMonthlyPayment() {
        assertEquals(161.33, loan.getMonthlyPayment(), 0.01);
    }

    // Test the getTotalPayment method
    @Test
    public void testGetTotalPayment() {
        assertEquals(5799.08, loan.getTotalPayment(), 0.01);
    }

    // Test the setAmount method with valid input
    @Test
    public void testSetAmountValidInput() {
        loan.setAmount(6000);
        assertEquals(6000, loan.getAmount(), 0.0);
    }

    // Test the setAmount method with invalid input
    @Test
    public void testSetAmountInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            loan.setAmount(-500);
        });
    }

    // Test the setRate method with valid input
    @Test
    public void testSetRateValidInput() {
        loan.setRate(2);
        assertEquals(10, loan.getRate(), 0.0);
    }

    // Test the setPeriod method with valid input
    @Test
    public void testSetPeriodValidInput() {
        loan.setPeriod(4);
        assertEquals(48, loan.getPeriod());
    }

    // Test the setPeriod method with invalid input
    @Test
    public void testSetPeriodInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            loan.setPeriod(0);
        });
    }
}
