package Model.View;

import Model.Model.Seans;
import Model.Repertuar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class RepertuarViewTest {

    @Mock
    private Repertuar mockRepertuar;

    @Mock
    private Seans mockSeans1;

    @Mock
    private Seans mockSeans2;
    @InjectMocks
    private RepertuarView repertuarView;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        repertuarView = new RepertuarView(mockRepertuar);
    }

    @Test
    void testWyswietlRepertuar_withSeanse() {
        // Mockowanie zwracanej listy seansów jako Vector<Seans>
        Vector<Seans> mockSeanse = new Vector<>(Arrays.asList(mockSeans1, mockSeans2));

        // Konfiguracja mocków
        when(mockRepertuar.getSeanse()).thenReturn(mockSeanse);
        when(mockSeans1.toString()).thenReturn("Seans 1: Film A (2025-01-14 18:00)");
        when(mockSeans2.toString()).thenReturn("Seans 2: Film B (2025-01-14 20:00)");

        // Przechwycenie wyjścia do konsoli
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Testowanie
        repertuarView.wyswietlRepertuar();

        // Przywrócenie oryginalnego wyjścia do konsoli
        System.setOut(originalOut);

        // Weryfikacja
        String output = outputStream.toString();
        assertTrue(output.contains("Repertuar:"));
        assertTrue(output.contains("Seans 1: Film A (2025-01-14 18:00)"));
        assertTrue(output.contains("Seans 2: Film B (2025-01-14 20:00)"));

        verify(mockRepertuar, times(1)).getSeanse();
    }


    @Test
    void testWyswietlRepertuar_withoutSeanse() {
        // Mockowanie pustej listy seansów jako Vector<Seans>
        when(mockRepertuar.getSeanse()).thenReturn(new Vector<>());

        // Przechwycenie wyjścia do konsoli
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Testowanie
        repertuarView.wyswietlRepertuar();

        // Przywrócenie oryginalnego wyjścia do konsoli
        System.setOut(originalOut);

        // Weryfikacja
        String output = outputStream.toString();
        assertTrue(output.contains("Brak dostepnych seansow w repertuarze."));

        // Sprawdzenie, czy metoda getSeanse() została wywołana
        verify(mockRepertuar, times(1)).getSeanse();
    }



    @Test
    void testWyswietlRepertuar_withInvalidSeanseData() {
        // Mockowanie zwracanej listy seansów jako Vector<Seans>
        Vector<Seans> mockSeanse = new Vector<>(Arrays.asList(mockSeans1, mockSeans2));

        // Konfiguracja mocków
        when(mockRepertuar.getSeanse()).thenReturn(mockSeanse);
        when(mockSeans1.toString()).thenReturn(null);  // Zwrócenie null
        when(mockSeans2.toString()).thenReturn("");   // Zwrócenie pustego ciągu

        // Przechwycenie wyjścia do konsoli
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Testowanie
        repertuarView.wyswietlRepertuar();

        // Przywrócenie oryginalnego wyjścia do konsoli
        System.setOut(originalOut);

        // Weryfikacja
        String output = outputStream.toString();
        assertTrue(output.contains("Brak poprawnych danych dla seansu."));

        // Nie weryfikujemy wywołań toString(), tylko getSeanse()
        verify(mockRepertuar, times(1)).getSeanse();
    }
}