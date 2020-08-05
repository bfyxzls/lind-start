package com.lind.elasticsearch.audit;

import com.lind.elasticsearch.util.ClassHelper;
import com.lind.elasticsearch.config.SpringContextConfig;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Aspect
public class AuditAspect {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 添加ES实体-切入点
     */
    @Pointcut("execution(* org.springframework.data.repository.CrudRepository.save(..))")
    public void save() {
    }

    /**
     * 更新ES实体-切入点
     */
    @Pointcut("execution(* org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate.update(..))")
    public void update() {
    }

    /**
     * 插入实体拦截器.
     *
     * @param joinPoint
     * @throws IllegalAccessException
     */
    @Before("save()")
    public void beforeSave(JoinPoint joinPoint) throws IllegalAccessException {
        System.out.println("插入拦截");

        if (joinPoint.getArgs().length > 0) {
            Object esBaseEntity = joinPoint.getArgs()[0];
            Field[] fields = ClassHelper.getAllFields(esBaseEntity.getClass());
            List<Field> fieldList = Arrays.stream(fields)
                    .filter(o -> o.getAnnotation(CreatedDate.class) != null
                            || o.getAnnotation(LastModifiedDate.class) != null)
                    .collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(fieldList)) {
                for (Field field : fieldList) {
                    field.setAccessible(true);//取消私有字段限制
                    if (field.get(esBaseEntity) == null) {
                        field.set(esBaseEntity, df.format(new Date()));
                    }
                }
            }
            List<Field> auditFieldList = Arrays.stream(fields)
                    .filter(o -> o.getAnnotation(CreatedBy.class) != null
                            || o.getAnnotation(LastModifiedBy.class) != null)
                    .collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(auditFieldList)) {
                for (Field field : auditFieldList) {
                    field.setAccessible(true);//取消私有字段限制
                    if (field.get(esBaseEntity) == null) {
                        EsAuditorAware esAuditorAware = SpringContextConfig.getBean(EsAuditorAware.class);
                        if (esAuditorAware != null) {
                            field.set(esBaseEntity, esAuditorAware.getCurrentAuditor().orElse(null));
                        }
                    }
                }
            }
        }

    }

    /**
     * 更新实体拦截器.
     *
     * @param joinPoint
     */
    @Before("update()")
    public void beforeUpdate(JoinPoint joinPoint) {
        System.out.println("更新拦截");
        if (joinPoint.getArgs().length == 1 && joinPoint.getArgs()[0] instanceof UpdateQuery) {
            UpdateQuery updateQuery = (UpdateQuery) joinPoint.getArgs()[0];
            Map source = updateQuery.getUpdateRequest().doc().sourceAsMap();
            Field[] fields = ClassHelper.getAllFields(updateQuery.getClazz());
            List<Field> fieldList = Arrays.stream(fields)
                    .filter(o -> o.getAnnotation(LastModifiedDate.class) != null)
                    .collect(Collectors.toList());
            for (Field field : fieldList) {
                if (!source.containsKey(field.getName())) {
                    source.put(field.getName(), df.format(new Date()));
                }
            }
            List<Field> auditFieldList = Arrays.stream(fields)
                    .filter(o -> o.getAnnotation(LastModifiedBy.class) != null)
                    .collect(Collectors.toList());
            for (Field field : auditFieldList) {
                if (!source.containsKey(field.getName())) {
                    EsAuditorAware esAuditorAware = SpringContextConfig.getBean(EsAuditorAware.class);
                    if (esAuditorAware != null) {
                        source.put(field.getName(), esAuditorAware.getCurrentAuditor().orElse(null));
                    }
                }
            }
            updateQuery.getUpdateRequest().doc().source(source);
        }
    }
}
