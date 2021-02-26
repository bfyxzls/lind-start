package com.lind.office.convert;

import com.lind.office.convert.utils.com.lind.office.convert.HtmlToWord;
import lombok.SneakyThrows;
import org.junit.Test;

public class HtmlToWordTest {


    /**
     * 写成word文件
     */
    @SneakyThrows
    @Test
    public void writeWordFile() {
        HtmlToWord.writeWordFile("<Html><body>hello world</body></html>","d:\\outDoc");
    }

}
