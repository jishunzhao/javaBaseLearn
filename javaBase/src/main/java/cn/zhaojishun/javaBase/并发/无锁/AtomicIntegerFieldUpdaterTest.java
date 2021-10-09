package cn.zhaojishun.javaBase.并发.无锁;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @Title: 字段更新器
 * @Description: TODO
 * @author 
 * @date  
 */
@Slf4j
public class AtomicIntegerFieldUpdaterTest {

    public static void main(String[] args) {
        User u = new User(null);

        AtomicReferenceFieldUpdater updater = AtomicReferenceFieldUpdater.newUpdater(User.class , String.class , "name");

        log.info(String.valueOf(updater.compareAndSet(u , null , "李四")));
        System.out.println(u);
    }

}
class User{
    volatile String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
