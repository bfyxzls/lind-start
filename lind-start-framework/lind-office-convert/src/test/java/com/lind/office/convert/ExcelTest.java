package com.lind.office.convert;

import com.lind.office.convert.excel.ExcelBean;
import com.lind.office.convert.excel.ExcelUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.ArrayList;
import java.util.List;

public class ExcelTest {
    @SneakyThrows
    @Test
    public void listExportExcel() {
        List<People> mapList = new ArrayList<>();

        mapList.add(new People("zhangsan", 100));
        mapList.add(new People("lisi", 200));
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        List<ExcelBean> excelBeans = new ArrayList<>();
        excelBeans.add(new ExcelBean("名称", "name"));
        excelBeans.add(new ExcelBean("数量", "totalData"));
        ExcelUtils.export(request, response, "test.xls", excelBeans, People.class, mapList);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public class People {
        private String name;
        private Integer totalData;
    }
}
