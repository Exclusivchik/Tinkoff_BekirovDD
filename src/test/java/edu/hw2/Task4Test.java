package edu.hw2;

import edu.hw2.Task4.CallingInfo;
import edu.hw2.Task4.Task4;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Тест1")
    public void igorGoffman() {
        //given
        CallingInfo callingInfo = Task4.callingInfo();

        //when
        String className  = callingInfo.className();
        String methodName = callingInfo.methodName();

        //then
        assertThat(className).isEqualTo("edu.hw2.Task4Test");
        assertThat(methodName).isEqualTo("igorGoffman");
    }
}
