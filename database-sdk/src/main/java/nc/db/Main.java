package nc.db;

import java.math.BigDecimal;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2.获取数据库连接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/heima_log", "root", "test*321");

        //3.获取发送 sql 语句对象
        Statement statement = connection.createStatement();

        //4.执行 SQL 语句并接收结果集
        ResultSet resultSet = statement.executeQuery("select  * from log_info");
        //5 处理结果集
        while(resultSet.next()){
            //5.1有数据，依据列名获取数据
            BigDecimal id = resultSet.getBigDecimal("id");
            Date date = resultSet.getDate("createTime");
            String content = resultSet.getString("content");
            System.out.println(id+"\t"+date.toString()+"\t"+content);
        }

        //6.释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }
}