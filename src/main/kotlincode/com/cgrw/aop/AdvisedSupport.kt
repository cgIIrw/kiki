package kotlincode.com.cgrw.aop

import org.aopalliance.intercept.MethodInterceptor

/**
 * @author: cgrw
 **/
// 通知支持，包含了方法匹配，拦截器对象以及目标对象包
class AdvisedSupport {
    // 被代理的目标对象包
    var targetSource: TargetSource? = null

    // 拦截器对象
    var methodInterceptor: MethodInterceptor? = null

    // 判断方法匹配的接口引用
    var methodMatcher: MethodMatcher? = null
}