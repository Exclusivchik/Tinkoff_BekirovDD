package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class TaskN {
    private TaskN() {
    }

    public static List<Animal> sortAnimalsByHeight(List<Animal> animals) {
        return animals.stream().sorted(Comparator.comparingInt(Animal::height)).toList();
    }

    public static List<Animal> getKMostHeavy(List<Animal> animals, int k) {
        return animals.stream().sorted(Comparator.comparingInt(Animal::weight).reversed()).limit(k).toList();
    }

    public static Map<Animal.Type, Long> getCountAnimalsOfEachType(List<Animal> animals) {
        return animals.stream().collect(Collectors.groupingBy(Animal::type, Collectors.counting()));
    }

    public static Animal getAnimalWithLongestName(List<Animal> animals) {
        return animals.stream().max(Comparator.comparing(animal -> animal.name().length())).orElse(null);
    }

    public static Animal.Sex getTheMostPopularSex(List<Animal> animals) {
        var femaleCount = animals.stream().filter(animal -> animal.sex() == Animal.Sex.F).count();
        var maleCount   = animals.stream().filter(animal -> animal.sex() == Animal.Sex.M).count();
        Animal.Sex response;
        if (femaleCount > maleCount) {
            response = Animal.Sex.F;
        } else {
            response = Animal.Sex.M;
        }
        return response;
    }

    public static Map<Animal.Type, Animal> getTheHeaviestAnimalOfEachType(List<Animal> animals) {
        return animals.stream().collect(Collectors.groupingBy(Animal::type, Collectors.collectingAndThen(Collectors
            .maxBy(Comparator.comparingInt(Animal::weight)), animal -> animal.orElse(null))));
    }

    public static Animal getKOlsdestAnimal(List<Animal> animals, int k) {
        return animals.stream().sorted(Comparator.comparingInt(Animal::age).reversed()).toList().get(k);
    }

    public static Animal getTheHeaviestAnimalLessKSm(List<Animal> animals, int k) {
        return animals.stream().filter(animal -> animal.height() < k)
            .max(Comparator.comparingInt(Animal::weight)).orElse(null);
    }

    public static Integer getSumOfPaws(List<Animal> animals) {
        return animals.stream().mapToInt(animal -> animal.paws()).sum();
    }

    public static List<Animal> getAnimalsWhichPawsIsNotEqualToAge(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.age() != animal.paws()).toList();
    }

    @SuppressWarnings("MagicNumber")
    public static List<Animal> getAnimalWhichBitesAndMore100Sm(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.bites() && animal.height() > 100).toList();
    }

    public static Long getCountAnimalsWhichWeightMoreThanHeight(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.weight() > animal.height()).count();
    }

    public static List<Animal> getAnimalsWhichNameContainsMoreThan2Words(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.name().split(" ").length > 2).toList();
    }

    public static Boolean isContainsDogWhichHeightMoreThanKSm(List<Animal> animals, int k) {
        return animals.stream().filter(animal -> animal.type() == Animal.Type.DOG && animal.height() > k).count() > 0;
    }

    public static Map<Animal.Type, Integer> getSumOfWeightAnimalsInRangeAge(List<Animal> animals, int k, int l) {
        return animals.stream()
            .filter(animal -> animal.age() >= k && animal.age() <= l)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    public static List<Animal> sortedByTypeThenBySexThenByName(List<Animal> animals) {
        return animals.stream().sorted(Comparator.comparing(Animal::type).thenComparing(Animal::sex)
            .thenComparing(Animal::name)).toList();
    }

    public static Boolean isItTrueThatSpidersBitesMoreThanDogs(List<Animal> animals) {
        double freqForDog = (double) animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG && animal.bites())
                .count() / animals.stream().filter(animal -> animal.type() == Animal.Type.DOG).count();

        double freqForSpider = (double) animals.stream()
            .filter(animal -> animal.type() == Animal.Type.SPIDER && animal.bites())
                .count() / animals.stream().filter(animal -> animal.type() == Animal.Type.SPIDER).count();

        return freqForSpider > freqForDog;
    }

    public static Animal getHeaviestFishInMoreThanOneList(List<List<Animal>> listListAnimals) {
        return listListAnimals.stream().flatMap(List::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
                .max(Comparator.comparingInt(Animal::weight)).orElse(null);
    }
}
