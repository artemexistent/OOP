import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.CoderResult;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The unit tests each function from {@link Main}
 */

class UnitTest {

    Graph graph;

    @org.junit.jupiter.api.Test
    void main() {
        for (int file = 1; file <= 10; file++ ) {
            try {
                String fileName = String.format("D:\\KNU\\git\\OOP\\Term_2\\Lab_1\\Test\\input\\%04d.txt", file);
                Scanner scanner = new Scanner(Path.of(fileName));
                Graphs type = (scanner.nextInt() == 1 ? Graphs.MATRIX : Graphs.LIST);
                graph = Fabric.createGraph(type);
                int n = scanner.nextInt();
                graph.create(n);
                int m = scanner.nextInt();
                for (int i = 0; i < m; i++) {
                    int x = scanner.nextInt() - 1;
                    int y = scanner.nextInt() - 1;
                    int z = scanner.nextInt();
                    graph.insertRib(x, y, z);
                }
                int key = scanner.nextInt();

                switch (key) {
                    case 1 -> numberComponents(file);
                    case 2 -> {
                        int vertex = scanner.nextInt() - 1;
                        distanceSearch(vertex, file);
                    }
                    case 3 -> sortVertex(file);
                    case 4 -> buildTree(file);
                    case 5 -> minBuildTree(file);
                }

            } catch (IOException ignore) {

            }
        }
    }

    void numberComponents(int file) {
        String fileName = String.format("D:\\KNU\\git\\OOP\\Term_2\\Lab_1\\Test\\output\\%04d.txt", file);
        try {
            Scanner scanner = new Scanner(Path.of(fileName));
            Assertions.assertEquals(scanner.nextInt(), Main.numberComponents(graph));
        } catch (IOException ignore) {
        }
    }

    void distanceSearch(int v, int file) {
        String fileName = String.format("D:\\KNU\\git\\OOP\\Term_2\\Lab_1\\Test\\output\\%04d.txt", file);
        try {
            Scanner scanner = new Scanner(Path.of(fileName));
            int[] distance = new int[graph.getSize()];
            for (int i = 0; i < distance.length; i++) {
                distance[i] = scanner.nextInt();
            }
            Assertions.assertArrayEquals(Main.getDistance(graph, v), distance);
        } catch (IOException ignore) {

        }
    }

    void sortVertex(int file) {
        String fileName = String.format("D:\\KNU\\git\\OOP\\Term_2\\Lab_1\\Test\\output\\%04d.txt", file);
        try {
            Scanner scanner = new Scanner(Path.of(fileName));
            int[] vertex = new int[graph.getSize()];
            for (int i = 0; i < graph.getSize(); i++) {
                vertex[i] = scanner.nextInt();
            }
            Assertions.assertArrayEquals(vertex, Main.getSortVertex(graph));
        } catch (IOException ignore) {

        }
    }

    void buildTree(int file) {
        String fileName = String.format("D:\\KNU\\git\\OOP\\Term_2\\Lab_1\\Test\\output\\%04d.txt", file);
        try {
            Scanner scanner = new Scanner(Path.of(fileName));
            Graph graphTree = Main.getTree(graph);
            StringBuilder output = new StringBuilder();
            while (scanner.hasNext()) {
                output.append(scanner.nextLine()).append("\n");
            }
//            System.out.println(graphTree.toString());
            Assertions.assertEquals(output.toString(), graphTree.toString());
        } catch (IOException ignore) {
        }
    }

    void minBuildTree(int file) {
        String fileName = String.format("D:\\KNU\\git\\OOP\\Term_2\\Lab_1\\Test\\output\\%04d.txt", file);
        try {
            Scanner scanner = new Scanner(Path.of(fileName));
            Graph graphMinTree = Main.getMinTree(graph);
            StringBuilder output = new StringBuilder();
            while (scanner.hasNext()) {
                output.append(scanner.nextLine()).append("\n");
            }
            if (graphMinTree != null) {
               Assertions.assertEquals(output.toString(), graphMinTree.toString());
            }

        } catch (IOException ignore) {
        }
    }
}