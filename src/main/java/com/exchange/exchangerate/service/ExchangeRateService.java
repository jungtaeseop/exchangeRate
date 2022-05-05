package com.exchange.exchangerate.service;

import com.exchange.exchangerate.configure.ExchangeRateProperties;
import com.exchange.exchangerate.data.CountryCode;
import com.sun.jdi.InternalException;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExchangeRateService {

    private final ExchangeRateProperties exchangeRateProperties;

    public double getExchangeRate(CountryCode countryCode) throws InternalException {
        double exchangeRate = 0.0;

        try {
            String url = exchangeRateProperties.getUrl();
            String key = exchangeRateProperties.getApiKey();

            String apiUrl = url  + "?apikey=" + key + "&currencies=" + countryCode
                    + "&source=USD";


            OkHttpClient client = new OkHttpClient();
            Request.Builder builder = new Request.Builder().url(apiUrl).get();
            //builder.addHeader("apikey", "EMyGNCLjrlrBkQJTRHbjrF1406oA5T4Z");
            Request request = builder.build();
            Response response = client.newCall(request).execute();


            if (response.isSuccessful()) {
                ResponseBody body = response.body();

                if (body != null) {
                    String currentCountryCode = "USD"+countryCode;
                    JSONObject jsonObject = new JSONObject(body.string());
                    exchangeRate = jsonObject.getJSONObject("quotes").getDouble(currentCountryCode);

                    if (exchangeRate == 0.0) {
                        throw new InternalException("환율 정보를 가져오지 못했습니다.");
                    }
                } else {
                    throw new InternalException("환율 정보를 가져오지 못했습니다.");
                }
            } else {
                throw new InternalException("환율 정보를 가져오지 못했습니다.");
            }
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }

        return exchangeRate;
    }

    public BigDecimal getReceptionAmount(double exchangeRate, int remittanceAmount) throws IllegalArgumentException {

        if(exchangeRate == 0.0){
            throw new IllegalArgumentException("환율값이 잘못 되었습니다.");
        }else if(remittanceAmount <= 0 || remittanceAmount > 10000){
            throw new IllegalArgumentException("송금액이 바르지 않습니다.");
        }

        BigDecimal exchangeRateBig = new BigDecimal(String.valueOf(exchangeRate));
        BigDecimal remittanceAmountBig = new BigDecimal(String.valueOf(remittanceAmount));

        return exchangeRateBig.multiply(remittanceAmountBig);
    }
}
