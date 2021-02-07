import java.util.Vector;

interface Graph {
    int getSize();
    void create(int n);
    void insertRib(int x, int y, int z);
    void dfs(int v, boolean[] used);
    void dijkstra(int v, boolean[] used, int[] distance);
    void topologicalSort(int v, boolean[] used, Vector<Integer> result);
}

class MatrixGraph implements Graph {
    int[][] matrix;

    @Override
    public int getSize() {
        return matrix.length;
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
}


class ListGraph implements Graph {
    Vector<Vector<int[]>> list;

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public void create(int n) {
        list = new Vector<>(0);
        for (int i = 0; i < n; i++) {
            list.add(new Vector<>(0));
        }
    }

    @Override
    public void insertRib(int x, int y, int z) {
        list.get(x).add(new int[]{y, z});
        list.get(y).add(new int[]{x, z});
    }

    @Override
    public void dfs(int v, boolean[] used) {
        used[v] = true;
        for (int i = 0; i < list.get(v).size(); i++) {
            int j = list.get(v).get(i)[0];
            if (!used[j]) {
                dfs(j, used);
            }
        }
    }

    @Override
    public void dijkstra(int v, boolean[] used, int[] distance) {
        used[v] = true;
        int nextVertex = -1;
        for (int i = 0; i < list.size(); i++) {
            int j = list.get(v).get(i)[0];
            if (!used[j]) {
                distance[j] = Math.min(distance[j], distance[v] + list.get(v).get(i)[1]);
                if (nextVertex == -1) {
                    nextVertex = j;
                }
                if (distance[nextVertex] > distance[j]) {
                    nextVertex = j;
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
        for (int i = 0; i < list.size(); i++) {
            int j = list.get(v).get(i)[0];
            if (!used[j]) {
                topologicalSort(j, used, result);
            }
        }
        result.add(v);
    }
}

class Fabric {
    static Graph createGraph(Graphs type) {
        if (type == Graphs.LIST) {
            return new ListGraph();
        }
        if (type == Graphs.MATRIX) {
            return new MatrixGraph();
        }
        return null;
    }
}

enum Graphs {
    MATRIX, LIST;
}