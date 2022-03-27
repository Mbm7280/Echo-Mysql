package com.echo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Echo
 * @Description:    测试类
 * @date 2022/3/27
 * @Version 1.0
 */
public class Test001 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        EchoDataSource dataSource = new EchoDataSource(2);
        for (int i = 0; i < 10; i++) {
            Connection con = dataSource.getConnection();
            System.out.println("发送连接:" + con);
            PreparedStatement pstmt = con.prepareStatement("select * from echo_order where id=1;");
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet == null) {
                return;
            }
            if (resultSet.next()) {
                String id = resultSet.getString("id");
                String orderName = resultSet.getString("order_name");
                System.out.println("查询结果:id:" + id + ",orderName" + orderName);
            }
            if (resultSet != null) resultSet.close();
            if (pstmt != null) pstmt.close();
//            con.close();;
            dataSource.closeConnection(con);
        }


    }


}
