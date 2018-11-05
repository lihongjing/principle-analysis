package com.online.springioc.annotation.assemble;

import java.lang.annotation.*;

/**
 * Created by lihongjing on 2018/8/6.
 */

/**
 * TYPE-用于描述类、接口(包括注解类型) 或enum声明
 * CONSTRUCTOR-用于描述构造器
 * METHOD-用于描述方法
 * PACKAGE-用于描述包
 * PARAMETER-用于描述参数
 * LOCAL_VARIABLE-用于描述局部变量
 * FIELD-用于描述域  属性
 */
@Target({ElementType.FIELD,ElementType.CONSTRUCTOR,ElementType.METHOD,ElementType.PARAMETER,ElementType.LOCAL_VARIABLE})

/**
 * 标记注解  表名这个注解应该被javadoc工具记录（默认javadoc是不记录注解的）
 */
@Documented

/**
 * 标记注解 使用此注解标记的自定义注解在类上使用时，
 * 子类会自动继承注解，否则不会继承（注：只有在类上使用才会继承）
 */
//@Inherited

/**
 * 定义注解标记的Class信息存在周期
 * source// 仅存在源码期，与编译器交互，用于代码检测
 * class// 保留在clss文件中，但运行时不会保留在jvm，
 *         主要用于编译时生成额外文件，如xml,java文件等，
 *         这个级别需要添加java代理（javaagent）动态生成字节码
 * runtime// 运行时保留相关信息，主要用于java反射相关操作
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {
    String value()default  ""; //value属性对其赋值可不用属性的名称，其它都需要指定属性名

    // spring Autowired  默认为true 必须匹配至少一个bean,,当指定为false可匹配0个bean
    boolean required() default true;

}
