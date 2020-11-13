package des;

/**
 * @author HuChang
 * @version 1.0
 * @date 2020/11/6 14:39
 */
public class CBCTDES extends TripleDES {

    private final long initialVector;

    public CBCTDES(long key1, long key2, long key3, long initialVector) {
        super(key1, key2, key3);
        this.initialVector = initialVector;
    }

    public long[] encrypt(long[] message) {
        long[] ans = new long[message.length];
        long temp;
        int mod = message.length / 333;
        if (mod == 0)
            mod = 1;
        for (int i = 0; i < message.length; i++) {
            if (i == 0) {
                temp = message[i] ^ this.initialVector;
            } else {
                temp = message[i] ^ ans[i - 1];
            }
            ans[i] = super.encrypt(temp);
            if (i % mod == 0)
                log(message, i, true);
            if (i == message.length - 1)
                log(message, i, true);
        }
        return ans;
    }

    public long[] decrypt(long[] cipher) {
        long[] ans = new long[cipher.length];
        long temp;
        int mod = cipher.length / 333;
        if (mod == 0)
            mod = 1;
        for (int i = 0; i < cipher.length; i++) {
            temp = super.decrypt(cipher[i]);
            if (i == 0) {
                ans[i] = temp ^ this.initialVector;
            } else {
                ans[i] = temp ^ cipher[i - 1];
            }
            if (i % mod == 0)
                log(cipher, i, false);
            if (i == cipher.length - 1)
                log(cipher, i, false);
        }
        return ans;
    }

    private void log(long[] cipher, int i, boolean en) {
        int s = (i + 1) * 30 / cipher.length;
        if (en)
            System.out.print("en process: ");
        else
            System.out.print("de process: ");
        for (int k = 0; k < s; k++) {
            System.out.print("*");
        }
        for (int k = s; k < 30; k++) {
            System.out.print("-");
        }
        if (i < cipher.length - 1)
            System.out.printf(" %.2f%%\r", ((double) (i + 1) * 100 / cipher.length));
        else
            System.out.printf(" %.2f%%\n", ((double) (i + 1) * 100 / cipher.length));
    }

    public static void main(String[] args) {
        long[] data = {1, 2, 3, 4, 5};
        CBCTDES cbct = new CBCTDES(-1152345345, -2543543, -453543543, -54354);
        long[] en = cbct.encrypt(data);
        long[] de = cbct.decrypt(en);
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < en.length; i++) {
            System.out.print(en[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < de.length; i++) {
            System.out.print(de[i] + " ");
        }
    }
}
