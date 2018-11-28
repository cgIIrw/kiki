package kotlincode.com.cgrw.ioc.context

import kotlincode.com.cgrw.ioc.beans.factory.AbsBeanFac

/**
 * @author: cgrw
 **/
abstract class AbstractApplicationContext(var beanFac: AbsBeanFac) : ApplicationContext {
    open fun refresh() {}

    // 相当于对工厂的getBean方法的代理，便于扩展
    override fun getBean(name: String): Any? {
        return beanFac.getBean(name)
    }
}