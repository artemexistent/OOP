import java.util.Vector;

interface Graph {
    void create(int n);
    void insertRib(int x, int y, int z);
}

class MatrixGraph implements Graph {
    int matrix[][];

    @Override
    public void create(int n) {
        matrix = new int[n][n];
    }

    @Override
    public void insertRib(int x, int y, int z) {
        matrix[x][y] = z;
        matrix[y][x] = z;
    }
}


class ListGraph implements Graph {
    Vector<Vector<int[]>> list;

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