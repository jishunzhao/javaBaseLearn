package cn.zhaojishun.javaBase.基础.注解;
//@Target
//最常用的元注解是@Target。使用@Target可以定义Annotation能够被应用于源码的哪些位置：
//
//        类或接口：ElementType.TYPE；
//        字段：ElementType.FIELD；
//        方法：ElementType.METHOD；
//        构造方法：ElementType.CONSTRUCTOR；
//        方法参数：ElementType.PARAMETER。
/*
@Retention
另一个重要的元注解@Retention定义了Annotation的生命周期：

        仅编译期：RetentionPolicy.SOURCE；
        仅class文件：RetentionPolicy.CLASS；
        运行期：RetentionPolicy.RUNTIME。*/

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull {

}
