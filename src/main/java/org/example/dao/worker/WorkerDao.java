package org.example.dao.worker;

import org.example.enitty.Worker;

import java.util.List;

public interface WorkerDao {

    List<Worker> getWorkersWithMaxSalary();

    List<Worker> getOldestAndYoungestWorkers();
}
