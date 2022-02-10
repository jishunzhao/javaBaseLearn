package cn.zhaojishun.javaBase.并发.线程.线程池;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**  
 * @Title: 线程池各种提交任务方法
 * @Description: TODO
 * @author 
 * @date  
 */
@Slf4j
public class SubmitTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        //带返回值的提交
        //submitTest(pool);
        //并行执行
        //invokeAll(pool);
        //哪个任务先成功执行完毕，返回此任务执行结果，其它任务取消
        invokeAnyTest(pool);
    }

    private static void invokeAnyTest(ExecutorService pool) throws InterruptedException, ExecutionException {
        String o = pool.invokeAny(Arrays.asList(
                () -> {
                    log.info("T1");
                    return "1";
                },
                () -> {
                    log.info("T2");
                    Thread.sleep(2000);
                    return "2";
                },
                () -> {
                    log.info("T3");
                    Thread.sleep(3000);
                    return "3";
                },
                () -> {
                    log.info("T4");
                    Thread.sleep(4000);
                    return "4";
                }
        ));
        log.info("taskList frist return value :{}", o);
    }

    private static void invokeAll(ExecutorService pool) throws InterruptedException {
        List<Future<String>> futures = pool.invokeAll(Arrays.asList(
                () -> {
                    log.info("T1");
                    return "1";
                },
                () -> {
                    log.info("T2");
                    Thread.sleep(2000);
                    return "2";
                },
                () -> {
                    log.info("T3");
                    Thread.sleep(3000);
                    return "3";
                },
                () -> {
                    log.info("T4");
                    Thread.sleep(4000);
                    return "4";
                }
        ));
        futures.forEach((str)->{
            try {
                log.info("return: {}", str.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    //submit
    private static void submitTest(ExecutorService pool) throws InterruptedException, ExecutionException {
        Future<String> submit = pool.submit(() -> {
            log.info("task....");
            Thread.sleep(2000);
            return "1";
        });
        String s = submit.get();
        log.info("return: {}", s);
    }
}
