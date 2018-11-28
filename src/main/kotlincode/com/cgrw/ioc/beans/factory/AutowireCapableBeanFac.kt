package kotlincode.com.cgrw.ioc.beans.factory

import kotlincode.com.cgrw.ioc.beans.BeanDef
import kotlincode.com.cgrw.ioc.BeanReference

/**
 * @author: cgrw
 **/
class AutowireCapableBeanFac : AbsBeanFac() {

    // 依赖注入产生bean
    override fun doCreateBean(beanDef: BeanDef): Any? {

        var bean = createBeanInstance(beanDef)
        beanDef.bean = bean
        applyPropertyValues(bean, beanDef)
        return bean
    }

    // 创建未添加属性值的bean对象
    fun createBeanInstance(beanDef: BeanDef) = beanDef.beanClass!!.newInstance()

    // 通过反射为创建的bean对象添加属性
    fun applyPropertyValues(bean: Any, beanDef: BeanDef) {
        for (p in beanDef.propertyValues.propertyValueList) {
            var declaredField = bean.javaClass.getDeclaredField(p.name)
            declaredField.isAccessible = true
            var value = p.value
            if (value is BeanReference) {
                var beanReference = value
                value = getBean(beanReference.id)
            }
            declaredField.set(bean, value)
        }
    }
}