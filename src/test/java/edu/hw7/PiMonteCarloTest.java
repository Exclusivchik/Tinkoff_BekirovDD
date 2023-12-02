package edu.hw7;

import org.junit.jupiter.api.Test;
//import java.time.Duration;
//import java.time.LocalDateTime;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PiMonteCarloTest {
    private static final double STANDART = 3.1415926535;

//    @Test
//    void boost() {
//        int simulations = 100000000;
//
//        var start1 = LocalDateTime.now();
//        PiMonteCarlo.linearPi(simulations);
//        var end1 = LocalDateTime.now();
//
//        var start2 = LocalDateTime.now();
//        PiMonteCarlo.multiThreadPi(simulations);
//        var end2 = LocalDateTime.now();
//
//        Duration duration1 = Duration.between(start1, end1);
//        Duration duration2 = Duration.between(start2, end2);
//
//        System.out.println(duration1.toMillis() + " " + duration2.toMillis());
//        assertThat(duration1).isGreaterThan(duration2);
//    }

    @Test
    void accuracyLinearPi() {
        int simulations = 10000000;
        var response = PiMonteCarlo.linearPi(simulations);
        assertThat(Math.abs(response - STANDART)).isLessThan(0.001);
    }

    @Test
    void accuracyMultiThreadPi(){
        int simulations = 10000000;
        var response = PiMonteCarlo.multiThreadPi(simulations);
        assertThat(Math.abs(response - STANDART)).isLessThan(0.001);
    }
}
