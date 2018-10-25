package de.codeboje.tutorials.feignfraport;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@SpringBootApplication
@EnableFeignClients
public class FeignTutorialFraportApplication implements CommandLineRunner {

	@Autowired
	private FlightClient flightClient;

	@Bean
	AuthInterceptor authFeign() {
		return new AuthInterceptor();
	}

	class AuthInterceptor implements RequestInterceptor {

		@Override
		public void apply(RequestTemplate template) {
			template.header("Authorization", "Bearer 9eda682e0609d9fa76f93f0190a8c8de");

		}

	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(FeignTutorialFraportApplication.class).web(WebApplicationType.NONE)
				.run(args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<String> dest = Arrays.asList(args);

		for (FlightWrapper flightWrapper : flightClient.getDepartingFlights()) {
			Flight flight = flightWrapper.getFlight();

			if (dest.contains(flight.getArrivalAirport())) {
				System.out.println(String.format("%s\tto\t%s\ton\t%s\twith\t%s", flight.getDepartureAirport(),
						flight.getArrivalAirport(), flight.getDeparture().getScheduled(), flight.getOperatingAirline().getName()));
			}
		}

	}
}
