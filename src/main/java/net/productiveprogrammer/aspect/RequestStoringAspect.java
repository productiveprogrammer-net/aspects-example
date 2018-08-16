package net.productiveprogrammer.aspect;

import net.productiveprogrammer.dao.ArgumentDao;
import net.productiveprogrammer.dao.RequestLogDao;
import net.productiveprogrammer.service.MethodParser;
import net.productiveprogrammer.service.ArgumentMatcher;
import net.productiveprogrammer.service.RequestLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.h2.util.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Aspect
@Component
public class RequestStoringAspect {

    private final RequestLogService requestLogService;
    private final MethodParser methodParser;
    private final ArgumentMatcher argumentMatcher;

    public RequestStoringAspect(final RequestLogService requestLogService,
                                final MethodParser methodParser,
                                final ArgumentMatcher argumentMatcher) {
        this.requestLogService = requestLogService;
        this.methodParser = methodParser;
        this.argumentMatcher = argumentMatcher;
    }

    @Pointcut("execution(public * * (..))")
    public void getPublicMethods() {
    }

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void getRestControllers() {
    }

    @Around("getPublicMethods() && getRestControllers()")
    public Object restControllerPublicMethods(final ProceedingJoinPoint pjp) throws Throwable {
        var sw = new StopWatch();
        var methodSignature = pjp.getSignature().toLongString();
        var params = methodParser.parse(methodSignature);
        var args = Arrays.stream(pjp.getArgs())
                .map(String::valueOf)
                .filter(str -> !StringUtils.isNullOrEmpty(str))
                .collect(Collectors.toList());
        sw.start();
        try {
            return pjp.proceed();
        } finally {
            sw.stop();
            var arguments =  argumentMatcher.match(params, args);
            var requestLog = new RequestLogDao();
            requestLog.setArguments(arguments);
            requestLog.setExecutionMillis(sw.getTotalTimeMillis());
            requestLog.setMethodName(methodSignature);
            requestLogService.saveRequestLog(requestLog);
        }
    }
}
