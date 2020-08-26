package com.lind.common;

import com.lind.common.util.IdMakerUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class IdMakerTest {
    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds   精确到秒的字符串
     * @param formatStr
     * @return
     */
    public static String timeStamp2Date(Integer seconds, String format) {
        if (seconds == null) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    @Test
    public void mongodbId() throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            log.info("id={}", IdMakerUtils.generateId(i));
        }

    }

    @Test
    public void hexDecConvert() {
        String hex = "ff";
        Integer dec = Integer.parseInt(hex, 16);
        log.info("dec={}", dec);

        String val = "5f46260501000001fc";

        Integer timer = Integer.parseInt(val.substring(0, 8), 16);
        String date = timeStamp2Date(timer, "yyyy-MM-dd HH:mm:ss");
        log.info("time:{}", date);

        Integer service = Integer.parseInt(val.substring(8, 10), 16);
        log.info("serviceId:{}", service);

        Integer inc = Integer.parseInt(val.substring(10), 16);
        log.info("inc:{}", inc);

    }
}
