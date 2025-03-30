import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {

    public int[] GetPlayer(StringBuilder[] photo, int x, int y, char item) {
        // Initialize tracking variables: area, Xmin, Xmax, Ymin, Ymax
        int[] result = new int[]{0, x, x, y, y}; // area, Xmin, Xmax, Ymin, Ymax
        dfs(photo, x, y, item, result);
        return result;
    }

    public void dfs(StringBuilder[] photo, int x, int y, char item, int[] result) {
        int m = photo.length;
        int n = photo[0].length();

        // Check out-of-bounds or already visited
        if (x < 0 || x >= m || y < 0 || y >= n || photo[x].charAt(y) != item) {
            return;
        }

        // Mark the cell as visited
        photo[x].setCharAt(y, '.');

        // Update area and boundaries
        result[0]++; // area
        result[1] = Math.min(result[1], x); // Xmin
        result[2] = Math.max(result[2], x); // Xmax
        result[3] = Math.min(result[3], y); // Ymin
        result[4] = Math.max(result[4], y); // Ymax

        // Explore four directions
        dfs(photo, x - 1, y, item, result); // Up
        dfs(photo, x + 1, y, item, result); // Down
        dfs(photo, x, y - 1, item, result); // Left
        dfs(photo, x, y + 1, item, result); // Right
    }



    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Define variables
        int rows, cols;
        int teamIdentifier;
        int thresholdArea;

        // Read Data

            // Get Photo Dimensions
            String Dimensions = scanner.nextLine();
            rows = Integer.parseInt(Dimensions.substring(0,Dimensions.indexOf(',')));
            cols = Integer.parseInt(Dimensions.substring(Dimensions.indexOf(',')+1));

            // Read Photo
            String[] Photo = new String[rows];
            for (int i = 0; i < rows; i++) {

            }

            // Read teamIdentifier
            teamIdentifier = scanner.nextInt();

            // Read Threshold Area
            thresholdArea = scanner.nextInt();


        // Analyze Data

    }
}