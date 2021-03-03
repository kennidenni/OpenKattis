import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BreakingBad {
    static boolean isPossible = true;

    public static void main(String[] args) {

        Kattio io = new Kattio(System.in, System.out);

        int items = io.getInt();
        Graph g = new Graph(items);

        for (int i = 0; i < items; i++) {
            g.addVertex(io.getWord());
        }

        int illegalPairs = io.getInt();

        for (int i = 0; i < illegalPairs; i++) {
            String item1 = io.getWord();
            String item2 = io.getWord();
            g.connectTwoNodes(item1, item2);
        }

        for (String s : g.vertices.keySet()) {
            Vertex v = g.vertices.get(s);
            if (!v.visited) {
                dfs(v);
            }

        }

        if (!isPossible) {
            System.out.println("impossible");
        } else {
            ArrayList<String> p1 = new ArrayList<>();
            ArrayList<String> p2 = new ArrayList<>();

            for (String s : g.vertices.keySet()) {
                Vertex v = Graph.vertices.get(s);
                if (v.color)
                    p1.add(s);
                else
                    p2.add(s);
            }

            for (String w : p1)
                System.out.print(w + " ");
            System.out.println();
            for (String j : p2)
                System.out.print(j + " ");
        }
        io.close();
    }

    public static void dfs(Vertex w) {
        w.visited = true;
        for (Vertex v : w.connected) {
            if (!v.visited) {
                v.color = !w.color;
                dfs(v);
            } else if (v.color == w.color) {
                isPossible = false;
                break;
            }
        }
    }

    public static class Vertex {
        public String name;
        public ArrayList<Vertex> connected;
        public boolean color;
        public boolean visited;

        public Vertex(String name) {
            this.name = name;
            connected = new ArrayList<Vertex>();
            color = false;
            visited = false;
        }

        public void setConnected(Vertex s) {
            this.connected.add(s);
        }
    }

    public static class Graph {
        static Map<String, Vertex> vertices;
        int items;


        public Graph(int items) {
            this.items = items;
            vertices = new HashMap<>();
        }

        public void addVertex(String name) {
            vertices.put(name, new Vertex(name));
        }

        public void connectTwoNodes(String v1, String v2) {
            vertices.get(v1).setConnected(vertices.get(v2));
            vertices.get(v2).setConnected(vertices.get(v1));
        }
    }

    static class Kattio extends PrintWriter {
        public Kattio(InputStream i) {
            super(new BufferedOutputStream(System.out));
            r = new BufferedReader(new InputStreamReader(i));
        }

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
