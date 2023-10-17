package edu.hw1;

public final class Task7 {
    private Task7() {
    }

    public static int getLenIn2Base(int n) {
        int lenBitN = 0;
        int newN = n;
        while (newN > 0) {
            lenBitN++;
            newN /= 2;
        }
        return lenBitN;
    }

    public static int rotateLeft(int n, int shift) {
        int newN = n;
        int lenBitN = getLenIn2Base(n);
        int newShift = shift % lenBitN;
        for (int i = 0; i < lenBitN; i++) {
            int newPos = (i + shift) % lenBitN;
            if ((n & (1 << i)) == (1 << i)) {
                newN = newN | (1 << newPos);
            } else {
                newN = newN & ~(1 << newPos);
            }
        }
        return newN;
    }

    public static int rotateRight(int n, int shift) {
        int newN = n;
        int lenBitN = getLenIn2Base(n);
        int newShift = lenBitN - shift % lenBitN;

        return rotateLeft(n, newShift);
    }
}
