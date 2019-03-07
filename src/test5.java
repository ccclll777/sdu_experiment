import java.text.DecimalFormat;
import java.util.Queue;

public class test5 {
        static int matrix[][] = {{0,1,1,0,0,0,0,0},
                            {0,0,0,1,1,0,0,0},
                            {0,0,0,0,0,1,1,0},
                            {1,0,0,0,0,0,0,1},
                            {1,0,0,0,0,0,0,1},
                            {0,0,0,0,0,0,1,0},
                            {0,0,0,0,0,1,0,0},
                            {1,0,0,0,0,0,0,0}};
//    static int matrix[][] = {{0, 1, 0, 1},
//            {0, 0, 0, 1},
//            {1, 1, 0, 0},
//            {0, 0, 1, 0}};
    static double s = 0.8;
    static double rank[] = new double[8];
    static double rank2[] = new double[8];
    static int count[] = new int[8];

    public static void init() {
        DecimalFormat df = new DecimalFormat("#0.000000");
        for (int i = 0; i < matrix.length; i++) {
            rank[i] = 0.125;
            rank2[i] = 0;
            count[i] = 0;
            System.out.print(df.format(rank[i]) + "\t\t\t");

        }
        System.out.println();
    }

    public static void pagerank() {
        DecimalFormat df = new DecimalFormat("#0.000000");

        for (int k = 0; k < 20; k++) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[i][j] == 1) {
                        count[i]++;
                    }
                }

            }
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[i][j] == 1) {
                        rank2[j] += rank[i] / count[i];
                    }
                }

            }
            for (int i = 0; i < matrix.length; i++) {
                rank2[i] =rank2[i]*s +0.2/matrix.length;
            }
            for (int i = 0; i < matrix.length; i++) {
                System.out.print(df.format(rank2[i]) + "\t\t\t");
            }
            System.out.println();
            for (int i = 0; i < matrix.length; i++) {
                rank[i] = rank2[i]*((1-s)/matrix.length);
                rank2[i] = 0;
                count[i] = 0;
            }
        }

    }

    public static void main(String[] args) {
        init();//初始化排名矩阵
        pagerank();


    }
}
