package kotlincode.com.cgrw.ioc.factory

import kotlincode.com.cgrw.ioc.BeanDef

/**
 * @author: cgrw
 **/
class AutowireCapableBeanFac: AbsBeanFac() {
    override fun doCreateBean(beanDef: BeanDef): Any?{

        try {

            var bean = beanDef.beanClass?.newInstance()
            return bean
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
        return null
    }
}