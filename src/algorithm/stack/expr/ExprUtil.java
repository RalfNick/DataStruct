package algorithm.stack.expr;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-08-03 下午12:44
 **/
public class ExprUtil {

    // https://blog.csdn.net/Antineutrino/article/details/6763722
    /**
     * 后缀表达式求值
     *
     * @param postExpr 后缀表达式
     * @return
     */
    public static int calPostExpress(String postExpr) {
        Stack<Integer> stack = new Stack<>();
        int num;
        String[] strs = postExpr.split(" ");
        for (int i = 0; i < strs.length; i++) {
            if (isNumber(strs[i])) {
                num = Integer.parseInt(strs[i]);
                stack.push(num);
            } else if (isOperator(strs[i])) {
                if (stack.size() > 1) {
                    int num1 = stack.pop();
                    int num2 = stack.pop();
                    stack.push(calculateInt(num2, num1, strs[i].toCharArray()[0]));
                } else {
                    throw new IllegalArgumentException("error express");
                }

            } else if (strs[i].equals(" ")) {
            } else {
                throw new IllegalArgumentException("error express");
            }
        }
        return stack.isEmpty() ? 0 : stack.pop();
    }

    /**
     * 前缀表达式求值
     *
     * @param preExpr 前缀表达式
     * @return
     */
    public static int calPreExpress(String preExpr) {
        Stack<Integer> stack = new Stack<>();
        int num;
        String[] strs = preExpr.split(" ");

        for (int i = strs.length - 1; i >= 0; i--) {
            if (isNumber(strs[i])) {
                num = Integer.parseInt(strs[i]);
                stack.push(num);
            } else if (isOperator(strs[i])) {
                if (stack.size() > 1) {
                    stack.push(calculateInt(stack.pop(), stack.pop(), strs[i].toCharArray()[0]));
                } else {
                    throw new IllegalArgumentException("error express");
                }

            } else if (strs[i].equals(" ")) {
            } else {
                throw new IllegalArgumentException("error express");
            }
        }
        return stack.isEmpty() ? 0 : stack.pop();
    }

    private static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 中缀表达式转前缀表达式
     *
     * @param input 输入的字符串
     */
    public static String toPolishNotation(String input) {
        int len = input.length();
        char ch, tempCh;
        double num;
        Stack<Character> opStack = new Stack<>();
        Stack<Object> exprStack = new Stack<>();
        int lastIndex;
        for (int i = len - 1; i >= 0; i--) {
            ch = input.charAt(i);
            if (Character.isDigit(ch)) {
                lastIndex = readReverseDigit(input, i);
                num = Double.parseDouble(input.substring(lastIndex, i + 1));
                if ((int) num == num) {
                    exprStack.push((int) num);
                } else {
                    exprStack.push(num);
                }
                i = lastIndex;
            } else if (isOperator(ch)) {
                while (!opStack.isEmpty() && opStack.peek() != ')'
                        && priorityCompare(ch, opStack.peek()) < 0) {
                    exprStack.push(opStack.pop());
                }
                opStack.push(ch);
            } else if (ch == '(') {
                while (!opStack.isEmpty() && (tempCh = opStack.pop()) != ')') {
                    exprStack.push(tempCh);
                }
            } else if (ch == ')') {
                opStack.push(ch);
            } else if (ch == ' ') {
            } else {
                throw new IllegalArgumentException("wrong character '" + ch + "'");
            }
        }
        while (!opStack.isEmpty()) {
            exprStack.push(opStack.pop());
        }
        StringBuilder builder = new StringBuilder();
        while (!exprStack.isEmpty()) {
            builder.append(exprStack.pop()).append(" ");
        }
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    /**
     * 中缀表达式转后缀表达式
     *
     * @param input
     * @return
     */
    public static String toReversePolishNotation(String input) {
        int len = input.length();
        char ch, tempCh;
        double num;
        Stack<Character> opStack = new Stack<>();
        Stack<Object> exprStack = new Stack<>();
        int lastIndex;
        for (int i = 0; i < len; i++) {
            ch = input.charAt(i);
            if (Character.isDigit(ch)) {
                lastIndex = readDigit(input, i);
                num = Double.parseDouble(input.substring(i, lastIndex));
                if ((int) num == num) {
                    exprStack.push((int) num);
                } else {
                    exprStack.push(num);
                }
                i = lastIndex - 1;
            } else if (isOperator(ch)) {
                while (!opStack.isEmpty() && opStack.peek() != '('
                        && priorityCompare(ch, opStack.peek()) <= 0) {
                    exprStack.push(opStack.pop());
                }
                opStack.push(ch);
            } else if (ch == ')') {
                while (!opStack.isEmpty() && (tempCh = opStack.pop()) != '(') {
                    exprStack.push(tempCh);
                }
            } else if (ch == '(') {
                opStack.push(ch);
            } else if (ch == ' ') {
            } else {
                throw new IllegalArgumentException("wrong character '" + ch + "'");
            }
        }
        while (!opStack.isEmpty()) {
            exprStack.push(opStack.pop());
        }
        StringBuilder builder = new StringBuilder();
        for (Object obj : exprStack) {
            builder.append(obj).append(" ");
        }
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    /**
     * 根据操作符计算值
     *
     * @param num1 数值1
     * @param num2 数值2
     * @param op   操作符
     * @return
     * @throws IllegalArgumentException
     */
    private static double calculateDouble(double num1, double num2, char op) throws IllegalArgumentException {
        switch (op) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 == 0) {
                    throw new IllegalArgumentException("divisor can not be 0");
                }
                return num1 / num2;
            default:
                return 0;
        }

    }

