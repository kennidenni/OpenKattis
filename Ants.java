import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Ants {
    public static void main(String[] args) {

        Kattio io = new Kattio(System.in, System.out);

        int cases = io.getInt();

        for (int i = 0; i < cases; i++) {
            int poleLength = io.getInt();
            int ants = io.getInt();

            ArrayList<Integer> arr = new ArrayList<>();

            for (int j = 0; j < ants; j++) {
                arr.add(io.getInt());
            }

            int nearestMiddle = 0;
            for (int num : arr) {
                if (Math.abs(poleLength / 2 - num) < Math.abs(poleLength / 2 - nearestMiddle)) {
                    nearestMiddle = num;
                }
            }

            String str = "";
            if (nearestMiddle > poleLength/2) {
                str = String.valueOf(poleLength - nearestMiddle);
            } else {
                str = String.valueOf(nearestMiddle);
            }


            int min = Collections.min(arr);
            int max = Collections.max(arr);
            if (poleLength - max < min)
                io.println(str + " " + (max));
            else
                io.println(str + " " + (poleLength - min));
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