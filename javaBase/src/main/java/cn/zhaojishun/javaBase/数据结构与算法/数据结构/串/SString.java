package cn.zhaojishun.javaBase.数据结构与算法.数据结构.串;

import java.util.Arrays;

/**
 * @program: javaBaseLearn
 * @description: 顺序串
 * @author: Jishun Zhao
 * @create: 2020-07-07 18:07
 */
public class SString {

    final int MAXSIZE = 255;

    char[] data;

    int lenth;

    SString(){
        data = new char[MAXSIZE];
        lenth = 0;
    }

    void add(char elem){
        data[lenth+1] = elem;
        lenth++;
    }

    char get(int index){return data[index];}

    /** 
     * @Description:  BF匹配算法
     * @Param:  字串
     * @return:  子串在正文串中的开始下标，没有返回-1
     * @Author: 
     * @Date:   
     */ 
    int indexBF(SString t){
        int i = 1;      //主串的下标 S
        int j = 1;      //子串的下标 T

        while (i <= lenth && j <= t.lenth){
            if (data[i] == t.data[j]){ i++;j++; }   //主串和子串一次匹配下一个字符
            else {
                i = i - j + 2;                      //主串子串回溯，重新开始下一次匹配
                j = 1;
            }
        }
        //判断是否找到 如果找到 子串的下标应该大于子串的长度   , 目标下标为 主串的下标减去子串的长度
        System.out.println(i+"--"+j);
        System.out.println(i);
        if(j > t.lenth) {return i - t.lenth;}
        else return -1;
    }
    
    /** 
     * @Description: KMP 算法
     * @Param:  
     * @return:  
     * @Author: 
     * @Date: 2020-07-07
     */ 
    int indexKMP(SString t){
        int i = 1;
        int j = 1;

        int[] next = SString.getKMPNext(t);
        while (i < lenth+1 && j < t.lenth+1){
            if (j == 0 || data[i] == t.data[j]){//j == 0 也就是j回到了0位置
                i++;
                j++;
            }else {
                j = next[j];
            }
        }
        if(j > t.lenth) {return i - t.lenth;}
        else return -1;
    }
    
    /** 
     * @Description: 求next
     * @Param:  
     * @return:  
     * @Author: 
     * @Date:   
     */
    public static int[] getKMPNext(SString str){

        int[] next = new int[str.lenth+1];
        int i = 1;
        int j = 0;

        while (i < str.lenth){
            if (j == 0 || str.data[i] == str.data[j]){  //j == 0 也就是j回到了0位置
                i++;
                j++;
                next[i] = j;
            }else {
                j = next[j];
            }
        }
        return next;
    }




    public static void main(String[] args) {
       SString s = new SString();
       s.add('a');
       s.add('b');
       s.add('c');
       s.add('a');
       s.add('a');
       s.add('b');
       s.add('b');
       s.add('c');
       s.add('a');
       s.add('b');
       s.add('c');
       s.add('a');
       s.add('a');
       s.add('b');
       s.add('d');
       s.add('a');
       s.add('b');

        System.out.println(SString.getKMPNext(s));

    }

}