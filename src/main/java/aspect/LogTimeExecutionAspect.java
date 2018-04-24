package aspect;

import lombok.extern.log4j.Log4j;
import org.apache.commons.lang.time.StopWatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Log4j
public class LogTimeExecutionAspect {
    private static final String LOG_MESSAGE_FORMAT = "%s.%s execution time: %d ms";
    private static final Log LOG = LogFactory.getLog(LogTimeExecutionAspect.class);

    @Pointcut("execution(@aspect.annotation.LogTimeExecution * *(..))")
    public void timePointCut() {}

    @Around("timePointCut()")
    public Object logTimeMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object retVal = joinPoint.proceed();

        stopWatch.stop();

        logExecutionTime(joinPoint, stopWatch);
        return retVal;
    }

    private void logExecutionTime(ProceedingJoinPoint joinPoint, StopWatch stopWatch) {
        String logMessage = String.format(LOG_MESSAGE_FORMAT, joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(), stopWatch.getTime());
        log.error(logMessage);
    }

}
