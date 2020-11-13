package argparse;

import des.DES;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author HuChang
 * @version 1.0
 * @date 2020/11/7 11:18
 */
public class ArgParse {

    private HashMap<String, ParametersParse> params = new HashMap<>();

    public ArgParse() {
    }

    public void printLog() {
        System.out.println("Usage mybox:\n mybox [FILED] ... [STRING] ... ");
        for (ParametersParse param : params.values()) {
            System.out.println(param.tipsMessage());
        }
    }

    public void addParams(String name, ParametersParse param) {
        params.put(name, param);
    }

    public String getValue(String name) {
        return params.get(name).getVal();
    }

    public void parseString(String[] args) {
        if ((args.length == 1 && args[0].equals("-help")) || args.length == 0) {
            printLog();
            System.exit(0);
        }
        int k = 0;
        try {
            if ((args.length & 1) == 1) {
                System.out.println("Parameters' error occurred");
                System.exit(-1);
            }
            for (int i = 0; i < args.length; i += 2) {
                k = i;
                ParametersParse obj =  params.get(args[i]);
                if (!args[i + 1].startsWith("-"))
                    obj.set(args[i + 1]);
                else
                    throw new RuntimeException();
            }
        } catch (Exception e) {
            System.out.println("'" + args[k + 1] + "' is a wrong parameter for starting with '-'");
        }
    }

    public static void main(String[] args) {
        DES.printBits(-1);
    }
}
