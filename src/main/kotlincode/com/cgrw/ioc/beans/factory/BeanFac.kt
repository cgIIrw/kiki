package kotlincode.com.cgrw.ioc.beans.factory

import kotlincode.com.cgrw.ioc.beans.BeanDef

/**
 * @author: cgrw
 **/
open interface BeanFac {

    fun getBean(id: String): Any?

//    fun registerBeanDef(name: String, beanDef: BeanDef)
}