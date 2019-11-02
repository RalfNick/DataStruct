package queue;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2018-12-10 下午8:07
 **/
public interface Queue<E> {

    boolean enQueue(E e);

    E deQueue();

    E peek();

    int size();
}
