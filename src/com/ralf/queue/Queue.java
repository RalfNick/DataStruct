package com.ralf.queue;

interface Queue<E> {

    boolean enQueue(E e);

    E deQueue();

    E peek();

    int size();

    boolean isEmpty();
}
