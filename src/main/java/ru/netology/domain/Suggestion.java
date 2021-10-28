package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Suggestion implements Comparable<Suggestion> {
    private long id;
    private int price;
    private String departure;
    private String arrival;
    private int timeInTravel;

    @Override
    public int compareTo(Suggestion o) {
        if (price < o.getPrice()) {
            return -1;
        } else if (price == o.getPrice()) {
            return 0;
        }
        return 1;
    }

    public boolean matchAirports(String departure, String arrival){
        return departure.equals(this.departure) && arrival.equals(this.arrival);
    }
}
