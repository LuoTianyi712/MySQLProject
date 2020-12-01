import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String [] args)
    {
        //实例化属性对象

        Connection connection = null;
        Statement statement = null;



        try {
            //注册JDBC驱动
            Class.forName(mysqlProperties.jdbcDriver);

            //打开链接
            System.out.println("连接数据库");
            connection = DriverManager.getConnection(
                    mysqlProperties.dbUrl,
                    mysqlProperties.user,
                    mysqlProperties.pass);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            statement = connection.createStatement();
            String sql;
            sql = "select * from Student";



            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {
                int id = resultSet.getInt("stu_id");
                String name = resultSet.getString("stu_name");
                String sex = resultSet.getString("stu_sex");
                String birth = resultSet.getString("stu_birth");

                System.out.println("学生ID"+"\t\t"+id);
                System.out.println("学生姓名"+"\t\t"+name);
                System.out.println("学生性别"+"\t\t"+sex);
                System.out.println("学生生日"+"\t\t"+birth);
                System.out.println();
            }
            //关闭
            resultSet.close();
            statement.close();
            connection.close();


        } catch (Exception se) {
            //处理JDBC错误
            se.printStackTrace();
        }//处理Class.forName
        finally {
            //释放资源,关闭端口
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se2) {
                //不执行
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("END");
    }
}
