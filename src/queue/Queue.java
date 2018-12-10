package queue;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2018-12-10 下午8:07
 **/
public interface Queue<E> {

    boolean push(E e);

    E poll();

    E peek();

    int size();
}
