package edu.rit.csci622.data.dao.impl;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class DateTypeHandler extends BaseTypeHandler<Date> {

	@Override
	public Date getNullableResult(ResultSet arg0, String arg1) throws SQLException {
		String date = arg0.getString(arg1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Date getNullableResult(ResultSet arg0, int arg1) throws SQLException {
		System.out.println("2");
		return null;
	}

	@Override
	public Date getNullableResult(CallableStatement arg0, int arg1) throws SQLException {
		System.out.println("1");
		return null;
	}

	@Override
	public void setNonNullParameter(PreparedStatement arg0, int arg1, Date arg2, JdbcType arg3) throws SQLException {
	}

}
