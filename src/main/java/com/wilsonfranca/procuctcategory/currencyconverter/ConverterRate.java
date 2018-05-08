package com.wilsonfranca.procuctcategory.currencyconverter;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

/**
 * Created by wilson.franca on 06/05/18.
 */
@Entity
public class ConverterRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source;

    private String target;

    @CreatedDate
    private Instant dateCreated;

    private Instant rateDate;

    private Double amount;

    private Double rate;

    public ConverterRate(){}

    public ConverterRate(String source, String target, Double amount, Double rate, Long timestamp) {
        this.source = source;
        this.target = target;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
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
