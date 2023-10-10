package edu.hw1;

public class Task7 {
    public static int getLenIn2Base(int n) {
        int len_bit_n = 0;
        while (n > 0) {
            len_bit_n++;
            n /= 2;
        }
        return len_bit_n;
    }

    public static int rotateLeft(int n, int shift) {
        int new_n = n, len_bit_a = getLenIn2Base(n);
        shift %= len_bit_a;
        for (int i = 0; i < len_bit_a; i++) {
            int new_pos = (i + shift) % len_bit_a;
            if ((n & (1 << i)) == (1 << i)) {
                new_n = new_n | (1 << new_pos);
            } else {
                new_n = new_n & ~(1 << new_pos);
            }
        }
        return new_n;
    }

    public static int rotateRight(int n, int shift) {
        int new_n = n, len_bit_a = getLenIn2Base(n);
        shift %= len_bit_a;
        for (int i = len_bit_a; i > -1; i--) {
            int new_pos = (i - shift);
            if (new_pos < 0) {
                new_pos += len_bit_a;
            }
            if ((n & (1 << i)) == (1 << i)) {
                new_n = new_n | (1 << new_pos);
            } else {
                new_n = new_n & ~(1 << new_pos);
            }
        }
        return new_n;
    }

    public static void main(String[] args) {
        System.out.println(rotateRight(8, 1));
        System.out.println(rotateLeft(16, 1));
        System.out.println(rotateLeft(17, 2));
    }
}
