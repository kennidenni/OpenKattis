import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class HiddenWords {
    private static int gridX;
    private static int gridY;
    private static int answer;

    private static String[][] grid;

    private static Trie trie;

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        gridX = io.getInt();
        gridY = io.getInt();
        answer = 0;

        grid = new String[gridX][gridY];

        for (int i = 0; i < gridX; i++) {
            String s = io.getWord();
            String[] in = s.split("");
            for (int j = 0; j < gridY; j++) {
                grid[i][j] = in[j];
            }
        }

        trie = new Trie();

        insertAllWords();

        int words = io.getInt();

        for (int i = 0; i < words; i++) {
            String s = io.getWord();

            if (trie.search(s))
                answer++;
        }
        System.out.println(answer);
    }

    private static void insertAllWords() {
        boolean[][] visited = new boolean[gridX][gridY];
        for (int j = 0; j < gridX; j++) {
            for (int k = 0; k < gridY; k++) {
                find(j, k, visited, 0, "");
            }
        }
    }

    private static void find(int j, int k, boolean[][] visited, int pos, String s) {

        if (pos >= 10) {
            trie.insert(s);
            return;
        }
        if (!inRange(j, k)) {
            return;
        }

        if (visited[j][k]) {
            trie.insert(s);
            return;
        }

        visited[j][k] = true;

        s += grid[j][k];

        find(j + 1, k, visited, pos + 1, s);
        find(j - 1, k, visited, pos + 1, s);
        find(j, k + 1, visited, pos + 1, s);
        find(j, k - 1, visited, pos + 1, s);

        visited[j][k] = false;
    }

    private static boolean inRange(int i, int j) {
        return i >= 0 && j >= 0 && i < gridX && j < gridY;
    }

    public static class Trie {

        static final int SIZE = 26;

        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        class TrieNode {
            TrieNode[] children = new TrieNode[SIZE];

            TrieNode() {
                for (int i = 0; i < SIZE; i++) {
                    children[i] = null;
                }
            }
        }

        void insert(String key) {
            int length = key.length();
            int index;

            TrieNode currNode = root;

            for (int i = 0; i < length; i++) {
                index = key.charAt(i) - 'A';

                if (currNode.children[index] == null)
                    currNode.children[index] = new TrieNode();

                currNode = currNode.children[index];
            }
        }

        boolean search(String key) {
            int length = key.length();
            int index;
            TrieNode currNode = root;

            for (int i = 0; i < length; i++) {
                index = key.charAt(i) - 'A';

                if (currNode.children[index] == null)
                    return false;

                currNode = currNode.children[index];
            }

            return (currNode != null);
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
