import java.util.Vector;

interface Graph {
    int getSize();
    void create(int n);
    void insertRib(int x, int y, int z);
    void dfs(int v, boolean[] used);
}

class MatrixGraph implements Graph {
    int matrix[][];

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