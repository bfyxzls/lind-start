package com.lind.rbac.sys.exception;

import cn.hutool.core.map.MapUtil;
import com.lind.common.exception.AbstractRestExceptionHandler;
import com.lind.common.rest.CommonResult;
import com.lind.common.util.ExceptionUtils;
import com.lind.common.util.HttpContextUtils;
import com.lind.common.util.IpInfoUtil;
import com.lind.common.util.JsonUtils;
import com.lind.rbac.log.entity.SysLogErrorEntity;
import com.lind.rbac.log.service.SysLogErrorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler extends AbstractRestExceptionHandler {

    @Autowired
    private SysLogErrorService sysLogErrorService;

    @ExceptionHandler(Exception.class)
    public CommonResult handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        saveLog(ex);

        return CommonResult.failure();
    }

    /**
     * 保存异常日志
     */
    private void saveLog(Exception ex) {
        SysLogErrorEntity log = new SysLogErrorEntity();

        //请求相关信息
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        log.setIp(IpInfoUtil.getIpAddr(request));
        log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
        log.setRequestUri(request.getRequestURI());
        log.setRequestMethod(request.getMethod());
        Map<String, String> params = HttpContextUtils.getParameterMap(request);
        if (MapUtil.isNotEmpty(params)) {
            log.setRequestParams(JsonUtils.toJsonString(params));
        }

        //异常信息
        log.setErrorInfo(ExceptionUtils.getErrorStackTrace(ex));

        //保存
        sysLogErrorService.save(log);
    }
}
