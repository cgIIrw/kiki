package kotlincode.com.cgrw.ioc.beans.factory

import kotlincode.com.cgrw.ioc.beans.BeanDef
import java.lang.IllegalArgumentException
import java.util.concurrent.ConcurrentHashMap

/**
 * @author: cgrw
 **/
abstract class AbsBeanFac : BeanFac {

    // 定义一个bean包的容器
    val beanDefMap = ConcurrentHashMap<String, BeanDef>()

    // 定义一个bean包名的容器
    val beanDefNames = ArrayList<String>()


    override fun getBean(name: String): Any {
        var beanDef = beanDefMap[name]

        // Elvis操作符的作用是为null则返回":"右边的表达式
        beanDef ?: throw IllegalArgumentException("该bean没有定义")

        var bean = beanDef.bean
        return bean?: doCreateBean(beanDef)!!
    }

    // 在工厂中注册bean包
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

    // 抽象方法，用来依赖注入创建bean对象
    abstract fun doCreateBean(beanDef: BeanDef): Any?
}