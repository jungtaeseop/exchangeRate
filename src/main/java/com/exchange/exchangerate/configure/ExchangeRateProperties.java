package com.exchange.exchangerate.configure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
@Getter
@Setter
public class ExchangeRateProperties {
    @Value("${property.exchange-rate-api-apikey}")
    public String apiKey;

    @Value("${property.exchange-rate-api-url}")
    public String url;

}
