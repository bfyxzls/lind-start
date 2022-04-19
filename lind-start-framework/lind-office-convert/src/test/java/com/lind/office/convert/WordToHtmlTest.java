package com.lind.office.convert;

import com.lind.office.convert.word.WordToHtml;
import org.junit.Test;

public class WordToHtmlTest {
    @Test
    public void docToHtml() throws Exception {
        WordToHtml.docxToHtml("D:\\SoFlu飞算全自动软件程平台调研.docx", "d:\\outHtml");
    }
}
