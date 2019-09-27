package moe.maple.scripts.util;

/**
 * @author umbreon22
 * Created on 9/17/2019.
 */
public class IntegerToWord {
    private static final String[] words = {
        "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
        "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };
    private static final String[] tens = {
        "", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    public static String convert(int number) {
        if(number == 0) return "zero";
        var sb = new StringBuilder();
        if(number < 0) {
            number = Math.abs(number);
            sb.append("negative ");
        }
        return build(sb, number).toString().trim();
    }

    private static StringBuilder build(StringBuilder sb, int remainingNumber) {
        if(remainingNumber < words.length) {
            return sb.append(words[remainingNumber]);
        } else if(remainingNumber < 100) {//is in tens!
            sb.append(tens[remainingNumber/10]).append(" ");
            return build(sb, remainingNumber%10);
        } else if(remainingNumber < 1000) {
            return build(sb, remainingNumber, "hundred", 100);
        } else if(remainingNumber < 1000000) {
            return build(sb, remainingNumber, "thousand", 1000);
        } else if(remainingNumber < 1000000000) {
            return build(sb, remainingNumber, "million", 1000000);
        } else {//integers only go to billions
            return build(sb, remainingNumber, "billion", 1000000000);
        }
    }

    private static StringBuilder build(StringBuilder sb, int remainingNumber, String placement, int capacity) {
        build(sb, remainingNumber / capacity);
        sb.append(" ").append(placement).append(" ");
        int remain = remainingNumber % capacity;
        if(remain < 100) {
            sb.append("and ");
        }
        return build(sb, remain);
    }

}
