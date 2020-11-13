package des;

import java.util.HashMap;
/**
 * @author HuChang
 * @version 1.0
 * @date 2020/11/6 15:00
 */
public class Utils {

    public static HashMap<Character, Integer> map = new HashMap<Character, Integer>() {{
        put('0', 0);
        put('1', 1);
        put('2', 2);
        put('3', 3);
        put('4', 4);
        put('5', 5);
        put('6', 6);
        put('7', 7);
        put('8', 8);
        put('9', 9);
        put('a', 10);
        put('b', 11);
        put('c', 12);
        put('d', 13);
        put('e', 14);
        put('f', 15);
    }};

    public static long toBits (String message) {
        long ans = 0;
        try {
            for (int i = 0; i < message.length(); i++) {
                if (!map.containsKey(message.charAt(i)))
                    throw new RuntimeException("String '" + message + "' contains illegal character '" + message.charAt(i) + "'");
                long data =  map.get(message.charAt(i));
                ans |= data;
                if (i != message.length() - 1)
                    ans <<= 4;
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ans;
    }

    public static long[] byteToLong(byte[] data) {
        int len = data.length;
        long[] messages = new long[len >> 3];
        for (int i = 0; i < (len >> 3); i++) {
            for (int j = 0; j < 8; j++) {
                messages[i] |= (data[i * 8 + j] & 0xff); // attention
                if (j < 7)
                    messages[i] <<= 8;
            }
        }
        return messages;
    }
    public static long[] byteToLongFill(byte[] data) {
        int len;
        if (data.length % 8 == 0) {
            len = data.length + 8;
        } else {
            len = (data.length / 8 + 2) * 8;
        }
        long[] messages = new long[len >> 3];
        messages[messages.length - 1] = 8 - data.length % 8;
        for (int i = 0; i < (len >> 3) - 1; i++) {
            for (int j = 0; j < 8; j++) {
                if (i * 8 + j < data.length)
                    messages[i] |= (data[i * 8 + j] & 0xff); // attention
                if (j < 7)
                    messages[i] <<= 8;
            }
        }
        return messages;
    }

    public static byte[] longToBytes(long [] messages) {
        byte[] ans = new byte[messages.length * 8];
        for (int i = 0; i < messages.length; i++) {
            for (int j = 0; j < 8; j++) {
                ans[i * 8 + j] = (byte) ((messages[i] >>> (8 * (7 - j))) & 0xff) ;
            }
        }
        return ans;
    }
    public static byte[] longToBytesDrop(long [] messages) {
        int minus = (int) messages[messages.length - 1] + 8;
        byte[] ans = new byte[messages.length * 8 - minus];
        for (int i = 0; i < messages.length - 1; i++) {
            for (int j = 0; j < 8; j++) {
                if (i * 8 + j == ans.length)
                    break;
                ans[i * 8 + j] = (byte) ((messages[i] >>> (8 * (7 - j))) & 0xff) ;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int a = 255;
        System.out.println((byte) (a));
    }
}
