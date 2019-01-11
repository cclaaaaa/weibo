package com.weibo.validate.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.weibo.validate.annotation.ValidateFactory.DEFAULT_ERROR_FLAG;

@Target(ElementType.FIELD)
@Documented
@Retention(value = RetentionPolicy.RUNTIME)//可被反射
@Inherited

/**
 * 数字验证
 * @author ccl
 *
 */
public @interface NumberAnno {
	/**
	 * 错误信息
	 * @return
	 */
	String errMsg() default DEFAULT_ERROR_FLAG.NUMBER_ANNO;
}
