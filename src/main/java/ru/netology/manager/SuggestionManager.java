package ru.netology.manager;

import ru.netology.domain.Suggestion;
import ru.netology.repository.SuggestionRepository;

import java.util.Arrays;

public class SuggestionManager {

    private SuggestionRepository suggestionRepository;

    public SuggestionManager(SuggestionRepository suggestionRepository) {
        this.suggestionRepository = suggestionRepository;
    }

    public Suggestion[] findAll(String departure, String arrival) {
        if (departure == null || arrival == null) {
            throw new IllegalArgumentException("Departure or arrival is null");
        }
        Suggestion[] all = suggestionRepository.findAll();
        Suggestion[] result = new Suggestion[0];

        for (Suggestion suggestion : all) {
            if (suggestion.matchAirports(departure, arrival)) {
                Suggestion[] tmp = new Suggestion[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = suggestion;
                result = tmp;
            }
        }

        if (result.length == 0) {
            return result;
        }
        Arrays.sort(result);
        return result;
    }

}
