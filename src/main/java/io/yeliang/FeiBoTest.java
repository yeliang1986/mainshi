package io.yeliang;

import java.util.LinkedList;

/**
 * 问题：
 *  F(n) = F(n-1) + F(n-2), where F1 =1, F2 = 2
 *  F1 =1
 *  F2 =1
 *  F3 =2
 *  F4 =3
 *  F5 =5
 *  F6 =8
 *  F7 =13
 *  F8 =21
 *  F9 =34
 *  F10=55
 *  F11=89
 *  F12=144
 *  可见index=12的F12是第一个包含3个数字的Fn，那么第一个包含1000个数字的Fn的index是多少？
 */
public class FeiBoTest {

    public static void main(String[] args) {
        Long curTime = System.currentTimeMillis();
        Long index = feibo(1000);
        System.out.println("time = "+(System.currentTimeMillis()-curTime)+"ms");
        System.out.println(index);
    }

    /**
     * 思想：采用linkedList存储大数。其中每个元素是10位字符串。
     * 大数相加时，从后至前遍历两个linkedList,对应元素依次相加，如有进位，则放入前一个元素中。
     * @param wei 位数
     */
    public static Long feibo(int wei){
        //arr1是加数
        LinkedList<String> arr1 = new LinkedList<String>();
        arr1.add("1");
        //arr2是被加数
        LinkedList<String> arr2 = new LinkedList<String>();
        arr2.add("1");
        //arr3是结果数
        LinkedList<String> arr3 = new LinkedList<String>();
        Long count = 2L;//记录目前生成斐波数字的个数
        Integer curIndex =0; //当前生成的斐波数字的位数。
        while(wei>curIndex){
            curIndex = 0;
            arr3 = add(arr1,arr2);
            for(String a : arr3){
                curIndex += a.length();
            }
            arr1 = arr2;
            arr2 = arr3;
            count ++;
            //System.out.println("curIndex="+curIndex+";count="+count+";arr3="+String.join(",",arr3));
        }
        return count;
    }


    /**
     * 根据斐波数据的规则，b永远是大于a的，
     * @param a
     * @param b
     * @return
     */
    public static LinkedList<String> add(LinkedList<String> a,LinkedList<String> b){
        //如果发现加数与被加数的列表长度不一致，先把加数的长度扩大。
        if(a.size()<b.size()){
            for(int i=0;i<b.size()-a.size();i++){
                a.addFirst("0");
            }
        }
        //初始化临时结果列表为被加数的列表。
        LinkedList<String> r = new LinkedList<>(b);
        Long borrow = 0L;//进位值
        //从后至前遍历被加数的元素
        for(int i=b.size()-1;i>=0;i--){
            Long b2 = Long.parseLong(b.get(i));
            Long a1 = Long.parseLong(a.get(i));

            Long temp = a1+b2+borrow;
            borrow = 0L;
            String tempStr  = String.valueOf(temp);
            //如果相加数大于18位，就生成一个新的元素。并记录进位值
            if(String.valueOf(temp).length()>18) {
                borrow = Long.parseLong(tempStr.substring(0,1));
                r.set(i,tempStr.substring(1));
                if(i==0){
                    r.addFirst(tempStr.substring(0,1));
                }
            }else{
                r.set(i,tempStr);
            }
        }
        return r;
    }

}
