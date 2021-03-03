import java.io.*;
import java.util.StringTokenizer;

public class Reseto {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        int n = io.getInt();
        int k = io.getInt();

        System.out.println(answer(n, k));

    }

    private static int answer(int n, int k) {
        boolean[] taken = new boolean[n + 1];

        for (int i = 2; i < taken.length; i++) {
            if (!taken[i]) {
                //System.out.println("i " + i);
                for (int m = i; m < taken.length; m += i) {

                    if (!taken[m]) {
                        //System.out.println("m " + m);
                        taken[m] = true;
                        k--;
                    }

                    if (k == 0) {
                        return m;
                    }
                }
            }
        }
        return -1;
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
