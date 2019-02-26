package tacos.web;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import tacos.domain.Ingredient;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/ingredients", produces = "application/json")
@CrossOrigin(origins = "*")
public class IngredientController {

    RestTemplate rest = new RestTemplate();

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public Ingredient getIngredientById(String ingredientId) {
        Map<String,String> urlVariables = new HashMap<>();
        urlVariables.put("id", ingredientId);
        return rest.getForObject("http://localhost:8080/ingredients/{id}",
                Ingredient.class, urlVariables);
    }

    public void updateIngredient(Ingredient ingredient) {
        rest.put("http://localhost:8080/ingredients/{id}",
                ingredient,
                ingredient.getId());
    }

    public void deleteIngredient(Ingredient ingredient) {
        rest.delete("http://localhost:8080/ingredients/{id}",
                ingredient.getId());
    }


    public Ingredient createIngredient(Ingredient ingredient) {
        return rest.postForObject("http://localhost:8080/ingredients",
                ingredient,
                Ingredient.class);
    }

}
