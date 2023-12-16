package com.gridnine.testing.test;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.service.impl.DepartureBeforeTheCurrentTime;
import com.gridnine.testing.service.impl.GroundTimeExceedsTwoHours;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class GroundTimeExceedsTwoHoursTest {

    @Test
    void shouldFilterFlightsWhenGroundTimeExceedsTwoHours() {
        LocalDateTime dep1 = LocalDateTime.now().plusDays(3);
        LocalDateTime arr1 = dep1.plusHours(2);
        Segment segment1 = new Segment(dep1, arr1);

        LocalDateTime dep2 = arr1.plusHours(4);
        LocalDateTime arr2 = dep2.plusHours(2);
        Segment segment2 = new Segment(dep2, arr2);

        LocalDateTime dep3 = LocalDateTime.now().plusDays(3);
        LocalDateTime arr3 = dep1.plusHours(2);
        Segment segment3 = new Segment(dep3, arr3);

        Flight flight1 = new Flight(List.of(segment1, segment2));
        Flight flight2 = new Flight(List.of(segment3));
        List<Flight> flightList = Arrays.asList(flight1, flight2);

        GroundTimeExceedsTwoHours groundTimeExceedsTwoHours = new GroundTimeExceedsTwoHours();

        List<Flight> expected = Arrays.asList(flight1);
        List<Flight> actual = groundTimeExceedsTwoHours.flightFilter(flightList);

        Assertions.assertEquals(expected, actual);
    }
}
