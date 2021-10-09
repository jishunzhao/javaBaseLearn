package cn.zhaojishun.javaBase.并发.模式;

import lombok.extern.slf4j.Slf4j;

/**
 * @Title: 优雅中断线程之利用停止标记
 * @Description: TODO
 * @author 
 * @date  
 */
@Slf4j
public class TPTVolatile {
    private Thread thread;
    private volatile boolean stop = false;

    public void start(){
        thread = new Thread(() -> {
            while(true) {
                Thread current = Thread.currentThread();
                if(stop) {
                    log.info("料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    log.info("将结果保存");
                } catch (InterruptedException e) {

                }
// 执行监控操作
            }
        },"监控线程");
        thread.start();
    }
    public void stop() {
        stop = true;
        thread.interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
        TPTVolatile t = new TPTVolatile();
        t.start();
        Thread.sleep(3500);
        log.debug("stop");
        t.stop();
    }

}

