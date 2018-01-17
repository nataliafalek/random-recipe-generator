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

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties
public class RandomRecipeGeneratorApplication implements CommandLineRunner {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    CuisineRepository cuisineRepository;

    public static void main(String[] args) {
        SpringApplication.run(RandomRecipeGeneratorApplication.class, args);
    }

    @Transactional
    public void run(String... strings) throws Exception {

        Map<Long, Recipe> recipesMap;
        String ingredientPath = "./server/src/main/resources/static/Ingredient_Data.csv";
        String recipePath = "./server/src/main/resources/static/Recipe_Data.csv";
        List<Ingredient> listOfIngredient;

        try (Stream<String> stream = Files.lines(Paths.get("./server/src/main/resources/static/Cuisine"))) {
            stream.forEach(a -> cuisineRepository.save(new Cuisine(a)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        listOfIngredient = readIngredientFromCsv(ingredientPath);
        recipesMap = readRecipeFromCsv(recipePath);
        changeIngredients(recipesMap, listOfIngredient, 1, 0, 5);
        changeIngredients(recipesMap, listOfIngredient, 2, 5, 11);
        changeIngredients(recipesMap, listOfIngredient, 3, 11, 16);
        changeIngredients(recipesMap, listOfIngredient, 4, 16, 25);
        changeIngredients(recipesMap, listOfIngredient, 5, 25, 34);

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


    private Map<Long, Recipe> readRecipeFromCsv(String path) {

        Map<Long, Recipe> recipesMap = new HashMap<>();

        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            long i = 1;
            for (String s : (Iterable<String>) () -> stream.skip(1).iterator()) {
                String[] str = s.split(";");
                recipesMap.put(i, new Recipe(str[1], str[2], Collections.emptyList(), str[3]));
                i++;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return recipesMap;
    }

    private List<Ingredient> readIngredientFromCsv(String path) {
        List<Ingredient> listOfIngredients = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            stream.skip(1).forEach(line -> {
                String[] str = line.split(";");
                listOfIngredients.add(new Ingredient(str[1], str[3], Double.parseDouble(str[2])));
            });
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return listOfIngredients;
    }

    private void changeIngredients(Map<Long, Recipe> recipesMap, List<Ingredient> listOfIngredients, long recipeIdx, int ingredientIdxStart, int ingredientIdxEnd) {
        recipesMap.get(recipeIdx).setIngredients(listOfIngredients.subList(ingredientIdxStart, ingredientIdxEnd));
        recipeRepository.save(recipesMap.get(recipeIdx));
    }

}
