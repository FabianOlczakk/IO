package Model.View;

import Model.Raport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RaportViewTest {

    @Mock
    private Raport mockRaport;

    private RaportView raportView;

    @BeforeEach
    void setUp() {
        // Inicjalizacja adnotacji Mockito
        MockitoAnnotations.openMocks(this);

        // Tworzymy RaportView, któremu wstrzykujemy mocka Raport
        raportView = new RaportView(mockRaport);
    }

    @Test
    void testWyswietlRaport_zPoprawnymRaportem() {
        // Ustawiamy zwracane dane z mockRaport
        when(mockRaport.getId()).thenReturn(123);
        when(mockRaport.getTypRaportu()).thenReturn("Sprzedaż");
        when(mockRaport.getDane()).thenReturn("Dane testowe raportu");

        // Przechwytujemy strumień System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            // Wywołanie metody
            raportView.wyswietlRaport(mockRaport);

            // Odczytujemy to, co zostało wypisane na konsolę
            String consoleOutput = outContent.toString();

            // Weryfikacja, że metody Raport zostały wywołane
            verify(mockRaport).getId();
            verify(mockRaport).getTypRaportu();
            verify(mockRaport).getDane();

            // Dodajemy asercje sprawdzające, czy oczekiwane fragmenty się pojawiły
            assertTrue(consoleOutput.contains("==== Wyswietlanie raportu ===="));
            assertTrue(consoleOutput.contains("Raport ID: 123"));
            assertTrue(consoleOutput.contains("Typ raportu: Sprzedaż"));
            assertTrue(consoleOutput.contains("Dane raportu: Dane testowe raportu"));
            assertTrue(consoleOutput.contains("==== Koniec raportu ===="));
        } finally {
            // Przywracamy oryginalny strumień
            System.setOut(originalOut);
        }
    }

    @Test
    void testWyswietlRaport_zNullem() {
        // Przechwytujemy strumień System.err
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(errContent));

        try {
            // Wywołanie z argumentem null
            raportView.wyswietlRaport(null);

            // Odczytujemy to, co zostało wypisane na standardowy strumień błędów
            String consoleError = errContent.toString();

            // Sprawdzamy, czy pojawił się komunikat o błędzie
            assertTrue(consoleError.contains("Błąd: Raport jest nullem!"));
        } finally {
            // Przywracamy oryginalny strumień błędów
            System.setErr(originalErr);
        }
    }

    @Test
    void testWyswietlRaport_zNiewłaściwymTypemObiektu() {
        // Przechwytujemy strumień System.err
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(errContent));

        try {
            // Podszywamy się innym obiektem, zamiast Raport
            String jakisNieRaport = "Nie jestem raportem!";
            raportView.wyswietlRaport(jakisNieRaport);

            // Odczytujemy błąd
            String consoleError = errContent.toString();

            // Sprawdzamy, czy pojawił się komunikat o niewłaściwym typie
            assertTrue(consoleError.contains("Błąd: Niewłaściwy typ obiektu, oczekiwano Raport!"));
        } finally {
            System.setErr(originalErr);
        }
    }

    @Test
    void testWyswietlBlad() {
        // Przechwytujemy strumień System.err
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(errContent));

        try {
            // Wywołujemy metodę wyświetlania błędu
            raportView.wyswietlBlad("Błędne dane!");

            // Odczytujemy komunikat błędu z konsoli
            String consoleError = errContent.toString();

            // Sprawdzamy, czy pojawił się poprawny komunikat
            assertTrue(consoleError.contains("Błąd: Błędne dane!"));
        } finally {
            System.setErr(originalErr);
        }
    }
}
