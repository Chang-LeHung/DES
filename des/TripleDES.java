package des;

/**
 * @author HuChang
 * @version 1.0
 * @date 2020/11/6 14:30
 */
public class TripleDES {

    DES des1;
    DES des2;
    DES des3;

    TripleDES(long secret1, long secret2, long secret3) {
        des1 = new DES(secret1);
        des2 = new DES(secret2);
        des3 = new DES(secret3);
    }

    public long decrypt(long cipher) {
        long plain = des3.decrypt(cipher);
        plain = des2.encrypt(plain);
        plain = des1.decrypt(plain);
        return plain;
    }

    public long encrypt(long plain) {
        long cipher = des1.encrypt(plain);
        cipher = des2.decrypt(cipher);
        cipher = des3.encrypt(cipher);
        return cipher;
    }

    public static void main(String[] args) {
        long a = 1;
        a <<= 63;
        System.out.println(a);
        DES.printBits(a);
    }
}
