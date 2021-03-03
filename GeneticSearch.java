import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class GeneticSearch {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);


        String s = io.getWord();

        while (!s.equals(Integer.toString(0))) {
            String l = io.getWord();

            System.out.print(findSinL(s, l) + " ");
            System.out.print(uniqueDelete(s, l) + " ");
            System.out.println(insertToS(s, l) + " ");

            s = io.getWord();
        }
    }

    private static int findSinL(String s, String l) {
        int ans = 0;
        int from = 0;

        while ((from = l.indexOf(s, from)) != -1) {
            ans++;
            from++;
        }

        return ans;
    }

    private static int uniqueDelete(String s, String l) {
        int ans = 0;

        HashSet<String> map = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            String temp = "";

            if (i == 0)
                temp = s.substring(1);
            else {
                temp += s.substring(0, i);
                temp += s.substring(i + 1);
            }

            map.add(temp);
        }

        for (String temp : map) {
            ans += findSinL(temp, l);
        }

        return ans;
    }

    private static int insertToS(String s, String l) {
        int ans = 0;

        HashSet<String> map = new HashSet<>();

        for (int i = 0; i < 4; i++) {
            String insert = "";

            if (i == 0)
                insert = "A";
            if (i == 1)
                insert = "G";
            if (i == 2)
                insert = "C";
            if (i == 3)
                insert = "T";


            for (int j = 0; j < s.length() + 1; j++) {
                String temp = "";
                if (j == 0) {
                    temp += insert;
                    temp += s;
                } else {
                    temp += s.substring(0, j);
                    temp += insert;
                    temp += s.substring(j);
                }

                map.add(temp);
            }
        }
        for (String temp : map) {
            ans += findSinL(temp, l);
        }

        return ans;
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
