package cn.zhaojishun.javaBase.java8新特性.流库;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: javaBaseLearn
 * @description:
 * @author: Jishun Zhao
 * @create: 2020-02-23 09:45
 */
public class Main {

    public static void main(String[] args) {
        //集合、MySQL 本质就是存储东西的；
        //计算都应该交给流来操作


        /**
         * 现在有5个用户！筛选：
         * 1、ID 必须是奇数
         * 2、年龄必须大于23岁
         * 3、用户名转为大写字母
         * 4、用户名字母倒着排序
         * 5、只输出一个用户！
         */
        User u6 = User.builder().id(6).name("田七").age(7).type(1).build();
        User u1 = User.builder().id(1).name("张三").age(15).type(1).build();
        User u2 = User.builder().id(2).name("李四").age(18).type(1).build();
        User u3 = User.builder().id(3).name("王五").age(80).type(2).build();
        User u4 = User.builder().id(4).name("赵六").age(2).type(2).build();
        User u5 = User.builder().id(5).name("田七").age(7).type(3).build();
        //存储数据
        List<User> userList = Arrays.asList(u1,u2,u3,u4,u5,u6);
        Map<Integer, List<User>> collect = userList.stream()
                .sorted(Comparator.comparingInt(User::getAge))
                .collect(Collectors.groupingBy(User::getType));
        System.out.println(collect);

        //按时间分组
        /*Map<String, List<File>> collect = Arrays.stream(files1).collect(Collectors.groupingBy(item -> new SimpleDateFormat("yyyy-MM-dd").format(new Date(item.lastModified()))));*/


        //使用流计算
        //Collection 接口的stream 方法可以将任何集合转换为一个流
        /*userList.stream()
                .filter((u)->{return u.getId() % 2 != 0;})
                .filter((u)->{return u.getAge() > 23;})
                .map((u)->{return u.getName().toUpperCase();})
                .sorted((uu1,uu2)->{return uu1.compareTo(uu2);})
                .limit(1)
                .forEach(System.out::print);*/


        //操作流的典型步骤
        //1、创建一个流
        //2、指定将初始流转换成其他流的中间操作，可能包含多个步骤
        //3、应用终止操作，从而产生结果
       /* long count = userList.stream().filter((u) -> u.getAge() > 20).count();
        System.out.println(count);*/
        //使用并行流
        /*long count1 = userList.parallelStream().filter(u -> u.getAge() < 10).count();
        System.out.println(count1);*/
        //Stream<T> filter(Predicate<? super T> predicate) 返回一个流，其中元素满足predicate
        //long count() 产生当前元素的数量
        //stream() 顺序流 parallelStream() 并行流

        //使用map()方法按照某种方式转换流中的值 下面是提取姓名中的姓
        //可以使用方法引用，也可以使用lambda表达式
        /*userList.stream().map(s -> s.getName().substring(0,1)).forEach(System.out::println);*/

        //limit() 在n个元素后结束并返回流
        //生成10个随机数
/*
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
*/

        //skip() 丢弃前n个元素
        // 例子去掉年龄最小的元素
        /*userList.stream()
                .sorted((uu1 , uu2) -> {return uu1.getAge() > uu2.getAge() ? 1 : -1;})
                .skip(1)
                .forEach(System.out::println);*/

        //链接流
        /*Stream.concat(Stream.of(12,13,14),Stream.of(15,16,1800))
                .forEach(System.out::println);*/

        //调试，.peek方法会产生另一个流，他的元素与原来流中元素相同，但是在每次获取一个元素时
        //都会调用一个函数，这对于调试很方便
        /*Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());*/


        //将排序结果收集到数据结构中 List Map Set
        /*User[] users = userList.stream()
                .sorted((uu1, uu2) -> {
                    return uu1.getAge() > uu2.getAge() ? 1 : -1;
                })
                .toArray(User[]::new);
*/
        /*Map<Integer, List<User>> collect = userList.stream()
                .sorted((uu1, uu2) -> {
                    return uu1.getAge() > uu2.getAge() ? 1 : -1;
                })
                .collect(Collectors.groupingBy(User::getType));
        System.out.println(collect);*/

        /*Set<User> collect1 = userList.stream().collect(Collectors.toSet());
        Map<Integer, User> collect2 = userList.stream().collect(
                Collectors.toMap(User::getId, Function.identity()));*/






    }


}