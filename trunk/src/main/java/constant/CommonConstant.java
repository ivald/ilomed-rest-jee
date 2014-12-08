package constant;

/**
 * Created by ivald79 on 29/08/2014.
 */
public interface CommonConstant {

    public final static String USER_TICKET = "USER_TICKET";
    public final static String MRESTDB_JNDI = "jdbc/MySQLDataSource";

    //Digits
    public final static Long ZERO_INTEGER = 0L;
    public final static Long ONE_INTEGER = 1L;

    //setting session to expiry in 30 mins (30min * 60 sec)
    public final static Integer SESSION_TIME_OUT = 1800;
}
