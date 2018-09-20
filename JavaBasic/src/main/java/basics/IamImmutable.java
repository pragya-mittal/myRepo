package basics;

import java.util.HashMap;
import java.util.Map.Entry;

public final class IamImmutable {
    private int i;
    private String s;
    private HashMap<String, String> h;

    public IamImmutable(int i, String s, HashMap<String, String> h) {
        this.i = i;
        this.s = s;

//        this.h=h;
        this.h = new HashMap<String, String>();
        for (Entry<String, String> entry: h.entrySet()) {
            this.h.put((entry.getKey()), entry.getValue());
        }
    }

    public int getI() {
        return i;
    }

    public String getS() {
        return s;
    }

    public HashMap<String, String> getH() {
        return h;
    }

    public static void main(String[] args) {
        int i = 6;
        String s = "!am@John";
        HashMap<String, String> h = new HashMap<String, String>();

        h.put("Info1", "!am@John");
        h.put("Inf02", "!amCrazy6");


        IamImmutable imm = new IamImmutable(i, s, h);

        h.put("Inf02", "!amCraxy7");

        System.out.println(imm.getS() + imm.getI());
        System.out.println(imm.getH());
//        for (Entry<String, String> entry: h.entrySet())
//            System.out.println(entry.getKey() + " --- " + entry.getValue());
    }
}

