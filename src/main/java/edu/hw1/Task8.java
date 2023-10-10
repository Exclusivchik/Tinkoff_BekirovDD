package edu.hw1;

public class Task8 {
    public static boolean knightBoardCapture(int[][] a){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(a[i][j] == 1){
                    if(i - 2 > -1 && j - 1 > -1 && a[i - 2][j - 1] == 1 ||
                        i - 2 > -1 && j + 1 <  8 && a[i - 2][j + 1] == 1 ||
                        i + 2 <  8 && j - 1 > -1 && a[i + 2][j - 1] == 1 ||
                        i + 2 <  8 && j + 1 <  8 && a[i + 2][j + 1] == 1 ||
                        i - 1 > -1 && j + 2 <  8 && a[i - 1][j + 2] == 1 ||
                        i - 1 > -1 && j - 2 > -1 && a[i - 1][j - 2] == 1 ||
                        i + 1 <  8 && j - 2 > -1 && a[i + 1][j - 2] == 1 ||
                        i + 1 <  8 && j + 2 <  8 && a[i + 1][j + 2] == 1){
                        return false;
                    }
                }

            }
        }
        return true;
    }

    public static void main(String[] args){

    }
}
