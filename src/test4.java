import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class test4 {

    static boolean vis[] = new boolean[100];
    static int dis[][]  =new int [100][100];
    public static int bfs(int v, int n)
    {
        //初始化矩阵
        for(int i = 0 ; i<=n ; i++)
        {
            vis[i] = false;
        }
        int tail = 0;   //用于记录每层压入时的结点
        int last=v;     //记录每层的最后一个元素：该层压入之后弹出之前更新：last=temp；
        int count=1;
        int level=0;
        vis[v]=true;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(v);

        while (!queue.isEmpty())
        {
            int x = queue.poll();
            for(int j = 1 ; j <=n ; j ++)//若未被访问过：访问并压入队列
            {
                if(!vis[j]&&dis[x][j]==1)
                {
                    vis[j] = true;
                    queue.add(j);
                    tail=j ;  //记录压入的结点
                    count++;
                }
            }
            if(x==last) //一层全部弹出，准备开始弹下一层：弹出的(x)=当前层最后一个元素(last)
            {
                last=tail;    //一层全都压入完后，更新last
                level++;
            }
            if(level==6)
                break;
        }
        return count;
    }
    public static void main(String[] args){

        System.out.println("请输入图存在的点数和边数");
        Scanner in = new Scanner(System.in);
        int v = in.nextInt();
        int e = in.nextInt();
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println("请输入依次输入边");
        int a;
        int b;
        for(int i =1 ; i <= e; i++)
        {
            a = in.nextInt();
            b = in.nextInt();
            dis[a][b] = 1;
            dis[b][a] = 1;
        }
        for(int j = 1 ; j<=v; j++)
        {
            double rate = (double) bfs(j,v) / (v );
            System.out.println("顶点"+j+"符合六度空间理论的节点占总节点的百分比为"+": " + df.format(rate * 100) + "%");


        }
    }
}
