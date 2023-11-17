package edu.hw3;

public class Task4 {
    private Task4() {
    }

    private static final int MAX_NUM = 3999;

    @SuppressWarnings("MagicNumber")
    public static String toRoman(int number) {
        if (number < 1 || number > MAX_NUM) {
            throw new IllegalArgumentException("Number must be between 1 and 3999");
        }

        String[] romanSymbols = {"M", "CM", "D", "CD", "C", "XC",
                                 "L", "XL", "X", "IX", "V", "IV", "I" };
        int[] numericValues = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        StringBuilder romanNumber = new StringBuilder();

        int tempNumber = number;
        for (int i = 0; i < romanSymbols.length; i++) {
            while (tempNumber >= numericValues[i]) {
                romanNumber.append(romanSymbols[i]);
                tempNumber -= numericValues[i];
            }
        }
        return romanNumber.toString();
    }
}
