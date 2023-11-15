package org.example;

import org.example.dao.worker.WorkerDaoImpl;
import org.example.enitty.Level;
import org.example.enitty.Worker;
import org.example.hibernate.HibernateUtils;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;

public class NewMain {
    public static void main (String[] args) {
        WorkerDaoImpl dao = new WorkerDaoImpl();

//        dao.createWorker(prepareWorker("Andrii", LocalDate.now().minusYears(20), 1200, Level.JUNIOR));
//        dao.createWorker(prepareWorker("Ivan", LocalDate.now().minusYears(35), 2100, Level.MIDDLE));
//        dao.createWorker(prepareWorker("Ostap", LocalDate.now().minusYears(21), 3400, Level.MIDDLE));
//        dao.createWorker(prepareWorker("Oleg", LocalDate.now().minusYears(21), 4200, Level.SENIOR));

//        dao.updateWorker(
//                prepareWorker(5L, "Modified Ostap", LocalDate.now().minusYears(22),
//                        3000, Level.MIDDLE));

//        Worker worker = dao.getWorkerById(3L);
//        System.out.println("--->>> " + worker);

//        List<Worker> workers = dao.getAllWorkers();
//        workers.forEach(worker -> System.out.println("worker list ==> " + worker));

//        List<Worker> maxSalaryWorkers = dao.getWorkersWithMaxSalary();
//        maxSalaryWorkers.forEach(worker -> System.out.println("worker with max salary ==> " + worker));

//        List<Worker> oldestAndYoungestWorkers = dao.getOldestAndYoungestWorkers();
//        oldestAndYoungestWorkers.forEach(worker -> System.out.println("oldest and youngest workers ==> " + worker));

//        dao.deleteWorkerById(3L);

//        dao.deleteWorker(
//                prepareWorker(4L, "Ivan", LocalDate.now().minusYears(35),
//                        2100, Level.MIDDLE));
    }

    private static Worker prepareWorker(String name, LocalDate birthday, Integer salary, Level level) {
        Worker worker = new Worker();
        worker.setName(name);
        worker.setBirthday(birthday);
        worker.setSalary(salary);
        worker.setLevels(level);
        return worker;
    }

    private static Worker prepareWorker(Long id, String name, LocalDate birthday, Integer salary, Level level) {
        Worker worker = prepareWorker(name, birthday, salary, level);
        worker.setId(id);
        return worker;
    }
}