import java.io.*;
import java.util.StringTokenizer;

public class MartianDNA {

    private static int arrLength;
    private static int highestNumber;
    private static int leftInReq;

    private static int[] dna;
    private static int[] req;
    private static int[] copyRec;

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        arrLength = io.getInt();
        highestNumber = io.getInt();
        int numberOfRequirement = io.getInt();

        dna = new int[arrLength];
        req = new int[highestNumber];

        for (int i = 0; i < arrLength; i++) {
            dna[i] = io.getInt();
        }

        for (int i = 0; i < numberOfRequirement; i++) {
            int num = io.getInt();
            int count = io.getInt();
            leftInReq += count;
            req[num] = count;
        }

        copyRec = req.clone();

        int answer = findSmallestSubDNA();
        if (answer == Integer.MAX_VALUE) {
            io.println("impossible");
        } else {
            io.println(answer);
        }

        io.close();
    }

    private static int findSmallestSubDNA() {
        int startPoint = 0;
        int endPoint = 0;
        int minLength = Integer.MAX_VALUE;

        while (startPoint < arrLength) {

            if (endPoint == arrLength && endPoint < startPoint + highestNumber) {
                return minLength;
            }
            if (leftInReq > 0 && endPoint < arrLength) {
                helpRemovingFromRec(dna[endPoint]);
                endPoint++;

            } else {
                if (minLength > endPoint - startPoint && leftInReq == 0) {
                    minLength = endPoint - startPoint;
                }

                helpAddingBackToRec(dna[startPoint]);

                startPoint++;
            }

        }
        return minLength;
    }

    private static void helpAddingBackToRec(int p) {
        if (req[p] < copyRec[p] && req[p] >= 0) {
            leftInReq++;
        }
        req[p]++;
    }

    private static void helpRemovingFromRec(int p) {
        if (req[p] > 0) {
            leftInReq--;
        }
        req[p]--;

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