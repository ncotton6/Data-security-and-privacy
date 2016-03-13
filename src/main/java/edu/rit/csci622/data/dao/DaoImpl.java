package edu.rit.csci622.data.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import edu.rit.csci622.model.User;

public class DaoImpl implements Dao {

	private String resource = "edu/rit/csci622/data/dao/mybatis_config.xml";
	private String namespace ="edu.rit.csci622.data.dao.mapper.mapper";
	private SqlSessionFactory factory;
	
	public DaoImpl() throws IOException{
		InputStream is = Resources.getResourceAsStream(resource);
		this.factory = new SqlSessionFactoryBuilder().build(is);
		is.close();
	}

	public int createUser(User user, String key) {
		SqlSession session = factory.openSession();
		try{
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("key", key);
			params.put("user", user);
			int id = session.selectOne(namespace+".createUser",params);
			session.commit();
			return id;
		}finally{
			if(session != null)
				session.close();
		}
	}

	public User getUser(int userId, String key) {
		SqlSession session = factory.openSession();
		try{
			User user = session.selectOne(namespace+".getUser",userId);
			session.commit();
			return user;
		}finally{
			if(session != null)
				session.close();
		}
	}
	
}
