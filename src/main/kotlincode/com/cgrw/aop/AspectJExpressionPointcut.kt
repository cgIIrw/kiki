package kotlincode.com.cgrw.aop

import java.lang.reflect.Method

/**
 * @author: cgrw
 **/
class AspectJExpressionPointcut : Pointcut, ClassFilter, MethodMatcher {

    var expression: String? = null



    override fun getClassFilter(): ClassFilter {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMethodMatcher(): MethodMatcher {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun matches(targetClass: Class<*>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun matches(method: Method, targetClass: Class<*>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}