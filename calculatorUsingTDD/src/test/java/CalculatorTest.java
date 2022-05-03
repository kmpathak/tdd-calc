import org.junit.Assert;
import org.junit.Test;


public class CalculatorTest {

    int Add(String numbers) {
        if(numbers.length()==0)
            return 0;
        else{
            return Integer.parseInt(numbers);
        }
    }

    @Test
    public void additionTest() {
        Assert.assertEquals(0, Add(""));
        Assert.assertEquals(1, Add("1"));
        Assert.assertEquals(762, Add("762"));
    }

}
