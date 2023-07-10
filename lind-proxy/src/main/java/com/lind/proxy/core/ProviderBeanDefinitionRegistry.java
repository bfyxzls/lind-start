package com.lind.proxy.core;

import lombok.SneakyThrows;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class ProviderBeanDefinitionRegistry implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {

	private MetadataReaderFactory metadataReaderFactory;

	private ResourcePatternResolver resourcePatternResolver;

	/**
	 * 需要实现你的注解类型.
	 * @return
	 */
	protected abstract Class getAnnotation();

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
		this.metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);
	}

	private Set<Class<?>> scannerPackages(String basePackage) {
		Set<Class<?>> set = new LinkedHashSet<>();
		String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
				+ StringUtils.replace(basePackage, ".", "/") + "/**/*.class";
		try {
			Resource[] resources = this.resourcePatternResolver.getResources(packageSearchPath);
			for (Resource resource : resources) {
				if (resource.isReadable()) {
					MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);
					String className = metadataReader.getClassMetadata().getClassName();
					Class<?> clazz;
					try {
						clazz = Class.forName(className);
						if (clazz.isAnnotationPresent(getAnnotation())) {
							set.add(clazz);
						}
					}
					catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return set;
	}

	@SneakyThrows
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry,
			BeanNameGenerator importBeanNameGenerator) {
		String[] basePackages = new String[] {
				Class.forName(importingClassMetadata.getClassName()).getPackage().getName() };

		// 使用自定义扫描器扫描
		Set<Class<?>> classes = new HashSet<>();
		for (String basePackage : basePackages) {
			classes.addAll(scannerPackages(basePackage));
		}
		for (Class beanClazz : classes) {
			BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(beanClazz);
			GenericBeanDefinition definition = (GenericBeanDefinition) builder.getRawBeanDefinition();

			MutablePropertyValues propertyValues = definition.getPropertyValues();
			propertyValues.add("interfaceType", beanClazz);
			definition.setBeanClass(ProviderProxyFactoryBean.class);
			definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
			registry.registerBeanDefinition(beanClazz.getSimpleName(), definition);

		}
	}

}
