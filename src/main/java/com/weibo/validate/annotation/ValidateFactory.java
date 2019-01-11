package com.weibo.validate.annotation;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.weibo.validate.common.AbstractValidate;
import com.weibo.validate.common.LengthValidate;
import com.weibo.validate.common.NumberValidate;
import com.weibo.validate.common.RegexValidate;
import com.weibo.validate.common.RequireValidate;

public class ValidateFactory {
	public interface DEFAULT_ERROR_FLAG {
		String LENGTH_ANNO = "LENGTH_ANNO";

		String NUMBER_ANNO = "NUMBER_ANNO";

		String REGEX_ANNO = "REGEX_ANNO";

		String REQUIRE_ANNO = "REQUIRE_ANNO";
	}
	  private static Map<String, String> errorMap = new HashMap<String, String>();
	    static {
	        errorMap.put(DEFAULT_ERROR_FLAG.LENGTH_ANNO, "长度验证失败");
	        errorMap.put(DEFAULT_ERROR_FLAG.NUMBER_ANNO, "数字验证失败");
	        errorMap.put(DEFAULT_ERROR_FLAG.REGEX_ANNO, "正则表达式验证失败");
	        errorMap.put(DEFAULT_ERROR_FLAG.REQUIRE_ANNO, "非空验证失败");
	    }

	    public static String getDefaultError(String type) {
	        return errorMap.get(type);
	    }

	public static AbstractValidate getValidate(Field field, Class<?> type, String value) {
		if (type.equals(RequireAnno.class)) {
			RequireAnno requireAnno = field.getAnnotation(RequireAnno.class);
			RequireValidate requireValidate = new RequireValidate();
			requireValidate.setErrorMsg(requireAnno.errMsg());
			requireValidate.setValue(value);
			return requireValidate;
		} else if (type.equals(NumberAnno.class)) {
			NumberAnno numberAnno = field.getAnnotation(NumberAnno.class);
			NumberValidate numberValidate = new NumberValidate();
			numberValidate.setErrorMsg(numberAnno.errMsg());
			numberValidate.setValue(value);
			return numberValidate;
		}else if(type.equals(RegexAnno.class)){
			RegexAnno regexAnno = field.getAnnotation(RegexAnno.class);
			RegexValidate regexValidate = new RegexValidate();
			regexValidate.setErrorMsg(regexAnno.errMsg());
			regexValidate.setValue(value);
			regexValidate.setPattern(regexAnno.pattern());
			return regexValidate;
		}else if(type.equals(LengthAnno.class)){
			LengthAnno lengthAnno = field.getAnnotation(LengthAnno.class);
			LengthValidate lengthValidate = new LengthValidate();
			lengthValidate.setErrorMsg(lengthAnno.errMsg());
			lengthValidate.setMinLength(lengthAnno.minLength());
			lengthValidate.setMaxLength(lengthAnno.maxLength());
			lengthValidate.setValue(value);
			return lengthValidate;
		}
		return null;
	}
}
