package org.example;

import org.example.enitty.Level;
import org.example.enitty.Worker;
import org.example.hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main (String[] args) {
        Session session = HibernateUtils.getInstance().getSessionFactory().openSession();

//        // TODO create worker
//        Transaction transaction = session.beginTransaction();
//            Worker newWorker = new Worker();
//            newWorker.setName("Taras");
//            newWorker.setBirthday(LocalDate.of(2001, 12, 21));
//            newWorker.setSalary(5000);
//            newWorker.setLevels(Level.MIDDLE);
//            session.persist(newWorker);
//        transaction.commit();

//         //TODO get worker by id or list of workers
//        Worker gettedWorker = session.get(Worker.class, 1L);
//        System.out.println("worker ==> " + gettedWorker);
//
//        List<Worker> workers = session.createQuery("from Worker", Worker.class).list();
//        workers.forEach(worker -> System.out.println("worker list ==> " + worker));

//        // TODO update worker
//        Transaction transaction = session.beginTransaction();
//            Worker existing = session.get(Worker.class, 1L);
//            existing.setName("Modified Taras");
//            session.persist(existing);
//        transaction.commit();

//        // TODO update worker use merge
//        Transaction transaction = session.beginTransaction();
//            Worker mergeWorker = new Worker();
//            mergeWorker.setId(1L);
//            mergeWorker.setName("Taras");
//            mergeWorker.setBirthday(LocalDate.of(2001, 12, 21));
//            mergeWorker.setSalary(5001);
//            mergeWorker.setLevels(Level.MIDDLE);
//            session.merge(mergeWorker);
//        transaction.commit();

//        // TODO delete worker
//        Transaction transaction = session.beginTransaction();
//            Worker existing = session.get(Worker.class, 1L);
//            session.remove(existing);
//        transaction.commit();

        session.close();
    }
}