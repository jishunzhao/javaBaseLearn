package cn.zhaojishun.javaBase.设计模式.设计原则.依赖倒转原则.构造方法传递;

import cn.zhaojishun.javaBase.设计模式.设计原则.依赖倒转原则.构造方法传递.ITV;

/**
 * @program: javaBaseLearn
 * @description:
 * @author: Shunji Zhao
 * @create: 2020-02-07 10:53
 */
public class KangJia implements ITV {
    @Override
    public void play() {
        System.out.println("KangJia电视机，打开");
    }
}