package kotlincode.com.cgrw.ioc.beans.factory

import kotlincode.com.cgrw.ioc.beans.BeanDef
import kotlincode.com.cgrw.ioc.beans.BeanPostProcessor
import java.lang.IllegalArgumentException
import java.util.concurrent.ConcurrentHashMap

/**
 * @author: cgrw
 **/
abstract class AbsBeanFac : BeanFac {

    // 定义一个bean包的容器
    val beanDefMap = ConcurrentHashMap<String, BeanDef>()

    // 定义一个添加bean包id的容器
    val beanDefIds = ArrayList<String>()

    private var beanPostProcessors = ArrayList<BeanPostProcessor>()



    override fun getBean(id: String): Any {
        var beanDef = beanDefMap[id]

        // Elvis操作符的作用是为null则返回":"右边的表达式
        beanDef ?: throw IllegalArgumentException("该bean没有定义")

        var bean = beanDef.bean
        return bean ?: doCreateBean(beanDef)!!
    }

    // 在工厂中注册bean包
    fun registerBeanDef(id: String, beanDef: BeanDef) {
        beanDefMap.put(id, beanDef)
        beanDefIds.add(id)
    }

    // 通过beanId获得创建的bean对象
    fun preInstantiateSingletons() {
        for (i in this.beanDefIds.iterator()) {
            var beanId = i
            getBean(beanId)
        }
    }

    // 创建未添加属性值的bean对象
    fun createBeanInstance(beanDef: BeanDef) = beanDef.beanClass!!.newInstance()

    // 依赖注入产生bean
    fun doCreateBean(beanDef: BeanDef): Any? {

        var bean = createBeanInstance(beanDef)
        beanDef.bean = bean
        applyPropertyValues(bean, beanDef)
        return bean
    }

    // 装配的抽象方法，留给装配工厂去实现
    protected abstract fun applyPropertyValues(bean: Any, beanDef: BeanDef)
}