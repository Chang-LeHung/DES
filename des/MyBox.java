package des;

import argparse.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;

/**
 * @author HuChang
 * @version 1.0
 * @date 2020/11/6 15:02
 */
public class MyBox {

    public static void main(String[] args) {
        System.out.println("############################### MyBox ##################################");
        System.out.println("### Description:                                                    ###");
        System.out.println("### Software MyBox was Written by Mr. HuChang in Java on 10/27/2020 ###");
        System.out.println(" _____________");
        System.out.println("< @牧牛的铃铛 >");
        System.out.println(" -------------");
        System.out.println("        \\   ^__^");
        System.out.println("         \\  (oo)\\_______");
        System.out.println("            (__)\\       )\\/\\");
        System.out.println("                ||---- w|");
        System.out.println("                ||     ||");
        ArgParse arg = new ArgParse();
        arg.addParams("-input", new ParametersParse("-input",  null,
                "input file path", "string"));
        arg.addParams("-output", new ParametersParse("-output",  "mybox",
                "output file path", "string"));
        arg.addParams("-key1", new ParametersParse("-key1",  "0123456789abcdef",
                "first secret key", "string"));
        arg.addParams("-key2", new ParametersParse("-key2",  "0123456789abcdef",
                "second secret key", "string"));
        arg.addParams("-key3", new ParametersParse("-key3",  "0123456789abcdef",
                "third secret key", "string"));
        arg.addParams("-iv", new ParametersParse("-iv",  "0123456789abcdef",
                "initialization vector of CBC encryption mode", "string"));
        arg.addParams("-mode", new ParametersParse("-mode",  "en",
                "encrypt or decrypt", "string"));
        arg.parseString(args);
        try {
            File f = new File(arg.getValue("-input"));
            byte[] bytes = new byte[(int) f.length()];
            FileInputStream is = new FileInputStream(f);
            is.read(bytes);
            is.close();
            if (arg.getValue("-key1").length() != 16 || arg.getValue("-key2").length() != 16 ||
            arg.getValue("-key3").length() != 16 || arg.getValue("-iv").length() != 16) {
                System.out.println("length of key1, key2, key3 or iv is not correct");
                System.exit(-1);
            }
            CBCTDES cbctdes = new CBCTDES(
                    Utils.toBits(arg.getValue("-key1")),
                    Utils.toBits(arg.getValue("-key2")),
                    Utils.toBits(arg.getValue("-key3")),
                    Utils.toBits(arg.getValue("-iv"))
            );
            if (arg.getValue("-mode").startsWith("en")) {
                long[] messages = Utils.byteToLongFill(bytes);
                Long s = System.currentTimeMillis();
                long[] cipher = cbctdes.encrypt(messages);

                Long e = System.currentTimeMillis();
                byte[] ans = Utils.longToBytes(cipher);
                FileOutputStream fo = new FileOutputStream(arg.getValue("-output") + ".mybox");
                fo.write(ans);
                fo.close();
                System.out.println("finished ~~~");
                System.out.println("Cost time : " + (double) (e - s) / 1000 + "s");
            }else if (arg.getValue("-mode").startsWith("de")) {
                long[] messages = Utils.byteToLong(bytes);
                Long s = System.currentTimeMillis();
                long[] plain = cbctdes.decrypt(messages);
                Long e = System.currentTimeMillis();
                byte[] ans = Utils.longToBytesDrop(plain);
                FileOutputStream fo = new FileOutputStream(arg.getValue("-output"));
                fo.write(ans);
                System.out.println("finished ~~~");
                System.out.println("Cost time : " + (double) (e - s) / 1000 + "s");
            }else {
                System.out.println("-mode is wrong");
                System.exit(-1);
            }
        }catch (Exception e){
            System.out.println("Parameters' error occurred");
        }
    }
}
