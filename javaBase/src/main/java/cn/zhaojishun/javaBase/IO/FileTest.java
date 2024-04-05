package cn.zhaojishun.javaBase.IO;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @program: javaBaseLearn
 * @description: file
 * @author: Shunji Zhao
 * @create: 2020-02-02 10:16
 */
public class FileTest {
    public static void main(String[] args) throws IOException {
        /*构造File对象时，既可以传入绝对路径，也可以传入相对路径。绝对路径是以根目录开头的完整路径，例如：*/
        /*注意Windows平台使用\作为路径分隔符，在Java字符串中需要用\\表示一个\。Linux平台使用/作为路径分隔符：*/
        /*真实环境中建议写绝对路径*/
        String windowFilePath = "F:\\testimg\\2020\\9\\10\\5601\\7344266.jpg";
        //String linuxFilePath = "cn/zhaojishun/javaBase/IO/text";
        File f = new File(windowFilePath);
        System.out.println(f.getParent());
        /*路径测试*/
        System.out.println(f.getPath());
        System.out.println(f.getAbsolutePath());
        System.out.println(f.getCanonicalPath());

        /*判断是否存在*/
        System.out.println(f.exists());
        System.out.println(f.isFile());
        System.out.println(f.isDirectory());

        /*文件权限相关*/
        System.out.println(f.canRead());
        System.out.println(f.canWrite());
        System.out.println(f.canExecute());//对目录而言，是否可执行表示能否列出它包含的文件和子目录。

        /*文件字节大小*/
        System.out.println(f.length());

        /*创建和删除文件*/
        File file = new File("F:\\test.test");
        if(file.createNewFile()){
            System.out.println("创建成功");
            if (file.delete()) {
                System.out.println("删除成功");
            }
        }
        /*创建临时文件，并在jvm退出后删除文件*/
        File file2 = File.createTempFile("tmp-", ".txt"); // 提供临时文件的前缀和后缀
        file2.deleteOnExit(); // JVM退出时自动删除
        System.out.println(file2.isFile());
        System.out.println(file2.getAbsolutePath());

        /*遍历文件和目录*/
        /*当File对象表示一个目录时，可以使用list()和listFiles()列出目录下的文件和子目录名。listFiles()提供了一系列重载方法，可以过滤不想要的文件和目录：*/

        File file3 = new File("C:\\Windows");
        File[] fs1 = file3.listFiles(); // 列出所有文件和子目录
        printFiles(fs1);
        File[] fs2 = file3.listFiles((File dir, String name)-> { // 仅列出.exe文件
                return name.endsWith(".exe"); // 返回true表示接受该文件
        });
        printFiles(fs2);

        /*Path对象*/
        /*Java标准库还提供了一个Path对象，它位于java.nio.file包。Path对象和File对象类似，但操作更加简单：

         */
        Path p1 = Paths.get(".", "project", "study"); // 构造一个Path对象
        System.out.println(p1);
        Path p2 = p1.toAbsolutePath(); // 转换为绝对路径
        System.out.println(p2);
        Path p3 = p2.normalize(); // 转换为规范路径
        System.out.println(p3);
        File file4 = p3.toFile(); // 转换为File对象
        System.out.println(file4);
        for (Path p : Paths.get("..").toAbsolutePath()) { // 可以直接遍历Path
            System.out.println("  " + p);
        }




    }
    /** 
     * @Description: 输出文件名称 
     * @Param:  file[]
     * @return:  void
     * @Author: 
     * @Date:   
     */ 
    static void printFiles(File[] files) {
        System.out.println("==========");
        if (files != null) {
            for (File f : files) {
                System.out.println(f);
            }
        }
        System.out.println("==========");
    }
    
    
    
}