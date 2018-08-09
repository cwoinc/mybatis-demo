package com.wantdo.mybatisdemo.interceptor;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 数据库操作性能拦截器,记录耗时
 * <p>
 * #@Intercepts定义Signature数组,因此可以拦截多个,但是只能拦截类型为：
 *
 * @author king
 * @see Executor
 * @see ParameterHandler
 * @see StatementHandler
 * @see ResultSetHandler
 */
@Intercepts(value = {
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
@Component
public class TimerInterceptor implements Interceptor {
    
    /**
     * 实现拦截的地方
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //InterceptorChain chain = new InterceptorChain();
        //chain.addInterceptor(new TestInterceptor());
        
        Object target = invocation.getTarget();
        Object result = null;
        if (target instanceof Executor) {
            long start = System.currentTimeMillis();
            Method method = invocation.getMethod();
            // 执行方法
            result = invocation.proceed();
            long end = System.currentTimeMillis();
            System.out.println("[TimerInterceptor] execute [" + method.getName() + "] cost [" + (end - start) + "] ms");
        }
        return result;
    }
    
    /**
     * Plugin.wrap生成拦截代理对象
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
    
    @Override
    public void setProperties(Properties properties) {
    
    }
    
}