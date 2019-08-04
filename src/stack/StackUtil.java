package stack;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2018-12-06 下午8:27
 **/
public class StackUtil {

    /**
     * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     */
    public static <T> void reverse(RStack<T> stack) {
        if (checkStackNull(stack)) return;
        RStack<T> stack1 = new RStack<>(stack.size());
        RStack<T> stack2 = new RStack<>(stack.size());
        while (!stack.isEmpty()) {
            stack1.push(stack.pop());
        }
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        while (!stack2.isEmpty()) {
            stack.push(stack2.pop());
        }
        // 对列最方便，但是违规了。。。
//        Queue<T> queue = new LinkedList<>();
//        while (!stack.isEmpty()){
//            queue.offer(stack.pop());
//        }
//        while (!queue.isEmpty()){
//            stack.push(queue.poll());
//        }
    }

    public static <T> void reverse1(RStack<T> stack) {
        if (checkStackNull(stack)) return;
        RStack<T> stack1 = new RStack<>(stack.size());
        while (!stack.isEmpty()) {
            stack1.push(stack.pop());
        }
        stack = stack1;
    }

    public static <T> void reverse2(RStack<T> stack) {
        if (checkStackNull(stack)) return;
        RStack<T> stack1 = new RStack<>(stack.size());
        while (!stack.isEmpty()) {
            stack1.push(stack.pop());
        }
        while (!stack1.isEmpty()) {
            T top = stack1.pop();
            addToBottom(stack, top);
        }
    }

    private static <T> void addToBottom(RStack<T> stack, T t) {
        if (stack.isEmpty()) {
            stack.push(t);
        } else {
            T top = stack.pop();
            addToBottom(stack, t);
            stack.push(top);
        }
    }


    private static <T> boolean checkStackNull(RStack<T> stack) {
        if (stack == null || stack.size() < 1) {
            return true;
        }
        return false;
    }

    /**
     * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     *
     * @param o 被删除的对象
     */
    public static <T> void remove(RStack<T> stack, T o) {
        if (checkStackNull(stack)) return;
        RStack<T> tempStack = new RStack<>(stack.size());
        while (!stack.isEmpty() && !stack.peek().equals(o)) {
            tempStack.push(stack.pop());
        }

        if (!stack.isEmpty()) {
            stack.pop();
        }

        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
    }

    /**
     * 从栈顶取得len个元素, 原来的栈中元素保持不变
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     *
     * @param len 长度
     */
    public static <T> T[] getTop(RStack<T> stack, int len) {

        if (stack == null || stack.size() < 1) {
            return null;
        } else if (len > stack.size()) {
            throw new IndexOutOfBoundsException();
        }

        RStack<T> tempStack = new RStack<>(stack.size());
        @SuppressWarnings("unchecked")
        T[] results = (T[]) new Object[len];
        for (int i = 0; i < len; i++) {
            results[i] = stack.peek();
            tempStack.push(stack.pop());
        }
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
        return results;
    }

    /**
     * 字符串s 可能包含这些字符：  ( ) [ ] { }, a,b,c... x,yz
     * 使用堆栈检查字符串s中的括号是不是成对出现的。
     * 例如s = "([e{d}f])" , 则该字符串中的括号是成对出现， 该方法返回true
     * 如果 s = "([b{x]y})", 则该字符串中的括号不是成对出现的， 该方法返回false;
     *
     * @param s 输入字符串
     */
    public static boolean isValidPairs(String s) {
        if (s == null || s.equals("")) {
            return true;
        }
        char[] chars = s.toCharArray();
        RStack<Character> stack = new RStack<>(chars.length / 2);
        RStack<Character> rightStack = new RStack<>(chars.length / 2);
        for (char chr : chars) {
            if (chr == '{') {
                stack.push('}');
            } else if (chr == '[') {
                stack.push(']');
            } else if (chr == '(') {
                stack.push(')');
            }
            if (chr == '}') {
                if ('}' != stack.pop()) {
                    return false;
                }
            } else if (chr == ']') {
                if (']' != stack.pop()) {
                    return false;
                }
            } else if (chr == ')') {
                if (')' != stack.pop()) {
                    return false;
                }
            }
        }


        return true;

    }
}
