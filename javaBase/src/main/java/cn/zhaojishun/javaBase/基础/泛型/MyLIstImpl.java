package cn.zhaojishun.javaBase.基础.泛型;

import java.io.Serializable;
import java.util.*;

/**
 * @program: javaBaseLearn
 * @description:
 * @author: Jishun Zhao
 * @create: 2020-02-22 11:14
 */
public class MyLIstImpl<T> implements MyListInterface<T> {
    //泛型类
    List list;
    HashMap map;
    ArrayList li1;

    public MyLIstImpl() {
        list = new ArrayList<>();
    }

    //泛型方法可以在方法参数的类型定义泛型，也可以在返回值时定义类型。
    @Override
    public void add(T ... t) {
        list.addAll(Arrays.asList(t));
    }

    @Override
    public List<T> getList() {
        return list;
    }

    @Override
    public <P> T getL(T t) {
        return t;
    }

    public static <Q extends Runnable , P extends Comparable & Serializable> List<String> create(Q a){
        return new ArrayList<String>();
    }

}