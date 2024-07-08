package com.example.tacocloud.repositories;

import com.example.tacocloud.dto.Ingredient;
import java.util.Optional;

public interface IngredientRepository {

    // получение всех ингредиентов в виде коллекции объектов Ingredient;
    Iterable<Ingredient> findAll();

    // получение одного ингредиента по идентификатору;
    Optional<Ingredient> findById(String id);

    // сохранение объекта Ingredient.
    Ingredient save(Ingredient ingredient);



}