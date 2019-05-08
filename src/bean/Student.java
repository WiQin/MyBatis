package bean;

import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @ClassName Student
 * @Description
 * @Author Wangyw
 */

/*@Alias("student")*/
//注解声明后需要配置
public class Student {
    private Integer studId;
    private String name;
    private String email;
    private Date dob;
    //自定义引用类型所有属性映射到一个字段
    private PhoneNumber phone;

    public Student() {
    }

    public Student(Integer studId, String name, String email, Date dob) {
        this.studId = studId;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Student(Integer studId, String name, String email, Date dob, PhoneNumber phone) {
        this.studId = studId;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.phone = phone;
    }

    public Integer getStudId() {
        return studId;
    }

    public void setStudId(Integer studId) {
        this.studId = studId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public PhoneNumber getPhone() {
        return phone;
    }

    public void setPhone(PhoneNumber phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studId=" + studId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", phone=" + phone +
                '}';
    }
}
