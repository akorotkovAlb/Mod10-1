package org.example.dao.worker;

import org.example.enitty.Worker;
import org.example.hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WorkerDaoImpl implements WorkerDao {

    private static final String GET_ALL_WORKER_QUERY = "from Worker";

    @Override
    public boolean createWorker (Worker worker) {
        boolean result = false;
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                worker.setId(null);
                session.persist(worker);
                transaction.commit();
                result = true;
            } catch(Exception ex) {
                ex.printStackTrace();
                transaction.rollback();
            }
        }
        return result;
    }

    @Override
    public boolean updateWorker (Worker worker) {
        boolean result = false;
        if (Objects.isNull(worker.getId())) {
            return false;
        }
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.merge(worker);
                transaction.commit();
                result = true;
            } catch(Exception ex) {
                ex.printStackTrace();
                transaction.rollback();
            }
        }
        return result;
    }

    @Override
    public Worker getWorkerById (Long workerId) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            return session.get(Worker.class, workerId);
        }
    }

    @Override
    public List<Worker> getAllWorkers () {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            return session.createQuery(GET_ALL_WORKER_QUERY, Worker.class).list();
        }
    }

    @Override
    public void deleteWorkerById (Long workerId) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Worker existing = session.get(Worker.class, workerId);
            session.remove(existing);
            transaction.commit();
        }
    }

    @Override
    public void deleteWorker (Worker worker) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(worker);
            transaction.commit();
        }
    }

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
