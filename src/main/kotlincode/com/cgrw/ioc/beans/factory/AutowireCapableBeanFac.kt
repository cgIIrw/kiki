package kotlincode.com.cgrw.ioc.beans.factory

import kotlincode.com.cgrw.aop.BeanFactoryAware
import kotlincode.com.cgrw.ioc.beans.BeanDef
import kotlincode.com.cgrw.ioc.beans.BeanReference

/**
 * @author: cgrw
 **/
// 自动装配内容工厂
class AutowireCapableBeanFac : AbsBeanFac() {

    // 通过反射为创建的bean对象添加属性
    override fun applyPropertyValues(bean: Any, beanDef: BeanDef) {
        if (bean is BeanFactoryAware)
            (bean as BeanFactoryAware).setBeanFactory(this)

        for (p in beanDef.propertyValues.propertyValueList) {
            var declaredField = bean.javaClass.getDeclaredField(p.name)
            declaredField.isAccessible = true
            var value = p.value
            if (value is BeanReference) {
                var beanReference = value
                value = doCreateBean(this.beanDefMap[beanReference.id]!!)!!
            }
            declaredField.set(bean, value)
        }
    }
}