package edu.rit.csci622.data.dao;

import java.lang.reflect.Method;

import org.jasypt.util.text.BasicTextEncryptor;

import edu.rit.csci622.data.PasswordHandler;

public class Dao {

	protected final BasicTextEncryptor encryptor = new BasicTextEncryptor();

	public Dao() {
		this.encryptor.setPassword(PasswordHandler.getAppPassword());
	}

	
	public String encrypt(String original){
		return this.encryptor.encrypt(original);
	}
	
	public String decrypt(String encrypted){
		return this.encryptor.decrypt(encrypted);
	}
	
	public Object encrypt(Class<?> clazz, Object obj) {
		/*try {
			Object ret = clazz.newInstance();
			Method[] methods = clazz.getMethods();
			for (Method m : methods) {
				try {
					if (m.getName().startsWith("get")) {
						String setMethodName = "set" + m.getName().substring(3);
						Method set = clazz.getMethod(setMethodName, m.getReturnType());
						Object value = m.invoke(obj, null);
						System.out.println(value.getClass() + " --- " + (value instanceof String));
						if (value instanceof String) {
							System.out.println("It works!!!!!!");
							String strValue = (String) value;
							String encrypted = encryptor.encrypt(strValue);
							System.out.println(
									m.getName() + " == (" + value + ") ==> " + set.getName() + " [" + encrypted + "]");
							set.invoke(ret, encrypted);
						}
					}
				} catch (Throwable e) {
					System.out.println("///////////////////////");
					e.printStackTrace();
				}
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;*/
		return obj;
	}

	public Object decrypt(Class<?> clazz, Object obj) {
		/*try {
			Object ret = clazz.newInstance();
			Method[] methods = clazz.getMethods();
			for (Method m : methods) {
				try {
					if (m.getName().startsWith("get")) {
						String setMethodName = "set" + m.getName().substring(3);
						Method set = clazz.getMethod(setMethodName, m.getReturnType());
						Object value = m.invoke(obj, null);
						System.out.println(value.getClass() + " --- " + (value instanceof String));
						if (value instanceof String) {
							System.out.println("It works!!!!!!");
							String strValue = (String) value;
							String encrypted = encryptor.decrypt(strValue);
							System.out.println(
									m.getName() + " == (" + value + ") ==> " + set.getName() + " [" + encrypted + "]");
							set.invoke(ret, encrypted);
						}
					}
				} catch (Throwable e) {
					System.out.println("++++++++++++++++++++++++++++++");
					e.printStackTrace();
				}
			}
			return ret;
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return null;*/
		return obj;
	}

}
