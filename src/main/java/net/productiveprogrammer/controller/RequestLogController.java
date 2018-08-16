package net.productiveprogrammer.controller;

import net.productiveprogrammer.dao.RequestLogDao;
import net.productiveprogrammer.service.RequestLogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/requests")
public class RequestLogController {

    private final RequestLogService requestLogService;

    public RequestLogController(RequestLogService requestLogService) {
        this.requestLogService = requestLogService;
    }

    @RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    public List<RequestLogDao> getAll(){
        return requestLogService.getAll();
    }

}
