package com.epam.car_rental.service.user;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.dao.DAOFactory;
import com.epam.car_rental.dao.EntityExistException;
import com.epam.car_rental.dao.EntityNotFoundException;
import com.epam.car_rental.dao.user.UserDAO;
import com.epam.car_rental.entity.User;
import com.epam.car_rental.service.ServiceException;
import com.epam.car_rental.service.validation.InputException;
import com.epam.car_rental.service.validation.InvalidParametersException;
import com.epam.car_rental.service.validation.validator.UserValidator;
import com.epam.car_rental.service.validation.validator.Validator;

import java.sql.Date;
import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public List<User> getUsers(int begin, int size) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try {
            return userDAO.getUsers(begin,size);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public User getUser(String login, String password) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try {
            UserValidator.isLogin(login);
            UserValidator.isPassword(password);

            return userDAO.getUser(login, password);
        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public void changeLogin(String userId, String login) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try {
            Validator.isNumber(userId);
            UserValidator.isLogin(login);
            userDAO.changeLogin(Integer.parseInt(userId), login);
        } catch (EntityExistException e) {
            throw new UserExistException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public void changePassword(String userId,String oldPassword, String newPassword) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try {
            Validator.isNumber(userId);
            UserValidator.isPassword(newPassword);
            UserValidator.isPassword(oldPassword);
            userDAO.changePassword(Integer.parseInt(userId),oldPassword, newPassword);
        }catch (EntityNotFoundException e){
            throw new UserNotFoundException(e.getMessage());
        }catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public void changeEmail(String  userId, String email) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try {
            Validator.isNumber(userId);
            UserValidator.isEmail(email);
            userDAO.changeEmail(Integer.parseInt(userId), email);
        } catch (EntityExistException e){
            throw new UserExistException(e.getMessage());
        }catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public void changePhone(String userId, String phone) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try {
            Validator.isNumber(userId);
            UserValidator.isPhone(phone);
            userDAO.changePhone(Integer.parseInt(userId), phone);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public void addUser(User user, String birthDate) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try{
            UserValidator.isInputDataValid(user, birthDate);
            Date date = Date.valueOf(birthDate);
            user.setBirthDate(date);
            userDAO.addUser(user);
        }catch (EntityExistException e){
            throw new UserExistException(e.getMessage());
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }catch (InputException e){
            throw new InvalidParametersException(e.getMessage());
        }
    }


    @Override
    public int usersCount() throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try{
            return userDAO.usersCount();
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }
}
