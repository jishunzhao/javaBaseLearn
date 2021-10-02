package cn.zhaojishun.javaBase.基础.异常处理;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

/**
 * @program: javaBaseLearn
 * @description:
 * @author: Shunji Zhao
 * @create: 2020-02-01 09:52
 */
public class Demo {

    public static void main(String[] args) {
        /*String m = "";
        try {
            if(m.equals("")) throw new MyException("参数为空");
        }catch (MyException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }*/

        //带资源的try语句 try块退出时，会自动调用res.close()。 可指定多个资源
        try (Scanner in = new Scanner(new FileInputStream("C:\\abc.txt"), "UTF-8");
             PrintStream out = new PrintStream("out.txt");) {
        }catch (IOException e){
            //获取异常堆栈信息
            StringWriter stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));
            String stackTrace = stringWriter.toString();
            System.out.println(stackTrace);
            //打印异常信息
            System.out.println(e.getMessage());
        }

        //所有线程的堆栈轨迹
       /* Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        for (Thread thread : allStackTraces.keySet()) {
            StackTraceElement[] stackTraceElements = allStackTraces.get(thread);
            for (StackTraceElement stackTraceElement : stackTraceElements) {
                System.out.println(stackTraceElement.toString());
            }
        }*/
    }
}