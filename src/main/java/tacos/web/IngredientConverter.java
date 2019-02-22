package tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tacos.Ingredient;
import tacos.data.IngredientRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class IngredientConverter implements Converter<String, Ingredient> {

    private  final IngredientRepository ingredientRepo;

    @Autowired
    public IngredientConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public Ingredient convert(String id) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(ingredients::add);
        return ingredients.stream().filter(ingredient -> ingredient.getId().equals(id)).findFirst().orElse(null);
    }

}
