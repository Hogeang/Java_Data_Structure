import java.util.*;
//위상 정렬  ( VOG , DAG )
class Graph{

    char [] Name;
    boolean [] Node;
    int [][] Edge;
    public Graph(){
        Node = new boolean[0];
        Edge = new int[0][0];
        Name = new char[0];
    }
    public Graph(int Size){
        Scanner scanner = new Scanner(System.in);
        Name = new char[Size];
        Node = new boolean[Size];
        Edge = new int[Size][Size];
        for(int i=0;i<Size;i++) {
            for (int j=0; j < Size; j++) {
                if (i != j) {
                    System.out.print(i + "," + j + ": 행은 연결되어있습니까? : " + i  +" -> " + j + "방향 연결이면 1 / 아니면 0 : " );
                    Edge[i][j] = scanner.nextInt();
                }  else{
                    Edge[i][j] = 0;
                }
                Node[i] = false; // 전부 비방문 상태
            }
        }
        for(int i=0;i<Size;i++){
            char temp = 'a';
            Name[i] = (char)((int)temp + i);
        }
    }

    // 싱크인지 , 소스인지 판단하고 아니면 다시 .
    public boolean find(char start, char end){
        boolean findEdge = false;
        int sink = 0; // 싱크
        int source = 0; // 소스
        int sinkFind = 0; // 싱크를 할 노드 위치
        int sourceFind = 0; // 소스를 찾을 노드 위치
        for(int i=0;i<Name.length;i++){
            if(Name[i] == start){
                sinkFind = i;

            }
            if(Name[i] == end){
                sourceFind = i;
            }
        } // 위치 파악중 찾아보기

        for(int i=0;i<this.Node.length;i++){
                if((Edge[i][sinkFind] ==1) && (i!=sinkFind))  sink++;
        } // 싱크인지 파악

        for(int i=0;i<this.Node.length;i++){
            if((Edge[sourceFind][i] ==1)&& (i!=sourceFind))  source++;
        } // 소스인지 파악

        // Sink, Source인지 확인하고 Update
        if(sink == 0 && source == 0){
            findEdge = true;
        }else{
            findEdge = false;
        }
        return findEdge;
    }

    public void findPath(char start, char end){
        int find = 0;
        char find_Path = '0';
        for(int i=0;i<Name.length;i++){
            if(Name[i] == start){
                find = i;
                Node[i] = true;
                find_Path = Name[i];
            }

        }

        if(find_Path == end) {
            System.out.println("경로가 있습니다.");
            return;
        }

        for(int i=0;i<this.Node.length;i++){
            // 목적지를 찾았을 경우
            // 가는 경로가 존재시 ( 방문하지 않은 행 )
            if((Edge[find][i] == 1)&& (Node[i] == false)){
                 findPath(Name[i],end);
            }
            if(i==this.Node.length-1){
                System.out.println("해당 경로는 없습니다.");
            }
        }


    }
}

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();
        Scanner scanner = new Scanner(System.in);
       int size,cases;
       char start, end;
       boolean findEdge;
      while(true) {
          System.out.print("인접 행렬 설정 (1 - 경로 지정 / 2 - 경로 탐색 ) : ");
          cases = scanner.nextInt();
          switch(cases) {
              case 1:
                  System.out.print("그래프 사이즈를 지정해주세요 : ");
                  size = scanner.nextInt();
                  graph = new Graph(size);
              break;
              case 2:
                  scanner.nextLine();
                  System.out.print("시작점을 지정해주세요 : ");
                  start = scanner.next().charAt(0);
                  scanner.nextLine();
                  System.out.print("종료점을 지정해주세요 : ");
                  end =  scanner.next().charAt(0);
                  findEdge = graph.find(start,end);
                  if(findEdge == true)
                  graph.findPath(start,end);
                  else
                      System.out.println("Sink, Source가 아닙니다.");
              break;
          }
      }
     }
}
