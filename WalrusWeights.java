import java.io.*;
import java.util.StringTokenizer;

public class WalrusWeights {
    static int[] in;
    static boolean[] out;

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        int cases = io.getInt();
        in = new int[cases];

        for (int i = 0; i < cases; i++) {
            in[i] = io.getInt();
        }

        out = new boolean[2001];
        out[0] = true;
        findValues();
        System.out.println(findBest());

        io.close();

    }

    private static int findBest() {
        for (int i = 0; i < out.length; i++) {
            if (out[1000 + i]) {
                return 1000 + i;
            } else if (out[1000 - i]) {
                return 1000 - i;
            }
        }

        return 0;
    }

    static void findValues() {
        for (int i = 0; i < in.length; i++) {
            int curr = in[i];
            int radius = 2001 - curr;

            for (int j = radius; j >= 0; j--) {
                if (out[j]) {
                    out[j + curr] = true;
                }
            }
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
