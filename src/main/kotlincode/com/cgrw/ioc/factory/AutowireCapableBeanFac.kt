package kotlincode.com.cgrw.ioc.factory

import kotlincode.com.cgrw.ioc.BeanDef

/**
 * @author: cgrw
 **/
class AutowireCapableBeanFac: AbsBeanFac() {
    override fun doCreateBean(beanDef: BeanDef): Any?{

        try {

            var bean = createBeanInstance(beanDef)
            return bean
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
        return null
    }

    fun createBeanInstance(beanDef: BeanDef) = beanDef.beanClass?.newInstance()

    fun applyPropertyValues(bean: Any, beanDef: BeanDef) {
        for (p in beanDef.propertyValues!!.propertyValueList) {
            var declaredField = bean.javaClass.getDeclaredField(p.name)
            declaredField.isAccessible = true
            declaredField.set(bean, p.value)
        }
    }
}