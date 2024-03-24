package com.ralf.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class MyThread {

    public static void main(String[] args) throws InterruptedException {
        FooBar fooBar = new FooBar(10);
        Thread foo = new Thread(() -> {
            try {
                fooBar.foo(() -> System.out.print("foo "));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        foo.start();
        Thread bar = new Thread(() -> {
            try {
                fooBar.bar(() -> System.out.print("bar "));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        bar.start();
        Thread.sleep(5000L);
    }

    private static class FooBar {
        private int n;
        private final Object lock = new Object();
        private boolean isFoo = true;

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            synchronized (lock) {
                for (int i = 0; i < n; i++) {
                    while (!isFoo) {
                        lock.wait();
                    }
                    // printFoo.run() outputs "foo". Do not change or remove this line.
                    printFoo.run();
                    isFoo = false;
                    lock.notifyAll();
                }
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            synchronized (lock) {
                for (int i = 0; i < n; i++) {
                    while (isFoo) {
                        lock.wait();
                    }
                    // printBar.run() outputs "bar". Do not change or remove this line.
                    printBar.run();
                    isFoo = true;
                    lock.notifyAll();
                }
            }
        }
    }

    private static class FooBar1 {
        private int n;
        private final Semaphore semaphore1 = new Semaphore(1);
        private final Semaphore semaphore2 = new Semaphore(0);

        public FooBar1(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            // printFoo.run() outputs "foo". Do not change or remove this line.
            for (int i = 0; i < n; i++) {
                semaphore1.acquire();
                printFoo.run();
                semaphore2.release();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            // printBar.run() outputs "bar". Do not change or remove this line.
            for (int i = 0; i < n; i++) {
                semaphore2.acquire();
                printBar.run();
                semaphore1.release();
            }
        }
    }

    private static class Foo {

        private final Object lock = new Object();
        private int state = 0;

        public Foo() {

        }

        public void first(Runnable printFirst) throws InterruptedException {
            synchronized (lock) {
                while (state != 0) {
                    lock.wait();
                }
                // printFirst.run() outputs "first". Do not change or remove this line.
                printFirst.run();
                state = 1;
                lock.notifyAll();
            }
        }

        public void second(Runnable printSecond) throws InterruptedException {
            synchronized (lock) {
                while (state != 1) {
                    lock.wait();
                }
                // printSecond.run() outputs "second". Do not change or remove this line.
                printSecond.run();
                state = 2;
                lock.notifyAll();
            }
        }

        public void third(Runnable printThird) throws InterruptedException {
            synchronized (lock) {
                while (state != 2) {
                    lock.wait();
                }
                // printThird.run() outputs "third". Do not change or remove this line.
                printThird.run();
            }
        }
    }

    private static class Foo1 {

        private final CountDownLatch first = new CountDownLatch(1);
        private final CountDownLatch second = new CountDownLatch(1);

        public Foo1() {

        }

        public void first(Runnable printFirst) throws InterruptedException {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            first.countDown();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            // printSecond.run() outputs "second". Do not change or remove this line.
            first.await();
            printSecond.run();
            second.countDown();
        }

        public void third(Runnable printThird) throws InterruptedException {
            second.await();
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }
}
