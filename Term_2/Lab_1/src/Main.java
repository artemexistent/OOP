import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("1. Matrix\n" + "2. List\n" + "Enter type graph: ");
        Graphs type = (scanner.nextInt() == 1 ? Graphs.MATRIX : Graphs.LIST);
        Graph graph = Fabric.createGraph(type);
        System.out.print("Enter number of vertices:");
        int n = scanner.nextInt();
        graph.create(n);
        System.out.print("Enter number of ribs:");
        int m = scanner.nextInt();
        System.out.printf("Enter %d ribs:\n", m);
        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt() - 1;
            int y = scanner.nextInt() - 1;
            int z = scanner.nextInt();
            graph.insertRib(x, y, z);
        }

        System.out.print("1. Searching for connectivity components\n" + "2. Dijkstra’s algorithm\n"
         + "3. Topological sort\n" + "4. Building a skeleton tree\n" + "5. Building a minimally skeletal tree\n");

        int key = scanner.nextInt();
        switch (key) {
            case 1 -> System.out.print("Number of components = " + numberComponents(graph));
            case 2 -> {
                System.out.print("Enter the initial vertex:");
                int vertex = scanner.nextInt();
                int[] distance = getDistance(graph, vertex);
                System.out.printf("The distance from %d to all other vertices:\n", vertex);
                for (int j : distance) {
                    System.out.print(j + " ");
                }
            }
            case 3 -> {
                int[] vertex = getSortVertex(graph);
                for (int i : vertex) {
                    System.out.print(i + " ");
                }
            }
            case 4 -> {
                Graph graphTree = getTree(graph);
                System.out.println("Skeleton tree:");
                System.out.println(graphTree);
            }
            case 5 -> {
                Graph graphMinTree = getMinTree(graph);

                System.out.println("Skeleton tree:");
                System.out.println(graphMinTree);
            }
        }

    }

    static int numberComponents(Graph graph) {
        int numberOfComponents = 0;
        boolean[] used = new boolean[graph.getSize()];
        for (int i = 0; i < used.length; i++) {
            used[i] = false;
        }

        for (int i = 0; i < used.length; i++) {
            if (!used[i]) {
                numberOfComponents++;
                graph.dfs(i, used);
            }
        }

        return numberOfComponents;
    }

    static int[] getDistance(Graph graph, int vertex) {
        boolean[] used = new boolean[graph.getSize()];
        int[] distance = new int[used.length];
        for (int i = 0; i < used.length; i++) {
            used[i] = false;
            distance[i] = Integer.MAX_VALUE;
        }

        distance[vertex] = 0;

        graph.dijkstra(vertex, used, distance);
        return distance;
    }

    static int[] getSortVertex(Graph graph) {
        boolean[] used = new boolean[graph.getSize()];
        for (int i = 0; i < used.length; i++) {
            used[i] = false;
        }

        Vector<Integer> result = new Vector<>(0);

        for (int i = 0; i < used.length; i++) {
            if (!used[i]) {
                graph.topologicalSort(i, used, result);
            }
        }

        int[] answer = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    static Graph getTree(Graph graph) {
        Graph graphTree = Fabric.createGraph(graph.getType());
        graphTree.create(graph.getSize());
        boolean[] used = new boolean[graph.getSize()];
        for (int i = 0; i < used.length; i++) {
            used[i] = false;
        }

        for (int i = 0; i < used.length; i++) {
            if (!used[i]) {
                graph.skeletonTree(i, used, graphTree);
            }
        }

        return graphTree;
    }

    static Graph getMinTree(Graph graph) {

        if (numberComponents(graph) != 1) {
            System.out.println("Impractical Building a Minimally Quilted Tree");
            return null;
        }

        Graph graphMinTree = Fabric.createGraph(graph.getType());
        graphMinTree.create(graph.getSize());
        boolean[] used = new boolean[graph.getSize()];
        int[] minRib = new int[used.length];
        int[] wayRib = new int[used.length];
        for (int i = 0; i < used.length; i++) {
            used[i] = false;
            minRib[i] = Integer.MAX_VALUE;
            wayRib[i] = -1;
        }
        minRib[0] = 0;
        graph.minSkeletonTree(used, graphMinTree, minRib, wayRib);


        return graphMinTree;
    }
}