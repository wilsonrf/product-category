package com.wilsonfranca.procuctcategory.currency;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by wilson.franca on 03/05/18.
 */
@Entity
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isoCode;

    private BigDecimal eurConversionFactor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public BigDecimal getEurConversionFactor() {
        return eurConversionFactor;
    }

    public void setEurConversionFactor(BigDecimal eurConversionFactor) {
        this.eurConversionFactor = eurConversionFactor;
    }
}
