package app.web.pavelk.speech4.speech;

import lombok.Getter;

import java.io.IOException;


public class Win32 implements Speech {

    private Long pid;
    @Getter
    private Os os;

    public Win32(Os win32) {
        os = win32;
    }

    @Override
    public void say(String text) {
        String command = """
                powershell.exe Write-Output 'start shell';
                Add-Type -AssemblyName System.speech;
                $speak = New-Object System.Speech.Synthesis.SpeechSynthesizer;
                $speak.Rate = 10;
                $speak.Speak('%s');
                Write-Output 'finish shell';
                """.formatted(text);
        try {
            Process process = Runtime.getRuntime().exec(command);
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
        String command = """
                taskkill /pid %s /T /F
                """.formatted(pid);
        try {
            Process process = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
