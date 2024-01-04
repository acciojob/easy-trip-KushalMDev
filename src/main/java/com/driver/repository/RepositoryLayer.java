package com.driver.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;

@Repository
public class RepositoryLayer {

	HashMap<Integer, Passenger> passengerMapping = new HashMap<>();
	HashMap<Integer, Flight> flightMapping = new HashMap<>();
	HashMap<String, Airport> airportMapping = new HashMap<>();
	HashMap<String, City> airportCityMapping = new HashMap<>();
	HashMap<Integer, ArrayList<Integer>> passengertickets = new HashMap<>();
	HashMap<Integer, Integer> seatingcapacity = new HashMap<>();

	public void addAirport(Airport airport) {
		// TODO Auto-generated method stub
		airportMapping.put(airport.getAirportName(), airport);
		airportCityMapping.put(airport.getAirportName(), airport.getCity());
	}

	public String getLargestAirportName() {
		// TODO Auto-generated method stub
		String airport = null;
		int max = Integer.MIN_VALUE;
		for (String key : airportMapping.keySet()) {
			Airport air = airportMapping.get(key);
			int numberOfterminals = air.getNoOfTerminals();
			if (numberOfterminals > max) {
				max = numberOfterminals;
				airport = key;
			} else if (numberOfterminals == max) {
				if (airport.compareTo(key) > 0) {
					airport = key;
				}
			}
		}
		return airport;
	}

	public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity, City toCity) {
		// TODO Auto-generated method stub
		double duration = Integer.MAX_VALUE;
		for (int fid : flightMapping.keySet()) {
			Flight flight = flightMapping.get(fid);
			City from = flight.getFromCity();
			City to = flight.getToCity();
			if (fromCity.equals(from) && toCity.equals(to)) {
				double dur = flight.getDuration();
				if (duration > dur) {
					duration = dur;
				}
			}
		}

		return duration == Integer.MAX_VALUE ? -1 : duration;
	}

	public void addFlight(Flight flight) {
		// TODO Auto-generated method stub
		if (!flightMapping.containsKey(flight.getFlightId())) {
			flightMapping.put(flight.getFlightId(), flight);
			seatingcapacity.put(flight.getFlightId(), 0);
		}
	}

	public void addPassenger(Passenger passenger) {
		// TODO Auto-generated method stub
		if (!passengerMapping.containsKey(passenger.getPassengerId())) {
			passengerMapping.put(passenger.getPassengerId(), passenger);
		}
	}

	public int getNumberOfPeopleOn(Date date, String airportName) {
		// TODO Auto-generated method stub
		int cnt = 0;
		if (!airportCityMapping.containsKey(airportName)) {
			return 0;
		}
		City mainCity = null;
		for (String str : airportCityMapping.keySet()) {
			if (str.equals(airportName)) {
				mainCity = airportCityMapping.get(str);
			}
		}
		if (!mainCity.equals(null)) {
			for (int fid : flightMapping.keySet()) {
				Flight flight = flightMapping.get(fid);
				City fromCity = flight.getFromCity();
				City toCity = flight.getToCity();
				Date fdate = flight.getFlightDate();
				if (!seatingcapacity.containsKey(fid)) {
					seatingcapacity.put(fid, 0);
				}
				if (fromCity.equals(mainCity) || toCity.equals(mainCity)) {
					if (fdate.compareTo(date) == 0) {
						cnt += seatingcapacity.get(fid);
					}
				}
			}

		}
		return cnt;

	}

	public int bookATicket(Integer flightId, Integer passengerId) {
		// TODO Auto-generated method stub
		int ans = 0;
		if (passengertickets.containsKey(passengerId)) {
			ArrayList<Integer> list = passengertickets.getOrDefault(passengerId, new ArrayList<>());
			for (int id : list) {
				if (id == flightId) {
					return 0;
				}
			}
		}
		if (seatingcapacity.containsKey(flightId)) {
			int currentCount = seatingcapacity.get(flightId);
			Flight flight = flightMapping.get(flightId);
			if (currentCount == flight.getMaxCapacity()) {
				return 0;
			}
		}

		if (seatingcapacity.containsKey(flightId)) {
			int currentCount = seatingcapacity.get(flightId);
			currentCount++;
			seatingcapacity.put(flightId, currentCount);
			if (!passengertickets.containsKey(passengerId)) {
				ArrayList<Integer> list = new ArrayList<>();
				list.add(flightId);
				passengertickets.put(passengerId, list);
				return 1;
			} else {
				ArrayList<Integer> list = passengertickets.get(passengerId);
				list.add(flightId);
				return 1;
			}
		}

		return ans;
	}

	public int calculateFlightFare(Integer flightId) {
		// TODO Auto-generated method stub
		int price = 0;
		if (!flightMapping.containsKey(flightId)) {
			return 0;
		}
		int noOfPeopleWhoHaveAlreadyBooked = seatingcapacity.get(flightId);
		price = 3000 + noOfPeopleWhoHaveAlreadyBooked * 50;
		return price;
	}

	public int cancelATicket(Integer flightId, Integer passengerId) {
		// TODO Auto-generated method stub
		if (!flightMapping.containsKey(flightId) || !passengerMapping.containsKey(passengerId)) {
			return 0;
		}
		if (!passengertickets.containsKey(passengerId)) {
			passengertickets.put(passengerId, new ArrayList<>());
		}
		ArrayList<Integer> list = passengertickets.get(passengerId);
		int ind = -1;
		boolean flag = false;
		for (int i = 0; i < list.size(); i++) {
			int fid = list.get(i);
			if (fid == flightId) {
				flag = true;
				ind = i;
				break;
			}
		}
		if (flag) {
			list.remove(ind);
			int count = seatingcapacity.get(flightId);
			count--;
			seatingcapacity.put(flightId, count);
			return 1;
		}

		return 0;
	}

	public int countOfBookingsDoneByPassengerAllCombined(Integer passengerId) {
		// TODO Auto-generated method stub
		if (!passengertickets.containsKey(passengerId)) {
			passengertickets.put(passengerId, new ArrayList<>());
		}
		return passengertickets.get(passengerId).size();
	}

	public String getAirportNameFromFlightId(Integer flightId) {
		// TODO Auto-generated method stub
		if (!flightMapping.containsKey(flightId)) {
			return null;
		}
		Flight flight = flightMapping.get(flightId);
		return flight.getFromCity() + "";

		// return null;
	}

	public int calculateRevenueOfAFlight(Integer flightId) {
		// TODO Auto-generated method stub
		int revenue = 0;
		int peoplebooked = seatingcapacity.get(flightId);
		if (peoplebooked == 0)
			return 0;
		for (int i = 0; i < peoplebooked; i++) {
			revenue += (3000 + (i * 50));
		}

		return revenue;
	}
}
