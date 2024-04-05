package cn.zhaojishun;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.Scanner;

public class Test2 implements Serializable {
    static double x;

    public static void main(String[] args) {
       int a[] = new int[1];
       a[1] = 1;
       System.out.println(a[1]);
    }

    private static void test_2022() {
        int a[][] = {{1,2,3,4},
                {12,13,14,5},
                {11,16,15,6},
                {10,9,8,7}};
        int i,j;

        for (i = 0; i < a.length/2; i++){
            for (j = i; j < a.length-i; j++){
                System.out.print(a[i][j]+" ");
            }
            for (j = i+1; j < a.length-i; j++){
                System.out.println(a[j][a.length-1-i]);
            }
        }
    }

    public static double Test2104_32(int n){
        double s = 1;
        if (n <= 0) return -1;
        for (int i = 1; i <= n; i++) {
            s += 1.0/(i*3);
        }
        return s;
    }

    private static void test2010_31() {
        Runnable r = ()->{
            try {
                Thread.sleep(400);
                System.out.println("1");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("3");
        };
        Thread t = new Thread(r);
        Thread t1 = new Thread(r);
        t.start();
        t1.start();
        try {
            System.out.println("a");
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("b");
    }

    public static void Test2204_32() {
        int size = 11;
        int middle = size/2;
        int[][] arr = new int[size][size];

        for (int i = 0; i < size; i++) {
            if (i != middle){
                arr[middle][i] = 1;
                arr[i][middle] = 1;
            }
        }
        for (int i = 0; i < size; i++) {
            arr[i][i] = 1;
            if (i != middle){
                arr[i][size-i-1] = 1;
            }
        }

        for (int k = 0; k<size; k++){
            for (int l = 0; l<size; l++){
                System.out.print(arr[k][l]+" ");
            }
            System.out.println();
        }

    }

    public static void Test2204_26() {
        int[][] array = new int[5][5];
        int i,j;
        for (i = 0; i<5; i++) array[0][i] = i+1;
        for (i = 1; i<5; i++) array[i][0] = i+1;
        for (i = 1; i<5; i++)
            for (j = 1; j<5; j++)
                array[i][j] = array[i][j-1] + array[i-1][j];

        for (int k = 0; k<5; k++){
            for (int l = 0; l<5; l++){
                System.out.print(array[k][l]+" ");
            }
            System.out.println();
        }
    }


    //202210_32p
    public static int sDiagonal(int[][] arr){
        //先算一个对角线，再算另一个，再相加
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i][i] + arr[arr.length-1-i][i];
        }
        return sum;
    }



    //2210_26
    public static boolean isPalindrome(int x) {
        //看完这道题，首先想到的是跟昨天的反转整数类似的想法，
        // 即反转之后判断跟原数是否相等，即相等就是回文数；
        //先排除几种特殊情况：
        if(x<0){
            return false;
        }
        if(x==0){
            return true;
        }
        //主要思想 反转int 如果反转后的数和原数相等 那么就是回文数
        int rev= 0; //反转后的数字
        int tmp = x;//12345
        while (tmp!=0){
            int p = tmp%10;//取出个位数12345--> 5
            tmp=tmp/10;    //缩小10倍  12345--> 1234
            rev = rev * 10 +p ;//反转后的数字乘10 再加上个位数 5 * 10 + 4 = 54
        }
        return rev ==x;
    }

    private void Test2110_26() {
        int i,j;
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入。。。");
        i = scanner.nextInt();
        while (i!=0){
            j = i % 10;//123
            System.out.print(j);
            i /= 10;
        }
    }

    private void Test211032() {
        int n = 5;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i ; j++)
                System.out.print(" ");
            for (int k = 0; k < n-i; k++)
                System.out.print("*");
            System.out.println();
        }
    }
}
class Test202210_33 extends WindowAdapter implements ActionListener, MouseMotionListener{
    JFrame jf;JPanel jp;JTextArea taText;
    JRadioButton rbUppercase, rbLowercase;
    JButton cButton;
    Font font;

    public void go() {
        jf = new JFrame("大小写转换");jf.setSize(500,300);
        Container c = jf.getContentPane();
        c.setLayout(new BoxLayout(c,BoxLayout.Y_AXIS));
        taText = new JTextArea("请输入文本",5,10);
        //测试鼠标事件
        //taText.addMouseListener(new MouseHandler());
        jp = new JPanel();
        jp.setLayout(new GridLayout(1,2));
        rbUppercase = new JRadioButton("大写");
        rbLowercase = new JRadioButton("小写");
        Graphics graphics = jf.getGraphics();

        JCheckBox x = new JCheckBox("666");
        //x.addActionListener((e)->System.out.println(e.getActionCommand()));
        x.addItemListener((e)->System.out.println("321"));

        jp.add(x);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rbLowercase);
        bg.add(rbUppercase);
        jp.add(rbLowercase);
        jp.add(rbUppercase);
        cButton = new JButton("转换");
        cButton.addActionListener(this);
        jf.add(taText);
        jf.add(jp);
        jf.add(cButton);
        jf.addWindowListener(this);
        jf.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        System.out.println(e.getSource());
        String text = taText.getText();
        if (rbUppercase.isSelected())
            taText.setText(text.toUpperCase());
        if (rbLowercase.isSelected())
            taText.setText(text.toLowerCase());
    }

    @Override
    public void windowClosing(WindowEvent e) {
        super.windowClosing(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    class MouseHandler extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("点击了");
            System.out.println(e.getX());
            System.out.println(e.getY());
            System.out.println(e.getClickCount());
            //super.mouseClicked(e);
        }
    }
}
