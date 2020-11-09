package com.lind.mybatis.audit;

import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
import com.lind.mybatis.util.ClassHelper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AuditorAware;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Properties;

/**
 * 建立和更新信息填充拦截器，大叔自己实现的.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Intercepts({@Signature(
        type = org.apache.ibatis.executor.Executor.class,
        method = "update",
        args = {MappedStatement.class, Object.class})})
public class AuditInterceptor extends AbstractSqlParserHandler implements Interceptor {

    @Autowired(required = false)
    AuditorAware auditorAware;
    @Autowired(required = false)
    DepartmentAuditorAware departmentAuditorAware;
    private Properties properties;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];

        // 获取 SQL 命令
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

        // 获取参数
        Object parameter = invocation.getArgs()[1];

        // 获取私有成员变量
        Field[] declaredFields = ClassHelper.getAllFields(parameter.getClass());

        // 是否为mybatis plug
        boolean isPlugUpdate = parameter.getClass().getDeclaredFields().length == 1
                && parameter.getClass().getDeclaredFields()[0].getName().equals("serialVersionUID");

        //兼容mybatis plus的update
        if (isPlugUpdate) {
            Map<String, Object> updateParam = (Map<String, Object>) parameter;
            Class<?> updateParamType = updateParam.get("param1").getClass();
            declaredFields = updateParamType.getDeclaredFields();
            if (updateParamType.getSuperclass() != null) {
                Field[] superField = updateParamType.getSuperclass().getDeclaredFields();
                declaredFields = ArrayUtils.addAll(declaredFields, superField);
            }
        }
        for (Field field : declaredFields) {

            // 添加
            if (SqlCommandType.INSERT.equals(sqlCommandType)) {
                if (field.getAnnotation(CreatedDate.class) != null) {
                    field.setAccessible(true);
                    field.set(parameter, new Timestamp(System.currentTimeMillis()));
                }

                if (field.getAnnotation(CreatedBy.class) != null) {
                    if (auditorAware != null) {
                        field.setAccessible(true);
                        field.set(parameter, auditorAware.getCurrentAuditor().orElse(null));
                    }
                }

                if (field.getAnnotation(CreatedDepartmentBy.class) != null) {
                    if (auditorAware != null) {
                        field.setAccessible(true);
                        field.set(parameter, auditorAware.getCurrentAuditor().orElse(null));
                    }
                }
            }


            // 更新
            if (SqlCommandType.INSERT.equals(sqlCommandType)
                    || SqlCommandType.UPDATE.equals(sqlCommandType)) {
                field.setAccessible(true);
                if (field.getAnnotation(LastModifiedDate.class) != null) {
                    //兼容mybatis plus的update
                    if (isPlugUpdate) {
                        Map<String, Object> updateParam = (Map<String, Object>) parameter;
                        field.set(updateParam.get("param1"), new Timestamp(System.currentTimeMillis()));
                    } else {
                        field.set(parameter, new Timestamp(System.currentTimeMillis()));
                    }
                }
                if (field.getAnnotation(LastModifiedBy.class) != null) {
                    field.setAccessible(true);
                    if (auditorAware != null) {
                        if (isPlugUpdate) {
                            Map<String, Object> updateParam = (Map<String, Object>) parameter;
                            field.set(updateParam.get("param1"), auditorAware.getCurrentAuditor().orElse(null));
                        } else {
                            field.set(parameter, auditorAware.getCurrentAuditor().orElse(null));
                        }
                    }
                }
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof org.apache.ibatis.executor.Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties prop) {
        this.properties = prop;
    }
}
