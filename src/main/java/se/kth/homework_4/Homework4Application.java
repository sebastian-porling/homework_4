package se.kth.homework_4;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.kth.homework_4.model.CurrencyService;
import se.kth.homework_4.model.Rate;
import se.kth.homework_4.model.RateService;

import javax.swing.text.html.HTMLDocument;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@SpringBootApplication
public class Homework4Application implements CommandLineRunner {

	private static CurrencyService currencyService;

	private static RateService rateService;

	@Autowired
	public Homework4Application(CurrencyService currencyService, RateService rateService) {
		Homework4Application.currencyService = currencyService;
		Homework4Application.rateService = rateService;
	}

	private static final String[] currencyCodes =
			{"CAD", "HKD", "ISK", "PHP", "DKK", "HUF",
					"CZK", "AUD", "RON", "SEK", "IDR", "INR",
					"BRL", "RUB", "HRK", "JPY", "THB", "CHF",
					"SGD", "PLN", "BGN", "TRY", "CNY", "NOK", "NZD",
					"ZAR", "USD", "MXN", "ILS", "GBP", "KRW", "MYR", "EUR"};

	private static final String API_URL = "https://api.exchangeratesapi.io/latest";
	private static final String API_BASE_PARAMETER = "?base=";

	public static void main(String[] args) {
		SpringApplication.run(Homework4Application.class, args);
	}

	@Override
	public void run(String[] args){
		initCurrencyTable();
		initRateTable();
		System.out.println("Database initialized...");
	}


	private static void initCurrencyTable(){
		currencyService.saveCurrencies(currencyCodes);
	}

	private static void initRateTable(){
		ExecutorService executor = Executors.newFixedThreadPool(currencyCodes.length);
		for(String currencyCode : currencyCodes){
			executor.execute(new Runnable() {
				@Override
				public void run(){
					try {
						setRates(currencyCode);
					}catch (Exception exception){
						System.err.println("Couldn't get the rates for: " + currencyCode);
					}
				}
			});
		}
	}

	private static void setRates(String currencyCode) throws Exception{
		HttpResponse<String> response = getAPIResponse(currencyCode);
		JSONObject jsonResponse = new JSONObject(response.body());
		JSONObject jsonRates = jsonResponse.getJSONObject("rates");
		Iterator iterator = jsonRates.keys();
		while (iterator.hasNext()){
			Object key = iterator.next();
			rateService.storeRate(
					new Rate(currencyService.get(currencyCode),
							currencyService.get(key.toString()),
							jsonRates.getDouble(key.toString())));
		}
	}

	private static HttpResponse getAPIResponse(String currencyCode) throws Exception{
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(API_URL + API_BASE_PARAMETER + currencyCode))
				.build();
		return client.send(request, HttpResponse.BodyHandlers.ofString());
	}
}
