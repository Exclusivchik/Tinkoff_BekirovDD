package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskNTest {
    @Test
    @DisplayName("от самого маленького к самому большому по росту")
    void test1() {
        Animal cat = new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, 5, 30, 5, true);
        Animal dog = new Animal("Max", Animal.Type.DOG, Animal.Sex.M, 3, 50, 20, true);
        Animal bird = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 0, false);
        Animal fish = new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 5, 0, false);
        Animal spider = new Animal("Man", Animal.Type.SPIDER, Animal.Sex.F, 10, 10, 2, true);

        List<Animal> animals = List.of(cat, dog, bird, fish, spider);

        List<Animal> expectedResult = List.of(fish, bird, spider, cat, dog);
        List<Animal> result = TaskN.sortAnimalsByHeight(animals);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("К самых тяжелых")
    void test2() {
        Animal cat = new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, 5, 30, 5, true);
        Animal dog = new Animal("Max", Animal.Type.DOG, Animal.Sex.M, 3, 50, 20, true);
        Animal bird = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 0, false);
        Animal fish = new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 5, 0, false);
        Animal spider = new Animal("Man", Animal.Type.SPIDER, Animal.Sex.F, 10, 10, 2, true);

        List<Animal> animals = List.of(cat, dog, bird, fish, spider);

        List<Animal> expectedResult = List.of(dog, cat);
        List<Animal> result = TaskN.getKMostHeavy(animals, 2);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Сколько животных каждого вида")
    void test3() {
        Animal cat = new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, 5, 30, 5, true);
        Animal dog = new Animal("Max", Animal.Type.DOG, Animal.Sex.M, 3, 50, 20, true);
        Animal bird = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 0, false);
        Animal fish = new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 5, 0, false);
        Animal spider = new Animal("Man", Animal.Type.SPIDER, Animal.Sex.F, 10, 10, 2, true);

        List<Animal> animals = List.of(cat, dog, bird, fish, spider);

        Map<Animal.Type, Long> expectedResult = new HashMap<>();
        expectedResult.put(Animal.Type.DOG, 1L);
        expectedResult.put(Animal.Type.FISH, 1L);
        expectedResult.put(Animal.Type.BIRD, 1L);
        expectedResult.put(Animal.Type.CAT, 1L);
        expectedResult.put(Animal.Type.SPIDER, 1L);

        var result = TaskN.getCountAnimalsOfEachType(animals);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("У какого животного самое длинное имя")
    void test4() {
        Animal cat = new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, 5, 30, 5, true);
        Animal dog = new Animal("Max", Animal.Type.DOG, Animal.Sex.M, 3, 50, 20, true);
        Animal bird = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 0, false);
        Animal fish = new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 5, 0, false);
        Animal spider = new Animal("Man", Animal.Type.SPIDER, Animal.Sex.F, 10, 10, 2, true);

        List<Animal> animals = List.of(cat, dog, bird, fish, spider);

        Animal expectedResult = bird;

        var result = TaskN.getAnimalWithLongestName(animals);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Каких животных больше: самцов или самок")
    void test5() {
        Animal cat = new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, 5, 30, 5, true);
        Animal dog = new Animal("Max", Animal.Type.DOG, Animal.Sex.M, 3, 50, 20, true);
        Animal bird = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 0, false);
        Animal fish = new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 5, 0, false);
        Animal spider = new Animal("Man", Animal.Type.SPIDER, Animal.Sex.F, 10, 10, 2, true);

        List<Animal> animals = List.of(cat, dog, bird, fish, spider);

        Animal.Sex expectedResult = Animal.Sex.M;

        var result = TaskN.getTheMostPopularSex(animals);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Самое тяжелое животное каждого вида")
    void test6() {
        Animal cat = new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, 5, 30, 5, true);
        Animal cat1 = new Animal("Yam", Animal.Type.CAT, Animal.Sex.F, 5, 30, 7, true);
        Animal dog = new Animal("Max", Animal.Type.DOG, Animal.Sex.M, 3, 50, 20, true);
        Animal dog1 = new Animal("Payne", Animal.Type.DOG, Animal.Sex.M, 3, 50, 19, true);
        Animal bird = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 0, false);
        Animal bird1 = new Animal("Fitty", Animal.Type.BIRD, Animal.Sex.M, 1, 10, 1, false);
        Animal fish = new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 5, 0, false);
        Animal fish1 = new Animal("Doru", Animal.Type.FISH, Animal.Sex.F, 1, 5, 1, false);
        Animal spider = new Animal("Man", Animal.Type.SPIDER, Animal.Sex.F, 10, 10, 2, true);

        List<Animal> animals = List.of(cat, cat1, dog, dog1, bird, bird1, fish, fish1, spider);

        Map<Animal.Type, Animal> expectedResult = new HashMap<>();
        expectedResult.put(Animal.Type.FISH, fish1);
        expectedResult.put(Animal.Type.DOG, dog);
        expectedResult.put(Animal.Type.CAT, cat1);
        expectedResult.put(Animal.Type.BIRD, bird1);
        expectedResult.put(Animal.Type.SPIDER, spider);

        var result = TaskN.getTheHeaviestAnimalOfEachType(animals);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("K-е самое старое животное")
    void test7() {
        Animal cat = new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, 5, 30, 5, true);
        Animal cat1 = new Animal("Yam", Animal.Type.CAT, Animal.Sex.F, 5, 30, 7, true);
        Animal dog = new Animal("Max", Animal.Type.DOG, Animal.Sex.M, 3, 50, 20, true);
        Animal dog1 = new Animal("Payne", Animal.Type.DOG, Animal.Sex.M, 3, 50, 19, true);
        Animal bird = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 0, false);
        Animal bird1 = new Animal("Fitty", Animal.Type.BIRD, Animal.Sex.M, 1, 10, 1, false);
        Animal fish = new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 5, 0, false);
        Animal fish1 = new Animal("Doru", Animal.Type.FISH, Animal.Sex.F, 1, 5, 1, false);
        Animal spider = new Animal("Man", Animal.Type.SPIDER, Animal.Sex.F, 10, 10, 2, true);

        List<Animal> animals = List.of(cat, cat1, dog, dog1, bird, bird1, fish, fish1, spider);

        Animal expectedResult = dog1;

        var result = TaskN.getKOlsdestAnimal(animals, 4);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Самое тяжелое животное среди животных ниже k см")
    void test8() {
        Animal cat = new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, 5, 30, 5, true);
        Animal cat1 = new Animal("Yam", Animal.Type.CAT, Animal.Sex.F, 5, 30, 7, true);
        Animal dog = new Animal("Max", Animal.Type.DOG, Animal.Sex.M, 3, 50, 20, true);
        Animal dog1 = new Animal("Payne", Animal.Type.DOG, Animal.Sex.M, 3, 50, 19, true);
        Animal bird = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 0, false);
        Animal bird1 = new Animal("Fitty", Animal.Type.BIRD, Animal.Sex.M, 1, 10, 1, false);
        Animal fish = new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 5, 0, false);
        Animal fish1 = new Animal("Doru", Animal.Type.FISH, Animal.Sex.F, 1, 5, 1, false);
        Animal spider = new Animal("Man", Animal.Type.SPIDER, Animal.Sex.F, 10, 10, 2, true);

        List<Animal> animals = List.of(cat, cat1, dog, dog1, bird, bird1, fish, fish1, spider);

        Animal expectedResult = cat1;

        var result = TaskN.getTheHeaviestAnimalLessKSm(animals, 50);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Сумма лап животных в списке")
    void test9() {
        Animal cat = new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, 5, 30, 5, true);
        Animal cat1 = new Animal("Yam", Animal.Type.CAT, Animal.Sex.F, 5, 30, 7, true);
        Animal dog = new Animal("Max", Animal.Type.DOG, Animal.Sex.M, 3, 50, 20, true);
        Animal dog1 = new Animal("Payne", Animal.Type.DOG, Animal.Sex.M, 3, 50, 19, true);
        Animal bird = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 0, false);
        Animal bird1 = new Animal("Fitty", Animal.Type.BIRD, Animal.Sex.M, 1, 10, 1, false);
        Animal fish = new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 5, 0, false);
        Animal fish1 = new Animal("Doru", Animal.Type.FISH, Animal.Sex.F, 1, 5, 1, false);
        Animal spider = new Animal("Man", Animal.Type.SPIDER, Animal.Sex.F, 10, 10, 2, true);

        List<Animal> animals = List.of(cat, cat1, dog, dog1, bird, bird1, fish, fish1, spider);

        Integer expectedResult = 28;

        var result = TaskN.getSumOfPaws(animals);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Список животных, возраст у которых не совпадает с количеством лап")
    void test10() {
        Animal cat = new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, 5, 30, 5, true);
        Animal cat1 = new Animal("Yam", Animal.Type.CAT, Animal.Sex.F, 4, 30, 7, true);
        Animal dog = new Animal("Max", Animal.Type.DOG, Animal.Sex.M, 3, 50, 20, true);
        Animal dog1 = new Animal("Payne", Animal.Type.DOG, Animal.Sex.M, 3, 50, 19, true);
        Animal bird = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 0, false);
        Animal bird1 = new Animal("Fitty", Animal.Type.BIRD, Animal.Sex.M, 1, 10, 1, false);
        Animal fish = new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 5, 0, false);
        Animal fish1 = new Animal("Doru", Animal.Type.FISH, Animal.Sex.F, 1, 5, 1, false);
        Animal spider = new Animal("Man", Animal.Type.SPIDER, Animal.Sex.F, 8, 10, 2, true);

        List<Animal> animals = List.of(cat, cat1, dog, dog1, bird, bird1, fish, fish1, spider);

        List<Animal> expectedResult = List.of(cat, dog, dog1, bird, bird1, fish, fish1);
        //expectedResult.sort(Comparator.comparingInt(Animal::age));

        var result = TaskN.getAnimalsWhichPawsIsNotEqualToAge(animals);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Список животных, которые могут укусить (bites == true) и рост которых превышает 100 см")
    void test11() {
        Animal cat = new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, 5, 300, 5, true);
        Animal cat1 = new Animal("Yam", Animal.Type.CAT, Animal.Sex.F, 4, 150, 7, true);
        Animal dog = new Animal("Max", Animal.Type.DOG, Animal.Sex.M, 3, 101, 20, true);
        Animal dog1 = new Animal("Payne", Animal.Type.DOG, Animal.Sex.M, 3, 50, 19, true);
        Animal bird = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 0, false);
        Animal bird1 = new Animal("Fitty", Animal.Type.BIRD, Animal.Sex.M, 1, 10, 1, false);
        Animal fish = new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 5, 0, false);
        Animal fish1 = new Animal("Doru", Animal.Type.FISH, Animal.Sex.F, 1, 5, 1, false);
        Animal spider = new Animal("Man", Animal.Type.SPIDER, Animal.Sex.F, 10, 101, 2, true);

        List<Animal> animals = List.of(cat, cat1, dog, dog1, bird, bird1, fish, fish1, spider);

        List<Animal> expectedResult = List.of(cat, cat1, dog, spider);

        var result = TaskN.getAnimalWhichBitesAndMore100Sm(animals);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Сколько в списке животных, вес которых превышает рост")
    void test12() {
        Animal cat = new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, 5, 10, 50, true);
        Animal cat1 = new Animal("Yam", Animal.Type.CAT, Animal.Sex.F, 4, 12, 15, true);
        Animal dog = new Animal("Max", Animal.Type.DOG, Animal.Sex.M, 3, 2, 12, true);
        Animal dog1 = new Animal("Payne", Animal.Type.DOG, Animal.Sex.M, 3, 50, 19, true);
        Animal bird = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 0, false);
        Animal bird1 = new Animal("Fitty", Animal.Type.BIRD, Animal.Sex.M, 1, 10, 1, false);
        Animal fish = new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 5, 0, false);
        Animal fish1 = new Animal("Doru", Animal.Type.FISH, Animal.Sex.F, 1, 5, 1, false);
        Animal spider = new Animal("Man", Animal.Type.SPIDER, Animal.Sex.F, 10, 7, 78, true);

        List<Animal> animals = List.of(cat, cat1, dog, dog1, bird, bird1, fish, fish1, spider);

        Long expectedResult = 4L;

        var result = TaskN.getCountAnimalsWhichWeightMoreThanHeight(animals);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Список животных, имена которых состоят из более чем двух слов")
    void test13() {
        Animal cat = new Animal("Tom Dj ecler", Animal.Type.CAT, Animal.Sex.M, 5, 10, 50, true);
        Animal cat1 = new Animal("Yam", Animal.Type.CAT, Animal.Sex.F, 4, 12, 15, true);
        Animal dog = new Animal("Max Cake tupoy", Animal.Type.DOG, Animal.Sex.M, 3, 2, 12, true);
        Animal dog1 = new Animal("Payne", Animal.Type.DOG, Animal.Sex.M, 3, 50, 19, true);
        Animal bird = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 0, false);
        Animal bird1 = new Animal("Fitty", Animal.Type.BIRD, Animal.Sex.M, 1, 10, 1, false);
        Animal fish = new Animal("Nemo the Dumbest", Animal.Type.FISH, Animal.Sex.M, 1, 5, 0, false);
        Animal fish1 = new Animal("Doru", Animal.Type.FISH, Animal.Sex.F, 1, 5, 1, false);
        Animal spider = new Animal("Man qwe invoker", Animal.Type.SPIDER, Animal.Sex.F, 10, 7, 78, true);

        List<Animal> animals = List.of(cat, cat1, dog, dog1, bird, bird1, fish, fish1, spider);

        List<Animal> expectedResult = List.of(cat, dog, fish, spider);

        var result = TaskN.getAnimalsWhichNameContainsMoreThan2Words(animals);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Есть ли в списке собака ростом более k см")
    void test14() {
        Animal cat = new Animal("Tom Dj ecler", Animal.Type.CAT, Animal.Sex.M, 5, 10, 50, true);
        Animal cat1 = new Animal("Yam", Animal.Type.CAT, Animal.Sex.F, 4, 12, 15, true);
        Animal dog = new Animal("Max Cake tupoy", Animal.Type.DOG, Animal.Sex.M, 3, 2, 12, true);
        Animal dog1 = new Animal("Payne", Animal.Type.DOG, Animal.Sex.M, 3, 50, 19, true);
        Animal bird = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 0, false);
        Animal bird1 = new Animal("Fitty", Animal.Type.BIRD, Animal.Sex.M, 1, 10, 1, false);
        Animal fish = new Animal("Nemo the Dumbest", Animal.Type.FISH, Animal.Sex.M, 1, 5, 0, false);
        Animal fish1 = new Animal("Doru", Animal.Type.FISH, Animal.Sex.F, 1, 5, 1, false);
        Animal spider = new Animal("Man qwe invoker", Animal.Type.SPIDER, Animal.Sex.F, 10, 7, 78, true);

        List<Animal> animals = List.of(cat, cat1, dog, dog1, bird, bird1, fish, fish1, spider);

        Boolean expectedResult = true;

        var result = TaskN.isContainsDogWhichHeightMoreThanKSm(animals, 10);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Найти суммарный вес животных каждого вида, которым от k до l лет")
    void test15() {
        Animal cat = new Animal("Tom Dj ecler", Animal.Type.CAT, Animal.Sex.M, 5, 10, 50, true);
        Animal cat1 = new Animal("Yam", Animal.Type.CAT, Animal.Sex.F, 4, 12, 15, true);
        Animal dog = new Animal("Max Cake tupoy", Animal.Type.DOG, Animal.Sex.M, 3, 2, 12, true);
        Animal dog1 = new Animal("Payne", Animal.Type.DOG, Animal.Sex.M, 3, 50, 19, true);
        Animal bird = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 0, false);
        Animal bird1 = new Animal("Fitty", Animal.Type.BIRD, Animal.Sex.M, 1, 10, 1, false);
        Animal fish = new Animal("Nemo the Dumbest", Animal.Type.FISH, Animal.Sex.M, 1, 5, 0, false);
        Animal fish1 = new Animal("Doru", Animal.Type.FISH, Animal.Sex.F, 1, 5, 1, false);
        Animal spider = new Animal("Man qwe invoker", Animal.Type.SPIDER, Animal.Sex.F, 10, 7, 78, true);

        List<Animal> animals = List.of(cat, cat1, dog, dog1, bird, bird1, fish, fish1, spider);

        Map<Animal.Type, Integer> expectedResult = Map.of(
            Animal.Type.CAT, 15,
            Animal.Type.DOG, 31,
            Animal.Type.BIRD, 1,
            Animal.Type.FISH, 1
        );

        var result = TaskN.getSumOfWeightAnimalsInRangeAge(animals, 0, 4);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Список животных, отсортированный по виду, затем по полу, затем по имени")
    void test16() {
        Animal cat = new Animal("Tom Dj ecler", Animal.Type.CAT, Animal.Sex.M, 5, 10, 50, true);
        Animal cat1 = new Animal("Yam", Animal.Type.CAT, Animal.Sex.F, 4, 12, 15, true);
        Animal dog = new Animal("Max Cake tupoy", Animal.Type.DOG, Animal.Sex.M, 3, 2, 12, true);
        Animal dog1 = new Animal("Payne", Animal.Type.DOG, Animal.Sex.M, 3, 50, 19, true);
        Animal bird = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 0, false);
        Animal bird1 = new Animal("Fitty", Animal.Type.BIRD, Animal.Sex.M, 1, 10, 1, false);
        Animal fish = new Animal("Nemo the Dumbest", Animal.Type.FISH, Animal.Sex.M, 1, 5, 0, false);
        Animal fish1 = new Animal("Doru", Animal.Type.FISH, Animal.Sex.F, 1, 5, 1, false);
        Animal spider = new Animal("Man qwe invoker", Animal.Type.SPIDER, Animal.Sex.F, 10, 7, 78, true);

        List<Animal> animals = List.of(cat, cat1, dog, dog1, bird, bird1, fish, fish1, spider);

        List<Animal> expectedResult = List.of(cat, cat1, dog, dog1, bird1, bird, fish, fish1, spider);
        var result = TaskN.sortedByTypeThenBySexThenByName(animals);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Правда ли, что пауки кусаются чаще, чем собаки")
    void test17() {
        Animal cat = new Animal("Tom Dj ecler", Animal.Type.CAT, Animal.Sex.M, 5, 10, 50, true);
        Animal cat1 = new Animal("Yam", Animal.Type.CAT, Animal.Sex.F, 4, 12, 15, true);
        Animal dog = new Animal("Max Cake tupoy", Animal.Type.DOG, Animal.Sex.M, 3, 2, 12, true);
        Animal dog1 = new Animal("Payne", Animal.Type.DOG, Animal.Sex.M, 3, 50, 19, true);
        Animal bird = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 0, false);
        Animal bird1 = new Animal("Fitty", Animal.Type.BIRD, Animal.Sex.M, 1, 10, 1, false);
        Animal fish = new Animal("Nemo the Dumbest", Animal.Type.FISH, Animal.Sex.M, 1, 5, 0, false);
        Animal fish1 = new Animal("Doru", Animal.Type.FISH, Animal.Sex.F, 1, 5, 1, false);
        Animal spider = new Animal("Man qwe invoker", Animal.Type.SPIDER, Animal.Sex.F, 10, 7, 78, true);

        List<Animal> animals = List.of(cat, cat1, dog, dog1, bird, bird1, fish, fish1, spider);

        Boolean expectedResult = false;
        var result = TaskN.isItTrueThatSpidersBitesMoreThanDogs(animals);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Найти самую тяжелую рыбку в 2-х или более списках")
    void test18() {
        Animal cat = new Animal("Tom Dj ecler", Animal.Type.CAT, Animal.Sex.M, 5, 10, 50, true);
        Animal cat1 = new Animal("Yam", Animal.Type.CAT, Animal.Sex.F, 4, 12, 15, true);
        Animal dog = new Animal("Max Cake tupoy", Animal.Type.DOG, Animal.Sex.M, 3, 2, 12, true);
        Animal dog1 = new Animal("Payne", Animal.Type.DOG, Animal.Sex.M, 3, 50, 19, true);
        Animal bird = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 0, false);
        Animal bird1 = new Animal("Fitty", Animal.Type.BIRD, Animal.Sex.M, 1, 10, 1, false);
        Animal fish = new Animal("Nemo the Dumbest", Animal.Type.FISH, Animal.Sex.M, 1, 5, 0, false);
        Animal fish1 = new Animal("Doru", Animal.Type.FISH, Animal.Sex.F, 1, 5, 1, false);
        Animal spider = new Animal("Man qwe invoker", Animal.Type.SPIDER, Animal.Sex.F, 10, 7, 78, true);

        List<Animal> animals1 = List.of(cat, dog, bird, fish, spider);
        List<Animal> animals2 = List.of(cat1, dog1, bird1, fish1);
        List<List<Animal>> full = List.of(animals1, animals2);

        Animal expectedResult = fish1;
        var result = TaskN.getHeaviestFishInMoreThanOneList(full);

        Assertions.assertEquals(expectedResult, result);
    }
}
