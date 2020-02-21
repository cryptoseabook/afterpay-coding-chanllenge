package au.com.afterpay.codingtest.codingtest;


import au.com.afterpay.codingtest.CreditCardTransaction;
import au.com.afterpay.codingtest.FradulentDetectionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class FradulentDetectionServiceTest {
    private FradulentDetectionService fradulentDetectionService;

    private List<String> transactions = new ArrayList<>();

    @Before
    public void setup() {
        fradulentDetectionService = new FradulentDetectionService();

        transactions.add("10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 10.00");
        transactions.add("10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T14:15:54, 10.00");
        transactions.add("10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T15:15:54, 100.00");

        transactions.add("10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-30T16:15:54, 10.00");
        transactions.add("10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-30T17:15:54, 10.00");
        transactions.add("10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-30T18:15:54, 10.00");

        transactions.add("10d7ce2f43e35fa57d1bbf8b1e3, 2014-05-01T19:15:54, 100.00");
        transactions.add("10d7ce2f43e35fa57d1bbf8b1e2, 2014-05-01T20:15:54, 100.00");
        transactions.add("10d7ce2f43e35fa57d1bbf8b1e2, 2014-05-01T21:15:54, 10.00");
        transactions.add("10d7ce2f43e35fa57d1bbf8b1e2, 2014-05-01T22:15:54, 10.00");
        transactions.add("10d7ce2f43e35fa57d1bbf8b1e3, 2014-05-01T23:15:54, 20.00");
        transactions.add("10d7ce2f43e35fa57d1bbf8b1e2, 2014-05-02T01:15:54, 20.00");

    }

    @Test
    public void parseTxn() {
        String transaction = "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 10.00";

        CreditCardTransaction creditCardTransaction = fradulentDetectionService.parseTxn(transaction);

        String id = creditCardTransaction.getId();
        Date date = creditCardTransaction.getDate();
        double amount = creditCardTransaction.getAmount();

        Assert.assertEquals("10d7ce2f43e35fa57d1bbf8b1e2", id);
        Assert.assertEquals(10.0, amount, 0);
    }


    @Test(expected = IllegalArgumentException.class)
    public void parseTxnFailed() {
        String transaction = "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54 10.00";
        CreditCardTransaction creditCardTransaction = fradulentDetectionService.parseTxn(transaction);
    }

    @Test
    public void setupCreditCardTxnBank() {
        Map<String, List<CreditCardTransaction>> creditCardTxnBank = fradulentDetectionService.setupCreditCardTxnBank(transactions);
        Assert.assertEquals(2, creditCardTxnBank.keySet().size());

        Assert.assertEquals(2,creditCardTxnBank.get("10d7ce2f43e35fa57d1bbf8b1e3").size());
        Assert.assertEquals(10,creditCardTxnBank.get("10d7ce2f43e35fa57d1bbf8b1e2").size());
    }

    @Test
    public void isFraudulent() {
        boolean fraudulent = fradulentDetectionService.isFraudulent(transactions, "10d7ce2f43e35fa57d1bbf8b1e3", 100);
        Assert.assertTrue(fraudulent);

        fraudulent = fradulentDetectionService.isFraudulent(transactions, "10d7ce2f43e35fa57d1bbf8b1e2", 150);
        Assert.assertFalse(fraudulent);
    }
}
