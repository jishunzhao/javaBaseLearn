package cn.zhaojishun.javaBase.设计模式.创建型模式.单例模式.懒汉式.线程安全;

/**
 * @program: javaBaseLearn
 * @description:
 * @author: Shunji Zhao
 * @create: 2020-02-08 16:26
 */
public class Singleton {

    private static volatile Singleton singleton;

    private Singleton(){}

    public static synchronized Singleton getInstance(){
        if(singleton == null){
            synchronized (Singleton.class){
                if (singleton == null){
                    singleton = new Singleton();
                }
            } 
        }
        return singleton;
    }
}