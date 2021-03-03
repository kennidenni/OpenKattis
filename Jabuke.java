package prob7;

import java.io.*;
import java.util.StringTokenizer;

public class Jabuke {

    static Point a;
    static Point b;
    static Point c;

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        a = new Point(io.getInt(), io.getInt());
        b = new Point(io.getInt(), io.getInt());
        c = new Point(io.getInt(), io.getInt());
        double fstTri = getAreal(a, b, c);
        io.println(fstTri);

        int n = io.getInt();
        int trees = 0;
        for (int i = 0; i < n; i++) {
            Point x = new Point(io.getInt(), io.getInt());
            if (containsPoint(x, fstTri))
                trees++;
        }
        io.println(trees);
        io.close();
    }

    public static double getAreal(Point a, Point b, Point c) {
        return (double) Math.abs(a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y)) / 2;
    }

    public static boolean containsPoint(Point x, double fstTri) {
        double q = getAreal(a, b, x);
        double w = getAreal(b, c, x);
        double e = getAreal(c, a, x);
        return q+w+e == fstTri;
    }


    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
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
