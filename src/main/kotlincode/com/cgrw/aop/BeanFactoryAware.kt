package kotlincode.com.cgrw.aop

import kotlincode.com.cgrw.ioc.beans.factory.BeanFac

/**
 * @author: cgrw
 **/
interface BeanFactoryAware {
    fun setBeanFactory(beanFac: BeanFac)
}