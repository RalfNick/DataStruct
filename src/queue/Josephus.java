package queue;

import java.util.ArrayList;
import java.util.List;

/**
 * DESCRIPTION
 * 约瑟夫问题，用Queue来实现 Josephus 问题
 *
 * @author lixin
 * @create 2019-02-10 上午10:11
 **/
public class Josephus {

    /**
     * 在这个古老的问题当中， N 个深陷绝境的人一致同意用这种方式减少生存人数：
     * N个人围成一圈（位置记为0到N-1）， 并且从第一个人报数， 报到 M 的人会被杀死， 直到最后一个人留下来
     *
     * @param n n个人
     * @param m 报数的最大值
     * @return
     */
    public static List<Integer> execute(int n, int m) {
        List<Integer> resultList = new ArrayList<>();
        CircleQueue<Integer> circleQueue = new CircleQueue<>(n);
        if (n < 1 || m < 1) {
            return resultList;
        }
        for (int i = 0; i < n; i++) {
            circleQueue.enQueque(i);
        }
        while (!circleQueue.isEmpty()) {
            for (int i = 0; i < m - 1; i++) {
                circleQueue.enQueque(circleQueue.deQueue());
            }
            resultList.add(circleQueue.deQueue());
        }
        return resultList;
    }
}
