package com.example.sirene.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class CommonUtils {

	static final String FIRST_DATE_FORMAT = "dd-MM-yyyy";

	static final String SECOND_DATE_FORMAT = "dd/MM/yyyy";

	static final String THIRD_DATE_FORMAT = "yyyyMMdd";

	static final String FOURTH_DATE_FORMAT = "yyyy-MM-dd";

	static final String FIFTH_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.S";

	public String dateToThirdFormat(String date) {
		if (!date.contains("-"))
			return date;
		Date dateToFormat = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(FIFTH_DATE_FORMAT);
			dateToFormat = sdf.parse(date);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		SimpleDateFormat sdf = new SimpleDateFormat(THIRD_DATE_FORMAT);
		return sdf.format(dateToFormat);

	}

	public String dateToSeconFormat(String date) {
		if (!date.contains("-"))
			return date;
		Date dateToFormat = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(FIFTH_DATE_FORMAT);
			dateToFormat = sdf.parse(date);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		SimpleDateFormat sdf = new SimpleDateFormat(SECOND_DATE_FORMAT);
		return sdf.format(dateToFormat);
	}

	public Date dateFromThirdFormat(String dateFormat) {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(THIRD_DATE_FORMAT);
			date = sdf.parse(dateFormat);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return date;
	}

	public Date dateFromFifthFormat(String dateFormat) throws ParseException  {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(FIFTH_DATE_FORMAT);
		date = sdf.parse(dateFormat);
		return date;
	}

	public String dateToFormat(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public String getFileName(String extention, String name) {
		final StringBuilder fileName = new StringBuilder(name).append("_")
				.append(dateToFormat(new Date(), "yyyyMMddHHmm")).append(extention);
		return fileName.toString();
	}

}
