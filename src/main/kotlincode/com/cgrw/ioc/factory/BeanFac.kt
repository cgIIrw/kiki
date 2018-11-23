package kotlincode.com.cgrw.ioc.factory

import kotlincode.com.cgrw.ioc.BeanDef
import java.util.concurrent.ConcurrentHashMap

/**
 * @author: cgrw
 **/
open interface BeanFac {

    fun getBean(name: String): BeanDef?

    fun registerBeanDef(name: String, beanDef: BeanDef)
}