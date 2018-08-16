package net.productiveprogrammer.service;

import net.productiveprogrammer.dao.RequestLogDao;
import net.productiveprogrammer.repository.RequestLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
public class RequestLogServiceImpl implements RequestLogService {

    private static final Executor EXECUTOR = Executors.newFixedThreadPool(5);
    private final RequestLogRepository requestLogRepository;

    public RequestLogServiceImpl(RequestLogRepository requestLogRepository) {
        this.requestLogRepository = requestLogRepository;
    }

    @Override
    public void saveRequestLog(final RequestLogDao requestLog) {
        EXECUTOR.execute(() -> requestLogRepository.saveAndFlush(requestLog));
    }

    @Override
    public List<RequestLogDao> getTodaysRequestLogs() {
        return requestLogRepository.findAllByCreatedDateTimeBetween(null, null);
    }

    @Override
    public List<RequestLogDao> getAll() {
        return requestLogRepository.findAll();
    }
}
