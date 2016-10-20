package ru.shevtsov.caloriesmng.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.shevtsov.caloriesmng.model.UserMeal;
import ru.shevtsov.caloriesmng.repository.UserMealRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by dead_rabbit
 * 19.10.2016
 */
@Repository
public class DataJpaUserMealRepositoryImpl implements UserMealRepository {

    @Autowired
    private ProxyUserMealRepository proxy;

    @Autowired
    private ProxyUserRepository userProxy;

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        userMeal.setUser(userProxy.getOne(userId));
        if (!userMeal.isNew() && get(userMeal.getId(), userId) == null) {
            return null;
        }
        return proxy.save(userMeal);
    }

    @Override
    public boolean delete(int id, int userId) {
        return proxy.delete(id, userId) != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        return proxy.get(id, userId);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return proxy.getAll(userId);
    }

    @Override
    public void deleteAll(int userId) {
    proxy.deleteAll(userId);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return proxy.getBetween(startDate, endDate, userId);
    }

}
