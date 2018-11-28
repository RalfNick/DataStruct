package List;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2018-11-25 下午4:22
 **/
public interface RList<T> {

    int size();

    boolean isEmpty();

    boolean contains(T o);

    T[] toArray();

    boolean add(T o);

    boolean remove(T o);

    void clear();

    T get(int index);

    T set(int index, T element);

    void add(int index, T element);

    T removeByIndex(int index);

    int indexOf(T o);

    RIterator<T> iterator();
}
