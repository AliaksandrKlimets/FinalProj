package com.epam.car_rental.service.info;

import com.epam.car_rental.entity.Fine;
import com.epam.car_rental.service.ServiceException;

import java.util.List;

public interface FineService {
    List<Fine> getFines(int begin, int size) throws ServiceException;

    List<Fine> getUserFines(String userId, int begin, int size) throws ServiceException;

    Fine getFine(String fineId) throws ServiceException;

    void changePaymentState(String fineId, String state, String number) throws ServiceException;

    void deleteFine(String fineId) throws ServiceException;

    void addFine(String userId, String carId, String cause, String bill, String dueDate) throws ServiceException;

    List<Fine> getUnpaidFines(int begin, int size) throws ServiceException;

    int finesCount() throws ServiceException;

    int userFinesCount(int id) throws ServiceException;

    int unpaidFinesCount() throws ServiceException;
}
