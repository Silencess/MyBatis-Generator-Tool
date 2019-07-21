package org.plum.generator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTest {
	 
    static String message=null;
 
    public static String test(String driver,String url,String user,String pwd) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(driver);
        
            // 打开链接
//            System.out.println("尝试连接数据库...");
            conn = DriverManager.getConnection(url,user,pwd);
        
            // 执行查询
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT 'X' FROM DUAL";
            ResultSet rs = stmt.executeQuery(sql);
        
            if(rs.next()) {
            	message = "success";
//            	System.out.println("数据库连接成功！");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
            message = se.toString();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
            message = e.toString();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return message;
    }
}
