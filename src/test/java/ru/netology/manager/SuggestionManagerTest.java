package ru.netology.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.Suggestion;
import ru.netology.repository.SuggestionRepository;

@ExtendWith(MockitoExtension.class)
class SuggestionManagerTest {

    @Mock
    private SuggestionRepository repository;
    @InjectMocks
    private SuggestionManager manager;

    private final Suggestion suggestion1 = new Suggestion(1, 1, "aaa", "bbb", 10);
    private final Suggestion suggestion2 = new Suggestion(2, 10, "ccc", "ddd", 20);
    private final Suggestion suggestion3 = new Suggestion(3, 3, "aaa", "bbb", 15);
    private final Suggestion suggestion4 = new Suggestion(4, 4, "aaa", "eee", 100);
    private final Suggestion suggestion5 = new Suggestion(5, 5, "ccc", "ddd", 25);

    @Test
    public void shouldFindAllWithRightOrderInRepository() {
        Mockito.doReturn(new Suggestion[]{suggestion1, suggestion2, suggestion3, suggestion4, suggestion5})
                .when(repository).findAll();
        Suggestion[] all = manager.findAll("aaa", "bbb");
        Assertions.assertArrayEquals(new Suggestion[]{suggestion1, suggestion3}, all);
    }

    @Test
    public void shouldFindAllWithWrongOrderInRepository() {
        Mockito.doReturn(new Suggestion[]{suggestion1, suggestion2, suggestion3, suggestion4, suggestion5})
                .when(repository).findAll();
        Suggestion[] all = manager.findAll("ccc", "ddd");
        Assertions.assertArrayEquals(new Suggestion[]{suggestion5, suggestion2}, all);
    }

    @Test
    public void shouldFindAllWithEmptyResult() {
        Mockito.doReturn(new Suggestion[]{suggestion1, suggestion2, suggestion3, suggestion4, suggestion5})
                .when(repository).findAll();
        Suggestion[] all = manager.findAll("asd", "qwe");
        Assertions.assertArrayEquals(new Suggestion[]{}, all);
    }

    @Test
    public void shouldFindAllWithOneResult() {
        Mockito.doReturn(new Suggestion[]{suggestion1, suggestion2, suggestion3, suggestion4, suggestion5})
                .when(repository).findAll();
        Suggestion[] all = manager.findAll("aaa", "eee");
        Assertions.assertArrayEquals(new Suggestion[]{suggestion4}, all);
    }

    @Test
    public void shouldFindAllWithNullDeparture(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> manager.findAll(null, "bbb"));
    }

    @Test
    public void shouldFindAllWithNullArrival(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> manager.findAll("aaa", null));
    }

    @Test
    public void shouldFindAllWithNullDepartureAndArrival(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> manager.findAll(null, null));
    }
}