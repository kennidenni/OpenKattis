import java.io.*;
import java.util.StringTokenizer;

public class FroshWeek {

    private static long swap;

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        int len = io.getInt();
        swap = 0;

        int[] in = new int[len];

        for (int i = 0; i < len; i++) {
            in[i] = io.getInt();
        }
        mergeSort(in);
        io.println(swap);
        io.close();

    }

    public static int[] mergeSort(int[] arr) {
        if (arr.length < 2) {
            return arr;

        } else {
            int mid = (int) Math.floor(arr.length * 0.5);

            int[] left = new int[(int) Math.floor(arr.length * 0.5)];
            for (int i = 0; i < mid; i++) {
                left[i] = arr[i];
            }

            int[] right = new int[(int) Math.ceil(arr.length * 0.5)];
            for (int i = mid; i < arr.length; i++) {
                right[i - mid] = arr[i];
            }

            //Split until there is only 1 element left
            return merge(mergeSort(left), mergeSort(right));
        }
    }

    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];

        int temp = 0;
        int tempLeftIndex = 0;
        int tempRightIndex = 0;
        long adding = 0;


        while (tempLeftIndex < left.length && tempRightIndex < right.length) {
            //Compare the elements from each array

            if (left[tempLeftIndex] <= right[tempRightIndex]) {
                result[temp] = left[tempLeftIndex];
                tempLeftIndex++;
                swap += adding;

            } else {
                result[temp] = right[tempRightIndex];
                tempRightIndex++;
                adding++;
            }
            temp++;
        }
        while (tempLeftIndex < left.length) {
            result[temp] = left[tempLeftIndex];
            tempLeftIndex++;
            swap += adding;
            temp++;
        }
        while (tempRightIndex < right.length) {
            result[temp] = right[tempRightIndex];
            tempRightIndex++;
            temp++;
        }
        return result;
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
