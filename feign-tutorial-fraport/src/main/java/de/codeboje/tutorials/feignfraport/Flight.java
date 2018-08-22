package de.codeboje.tutorials.feignfraport;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Flight {

	private String departureAirport;
	private String arrivalAirport;
	private String originDate;
	
	private ScheduledTimes departure;
	
	private ScheduledTimes arrival;
	
	private Airline operatingAirline;
	
	private FlightNumber flightNumber;
}
