import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class PeskyMosquitoes {

    static double d;
    static double rad;
    static ArrayList<Point> arr;

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        int times = io.getInt();

        for (int i = 0; i < times; i++) {
            int mos = io.getInt();
            d = io.getDouble();

            rad = d / 2;
            int best = 0;

            arr = new ArrayList<>();
            for (int j = 0; j < mos; j++) {
                Point x = new Point(io.getDouble(), io.getDouble());
                arr.add(x);
            }

            for (int j = 0; j < arr.size(); j++) {
                for (int k = 1; k < arr.size(); k++) {
                    double dist = euclideanDistance(arr.get(j), arr.get(k));

                    if (dist > rad + rad) {
                        if (best < 1) {
                            best = 1;
                        }
                    } else if (dist == d) {
                        double a = dist / 2;

                        Point P2 = new Point(0, 0);
                        P2.x = arr.get(j).x + a * (arr.get(k).x - arr.get(j).x) / dist;
                        P2.y = arr.get(j).y + a * (arr.get(k).y - arr.get(j).y) / dist;
                        int num = findAllInsideCircle(P2);
                        if (num > best) {
                            best = num;
                        }
                    }
                    else if (dist != 0) {
                        Point[] points = findPoint(arr.get(j), arr.get(k), dist);
                        int num = findAllInsideCircle(points[0]);
                        int num2 = findAllInsideCircle(points[1]);
                        if (num > num2) {
                            if (num > best) {
                                best = num;
                            }
                        } else {
                            if (num2 > best) {
                                best = num2;
                            }
                        }
                    }
                }
            }

            io.println(best);
        }

        io.close();
    }

    private static int findAllInsideCircle(Point next) {
        int inside = 0;
        for (int i = 0; i < arr.size(); i++) {
            double dist = euclideanDistance(arr.get(i), next);
            if (dist <= rad) {
                inside++;
            }
        }
        return inside;
    }

    public static double euclideanDistance(Point fst, Point snd) {
        Point result = new Point(0, 0);
        result.y = Math.abs(fst.y - snd.y);
        result.x = Math.abs(fst.x - snd.x);
        return Math.sqrt((result.y) * (result.y) + (result.x) * (result.x));
    }

    private static Point[] findPoint(Point fst, Point snd, double dist) {
        double a = dist / 2;
        double h = Math.sqrt((rad * rad) - (a * a));
        Point[] ret = new Point[2];

        Point P2 = new Point(0, 0);
        P2.x = fst.x + (a / dist) * (snd.x - fst.x);
        P2.y = fst.y + (a / dist) * (snd.y - fst.y);

        Point P3 = new Point(0, 0);
        P3.x = P2.x + (h / dist) * (snd.y - fst.y);
        P3.y = P2.y - (h / dist) * (snd.x - fst.x);
        ret[0] = P3;

        Point P4 = new Point(0, 0);
        P4.x = P2.x - (h / dist) * (snd.y - fst.y);
        P4.y = P2.y + (h / dist) * (snd.x - fst.x);
        ret[1] = P4;

        return ret;
    }

    public static class Point {
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "x:" + x + ", y:" + y;
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
