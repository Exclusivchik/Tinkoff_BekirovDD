package edu.hw10.Task1.Classes;

import edu.hw10.Task1.Annots.Max;
import edu.hw10.Task1.Annots.Min;
import edu.hw10.Task1.Annots.NotNull;

public class MyClassWithAnnots {
    private int age;
    private String name;

    public MyClassWithAnnots(@Min(3) @Max(78) int age, @NotNull String name) {
        this.age = age;
        this.name = name;
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public static MyClassWithAnnots create() {
        return new MyClassWithAnnots(19, "Damir");
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
