package se.kth.homework_4.integration;

import org.springframework.data.jpa.repository.JpaRepository;
import se.kth.homework_4.model.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    Currency findByCode(String code);
}
