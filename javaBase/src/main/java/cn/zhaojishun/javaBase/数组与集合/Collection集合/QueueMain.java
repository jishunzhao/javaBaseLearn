package cn.zhaojishun.javaBase.数组与集合.Collection集合;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: javaBaseLearn
 * @description:
 * @author: Jishun Zhao
 * @create: 2020-02-22 12:28
 */
public class QueueMain {

    //main 函数
    public static void main(String[] args) {
//创建队列
        Queue<String> queue = new LinkedList<>();
//add
        queue.add("语文");
        queue.add("数学");
//添加元素offer，和add 的区别是不抛异常
        queue.offer("语文");
        queue.offer("物理");
        queue.offer("英语");
//输出
        print(queue);
        print("------------分隔号--------------");
//返回第一个元素，并在队列中删除
//先进先出原则
        print("弹出--poll=>>>"+queue.poll());
//输出
        print(queue);
        print("------------分隔号--------------");
//返回第一个元素，只取不删除
        print("element=>>>"+queue.element());

        print(queue);
    print("------------分隔号--------------");
    //返回第一个元素和element 的区别是不抛异常
    print("peek=>>>"+queue.peek());
    print(queue);
}
    /**
     * 打印集合方法
     * @param collection 注意类型是集合Collection
     */
    public static void print(Collection collection) {
        print ("printList==>输出结果：");
//可以用forEach 简化打印
        collection.forEach (System.out::println);
    }
    /**
     * 打印对象方法
     * @param object 注意类型是对象Object
     */
    public static void print(Object object) {
        System.out.println (object);
    }

}