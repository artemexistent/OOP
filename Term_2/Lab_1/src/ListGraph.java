import java.util.Vector;

/**
 * The class of the graph stored by the adjacency list
 * @see Graph
 */


class ListGraph implements Graph {
    Vector<Vector<int[]>> list;

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public Graphs getType() {
        return Graphs.LIST;
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
        Vector<Integer> nextVertex = new Vector<>(0);
        for (int i = 0; i < list.get(v).size(); i++) {
            int j = list.get(v).get(i)[0];
            if (!used[j]) {
                distance[j] = Math.min(distance[j], distance[v] + list.get(v).get(i)[1]);
                nextVertex.add(j);
            }
        }
        while (nextVertex.size() != 0) {
            int j = 0;
            for (int i = 1; i < nextVertex.size(); i++) {
                if (distance[nextVertex.get(i)] < distance[nextVertex.get(j)]) {
                    j = i;
                }
            }
            if (!used[nextVertex.get(j)]) {
                dijkstra(nextVertex.get(j), used, distance);
            }
            nextVertex.remove(j);
        }
    }

    @Override
    public void topologicalSort(int v, boolean[] used, Vector<Integer> result) {
        used[v] = true;
        for (int i = 0; i < list.get(v).size(); i++) {
            int j = list.get(v).get(i)[0];
            if (!used[j]) {
                topologicalSort(j, used, result);
            }
        }
        result.add(v);
    }

    @Override
    public void skeletonTree(int v, boolean[] used, Graph graphTree) {
        used[v] = true;
        for (int i = 0; i < list.get(v).size(); i++) {
            int j = list.get(v).get(i)[0];
            if (!used[j]) {
                graphTree.insertRib(v, j, list.get(v).get(i)[1]);
                skeletonTree(j, used, graphTree);
            }
        }
    }

    @Override
    public void minSkeletonTree(boolean[] used, Graph graphMinTree, int[] minRib, int[] wayRib) {
        for (int v = 0; v < list.size(); v++) {
            int nextVertex = -1;
            for (int i = 0; i < list.get(v).size(); i++) {
                int[] j = list.get(v).get(i);
                if (!used[j[0]]) {
                    if (nextVertex == -1) {
                        nextVertex = j[0];
                    }
                    if (minRib[j[0]] < minRib[nextVertex]) {
                        nextVertex = j[0];
                    }
                }
            }
            if (nextVertex != -1) {

                used[nextVertex] = true;

                if (wayRib[nextVertex] != -1) {
                    int z = 0;
                    for (int[] i : list.get(nextVertex)) {
                        if (i[0] == wayRib[nextVertex]) {
                            z = i[1];
                            break;
                        }
                    }
                    graphMinTree.insertRib(nextVertex, wayRib[nextVertex], z);
                }

                for (int i = 0; i < list.get(nextVertex).size(); i++) {
                    if (list.get(nextVertex).get(i)[1] < minRib[list.get(nextVertex).get(i)[0]]) {
                        minRib[list.get(nextVertex).get(i)[0]] = list.get(nextVertex).get(i)[1];
                        wayRib[list.get(nextVertex).get(i)[0]] = nextVertex;
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringGraph = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            for (int[] j : list.get(i)){
                stringGraph.append(i).append(" ").append(j[0]).append(" ").append(j[1]).append("\n");
            }
        }
        return stringGraph.toString();
    }
}