package app.web.pavelk.speech4.speech;

public class Factory {

    public static Speech create() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.equals(Os.WIN32.getOsName())) {
            return new Win32(Os.WIN32);
        } else if (os.equals(Os.LINUX.getOsName())) {
            return new Linux(Os.LINUX);
        } else {
            throw new IllegalArgumentException("system not supported");
        }
    }

}
