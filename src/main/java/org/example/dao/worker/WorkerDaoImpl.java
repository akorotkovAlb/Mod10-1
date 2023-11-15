package org.example.dao.worker;

import org.example.enitty.Worker;
import org.example.hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WorkerDaoImpl implements WorkerDao {

    @Override
    public List<Worker> getWorkersWithMaxSalary() {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Integer maxSalary = session.createQuery("SELECT MAX(salary) FROM Worker", Integer.class)
                    .getSingleResult();
            return session
                    .createQuery("FROM Worker WHERE salary = :maxSalary", Worker.class)
                    .setParameter("maxSalary", maxSalary)
                    .list();
        }
    }

    @Override
    public List<Worker> getOldestAndYoungestWorkers() {
        List<Worker> result = new ArrayList<>();
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                LocalDate minBirthday = session.createQuery("SELECT MIN(birthday) FROM Worker", LocalDate.class)
                        .getSingleResult();
                LocalDate maxBirthday = session.createQuery("SELECT MAX(birthday) FROM Worker", LocalDate.class)
                        .getSingleResult();
                result = session
                        .createQuery("FROM Worker WHERE birthday = :minBirthday OR birthday = :maxBirthday", Worker.class)
                        .setParameter("minBirthday", minBirthday)
                        .setParameter("maxBirthday", maxBirthday)
                        .list();
                transaction.commit();
            } catch(Exception ex) {
                ex.printStackTrace();
                transaction.rollback();
            }
            return result;
        }
    }
}
