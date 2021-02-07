import java.util.Vector;

class MatrixGraph implements Graph {
    int[][] matrix;

    @Override
    public int getSize() {
        return matrix.length;
    }

    @Override
    public Graphs getType() {
        return Graphs.MATRIX;
    }

    @Override
    public void create(int n) {
        matrix = new int[n][n];
    }

    @Override
    public void insertRib(int x, int y, int z) {
        matrix[x][y] = z;
        matrix[y][x] = z;
    }

    @Override
    public void dfs(int v, boolean[] used) {
        used[v] = true;

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[v][i] != 0 && !used[i]) {
                dfs(i, used);
            }
        }

    }

    @Override
    public void dijkstra(int v, boolean[] used, int[] distance) {
        used[v] = true;
        int nextVertex = -1;
        for (int i = 0; i < matrix.length; i++) {
            if (!used[i] && matrix[v][i] != 0) {
                distance[i] = Math.min(distance[i], distance[v] + matrix[v][i]);
                if (nextVertex == -1) {
                    nextVertex = i;
                }
                if (distance[nextVertex] > distance[i]) {
                    nextVertex = i;
                }
            }
        }
        if (nextVertex != -1) {
            dijkstra(nextVertex, used, distance);
        }

    }

    @Override
    public void topologicalSort(int v, boolean[] used, Vector<Integer> result) {
        used[v] = true;
        for (int i = 0; i < matrix.length; i++) {
            if (!used[i] && matrix[v][i] != 0) {
                topologicalSort(i, used, result);
            }
        }
        result.add(v);
    }

    @Override
    public void skeletonTree(int v, boolean[] used, Graph graphTree) {
        used[v] = true;
        for (int i = 0; i < matrix.length; i++) {
            if (!used[i] && matrix[v][i] != 0) {
                graphTree.insertRib(v, i, matrix[v][i]);
                skeletonTree(i, used, graphTree);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringGraph = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++){
                if (matrix[i][j] != 0) {
                    stringGraph.append(i).append(" ").append(j).append(" ").append(matrix[i][j]).append("\n");
                }
            }
        }
        return stringGraph.toString();
    }

}
