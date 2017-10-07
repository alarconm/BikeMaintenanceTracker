package com.bikemaintapp.Bike.Maintenance.App.validations;

import com.bikemaintapp.Bike.Maintenance.App.models.User;
import com.bikemaintapp.Bike.Maintenance.App.models.data.IUserInfoDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserInfoDao implements IUserInfoDao {

    @PersistenceContext
    private EntityManager entityManager;

    public User getActiveUser(String userName) {
        User activeUserInfo = new User();
        short enabled = 1;

        List<?> list = entityManager.createQuery("SELECT u FROM User u WHERE userName=? and enabled=?")
                .setParameter(1, userName).setParameter(2, enabled).getResultList();

        if (!list.isEmpty()) {
            activeUserInfo = (User) list.get(0);
        }
        return activeUserInfo;
    }
}