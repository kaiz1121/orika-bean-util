package com.kaiz.util;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author kaiz
 * @date 11:37 2019-11-12.
 */
public class BeanMapperUtil {

    private BeanMapperUtil(){}

    private static final MapperFactory MAPPER_FACTORY = new DefaultMapperFactory.Builder().build();

    private static final MapperFacade MAPPER_FACADE = MAPPER_FACTORY.getMapperFacade();

    /**
     * <p>Title: </p>
     * <p>Description: </p>
     * @Author:kaiz
     * @param destinationClass : 目标类
     * @param sourceClass : 源类
     * @param configMap : 自定义字段映射 K：源  V:目标
     * @param excludeFiled : 自定义排除映射字段
     * @return: ma.glasnost.orika.MapperFacade
     * @Date: 2019-11-12 15:54
     */
    private static <S, D> MapperFacade getMapperFacade(Class<D> destinationClass, Class<S> sourceClass, Map<String, String> configMap, List<String> excludeFiled) {
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        ClassMapBuilder classMapBuilder = factory.classMap(sourceClass, destinationClass);
        if (!(configMap == null || configMap.isEmpty())){
            configMap.forEach(classMapBuilder::field);
        }
        if (!(excludeFiled == null || excludeFiled.isEmpty())){
            excludeFiled.forEach(classMapBuilder::exclude);
        }
        classMapBuilder.byDefault().register();
        return factory.getMapperFacade();
    }


    /**
     * <p>Title: </p>
     * <p>Description: </p>
     * @Author:kaiz
     * @param data : 源数据
     * @param destinationClass : 目标类
     * @param configMap : 自定义字段映射 K：源  V:目标,可直接传入null
     * @param excludeFiled : 自定义排除映射字段,可直接传入null
     * @return: D
     * @Date: 2019-11-12 15:00
     */
    public static <S, D> D map(S data, Class<D> destinationClass, Map<String, String> configMap, List<String> excludeFiled) {
        MapperFacade mapperFacade = getMapperFacade(destinationClass, data.getClass(), configMap, excludeFiled);
        return mapperFacade.map(data, destinationClass);
    }

    /**
     * <p>Title: </p>
     * <p>Description: 支持自定义字段映射和排除映射字段</p>
     * @Author:kaiz
     * @param data : 源数据
     * @param sourceClass : 源类
     * @param destinationClass : 目标类
     * @param configMap : 自定义字段映射 K：源  V:目标,可直接传入null
     * @param excludeFiled : 自定义排除映射字段,可直接传入null
     * @return: java.util.List<D>
     * @Date: 2019-11-12 15:00
     */
    public static <S, D> List<D> mapList(Collection<S> data, Class<S> sourceClass, Class<D> destinationClass, Map<String, String> configMap, List<String> excludeFiled) {
        MapperFacade mapperFacade = getMapperFacade(destinationClass, sourceClass, configMap, excludeFiled);
        return mapperFacade.mapAsList(data, TypeFactory.valueOf(sourceClass), TypeFactory.valueOf(destinationClass));
    }


    /**
     * 简单的复制出新类型对象.
     * <p>
     * 通过source.getClass() 获得源Class
     */
    public static <S, D> D map(S source, Class<D> destinationClass) {
        return MAPPER_FACADE.map(source, destinationClass);
    }

    /**
     * 极致性能的复制出新类型对象.
     * <p>
     * 预先通过BeanMapper.getType() 静态获取并缓存Type类型，在此处传入
     */
    public static <S, D> D map(S source, Type<S> sourceType, Type<D> destinationType) {
        return MAPPER_FACADE.map(source, sourceType, destinationType);
    }

    /**
     * 简单的复制出新对象列表到ArrayList
     * <p>
     * 不建议使用mapper.mapAsList(Iterable<S>,Class<D>)接口, sourceClass需要反射，实在有点慢
     */
    public static <S, D> List<D> mapList(Iterable<S> sourceList, Class<S> sourceClass, Class<D> destinationClass) {
        return MAPPER_FACADE.mapAsList(sourceList, TypeFactory.valueOf(sourceClass), TypeFactory.valueOf(destinationClass));
    }

    /**
     * 极致性能的复制出新类型对象到ArrayList.
     * <p>
     * 预先通过BeanMapper.getType() 静态获取并缓存Type类型，在此处传入
     */
    public static <S, D> List<D> mapList(Iterable<S> sourceList, Type<S> sourceType, Type<D> destinationType) {
        return MAPPER_FACADE.mapAsList(sourceList, sourceType, destinationType);
    }

    /**
     * 简单复制出新对象列表到数组
     * <p>
     * 通过source.getComponentType() 获得源Class
     */
    public static <S, D> D[] mapArray(final D[] destination, final S[] source, final Class<D> destinationClass) {
        return MAPPER_FACADE.mapAsArray(destination, source, destinationClass);
    }

    /**
     * 极致性能的复制出新类型对象到数组
     * <p>
     * 预先通过BeanMapper.getType() 静态获取并缓存Type类型，在此处传入
     */
    public static <S, D> D[] mapArray(D[] destination, S[] source, Type<S> sourceType, Type<D> destinationType) {
        return MAPPER_FACADE.mapAsArray(destination, source, sourceType, destinationType);
    }

    /**
     * 预先获取orika转换所需要的Type，避免每次转换.
     */
    public static <E> Type<E> getType(final Class<E> rawType) {
        return TypeFactory.valueOf(rawType);
    }

}
