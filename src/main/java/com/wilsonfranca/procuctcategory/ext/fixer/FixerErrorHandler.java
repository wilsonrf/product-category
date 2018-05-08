package com.wilsonfranca.procuctcategory.ext.fixer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wilsonfranca.procuctcategory.currencyconverter.exception.ClientException;
import com.wilsonfranca.procuctcategory.currencyconverter.exception.NotFoundException;
import com.wilsonfranca.procuctcategory.currencyconverter.exception.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * Created by wilson on 08/05/18.
 */
public class FixerErrorHandler implements ResponseErrorHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {

        HttpStatus.Series series = response.getStatusCode().series();

        return series.equals(HttpStatus.Series.SERVER_ERROR) || series.equals(HttpStatus.Series.CLIENT_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

        HttpStatus httpStatus = response.getStatusCode();

        switch (httpStatus) {
            case BAD_REQUEST:
                handleBadRequest(response);
                break;
            case UNAUTHORIZED:
            case FORBIDDEN:
            case TOO_MANY_REQUESTS:
                handleUnauthorized(response);
                break;
            case NOT_FOUND:
                handleNotFound(response);
                break;
            default:
                handleDefaultClient(response);
        }

    }

    private void handleDefaultClient(ClientHttpResponse response) {
        logger.warn("Client error [{}]", response);
        throw new ClientException();
    }

    private void handleNotFound(ClientHttpResponse response) {
        logger.warn("Client error [{}]", response);
        throw new NotFoundException();
    }

    private void handleUnauthorized(ClientHttpResponse response) throws IOException {
        logger.warn("Client error [{}]", response.getStatusText());
        throw new UnauthorizedException();
    }

    private void handleBadRequest(ClientHttpResponse response) {

        ObjectMapper objectMapper = new ObjectMapper();

        FixerError error = null;

        try {

            logger.warn("Client error [{}] [{}] [{}]", response.getStatusText(), response.getStatusCode(), response.getBody());

            error = objectMapper.readValue(response.getBody(), FixerError.class);

            logger.info("Fixer error [{}]", error);
            throw new ClientException();

        } catch (IOException e) {
            throw new ClientException();
        }



    }
}
