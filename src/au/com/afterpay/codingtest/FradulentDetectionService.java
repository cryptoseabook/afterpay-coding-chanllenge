package au.com.afterpay.codingtest;

import java.util.*;
import java.util.stream.Collectors;

public class FradulentDetectionService {

    public CreditCardTransaction parseTxn(String transaction) {
        if (transaction == null || transaction.isEmpty()) {
            throw new IllegalArgumentException("Invalid creditCard details");
        }

        String[] creditCardDetails = transaction.split(",");
        if (creditCardDetails == null || creditCardDetails.length != 3) {
            throw new IllegalArgumentException("Invalid creditCard details");
        }

        String id = creditCardDetails[0];
        Date spentDate = Utils.parseDateStr(creditCardDetails[1]);
        double amount = Double.valueOf(creditCardDetails[2]);

        return new CreditCardTransaction(id, spentDate, amount);
    }

    public Map<String, List<CreditCardTransaction>> setupCreditCardTxnBank(List<String> transactions) {
        Map<String, List<CreditCardTransaction>> creditCardTxnBank = new HashMap<>();
        for (String trans : transactions) {
            try {
                CreditCardTransaction ccTxn = parseTxn(trans);

                if (!creditCardTxnBank.containsKey(ccTxn.getId())) {
                    creditCardTxnBank.put(ccTxn.getId(), new ArrayList(Arrays.asList(ccTxn)));
                } else {
                    creditCardTxnBank.get(ccTxn.getId()).add(ccTxn);
                }

            } catch (RuntimeException ex) {
                System.err.println("Invalid Transaction: " + trans);
            }

        }

        return creditCardTxnBank;

    }

    public boolean isFraudulent(List<String> transactions, String creditCardHash, double limit) {
        Map<String, List<CreditCardTransaction>> creditCardTxnBank = setupCreditCardTxnBank(transactions);
        List<CreditCardTransaction> creditCardTransactions = creditCardTxnBank.get(creditCardHash);
        List<CreditCardTransaction> cloned = new ArrayList(creditCardTransactions);

        for (CreditCardTransaction creditCardTransaction : creditCardTransactions) {
            Date start = creditCardTransaction.getDate();
            Date finish = Utils.timeAfter24HourWindow(start);

            List<CreditCardTransaction> result = cloned.stream()
                    .filter(cctxn -> Utils.isInTimeWindowInclusive(start, finish,cctxn.getDate()))
                    .collect(Collectors.toList());

            Double spendTotal = result.stream()
                    .map(txn -> txn.getAmount())
                    .collect(Collectors.summingDouble(Double::doubleValue));

            if (spendTotal > limit) {
                return true;
            }
        }

        return false;
    }

}
