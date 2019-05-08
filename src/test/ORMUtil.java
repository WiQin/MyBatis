package test;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.*;


/**
 * @ClassName ORMUtil
 * @Description
 * save()方法
 * 传入对象，将对象持久化到数据库中
 * @Author Wangyw
 */
public class ORMUtil {
    //读取配置文件保存类型与表的对应关系
    //k:全类名 v:表名
    private Map<String,String> tableMap;
    //读取配置文件，保存属性与字段的对应关系
    //k:属性名  v:字段
    private Map<String,String> columnMap;

    public ORMUtil() {
        tableMap = new HashMap<String,String>();
        columnMap = new HashMap<String,String>();
        //解析xml配置文件   路径一定要写对，从项目名下的包开始写
        parseXML("src/bean/student.xml");
    }

    //解析xml文件，初始化tableMap与columnMap
    public void parseXML(String path){
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(path);
            Element rootClass = doc.getRootElement();

            //获取类名
            String className = rootClass.attributeValue("class");
            //获取表名
            String tableName = rootClass.attributeValue("table");
            tableMap.put(className,tableName);

            //获取到所有的poperty
            List props = rootClass.elements();
            for(int i = 0;i<props.size();i++){
                Element ele = (Element) props.get(i);
                String filedName = ele.attributeValue("name");
                String columnname = ele.attributeValue("column");
                columnMap.put(filedName,columnname);
            }
            System.out.println(tableMap);
            System.out.println(columnMap);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public String mysub(String str){
        return  str.substring(0,str.length()-1);
    }

    public static void main(String[] args) {

    }

    public void save(Object obj){
        Class<? extends Object> aClass = obj.getClass();

        //获取到实际保存的对象的全类名
        String className = aClass.getName();
        System.out.println(className);
        //获取到对应的数据库表名
        String tableName = tableMap.get(className);

        //保存列名字符串stud_id,name.email,dob
        String columnStr = "";
        //保存对应列的?
        String valueStr = "";
        //索引
        int index = 1;
        //索引对应的类型名称
        Map<Integer,String> fieldTypeNames =
                new HashMap<Integer, String>();
        //索引对应的属性值
        Map<Integer,Object> fieldValuesNames =
                new HashMap<Integer, Object>();

        Set<String> fieldNames = columnMap.keySet();
        //遍历获取所有的属性名
        for(String fieldName : fieldNames){
            System.out.println(fieldName+" ");
            //通过属性名获取字段名
            String columnName = columnMap.get(fieldName);
            //拼接字段字符串
            columnStr += columnName + ",";
            //拼接?字符串
            valueStr += "?,";

            try {
                //获取到属性名对应的属性对象
                Field field = aClass.getDeclaredField(fieldName);
                //获取属性类型
                String fieldTypeName = field.getType().getName();
                fieldTypeNames.put(index,fieldTypeName);
                //获取参数obj的参数值
                field.setAccessible(true);
                Object fieldValue = field.get(obj);
                fieldValuesNames.put(index,fieldValue);

            } catch (Exception e) {
                e.printStackTrace();
            }

            //获取插入值的索引
            index++;
        }
        System.out.println(columnStr);
        System.out.println(valueStr);

        String sql = "insert into "+tableName+"("+mysub(columnStr)+") values ("+mysub(valueStr)+")";
        System.out.println(sql);

        System.out.println(fieldTypeNames);
        System.out.println(fieldValuesNames);

        try {
            //1.加载驱动
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //2.获取连接
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:ORCL", "wangyw", "wangyw");
            PreparedStatement prep = conn.prepareStatement(sql);
            //在对应索引位置应该设置什么类型的什么值

            Set<Integer> keys = fieldTypeNames.keySet();
            for(int key : keys){
                if(fieldTypeNames.get(key).contains("Integer")){
                    prep.setInt(key,(Integer)fieldValuesNames.get(key));
                }else if(fieldTypeNames.get(key).contains("String")){
                    prep.setString(key,(String)fieldValuesNames.get(key));
                }else if(fieldTypeNames.get(key).contains("Date")){
                    prep.setDate(key,new java.sql.Date(((Date)fieldValuesNames.get(key)).getTime()) );
                }
            }
            prep.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
