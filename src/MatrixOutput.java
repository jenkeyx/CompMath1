import java.io.OutputStream;
import java.io.PrintWriter;

public class MatrixOutput {
    PrintWriter writer;

    public MatrixOutput(OutputStream outputStream) {
        this.writer = new PrintWriter(outputStream);
    }

    public void printSystem(Matrix matrix){
        double[][] a = matrix.getA();
        int n = matrix.getSize();
        writer.println("Система решений");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <=n ; j++) {
                if (j == n){
                    writer.print("="+ matrix.getB()[i]);
                }else {
                    if (j!=0){
                        writer.print("+");
                    }
                    writer.print(a[i][j]+ "* X" + (j+1));
                }
            }
            writer.print("\n");
        }
        writer.flush();
    }
    public void writeTableHeader(){
        writer.print("X\t\t\t\t\t|");
        writer.print("\t\tПогрешность\n");
        writer.print("----------------------------------------------------\n");
        writer.flush();
    }
    public void writeTableLine(double x, double dif) {
        writer.print(x + "\t\t" + dif +"\n");
        writer.flush();
    }

}
