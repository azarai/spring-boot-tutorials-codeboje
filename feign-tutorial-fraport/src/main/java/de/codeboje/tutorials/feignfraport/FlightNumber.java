package de.codeboje.tutorials.feignfraport;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FlightNumber {

	private String airlineCode;
	private String trackNumber;
}
