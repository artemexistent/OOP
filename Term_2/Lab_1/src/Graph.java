import java.util.Vector;

interface Graph {
    int getSize();
    Graphs getType();
    void create(int n);
    void insertRib(int x, int y, int z);

    /**
     * Depth rounds
     * @param v current peak
     * @param used an array of used nodes
     */
    void dfs(int v, boolean[] used);

    /**
     * Dijkstra's algorithm
     * @param v current peak
     * @param used an array of used nodes
     * @param distance distance array
     */
    void dijkstra(int v, boolean[] used, int[] distance);

    /**
     * topological sorting
     * @param v current peak
     * @param used an array of used nodes
     * @param result an array of sorted nodes
     */
    void topologicalSort(int v, boolean[] used, Vector<Integer> result);

    /**
     * sub-tree construction
     * @param v current peak
     * @param used an array of used nodes
     * @param graphTree sub-tree
     */
    void skeletonTree(int v, boolean[] used, Graph graphTree);

    /**
     * building a minimum subtree
     * @param used an array of used nodes
     * @param graphMinTree minimum subtree
     * @param minRib an array of minimal rebbes from vertices
     * @param wayRib minimum path array
     */
    void minSkeletonTree(boolean[] used, Graph graphMinTree,int[] minRib, int[] wayRib);
}

/**
 * Creation of the graph of the class that was specified
 */
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

/**
 * graph classes
 */
enum Graphs {
    MATRIX, LIST;
}