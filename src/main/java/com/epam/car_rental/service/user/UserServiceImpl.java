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

import java.sql.Date;
import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public List<User> getUsers() throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try {
            return userDAO.getUsers();
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
    public void changeLogin(int userId, String login) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try {
            UserValidator.isLogin(login);
            userDAO.changeLogin(userId, login);
        } catch (EntityExistException e) {
            throw new UserExistException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public void changePassword(int userId, String newPassword) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try {
            UserValidator.isPassword(newPassword);
            userDAO.changePassword(userId, newPassword);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public void changeName(int id, String name) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try {
            UserValidator.isName(name);
            userDAO.changeName(id, name);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public void changeSurname(int id, String surname) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try {
            UserValidator.isName(surname);
            userDAO.changeSurname(id, surname);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public void changeEmail(int id, String email) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try {
            UserValidator.isEmail(email);
            userDAO.changeEmail(id, email);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public void changePhone(int id, String phone) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try {
            UserValidator.isPhone(phone);
            userDAO.changePhone(id, phone);
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
    public void deleteUser(int id) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try{
            userDAO.deleteUser(id);
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public long getUserIdByLogin(String login) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try{
            UserValidator.isLogin(login);
            return userDAO.getUserIdByLogin(login);
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }catch (InputException e){
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public User getUserByLogin(String login) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try{
            UserValidator.isLogin(login);
            return userDAO.getUserByLogin(login);
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }catch (InputException e){
            throw new InvalidParametersException(e.getMessage());
        }
    }
}
