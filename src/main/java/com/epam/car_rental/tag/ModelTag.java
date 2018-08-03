package com.epam.car_rental.tag;

import com.epam.car_rental.dao.connector.ConnectionPool;
import com.epam.car_rental.util.TagUtil;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModelTag extends TagSupport {
    private final static Logger LOGGER = Logger.getLogger(ModelTag.class);

    private String carId;

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    @Override
    public int doStartTag() throws JspException {
        Connection connection = null;
        String model = null;
        JspWriter out = pageContext.getOut();

        try {
            connection = ConnectionPool.getInstance().getConnection();
            String query = "select car_model from car_rental.car where car_id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,Integer.parseInt(carId));
            ResultSet set = statement.executeQuery();
            if(!set.isBeforeFirst()){
                model = "Отсутствует";
            }else{
                while (set.next()){
                    model = set.getString(1);
                    pageContext.setAttribute("model",model);
                }
            }
            return TagUtil.writeTag(out,model,"Login reading error", LOGGER);
        }catch (SQLException e){
            return TagUtil.writeTag(out,"Упс","Login oops", LOGGER);
        }finally {
            ConnectionPool.getInstance().closeConnection(connection);
        }
    }
}
