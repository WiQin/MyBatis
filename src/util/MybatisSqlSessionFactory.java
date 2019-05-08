package util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName MybatisSqlSessionFactory
 * @Description 获取SqlSession实现类对象
 * @Author Wangyw
 */
public class MybatisSqlSessionFactory {
    private static SqlSessionFactory sqlSessionFactory;

    public static SqlSessionFactory getSqlSessionFactory(){
        if(sqlSessionFactory == null){
            try {
                InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory =  new SqlSessionFactoryBuilder().build(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sqlSessionFactory;
    }

    public static SqlSession getSqlSession(){
        return  getSqlSession(false);
    }

    public static SqlSession getSqlSession(Boolean autocommit){
        return  getSqlSessionFactory().openSession(autocommit);
    }
}
