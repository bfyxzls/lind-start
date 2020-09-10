package com.lind.hbase.exception.handler;

import com.lind.hbase.exception.HbaseSystemException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.hadoop.hbase.TableExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
public class HBaseExceptionHandler {


    /**
     * hbase重复建表
     *
     * @param e TableExistsException
     * @return
     */
    @ExceptionHandler(TableExistsException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> handleTableExistsException(TableExistsException e) {
        log.error("创建hbase表格错误：", e);
        return ResponseEntity.ok(ExceptionUtils.getMessage(e));
    }

    /**
     * hbase操作错误
     *
     * @param e TableExistsException
     * @return
     */
    @ExceptionHandler(HbaseSystemException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> handleHbaseSystemException(HbaseSystemException e) {
        log.error("操作hbase数据库错误：", e);
        return ResponseEntity.ok(ExceptionUtils.getMessage(e));
    }

}
