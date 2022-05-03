import org.junit.Assert;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;


public class CalculatorTest {

    int Add(String numbers) {
        if (numbers.length() == 0)
            return 0;
        else {
            String delimiter = "";
            if (numbers.startsWith("//")) {
                delimiter = numbers.substring(2,3);
                numbers = numbers.substring(4);
            } else
                delimiter = ",|\n";
            String[] nums = numbers.split(delimiter);
            int sum = 0;
            int i = 0;
            while (i < nums.length) {
                sum += Integer.parseInt(nums[i]);
                i++;
            }
            return sum;
        }
    }

    @Test
    public void additionTestBasic() {
        Assert.assertEquals(0, Add(""));
        Assert.assertEquals(1, Add("1"));
        Assert.assertEquals(762, Add("762"));
        Assert.assertEquals(5, Add("2,3"));
        Assert.assertEquals(1030, Add("1026,4"));
        Assert.assertEquals(14, Add("2,4,8"));
        Assert.assertEquals(18, Add("3,6,7,2"));
    }

    @Test
    public void additionTestWithNewLine() {
        Assert.assertEquals(6, Add("2\n4"));
        Assert.assertEquals(14, Add("2\n5,7"));
        Assert.assertEquals(15, Add("3,5\n7"));
        Assert.assertEquals(21, Add("3,5\n7\n6"));
    }

    @Test
    public void additionTestWithDifferentDelimiters() {
        Assert.assertEquals(6, Add("//;\n2;4"));
        Assert.assertEquals(6, Add("//:\n2:4"));
        Assert.assertEquals(11, Add("//:\n2:4:5"));
        Assert.assertEquals(11, Add("//\n\n2\n4\n5"));
        Assert.assertEquals(11, Add("//,\n2,4,5"));
    }
    @Test
    public void additionTestWithNegativeNumbers() {
        ThrowingRunnable runnable = new ThrowingRunnable() {
            public void run() throws Throwable {
                Add("-3");
            }
        };
        Exception exception1 = Assert.assertThrows(Exception.class,runnable);
        Assert.assertEquals("negatives not allowed -3",exception1.getMessage());
    }
    @Test
    public void executeAllTestCases() {
        additionTestBasic();
        additionTestWithNewLine();
        additionTestWithDifferentDelimiters();
        additionTestWithNegativeNumbers();
    }
}
