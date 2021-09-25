package cn.zhaojishun.javaBase.基础.枚举;

import java.util.Arrays;

/**
 * @program: javaBaseLearn
 * @description:
 * @author: Jishun Zhao
 * @create: 2020-02-22 18:27
 */
public class Main {

    public static void main(String[] args) {

        Size s = Enum.valueOf(Size.class,"SAMIL");

        System.out.println(s.toString());

        System.out.println(s == Size.SAMIL);

        System.out.println(s.getAbbreviation());
        //Size.values 返回包含全部枚举值的数组
        System.out.println(Arrays.toString(Size.values()));
        //返回声明枚举常量的位置 ， 从0开始
        System.out.println(Size.EXTRA_LARGE.ordinal());
    }

}