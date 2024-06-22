package com.rsa.bingo.domain.services;

import com.rsa.bingo.Data;
import com.rsa.bingo.domain.models.Player;
import com.rsa.bingo.domain.repositories.CardRepository;
import com.rsa.bingo.domain.repositories.CustomizationRepository;
import com.rsa.bingo.domain.repositories.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @InjectMocks
    private PlayerService service;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private CardRepository cardRepository;

    @Mock
    private CustomizationRepository customizationRepository;

    @Test
    void findAll() {
        var expected = List.of(Data.PLAYER());

        when(playerRepository.findAll()).thenReturn(expected);

        var actual = service.findAll();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertIterableEquals(expected, actual)
        );

        verify(playerRepository, times(1)).findAll();
        verifyNoMoreInteractions(playerRepository, cardRepository, customizationRepository);
    }

    @Test
    void findById() {
        var expected = Data.PLAYER();

        when(playerRepository.findById(1)).thenReturn(Optional.of(expected));

        var actual = service.findById(1);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertTrue(actual.isPresent()),
                () -> assertEquals(expected, actual.orElse(null))
        );

        verify(playerRepository, times(1)).findById(any());
        verify(playerRepository, times(1)).findById(1);
        verifyNoMoreInteractions(playerRepository, cardRepository, customizationRepository);
    }

    @Test
    void findByName() {
        var name = "Player";
        var expected = List.of(Data.PLAYER());

        when(playerRepository.findByName(name)).thenReturn(expected);

        var actual = service.findByName(name);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertIterableEquals(expected, actual)
        );

        verify(playerRepository, times(1)).findByName(any());
        verify(playerRepository, times(1)).findByName(name);
        verifyNoMoreInteractions(playerRepository, cardRepository, customizationRepository);
    }

    @Test
    void save() {
        var player = new Player(null, "Player");
        var expected = Data.PLAYER();

        when(playerRepository.save(player)).thenReturn(expected);

        var actual = service.save(player);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(1, actual.getId()),
                () -> assertEquals(expected, actual)
        );

        verify(playerRepository, times(1)).save(any());
        verify(playerRepository, times(1)).save(player);
        verifyNoMoreInteractions(playerRepository, cardRepository, customizationRepository);
    }

    @Test
    void delete() {
        var player = Data.PLAYER();
        var cards = List.of(Data.CARD(1, player));

        when(cardRepository.findByPlayerId(1)).thenReturn(cards);

        assertDoesNotThrow(() -> service.delete(1));

        verify(playerRepository, times(1)).delete(any());
        verify(playerRepository, times(1)).delete(1);
        verify(cardRepository, times(1)).delete(any());
        verify(cardRepository, times(1)).delete(1);
        verify(customizationRepository, times(1)).delete(anyInt());
        verify(customizationRepository, times(1)).delete(1);
        verifyNoMoreInteractions(playerRepository, cardRepository, customizationRepository);
    }
}