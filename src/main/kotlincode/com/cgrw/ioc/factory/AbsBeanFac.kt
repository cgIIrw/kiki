package kotlincode.com.cgrw.ioc.factory

import kotlincode.com.cgrw.ioc.BeanDef
import java.util.concurrent.ConcurrentHashMap

/**
 * @author: cgrw
 **/
abstract class AbsBeanFac : BeanFac{

    private val beanDefMap = ConcurrentHashMap<String, BeanDef>()

    override fun getBean(name: String) = beanDefMap[name]

    override fun registerBeanDef(name: String, beanDef: BeanDef) {
        var bean = doCreateBean(beanDef)
        beanDef.bean = bean
        beanDefMap.put(name, beanDef)
    }

    abstract fun doCreateBean(beanDef: BeanDef): Any?
}