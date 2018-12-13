package kotlincode.com.cgrw.aop

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * @author: cgrw
 **/
class JdkDynamicAopProxy(var advised: AdvisedSupport) : AopProxy, InvocationHandler{

    override fun getProxy(): Any? {
        return Proxy.newProxyInstance(this.javaClass.classLoader, advised.targetSource!!.targetClass, this)

    }

    override fun invoke(proxy: Any?, method: Method?, args: Array<Any>?): Any? {
        var methodInterceptor = advised.methodInterceptor
        var methodMatcher = advised.methodMatcher

        if (methodMatcher != null && methodMatcher.matches(method!!, advised.targetSource!!.target!!::class.java))
            // 匹配时返回拦截器的处理方法
            return methodInterceptor?.invoke(ReflectiveMethodInvocation(advised.targetSource!!.target, method, args))
        else {
            // 不匹配时返回目标对象的原本方法
            return method?.invoke(advised.targetSource!!.target, args)
        }
    }
}