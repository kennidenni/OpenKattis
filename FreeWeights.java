import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class FreeWeights {

    static ArrayList<Integer> list1;
    static ArrayList<Integer> list2;

    static int pairs;
    static int highestWeight;
    static int lowestWeight;

    static Kattio io;

    public static void main(String[] args) {
        io = new Kattio(System.in, System.out);

        pairs = io.getInt();
        highestWeight = 0;
        lowestWeight = Integer.MAX_VALUE;

        list1 = readInput();
        list2 = readInput();
        list1.add(0);
        list2.add(0);

        ArrayList<Integer> help = new ArrayList<>(list1);
        help.addAll(list2);
        Collections.sort(help);

        int low = 0;
        int high = help.size();
        int possible = 0;

        while (low + 1 < high) {
            int mid = (high + low) / 2;
            int midValue = help.get(mid);
            if (checkPairs(list1, midValue) && checkPairs(list2, midValue)) {
                high = mid;
                possible = mid;
            } else {
                low = mid + 1;
            }
        }
        io.println(help.get(possible));

        io.close();
    }

    private static ArrayList<Integer> readInput() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < pairs; i++) {
            int weight = io.getInt();
            list.add(weight);
        }
        return list;
    }

    public static boolean checkPairs(ArrayList<Integer> l, int value) {
        int holdingWeigth = -1;
        for (int i : l) {
            if (i > value) {
                if (holdingWeigth == -1) {
                    holdingWeigth = i;
                } else {
                    if (holdingWeigth != i) {
                        return false;
                    }
                    holdingWeigth = -1;
                }
            }
        }
        if (holdingWeigth != -1)
            return false;
        return true;
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
