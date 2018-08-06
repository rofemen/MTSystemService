/**
 * @author:郑日枋
 * @time:2017年3月19日 下午2:08:05
 * @filename:DateConverter.java
 */
package com.rofe.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date> {

	public Date convert(String source){
		System.out.println(source);
		// 实现 将日期串转成日期类型(格式是yyyy-MM-dd HH:mm:ss)
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm");
			// 转成直接返回
			try {
				return simpleDateFormat.parse(source);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		return null;
	}

}
