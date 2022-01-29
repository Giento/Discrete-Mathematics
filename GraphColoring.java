package hr.fer.oop.dismatlab3;

import java.io.*;
import java.util.Scanner;

public class GraphColoring {
    int n;
    int[] color;

    public GraphColoring(int n) {
        this.n = n;
        color = new int[n];
    }

    public static void main(String[] args) {
        System.out.print("Unesite ime datoteke: ");

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        File file = new File(str);
        sc.close();

        try (BufferedReader br = new BufferedReader(
                new FileReader(file.getAbsolutePath()))) {

            int n;
            int numOfS;

            n = Integer.parseInt(br.readLine());
            numOfS = Integer.parseInt(br.readLine());
            String string = br.readLine();
            String[] arrOfS = string.split(" ");

            //vrijednosti skupa s
            int[] s = new int[arrOfS.length];
            for (int i = 0; i < arrOfS.length; i++)
                s[i] = Integer.parseInt(arrOfS[i]);

            textFileCheck(s, n, numOfS);

            //postavljanje vrijednosti matrice susjedstva
            int[][] adjMatrix = new int[n][n];
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    int x = Math.abs(i - j);
                    for (int num: s) {
                        if (x == num)
                            adjMatrix[i][j] = 1;
                    }
                }
            }
            printMatrix(adjMatrix, n);

            GraphColoring Coloring = new GraphColoring(n);
            int i = 1;
            while (!Coloring.colourGraph(adjMatrix, i))
                i++;

        } catch (IOException ex) {
            System.out.println("Došlo je do greške.");
        }
    }

    public boolean colourGraph(int[][] adjMatrix, int m) {
        if (!graphColoringUtil(adjMatrix, m, color, 0))
            return false;
        printChromatic(m);
        return true;
    }

    public boolean graphColoringUtil(int[][] adjMatrix, int m,
                                     int[] color, int k) {
        // ako su svi vrhovi obojani
        if (k == n)
            return true;

        // probaj bojati trenutni vrh (vrh k)
        for (int c = 1; c <= m; c++) {
            if (canColor(adjMatrix, k, c)) {
                color[k] = c;
                if (graphColoringUtil(adjMatrix, m, color, k + 1))
                    return true;

                color[k] = 0;
            }
        }
        return false;

    }

    private boolean canColor(int[][] adjMatrix, int k, int c) {
        for (int i = 0; i < n; i++) {
            if (adjMatrix[k][i] == 1 && c == color[i])
                return false;
        }
        return true;
    }

    public void printChromatic(int m) {
        System.out.println("Kromatski broj zadanog grafa je " + m);
    }


    public static void printMatrix(int[][] adjMatrix, int n) {
        System.out.println("Matrica susjedstva:");
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void textFileCheck(int[] s,int n, int numOfS) {
        System.out.println();
        System.out.println("Navedeni parametri su sljedeci: ");
        System.out.println("n = " + n + "\n" + "s = " + numOfS);
        System.out.print("vrijednosti skupa s: ");
        for (int i = 0; i < numOfS; i++)
            System.out.print(s[i] + "  ");
        System.out.println();
        System.out.println();
    }
}
