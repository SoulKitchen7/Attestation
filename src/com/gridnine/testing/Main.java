package com.gridnine.testing;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.FlightBuilder;
import com.gridnine.testing.service.impl.ArrivalBeforeDeparture;
import com.gridnine.testing.service.impl.DepartureBeforeTheCurrentTime;
import com.gridnine.testing.service.impl.GroundTimeExceedsTwoHours;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Flight> flightList = FlightBuilder.createFlights();

        System.out.println("------------------------------------");
        System.out.println("Список всех наборов перелетов");
        flightList.forEach(System.out::println);

        System.out.println("------------------------------------");
        System.out.println("Список всех наборов перелетов, где вылет до текущего времени");
        DepartureBeforeTheCurrentTime departureBeforeTheCurrentTime = new DepartureBeforeTheCurrentTime();
        List<Flight> flightsWhenDepartureBeforeTheCurrentTime = departureBeforeTheCurrentTime.flightFilter(flightList);
        flightsWhenDepartureBeforeTheCurrentTime.forEach(System.out::println);

        System.out.println("------------------------------------");
        System.out.println("Список всех наборов перелетов, где сегменты с датой прилёта раньше даты вылета");
        ArrivalBeforeDeparture arrivalBeforeDeparture = new ArrivalBeforeDeparture();
        List<Flight> flightsWhenArrivalBeforeDeparture = arrivalBeforeDeparture.flightFilter(flightList);
        flightsWhenArrivalBeforeDeparture.forEach(System.out::println);

        System.out.println("------------------------------------");
        System.out.println("Список всех наборов перелетов, где время, проведённое на земле превышает два часа");
        GroundTimeExceedsTwoHours groundTimeExceedsTwoHours = new GroundTimeExceedsTwoHours();
        List<Flight> flightsWhenGroundTimeExceedsTwoHours = groundTimeExceedsTwoHours.flightFilter(flightList);
        flightsWhenGroundTimeExceedsTwoHours.forEach(System.out::println);

    }
}