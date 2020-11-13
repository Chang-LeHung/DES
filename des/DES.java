package des;

/**
 * @author HuChang
 * @version 1.0
 * @date 2020/11/5 23:29
 */

public class DES {
    private final long secretKey;
    private final long[] subSecretKeys = new long[16];
    public static byte[][] initIPSub = {
            {58, 50, 42, 34, 26, 18, 10, 2},
            {60, 52, 44, 36, 28, 20, 12, 4},
            {62, 54, 46, 38, 30, 22, 14, 6},
            {64, 56, 48, 40, 32, 24, 16, 8},
            {57, 49, 41, 33, 25, 17, 9 , 1},
            {59, 51, 43, 35, 27, 19, 11, 3},
            {61, 53, 45, 37, 29, 21, 13, 5},
            {63, 55, 47, 39, 31, 23, 15, 7}
    };
    public static byte[][] initIPInverseSub = {
            {40, 8, 48, 16, 56, 24, 64, 32},
            {39, 7, 47, 15, 55, 23, 63, 31},
            {38, 6, 46, 14, 54, 22, 62, 30},
            {37, 5, 45, 13, 53, 21, 61, 29},
            {36, 4, 44, 12, 52, 20, 60, 28},
            {35, 3, 43, 11, 51, 19, 59, 27},
            {34, 2, 42, 10, 50, 18, 58, 26},
            {33, 1, 41,  9, 49, 17, 57, 25}
    };
    public static byte[][] initialKeySub1 = {
            {57, 49, 41, 33, 25, 17,  9},
            { 1, 58, 50, 42, 34, 26, 18},
            {10,  2, 59, 51, 43, 35, 27},
            {19, 11,  3, 60, 52, 44, 36},
            {63, 55, 47, 39, 31, 23, 15},
            { 7, 62, 54, 46, 38, 30, 22},
            {14,  6, 61, 53, 45, 37, 29},
            {21, 13,  5, 28, 20, 12,  4}
    };
    public static byte[][] initialKeySub2 = {
            {14, 17, 11, 24,  1,  5},
            { 3, 28, 15,  6, 21, 10},
            {23, 19, 12,  4, 26,  8},
            {16,  7, 27, 20, 13,  2},
            {41, 52, 31, 37, 47, 55},
            {30, 40, 51, 45, 33, 48},
            {44, 49, 39, 56, 34, 53},
            {46, 42, 50, 36, 29, 32}
    };
    public static byte[][] extendsTable = {
            {32,  1,  2,  3,  4,  5},
            { 4,  5,  6,  7,  8,  9},
            { 8,  9, 10, 11, 12, 13},
            {12, 13, 14, 15, 16, 17},
            {16, 17, 18, 19, 20, 21},
            {20, 21, 22, 23, 24, 25},
            {24, 25, 26, 27, 28, 29},
            {28, 29, 30, 31, 32,  1}
    };
    public static byte[][] P = {
            {16,  7, 20, 21},
            {29, 12, 28, 17},
            { 1, 15, 23, 26},
            { 5, 18, 31, 10},
            { 2,  8, 24, 14},
            {32, 27,  3,  9},
            {19, 13, 30,  6},
            {22, 11,  4, 25}
    };
    public static byte[][] S1 = {
            {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
            {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
            {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
            {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
    };
    public static byte[][] S2 = {
            {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
            {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
            {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
            {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}
    };
    public static byte[][] S3 = {
            {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
            {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
            {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
            {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
    };
    public static byte[][] S4 = {
            {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
            {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
            {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
            {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}
    };
    public static byte[][] S5 = {
            {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
            {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
            {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
            {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
    };
    public static byte[][] S6 = {
            {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
            {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
            {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
            {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}
    };
    public static byte[][] S7 = {
            {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
            {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
            {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
            {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
    };
    public static byte[][] S8 = {
            {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
            {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
            {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
            {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}
    };
    public DES(long secretKey) {
        this.secretKey = secretKey;
        this.generateSecretKey();
    }

    public long encrypt(long data) {
        data = initialSubstitution(data, false);
        long left = (data >>> 32);
        long right = (data << 32);
        right >>>= 32;
        long tempLeft;
        for (int i = 0; i < 16; i++) {
            tempLeft = left;
            left = right;
            right = roundFunction(right, i);
            right ^= tempLeft;
        }
        long ans = 0;
        ans |= (right << 32);
        ans |= left;
        ans = initialSubstitution(ans, true);
        return ans;
    }
    public long decrypt(long data) {
        data = initialSubstitution(data, false);
        long left = (data >>> 32);
        long right = (data << 32);
        right >>>= 32;
        long tempLeft;
        for (int i = 0; i < 16; i++) {
            tempLeft = left;
            left = right;
            right = roundFunction(right, 15 - i);
            right ^= tempLeft;
        }
        long ans = 0;
        ans |= (right << 32);
        ans |= left;
        ans = initialSubstitution(ans, true);
        return ans;
    }

    private long roundFunction(long r, int k) {
        r = extendsSubstitution(r, true);
        r ^= this.subSecretKeys[k];
        long ans = 0;
        for (int i = 0; i < 8; i++) {
            long temp = (r >>> (6 * (7 - i)));
            long row = (temp & 1) + ((temp & 32) >>> 4);
            long col = ((temp & 30) >>> 1);
            long val;
            switch (i) {
                case 0: {
                    val =  S1[(int) row][(int) col];
                    break;
                }
                case 1:{
                    val =  S2[(int) row][(int) col];
                    break;
                }
                case 2: {
                    val =  S3[(int) row][(int) col];
                    break;
                }
                case 3:{
                    val =  S4[(int) row][(int) col];
                    break;
                }
                case 4:{
                    val =  S5[(int) row][(int) col];
                    break;
                }
                case 5:{
                    val =  S6[(int) row][(int) col];
                    break;
                }
                case 6:{
                    val =  S7[(int) row][(int) col];
                    break;
                }
                default:{
                    val =  S8[(int) row][(int) col];
                    break;
                }
            }
            ans |= (val << 4 * (7 - i));
        }
        ans = extendsSubstitution(ans, false);
        return ans;
    }

    private long extendsSubstitution(long data, boolean extend) {
        long ans = 0;
        if (extend) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 6; j++) {
                    long temp = (data >>> (32 - extendsTable[i][j])) & 1;
                    ans |= (temp << (47 - (i * 6 + j)));
                }
            }
        } else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 4; j++) {
                    long temp = (data >>> (32 - P[i][j])) & 1;
                    ans |= (temp << (31 - (i * 4 + j)));
                }
            }
        }
        return ans;
    }

    public static long secretKeySubstitution(long data, boolean key1) {
        long ans = 0;
        if (key1) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 7; j++) {
                    long temp = (data >>> (64 - initialKeySub1[i][j])) & 1;
                    ans |= (temp << (55 - (i * 7 + j)));
                }
            }
        } else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 6; j++) {
                    long temp = (data >>> (56 - initialKeySub2[i][j])) & 1;
                    ans |= (temp << (47 - (i * 6 + j)));
                }
            }
        }

        return ans;
    }
    private void generateSecretKey() {
        long data = secretKeySubstitution(this.secretKey, true);
        long left = 0;
        left |= (data >>> 28);
        long right = 0;
        long r = 0xfffffff;
        right |= (data & r);
        for (int i = 1; i <= 16; i++) {
            if (i == 1 || i == 2 || i == 9 || i == 16) {
                left = leftMove(left, 1);
                right = leftMove(right, 1);
            } else {
                left = leftMove(left, 2);
                right = leftMove(right, 2);
            }
            long key = 0;
            key |= right;
            key |= (left << 28);
            this.subSecretKeys[i - 1] = secretKeySubstitution(key, false);
        }
    }

    public static long initialSubstitution(long data, boolean inverse) {
        long ans = 0;
        long temp;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (inverse)
                    temp = (data >>> (64 - initIPInverseSub[i][j])) & 1;
                else
                    temp = (data >>> (64 - initIPSub[i][j])) & 1;
                ans |= (temp << (63 - (i * 8 + j)));
            }
        }
        return ans;
    }

    public static long leftMove(long data, int step) {
        long ans = ((data << step) | (data >>> (28 - step)));
        long r = 0xfffffff;
        return ans & r;
    }

    public static void printBits(long data) {
        for (int i = 0; i < 64; i++) {
            System.out.print((data >>> (63 - i)) & 1);
            System.out.print(" ");
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        long data = -7460683158677674621L;
        DES des = new DES(1311768467302729063L);
        long en = des.encrypt(data);
        int l = (int) en;
        int r = (int) (en >>> 32);
        long de = des.decrypt(en);
        System.out.println(data);
        System.out.print(Integer.toHexString(r));
        System.out.print(Integer.toHexString(l));
        System.out.println();
        System.out.println(de);
    }
}
