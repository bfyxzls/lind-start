package com.lind.common;

import com.lind.common.dto.DateRangeDTO;
import com.lind.common.util.HtmlUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class HtmlUtilsTest {
    @Test
    public void getFiledDescByType() {
        HtmlUtils.getFiledDescByType(DateRangeDTO.class).forEach(o -> {
            log.info(o);
        });
    }

    @Test
    public void getFiledNameByType() {
        HtmlUtils.getFiledNameByType(DateRangeDTO.class).forEach(o -> {
            log.info(o);
            log.info("desc:{}", HtmlUtils.getFieldDescByName(o, DateRangeDTO.class));
        });
    }


}
