import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class Sudoku  {
    List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    private static final int SIZE = 9;
    private final int[][] board;
    private final int[][] board1;
    String zorlukTutucu;

    public Sudoku(String zorlukTutucu) {
        this.zorlukTutucu=zorlukTutucu;
        board = new int[SIZE][SIZE];
        board1 = new int[SIZE][SIZE];
        sudokuOlustur();
        copy();
        bosBirak();
    }
    private boolean gecerliMi(int x, int y, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (board[x][i] == num || board[i][y] == num) {
                return false;
            }
        }
        int kucuk3x = x - x % 3;
        int kucuk3y = y - y % 3;
        for (int i = kucuk3x; i < kucuk3x + 3; i++) {
            for (int j = kucuk3y; j < kucuk3y + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean kontrolluYazi() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (board[x][y] == 0) {
                    Collections.shuffle(nums);
                    for (int i = 0; i < SIZE; i++) {
                        int num = nums.get(i);
                        if (gecerliMi(x, y, num)) {
                            board[x][y] = num;
                            if (kontrolluYazi()) {
                                return true;
                            } else {
                                board[x][y] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private void sudokuOlustur() {
        kontrolluYazi();
    }

    private void sudokuOlusturBos() {
        kontrolluYazi();
    }

    private void copy() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {

                board1[x][y] = board[x][y];

            }
        }

    }

    private void bosBirak() {
        Random random = new Random();
        int bosAdet;
        switch (zorlukTutucu){

            case "kolay":
                bosAdet = 5;
                while (bosAdet > 0) {
                    int row = random.nextInt(9);
                    int col = random.nextInt(9);
                    while (board1[row][col] != 0) {
                        board1[row][col] = 0;
                        bosAdet--;
                    }
                }
                break;
            case "orta":
                bosAdet = 20;
                while (bosAdet > 0) {
                    int row = random.nextInt(9);
                    int col = random.nextInt(9);
                    while (board1[row][col] != 0) {
                        board1[row][col] = 0;
                        bosAdet--;
                    }
                }
                break;
            case "zor":
                bosAdet = 30;
                while (bosAdet > 0) {
                    int row = random.nextInt(9);
                    int col = random.nextInt(9);
                    while (board1[row][col] != 0) {
                        board1[row][col] = 0;
                        bosAdet--;
                    }
                }
                break;
        }
    }
    public int[][] getBoard1() {
        return board1;
    }
    public int[][] getBoard() {
        return board;
    }
    public String kutuya(int x , int y ){
        int a = 0;
        String sayi = null;
        a= board1[x][y];
        if(board1[x][y]==0){
            sayi=" ";
        }
        else {
            sayi=String.valueOf(a);}

        return sayi;
    }


    public void tahtaciz() {
        for (int i = 0; i < SIZE; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("---------------------");
            }
            for (int j = 0; j < SIZE; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}