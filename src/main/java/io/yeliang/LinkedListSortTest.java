package io.yeliang;

import java.util.LinkedList;

/**
 * 问题:将1，2，3，4， .....，99，100的顺序数列，排序成 100，1，99，2，98，3，......，51，50这样的非等差数列。
 * 要求:空间复杂度o(1)，时间复杂度o(n)。
        */
public class LinkedListSortTest {
    public static void main(String[] args) {

    }
    public static void exec() {
        System.out.println("--------sorted前-----------");
        LinkedList<Integer> source = create();
        for(int i=0;i<source.size();i++){
            System.out.print(source.get(i)+",");
        }
        System.out.println();
        System.out.println("-----sorted后------");
        sort(source);
        for(int i=0;i<source.size();i++){
            System.out.print(source.get(i)+",");
        }
    }

    /**
     * 时间为O(n/2),空间为O(1)
     * @param source
     */
    public static void sort(LinkedList<Integer> source){
        int index=source.size();
        int len = source.size();
        int end = source.size()-1;
        while(index>source.size()/2){
            Integer tmp = source.get(end);
            source.remove(end);
            source.add((len-index)*2,tmp);
            index--;
        }
    }

    public static LinkedList<Integer> create(){
        LinkedList<Integer> a = new LinkedList<>();
        for(int i=1;i<=100;i++){
            a.add(i);
        }
        return a;
    }
}
