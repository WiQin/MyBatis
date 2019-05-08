package test;

import bean.Student;

import java.util.Date;

/**
 * @ClassName Test
 * @Description
 * @Author Wangyw
 */
public class Test {
    public static void main(String[] args) {
        //1.创建util对象
        ORMUtil util = new ORMUtil();
        //2.创建Student对象
        Student student = new Student(2,"Wix","xxx",new Date());
        //3.通过util.save(student),将student中的数据持久化到数据库中
        util.save(student);
    }
}
