package com.rsa.bingo.domain.services;

import com.rsa.bingo.Data;
import com.rsa.bingo.domain.repositories.CardRepository;
import com.rsa.bingo.domain.repositories.CustomizationRepository;
import com.rsa.bingo.domain.utils.Parser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CardServiceTest {

    @InjectMocks
    private CardService service;

    @Mock
    private CardRepository cardRepository;

    @Mock
    private CustomizationRepository customizationRepository;

    @Test
    void findById() {
        var expected = Data.CARD(1, Data.PLAYER());

        when(cardRepository.findById(1)).thenReturn(Optional.of(expected));

        var actual = service.findById(1);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertTrue(actual.isPresent()),
                () -> assertEquals(expected, actual.orElse(null))
        );

        verify(cardRepository, times(1)).findById(any());
        verify(cardRepository, times(1)).findById(1);
        verifyNoMoreInteractions(cardRepository, customizationRepository);
    }

    @Test
    void findByPlayerId() {
        var expected = List.of(Data.CARD(1, Data.PLAYER()));

        when(cardRepository.findByPlayerId(1)).thenReturn(expected);

        var actual = service.findByPlayerId(1);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertIterableEquals(expected, actual)
        );

        verify(cardRepository, times(1)).findByPlayerId(any());
        verify(cardRepository, times(1)).findByPlayerId(1);
        verifyNoMoreInteractions(cardRepository, customizationRepository);
    }

    @Test
    void save() {
        var card = Data.CARD(null, Data.PLAYER());
        var expected = Data.CARD(1, Data.PLAYER());

        when(cardRepository.save(card)).thenReturn(expected);

        var actual = service.save(card);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(1, actual.getId()),
                () -> assertEquals(expected, actual)
        );

        verify(cardRepository, times(1)).save(any());
        verify(cardRepository, times(1)).save(card);
        verifyNoMoreInteractions(cardRepository, customizationRepository);
    }

    @Test
    void delete() {
        assertDoesNotThrow(() -> service.delete(1));

        verify(cardRepository, times(1)).delete(any());
        verify(cardRepository, times(1)).delete(1);
        verify(customizationRepository, times(1)).delete(anyInt());
        verify(customizationRepository, times(1)).delete(1);
        verifyNoMoreInteractions(cardRepository, customizationRepository);
    }

    @Test
    void toBytes() {
        var card = Data.CARD(1, Data.PLAYER());
        var customization = Data.CUSTOMIZATION();
        var expected = "PASSED".getBytes();

        byte[] actual;
        try (MockedStatic<Parser> mockedStatic = Mockito.mockStatic(Parser.class)) {
            mockedStatic.when(() -> Parser.getBytes(card, customization)).thenReturn(expected);

            actual = service.toBytes(card, customization);

            mockedStatic.verify(() -> Parser.getBytes(any(), any()), times(1));
            mockedStatic.verify(() -> Parser.getBytes(card, customization), times(1));
            mockedStatic.verifyNoMoreInteractions();
        }

        assertAll(
                () -> assertNotNull(actual),
                () -> assertArrayEquals(expected, actual)
        );

        verifyNoMoreInteractions(cardRepository, customizationRepository);
    }
}