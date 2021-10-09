package cn.zhaojishun.javaBase.并发.线程.变量的线程安全;

import java.util.ArrayList;

/**
 * @Title:
 * @Description: 变量的线程安全
 * @author 
 * @date  
 */
public class Thread01 {


    public void method1(int loopNumber) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < loopNumber; i++) {
// { 临界区, 会产生竞态条件
            method2(list);
            method3(list);
// } 临界区
        }
    }

    private void method2(ArrayList<String> list) {
        list.add("1");
    }
    private void method3(ArrayList<String> list) {
        list.remove(0);
    }


    static final int THREAD_NUMBER = 2;
    static final int LOOP_NUMBER = 200;
    public static void main(String[] args) {
        Thread01 test = new Thread01();
        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(() -> {
                test.method1(LOOP_NUMBER);
            }, "Thread" + i).start();
        }



    }
}
