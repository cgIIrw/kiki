package kotlincode.com.cgrw.ioc.beans.io

/**
 * @author: cgrw
 **/
open class ResourceLoader {
    fun getResource(location: String): Resource {
        var resource = this.javaClass.classLoader.getResource(location)
        return UrlResource(resource)
    }
}