package com.telran;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws InterruptedException {
	    //synchronized {
        //      single thread at a time
        //}

        Counter counter = new Counter(0);

//        List<ThreadCounter> list = new ArrayList<>();
//
//        list.add(new ThreadCounter(counter));
//        list.add(new ThreadCounter(counter));
//        list.add(new ThreadCounter(counter));
//
//        list.forEach(Thread::start);
//
//
//        for (ThreadCounter threadCounter : list) {
//            threadCounter.join();
//        }

        long start = System.currentTimeMillis();
        Stream.generate(() -> new ThreadCounter(counter))
                .limit(100)
                .peek(x -> x.start())
                .forEach(x -> {
                    try {
                        x.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

        long end = System.currentTimeMillis();

        System.out.println("Total time milliseconds: " + (end - start));
        System.out.println(counter.getValue());

        //synchronized | MUTEX | Monitor
        //MUTEX - Mutual Exclusion (Взаимное Исключение)
        //Monitor - each reference type (Integer, String, Person ..)
        //boolean isLocked = true
        //Intrinsic Lock

        //Race Condition - состояние
        //Critical Section - участок кода, где может возникнуть Race Condition

        //synchronized(monitor) {}
        //synchronized method (this)
        //synchronized static method (Counter.class)

        Class counterClass = Counter.class;
        counterClass.getMethods();

        //Class (Counter.class) - object, created by Java for every class in the project
        //This object is UNIQUE for each class

        ThreadCounter.staticMethod();
        new ThreadCounter(counter).staticMethod();
    }
}

class Counter {
    private AtomicInteger value;
//    private Integer value;

    public Counter(Integer value) {
        this.value = new AtomicInteger(value);
//        this.value = value;
    }

    public Integer getValue() {
        return value.get();
//        return value;
    }

    public void increment() {
        this.value.incrementAndGet();
//        this.value++;
    }
}

class ThreadCounter extends Thread {
    private Counter counter;
    public ThreadCounter(Counter counter) {
        this.counter = counter;
    }

    public static synchronized void staticMethod() {
        //synchronized(ThreadCounter.class) {
        //
        //}
    }
    public synchronized void instanceMethod() {
        //synchronized(this) {
        //
        //}
    }


    @Override
    public void run() {
        //3 датчика/холодильник
        //холодильников - 100

        //3 * 100 = 300 статус сообщений/сек


        //monitor.isLocked = true;
//        synchronized (monitor) {
        //Thread 2
        for (int i = 0; i < 10_000_000; i++) {
            counter.increment();
        }
//        }
        //Thread 1
    }
}


class CpuPredictionExample {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        long start = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {

            list.add(i);

            // 1000
            // 1600

        }
        long end = System.currentTimeMillis();

        System.out.println(end - start);

        list = new ArrayList<>();

        start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
            //Thread.sleep(1);
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
