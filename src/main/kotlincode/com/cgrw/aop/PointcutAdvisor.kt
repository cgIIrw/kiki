package kotlincode.com.cgrw.aop

/**
 * @author: cgrw
 **/
// 切面接口，提供切点和advice的获取方法
interface PointcutAdvisor: Advisor {
    fun getPointcut(): Pointcut
}


