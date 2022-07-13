import java.util.ArrayList;

public class l001{
    static class Edge {
        int v= 0,w= 0;

        Edge(int v,int w){
            this.v = v;
            this.w = w;
        }
    }

    //O(2E) because bidirectional hai , not O(V.E)
    public static void display(ArrayList<Edge> []graph){
        for(int i =0;i < graph.length ;i++){
            System.out.print(i + "-->");
            for(Edge e : graph[i]){
                System.out.print(" (" + e.v + " , " + e.w + " )");
            }
            System.out.println();
        }
    }

    public static void addEdge(ArrayList<Edge> []graph,int u,int v,int w){
        graph[u].add(new Edge(v,w));
        graph[v].add(new Edge(u,w));
    }

    //O(E) : worst case single point connected to all vertex.
    public static int findEdge(ArrayList<Edge> []graph,int u,int v){
        ArrayList<Edge> list = graph[u];
        for(int i = 0;i<list.size();i++){
            Edge e = list.get(i);
            if(e.v == v)
                return i;
        }
        return -1;
    }

    //O(E)
    public static void removeEdge(ArrayList<Edge>[] graph,int u,int v){
        int idx = findEdge(graph, u, v);
        graph[u].remove(idx);

        idx = findEdge(graph, v, u);
        graph[v].remove(idx);
    }

    //O(E)
    public static void removeVertex(ArrayList<Edge> [] graph,int u){
        ArrayList<Edge> list = graph[u];
        while(list.size() != 0){
            Edge e = list.get(list.size() - 1); //no shifting takes place..
            removeEdge(graph,u,e.v);
        }
    }

    //O(E) where E is total no of edges in that partcular component.(hum edges explore kr rhe hai and vertices edges se zyada ho skti hai)
    public static boolean dfs_findPath(ArrayList<Edge> []graph,int src,int dest,boolean []vis){
        if(src == dest)
            return true;

        vis[src] = true;
        
        boolean res = false;
        for(Edge e : graph[src]){
            if(!vis[e.v])
                res = res || dfs_findPath(graph, e.v, dest,vis);
        }

        return res;
    }

    public static int printAllPaths(ArrayList<Edge>[]graph,int src,int dest,boolean []vis,String psf,int wsf){
        if(src == dest){
            System.out.println(psf + dest + "@ " + wsf);
            return 1;
        }
        
        int count = 0;
        vis[src] = true;
        for(Edge e : graph[src]){
            if(!vis[e.v])
                count += printAllPaths(graph, e.v, dest, vis, psf + src, wsf + e.w);
        }
        vis[src] = false;
        return count;
    }

    //O(E) : as travel all neighbours. 
    public static void dfs_Gcc(ArrayList<Edge> []graph,int src,boolean []vis){
        vis[src] = true;
        for(Edge e: graph[src]){
            if(!vis[e.v])
                dfs_Gcc(graph,e.v,vis);
        }
    }

    //O(V + E) : E for dfs_Gcc and V for this gcc fn or freely vertex ehich does not have edge. 
    public static void getConnectedComponents(ArrayList<Edge> []graph){
        boolean []vis = new boolean[graph.length];
        int component = 0;
        for(int i = 0;i<graph.length;i++){
            if(!vis[i]){
                component++;
                dfs_Gcc(graph,i,vis);
            }
        }
        System.out.println(component);
    }

    public static void constructGraph(){
        int N = 7;
        ArrayList<Edge> []graph = new ArrayList[N];

        for(int i =0;i<N;i++){
            graph[i] = new ArrayList<>();
        }

        addEdge(graph,0,1,10);
        addEdge(graph,1,2,10);
        addEdge(graph,2,3,40);
        addEdge(graph,0,3,10);
        addEdge(graph,3,4,2);
        addEdge(graph,4,5,2);
        addEdge(graph,5,6,3);
        addEdge(graph,4,6,8);

        display(graph);
        boolean []vis = new boolean[N];
        // System.out.println(dfs_findPath(graph, 0, 6,vis)) ;
        // System.out.println(printAllPaths(graph, 0, 6,vis,"",0)) ;
        getConnectedComponents(graph);
        // removeEdge(graph,3,4);
        // display(graph);
        // removeVertex(graph, 6);
        // display(graph);

    }

    public static void main(String []args){
        constructGraph();
    }
}