package kotlincode.com.cgrw.aop

import org.aopalliance.intercept.MethodInterceptor

/**
 * @author: cgrw
 **/
class AdvisedSupport {
    // 被代理的目标对象包
    var targetSource: TargetSource? = null

    // 拦截器对象
    var methodInterceptor: MethodInterceptor? = null
}