package com.gridnine.testing.service.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.service.FlightFilter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GroundTimeExceedsTwoHours implements FlightFilter {

    private final long GROUND_TIME = 2;

    public GroundTimeExceedsTwoHours() {

    }
    @Override
    public List<Flight> flightFilter(List<Flight> flights) {

        List<Flight> filteredFlights = new ArrayList<>();
        flights.stream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    if (segments.size() <= 1) {
                        return true;
                    }
                    long totalGroundTime = IntStream.range(0, segments.size() - 1)
                            .mapToLong(i -> Duration.between(segments.get(i).getArrivalDate(),
                                    segments.get(i + 1).getDepartureDate()).toHours())
                            .sum();
                    if (totalGroundTime > GROUND_TIME) {
                        return filteredFlights.add(flight);
                    }
                    return false;
                }).collect(Collectors.toList());

        return filteredFlights;
    }
}