    /**
     * 根据操作符计算值
     *
     * @param num1 数值1
     * @param num2 数值2
     * @param op   操作符
     * @return
     * @throws IllegalArgumentException
     */
    private static int calculateInt(int num1, int num2, char op) throws IllegalArgumentException {
        return (int) calculateDouble(num1, num2, op);
    }

    /**
     * 比较操作符的优先级
     *
     * @param op1 操作符 1
     * @param op2 操作符 2
     * @return
     */
    private static int priorityCompare(char op1, char op2) {
        switch (op1) {
            case '+':
            case '-':
                return op2 == '*' || op2 == '/' ? -1 : 0;
            case '*':
            case '/':
                return op2 == '+' || op2 == '-' ? 1 : 0;
        }
        return 1;
    }

    /**
     * 读取反向数字
     *
     * @param input 输入字符串
     * @param start 起始位置
     * @return
     * @throws IllegalArgumentException
     */
    private static int readReverseDigit(String input, int start) throws IllegalArgumentException {
        int dotIndex = -1;
        char c;
        for (int i = start; i >= 0; i--) {
            c = input.charAt(i);
            if (c == '.') {
                if (dotIndex != -1) {
                    throw new IllegalArgumentException("there have more than 1 dots in the input");
                } else {
                    dotIndex = i;
                }
            } else if (!Character.isDigit(c)) {
                return i + 1;
            } else if (i == 0) {
                return 0;
            }
        }
        throw new IllegalArgumentException("not a number.");
    }

    /**
     * 读取输入字符串的数字(包括double类型)
     *
     * @param input 输入字符串
     * @param start 其实字符
     * @return
     */
    private static int readDigit(String input, int start) throws IllegalArgumentException {
        int len = input.length();
        int dotIndex = -1;
        char c;
        for (int i = start; i < len; i++) {
            c = input.charAt(i);
            if (c == '.') {
                if (dotIndex != -1) {
                    throw new IllegalArgumentException("there have more than 1 dots in the input");
                } else if (i == len - 1) {
                    throw new IllegalArgumentException("not a number,dot can't be the last part of a number");
                } else {
                    dotIndex = i;
                }
            } else if (!Character.isDigit(c)) {
                if (dotIndex == -1 || i - dotIndex > 1) {
                    return i;
                } else {
                    throw new IllegalArgumentException("not a number,dot can't be the last part of a number");
                }
            } else if (i == len - 1) {
                return len;
            }
        }
        throw new IllegalArgumentException("not a number");
    }

    /**
     * 是否是运算符
     *
     * @param c 字符
     * @return
     */
    private static boolean isOperator(char c) {
        return (c == '+') || (c == '-') || (c == '*') || (c == '/');
    }

    /**
     * 是否是运算符
     *
     * @param str 字符
     * @return
     */
    private static boolean isOperator(String str) {
        return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/");
    }
}
