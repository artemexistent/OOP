import java.util.Scanner;

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

        System.out.println("1. Searching for connectivity components\n" + "2. Dijkstra’s algorithm\n");

        int key = scanner.nextInt();
        switch (key) {
            case 1:
                System.out.print("Number of components = " + searchComponents(graph));
                break;
            case 2:
                System.out.print("Enter the initial vertex:");
                int vertex = scanner.nextInt();;
                distanceSearch(graph, vertex);
                break;
        }

    }

    static int searchComponents(Graph graph) {
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

    static void distanceSearch(Graph graph, int vertex) {
        boolean[] used = new boolean[graph.getSize()];
        int[] distance = new int[used.length];
        for (int i = 0; i < used.length; i++) {
            used[i] = false;
            distance[i] = Integer.MAX_VALUE;
        }

        distance[vertex] = 0;

        graph.dijkstra(vertex, used, distance);
        System.out.printf("The distance from %d to all other vertices:\n", vertex);
        for (int j : distance) {
            System.out.print(j + " ");
        }
    }

}