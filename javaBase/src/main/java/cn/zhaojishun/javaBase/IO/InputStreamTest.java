package cn.zhaojishun.javaBase.IO;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * @program: javaBaseLearn
 * @description: InputStreamTest
 * @author: Shunji Zhao
 * @create: 2020-02-02 10:51
 */
public class InputStreamTest {

    private static String ac;

    public static void main(String[] args) throws IOException {

        readFile("F:\\project\\javaBaseLearn\\javaBase\\src\\main\\java\\cn\\zhaojishun\\javaBase\\IO\\text");

        //SecondReader("F:\\project\\javaBaseLearn\\javaBase\\src\\main\\java\\cn\\zhaojishun\\javaBase\\IO\\text");
    }

    /**
     * @Description: 完整地读取一个FileInputStream的所有字节
     * @Param:
     * @return:
     * @Author:
     * @Date:
     */

    public static void readFile(String path) throws IOException {
        try (InputStream input = new FileInputStream(path)) {
            int n;
            while ((n = input.read()) != -1) {
                System.out.println(n);
            }
        } // 编译器在此自动为我们写入finally并调用close()


    }
    public static void SecondReader(String pa) throws IOException {
        FileReader fr = new FileReader(pa);
        //定义一个字符数组，用于存储读到字符
        //该reader(char[])返回读到的字符个数。
        char[] buf = new char[1024]; //2k
        int num = 0;
        while ((num=fr.read(buf))!=-1)
            System.out.print(new String(buf,0,num));
        fr.close();
    }
}