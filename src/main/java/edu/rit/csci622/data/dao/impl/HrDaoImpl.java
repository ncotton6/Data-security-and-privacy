package edu.rit.csci622.data.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import edu.rit.csci622.data.dao.HREmployeeHandlerDao;
import edu.rit.csci622.model.Hire;

public class HrDaoImpl extends GeneralDaoImpl implements HREmployeeHandlerDao{

	private final String resource = "edu/rit/csci622/data/dao/HRConfig.xml";
	private SqlSessionFactory factory;
	
	public HrDaoImpl() throws IOException {
		super();
		InputStream is = Resources.getResourceAsStream(resource);
		this.factory = new SqlSessionFactoryBuilder().build(is);
		is.close();
	}

	public void hrHireSignOff(int userId, int hrId, int roleId) {
		SqlSession session = factory.openSession();
		try{
			session.getMapper(HREmployeeHandlerDao.class).hrHireSignOff(userId, hrId, roleId);
			session.commit();
		}finally{
			if(session != null)
				session.close();
		}
	}

	public List<Hire> getHire() {
		SqlSession session = factory.openSession();
		try{
			List<Hire> ret = session.getMapper(HREmployeeHandlerDao.class).getHire();
			session.commit();
			return ret;
		}finally{
			if(session != null)
				session.close();
		}
	}

}
