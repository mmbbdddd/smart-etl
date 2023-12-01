package cn.hz.setl.commons.cmd;

import org.apache.commons.exec.*;

import java.io.ByteArrayOutputStream;

public class ShellCommandTest {

    @org.junit.Test
    public void runCommand() throws Exception {
        String                command     = "ping 127.0.0.1";
        ByteArrayOutputStream susStream   = new ByteArrayOutputStream();
        ByteArrayOutputStream errStream   = new ByteArrayOutputStream();
        CommandLine           commandLine = CommandLine.parse(command);
        DefaultExecutor       exec        = new DefaultExecutor();
//设置一分钟超时
        ExecuteWatchdog watchdog = new ExecuteWatchdog(6 * 1000);
        exec.setWatchdog(watchdog);
        PumpStreamHandler streamHandler = new PumpStreamHandler(susStream, errStream);
        exec.setStreamHandler(streamHandler);
        try {
            int code = exec.execute(commandLine);
            System.out.println("result code: " + code);
            // 不同操作系统注意编码，否则结果乱码
            String suc = susStream.toString("GBK");
            String err = errStream.toString("GBK");
            System.out.println(suc + err);
        } catch (ExecuteException e) {
            if (watchdog.killedProcess()) {
                // 被watchdog故意杀死
                System.err.println("超时了");
            }
        }
    }
}