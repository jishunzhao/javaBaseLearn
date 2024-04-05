package cn.zhaojishun.javaBase.基础.日期时间;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 * @program: javaBaseLearn
 * @description:
 * @author: Jishun Zhao
 * @create: 2020-02-22 14:37
 */
public class Main {

    /**
     * 例如:2018-12-28
     */
    public static final String DATE = "yyyy-MM-dd";
    /**
     * 例如:2018-12-28 10:00:00
     */
    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    /**
     * 例如:10:00:00
     */
    public static final String TIME = "HHmmss";
    /**
     * 例如:10:00
     */
    public static final String TIME_WITHOUT_SECOND = "HH:mm";

    /**
     * 例如:2018-12-28 10:00
     */
    public static final String DATE_TIME_WITHOUT_SECONDS = "yyyy-MM-dd HH:mm";

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        //获得此刻时间
        System.out.println("当前月份"+now.getMonthValue());
        System.out.println("当前日"+now.getDayOfMonth());
        System.out.println("当前小时"+now.getHour());
        System.out.println("当前分钟"+now.getMinute());
        System.out.println("当前秒"+now.getSecond());
        //构造时间
        LocalDateTime of = LocalDateTime.of(2020, Month.JANUARY, 12, 8, 20, 5);
        System.out.println(of);
        //日期格式化
        System.out.println(now.format(DateTimeFormatter.ISO_DATE));
        System.out.println(now.format(DateTimeFormatter.BASIC_ISO_DATE ));
        System.out.println(now.format(DateTimeFormatter.ofPattern(DATE_TIME)));
        Instant now1 = Instant.now();
    }

}