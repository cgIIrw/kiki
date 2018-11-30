package kotlincode.com.cgrw.aop

/**
 * @author: cgrw
 **/
// 类过滤器接口
interface ClassFilter {
    fun matches(targetClass: Class<*>): Boolean
}