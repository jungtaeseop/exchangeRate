package com.exchange.exchangerate.dto;

import com.exchange.exchangerate.data.CountryCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ExchangeRateDto {
    private CountryCode countryCode;
    private double exchangeRate;
    private BigDecimal receptionAmount;
    private int remittanceAmount;
    boolean status = false;
}
