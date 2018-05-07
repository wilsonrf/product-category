package com.wilsonfranca.procuctcategory.currencyconverter;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import java.time.Instant;

/**
 * Created by wilson.franca on 06/05/18.
 */
@Entity
public class ConverterRate {

    @Id
    private Long id;

    private String from;

    private String to;

    @CreatedDate
    private Instant dateCreated;

    private Instant rateDate;

    private Double amount;

    private Double rate;

    public ConverterRate(){}

    public ConverterRate(String from, String to, Double amount, Double rate, Long timestamp) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.rate = rate;
        this.rateDate = Instant.ofEpochSecond(timestamp);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Instant getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Instant getRateDate() {
        return rateDate;
    }

    public void setRateDate(Instant rateDate) {
        this.rateDate = rateDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

}
