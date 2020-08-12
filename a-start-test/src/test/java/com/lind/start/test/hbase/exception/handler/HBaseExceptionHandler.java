package com.lind.start.test.hbase.exception.handler;

import com.lind.common.exception.CommonResult;
import com.lind.start.test.hbase.exception.HbaseSystemException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.hadoop.hbase.TableExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author 赵阳
 * @Description TODO
 * @date 2020/7/21 19:50
 **/
@Slf4j
public class HBaseExceptionHandler {


    /**
     * hbase重复建表
     *
     * @param e
     * @return
     */
    @ExceptionHandler(TableExistsException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult<String> handleTableExistsException(TableExistsException e) {
        log.error("创建hbase表格错误：", e);
        return CommonResult.clientFailure(ExceptionUtils.getMessage(e));
    }

    /**
     * hbase操作错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HbaseSystemException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult<String> handleHbaseSystemException(HbaseSystemException e) {
        log.error("操作hbase数据库错误：", e);
        return CommonResult.clientFailure(ExceptionUtils.getMessage(e));
    }

}
