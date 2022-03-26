package app.web.pavelk.speech4.speech;

import lombok.Getter;

import java.io.IOException;

public class Linux implements Speech {

    private Long pid;
    @Getter
    private Os os;

    public Linux(Os linux) {
        os = linux;
    }

    @Override
    public void say(String text) {
        String[] command = {"/bin/bash", "-c", "xclip -o | festival --tts --language russian --pipe"};
        String[] command4 = {"/bin/bash", "-c", "echo '(\"%s\")' | padsp festival --tts --language russian --pipe".formatted(text)};
        String[] command2 = {"/bin/bash", "-c", "echo '(\"%s\")' | padsp festival --tts --language russian --pipe".formatted(text)};
        try {
            Process process = Runtime.getRuntime().exec(command2);
//            System.out.println(new String(process.getInputStream().readAllBytes()));
//            System.out.println(new String(process.getErrorStream().readAllBytes()));
            pid = process.pid();
//            System.out.println(pid);
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
