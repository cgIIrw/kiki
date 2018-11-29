package kotlincode.com.cgrw.aop

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * @author: cgrw
 **/
class JdkDynamicAopProxy(var advised: AdvisedSupport) : AopProxy, InvocationHandler{


    override fun getProxy(): Any? {
        return Proxy.newProxyInstance(this.javaClass.classLoader, arrayOf(advised.targetSource!!.targetClass), this)

    }

    override fun invoke(proxy: Any?, method: Method?, args: Array<Any>?): Any {
        var methodInterceptor = advised.methodInterceptor
        return methodInterceptor!!.invoke(ReflectiveMethodInvocation(advised.targetSource!!.target!!, method!!, args!!))
    }
}