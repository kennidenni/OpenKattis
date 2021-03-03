import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MuddyHike {

    static int h;
    static int w;
    static int globalMin;

    static int[][] arr;
    static int[][] dist;

    static PriorityQueue<Node> queue;

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        h = io.getInt();
        w = io.getInt();

        arr = new int[h][w];
        dist = new int[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                arr[i][j] = io.getInt();
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        globalMin = Integer.MAX_VALUE;

        runDijkstra();

        for (int j = 0; j < h; j++) {
            globalMin = Math.min(globalMin, dist[j][w - 1]);
        }

        io.println(globalMin);
        io.close();
    }


    private static void runDijkstra() {
        queue = new PriorityQueue<>();
        // Push all starting nodes
        for (int i = 0; i < h; i++) {
            int j = 0;
            dist[i][j] = arr[i][j];
            Node firstNode = new Node(i, j, arr[i][j]);
            queue.add(firstNode);
        }

        while (!queue.isEmpty()) {
            int x = queue.peek().x;
            int y = queue.peek().y;
            int mud = queue.peek().mud;
            queue.poll();

            if (x - 1 >= 0) {
                helpCheckPos(x - 1, y, mud);
            }
            if (x + 1 < h) {
                helpCheckPos(x + 1, y, mud);
            }
            if (y - 1 >= 0) {
                helpCheckPos(x, y - 1, mud);
            }
            if (y + 1 < w) {
                helpCheckPos(x, y + 1, mud);
            }
        }
    }

    public static void helpCheckPos(int x, int y, int mud) {
        int currMax = Math.max(arr[x][y], mud);
        if (currMax < dist[x][y]) {
            dist[x][y] = Math.max(arr[x][y], mud);
            queue.add(new Node(x, y, dist[x][y]));
        }
    }

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int mud;

        public Node(int x, int y, int mud) {
            this.x = x;
            this.y = y;
            this.mud = mud;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.mud, o.mud);
        }
    }


    static class Kattio extends PrintWriter {
        private BufferedReader r;
        private String line;
        private StringTokenizer st;
        private String token;

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
