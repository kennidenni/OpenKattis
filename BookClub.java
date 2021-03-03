import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

class BookClub {

    static int PEOPLE;
    static int INTERESTS;

    public static void main(String[] args) {

        Kattio io = new Kattio(System.in, System.out);

        PEOPLE = io.getInt();
        INTERESTS = io.getInt();

        ArrayList<Integer>[] userInterests = new ArrayList[PEOPLE];

        for (int i = 0; i < PEOPLE; i++) {
            userInterests[i] = new ArrayList<>();
        }

        for (int i = 0; i < INTERESTS; i++) {
            int user = io.getInt();
            int book = io.getInt();

            userInterests[user].add(book);
        }

        if (bipartite(userInterests) == PEOPLE)
            System.out.println("YES");
        else
            System.out.println("NO");
    }

    static int bipartite(ArrayList<Integer>[] userInterests) {
        int[] left = new int[PEOPLE];
        int[] right = new int[PEOPLE];
        boolean[] visited;

        for (int i = 0; i < left.length; i++) {
            left[i] = -1;
            right[i] = -1;
        }

        int works = 0;
        for (int i = 0; i < left.length; i++) {
            visited = new boolean[PEOPLE];
            if (match(i, left, right, visited, userInterests)) {
                works++;
            }
        }

        return works;
    }

    static boolean match(int curr, int[] left, int[] right, boolean[] visited, ArrayList<Integer>[] userInterests) {
        if (visited[curr])
            return false;

        visited[curr] = true;

        for (int next : userInterests[curr]) {
            if (right[next] == -1 || match(right[next], left, right, visited, userInterests)) {
                left[curr] = next;
                right[next] = curr;
                return true;
            }
        }
        return false;
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