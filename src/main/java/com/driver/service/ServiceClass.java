package com.driver.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import com.driver.repository.RepositoryLayer;

@Service
public class ServiceClass {

	RepositoryLayer repolayer = new RepositoryLayer();

	public void addAirport(Airport airport) {
		// TODO Auto-generated method stub
		repolayer.addAirport(airport);
	}

	public String getLargestAirportName() {
		// TODO Auto-generated method stub
		return repolayer.getLargestAirportName();
	}

	public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity, City toCity) {
		// TODO Auto-generated method stub
		return repolayer.getShortestDurationOfPossibleBetweenTwoCities(fromCity, toCity);
	}

	public void addFlight(Flight flight) {
		// TODO Auto-generated method stub
		repolayer.addFlight(flight);
	}

	public void addPassenger(Passenger passenger) {
		// TODO Auto-generated method stub
		repolayer.addPassenger(passenger);
	}

	public int getNumberOfPeopleOn(Date date, String airportName) {
		// TODO Auto-generated method stub
		return repolayer.getNumberOfPeopleOn(date, airportName);
	}

	public int bookATicket(Integer flightId, Integer passengerId) {
		// TODO Auto-generated method stub
		return repolayer.bookATicket(flightId, passengerId);
	}

	public int calculateFlightFare(Integer flightId) {
		// TODO Auto-generated method stub
		return repolayer.calculateFlightFare(flightId);
	}

	public int cancelATicket(Integer flightId, Integer passengerId) {
		// TODO Auto-generated method stub
		return repolayer.cancelATicket(flightId, passengerId);
	}

	public int countOfBookingsDoneByPassengerAllCombined(Integer passengerId) {
		// TODO Auto-generated method stub
		return repolayer.countOfBookingsDoneByPassengerAllCombined(passengerId);
	}

	public String getAirportNameFromFlightId(Integer flightId) {
		// TODO Auto-generated method stub
		return repolayer.getAirportNameFromFlightId(flightId);
	}

	public int calculateRevenueOfAFlight(Integer flightId) {
		// TODO Auto-generated method stub
		return repolayer.calculateRevenueOfAFlight(flightId);
	}

}
