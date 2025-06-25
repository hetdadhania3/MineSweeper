import java.util.List;

public class Main {
    public static void main(String[] args) {

        // input object will help us in taking input of our requirements
        InputTaker input = new InputTaker();

        // Setting up the game
        System.out.println("\u001B[34mWelcome in MineSweeper\u001B[0m");
        int rows, columns, mines; // number of rows and columns grid will have

        
        System.out.println("Enter the number of Rows");
        rows = input.getInt(1, 80); // Number of rows must be 1 to 80

        
        System.out.println("Enter the number of Columns");
        columns = input.getInt(1, 80); // Number of column must be 1 to 80


        // Number of Mines 
        System.out.println("Enter the number of Mines");
        mines = input.getInt(1, rows * columns - 1); // There should be at least 1 empty box




        // Instructions 
        System.out.println("\u001B[34mFor revealing the box use format \"r row col\" and for flagging use \"f row col\"\u001B[0m");
        System.out.println("\u001B[34mHere you go:\u001B[0m");
        
        
        
        // creating grid object
        Grid grid = new Grid(rows, columns, mines);

        while (true) {

            //Getting move which is entered by the Player 
            // Functions are build in such a way that it always check the input is valid or not 
            List<Object> move = input.getMove(rows-1, columns-1);
            char cmd = (Character) move.get(0);
            int row = (Integer) move.get(1);
            int col = (Integer) move.get(2);
            System.out.printf("Command=%c, row=%d, col=%d%n", cmd, row, col);



            // doing actions as per the move given by use 
            // if cmd is f it means we have to flag and reveal the grid at cmd r 

            if (cmd == 'f') {
                // flagging the grid at [row,col]
                grid.flagger(row, col);
            } else {

                //cmd is r 

                if (grid.UpperLayer[row][col] == '-') {

                    // if grid at [row,col] is not revealed already then we have to reveal it 
                    // we will call revealBox function which will reveal the mines and print it onces 
                    int isSafe = grid.revealBox(row, col,1);


                    // performing further operations on basis of requirements 
                    if (isSafe == 0) {
                        System.out.println("\u001B[31mBoom! You hit a mine. Game over.\u001B[0m");
                        break;
                    } else if(isSafe==1){
                        System.out.println("\u001B[32mSafe move, keep going!\u001B[0m");
                    }else{
                        System.out.println("\u001B[32m Congratulations! You cleared the board.\u001B[0m");
                        break;
                    }
                }else if(grid.UpperLayer[row][col] == 'F'){


                    // If grid[row,col] is flagged we can't reveal it 

                    System.out.println("\u001B[31mYou can't reveal a flagged box\u001B[0m");
                }else{
                    System.out.println("\u001B[31mThis box is already revealed.\u001B[0m");
                }
            }
        }
    }
}