package io.khasang.moika.service;

import io.khasang.moika.entity.Queue;

import java.util.List;

public interface QueueService {
    void addQueue(Queue company);
    Queue getQueueById(long id);
    void updateQueue(Queue queue);
    void deletуQueue(Queue queue);
    List<Queue> getAllQueue();
}
