import javax.swing.*;
import java.io.*;
import java.util.Vector;

/**
 * This class corresponds to a matrix and all operations on them take place in it
 */

public class Matrix  implements Serializable{
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

    public Matrix(Box box) {
        this.n = this.m = 1;
        matrix = new Box[][]{{box}};
    }

    public Matrix(Matrix matrix) {
        this.n = matrix.matrix.length;
        this.m = matrix.matrix[0].length;
        this.matrix = matrix.matrix;
    }

    /**
     * addition of two matrices
     * @param a
     * @return result of addition
     */
    public Matrix amount(Matrix a) {
        Box[][] result = new Box[a.n][a.m];
        for (int i = 0; i < a.n; i ++) {
            for (int j = 0; j < a.m; j ++) {
                result[i][j] = a.matrix[i][j].amount(this.matrix[i][j]);
            }
        }
        return new Matrix(result);
    }

    /**
     * multiply of two matrix
     * @param a
     * @return result of multiply
     */
    public Matrix multiply(Matrix a) {
        Box[][] result = new Box[this.n][a.m];
        for (int i = 0; i < this.n; i ++) {
            for (int j = 0; j < a.m; j ++) {
                for (int k = 0; k < Math.min(this.matrix[i].length, a.matrix.length); k++) {
                    result[i][j] = this.matrix[i][k].multiply(a.matrix[k][j]);
                }
            }
        }
        return new Matrix(result);
    }


    /**
     * matrix mapping relative to the main diagonal
     * @return result of mapping
     */
    public Matrix maidDiagonal() {
        Box[][] result = new Box[this.m][this.n];
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                result[j][i] = this.matrix[i][j];
            }
        }
        return new Matrix(result);
    }

    /**
     * matrix mapping relative to the side diagonal
     * @return result of mapping
     */
    public Matrix sideDiagonal() {
        Box[][] result = new Box[this.m][this.n];
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                result[result.length - 1 - j][this.n - i - 1] = this.matrix[i][j];
            }
        }
        return new Matrix(result);
    }

    /**
     * matrix mapping relative to the vertical line
     * @return result of mapping
     */
    public Matrix lineVertical() {
        Box[][] result = new Box[this.n][this.m];
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                result[i][j] = this.matrix[i][this.m - j - 1];
            }
        }
        return new Matrix(result);
    }

    /**
     * matrix mapping relative to the horizontal line
     * @return result of mapping
     */
    public Matrix lineHorizontal() {
        Box[][] result = new Box[this.n][this.m];
        for (int i = 0; i < this.n; i++) {
            System.arraycopy(this.matrix[this.n - 1 - i], 0, result[i], 0, this.m);
        }
        return new Matrix(result);
    }

    /**
     * finding determinate
     * @return determinate of matrix
     */
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

    /**
     * the Gaussian method for finding the solution of the system
     * @param answer solution of the system
     * @return
     */
    int gauss(Vector<Box> answer) {
        int n = this.n;
        int m = this.m - 1;
        double EPS = 1.0E-3;
        int[] where = new int[m];
        for (int i  = 0; i < m; i ++) {
            where[i] = -1;
        }

        for (int col = 0, row = 0; col < m && row < n; ++col) {
            int sel = row;
            for (int i=row; i<n; ++i)
                if (this.matrix[i][col].abs().swap(this.matrix[sel][col].abs()))
                    sel = i;
            if (!this.matrix[sel][col].abs().swap(new Box(EPS)))
                continue;
            for (int i=col; i<=m; ++i)
                swap (this.matrix[sel][i], this.matrix[row][i]);
            where[col] = row;

            for (int i=0; i<n; ++i)
                if (i != row) {
                    double c = this.matrix[i][col].del(this.matrix[row][col]);
                    for (int j=col; j<=m; ++j)
                        this.matrix[i][j] = this.matrix[i][j].minus(this.matrix[row][j].multiply(c));
                }
            ++row;
        }

        for (int i = 0; i < m; i ++) {
            answer.add(new Box(0));
        }

        for (int i=0; i<m; ++i)
            if (where[i] != -1)
                answer.set(i, new Box(this.matrix[where[i]][m].del(this.matrix[where[i]][i])));
        for (int i=0; i<n; ++i) {
            Box sum = new Box(0);
            for (int j=0; j<m; ++j)
                sum = sum.amount(answer.get(j).multiply(this.matrix[i][j]));
            if ((sum.minus(this.matrix[i][m])).abs().swap(new Box(EPS)))
                return 0;
        }

        for (int i=0; i<m; ++i)
            if (where[i] == -1)
                return Integer.MAX_VALUE;
        return 1;


    }

    /**
     * Finding the matrix rank
     * @return matrix rank
     */
    int rank() {
        int rank = Math.max(n, m);
        boolean[] line_used = new boolean[n];
        Box EPS = new Box(1.0E-3);

        for (int i=0; i<m; ++i) {
            int j;
            for (j=0; j<n; ++j)
                if (!line_used[j] && this.matrix[i][j].abs().swap(EPS))
                    break;
            if (j == n)
                --rank;
            else {
                line_used[j] = true;
                for (int p=i+1; p<m; ++p)
                    this.matrix[j][p] = this.matrix[j][p].del1(this.matrix[j][i]);
                for (int k=0; k<n; ++k)
                    if (k != j && this.matrix[k][i].abs().swap(EPS))
                        for (int p=i+1; p<m; ++p)
                            this.matrix[k][p] = this.matrix[k][p].minus(this.matrix[j][p].multiply(this.matrix[k][i]));
            }
        }
        return rank;
    }

    private void swap(Box matrix, Box matrix1) {
        Box c = matrix;
        matrix = matrix1;
        matrix1 = c;
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

    public String write() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < m; j ++) {
                s.append(matrix[i][j]).append(" ");
            }
            s.append("<br/>");
        }
        return s.toString();
    }

    public String write(Matrix a) {
        StringBuilder s = new StringBuilder("<pre>");
        for (int i = 0; i < n; i ++) {
            s.append("(");
            for (int j = 0; j < m; j ++) {
                s.append(matrix[i][j]).append(" ");
            }


            if (i == n - 1) {
                s.append(") and (");
            } else {
                s.append(")     (");
            }

            for (int j = 0; j < a.m; j ++) {
                s.append(a.matrix[i][j]).append(" ");
            }
            s.append(")<br/>");
        }
        return s.toString();
    }

    public String writeSystem() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < m; j++) {
                    s.append(matrix[i][j]).append(" ");
                    s.append((j < m - 1 ? "x" + (j + 1) + (j == m - 2 ? " = " : " + ") : ""));
            }
            s.append("<br/>");
        }
        return s.toString();
    }


}

