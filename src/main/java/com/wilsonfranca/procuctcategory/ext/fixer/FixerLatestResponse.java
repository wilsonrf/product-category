package com.wilsonfranca.procuctcategory.ext.fixer;

import java.util.Map;

/**
 * Created by wilson.franca on 27/02/18.
 *
 * {
 *   "success": true,
 *   "timestamp": 1449877801,
 *   "base": "USD",
 *   "rates": {
 *     "ARS": 9.750101
 *    }
 * }
 *
 *
 *
 *
 */
public class FixerLatestResponse {

    private boolean success;
    private Long timestamp;
    private Map<String, Double> rates;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
