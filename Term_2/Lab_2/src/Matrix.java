import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;

public class Matrix {
    private Box[][] matrix;
    private int n,m;

    public Matrix(int n, int m, Box[][] matrix) {
        this.n = n;
        this.m = m;
        this.matrix = matrix;
    }

    public Matrix(Box[][] matrix) {
        this.n = matrix.length;
        this.m = matrix[0].length;
        this.matrix = matrix;
    }

    public Matrix amount(Matrix a) {
        Box[][] result = new Box[a.n][a.m];
        for (int i = 0; i < a.n; i ++) {
            for (int j = 0; j < a.m; j ++) {
                result[i][j] = a.matrix[i][j].amount(this.matrix[i][j]);
            }
        }
        return new Matrix(result);
    }

    public Matrix multiply(Matrix a) {
        Box[][] result = new Box[this.n][a.m];
        for (int i = 0; i < this.n; i ++) {
            for (int j = 0; j < a.m; j ++) {
                for (int k = 0; k < Math.min(this.matrix[i].length, a.matrix.length); k++) {
                    result[i][j] = result[i][j].amount(this.matrix[i][k].multiply(a.matrix[k][j]));
                }
            }
        }
        return new Matrix(result);
    }

    public Matrix maidDiagonal() {
        Box[][] result = new Box[this.m][this.n];
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                result[j][i] = this.matrix[i][j];
            }
        }
        return new Matrix(result);
    }

    public Matrix sideDiagonal() {
        Box[][] result = new Box[this.m][this.n];
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                result[result.length - 1 - j][this.n - i - 1] = this.matrix[i][j];
            }
        }
        return new Matrix(result);
    }


    public Matrix lineVertical() {
        Box[][] result = new Box[this.n][this.m];
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                result[i][j] = this.matrix[i][this.m - j - 1];
            }
        }
        return new Matrix(result);
    }

    public Matrix lineHorizontal() {
        Box[][] result = new Box[this.n][this.m];
        for (int i = 0; i < this.n; i++) {
            System.arraycopy(this.matrix[this.n - 1 - i], 0, result[i], 0, this.m);
        }
        return new Matrix(result);
    }

    public Box determinate() {
        if (this.n == 2) {
            return this.matrix[0][0].multiply(this.matrix[1][1]).amount(this.matrix[0][1].multiply(this.matrix[1][0]).multiply(-1.0));
        }
        Box result = new Box(0.0);
        for (int k = 0; k < this.n; k++) {
            Box[][] b = new Box[this.n - 1][this.m - 1];
            for (int i = 1; i < this.n; i++) {
                int r = 0;
                for (int j = 0; j < this.m; j++) {
                    if (j != k) {
                        b[i - 1][r] = this.matrix[i][j];
                        r++;
                    }
                }
            }
            result = result.amount(this.matrix[0][k].multiply(k % 2 == 0 ? 1 : -1).multiply(new Matrix(b).determinate()));
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < m; j ++) {
                s.append(matrix[i][j]).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}

class Box {
    private double number = 0.0;

    public Box(double number) {
        this.number = number;
    }

    public Box(String s) {
        number = Double.parseDouble(s);
    }

    public Box amount(Box a) {
        return new Box(a.number + this.number);
    }

    public Box multiply(Box a) {
        return new Box(a.number * this.number);
    }

    public Box multiply(double a) {
        return new Box(a * this.number);
    }

    @Override
    public String toString() {
        if(number == (int) number)
            return String.format("%d",(int)number);
        else
            return String.format("%s",number);
    }
}
