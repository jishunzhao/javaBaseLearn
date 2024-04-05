package cn.zhaojishun.javaBase.基础.泛型;

import java.util.List;

//泛型的本质是为了类型参数化
// （在不创建新的类型的情况下，通过泛型指定的不同类型来控制形参具体限制的类型）
// 不一定取名是T，可以是任意名字。可以传多个泛型，如<T,M,K>
public interface MyListInterface<T> {

    //泛型接口

    void add(T ... t);

    List<T> getList();

    <P> T getL(T t);
}
