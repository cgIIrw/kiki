package kotlincode.com.cgrw.aop

import net.sf.cglib.proxy.Enhancer
import net.sf.cglib.proxy.MethodInterceptor
import net.sf.cglib.proxy.MethodProxy
import java.lang.reflect.Method

/**
 * @author: cgrw
 **/

class CglibAopProxy(var advised: AdvisedSupport) : MethodInterceptor, AopProxy {

    var targetObject = advised.targetSource!!.target

    override fun getProxy(): Any? {
        return createProxyInstance()
    }

    fun createProxyInstance(): Any? {
        var enhancer = Enhancer()
        enhancer.setSuperclass(this.targetObject!!::class.java)
        enhancer.setCallback(this)
        return enhancer.create()
    }

    override fun intercept(p0: Any?, p1: Method?, p2: Array<Any>?, p3: MethodProxy?): Any? {
        var methodInterceptor = advised.methodInterceptor
        var methodMatcher = advised.methodMatcher

        if (methodMatcher != null && methodMatcher.matches(p1!!, advised.targetSource!!.target!!::class.java))
        // 匹配时返回拦截器的处理方法
            return methodInterceptor?.invoke(ReflectiveMethodInvocation(advised.targetSource!!.target, p1, p2))
        else {
            // 不匹配时返回目标对象的原本方法
            return p1?.invoke(advised.targetSource!!.target, p2)
        }
    }
}