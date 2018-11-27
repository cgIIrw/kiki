package kotlincode.com.cgrw.ioc.factory

import kotlincode.com.cgrw.ioc.BeanDef
import kotlincode.com.cgrw.ioc.BeanReference

/**
 * @author: cgrw
 **/
class AutowireCapableBeanFac : AbsBeanFac() {
    override fun doCreateBean(beanDef: BeanDef): Any? {

//        try {

        var bean = createBeanInstance(beanDef)
        beanDef.bean = bean
        applyPropertyValues(bean, beanDef)
        return bean
//            return bean
//        } catch (e: InstantiationException) {
//            e.printStackTrace()
//        } catch (e: IllegalAccessException) {
//            e.printStackTrace()
//        }
//        return null
    }

    fun createBeanInstance(beanDef: BeanDef) = beanDef.beanClass!!.newInstance()

    fun applyPropertyValues(bean: Any, beanDef: BeanDef) {
        for (p in beanDef.propertyValues.propertyValueList) {
            var declaredField = bean.javaClass.getDeclaredField(p.name)
            declaredField.isAccessible = true
            var value = p.value
            if (value is BeanReference) {
                var beanReference = value
                value = getBean(beanReference.name)
            }
            declaredField.set(bean, value)
        }
    }
}