// Copyright 2015 Skiddoo Pty Ltd

package au.com.skiddoo.shopping;

import java.math.BigDecimal;

/**
 * @author jaiew
 */
public class Price {

    // Implement the price object;
    private BigDecimal amount;
    private String currency;

    public Price(BigDecimal amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Price() {
        amount = BigDecimal.ZERO;
        currency = "AUD";
    }

    public Price(BigDecimal amount) {

        this();
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void add(Price newPrice) {

        amount = amount.add(newPrice.getAmount());
    }

    @Override
    public String toString() {
        return "$" + amount + currency;
    }
}
