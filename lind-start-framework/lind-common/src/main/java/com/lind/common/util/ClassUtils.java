package com.lind.common.util;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class ClassUtils {

	/**
	 * 根据包路径获取包及子包下的具有某个注解的类型，它将被动态代理
	 * @param basePackage basePackage
	 * @return Set<Class < ?>> Set<Class<?>>
	 */
	public static Set<Class<?>> scannerPackages(String basePackage, Class anno,
			MetadataReaderFactory metadataReaderFactory, ResourcePatternResolver resourcePatternResolver) {
		Set<Class<?>> set = new LinkedHashSet<>();
		String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
				+ StringUtils.replace(basePackage, ".", "/") + "/**/*.class";
		try {
			Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
			for (Resource resource : resources) {
				if (resource.isReadable()) {
					MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
					String className = metadataReader.getClassMetadata().getClassName();
					Class<?> clazz;
					try {
						clazz = Class.forName(className);
						if (anno.isAnnotation() && clazz.isAnnotationPresent(anno)) {
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

	/**
	 * 是否为自定义的类.
	 * @param classze
	 * @return
	 */
	public static boolean isCustomClass(Class classze) {
		return classze.getClassLoader() != null;
	}

}
