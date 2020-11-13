package argparse;


import java.util.ArrayList;

/**
 * @author HuChang
 * @version 1.0
 * @date 2020/11/6 22:58
 */
public class ParametersParse {

    private String name;
    private String defaultVal;
    private String message;
    private String type;
    private ArrayList<String> values;

    public ParametersParse(String name, String defaultVal, String message, String type) {
        this.name = name;
        this.defaultVal = defaultVal;
        this.message = message;
        this.type = type;
    }
    public void set(String val) {
        if (this.type.equals("string"))
            this.defaultVal = val;
        else
            add(val);
    }
    public void add(String val) {
        if (this.values == null) {
            this.values = new ArrayList<String> ();
        }
        this.values.add(val);
    }
    public String get(int idx) {
        return this.values.get(idx);
    }
    public String tipsMessage() {
        return  "   " + this.name + "\t--" + this.message + " \n                    default=" + this.defaultVal;
    }
    public String getVal() {
        return this.defaultVal;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        ParametersParse param = new ParametersParse("123","123",  "dasdasd", "asd");
        System.out.println(param.tipsMessage());
        Class<?> c = Class.forName("java.lang.Integer");
        Integer a = 10;
        System.out.println(a.getClass().equals(c));
    }
}
