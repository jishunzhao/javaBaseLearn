package cn.zhaojishun.javaBase.java8新特性.optional;

import java.util.Optional;

/**
 * @program: javaBaseLearn
 * @description:
 * @author: Jishun Zhao
 * @create: 2020-02-25 10:49
 */
public class Main {

    public static void main(String[] args) {
        //创建一个空的Optional 对象
        Optional<Object> optionalBox = Optional.empty();
        //是否包含值 false
        System.out.println(optionalBox.isPresent());
        String s = "1";
        //将盒子里装东西 注意of方法只能放确定不是null 的值 否则NPE
        Optional<String> optionalBox2 = Optional.of(s);
        //如果不确定要装的东西是不是会null那么用 ofNullable
        String str1 = null;
        Optional<String> optionalBox1 = Optional.ofNullable(str1);
        //从盒子里取出值 这并不是Optional 推荐的做法，因为如果盒子里装的是null则会 抛出NoSuchElementException
//        String s2 = optionalBox1.get();
//        System.out.printf(s2);
        //如果盒子里有东西就返回，如果没有就返回指定的东西
        String s2 = optionalBox1.orElse("33");
        System.out.println(s2);
        //函数式返回
        String s3 = optionalBox1.orElseGet(() -> "44");
        System.out.println(s3);
        //orElse 与 orElseGet 区别
        // 如果orElse的参数是返回实例化一个对象的话，无论盒子是否是空，这个对象都会背创建
        // 也就是说，当orElse有值时我们其实期望它不用执行参数里的语句，但实际上也会去执行new对象
        // 当获取默认值的代价较高时使用 orElseGet

        //如果盒子为空那么抛出一个异常，也可以自己提供异常
        //optionalBox1.orElseThrow(()->new RuntimeException("box is null"));

        //如果盒子有值，执行一段逻辑
        optionalBox2.ifPresent(e-> System.out.println(e));
        //盒子有值和没值分别执行逻辑
        //optionalBox2.ifPresentOrElse(e-> System.out.println(e),()-> System.out.println("box is null"));

        //使用 map对盒子内容进行变换，如果盒子是空的则什么都不会做
        Optional<Long> aLong = optionalBox2.map(e -> Long.valueOf(e));
        //如过方法返回的是嵌套的 Optional 则可以用flatMap方法进行扁平化

        // Optional 不适合的情景
        // 1、不适合作为类的成员 因为Optional会增加内存消耗 并且使得类的序列化变得复杂
        // 2、不适合用作方法的参数
        // 3、不适合用做构造函数的参数
        // 4、不适合包装一个集合 因为集合已经很好的能处理空集合的情况
        // 5、不适合使用get方法
    }

}