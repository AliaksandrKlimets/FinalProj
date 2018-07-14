package com.epam.car_rental.service.info;

import com.epam.car_rental.entity.Fine;
import com.epam.car_rental.service.ServiceException;

import java.util.List;

public interface FineService {
    List<Fine> getFines() throws ServiceException;

    List<Fine> getUserFines(String userId) throws ServiceException;

    Fine getFine(String fineId) throws ServiceException;

    void changePaymentState(String fineId, String state) throws ServiceException;

    void deleteFine(String fineId) throws ServiceException;

    void addFine(String userId,String carId, String cause, String bill, String dueDate) throws ServiceException;

    List<Fine> getUnpaidFines() throws ServiceException;
}
