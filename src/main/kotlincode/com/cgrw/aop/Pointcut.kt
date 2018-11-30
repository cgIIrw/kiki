package kotlincode.com.cgrw.aop

/**
 * @author: cgrw
 **/
// 切点接口，表示在哪里切入
interface Pointcut {
    fun getClassFilter(): ClassFilter
    fun getMethodMatcher(): MethodMatcher
}