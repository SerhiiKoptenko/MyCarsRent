package model.DAO.service;

import model.DAO.CarDAO;
import model.DAO.DaoFactory;
import model.entity.Car;
import model.entity.Client;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * service layer to call CarDAO
 */
public class CarService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public boolean addCar(Car car) {
        boolean result = false;
        try (CarDAO carDao = daoFactory.createCarDao()) {
            result = carDao.addCar(car);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean deleteCar(int id) {
        boolean result = false;
        try (CarDAO carDao = daoFactory.createCarDao()) {
            result = carDao.deleteCar(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Car getCarById(int id) {
        Car car = new Car();
        try (CarDAO carDao = daoFactory.createCarDao()) {
            car = carDao.getCarById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return car;
    }

    public boolean updatePrice(BigDecimal price, Car car) {
        boolean result = false;
        try (CarDAO carDao = daoFactory.createCarDao()) {
            result = carDao.updatePrice(price, car);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Car> getCarsByClass(String carClass, String column, String sortingOrder) {
        List<Car> list = new ArrayList<>();
        try (CarDAO carDao = daoFactory.createCarDao()) {
            list = carDao.getCarsByClass(carClass, column, sortingOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Car> getCarsByMarque(String marque, String column, String sortingOrder) {
        List<Car> list = new ArrayList<>();
        try (CarDAO carDao = daoFactory.createCarDao()) {
            list = carDao.getCarsByMarque(marque, column, sortingOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Car> getCarsByClient(Client client, int index, int offset) {
        List<Car> list = new ArrayList<>();
        try (CarDAO carDao = daoFactory.createCarDao()) {
            list = carDao.getCarsByClient(client, index, offset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Car> getAllCars(String column, String sortingOrder) {
        List<Car> list = new ArrayList<>();
        try (CarDAO carDao = daoFactory.createCarDao()) {
            list = carDao.getAllCars(column, sortingOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Car> getOrderedCars(int index, int offset) {
        List<Car> list = new ArrayList<>();
        try (CarDAO carDao = daoFactory.createCarDao()) {
            list = carDao.getOrderedCars(index, offset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
