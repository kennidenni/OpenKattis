package prob8;

import java.util.*;

public class KnightsInFen {
    static int[][] answer;
    static int best;

    static int[][] end;

    static Scanner reader;

    public static void main(String[] args) {
        reader = new Scanner(System.in);

        int cases = reader.nextInt();
        String line = reader.nextLine();

        for (int x = 0; x < cases; x++) {

            answer = new int[][]{
                    {1, 1, 1, 1, 1},
                    {0, 1, 1, 1, 1},
                    {0, 0, -1, 1, 1},
                    {0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0}};

            best = 11;
            end = new int[5][5];

            readInput();

            runBrute(answer, new Pos(2, 2), new Pos(6, 6), 0);

            if (best >= 11) {
                System.out.println("Unsolvable in less than 11 move(s).");
            } else
                System.out.println("Solvable in " + best + " move(s).");
            System.out.flush();
        }
    }

    private static void runBrute(int[][] arr, Pos empty, Pos lastEmpty, int times) {
        if (correct(arr)) {
            best = Math.min(best, times);
            return;
        }
        if (times == 10) {
            return;
        }

        ArrayList<Pos> possible = findPossible(empty, lastEmpty);

        for (Pos p : possible) {

            int knight = arr[p.x][p.y];
            arr[empty.x][empty.y] = knight;
            arr[p.x][p.y] = -1;

            Pos nextEmpty = new Pos(p.x, p.y);

            runBrute(arr, nextEmpty, empty, times + 1);

            arr[empty.x][empty.y] = -1;
            arr[p.x][p.y] = knight;
        }
    }

    private static boolean correct(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] != end[i][j])
                    return false;
            }
        }
        return true;
    }

    private static ArrayList<Pos> findPossible(Pos empty, Pos lastEmpty) {

        ArrayList<Pos> possible = new ArrayList();
        int x = empty.x;
        int y = empty.y;
        if (!correctPlace(lastEmpty, x + 2, y + 1)) {
            possible.add(new Pos(x + 2, y + 1));
        }
        if (!correctPlace(lastEmpty, x + 2, y - 1)) {
            possible.add(new Pos(x + 2, y - 1));
        }
        if (!correctPlace(lastEmpty, x - 2, y + 1)) {
            possible.add(new Pos(x - 2, y + 1));
        }
        if (!correctPlace(lastEmpty, x - 2, y - 1)) {
            possible.add(new Pos(x - 2, y - 1));
        }
        if (!correctPlace(lastEmpty, x + 1, y + 2)) {
            possible.add(new Pos(x + 1, y + 2));
        }
        if (!correctPlace(lastEmpty, x - 1, y + 2)) {
            possible.add(new Pos(x - 1, y + 2));
        }
        if (!correctPlace(lastEmpty, x + 1, y - 2)) {
            possible.add(new Pos(x + 1, y - 2));
        }
        if (!correctPlace(lastEmpty, x - 1, y - 2)) {
            possible.add(new Pos(x - 1, y - 2));
        }
        return possible;
    }

    private static boolean correctPlace(Pos lastEmpty, int x, int y) {
        if (x >= 0 && x < 5 && y >= 0 && y < 5) {
            if (x == lastEmpty.x && y == lastEmpty.y)
                return true;
            return false;
        }
        return true;
    }

    private static void readInput() {

        for (int i = 0; i < 5; i++) {
            String[] lines = reader.nextLine().split("");

            for (int j = 0; j < 5; j++) {
                if (lines[j].equals(" "))
                    end[i][j] = -1;
                else
                    end[i][j] = Integer.parseInt(lines[j]);
            }
        }
    }

    private static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}