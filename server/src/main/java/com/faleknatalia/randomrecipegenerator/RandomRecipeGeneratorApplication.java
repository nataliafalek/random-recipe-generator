package com.faleknatalia.randomrecipegenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties
public class RandomRecipeGeneratorApplication implements CommandLineRunner {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(RandomRecipeGeneratorApplication.class);

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    CuisineRepository cuisineRepository;

    public static void main(String[] args) {
        SpringApplication.run(RandomRecipeGeneratorApplication.class, args);
    }

    @Transactional
    public void run(String... strings) throws Exception {

        Map<Long, Recipe> recipesMap = readRecipeFromCsv("/static/Recipe_Data.csv");
        List<Ingredient> listOfIngredient = readIngredientFromCsv("/static/Ingredient_Data.csv");

        changeIngredients(recipesMap, listOfIngredient, 1, 0, 5);
        changeIngredients(recipesMap, listOfIngredient, 2, 5, 11);
        changeIngredients(recipesMap, listOfIngredient, 3, 11, 16);
        changeIngredients(recipesMap, listOfIngredient, 4, 16, 25);
        changeIngredients(recipesMap, listOfIngredient, 5, 25, 34);

        cuisineRepository.save(readCuisineFromFile("/static/Cuisine"));
        recipeRepository.save(recipesMap.values());

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

        try (Stream<String> stream = Files.lines(Paths.get(this.getClass().getResource(path).toURI()))) {
            stream.skip(1).forEach(line -> {
                String[] str = line.split(";");
                recipesMap.put(Long.parseLong(str[0]), new Recipe(str[1], str[2], Collections.emptyList(), str[3]));

            });
        } catch (IOException|URISyntaxException e) {
            logger.error("Can't read recipe from file", e);
        }
        return recipesMap;
    }

    private List<Ingredient> readIngredientFromCsv(String path) {
        try (Stream<String> stream = Files.lines(Paths.get(this.getClass().getResource(path).toURI()))) {
            return stream.skip(1).map(line -> {
                String[] str = line.split(";");
                return (new Ingredient(str[1], str[3], Double.parseDouble(str[2])));
            }).collect(Collectors.toList());
        } catch (IOException|URISyntaxException e) {
            logger.error("Can't read ingredients from file", e);
            return new ArrayList<>();
        }
    }

    private void changeIngredients(Map<Long, Recipe> recipesMap, List<Ingredient> listOfIngredients, long recipeIdx, int ingredientIdxStart, int ingredientIdxEnd) {
        recipesMap.get(recipeIdx).setIngredients(listOfIngredients.subList(ingredientIdxStart, ingredientIdxEnd));
    }

    private List<Cuisine> readCuisineFromFile(String path) {
        List<Cuisine> cuisines = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(this.getClass().getResource(path).toURI()))) {
           cuisines = stream.map(a -> new Cuisine(a)).collect(Collectors.toList());
        } catch (IOException|URISyntaxException e) {
            logger.error("Can't load cuisines from file", e);
        }
        return cuisines;
    }
}
