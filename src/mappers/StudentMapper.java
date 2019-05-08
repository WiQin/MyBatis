package mappers;

import java.util.List;
import bean.Student;

/**
* @Author Wangyw
* @Description   映射接口
 * 方法名对应映射文件中标签ID值
* @Date 2019/3/19 0019
* @Param
* @Return
**/


//创建映射器Mapper接口StudentMapper
//方法名和StudentMapper.xml中定义的SQL映射定义名相同
//这个其实就是dao层接口(数据访问层,负责和数据库进行交互)
public interface StudentMapper {
    List<Student> findAllStudents();
    Student findStudentById(Integer id);
    void insertStudent(Student student);
    void updateStudent(Student student);
    void deleteStudentById(Integer id);
    void insertStudentType(Student student);
    Student selectStudentByID_Type(Integer id);
}
