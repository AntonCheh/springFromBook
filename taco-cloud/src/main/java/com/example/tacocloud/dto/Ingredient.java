package com.example.tacocloud.dto;

import lombok.Data;

@Data
public class Ingredient {

    private final String id;

    private final String name;

    private final Type type;

}
