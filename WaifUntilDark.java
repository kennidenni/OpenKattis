import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class WaifUntilDark {

    static int children;
    static int numToys;
    static int categories;

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        children = io.getInt();
        numToys = io.getInt();
        categories = io.getInt();

        Graph g = new Graph(children + numToys + categories + 2);

        HashMap<Integer, Boolean> inCategory = new HashMap<>();

        for (int i = 1; i < children + 1; i++) {
            g.addEdge(0, i, 1);
            int inputToys = io.getInt();

            for (int j = 0; j < inputToys; j++) {
                int toy = io.getInt();
                inCategory.put(toy + children, false);
                g.addEdge(i, toy + children, 1);
            }
        }

        for (int i = 0; i < categories; i++) {
            int in = io.getInt();
            int[] toys = new int[in];

            for (int j = 0; j < in; j++) {
                int toy = io.getInt();
                toys[j] = toy;
                inCategory.remove(toys[j] + children);
            }
            int times = io.getInt();

            for (int j = 0; j < toys.length; j++) {
                g.addEdge(toys[j] + children, i + children + numToys + 1, 1);
            }
            g.addEdge(children + numToys + i + 1, children + numToys + categories + 1, times);
        }

        for (int toy : inCategory.keySet()) {
            g.addEdge(toy, children + numToys + categories + 1, 1);
        }

        System.out.println(fordFulkerson(g, 0, children + numToys + categories + 1));


    }

    static int fordFulkerson(Graph g, int source, int target) {
        int max_flow = 0;

        int[] parent = new int[children + numToys + categories + 2];

        // Update the flow when there is a path from s to t
        while (bfs(g, source, target, parent)) {
            int path_flow = Integer.MAX_VALUE;
            int curr = target;
            int next;

            //find flow value
            while (source != curr) {
                next = parent[curr];
                path_flow = Math.min(path_flow, g.getNode(next, curr).cap);
                curr = parent[curr];
            }

            // update capacities and reverse edges along the path
            curr = target;
            while (curr != source) {
                next = parent[curr];
                g.getNode(next, curr).cap -= path_flow;
                g.getNode(curr, next).cap += path_flow;
                curr = parent[curr];
            }
            max_flow += path_flow;
        }

        return max_flow;
    }

    static boolean bfs(Graph g, int source, int target, int parent[]) {
        boolean visited[] = new boolean[children + numToys + categories + 2];

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        //BFS
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (Edge e : g.edges[curr]) {
                if (!visited[e.to] && e.cap > 0) {
                    queue.add(e.to);
                    parent[e.to] = curr;
                    visited[e.to] = true;
                }
            }
        }
        return (visited[target]);
    }

    static class Graph {
        ArrayList<Edge>[] edges;

        public Graph(int n) {
            edges = new ArrayList[n];
            for (int i = 0; i < edges.length; i++) {
                edges[i] = new ArrayList<>();
            }
        }

        public void addEdge(int from, int to, int cap) {
            edges[from].add(new Edge(to, cap));
            edges[to].add(new Edge(from, 0));
        }

        public Edge getNode(int u, int v) {
            for (Edge e : edges[u]) {
                if (e.to == v)
                    return e;
            }
            return null;
        }
    }

    static class Edge {
        int to;
        int cap;

        public Edge(int to, int cap) {
            this.to = to;
            this.cap = cap;
        }
    }

    static class Kattio extends PrintWriter {

        public Kattio(InputStream i, OutputStream o) {
            super(new BufferedOutputStream(o));
            r = new BufferedReader(new InputStreamReader(i));
        }

        public boolean hasMoreTokens() {
            return peekToken() != null;
        }

        public int getInt() {
            return Integer.parseInt(nextToken());
        }

        public double getDouble() {
            return Double.parseDouble(nextToken());
        }

        public long getLong() {
            return Long.parseLong(nextToken());
        }

        public String getWord() {
            return nextToken();
        }

        private BufferedReader r;
        private String line;
        private StringTokenizer st;
        private String token;

        private String peekToken() {
            if (token == null)
                try {
                    while (st == null || !st.hasMoreTokens()) {
                        line = r.readLine();
                        if (line == null) return null;
                        st = new StringTokenizer(line);
                    }
                    token = st.nextToken();
                } catch (IOException e) {
                }
            return token;
        }

        private String nextToken() {
            String ans = peekToken();
            token = null;
            return ans;
        }
    }
}