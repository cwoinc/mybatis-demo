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

import java.util.Properties;

/**
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
public class TestInterceptor implements Interceptor {
    
    /**
     * 实现拦截的地方
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //System.out.println();
        //Arrays.stream(invocation.getArgs()).forEach(System.out::println);
        //System.out.println();
        return invocation.proceed();
    }
    
    /**
     * Plugin.wrap生成拦截代理对象
     */
    @Override
    public Object plugin(Object target) {
        //System.out.println(target);
        return Plugin.wrap(target, this);
    }
    
    @Override
    public void setProperties(Properties properties) {
        String prop1 = properties.getProperty("prop1");
        String prop2 = properties.getProperty("prop2");
        System.out.println(prop1 + "------" + prop2);
    }
    
}