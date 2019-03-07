import java.util.Scanner;

public class Main {
    static int matrix1[][] = {{0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 0, 0},
            {0, 1, 0, 0, 0, 0, 1, 1},
            {0, 1, 0, 0, 1, 0, 0, 0},
            {0, 1, 0, 1, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0}};//邻接矩阵表示的图
    static int matrix2[][] = {{100, 100, 100, 100, 100, 100, 100, 100},
            {100, 100, 10, 5, 3, 4, 100, 100},
            {100, 10, 100, 100, 100, 100, 6, 8},
            {100, 5, 100, 100, 6, 100, 100, 100},
            {100, 3, 100, 6, 100, 100, 100, 100},
            {100, 4, 100, 100, 100, 100, 7, 100},
            {100, 100, 6, 100, 100, 7, 100, 100},
            {100, 100, 8, 100, 100, 100, 100, 100}};//图每条边的权重，表示双方发过的邮件数。
    static int inedge[] = new int[8];//储存顶点的入度
    static int label2[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public static int Dijkstra(int v, int end, int distx[][]) {
        int dist[] = new int[8];
        boolean s[] = new boolean[8];
        for (int i = 0; i < 8; i++) {
            dist[i] = distx[v][i];
            s[i] = false;
        }
        dist[v] = 0;
        s[v] = true;
        for (int i = 2; i < 8; i++) {
            int best = v;
            int temp = 100;
            for (int j = 1; j < 8; j++) {
                if (!s[j] && dist[j] < temp) {
                    best = j;
                    temp = dist[j];
                }
            }
            s[best] = true;
            for (int j = 1; j < 8; j++) {

                if (s[j] == false && distx[best][j] != 100 && dist[best] + distx[best][j] < dist[j]) {
                    dist[j] = dist[best] + distx[best][j];
                }
            }
        }

        return dist[end];
    }

    public static void print_mix_dist() {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入您要求那两个节点的最短路径");
        int a = in.nextInt();//开始节点
        int b = in.nextInt();//结束节点
        int x = Dijkstra(a, b, matrix2);
        System.out.println("节点" + a + "到节点" + b + "的最短路径为:" + x);

    }

    public static void get_cluser() {
        int s1, l1, s2;
        double s[] = new double[8];//储存标记
        double cc[] = new double[8];//储存聚集系数


        for (int i = 1; i < 8; i++) {
            l1 = 0;
            for (int j = 0; j < 8; j++)//初始化矩阵
            {
                s[j] = 0;
            }

            if (inedge[i] >= 2) //只计算度大于等于2的节点
            {
                for (int j = 1; j < 8; j++) {//遍历这个节点  将她的所有朋友标记
                    if (matrix1[i][j] == 1) {
                        s[j] = 1;
                    }
                }
                for (int k = 1; k < 8; k++) {
                    if (s[k] == 1) { //如果节点被标记 就找这个节点的朋友
                        s1 = k;
                        for (int x = s1; x < 8; x++) {
                            if (s[x] == 1) {
                                s2 = x;
                                if (matrix1[s1][s2] == 1) {//如果满足，就是a的两个朋友也是朋友的节点
                                    l1++;
                                }
                            }
                        }
                    }
                }
                cc[i] = (2.0 * l1) / ((inedge[i] * (inedge[i] - 1)));

            }
            if (inedge[i] != 0)
                System.out.print(i + "的聚集系数为:" + cc[i] + "\n");
        }

    }

    private static void DepthFirstSearch(int v) {

        label2[v] = 1;
        System.out.print(v + " ");
        for (int i = 1; i < 8; i++) {
            if (label2[i] == 0 && matrix1[v][i] != 0) {

                DepthFirstSearch(i);
            }
        }
    }

    public static void get_inedge() {
        for (int i = 0; i < 8; i++) {
            inedge[i] = 0;
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (matrix1[i][j] != 0) {
                    inedge[i]++;
                }
            }
        }

    }

    public static void main(String[] args) {

        print_mix_dist();//输出两个节点的最短路径
        System.out.println();
        System.out.println();
        get_inedge();//计算顶点的入度
        get_cluser();//计算聚集系数
        System.out.println();
        System.out.println();
        System.out.println("强连通分量为：");
        DepthFirstSearch(1);

    }


}

