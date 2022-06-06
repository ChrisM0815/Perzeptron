package com.company;

import java.util.ArrayList;

public class Main {

    private static final int bias = 1;

    public static void main(String[] args) {
        ArrayList<int[]> mplus = new ArrayList<>();
        ArrayList<int[]> mminus = new ArrayList<>();

        mminus.add(new int[]{6, 1, bias});
        mminus.add(new int[]{7, 3, bias});
        mminus.add(new int[]{8, 2, bias});
        mminus.add(new int[]{9, 0, bias});

        mplus.add(new int[]{8, 4, bias});
        mplus.add(new int[]{8, 6, bias});
        mplus.add(new int[]{9, 2, bias});
        mplus.add(new int[]{9, 5, bias});

        perzeptron(mplus,mminus);

    }

    private static int[] perzeptron(ArrayList<int[]> mplus, ArrayList<int[]> mminus){
        boolean changed;
        int iteration = 1;
        int[] w_vector = new int[]{1,1,1};
        print(w_vector,"Start weight vector");
        System.out.println();
        do{
            System.out.println("=======================ITERATION "+iteration+"=======================================");
            changed = false;
            for (int[] i:mplus) {
                if(calcWX(w_vector,i) < 0){
                    w_vector = add(w_vector,i);
                    changed = true;
                    print(w_vector,"new Weigth vector");
                    print(i,"  |  added     ");
                    System.out.println();
                }
            }
            for (int[] i:mminus) {
                if(calcWX(w_vector,i) >= 0){
                    w_vector = sub(w_vector,i);
                    changed = true;
                    print(w_vector,"new weight vector");
                    print(i,"  |  subtracted");
                    System.out.println();
                }
            }
            iteration++;
            System.out.println();
        }while(changed == true);
        print(w_vector,"=======================FINISHED============================================\n"+"weight Vector after "+iteration+" iterations");
        System.out.println();
        return w_vector;
    }



    private static int calcWX(int[] w_vector, int[] i_vector)
    {
        int wx = 0;
        for (int i = 0;i < w_vector.length;i++) { wx = wx + (w_vector[i] * i_vector[i]); }
        return wx;
    }

    private static int[] add(int[] w_vector, int[] i_vector)
    {
        for (int i = 0;i < w_vector.length;i++) { w_vector[i] = w_vector[i] + i_vector[i]; }
        return w_vector;
    }

    private static int[] sub(int[] w_vector, int[] i_vector)
    {
        for (int i = 0;i < w_vector.length;i++) { w_vector[i] = w_vector[i] - i_vector[i];}
        return w_vector;
    }

    private static void print(int[] vector, String name){
        System.out.print(name + ":");
        for (int i:vector) { System.out.printf(" %5d",i);}
    }


}




