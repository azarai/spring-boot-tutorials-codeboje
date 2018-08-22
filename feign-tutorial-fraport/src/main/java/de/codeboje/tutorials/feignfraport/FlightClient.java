package de.codeboje.tutorials.feignfraport;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="FlightClient", url= "https://developer.fraport.de/api/flights/1.0")
public interface FlightClient {

    @RequestMapping(method = RequestMethod.GET, value = "/flight/FRA/departure")
    List<FlightWrapper> getDepartingFlights();
    
    
    @RequestMapping(method = RequestMethod.GET, value = "/flightDetails/{airlineCode}/{flightNumber}/")
    List<FlightWrapper> getFlightDetails(@PathVariable("airlineCode") String airlineCode, @PathVariable("flightNumber") Integer flightNumber);
    
}
