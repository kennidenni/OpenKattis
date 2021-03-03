import java.io.*;
import java.util.*;

public class RobotProtection {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        int times = io.getInt();

        while (times != 0) {
            if (times > 2) {
                Point[] points = new Point[times];

                for (int i = 0; i < times; i++) {
                    int x = io.getInt();
                    int y = io.getInt();
                    points[i] = new Point(x, y);
                }

                System.out.println(grahamScan(points));

            } else {
                ArrayList<Point> points = new ArrayList<>();
                for (int i = 0; i < times; i++) {
                    int x = io.getInt();
                    int y = io.getInt();
                    points.add(new Point(x, y));
                }
                System.out.println(0);
            }
            times = io.getInt();
        }
    }

    private static double grahamScan(Point[] points) {
        ArrayList<Point> areal = new ArrayList<>();

        Arrays.sort(points);

        Stack<Point> s = computePoints(points);
        while (!s.empty()) {
            areal.add(s.pop());
        }

        Arrays.sort(points, Collections.reverseOrder());

        s = computePoints(points);
        while (!s.empty()) {
            areal.add(s.pop());
        }

        return computeArea(areal);
    }


    private static Stack computePoints(Point[] points) {
        Stack<Point> t = new Stack<>();
        t.push(points[0]);
        t.push(points[1]);

        for (int i = 2; i < points.length; i++) {
            while (t.size() > 1 && dir(secondTop(t), t.peek(), points[i]) != 2) {
                t.pop();
            }
            t.push(points[i]);
        }
        return t;
    }

    private static double computeArea(ArrayList<Point> areal) {
        float area = 0;
        for (int i = 1; i < areal.size(); i++) {
            area = area + (areal.get(i - 1).x + areal.get(i).x) * (areal.get(i - 1).y - areal.get(i).y);
        }

        return area / 2;
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