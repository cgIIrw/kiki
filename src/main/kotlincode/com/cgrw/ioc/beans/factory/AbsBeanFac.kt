package kotlincode.com.cgrw.ioc.beans.factory

import kotlincode.com.cgrw.aop.AspectJAwareAdvisorAutoProxyCreator
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

    // 定义后置处理器容器
    private var beanPostProcessors = ArrayList<BeanPostProcessor>()

    // bean初始化方法
    protected fun initializeBean(_bean: Any, id: String): Any {
        var bean = _bean
        for (beanPostProcessor in beanPostProcessors) {
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, id)
        }

        // todo

        for (beanPostProcessor in beanPostProcessors) {
            bean = beanPostProcessor.postProcessAfterInitialization(bean, id)
        }
        return bean
    }

    override fun getBean(id: String): Any {
        var beanDef = beanDefMap[id]

        // Elvis操作符的作用是为null则返回":"右边的表达式
        beanDef ?: throw IllegalArgumentException("该bean没有定义")

        var bean = beanDef.bean

        // 添加bean初始化处理
        return bean ?: initializeBean(doCreateBean(beanDef)!!, id)
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

        // 添加判断语句避免重复创建对象
        if (beanDef.bean != null)
            return beanDef.bean

        var bean = createBeanInstance(beanDef)
        applyPropertyValues(bean, beanDef)
        beanDef.bean = bean
        return bean
    }

    // 装配的抽象方法，留给装配工厂去实现
    abstract fun applyPropertyValues(bean: Any, beanDef: BeanDef)

    // 在后置处理器容器中添加后置处理器
    fun addBeanPostProcessor(beanPostProcessor: BeanPostProcessor) {
        this.beanPostProcessors.add(beanPostProcessor)
    }

    // 根据类型返回相同类型bean构成的容器的方法
    fun getBeansForType(type: Class<*>): List<*> {
        var beans = ArrayList<Any>()

        for (beanDefId in beanDefIds) {
            if (type.isAssignableFrom(beanDefMap[beanDefId]!!.beanClass))
                beans.add(doCreateBean(beanDefMap[beanDefId]!!)!!)
        }
        return beans
    }
}