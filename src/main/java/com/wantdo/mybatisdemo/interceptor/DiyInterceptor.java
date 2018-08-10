package com.wantdo.mybatisdemo.interceptor;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author king
 * @version 2018-08-10 2:19 PM
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        //@Signature(type = ParameterHandler.class, method = "query", args = {}),
        //@Signature(type = StatementHandler.class, method = "query", args = {}),
        //@Signature(type = ResultSetHandler.class, method = "query", args = {}),
})
@Component
public class DiyInterceptor implements Interceptor {
    
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //System.out.println();
        //Arrays.stream(invocation.getArgs()).forEach(System.out::println);
        //System.out.println();
        
        Object object = invocation.getArgs()[1];
        if (object instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> entity = (Map<String, Object>) object;
            AtomicInteger atomicInteger = new AtomicInteger();
            //int[] i={0};
            new ArrayList<>(entity.entrySet()).stream().filter(e -> e.getKey().startsWith("param"))
                    .forEach(e -> {
                        //i[0]++;
                        
                        Object value = e.getValue();
                        if (null == value) {
                            return;
                        }
                        if (value instanceof Collection) {
                            Collection collection = (Collection) value;
                            atomicInteger.addAndGet(collection.size());
                        } else if (value instanceof String) {
                            String str = (String) value;
                            if (!str.isEmpty()) {
                                atomicInteger.addAndGet(1);
                            }
                        } else {
                            atomicInteger.addAndGet(1);
                        }
                        //System.out.println(e.getKey() + "\t" + e.getValue());
                    });
            if (atomicInteger.get() == 0) {
                return new ArrayList<>();
            }
        }
        
        return invocation.proceed();
    }
    
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
    
    @Override
    public void setProperties(Properties properties) {
    
    }
}
