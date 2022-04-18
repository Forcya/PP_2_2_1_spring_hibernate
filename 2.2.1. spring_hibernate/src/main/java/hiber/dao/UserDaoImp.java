package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user, Car car) {
      user.setCar(car);
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public List<User> getByCar (String model, int series) {
      Session session = sessionFactory.getCurrentSession();
      Query<User> query = session.createQuery("FROM User as usr INNER JOIN FETCH usr.car as cr WHERE cr.model = :model AND cr.series = :series");
      query.setParameter("model", model);
      query.setParameter("series", series);
      List<User> listUser = query.getResultList();
      return listUser;
   }
}
