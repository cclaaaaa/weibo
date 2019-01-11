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
 * 长度验证
 * @author ccl
 *
 */
public @interface LengthAnno {
	//最小长度
	int minLength() default 1;
	//最大长度
	int maxLength();
	//错误信息
	String errMsg() default DEFAULT_ERROR_FLAG.LENGTH_ANNO;
}
