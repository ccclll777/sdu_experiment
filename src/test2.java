import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class test2 {
    static int matrix1[][] = {{0, 1, 0, 0, 0, 1, 1},
            {1, 0, 1, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 1},
            {0, 0, 0, 0, 1, 0, 1},
            {0, 0, 0, 1, 0, 1, 0},
            {1, 1, 1, 0, 1, 0, 1},
            {1, 0, 1, 1, 0, 1, 0}};

    static int matrix2[][] = {{0, 1, 1, 0, 0, 1, 1},
            {1, 0, 1, 0, 1, 1, 0},
            {1, 1, 0, 0, 0, 1, 1},
            {0, 0, 0, 0, 1, 1, 1},
            {0, 1, 0, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 0, 1},
            {1, 0, 1, 1, 1, 1, 0}};
    static int matrix3[][] =
            {{0, 1, 1, 1, 1, 1, 1},
                    {1, 0, 1, 0, 1, 1, 1},
                    {1, 1, 0, 0, 1, 1, 1},
                    {1, 0, 0, 0, 1, 1, 1},
                    {1, 1, 1, 1, 0, 1, 1},
                    {1, 1, 1, 1, 1, 0, 1},
                    {1, 1, 1, 1, 1, 1, 0}};

    //    static int matrix4[][] =
//            {{0, 1, 1, 1, 1, 0, 0,0,0,0,0},
//                    {1, 0, 1, 0, 0, 1, 0,0,0,0,0},
//                    {1, 1, 0, 0, 0, 1, 0,0,0,0,0},
//                    {1, 0, 0, 0, 0, 0, 1,1,0,0,0},
//                    {1, 0, 0, 0, 0, 0, 0,1,0,0,0},
//                    {0, 1, 1, 0, 0, 0, 0,0,1,0,0},
//                    {0, 0, 0, 1, 0, 0, 0,0,1,1,0},
//                    {0, 0, 0, 0, 0, 1, 1,0,0,0,1},
//                    {0, 0, 0, 0, 0, 0, 1,1,0,0,1},
//                    {0, 0, 0, 0, 0, 0, 0,0,1,1,0}};
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

    public static void get_cluser(int matrix[][]) {
        int s1, l1, s2;
        double s[] = new double[8];//储存标记
        double cc[] = new double[8];//储存聚集系数


        for (int i = 1; i < matrix.length; i++) {
            l1 = 0;
            for (int j = 0; j < matrix.length; j++)//初始化矩阵
            {
                s[j] = 0;
            }

            if (inedge[i] >= 2) //只计算度大于等于2的节点
            {
                for (int j = 1; j < matrix.length; j++) {//遍历这个节点  将她的所有朋友标记
                    if (matrix[i][j] == 1) {
                        s[j] = 1;
                    }
                }
                for (int k = 1; k < matrix.length; k++) {
                    if (s[k] == 1) { //如果节点被标记 就找这个节点的朋友
                        s1 = k;
                        for (int x = s1; x < matrix.length; x++) {
                            if (s[x] == 1) {
                                s2 = x;
                                if (matrix[s1][s2] == 1) {//如果满足，就是a的两个朋友也是朋友的节点
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

    public static void BreadthFirstSearch(int matrix[][]) {

        double CB[] = new double[20];
        int sum = 0;
        int w;

        for (int j = 0; j < 7; j++) {
            double sigma[] = new double[20];
            double dist[] = new double[20];


            Queue<Integer> queue = new LinkedList<Integer>();
            queue.offer(j);
            dist[j] = 0;
            sigma[j] = 1;

            while (!queue.isEmpty()) {
                w = queue.poll();

                for (int i = 0; i < matrix.length; i++) {
                    if (matrix[w][i] != 0) {

                        if (dist[i] == 0)//寻找最短路径的数量
                        {
                            dist[i] = dist[w] + 1;
                            queue.offer(i);

                        }
                        if (dist[i] == dist[w] + 1) {
                            sigma[i] += sigma[w];//每个节点发送一个单位的流量

                        }
                    }


                }
            }

            double delta[] = new double[20];
            for (int i = 0; i < 20; i++) {
                for (int k = 0; k < 20; k++) {
                    if (sigma[i] * (1 + delta[i]) != 0)
                        delta[k] += sigma[k] / (sigma[i] * (1 + delta[i]));


                }

                if (i != j) {
                    CB[i] += delta[i];
                }
            }

        }
        for (int i = 0; i < 7; i++) {
            CB[i] /= 2;
        }
        for (int i = 0; i < 7; i++) {
            System.out.print("介数为：" + CB[i] + "\n ");
        }
    }

    public static void get_inedge(int matrix[][]) {
        for (int i = 0; i < matrix.length; i++) {
            inedge[i] = 0;
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] != 0) {
                    inedge[i]++;
                }
            }
        }

    }

    public static void CalculateProbility(int matrix1[][], int matrix2[][]) {
        int CommonFriendsCount = 0;
        int rmatrix1[][] = new int[7][7];//计算当前两个不是朋友的人的共同朋友数

        double sum[] = new double[5];//储存当前两个人不是朋友的共同朋友数个个数
        double sum2[] = new double[5];//当前两个人不是朋友但是下个时间朋友 他们共同朋友个数

        //计算当前两个不是朋友的人的共同朋友数
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1.length; j++) {
                if (i != j)//
                {

                    for (int k = 0; k < matrix1.length; k++) {
                        rmatrix1[i][j] += matrix1[i][k] * matrix1[k][j];
                    }
                } else {
                    rmatrix1[i][j] = 0;
                }
            }
        }

        //计算当前两个人不是朋友的共同朋友数个个数 如  共同朋友为0 的有2个  为1 的有10个
        for (int i = 0; i < matrix2.length; i++) {
            for (int j = 0; j < matrix2.length; j++) {
                if (i != j && matrix1[i][j] != 1)
                    sum[rmatrix1[i][j]]++;
            }
        }
        for (int i = 0; i < 5; i++) {

            System.out.println("共同朋友数为" + i + "的人有:" + (int) sum[i] + "个");
        }
        System.out.println();

        //计算当前两个人不是朋友但是下个时间朋友 他们共同朋友个数
        for (int i = 0; i < matrix2.length; i++) {
            for (int j = 0; j < matrix2.length; j++) {
                if (i != j && matrix1[i][j] == 0 && matrix2[i][j] == 1)
                    sum2[rmatrix1[i][j]]++;
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("第二时刻有" + i + "个共同朋友的人成为朋友的人有：" + (int) sum2[i] + "个");
        }
        System.out.println();
        for (int i = 0; i < 5; i++) {
            if (sum[i] != 0) {
                double result = sum2[i] / sum[i];
                System.out.println("当朋友数为" + i + "时第一和第二快照之间成为朋友的概率为" + result);
            } else
                System.out.println("当朋友数为" + i + "时第一和第二快照之间成为朋友的概率为" + 0);
        }
    }

    public static void main(String[] args) {

        System.out.println("第一时刻到第二时刻结果如下：");
        CalculateProbility(matrix1, matrix2);
        System.out.println();
        System.out.println("————————————————————————————————————————————————————————");
        System.out.println("第二时刻到第三时刻结果如下：");
        CalculateProbility(matrix2, matrix3);
        System.out.println("————————————————————————————————————————————————————————");
        System.out.println("第一时刻每个人的聚集系数：");
        get_inedge(matrix1);
        get_cluser(matrix1);
        System.out.println("————————————————————————————————————————————————————————");
        System.out.println("第二时刻每个人的聚集系数：");
        get_inedge(matrix2);
        get_cluser(matrix2);
        System.out.println("————————————————————————————————————————————————————————");
        System.out.println("第三时刻每个人的聚集系数：");
        get_inedge(matrix3);
        get_cluser(matrix3);
        System.out.println("第一个时刻图的介数：");
        BreadthFirstSearch(matrix1);
        System.out.println("第二个时刻图的介数：");
        BreadthFirstSearch(matrix2);
        System.out.println("第三个时刻图的介数：");
        BreadthFirstSearch(matrix3);
    }


}

