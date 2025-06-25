import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputTaker {

    Scanner sc = new Scanner(System.in);

    int getInt(int low, int high) {
        while (true) {
            if (!sc.hasNextInt()) {
                sc.nextLine();
                System.out.println("\u001B[31mInvalid input. Please enter an integer between " + low + " and " + high + "\u001B[0m");
                continue;
            }
            int in = sc.nextInt();
            sc.nextLine();
            if (in < low || in > high) {
                System.out.println("\u001B[31mInvalid input. Please enter an integer between " + low + " and " + high + "\u001B[0m");
                continue;
            }
            return in;
        }
    }

    public List<Object> getMove(int MaxRow, int MaxColumn) {
        while (true) {
            System.out.print("Enter your move (e.g., r 2 3 or f 0 1): ");
            String line = sc.nextLine().trim().toLowerCase();
            String[] parts = line.split("\\s+");

            // Must have exactly 3 tokens
            if (parts.length != 3) {
                System.out.println("\u001B[31mInvalid format. Use: r row col or f row col.\u001B[0m");
                continue;
            }

            // First token: exactly one character, 'r' or 'f'
            if (parts[0].length() != 1 ||
                    !(parts[0].charAt(0) == 'r' || parts[0].charAt(0) == 'f')) {
                System.out.println("\u001B[31mInvalid command. First token must be exactly 'r' or 'f'.\u001B[0m");
                continue;
            }
            char command = parts[0].charAt(0);

            // Next two tokens: integers?
            int row, col;
            try {
                row = Integer.parseInt(parts[1]);
                col = Integer.parseInt(parts[2]);
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mRow and column must be integers.\u001B[0m");
                continue;
            }

            // Check range
            if (row < 0 || row > MaxRow || col < 0 || col > MaxColumn) {
                System.out.printf("\u001B[31mRow and column must be between %d and %d.\u001B[0m%n", MaxRow, MaxColumn);
                continue;
            }

            // All checks passed!
            return Arrays.asList((Character) command, row, col);
        }
    }

}