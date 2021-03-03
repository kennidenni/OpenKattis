import java.io.*;
import java.util.StringTokenizer;

public class GuessTheNumber {

    public static void main(String[] args) {

        Kattio io = new Kattio(System.in, System.out);

        int lowerBound = 1;
        int upperBound = 1000;
        int currentNum = (lowerBound + upperBound) / 2;

        System.out.println(currentNum);

        int numberOfGuess = 1;
        while (numberOfGuess < 11) {

            String input = io.getWord();

            if (input.equals("correct")) {
                break;
            } else {
                numberOfGuess++;

                if (input.equals("higher")) {
                    lowerBound = currentNum + 1;
                    currentNum = (lowerBound + upperBound + 1) / 2;
                }

                if (input.equals("lower")) {
                    upperBound = currentNum - 1;
                    currentNum = (lowerBound + upperBound) / 2;
                }
                io.println(currentNum);
            }
            io.flush();
        }

        io.close();

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
