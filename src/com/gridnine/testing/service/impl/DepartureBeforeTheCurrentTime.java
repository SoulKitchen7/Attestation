package com.gridnine.testing.service.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.service.FlightFilter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DepartureBeforeTheCurrentTime implements FlightFilter {

    public DepartureBeforeTheCurrentTime (){

    }
    @Override
    public List<Flight> flightFilter(List<Flight> flights) {
        LocalDateTime currentTime = LocalDateTime.now();
        List<Flight> filteredFlights = new ArrayList<>();
        flights.stream()
                .filter(flight -> {
                    boolean isCorrect = flight.getSegments().stream()
                            .anyMatch(segment -> segment.getDepartureDate().isBefore(currentTime));
                    if (isCorrect) {
                        filteredFlights.add(flight);
                    }
                    return false;
                }).collect(Collectors.toList());

        return filteredFlights;
    }
}
