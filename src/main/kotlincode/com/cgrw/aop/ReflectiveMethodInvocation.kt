package kotlincode.com.cgrw.aop

import org.aopalliance.intercept.MethodInvocation
import java.lang.reflect.AccessibleObject
import java.lang.reflect.Method

/**
 * @author: cgrw
 **/
// 如其名，反射方法调用类
class ReflectiveMethodInvocation(var target: Any?, @JvmField var method: Method?, var args: Array<Any>?) : MethodInvocation {

    override fun getMethod(): Method? {
        return method
    }

    override fun getArguments(): Array<Any>? {
        return args
    }

    override fun proceed(): Any? {
        return method?.let { it.invoke(target, args) }
    }

    override fun getStaticPart(): AccessibleObject? {
        return method
    }

    override fun getThis(): Any? {
        return target
    }
}