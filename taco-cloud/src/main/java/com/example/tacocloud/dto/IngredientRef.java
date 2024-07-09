package com.example.tacocloud.dto;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Data
public class IngredientRef {
    private final String ingredient;
    private List<Ingredient> ingredients;


    public IngredientRef(String ingredient) {
        this.ingredient = ingredient;
    }


}