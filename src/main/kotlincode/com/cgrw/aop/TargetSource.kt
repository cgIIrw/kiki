package kotlincode.com.cgrw.aop

/**
 * @author: cgrw
 **/
// 被代理的目标对象包
class TargetSource {

    // 被代理的目标对象的Class对象
    var targetClass: Class<*>? = null
    private set

    // 被代理的目标对象
    var target: Any? = null
    private set
}