import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FloodFill {
    //BFS
    public static int[][] floodFillBFS(int[][] image, int sr, int sc, int color) {
        if(image == null || image[sr][sc] == color) return image; //if image is null or if the to-be-processed element
        //is already the same color as to be changed, we can just return the image itself.

        int originalColor = image[sr][sc]; //store the original color of the to-be-processed grid.
        int[][] directions = new int[][]{{0,1}, {1,0}, {0,-1}, {-1,0}};

        Queue<Integer> q = new LinkedList<>(); //O(m*n) S.C
        q.add(sr); //add row
        q.add(sc); //add col

        while(!q.isEmpty()) { //O(m*n) T.C, single traversal of all nodes in BFS
            int r = q.poll();
            int c = q.poll();
            image[r][c] = color; //change the color of popped row and col

            for(int[] dir: directions) { //traverse the 4 directions
                int nr = r + dir[0];
                int nc = c + dir[1];

                if(nr >= 0 && nc >= 0 && nr < image.length && nc < image[0].length //boundary check
                        && image[nr][nc] == originalColor) { //if neighbor grid is of same original color
                    q.add(nr); //add these to the q to be processed in BFS
                    q.add(nc);
                }
            }
        }
        return image;
    }

    //DFS
    public static int[][] floodFillDFS(int[][] image, int sr, int sc, int color) { //O(m*n) T.C, O(1) S.C
        if(image == null || image[sr][sc] == color) return image; //if image is null or if the to-be-processed element
        //is already the same color as to be changed, we can just return the image itself.

        int originalColor = image[sr][sc]; //store original color of the grid
        int[][] directions = new int[][]{{0,1}, {1,0}, {0,-1}, {-1,0}};

        dfs(image, sr, sc, originalColor, color, directions); //call dfs
        return image;
    }

    static void dfs(int[][] image, int r, int c, int original, int color, int[][] directions) {
        //base
        if(r < 0 || c < 0 || r == image.length || c == image[0].length || //boundary check
                image[r][c] != original) return; //do not proceed if current grid is not same as original color

        //logic
        image[r][c] = color; //change the current grid color

        //recursion
        for(int[] dir : directions) { //traverse the directions
            int nr = r + dir[0];
            int nc = c + dir[1];

            dfs(image, nr, nc, original, color, directions); //recursive call of dfs on changed row and col
        }
    }

    public static void main(String[] args) {
        int[][] image = {{1,0,1}, {2,1,1}, {0,1,0}, {0,0,1}};
        int sr = 2;
        int sc = 1;
        int color = 2;

        System.out.println("Changed image of " + Arrays.deepToString(image) +
                " using BFS after flood fill is: " + Arrays.deepToString(floodFillBFS(image, sr, sc, color)));

        System.out.println("Changed image of " + Arrays.deepToString(image) +
                " using DFS after flood fill is: " + Arrays.deepToString(floodFillDFS(image, sc, sr, color)));
    }
}