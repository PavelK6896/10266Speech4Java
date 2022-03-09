package app.web.pavelk.speech4;

public class Factory {

    public static Speech create() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.equals("windows 10")) {
            return new Win32();
        } else if (os.equals("linux")) {
            return new Linux();
        } else {
            throw new IllegalArgumentException("system not supported");
        }
    }

}
