
package com.web.demo.utils;


import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;



public class ConnectDB {

	private static ConnectDB _instance = null;
	Reader _reader ;
	SqlSessionFactoryBuilder _builder;
	SqlSessionFactory _factory;
	static SqlSession _session;
	public static synchronized ConnectDB getInstance() throws Exception
	{
		if (_instance==null)
		{
			_instance = new ConnectDB();
		}
		return _instance;
	}
	 
	private ConnectDB() throws Exception {
		_reader = Resources.getResourceAsReader("MapperConfig.xml");
		_builder = new SqlSessionFactoryBuilder();
		_factory = _builder.build(_reader);
		
	}
	
	public void getReaderResources(String mapperFileName) throws Exception
	{
		_reader = Resources.getResourceAsReader(mapperFileName);
	}
	public SqlSession getSession()
	{	
		_session = _factory.openSession();
		return _session;
	}
	
	//static CustomerMapper mapper;
	//Reader reader = Resources.getResourceAsReader("MapperConfig.xml");
	//SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
	//SqlSessionFactory factory = builder.build(reader);
	//SqlSession session = factory.openSession();
	//CustomerMapper mapper = session.getMapper(CustomerMapper.class);
}
