package kotlincode.com.cgrw.aop

/**
 * @author: cgrw
 **/
// 被代理的目标对象包
class TargetSource {

    // 被代理的目标对象的Class对象
    var targetClass: Array<Class<*>>
        private set

    // 被代理的目标对象
    var target: Any? = null
        private set

    constructor(target: Any, vararg targetClass: Class<*>) {
        this.target = target
        this.targetClass = Array(targetClass.size) { i -> targetClass[i] }
    }
}