package com.rsa.bingo.domain.models;

import lombok.Data;
import lombok.Setter;

@Data
public class Colors {

    @Setter
    private Integer id;

    private final String primaryColor;

    private final String secondaryColor;
}
