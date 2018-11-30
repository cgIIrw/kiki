package kotlincode.com.cgrw.aop

import java.lang.reflect.Method

/**
 * @author: cgrw
 **/
// 判断方法匹配的接口，提供boolean方法
interface MethodMatcher {
    fun matches(method: Method, targetClass: Class<*>): Boolean
}