package cn.zhaojishun.javaBase.基础.配置文件读取;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @program: javaBaseLearn
 * @description:
 * @author: Shunji Zhao
 * @create: 2020-02-10 12:02
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Properties properties = readProperty();
        System.out.println(properties.getProperty("dbtype"));
    }

    //读取配置文件
    private static Properties readProperty() throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("db.properties").getPath()));
        return prop;
    }
}