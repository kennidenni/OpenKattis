import java.io.*;
import java.util.StringTokenizer;

public class PointsOfSnow {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        int len = io.getInt();
        int report = io.getInt();
        int quest = io.getInt();

        SegmentTree st = new SegmentTree(len);

        for (int i = 0; i < report + quest; i++) {
            String s = io.getWord();
            if (s.equals("!")) {
                int start = io.getInt();
                int end = io.getInt();
                int amount = io.getInt();
                st.addToInterval(start, end - 1, amount);
            }
            if (s.equals("?")) {
                int place = io.getInt();
                io.println(st.sumValue(place - 1));
            }
        }
        io.close();
    }

    static class SegmentTree {
        long[] tree;
        int N;
        int Offs;

        public SegmentTree(int size) {
            N = 2;
            Offs = 2;
            //Find nearest power of two
            while (2 * size + 3 > N) {
                N *= 2;
            }
            //Same as N / 2
            while (size + 2 > Offs) {
                Offs *= 2;
            }
            tree = new long[N];
        }

        public long sumValue(int p) {
            int P = p + Offs;
            long sum = 0;

            //Traverse up in the tree
            while (P > 0) {
                sum += tree[P];
                P = P / 2;
            }
            return sum;
        }

        public void addToInterval(int start, int end, int amount) {
            int L = start + Offs;
            int R = end + Offs;

            boolean LPlaced = false;
            boolean RPlaced = false;

            boolean cont = L != R;

            if (!cont) {
                tree[L] += amount;
            }

            while (cont) {
                boolean Lright = L % 2 == 0;
                boolean Rleft = R % 2 == 1;
                L /= 2;
                R /= 2;

                //Decide what to do when hitting same parent
                if (L == R) {
                    if (!LPlaced && !RPlaced) {
                        tree[L] += amount;
                    } else if (LPlaced && !RPlaced) {
                        tree[2 * L + 1] += amount;
                    } else if (!LPlaced && RPlaced) {
                        tree[2 * R] += amount;
                    }
                    break;
                }

                //Going from Left to Right
                if (Lright) {
                    if (LPlaced) {
                        tree[2 * L + 1] += amount;
                    }
                //Left not placed
                } else if (!LPlaced) {
                    LPlaced = true;
                    tree[2 * L + 1] += amount;
                }

                //Going from Right to Left
                if (Rleft) {
                    if (RPlaced) {
                        tree[2 * R] += amount;
                    }
                //Right not placed
                } else if (!RPlaced) {
                    RPlaced = true;
                    tree[2 * R] += amount;
                }
            }
        }

        @Override
        public String toString() {
            String str = "";
            for (int i = 0; i < tree.length; i++) {
                str += i + " ";
            }
            str += "\n";
            for (int i = 0; i < tree.length; i++) {
                str += tree[i] + " ";
                if (i >= 10) {
                    str += " ";
                }
            }
            return str;
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
