package cn.zhaojishun.javaBase.并发.模式;


import lombok.extern.slf4j.Slf4j;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**  
 * @Title: 同步模式之保护性暂停 多任务
 * @Description: TODO
 * @author 
 * @date  
 */
public class GuardedObjectMoreTask {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new People().start();
        }
        Thread.sleep(1000);
        for (Integer id : Mailboxes.getIds()) {
            new Postman(id, "内容" + id).start();
        }

    }

}

@Slf4j
class GuardedObject <T> {

    // 标识Guarded Object
    private int id;
    //结果信息
    private T response;

    public GuardedObject(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    //获取结果
    public T getResponse() {
        synchronized (this) {
            //条件不满足一直等待
            while (response == null) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return response;
        }
    }

    //获取结果 ， 带超时时间
    public Object get(long millis) {
        synchronized (this) {
// 1) 记录最初时间
            long begin = System.currentTimeMillis();
// 2) 已经经历的时间
            long timePassed = 0;
            while (response == null) {
// 4) 假设millis 是1000，结果在400 时唤醒了，那么还有600 要等
                long waitTime = millis - timePassed;
                log.info("waitTime: {}", waitTime);
                if (waitTime <= 0) {
                    log.info("break...");
                    break;
                }
                try {
                    this.wait(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
// 3) 如果提前被唤醒，这时已经经历的时间假设为400
                timePassed = System.currentTimeMillis() - begin;
                log.info("timePassed: {}, object is null {}",
                        timePassed, response == null);
            }
            return response;
        }
    }

    //放置结果
    public void complete(T r) {
        synchronized (this) {
            //条件满足 通知线程
            this.response = r;
            this.notifyAll();
        }
    }
}

//中间解耦类
class Mailboxes {

    private static Map<Integer, GuardedObject> boxes = new Hashtable<>();
    private static int id = 1;
    // 产生唯一id
    private static synchronized int generateId() {
        return id++;
    }
    public static GuardedObject getGuardedObject(int id) {
        return boxes.remove(id);
    }
    public static GuardedObject createGuardedObject() {
        GuardedObject go = new GuardedObject(generateId());
        boxes.put(go.getId(), go);
        return go;
    }
    public static Set<Integer> getIds() {
        return boxes.keySet();
    }
}

//业务类
@Slf4j
class People extends Thread{
    @Override
    public void run() {
// 收信
        GuardedObject guardedObject = Mailboxes.createGuardedObject();
        log.info("开始收信id:{}", guardedObject.getId());
        Object mail = guardedObject.get(5000);
        log.info("收到信id:{}, 内容:{}", guardedObject.getId(), mail);
    }
}

@Slf4j
class Postman extends Thread {
    private int id;
    private String mail;
    public Postman(int id, String mail) {
        this.id = id;
        this.mail = mail;
    }
    @Override
    public void run() {
        GuardedObject guardedObject = Mailboxes.getGuardedObject(id);
        log.info("送信id:{}, 内容:{}", id, mail);
        guardedObject.complete(mail);
    }
}



