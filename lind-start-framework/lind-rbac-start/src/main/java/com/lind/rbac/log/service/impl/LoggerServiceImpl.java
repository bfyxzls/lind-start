package com.lind.rbac.log.service.impl;

import com.lind.common.util.CopyUtils;
import com.lind.logger.entity.LoggerInfo;
import com.lind.logger.service.LoggerService;
import com.lind.rbac.log.entity.SysLogOperationEntity;
import com.lind.rbac.log.service.SysLogOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lind
 * @date 2022/7/1 13:50
 * @description
 */
@Service
public class LoggerServiceImpl implements LoggerService {
    @Autowired
    SysLogOperationService sysLogOperationService;

    @Override
    public void insert(LoggerInfo loggerInfo) {
        SysLogOperationEntity sysLogOperationEntity = new SysLogOperationEntity();
        CopyUtils.copyProperties(loggerInfo, sysLogOperationEntity);
        sysLogOperationService.insert(sysLogOperationEntity);
    }
}
