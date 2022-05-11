import org.junit.Assert;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

public class CalculatorTest {

    @Test
    public void additionTestBasic() throws Exception {
        Assert.assertEquals(0, Calculator.Add(""));
        Assert.assertEquals(1, Calculator.Add("1"));
        Assert.assertEquals(762, Calculator.Add("762"));
        Assert.assertEquals(5, Calculator.Add("2,3"));
        Assert.assertEquals(1030, Calculator.Add("1026,4"));
        Assert.assertEquals(14, Calculator.Add("2,4,8"));
        Assert.assertEquals(18, Calculator.Add("3,6,7,2"));
    }

    @Test
    public void additionTestWithNewLine() throws Exception {
        Assert.assertEquals(6, Calculator.Add("2\n4"));
        Assert.assertEquals(14, Calculator.Add("2\n5,7"));
        Assert.assertEquals(15, Calculator.Add("3,5\n7"));
        Assert.assertEquals(21, Calculator.Add("3,5\n7\n6"));
    }

    @Test
    public void additionTestWithDifferentDelimiters() throws Exception {
        Assert.assertEquals(6, Calculator.Add("//;\n2;4"));
        Assert.assertEquals(6, Calculator.Add("//:\n2:4"));
        Assert.assertEquals(11, Calculator.Add("//:\n2:4:5"));
        Assert.assertEquals(11, Calculator.Add("//\n\n2\n4\n5"));
        Assert.assertEquals(11, Calculator.Add("//,\n2,4,5"));
    }

    @Test
    public void additionTestWithNegativeNumbers() {
        ThrowingRunnable runnable = new ThrowingRunnable() {
            public void run() throws Throwable {
                Calculator.Add("-3");
            }
        };
        Exception exception1 = Assert.assertThrows(Exception.class, runnable);
        Assert.assertEquals("negatives not allowed -3", exception1.getMessage());
        ThrowingRunnable runnable2 = new ThrowingRunnable() {
            public void run() throws Throwable {
                Calculator.Add("-3,2,9,-8,-2");
            }
        };
        Exception exception2 = Assert.assertThrows(Exception.class, runnable2);
        Assert.assertEquals("negatives not allowed -3,-8,-2", exception2.getMessage());
        ThrowingRunnable runnable3 = new ThrowingRunnable() {
            public void run() throws Throwable {
                Calculator.Add("-3,-2,-9,-8,-2");
            }
        };
        Exception exception3 = Assert.assertThrows(Exception.class, runnable3);
        Assert.assertEquals("negatives not allowed -3,-2,-9,-8,-2", exception3.getMessage());
    }
}
