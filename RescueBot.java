import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RescueBot {


    public static void main(String[] args) {
        System.out.println("=== RESCUE BOT ===\n");

        int[][] map = {
            {0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0}
        };

        int robotRow = 0;
        int robotCol = 0;

        int targetRow = 4;
        int targetCol = 4;


        Score scoreManager = new Score();

        int steps = 0;

        List<int[]> path = BFSPathTracker.findPath(map, robotRow, robotCol, targetRow, targetCol);

        for (int i = 0; i < path.size(); i++) {
            int[] pos = path.get(i);
            robotRow = pos[0];
            robotCol = pos[1];

            printGrid(map, robotRow, robotCol, targetRow, targetCol);

            System.out.println("Step: " + i);
                steps++;

System.out.print("Steps taken: " + steps);

scoreManager.losePoints();
                System.out.println(" Current score: " + scoreManager.getScore());

            }

        printGrid(map, robotRow, robotCol, targetRow, targetCol);
        if (robotRow == targetRow && robotCol == targetCol) {
            System.out.println("\nRescue Bot reached the target!");
        } else {
            System.out.println("\nRescue Bot could not reach the target.");
        }
        System.out.println("Total steps taken: " + steps);

boolean reachable = BFSPathFinder.canReach(map, robotRow, robotCol, targetRow, targetCol);
        System.out.println("Target is reachable: " + reachable);

         SaveManager.SaveScore(scoreManager.getScore());

         System.out.println("=== GAME OVER ===");
         System.out.println("Robot final position: (" + robotRow + ", " + robotCol + ")");
         System.out.println("Target position: (" + targetRow + ", " + targetCol + ")");
         System.out.println("Final score: " + scoreManager.getScore());
         
    }

    public static void printGrid(int[][] map, int robotRow, int robotCol, int targetRow, int targetCol) {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                if (row == robotRow && col == robotCol) {
                    System.out.print("R ");
                } else if (row == targetRow && col == targetCol) {
                    System.out.print("T ");
                } else if (map[row][col] == 1) {
                    System.out.print("# ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    
    static class BFSPathFinder {
        public static boolean canReach(int[][] map, int startRow, int startCol, int targetRow, int targetCol) {
            if (startRow == targetRow && startCol == targetCol) return true;
            int rows = map.length;
            int cols = map[0].length;
            boolean[][] visited = new boolean[rows][cols];
            java.util.Queue<int[]> q = new java.util.LinkedList<>();
            if (map[startRow][startCol] == 1) return false;
            q.add(new int[]{startRow, startCol});
            visited[startRow][startCol] = true;
            int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                for (int[] d : dirs) {
                    int nr = cur[0] + d[0];
                    int nc = cur[1] + d[1];
                    if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) continue;
                    if (visited[nr][nc]) continue;
                    if (map[nr][nc] == 1) continue; // wall
                    if (nr == targetRow && nc == targetCol) return true;
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                }
            }
            return false;
        }
    }

    public static boolean canMove(int[][] map, int row, int col) {
        if (row < 0 || row >= map.length) {
            return false;
        }
        if (col < 0 || col >= map[row].length) {
            return false;
        }
        return map[row][col] == 0;
    }

    public static void autoMove(int[][] map, int[] robot, int targetRow, int targetCol, Score scoreManager) {
        if (robot[0] < targetRow && canMove(map, robot[0] + 1, robot[1])) {
            robot[0]++;
            System.out.println("Autopilot: moving DOWN");
        } else if (robot[1] < targetCol && canMove(map, robot[0], robot[1] + 1)) {
            robot[1]++;
            System.out.println("Autopilot: moving RIGHT");
        } else if (robot[0] > targetRow && canMove(map, robot[0] - 1, robot[1])) {
            robot[0]--;
            System.out.println("Autopilot: moving UP");
        } else if (robot[1] > targetCol && canMove(map, robot[0], robot[1] - 1)) {
            robot[1]--;
            System.out.println("Autopilot: moving LEFT");
        } else {
            System.out.println("Autopilot: no valid move available.");
        }
       
    }
}
class Robot { 
    private int row;
    private int col;

    public Robot(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    public void moveDown() {
        row++;
    }
    public void moveUp() {
        row--;
    }
    public void moveRight() {
        col++;
    }
    public void moveLeft() {
        col--;
}

}

class Grid {
    private int[][] map;

    public Grid(int[][] map) {
        this.map = map;
    }

  public int[][] getMap() {
        return map;
    }

    public int getRows() {
        return map.length;
    }

    public int getCols() {
        return map[0].length;
    }
}

class PathFinder {
    public static boolean targetExists(int[][] map, int targetRow, int targetCol) {
        if (targetRow < 0 || targetRow >= map.length) {
            return false;
        }
        if (targetCol < 0 || targetCol >= map[targetRow].length) {
            return false;
        }
        return map[targetRow][targetCol] == 0;
    }
}

class Score {
    private int score;

    public Score() {
        score = 100;
    }

    public int losePoints() {
        score--;
        return score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

class SaveManager {

    public static void SaveScore(int score) {
        try {
            PrintWriter writer = new PrintWriter(new File("score.txt"));
            writer.println(score);
            writer.close();
            System.out.println("Game saved!");
        }
        catch (Exception e) {
            System.out.println("Error saving game: " + e.getMessage());
        }
    }
}

class BFSPathTracker {

    private static class Node {

        int r, c;

        Node parent;

        Node(int r, int c, Node parent) {

            this.r = r;

            this.c = c;

            this.parent = parent;

        }

    }

    public static List<int[]> findPath(int[][] map, int sr, int sc, int tr, int tc) {

        boolean[][] visited = new boolean[map.length][map[0].length];

        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(sr, sc, null));

        visited[sr][sc] = true;

        int[] dr = {1, -1, 0, 0};

        int[] dc = {0, 0, 1, -1};

        Node end = null;

        while (!queue.isEmpty()) {

            Node current = queue.poll();

            if (current.r == tr && current.c == tc) {

                end = current;

                break;

            }

            for (int i = 0; i < 4; i++) {

                int nr = current.r + dr[i];

                int nc = current.c + dc[i];

                if (nr >= 0 && nr < map.length &&

                    nc >= 0 && nc < map[0].length &&

                    map[nr][nc] == 0 &&

                    !visited[nr][nc]) {

                    visited[nr][nc] = true;

                    queue.add(new Node(nr, nc, current));

                }

            }

        }

        List<int[]> path = new ArrayList<>();

        while (end != null) {

            path.add(0, new int[]{end.r, end.c});

            end = end.parent;

        }

        return path;

    }

}

