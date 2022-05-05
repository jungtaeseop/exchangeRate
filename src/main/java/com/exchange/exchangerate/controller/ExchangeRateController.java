package com.exchange.exchangerate.controller;

import com.exchange.exchangerate.data.CountryCode;
import com.exchange.exchangerate.dto.ExchangeRateDto;
import com.exchange.exchangerate.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @GetMapping("/exchangerate")
    public String getExchangeForm(Model model){
        ExchangeRateDto exchangeRateDto = new ExchangeRateDto();
        model.addAttribute("exchangeRateDto",exchangeRateDto);
        return "exchangeRate";
    }

    @ResponseBody
    @GetMapping("/exchangerate-info/{countryCode}")
    public ExchangeRateDto getExchangeRate(@PathVariable CountryCode countryCode, Model model){
        ExchangeRateDto exchangeRateDto = new ExchangeRateDto();
        exchangeRateDto.setExchangeRate(exchangeRateService.getExchangeRate(countryCode));
        return exchangeRateDto;
    }


    @PostMapping("/exchangerate-calculation")
    public String getReceptionAmount(@RequestBody ExchangeRateDto exchangeRateDto,Model model){
        exchangeRateDto.setReceptionAmount(exchangeRateService.getReceptionAmount(exchangeRateDto.getExchangeRate(),exchangeRateDto.getRemittanceAmount()));
        exchangeRateDto.setStatus(true);
        model.addAttribute("exchangeRateDto",exchangeRateDto);
        return "/exchangeRate :: #resultDiv";
    }


}
