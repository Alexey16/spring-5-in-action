package tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tacos.data.IngredientRepository;

import java.net.InetAddress;

@SpringBootApplication
public class TacoCloudApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(TacoCloudApplication.class, args);
        IngredientRepository ingredientRepository = applicationContext.getBean(IngredientRepository.class);

        ingredientRepository.save(new Ingredient("FLTO", "Flour Tortilla",  Ingredient.Type.valueOf("WRAP")));
        ingredientRepository.save(new Ingredient("COTO", "Corn Tortilla",  Ingredient.Type.valueOf("WRAP")));
        ingredientRepository.save(new Ingredient("GRBF", "Ground Beef",  Ingredient.Type.valueOf("PROTEIN")));
        ingredientRepository.save(new Ingredient("CARN", "Carnitas",  Ingredient.Type.valueOf("PROTEIN")));
        ingredientRepository.save(new Ingredient("TMTO", "Diced Tomatoes",  Ingredient.Type.valueOf("VEGGIES")));
        ingredientRepository.save(new Ingredient("LETC", "Lettuce",  Ingredient.Type.valueOf("VEGGIES")));
        ingredientRepository.save(new Ingredient("CHED", "Cheddar",  Ingredient.Type.valueOf("CHEESE")));
        ingredientRepository.save(new Ingredient("JACK", "Monterrey Jack",  Ingredient.Type.valueOf("CHEESE")));
        ingredientRepository.save(new Ingredient("SLSA", "Salsa",  Ingredient.Type.valueOf("SAUCE")));
        ingredientRepository.save(new Ingredient("SRCR", "Sour Cream",  Ingredient.Type.valueOf("SAUCE")));

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }
}
