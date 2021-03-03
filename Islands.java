import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Islands {

    private static boolean[][] visited;
    private static int rows;
    private static int columns;

    public static void main(String[] args) {

        Kattio io = new Kattio(System.in, System.out);

        rows = io.getInt();
        columns = io.getInt();

        visited = new boolean[rows][columns];
        String[][] island = new String[rows][columns];

        for (int i = 0; i < rows; i++) {
            String[] temp = io.getWord().split("");

            for (int j = 0; j < columns; j++) {
                island[i][j] = temp[j];
                visited[i][j] = false;
            }
        }

        int islands = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (island[i][j].equals("L") && !visited[i][j]) {
                    explore(island, i, j);
                    islands += 1;
                } else if (island[i][j].equals("W")) {
                    visited[i][j] = true;
                }
            }
        }

        io.println(islands);
        io.close();
    }

    private static void explore(String[][] island, int i, int j) {
        visited[i][j] = true;
        for (Pos p : getPossiblePos(island, i, j)) {
            if (!visited[p.getX()][p.getY()] && !island[p.getX()][p.getY()].equals("W"))
                explore(island, p.getX(), p.getY());
        }
    }

    private static ArrayList<Pos> getPossiblePos(String[][] island, int i, int j) {
        ArrayList<Pos> list = new ArrayList<>();

        if (i - 1 >= 0)
            list.add(new Pos(i - 1, j));
        if (j - 1 >= 0)
            list.add(new Pos(i, j - 1));
        if (i + 1 < rows)
            list.add(new Pos(i + 1, j));
        if (j + 1 < columns)
            list.add(new Pos(i, j + 1));

        return list;
    }

    private static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
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