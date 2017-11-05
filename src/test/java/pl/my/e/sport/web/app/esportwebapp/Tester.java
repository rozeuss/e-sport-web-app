package pl.my.e.sport.web.app.esportwebapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tester {

    @org.junit.Test
    public void countNumberOfPhasesForMatch() {
        int numberOfParticipants = 32;
        List<Integer> phases = new ArrayList<>();
        int j = 0;
        while (numberOfParticipants > 0) {
            numberOfParticipants /= 2;
            phases.add(j);
            j++;
        }

        phases.forEach(System.out::println);
    }

    @org.junit.Test
    public void preparePhasesForTournament() {
        Integer numberOfParticipants = 16;
        Map<Integer, Integer> phases = new HashMap<>();
        int phase = (int) (Math.log(numberOfParticipants) / Math.log(2));
        while (numberOfParticipants > 0) {
            int numberOfMatches = numberOfParticipants /= 2;
            phases.put(phase, numberOfMatches);
            phase--;
        }
        phases.put(0, 1);
        phases.forEach((integer, integer2) -> System.out.println(integer + ": " + integer2));
        // return phases;
    }
}
