package app.web.pavelk.speech4;

import java.io.IOException;

public class Linux implements Speech {

    private Long pid;

    @Override
    public void say(String text) {
        String[] command = {"/bin/bash", "-c", "echo '(SayText \"%s\")' | padsp festival --pipe".formatted(text)};
        try {
            Process process = Runtime.getRuntime().exec(command);
//            System.out.println(new String(process.getInputStream().readAllBytes()));
//            System.out.println(new String(process.getErrorStream().readAllBytes()));
            pid = process.pid();
            System.out.println(pid);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void stop() {
        if (pid == null)
            return;
//        String[] command = {"/bin/bash", "-c", "kill -9 %s".formatted(pid)};
        String[] command = {"/bin/bash", "-c", "killall festival;killall aplay;sleep .1;killall aplay"};
        try {
            Process process = Runtime.getRuntime().exec(command);
            System.out.println(new String(process.getInputStream().readAllBytes()));
            System.out.println(new String(process.getErrorStream().readAllBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
