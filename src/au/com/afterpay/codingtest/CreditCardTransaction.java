package au.com.afterpay.codingtest;

import java.util.Date;

public class CreditCardTransaction {
    private String id;
    private Date date;
    private double amount;

    public CreditCardTransaction() {}

    public CreditCardTransaction(String id, Date date, double amount) {
        this.id = id;
        this.date = date;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
