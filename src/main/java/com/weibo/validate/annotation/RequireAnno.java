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
@Retention(RetentionPolicy.RUNTIME)
@Inherited
/**
 * 非空验证
 * @author ccl
 *
 */
public @interface RequireAnno {
	/**
	 * 错误信息
	 * @return
	 */
	String errMsg() default DEFAULT_ERROR_FLAG.REQUIRE_ANNO;
}
