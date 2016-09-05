package cn.edu.nwsuaf.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by luxiaoping on 2016/8/8.
 */
public class DBAccess {
    public SqlSession getSession() throws IOException {
        Reader reader = Resources.getResourceAsReader("config/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        return sqlSession;
    }
}
