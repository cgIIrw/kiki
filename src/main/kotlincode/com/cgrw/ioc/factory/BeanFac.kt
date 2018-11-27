package kotlincode.com.cgrw.ioc.factory

import kotlincode.com.cgrw.ioc.BeanDef

/**
 * @author: cgrw
 **/
open interface BeanFac {

    fun getBean(name: String): Any?

    fun registerBeanDef(name: String, beanDef: BeanDef)
}