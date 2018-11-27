package kotlincode.com.cgrw.ioc.factory

import kotlincode.com.cgrw.ioc.BeanDef
import java.lang.IllegalArgumentException
import java.util.concurrent.ConcurrentHashMap

/**
 * @author: cgrw
 **/
abstract class AbsBeanFac : BeanFac{

    // 定义一个bean包的容器
    val beanDefMap = ConcurrentHashMap<String, BeanDef>()

    // 定义一个bean包名的容器
    val beanDefNames = ArrayList<String>()


    override fun getBean(name: String): Any {
        var beanDef = beanDefMap[name]
        if (beanDef == null)
            throw IllegalArgumentException("该bean没有定义")


        var bean = beanDef.bean
        if (bean == null) {
            bean = doCreateBean(beanDef)
        }
        return bean!!
    }

    override fun registerBeanDef(name: String, beanDef: BeanDef) {
        beanDefMap.put(name, beanDef)
        beanDefNames.add(name)
    }

    fun preInstantiateSingletons() {
        for (i in this.beanDefNames.iterator()) {
            var beanName = i
            getBean(beanName)
        }
    }

    abstract fun doCreateBean(beanDef: BeanDef): Any?
}