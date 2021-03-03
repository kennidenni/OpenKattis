import java.io.*;
import java.util.StringTokenizer;

public class InverseFactorial {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        String in = io.getWord();

        int len = in.length();
        if (len > 3)
            System.out.println(solution(len));
        else {
            int i = Integer.valueOf(in);
            if (i == 1)
                System.out.println(1);
            else if (i == 2)
                System.out.println(2);
            else if (i == 6)
                System.out.println(3);
            else if (i == 24)
                System.out.println(4);
            else if (i == 120)
                System.out.println(5);
            else
                System.out.println(6);

            //else
            //    System.out.println(7);
        }

    }

    private static int solution(int len) {
        double ans = 1;
        int i;
        for (i = 1; i < 100000000; i++) {
            ans = ans + Math.log10(i);
            if ((int) ans == len)
                return i;
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
