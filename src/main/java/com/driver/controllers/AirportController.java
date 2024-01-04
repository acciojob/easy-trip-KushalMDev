package com.driver.controllers;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import com.driver.service.ServiceClass;

@RestController
public class AirportController {
	ServiceClass serviceclass = new ServiceClass();

	@PostMapping("/add_airport")
	public String addAirport(@RequestBody Airport airport) {

		// Simply add airport details to your database
		// Return a String message "SUCCESS"
		serviceclass.addAirport(airport);
		return "SUCCESS";
	}

	@GetMapping("/get-largest-aiport")
	public String getLargestAirportName() {

		// Largest airport is in terms of terminals. 3 terminal airport is larger than 2
		// terminal airport
		// Incase of a tie return the Lexicographically smallest airportName

		return serviceclass.getLargestAirportName();
	}

	@GetMapping("/get-shortest-time-travel-between-cities")
	public double getShortestDurationOfPossibleBetweenTwoCities(@RequestParam("fromCity") City fromCity,
			@RequestParam("toCity") City toCity) {

		// Find the duration by finding the shortest flight that connects these 2 cities
		// directly
		// If there is no direct flight between 2 cities return -1.

		return serviceclass.getShortestDurationOfPossibleBetweenTwoCities(fromCity, toCity);
	}

	@GetMapping("/get-number-of-people-on-airport-on/{date}")
	public int getNumberOfPeopleOn(@PathVariable("date") Date date, @RequestParam("airportName") String airportName) {

		// Calculate the total number of people who have flights on that day on a
		// particular airport
		// This includes both the people who have come for a flight and who have landed
		// on an airport after their flight

		return serviceclass.getNumberOfPeopleOn(date, airportName);
	}

	@GetMapping("/calculate-fare")
	public int calculateFlightFare(@RequestParam("flightId") Integer flightId) {

		// Calculation of flight prices is a function of number of people who have
		// booked the flight already.
		// Price for any flight will be : 3000 + noOfPeopleWhoHaveAlreadyBooked*50
		// Suppose if 2 people have booked the flight already : the price of flight for
		// the third person will be 3000 + 2*50 = 3100
		// This will not include the current person who is trying to book, he might also
		// be just checking price

		return serviceclass.calculateFlightFare(flightId);

	}

	@PostMapping("/book-a-ticket")
	public String bookATicket(@RequestParam("flightId") Integer flightId,
			@RequestParam("passengerId") Integer passengerId) {

		// If the numberOfPassengers who have booked the flight is greater than :
		// maxCapacity, in that case :
		// return a String "FAILURE"
		// Also if the passenger has already booked a flight then also return "FAILURE".
		// else if you are able to book a ticket then return "SUCCESS"
		int result = serviceclass.bookATicket(flightId, passengerId);
		return result == 1 ? "SUCCESS" : "FAILURE";
	}

	@PutMapping("/cancel-a-ticket")
	public String cancelATicket(@RequestParam("flightId") Integer flightId,
			@RequestParam("passengerId") Integer passengerId) {

		// If the passenger has not booked a ticket for that flight or the flightId is
		// invalid or in any other failure case
		// then return a "FAILURE" message
		// Otherwise return a "SUCCESS" message
		// and also cancel the ticket that passenger had booked earlier on the given
		// flightId
		int result = serviceclass.cancelATicket(flightId, passengerId);
		return result == 1 ? "SUCCESS" : "FAILURE";
	}

	@GetMapping("/get-count-of-bookings-done-by-a-passenger/{passengerId}")
	public int countOfBookingsDoneByPassengerAllCombined(@PathVariable("passengerId") Integer passengerId) {

		// Tell the count of flight bookings done by a passenger: This will tell the
		// total count of flight bookings done by a passenger :
		return serviceclass.countOfBookingsDoneByPassengerAllCombined(passengerId);
	}

	@PostMapping("/add-flight")
	public String addFlight(@RequestBody Flight flight) {

		// Return a "SUCCESS" message string after adding a flight.
		serviceclass.addFlight(flight);
		return "SUCCESS";
	}

	@GetMapping("/get-aiportName-from-flight-takeoff/{flightId}")
	public String getAirportNameFromFlightId(@PathVariable("flightId") Integer flightId) {

		// We need to get the starting airportName from where the flight will be taking
		// off (Hint think of City variable if that can be of some use)
		// return null incase the flightId is invalid or you are not able to find the
		// airportName

		return serviceclass.getAirportNameFromFlightId(flightId);
	}

	@GetMapping("/calculate-revenue-collected/{flightId}")
	public int calculateRevenueOfAFlight(@PathVariable("flightId") Integer flightId) {

		// Calculate the total revenue that a flight could have
		// That is of all the passengers that have booked a flight till now and then
		// calculate the revenue
		// Revenue will also decrease if some passenger cancels the flight

		return serviceclass.calculateRevenueOfAFlight(flightId);
	}

	@PostMapping("/add-passenger")
	public String addPassenger(@RequestBody Passenger passenger) {

		// Add a passenger to the database
		// And return a "SUCCESS" message if the passenger has been added successfully.
		serviceclass.addPassenger(passenger);
		return "SUCCESS";
	}

}
