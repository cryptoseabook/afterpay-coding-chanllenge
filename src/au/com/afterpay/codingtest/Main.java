package au.com.afterpay.codingtest;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        FradulentDetectionService fradulentDetectionService = new FradulentDetectionService();

        List<String> transactions = new ArrayList<>();
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
        transactions.add("10d7ce2f43e35fa57d1bbf8b1e2, 2014-05-01T23:15:54, 20.00");
        transactions.add("10d7ce2f43e35fa57d1bbf8b1e2, 2014-05-02T01:15:54, 20.00");


        boolean result = fradulentDetectionService.isFraudulent(transactions, "10d7ce2f43e35fa57d1bbf8b1e2", 150);

        System.out.println(result);

    }
}
