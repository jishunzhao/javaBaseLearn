package cn.zhaojishun.javaBase.实践.反射模拟注入框架;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Container container = new Container();
        container.init();

//        Object customer = container.getBean(Class.forName("cn.zhaojishun.javaBase.实践.反射模拟注入框架.Customer"));
//        Object customer1 = container.getBean(Class.forName("cn.zhaojishun.javaBase.实践.反射模拟注入框架.Customer"));
//        System.out.println(customer);
//        System.out.println(customer1);
        Class<?> orderClass = Class.forName("cn.zhaojishun.javaBase.实践.反射模拟注入框架.Order");
        Object o = container.createInstance(orderClass);
        //获取服务中custome
        Field customer = orderClass.getDeclaredField("address");
        customer.setAccessible(true);
        Object fieldValue = customer.get(o);
        System.out.println(fieldValue);

    }
}
