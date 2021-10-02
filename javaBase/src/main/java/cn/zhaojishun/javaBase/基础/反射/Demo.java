package cn.zhaojishun.javaBase.基础.反射;

import java.lang.reflect.Field;

/**
 * @author 顺
 * @Title: Demo
 * @ProjectName javaBaseLearn
 * @Description: TODO
 * @date 2020/1/2712:33
 */
public class Demo {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Dog d = new Dog();
        d.setAge(1);
        d.setName("Dogwhh");
        //返回类的名字
        System.out.println(d.getClass().getName());


        //可以调用静态方法forName 获得类名对应的Class 对象。
        String dassName = "java.util.Random";
        Class cl = Class.forName(dassName) ;

        //根据类名创建对象 newlnstance 方法调用默认的构造器（没有参数的构造器）初始化新创建的对象
        String className = "cn.zhaojishun.javaBase.基础.反射.Dog";
        Class c2 = Class.forName(className);

        Object o = c2.newInstance();

        //getFields 方法将返回一个包含Field 对象的数组
        Field[] field = Class.forName(className).getDeclaredFields();

        System.out.println(field.length);

    }


}
