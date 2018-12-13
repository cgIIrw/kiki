package kotlincode.com.cgrw.ioc.context

import kotlincode.com.cgrw.ioc.beans.BeanPostProcessor
import kotlincode.com.cgrw.ioc.beans.factory.AbsBeanFac

/**
 * @author: cgrw
 **/
abstract class AbstractApplicationContext(var beanFac: AbsBeanFac) : ApplicationContext {
    fun refresh() {
        loadBeanDefinitions(beanFac)
        registerBeanPostProcessors(beanFac)
        onRefresh()
    }

    // 相当于对工厂的getBean方法的代理，便于扩展
    override fun getBean(name: String): Any? {
        return beanFac.getBean(name)
    }


    // 本方法的目的是将自动装载类的部分抽离给下层实现，比如采用Xml、JavaConfig等等
    protected abstract fun loadBeanDefinitions(beanFac: AbsBeanFac)

    // 将BeanPostProcessor类型的bean注册到工厂内的BeanPostProcessor容器中
    fun registerBeanPostProcessors(beanFac: AbsBeanFac) {
        var beanPostProcessors = beanFac.getBeansForType(BeanPostProcessor::class.java)
        for (beanPostProcessor in beanPostProcessors) {
            beanFac.addBeanPostProcessor(beanPostProcessor as BeanPostProcessor)
        }
    }

    // 通过beanId获得创建的bean对象
    protected fun onRefresh() {
        beanFac.preInstantiateSingletons()
    }
}