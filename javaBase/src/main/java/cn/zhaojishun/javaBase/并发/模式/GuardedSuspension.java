package cn.zhaojishun.javaBase.并发.模式;

import lombok.extern.slf4j.Slf4j;

/**  
 * @Title: 同步模式之保护性暂停
 * @Description: TODO
 * @author 
 * @date  
 */
@Slf4j
public class GuardedSuspension {

    public static void main(String[] args) {
        Guarded<String> guarded = new Guarded<>();

        //设置结果
        new Thread(() -> {
            try {
                //模拟耗时操作
                Thread.sleep(3000);
                log.info("complete.....");
                guarded.complete("abc");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //获得结果
        String response = guarded.getResponse();
        log.info(response);
    }
}

@Slf4j
class Guarded<T> {

    private T response;
    private Object lock = new Object();
    public T getResponse() {
        synchronized (lock) {
            //条件不满足一直等待
            while (response == null) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return response;
        }
    }

    public Object get(long millis) {
        synchronized (lock) {
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
                    lock.wait(waitTime);
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


    public void complete(T r) {
        synchronized (lock) {
            //条件满足 通知线程
            this.response = r;
            lock.notifyAll();
        }
    }
}

