package com.wilsonfranca.procuctcategory.product.offer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by wilson on 07/05/18.
 */
public class OfferPriceSerializer extends JsonSerializer<BigDecimal> {

    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException, JsonProcessingException {

        DecimalFormat euros = new DecimalFormat( "EUR 0.00" );
        String formated = euros.format(value.doubleValue());
        gen.writeString(formated);

    }
}
