import java.awt.*;
import java.util.*;

interface IPlayersFinder {
    /**
     * Search for players locations at the given photo
     * @param photo
     * Two dimension array of photo contents
     * Will contain between 1 and 50 elements, inclusive
     * @param team
     * Identifier of the team
     * @param threshold
     * Minimum area for an element
     * Will be between 1 and 10000, inclusive
     * @return
     * Array of players locations of the given team
     */
    java.awt.Point[] findPlayers(String[] photo, int team, int threshold);
}

class GraphPlayer {
    // input
    public String[] photo;
    public int rows, cols;

    // all cells are zeros
    public int[][] visited;
    // character because the photo is string
    public char team;
    public int threshold;
    public int x, y;



    // output
    public int Xmin = 50, Xmax = -1;
    public int Ymin = 50, Ymax = -1;
    public int playerArea = 0;
    public java.awt.Point center;


    // get the data of the player
    public GraphPlayer(String[] photo, int rows, int cols, int threshold, int team, int[][] visited, int x, int y)
    {
        this.photo = photo;
        this.threshold = threshold;
        this.team = (char) ('0' + team);
        this.rows = rows;
        this.cols = cols;
        this.visited = visited;
        this.x = x;
        this.y = y;
        this.center = null;
        GraphDepthSearch(x, y);
        if (playerArea * 4 >= threshold) {
            int centerX = (Xmin + Xmax) + 1;
            int centerY = (Ymin + Ymax) + 1;
            center = new java.awt.Point(centerX, centerY);
        }
    }

    /*
     *   1- check if player
     *   {
     *       if the point is player -> visited.place = 1
     *                                 area++
     *                                 check X's and Y's to get the min and max
     *       and then use recursion to loop => UP -> LEFT -> RIGHT -> DOWN
     *       the base case must be when not team then return null;
     *   }
     *
     *   2- Check Area Threshold
     *   {
     *       if correct -> calculate the center and return it
     *       if not -> return null
     *   }
     */

    public void GraphDepthSearch(int x, int y)
    {
        // Out-of-bounds check
        if (x < 0 || y < 0 || x >= rows || y >= cols) return;

        // Base case: If not the same team or already visited
        if (photo[x].charAt(y) != team || visited[x][y] == 1) return;

        // mark as visited
        visited[x][y] = 1;
        // increase area
        playerArea++;

        // check min and max
        Xmin = Math.min(Xmin, x);
        Xmax = Math.max(Xmax, x);
        Ymin = Math.min(Ymin, y);
        Ymax = Math.max(Ymax, y);

        // Shift Right
        GraphDepthSearch(x, y + 1);
        // Shift Down
        GraphDepthSearch(x + 1, y);
        // Shift Left
        GraphDepthSearch(x, y - 1);
        // Shift Up
        GraphDepthSearch(x - 1, y);

    }

    // return center
    public java.awt.Point getCenter()
    {
        return center;
    }

}


class Solution implements IPlayersFinder {

    @Override
    public java.awt.Point[] findPlayers(String[] photo, int team, int threshold)
    {
        int rows = photo.length;
        int cols = photo[0].length();
        int[][] visited = new int[rows][cols];

        int count = 0;
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                if (visited[i][j] == 0 && photo[i].charAt(j) == (char) ('0' + team))
                {
                    GraphPlayer GP = new GraphPlayer(photo, rows, cols, threshold, team, visited, i, j);
                    if (GP.getCenter() != null) count++;
                }
            }
        }

        java.awt.Point[] players = new java.awt.Point[count];
        int index = 0;
        visited = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (visited[i][j] == 0 && photo[i].charAt(j) == (char) ('0' + team)) {
                    GraphPlayer GP = new GraphPlayer(photo, rows, cols, threshold, team, visited, i, j);
                    if (GP.getCenter() != null) {
                        players[index++] = GP.getCenter();
                    }
                }
            }
        }

        for (int i = 0; i < players.length - 1; i++) {
            for (int j = 0; j < players.length - i - 1; j++) {
                if (players[j].y > players[j + 1].y ||
                        (players[j].y == players[j + 1].y && players[j].x > players[j + 1].x)) {
                    // Swap players[j] and players[j + 1]
                    Point temp = players[j];
                    players[j] = players[j + 1];
                    players[j + 1] = temp;
                }
            }
        }

        return players;
    }


    // Main
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read photo dimensions
        String[] dimensions = scanner.nextLine().split(",");
        int rows = Integer.parseInt(dimensions[0].trim());
        int cols = Integer.parseInt(dimensions[1].trim());

        // Read the photo
        String[] photo = new String[rows];
        for (int i = 0; i < rows; i++) {
            photo[i] = scanner.nextLine();
        }

        // Read the team identifier
        int teamIdentifier = Integer.parseInt(scanner.nextLine().trim());

        // Read the minimum area threshold
        int threshold = Integer.parseInt(scanner.nextLine().trim());

        // Process the image
        Solution finder = new Solution();
        Point[] players = finder.findPlayers(photo, teamIdentifier, threshold);

        // Print results in the required format: [(x1,y1), (x2,y2), ...]
        System.out.print("[");
        for (int i = 0; i < players.length; i++) {
            System.out.print("(" + players[i].y + ", " + players[i].x + ")");
            if (i < players.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        scanner.close();
    }

}