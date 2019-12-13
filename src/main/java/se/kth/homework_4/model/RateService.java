package se.kth.homework_4.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.kth.homework_4.integration.RateRepository;

import java.util.List;

@Service
@Transactional
public class RateService {

    @Autowired
    private RateRepository rateRepository;

    public List<Rate> getAll(){
        return rateRepository.findAll();
    }

    public Rate getRateForConversion(Currency fromCurrency, Currency toCurrency){
        return rateRepository.findByFromCurrencyAndToCurrency(fromCurrency, toCurrency);
    }

    public void storeRate(Rate rate){
        rateRepository.save(rate);
    }

    public void incrementNumberOfUses(Rate rate){
        rate.incrementNumberOfUses();
        rateRepository.save(rate);
    }

}
