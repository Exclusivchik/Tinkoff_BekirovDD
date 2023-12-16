package edu.hw10;

import edu.hw10.Task1.Classes.MyClassWithAnnots;
import edu.hw10.Task1.Classes.MyRecord;
import edu.hw10.Task1.RandomObjectGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class RandomObjectGeneratorTest {
    @Test
    @DisplayName("Бизнесс логика для POJO")
    void test1() {
        var myClassWithAnnots = (MyClassWithAnnots) RandomObjectGenerator.nextObject(MyClassWithAnnots.class);
        assertThat(myClassWithAnnots.getName()).isNotEqualTo(null);
        assertThat(myClassWithAnnots.getAge()).isLessThanOrEqualTo(78).isGreaterThanOrEqualTo(3);
    }

    @Test
    @DisplayName("Бизнесс логика для POJO с фабричным методом")
    void test2() {
        var myClassWithAnnots = (MyClassWithAnnots) RandomObjectGenerator.nextObject(MyClassWithAnnots.class, "create");
        assertThat(myClassWithAnnots.getName()).isEqualTo("Damir");
        assertThat(myClassWithAnnots.getAge()).isEqualTo(19);
    }

    @Test
    @DisplayName("Бизнесс логика для Record")
    void test3() {
        var myRecord = (MyRecord) RandomObjectGenerator.nextObject(MyRecord.class);
        assertThat(myRecord.a()).isEqualTo(0);
        assertThat(myRecord.b()).isEqualTo(0);
        assertThat(myRecord.title()).isNull();
    }
}
