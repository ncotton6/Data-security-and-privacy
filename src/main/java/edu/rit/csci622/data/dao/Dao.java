package edu.rit.csci622.data.dao;

import java.lang.reflect.Method;

import org.jasypt.util.text.BasicTextEncryptor;

import edu.rit.csci622.data.PasswordHandler;

public class Dao {

	protected final BasicTextEncryptor encryptor = new BasicTextEncryptor();

	public Dao() {
		this.encryptor.setPassword(PasswordHandler.getAppPassword());
	}

	public Object encrypt(Class<?> clazz, Object obj) {
		try {
			Object ret = clazz.newInstance();
			Method[] methods = clazz.getMethods();
			for (Method m : methods) {
				if (m.getName().startsWith("get")) {
					String setMethodName = "set" + m.getName().substring(3);
					Method set = clazz.getMethod(setMethodName, m.getReturnType());
					Object value = m.invoke(obj, null);
					if (value instanceof String) {
						String strValue = (String) value;
						set.invoke(ret, encryptor.encrypt(strValue));
					}
				}
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object decrypt(Class<?> clazz, Object obj) {
		try {
			Object ret = clazz.newInstance();
			Method[] methods = clazz.getMethods();
			for (Method m : methods) {
				try{
				if (m.getName().startsWith("get")) {
					String setMethodName = "set" + m.getName().substring(3);
					Method set = clazz.getMethod(setMethodName, m.getReturnType());
					Object value = m.invoke(obj, null);
					if (value instanceof String) {
						String strValue = (String) value;
						set.invoke(ret, encryptor.decrypt(strValue));
					}
				}
				}catch(Exception e){
					
				}
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
