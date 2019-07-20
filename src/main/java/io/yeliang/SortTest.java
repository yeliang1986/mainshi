package io.yeliang;

/**
 * 问题:将1，2，3，4， .....，99，100的顺序数列，排序成 100，1，99，2，98，3，......，51，50这样的非等差数列。
 * 要求:空间复杂度o(1)，时间复杂度o(n)。
        */
public class SortTest {
    public static void main(String[] args) {
        Integer[] source = create();
        for(int i=0;i<source.length;i++){
            System.out.print(source[i]+",");
        }
        System.out.println();
        sort2(source);
    }


    /**
     * 时间为O(n),空间为O(n)
     * @param source
     */
    public static void sort2(Integer[] source){
         Integer[] result = new Integer[source.length];
        for(int i=0;i<source.length/2;i++){
            result[(i*2)+1] = source[i];
        }
        for(int i=source.length-1;i>=source.length/2;i--){
            result[(99-i)*2] = source[i];
        }
        for(Integer i : result ){
            System.out.println(i);
        }
    }

    public static Integer[] create(){
        Integer[] a = new Integer[100];
        for(int i=0;i<100;i++){
            a[i]=i+1;
        }
        return a;
    }
}
