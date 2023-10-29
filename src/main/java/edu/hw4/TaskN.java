package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class TaskN {
    private TaskN() {
    }

    public static List<Animal> task1(List<Animal> animals) {
        return animals.stream().sorted(Comparator.comparingInt(Animal::height)).toList();
    }

    public static List<Animal> task2(List<Animal> animals, int k) {
        return animals.stream().sorted(Comparator.comparingInt(Animal::weight).reversed()).limit(k).toList();
    }

    public static Map<Animal.Type, Long> task3(List<Animal> animals) {
        return animals.stream().collect(Collectors.groupingBy(Animal::type, Collectors.counting()));
    }

    public static Animal task4(List<Animal> animals) {
        return animals.stream().max(Comparator.comparing(animal -> animal.name().length())).orElse(null);
    }

    public static Animal.Sex task5(List<Animal> animals) {
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

    public static Map<Animal.Type, Animal> task6(List<Animal> animals) {
        return animals.stream().collect(Collectors.groupingBy(Animal::type, Collectors.collectingAndThen(Collectors
            .maxBy(Comparator.comparingInt(Animal::weight)), animal -> animal.orElse(null))));
    }

    public static Animal task7(List<Animal> animals, int k) {
        return animals.stream().sorted(Comparator.comparingInt(Animal::age).reversed()).toList().get(k);
    }

    public static Animal task8(List<Animal> animals, int k) {
        return animals.stream().filter(animal -> animal.height() < k)
            .max(Comparator.comparingInt(Animal::weight)).orElse(null);
    }

    public static Long task9(List<Animal> animals) {
        return animals.stream().map(animal -> animal.paws()).count();
    }

    public static List<Animal> task10(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.age() != animal.paws()).toList();
    }

    @SuppressWarnings("MagicNumber")
    public static List<Animal> task11(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.bites() && animal.height() < 100).toList();
    }

    public static Long task12(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.weight() > animal.height()).count();
    }

    public static List<Animal> task13(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.name().split(" ").length > 2).toList();
    }

    public static Boolean task14(List<Animal> animals, int k) {
        return animals.stream().filter(animal -> animal.type() == Animal.Type.DOG && animal.height() > k).count() > 0;
    }

    public static Integer task15(List<Animal> animals, int k, int l) {
        return animals.stream().filter(animal -> animal.age() >= k && animal.age() <= l)
            .mapToInt(animal -> animal.weight()).sum();
    }

    public static List<Animal> task16(List<Animal> animals) {
        return animals.stream().sorted(new Comparator<Animal>() {
            @Override
            public int compare(Animal o1, Animal o2) {
                if (o1.type().compareTo(o2.type()) != 0) {
                    return o1.type().compareTo(o2.type());
                }
                if (o1.sex().compareTo(o2.sex()) != 0) {
                    o1.sex().compareTo(o2.sex());
                }
                return o1.name().compareTo(o2.name());
            }
        }).toList();
    }

    public static Boolean task17(List<Animal> animals) {
        double freqForDog = (double) animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG && animal.bites())
                .count() / animals.stream().filter(animal -> animal.type() == Animal.Type.DOG).count();

        double freqForSpider = (double) animals.stream()
            .filter(animal -> animal.type() == Animal.Type.SPIDER && animal.bites())
                .count() / animals.stream().filter(animal -> animal.type() == Animal.Type.SPIDER).count();

        return freqForSpider > freqForDog;
    }

    public static Animal task18(List<List<Animal>> listListAnimals) {
        return listListAnimals.stream().flatMap(List::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
                .max(Comparator.comparingInt(Animal::weight)).orElse(null);
    }
}
