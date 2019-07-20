package io.yeliang;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题：素数是自然数中大于1，且只能被正整数中的1和自身整除的自然数。
 *  比如10以下的素数和为 ：2 + 3 + 5 + 7 = 17
 *  请计算2,000,000以下的所有素数和
 */
public class PrimeTest {

    public static void main(String[] args) {
        Long sum = 0L;
        long start=System.currentTimeMillis();
        List<Integer> primes = prime(2000000);
        for(Integer p :primes){
            sum +=p;
        }
        long end=System.currentTimeMillis();
        System.out.println("time=="+(end-start));
        System.out.println("sum="+sum);
    }

    public static List<Integer> prime(int number){
        List<Integer> primes = new ArrayList<Integer>();
        op:for(int i=2;i<=number;i++){
            for(int j=2;j<=Math.sqrt(i);j++){
                if(i%j==0){
                    continue  op;
                }
            }
            primes.add(i);
        }
        return primes;
    }
}
