package cn.zhaojishun.javaBase.并发.juc;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Slf4j
public class ConcurrentHashMapTest {

    static final String ALPHA = "abcedfghijklmnopqrstuvwxyz";
    static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {

        //generateTestData();
        //不是线程安全
        /*demo(
                // 创建map 集合
                // 创建ConcurrentHashMap 对不对？
                () -> new ConcurrentHashMap<String, Integer>(),
                // 进行计数
                (map, words) -> {
                    for (String word : words) {
                        Integer counter = map.get(word);
                        int newValue = counter == null ? 1 : counter + 1;
                        map.put(word, newValue);
                    }
                }
        );*/

        demo(
                () -> new ConcurrentHashMap<String, LongAdder>(),
                (map, words) -> {
                    for (String word : words) {
                        // 注意不能使用putIfAbsent，此方法返回的是上一次的value，首次调用返回null
                        map.computeIfAbsent(word, (key) -> new LongAdder()).increment();
                    }
                }
        );


    }

    public static void generateTestData() {
        int length = ALPHA.length();
        int count = 200;
        List<String> list = new ArrayList<>(length * count);
        for (int i = 0; i < length; i++) {
            char ch = ALPHA.charAt(i);
            for (int j = 0; j < count; j++) {
                list.add(String.valueOf(ch));
            }
        }
        Collections.shuffle(list);
        for (int i = 0; i < 26; i++) {
            try (PrintWriter out = new PrintWriter(
                    new OutputStreamWriter(
                            new FileOutputStream("tmp/" + (i + 1) + ".txt")))) {
                String collect = list.subList(i * count, (i + 1) * count).stream()
                        .collect(Collectors.joining("\n"));
                out.print(collect);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static <V> void demo(Supplier<Map<String, V>> supplier,
                                 BiConsumer<Map<String, V>, List<String>> consumer) {
        Map<String, V> counterMap = supplier.get();
        List<Thread> ts = new ArrayList<>();
        for (int i = 1; i <= 26; i++) {
            int idx = i;
            Thread thread = new Thread(() -> {
                List<String> words = readFromFile(idx);
                consumer.accept(counterMap, words);
            });
            ts.add(thread);
        }
        ts.forEach(t -> t.start());
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        });
        System.out.println(counterMap);
    }

    public static List<String> readFromFile(int i) {
        String path = ConcurrentHashMapTest.class.getClassLoader().getResource("").getFile() + "tmp/" + i + ".txt";
        ArrayList<String> words = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            while (true) {
                String word = in.readLine();
                if (word == null) {
                    break;
                }
                words.add(word);
            }
            return words;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

