package cn.zhaojishun.javaBase.网络;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @program: javaBaseLearn
 * @description:
 * @author: Jishun Zhao
 * @create: 2020-07-27 11:59
 */
public class Main {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://m.zhaojishun.cn/MTImg/index3.html");
        System.out.println(url.getHost());

        String hostIP = "http://m.zhaojishun.cn/MTImg/index3.html";
        hostIP = hostIP.replace("http://","").replace("https://","");//去除http和https前缀
        String [] arr = hostIP.split("/");//按‘/’分隔，取第一个
        hostIP = arr[0];

        System.out.println(hostIP);

    }

}