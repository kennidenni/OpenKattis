import java.io.*;
import java.util.*;


public class SimplePolygon {
    static HashMap<Point, Integer> numbered;

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        int times = io.getInt();

        for (int i = 0; i < times; i++) {
            int nrPoints = io.getInt();

            Point[] points = new Point[nrPoints];
            numbered = new HashMap<>();

            for (int j = 0; j < nrPoints; j++) {
                int x = io.getInt();
                int y = io.getInt();

                Point p = new Point(x, y);
                points[j] = p;
                numbered.put(p, j);
            }

            Arrays.sort(points);

            grahamScan(points);
            System.out.println();
        }
    }

    private static void grahamScan(Point[] points) {
        ArrayList<Point> found = new ArrayList<>();

        Arrays.sort(points);

        Stack<Point> s = computePoints(points);

        while (!s.empty()){
            Point p = s.pop();
            found.add(p);
            System.out.print(numbered.get(p) + " ");
        }


        for (int i = 0; i < points.length; i++) {
            if (!found.contains(points[i])) {
                System.out.print(numbered.get(points[i]) + " ");
            }
        }
    }


    private static Stack computePoints(Point[] points) {
        Stack<Point> t = new Stack<>();
        t.push(points[0]);
        t.push(points[1]);

        for (int i = 2; i < points.length; i++) {
            while (t.size() > 1 && dir(secondTop(t), t.peek(), points[i]) == 2) {
                t.pop();
            }
            t.push(points[i]);
        }
        return t;
    }

    private static Point secondTop(Stack<Point> s) {
        Point temp = s.pop();
        Point ret = s.peek();
        s.push(temp);
        return ret;
    }

    private static int dir(Point a, Point b, Point c) {
        int val = (b.y - a.y) * (c.x - b.x) - (b.x - a.x) * (c.y - b.y);

        if (val == 0)
            return 0;     //colinear
        else if (val < 0)
            return 2;    //anti-clockwise direction
        return 1;    //clockwise direction
    }

    private static class Point implements Comparable<Point> {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (x != o.x)
                return Integer.compare(x, o.x);

            if (y != o.y)
                return Integer.compare(y, o.y);

            return 0;
        }
    }

    static class Kattio extends PrintWriter {

        private BufferedReader r;
        private String line;
        private StringTokenizer st;
        private String token;

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
