package cn.zhaojishun.javaBase.基础.泛型;

import cn.zhaojishun.javaBase.扩展.lombok.User;

/**
 * @program: javaBaseLearn
 * @description: 泛型方法的使用
 * @author: Jishun Zhao
 * @create: 2020-02-22 11:18
 */
public class Main {

    public static void main(String[] args) {


        MyListInterface<String> list = new MyLIstImpl<>();
        String p = list.getL("list");
       /* initData(list);

        list.getList().forEach(System.out::println);*/


    }

    //初始化数据
    public static void initData(MyListInterface list){
        User[] user = new User[5];
        for (int i = 0; i < 5; i++) {
            user[i] = User.builder().name("zhangsan"+i).age(15).build();
        }
        list.add(user);

    }
}