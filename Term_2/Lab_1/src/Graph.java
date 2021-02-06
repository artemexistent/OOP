import java.util.Vector;

public interface Graph {

}

class MatrixGraph implements Graph {
    int matrix[][];
}

class ListGraph implements Graph {
    Vector<Vector<int[]>> list;
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