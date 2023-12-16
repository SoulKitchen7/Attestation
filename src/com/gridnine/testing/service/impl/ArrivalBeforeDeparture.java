package com.gridnine.testing.service.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.FlightFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArrivalBeforeDeparture implements FlightFilter {

    public ArrivalBeforeDeparture() {
    }

    @Override
    public List<Flight> flightFilter(List<Flight> flights) {

        List<Flight> filteredFlights = new ArrayList<>();
        flights.stream()
                .filter(flight -> {
                    boolean isCorrect = flight.getSegments().stream()
                            .anyMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate()));
                    if (isCorrect) {
                        filteredFlights.add(flight);
                    }
                    return false;
                }).collect(Collectors.toList());

        return filteredFlights;
    }
}
