package com.lind.office.convert;

import com.lind.office.convert.utils.com.lind.office.convert.WordToHtml;
import org.junit.Test;

public class WordToHtmlTest {
    @Test
    public void docToHtml() throws Exception {
        WordToHtml.docxToHtml("D:\\接口管理平台-DOClever使用文档v1.0.docx", "d:\\outHtml");
    }
}
