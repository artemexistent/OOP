import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("1. Matrix\n" + "2. List\n" + "Enter type graph: ");
        Graphs type = (scanner.nextInt() == 1 ? Graphs.MATRIX : Graphs.LIST);
        Graph graph = Fabric.createGraph(type);


    }

}