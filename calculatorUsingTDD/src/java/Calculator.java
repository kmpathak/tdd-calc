
public class Calculator {

    static String message = "";

    static int Add(String numbers) throws Exception {
        if (numbers.length() == 0) {
            return 0;
        } else {
            String[] inputs = getOperands(numbers);
            int sum = calculateSum(inputs);
            throwIfNegativeNumbers();
            return sum;
        }
    }

    private static void throwIfNegativeNumbers() throws Exception {
        if (message.length() > 0) {
            message = message.replaceAll(",$", "");
            String exception = message;
            message = "";
            throw new Exception("negatives not allowed " + exception);
        }
    }

    private static int calculateSum(String[] nums) {
        int sum = 0;
        int i = 0;
        while (i < nums.length) {
            int num = Integer.parseInt(nums[i++]);
            if (num < 0)
                message += num + ",";
            sum += num;
        }
        return sum;

    }

    public static String[] getOperands(String numbers) {
        String delimiter = "";
        if (numbers.startsWith("//")) {
            delimiter = numbers.substring(2, 3);
            numbers = numbers.substring(4);
        } else
            delimiter = ",|\n";
        String[] nums = numbers.split(delimiter);
        return nums;
    }
}
