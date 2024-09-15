package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public void addUserAndCar(User user, Car car) {
      user.setCar(car);
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public User getUserByCar(String model, int series) {
      try {
         Session session = sessionFactory.getCurrentSession();
         TypedQuery<User> query = session.createQuery("from User u where u.car.model = :model and u.car.series = :series ", User.class);
         query.setParameter("model", model);
         query.setParameter("series", series);
         query.setMaxResults(1);
         return query.getSingleResult();
      }
      catch (NoResultException e) {
         throw new RuntimeException("Пользователь, владеющий таким автомобилем не найден в БД", e);
      }
      catch (RuntimeException e) {
         throw new RuntimeException("Найдено несколько пользователей с таким автомобилем", e);
      }
      ////////////// По идее, в случае если в БД несколько юзеров с одниковыми моделями и сериями автомобилей, лучше возвращать лист юзеров.
      ////////////// Но по условию задачи нужно  вернуть именно одного юзера, поэтому сделал так.
   }


}
