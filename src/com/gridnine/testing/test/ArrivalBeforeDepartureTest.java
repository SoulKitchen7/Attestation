package com.gridnine.testing.test;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.service.impl.ArrivalBeforeDeparture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class ArrivalBeforeDepartureTest {

    @Test
    void shouldFilterFlightsWhenArrivalBeforeDeparture() {
        LocalDateTime dep1 = LocalDateTime.now().plusDays(3);
        LocalDateTime arr1 = dep1.minusHours(2);
        Segment segment1 = new Segment(dep1, arr1);

        LocalDateTime dep2 = LocalDateTime.now().plusDays(3);
        LocalDateTime arr2 = dep2.plusHours(2);
        Segment segment2 = new Segment(dep2, arr2);

        Flight flight1 = new Flight(List.of(segment1));
        Flight flight2 = new Flight(List.of(segment2));
        List<Flight> flightList = Arrays.asList(flight1, flight2);

        ArrivalBeforeDeparture arrivalBeforeDeparture = new ArrivalBeforeDeparture();

        List<Flight> expected = Arrays.asList(flight1);
        List<Flight> actual = arrivalBeforeDeparture.flightFilter(flightList);

        Assertions.assertEquals(expected, actual);
    }
}
