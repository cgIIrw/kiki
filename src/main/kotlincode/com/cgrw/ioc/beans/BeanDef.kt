package kotlincode.com.cgrw.ioc.beans

/**
 * @author: cgrw
 **/
open class BeanDef {
    var bean: Any? = null

    var beanClass: Class<*>? = null

    var propertyValues: PropertyValues = PropertyValues()

    var beanClassName :String? = null
    set(value) {
        field = value
        try {
            this.beanClass = Class.forName(value)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
    }
}