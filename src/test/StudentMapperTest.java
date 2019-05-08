package test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import bean.PhoneNumber;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import mappers.StudentMapper;
import bean.Student;
import util.MybatisSqlSessionFactory;

/**
 * @ClassName StudentMapperTest
 * @Description
 * @Author Wangyw
 */
public class StudentMapperTest {

    @Test
    public void test(){
        Student s = new Student(2,"tom2","222@briup.com",new Date());
        String fName = "studId";
        String fName2 = "get"+fName.substring(0,1).toUpperCase()+fName.substring(1);
        System.out.println(fName2);

        Class<? extends Student> c = s.getClass();
        try {
            Method method = c.getMethod(fName2);
            Object value = method.invoke(s);
            System.out.println(value);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void test_insert(){

        try {
            //1.读取mybatis的配置文件，mybatis-config.xml
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //2.获取SqlSessionFactory工厂类对象
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //3.获取SqlSession实现类对象
            SqlSession sqlSession = sqlSessionFactory.openSession();
            //4.通过session实现类动态获取映射接口的实现类对象
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            //5.执行实现类中的方法
            Student s = new Student(4,"XLCH_Wix","123@briup.com",new Date());
            studentMapper.insertStudent(s);
            //6.提交事务
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test_insert2(){

        try {
            //1.读取mybatis的配置文件，mybatis-config.xml
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //2.获取SqlSessionFactory工厂类对象
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //3.获取SqlSession实现类对象
            SqlSession sqlSession = sqlSessionFactory.openSession();
            //4.执行实现类中的方法
            Student s = new Student(3,"XLCH_Wix","123@briup.com",new Date());
            sqlSession.insert("mappers.StudentMapper.insertStudent",s);
            //5.提交事务
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test_selectById(){
        try {
            InputStream in =
                    Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactory ssf =
                    new SqlSessionFactoryBuilder().build(in);

            SqlSession sqlSession =
                    ssf.openSession();

            StudentMapper mapper =
                    sqlSession.getMapper(StudentMapper.class);

            Student s = mapper.findStudentById(1);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_selectAll(){
        try {
            //1.读取配置文件
            InputStream is =
                    Resources.getResourceAsStream("mybatis-config.xml");
            //2.创建工厂对象
            SqlSessionFactory ssf =
                    new SqlSessionFactoryBuilder().build(is);
            //3.获取sqlsession对象
            SqlSession sqlSession =
                    ssf.openSession();
            //4.获取映射接口的实现类对象
            StudentMapper mapper =
                    sqlSession.getMapper(StudentMapper.class);
            //5.执行方法--sql
            List<Student> s = mapper.findAllStudents();
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_updateStudent(){

        SqlSession sqlSession = null;

        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
            sqlSession = ssf.openSession();

            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

            Student s = new Student(1,"XLCH_SiriusKing","",new Date());
            mapper.updateStudent(s);

            sqlSession.commit();
        } catch (IOException e) {
            sqlSession.rollback();
            e.printStackTrace();
        }
    }

    @Test
    public void test_deleteStudentById(){

        SqlSession sqlSession = null;

        try {

            /*InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory rsf = new SqlSessionFactoryBuilder().build(is);
            sqlSession = rsf.openSession();*/

            sqlSession = MybatisSqlSessionFactory.getSqlSession();
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

            mapper.deleteStudentById(2);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        }
    }

    @Test
    public void test_insertType(){
        SqlSession sqlSession = null;

        try {

            sqlSession = MybatisSqlSessionFactory.getSqlSession(true);
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            PhoneNumber number = new PhoneNumber("110", "120", "130");
            mapper.insertStudentType(new Student(2,"SiriusKing","111",new Date(),number));

        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        }
    }

    @Test
    public void test_selectStudentByID_Type(){
        SqlSession sqlSession = null;

        try {

            sqlSession = MybatisSqlSessionFactory.getSqlSession();
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

            Student student = mapper.selectStudentByID_Type(2);
            System.out.println(student);

        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        }
    }
}



