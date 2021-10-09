package cn.zhaojishun.javaBase.并发.模式;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * @Title: 异步模式之生产者消费者
 * @Description: TODO
 * @author 
 * @date  
 */
@Slf4j
class Main{
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue(4);
        //模拟生产
        for (int i = 0; i < 5; i++) {
            int t = i;
            new Thread(()->{
                Message message = new Message(t, "消息是" + t);
                messageQueue.put(message);
            } , "生产者" + t).start();
        }

        new Thread(()->{
            while (true){
                Message take = messageQueue.take();
                String s = (String)take.getMessage();
                log.info("消费了" + s);
            }
        }).start();


    }
}

public class Message {

    private int id;

    private Object message;

    public Message(int id, Object t) {
        this.id = id;
        this.message = t;
    }

    public int getId() {
        return id;
    }

    public Object getMessage() {
        return message;
    }
}

@Slf4j
class MessageQueue{
    private LinkedList<Message> queue = new LinkedList<>();
    private int capacity;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
    }

    //添加
    public void put(Message message){
        synchronized (queue){
            while (queue.size() == capacity){
                log.info("队列已满！");
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info("队列add");
            queue.add(message);
            queue.notifyAll();
        }
    }

    public Message take(){
        synchronized (queue){
            while (queue.size() == 0){
                log.info("队列没东西！");
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info("队列pop");
            Message stringMessage = queue.removeFirst();
            queue.notifyAll();
            return stringMessage;
        }
    }
}

