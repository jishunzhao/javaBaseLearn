package cn.zhaojishun.javaBase.设计模式.创建型模式.工厂模式.抽象工厂模式.抽象工厂模式数据访问;

public interface IUser {

    void insert(User user);

    User getUser(int id);
}
