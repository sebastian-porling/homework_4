package se.kth.homework_4.integration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import se.kth.homework_4.model.Currency;
import se.kth.homework_4.model.Rate;

public interface RateRepository extends JpaRepository<Rate, Integer> {
    Rate findByFromCurrencyAndToCurrency(@Param("from_currency") Currency fromCurrency,@Param("to_currency") Currency toCurrency);
}
