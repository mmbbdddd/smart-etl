package cn.hz.setl.commons.utils;

import cn.hz.setl.commons.dto.ApiResult;
import org.apache.commons.exec.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

public class CommandUtils {
    public static ApiResult exec(String command, String charset, Integer timeout) throws TimeoutException, IOException {
        ByteArrayOutputStream susStream   = new ByteArrayOutputStream();
        ByteArrayOutputStream errStream   = new ByteArrayOutputStream();
        CommandLine           commandLine = CommandLine.parse(command);
        DefaultExecutor       exec        = new DefaultExecutor();
//设置一分钟超时
        ExecuteWatchdog watchdog = new ExecuteWatchdog(timeout);
        exec.setWatchdog(watchdog);
        PumpStreamHandler streamHandler = new PumpStreamHandler(susStream, errStream);
        exec.setStreamHandler(streamHandler);

        try {
            int code = exec.execute(commandLine);
            if (code == 0) {
                String suc = susStream.toString(charset);
                return ApiResult.of(suc);
            } else {
                String err = errStream.toString(charset);
                return ApiResult.error(err);
            }
        } catch (UnsupportedEncodingException e) {
            return ApiResult.error(e.getMessage());
        } catch (ExecuteException e) {
            if (watchdog.killedProcess()) {
                // 被watchdog故意杀死
                throw new TimeoutException(e.getMessage());
            } else {
                throw e;
            }
        }
    }
}
