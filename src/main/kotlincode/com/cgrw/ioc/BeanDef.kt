package kotlincode.com.cgrw.ioc

/**
 * @author: cgrw
 **/
open class BeanDef {
    var bean: Any? = null

    var beanClass: Class<*>? = null

    var beanClassName :String? = null
    get() = field
    set(value) {
        field = value
        try {
            this.beanClass = Class.forName(value)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
    }
}