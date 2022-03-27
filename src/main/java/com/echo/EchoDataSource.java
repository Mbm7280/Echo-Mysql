package com.echo;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * @author Echo
 * @Description:    自定义数据库链接
 * @date 2022/3/27
 * @Version 1.0
 */
public class EchoDataSource implements DataSource {

    private String driverClassName = "com.mysql.jdbc.Driver";    //启动驱动
    private String url = "jdbc:mysql://localhost:3306/echo_order";    //设置连接路径
    private String username = "root";    //数据库用户名
    private String password = "admin";    //数据库连接密码
    private LinkedList<Connection> connections = new LinkedList<>();


    public EchoDataSource (int initialSize) throws ClassNotFoundException, SQLException {
        Class.forName(driverClassName); //执行驱动
        for (int i = 0; i < initialSize; i++) {
            Connection connection = DriverManager.getConnection(url, username, password); //获取连接
            connections.add(connection);
        }
    }

    /**
     * 获取连接
     * @return
     * @throws SQLException
     */
    @Override
    public Connection getConnection() throws SQLException {
        Connection connection = connections.removeFirst();
        return connection;
    }

    /**
     * 关闭连接
     */
    public void closeConnection(Connection connection) {
        connections.add(connection);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
