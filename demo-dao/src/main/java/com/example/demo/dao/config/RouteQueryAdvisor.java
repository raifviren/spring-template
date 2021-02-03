package com.example.demo.dao.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author ankitsingodia
 * <p>
 * Interceptor for the annotations
 */
@Aspect
@Component
@Slf4j
public class RouteQueryAdvisor implements Ordered {

    private int order;

    @Value("99")
    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public int getOrder() {
        return order;
    }

    @Around("@annotation(com.example.demo.dao.annotations.ReadOnly)")
    public Object aroundReadOnly(ProceedingJoinPoint pjp) throws Throwable {
        try {
            log.info(" Current Transaction Type ReadOnly ? " + TransactionSynchronizationManager.isCurrentTransactionReadOnly());
            if(TransactionSynchronizationManager.isActualTransactionActive()){
                log.info(" Current txn is Active : no context switching : continuing with existing txn");
                return pjp.proceed();
            }
//            log.info(" Current DBType " + DbContextHolderImpl.getDbType());
//            DbContextHolderImpl.clearDbType();
//            log.info(" Post Clear DBType " + DbContextHolderImpl.getDbType());
            log.info(" Switching to slave transaction ");
            DataSourceType dataSourceType = DataSourceType.SLAVE;
            DbContextHolderImpl.setDbType(dataSourceType);
            Object res = pjp.proceed();
            log.info(" Current DBType post clearing " + DbContextHolderImpl.getDbType());
            DbContextHolderImpl.clearDbType();
            log.info(" Current DBType after clearing " + DbContextHolderImpl.getDbType());
            return res;
        } finally {
            // restore state
            DbContextHolderImpl.clearDbType();
            log.info(" Current DBType post clearing in finally block " + DbContextHolderImpl.getDbType());
//            DbContextHolderImpl.setDbType(DataSourceType.MASTER);
//            log.info(" Current DBType set by finally block" + DbContextHolderImpl.getDbType());
        }

    }


}
