package com.jian.bean.privilege;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 用来标注执行该方法要拥有什么权限
 * @author JOJO
 * @date 2012-9-20
 */
@Retention(RetentionPolicy.RUNTIME)//代表Permission注解保留在的阶段
@Target(ElementType.METHOD)//只能标注在方法上面
public @interface Permission
{
    /* 权限模块   */
    String module();
    /* 权限值 */
    String privilege();
}
