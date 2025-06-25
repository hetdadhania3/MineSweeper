import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Grid {


    
    private int rows, columns, mines, safebox;
    public char[][] UpperLayer; // we will be printing this layer for user
    private int[][] DownLayer; // we will use this layer for our calculations
    
    // making object for printer 
    
    @SuppressWarnings("unused")
    private Grid() {
        // making private default constructor so it is impossible to create object of
        // Grid without
        // passing parameters
    }
    
    
    //initializing Grid object with must need parameters   

    Grid(int rows, int columns, int mines) {
        this.rows = rows;
        this.columns = columns;
        this.mines = mines;
        this.printer = new Printing();
        this.safebox = (rows) * (columns) - mines;
        UpperLayer = new char[rows][columns];
        DownLayer = new int[rows][columns];
        settingBoardUp(); // now we we will set up both the layer using this method
        printer.printGrid();

    }

    // Private class printing , purpose of printing class is to print the grid 
    // We make it private so outer class can't print whenever they want 
    // Grid class will have control over Printing class 
    private class Printing {
       
        // printing class has only one method which is to print the grid 


        public void printGrid() {
            System.out.print("   ");

            // if number of rows of grid is greater then 10 then for spacing we have to this if conditions 
            if (rows >= 10) {
                System.out.print(" ");
            }


            for (int i = 0; i < columns; i++) {
                System.out.print(i + "  ");
            }

            System.out.println();
            System.out.print(" -");


            for (int i = 0; i < columns; i++) {
                System.out.print("---");
            }
            System.out.println();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {



                    if (j == 0) {
                        if (i < 10 && rows >= 10) { // again for spacing purpose 
                            System.out.print(" " + i + "| ");
                        } else {
                            System.out.print(i + "| ");
                        }
                    }


                    // this is only for colored output 
                    char ch = UpperLayer[i][j];
                    if (ch == '*') {
                        System.out.print("\u001B[31m" + ch + "\u001B[0m  "); // Red for mine
                    } else if (ch == 'F') {
                        System.out.print("\u001B[34m" + ch + "\u001B[0m  "); // Blue for flag
                    } else if (Character.isDigit(ch)) {
                        System.out.print("\u001B[32m" + ch + "\u001B[0m  "); // Green for revealed number
                    } else {
                        System.out.print(ch + "  ");
                    }

                }
                System.out.println();
            }
        }
    }

    //making printer object 
    Printing printer;

    private void settingBoardUp() {

        // First we will set upper layer , in initial state we will just put "-" sign at
        // every box

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                UpperLayer[i][j] = '-';
            }
        }

        // we will put mines randomly 
        Random rand = new Random();
        int TotalCell = (this.rows) * (this.columns);
        int TotalMines = this.mines;
        Set<Integer> MinePositions = new HashSet<>();
        while (MinePositions.size() < TotalMines) {
            MinePositions.add(rand.nextInt(TotalCell));
        }

        // Now we will place this mines in down layer which is for our calculation
        for (int pos : MinePositions) {
            int i = pos / columns;
            int j = pos % columns;
            DownLayer[i][j] = -1;
        }
    }


    
    public int revealBox(int row, int col, int DoWeHaveToPrint) {
        if (DownLayer[row][col] == -1) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (DownLayer[i][j] == -1) {
                        UpperLayer[i][j] = '*';
                    }
                }
            }
            printer.printGrid();
            return 0;
        }
        safebox--;
        int dx[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int dy[] = { -1, 0, 1, -1, 1, -1, 0, 1 };
        int neighbourMines = 0;
        for (int i = 0; i < 8; i++) {
            if (row + dx[i] >= 0 && row + dx[i] < rows && col + dy[i] >= 0 && col + dy[i] < columns
                    && DownLayer[row + dx[i]][col + dy[i]] == -1) {
                neighbourMines++;
            }
        }
        UpperLayer[row][col] = (char) (neighbourMines + '0');
        if (neighbourMines == 0) {
            for (int i = 0; i < 8; i++) {
                if (row + dx[i] >= 0 && row + dx[i] < rows && col + dy[i] >= 0 && col + dy[i] < columns
                        && DownLayer[row + dx[i]][col + dy[i]] != -1 && (UpperLayer[row + dx[i]][col + dy[i]] == '-'
                                || UpperLayer[row + dx[i]][col + dy[i]] == 'F')) {
                    revealBox(row + dx[i], col + dy[i], 0);
                }
            }
        }
        if (DoWeHaveToPrint == 1) {
            printer.printGrid();
        }
        if (safebox == 0)
            return 2;
        return 1;
    }

    public void flagger(int row, int col) {
        if (UpperLayer[row][col] == '-') {
            UpperLayer[row][col] = 'F';
        } else if (UpperLayer[row][col] == 'F') {
            UpperLayer[row][col] = '-';
        } else {
            System.out.println("You can't flag current Box");
            return;
        }
        printer.printGrid();
    }

}
