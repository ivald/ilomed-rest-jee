package repository.impl;

import constant.CommonConstant;
import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ivald79 on 30/09/2014.
 */
public abstract class AbstractSQLRepositoryImpl {

    private static final Logger LOGGER = Logger.getLogger(AbstractSQLRepositoryImpl.class.getName());

    private DataSource dataSource = null;

    public AbstractSQLRepositoryImpl() {
        this.dataSource = lookup(CommonConstant.MRESTDB_JNDI);
    }

    private DataSource lookup(String jndiDS) {
        try {
            InitialContext ic = new InitialContext();
            return  (DataSource) ic.lookup(jndiDS);
        } catch (NamingException ne) {
            LOGGER.error(ne);
            return null;
        }
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * The method implements exists row update/delete.
     *
     * @throws SQLException
     */
    protected void executeUpdate() throws SQLException {
        this.getPreparedStatement().executeUpdate();
    }

    /**
     * The method implements fetch rows from db.
     *
     * @return ResultSet
     * @throws SQLException
     */
    protected ResultSet executeQuery() throws SQLException {
        return this.getPreparedStatement().executeQuery();
    }

    protected void closeConnection(Connection conn) {
        try {
            if(conn != null) {
                conn.close();
            }
            LOGGER.info("Connection was closed successfully.");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    protected abstract PreparedStatement getPreparedStatement();
}
