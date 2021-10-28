package ru.netology.repository;

import ru.netology.domain.Suggestion;
import ru.netology.exception.NotFoundException;

public class SuggestionRepository {

    private Suggestion[] items = new Suggestion[0];

    public void save(Suggestion item) {
        int length = items.length + 1;
        Suggestion[] tmp = new Suggestion[length];
        System.arraycopy(items, 0, tmp, 0, items.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = item;
        items = tmp;
    }

    public Suggestion[] findAll() {
        return items;
    }

    public Suggestion findById(int id) {
        for (Suggestion item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Suggestion with id " + id + " not found");
        }
        int length = items.length - 1;
        Suggestion[] tmp = new Suggestion[length];
        int index = 0;
        for (Suggestion item : items) {
            if (item.getId() != id) {
                tmp[index] = item;
                index++;
            }
        }
        items = tmp;
    }
}
