import java.util.Vector;

interface Graph {
    int getSize();
    Graphs getType();
    void create(int n);
    void insertRib(int x, int y, int z);
    void dfs(int v, boolean[] used);
    void dijkstra(int v, boolean[] used, int[] distance);
    void topologicalSort(int v, boolean[] used, Vector<Integer> result);
    void skeletonTree(int v, boolean[] used, Graph graphTree);
    void minSkeletonTree(boolean[] used, Graph graphMinTree,int[] minRib, int[] wayRib);
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