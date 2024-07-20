import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
Intuition behind this is that treat all initial 0s as root nodes and then its immediate neighbors as children
and so forth. So after each level order traversal, this means that new 1s are that much distanced from original 0.
 */
public class ZeroOneMatrix { //01-matrix
    public static int[][] updateMatrix(int[][] mat) {
        if(mat == null) return null; //null check

        Queue<int[]> q = new LinkedList<>(); //q stores co-ordinates of initial 0's in matrix
        int m = mat.length;
        int n = mat[0].length;

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) { //O(m*n) T.C
                if(mat[i][j] == 1) mat[i][j] = -1; //temporary state change to differentiate between
                    //actual 1s and the calculated distance 1s
                else q.add(new int[]{i,j}); //add 0s to the q
            }
        }

        int[][] directions = new int[][]{{0,1}, {1,0}, {0,-1}, {-1,0}};
        int distance = 1; //start the distance from 1 as already 0s are inserted into q and next insertion should be 1st

        while(!q.isEmpty()) { //O(m*n) T.C, O(m*n) S.C
            int size = q.size(); //need to maintain size to calculate distance at each level
            for(int i=0; i<size; i++) {
                int[] current = q.poll();
                for(int[] dir : directions) {
                    assert current != null;
                    int nr = current[0] + dir[0]; //calculate the neighbor
                    int nc = current[1] + dir[1];

                    if(nr >= 0 && nc >= 0 && nr < m && nc < n //boundary check
                            && mat[nr][nc] == -1) { //proceed only if the cell is -1 (which is original value 1)
                        q.add(new int[]{nr, nc}); //add these to q for further level traversal
                        mat[nr][nc] = distance; //change the value of cell to distance at current level
                    }
                }
            }
            distance++; //increase the distance at each size
        }
        return mat;
    }

    public static void main(String[] args) {
        int[][] mat1 = new int[][]{{1,0,1}, {0,0,1}, {1,1,1}, {1,1,1}, {0,1,1}};

        System.out.println("The distances of each value from 0 in the given matrix: "
                + Arrays.deepToString(mat1) + " is: \n" + Arrays.deepToString(updateMatrix(mat1)));
    }
}