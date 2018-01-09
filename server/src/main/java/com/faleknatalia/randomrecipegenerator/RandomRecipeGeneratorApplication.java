package com.faleknatalia.randomrecipegenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties
public class RandomRecipeGeneratorApplication implements CommandLineRunner {

    @Autowired
    RecipeRepository recipeRepository;

    public static void main(String[] args) {
        SpringApplication.run(RandomRecipeGeneratorApplication.class, args);
    }

    @Transactional
    public void run(String... strings) throws Exception {
        List<Ingredient> listIngredients = new ArrayList<Ingredient>() {{
            add(new Ingredient("jabłko", "kg", 1));
            add(new Ingredient("masło", "g", 100));
            add(new Ingredient("mąka", "kg", 1));
        }};

        Recipe recipe1 = Recipe.newRecipe(new Recipe("Poland", "jabłecznik", listIngredients, "Pokroić i podgrzać"));

        recipeRepository.save(recipe1);

    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }
}
