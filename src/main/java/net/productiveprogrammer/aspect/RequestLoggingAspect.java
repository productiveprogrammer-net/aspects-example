package net.productiveprogrammer.aspect;

import net.productiveprogrammer.logger.RequestLogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
public class RequestLoggingAspect {

    private static final String MSG_FORMAT = "Method %s took %dms with args %s";

    private final RequestLogger requestLogger;

    public RequestLoggingAspect(final RequestLogger requestLogger) {
        this.requestLogger = requestLogger;
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
        var methodName = pjp.getSignature().toLongString();
        var args = Arrays.stream(pjp.getArgs())
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        sw.start();
        try {
            return pjp.proceed();
        } finally {
            sw.stop();
            var msg = String.format(MSG_FORMAT, methodName, sw.getTotalTimeMillis(), args);
            requestLogger.log(msg);
        }
    }
}
