package com.epam.car_rental.tag;

import com.epam.car_rental.dao.DAOFactory;
import com.epam.car_rental.dao.connector.ConnectionPool;
import com.epam.car_rental.dao.user.UserDAO;
import com.epam.car_rental.service.ServiceFactory;
import com.epam.car_rental.service.user.UserService;
import com.epam.car_rental.util.TagUtil;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginTag extends TagSupport {
    private final static Logger LOGGER = Logger.getLogger(LoginTag.class);

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public int doStartTag() throws JspException {
        Connection connection = null;
        String login = null;
        JspWriter out = pageContext.getOut();

        try {
            connection = ConnectionPool.getInstance().getConnection();
            String query = "select login from car_rental.user_info inner join car_rental.user on user.user_id = user_info.user_id where user.user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,Integer.parseInt(userId));
            ResultSet set = statement.executeQuery();
            if(!set.isBeforeFirst()){
                login = "null";
            }else{
                while (set.next()){
                    login = set.getString(1);
                    pageContext.setAttribute("login",login);
                }
            }
            return TagUtil.writeTag(out,login,"Login reading error", LOGGER);
        }catch (SQLException e){
                return TagUtil.writeTag(out,"Упс","Login oops", LOGGER);
        }finally {
            ConnectionPool.getInstance().closeConnection(connection);
        }
    }
}
