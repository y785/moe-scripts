package moe.maple.scripts.util;

/**
 * @author umbreon22
 * Created on 9/17/2019.
 */
public class IntegerToWord {

    private static final String[] words = {"",
        "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
        "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };

    private static final String[] tens = {"", words[10],
        "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    private static final int
            ONE_HUNDRED  = 100,
            ONE_THOUSAND = 1000,
            ONE_MILLION  = 1000000,
            ONE_BILLION  = 1000000000;

    private static final String
            HUNDRED = "hundred",
            THOUSAND = "thousand",
            MILLION = "million",
            BILLION = "billion";

    public static String convert(int number) {
        if(number == 0) return "zero";
        var sb = new StringBuilder();
        if(number < 0) {
            number = Math.abs(number);
            sb.append("negative ");
        }
        return build(sb, number).toString();
    }

    private static StringBuilder build(StringBuilder sb, int remainingNumber) {
        if(remainingNumber < words.length) {
            return sb.append(words[remainingNumber]);
        } else if(remainingNumber < ONE_HUNDRED) {//is in tens!
            sb.append(tens[remainingNumber/10]);
            int remain = remainingNumber%10;
            if(remain > 0) sb.append(" ");
            return build(sb, remain);
        } else if(remainingNumber < ONE_THOUSAND) {//is in.. capacity string
            return build(sb, remainingNumber, HUNDRED, ONE_HUNDRED);
        } else if(remainingNumber < ONE_MILLION) {
            return build(sb, remainingNumber, THOUSAND, ONE_THOUSAND);
        } else if(remainingNumber < ONE_BILLION) {
            return build(sb, remainingNumber, MILLION, ONE_MILLION);
        } else {
            return build(sb, remainingNumber, BILLION, ONE_BILLION);
        }
    }


    private static StringBuilder build(StringBuilder sb, int remainingNumber, String placement, int capacity) {
        build(sb, remainingNumber / capacity);
        sb.append(" ").append(placement);
        int remain = remainingNumber % capacity;
        if(remain > 0) {
            sb.append(" ");
            if (remain < 100) {
                sb.append("and ");
            }
        }
        return build(sb, remain);
    }

}
