import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class History implements Serializable {

    Matrix[] matrix;
    int n;
    String operation;
    Matrix result;
    String resultSystem;

    public History(Matrix[] matrix, int n, String operation) {
        this.matrix = matrix;
        this.n = n;
        this.operation = operation;
    }

    public void setResult(Matrix result) {
        this.result = result;
    }

    public void setResultSystem(String resultSystem) {
        this.resultSystem = resultSystem;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        System.out.println("Our writeObject");
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        System.out.println("Our readObject");
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("<html>");
        if (n == 1) {
            s.append(operation).append("<br/>").append("<br/>");
            s.append(matrix[0]).append(" ");
            s.append("<br/>Result: <br/>").append(result.write());
        }
        if (n == 2) {
            s.append(operation).append("<br/>").append("<br/>");
            s.append(matrix[0].write(matrix[1]));
            s.append("<br/>Result: <br/>").append(result.write());
        }
        if (n == 3) {
            s.append(operation).append("<br/>").append("<br/>");
            s.append(matrix[0].writeSystem());
            s.append("<br/>Result: <br/>").append(resultSystem);
        }

        s.append("<br/><br/></html>");
        return s.toString();
    }
}
