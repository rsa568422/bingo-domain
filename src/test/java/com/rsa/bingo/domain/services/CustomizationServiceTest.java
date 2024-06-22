package com.rsa.bingo.domain.services;

import com.rsa.bingo.Data;
import com.rsa.bingo.domain.models.Customization;
import com.rsa.bingo.domain.repositories.CustomizationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomizationServiceTest {

    @InjectMocks
    private CustomizationService service;

    @Mock
    private CustomizationRepository customizationRepository;

    @Test
    void findByCardId() {
        var expected = List.of(Data.CUSTOMIZATION());

        when(customizationRepository.findByCardId(1)).thenReturn(expected);

        var actual = service.findByCardId(1);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertIterableEquals(expected, actual)
        );

        verify(customizationRepository, times(1)).findByCardId(any());
        verify(customizationRepository, times(1)).findByCardId(1);
        verifyNoMoreInteractions(customizationRepository);
    }

    @Test
    void save() {
        var customization = Data.CUSTOMIZATION();
        var expected = Data.CUSTOMIZATION(1);

        when(customizationRepository.save(customization)).thenReturn(expected);

        var actual = service.save(customization);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(1, actual.getCardId()),
                () -> assertEquals(expected, actual)
        );

        verify(customizationRepository, times(1)).save(any());
        verify(customizationRepository, times(1)).save(customization);
        verifyNoMoreInteractions(customizationRepository);
    }

    @Test
    void delete() {
        assertDoesNotThrow(() -> service.delete(1));

        verify(customizationRepository, times(1)).delete(anyInt());
        verify(customizationRepository, times(1)).delete(1);
        verifyNoMoreInteractions(customizationRepository);
    }

    @Test
    void deleteByCustomization() {
        var customization = Data.CUSTOMIZATION(1);

        assertDoesNotThrow(() -> service.delete(customization));

        verify(customizationRepository, times(1)).delete(any(Customization.class));
        verify(customizationRepository, times(1)).delete(customization);
        verifyNoMoreInteractions(customizationRepository);
    }
}