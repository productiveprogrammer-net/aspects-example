package net.productiveprogrammer.service;

import net.productiveprogrammer.dao.RequestLogDao;

import java.util.List;

public interface RequestLogService {

    void saveRequestLog(final RequestLogDao requestLog);

    List<RequestLogDao> getTodaysRequestLogs();

    List<RequestLogDao> getAll();
}
