import java.awt.*;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.List;
import java.util.regex.*;

public class PlayersFinder implements IPlayersFinder {

    // Function to get player's bounding box and area
    public int[] getPlayer(String[] photo, int x, int y, char item) {
        int[] result = new int[]{0, x, x, y, y}; // [area, Xmin, Xmax, Ymin, Ymax]
        photo = dfs(photo, x, y, item, result); // Modify photo during DFS
        return result;
    }

    // DFS function to find connected parts of the player
    public String[] dfs(String[] photo, int x, int y, char item, int[] result) {
        int rows = photo.length, cols = photo[0].length();

        // Base Case: Out-Of-Bounds, not team
        if (x < 0 || y < 0 || x >= rows || y >= cols || photo[x].charAt(y) != item) {
            return photo;
        }

        // Visited
        photo[x] = photo[x].substring(0, y) + '.' + photo[x].substring(y + 1);

        // Update Data
            // Area
            result[0]++;
            // Xmin
            result[1] = Math.min(result[1], x);
            // Xmax
            result[2] = Math.max(result[2], x);
            // Ymin
            result[3] = Math.min(result[3], y);
            // Ymax
            result[4] = Math.max(result[4], y);

        // Go all four directions
            // Up
            photo = dfs(photo, x - 1, y, item, result);
            // Down
            photo = dfs(photo, x + 1, y, item, result);
            // Left
            photo = dfs(photo, x, y - 1, item, result);
            // Right
            photo = dfs(photo, x, y + 1, item, result);

        return photo;
    }

    @Override
    public Point[] findPlayers(String[] photo, int teamIdentifier, int threshold) {
        List<Point> players = new ArrayList<>();
        int rows = photo.length;
        int cols = photo[0].length();
        char team = (char) (teamIdentifier + '0'); // Convert to character

        // Traverse the entire photo
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (photo[i].charAt(j) == team) {
                    // Get the bounding box and area
                    int[] player = getPlayer(photo, i, j, team);

                    // Check if it meets the minimum area requirement
                    if (player[0] * 4 >= threshold) { // Each square is 2x2, so area * 4
                        int centerX = (player[3] + player[4] + 1); // (Ymin + Ymax + 1)
                        int centerY = (player[1] + player[2] + 1); // (Xmin + Xmax + 1)
                        players.add(new Point(centerX, centerY));
                    }
                }
            }
        }

        // Sort players by (x, then y)
        players.sort(Comparator.comparingInt(p -> p.x * 1000 + p.y));

        return players.toArray(new Point[0]); // Convert List to Array
    }

    // Main method for input and output processing
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read board dimensions
        String[] dimensions = scanner.nextLine().split(",");
        int rows = Integer.parseInt(dimensions[0]);
        int cols = Integer.parseInt(dimensions[1]);

        // Read the photo
        String[] photo = new String[rows];
        for (int i = 0; i < rows; i++) {
            photo[i] = scanner.nextLine();
        }

        // Read the team identifier
        int teamIdentifier = scanner.nextInt();

        // Read the minimum area threshold
        int threshold = scanner.nextInt();

        // Process the image
        PlayersFinder finder = new PlayersFinder();
        Point[] players = finder.findPlayers(photo, teamIdentifier, threshold);

        // Print results in the required format
        System.out.println(Arrays.toString(players));

        scanner.close();
    }
}
