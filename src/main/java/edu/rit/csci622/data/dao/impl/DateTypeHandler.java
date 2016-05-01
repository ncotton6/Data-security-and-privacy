package edu.rit.csci622.data.dao.impl;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class DateTypeHandler extends BaseTypeHandler<Date> {

	@Override
	public Date getNullableResult(ResultSet arg0, String arg1) throws SQLException {
		System.out.println("3");
		return arg0.getDate(arg1);
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
