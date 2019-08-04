package algorithm.stack.expr;

import org.junit.Assert;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-08-04 下午4:48
 **/
public class ExprUtilTest {

    @Test
    public void testCalPostExpress() {
        String str = "3 4 + 5 * 6 -";
        int result = ExprUtil.calPostExpress(str);
        Assert.assertEquals(29, result);
    }

    @Test
    public void testCalPreExpress() {
        String str = "- * + 3 4 5 6";
        int result = ExprUtil.calPreExpress(str);
        Assert.assertEquals(29, result);
    }

    @Test
    public void testToPolishNotation() {
        String inExpr = "1+((2+3)*4)-5";
        String result = ExprUtil.toPolishNotation(inExpr);
        Assert.assertEquals("- + 1 * + 2 3 4 5", result);
    }

    @Test
    public void testToReversePolishNotation() {
        String inExpr = "1+((2+3)*4)-5";
        String result = ExprUtil.toReversePolishNotation(inExpr);
        Assert.assertEquals("1 2 3 + 4 * + 5 -", result);
    }
}
