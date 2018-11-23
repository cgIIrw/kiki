package kotlincode.com.cgrw.ioc

/**
 * @author: cgrw
 **/
class PropertyValues {
    val propertyValueList = ArrayList<PropertyValue>()

    fun addProper(pv: PropertyValue) {
        this.propertyValueList.add(pv)
    }
}