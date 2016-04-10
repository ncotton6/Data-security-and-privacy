package edu.rit.csci622.data.dao;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;

import edu.rit.csci622.data.PasswordHandler;

public class Dao {

	protected final BasicTextEncryptor encryptor = new BasicTextEncryptor();
	protected final BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

	public Dao() {
		this.encryptor.setPassword(PasswordHandler.getAppPassword());
	}

	public String encrypt(String original) {
		return this.encryptor.encrypt(original);
	}

	public String decrypt(String encrypted) {
		return this.encryptor.decrypt(encrypted);
	}

}
