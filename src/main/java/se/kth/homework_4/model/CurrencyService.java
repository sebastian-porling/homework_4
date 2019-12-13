package se.kth.homework_4.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.kth.homework_4.integration.CurrencyRepository;

import java.util.List;

@Service
@Transactional
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public List<Currency> getAll(){
        return currencyRepository.findAll();
    }

    public Currency get(String code){
        return currencyRepository.findByCode(code);
    }

    public void saveCurrencies(String[] currencyCodes){
        for(String currencyCode : currencyCodes){
            currencyRepository.save(new Currency(currencyCode));
        }
    }
}