/**
 * this class was created to store different types of numbers and operations on them
 */


class Box implements Serializable{
    public boolean isNull;
    private double number = 0.0;

    public Box(double number) {
        this.number = number;
        isNull = (number == 0);
    }

    public Box(String s) {
        number = Double.parseDouble(s);
    }

    /**
     * addition of two numbers
     * @param a
     * @return result of addition
     */
    public Box amount(Box a) {
        return new Box(a.number + this.number);
    }

    /**
     * multiply of two numbers
     * @param a
     * @return result of multiply
     */
    public Box multiply(Box a) {
        return new Box(a.number * this.number);
    }
    /**
     * multiply of two numbers
     * @param a
     * @return result of multiply
     */
    public Box multiply(double a) {
        return new Box(a * this.number);
    }

    public Box abs() {
        return new Box(Math.abs(number));
    }

    public boolean swap(Box a) {
        if (this.number > a.number) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if(number == (int) number)
            return String.format("%d",(int)number);
        else
            return String.format("%s",number);
    }

    /**
     * division of two numbers
     * @param matrix
     * @return result of division
     */
    public double del(Box matrix) {
        return this.number / matrix.number;
    }

    /**
     * division of two numbers
     * @param matrix
     * @return result of division
     */
    public Box del1(Box matrix) {
        return new Box(this.number / matrix.number);
    }


    /**
     * subtracting two numbers
     * @param matrix
     * @return result of subtracting
     */
    public Box minus(Box matrix) {
        return new Box(this.number - matrix.number);
    }

}
