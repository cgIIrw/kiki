package kotlincode.com.cgrw.ioc.io

/**
 * @author: cgrw
 **/
class ResourceLoader {
    fun getResource(location: String): Resource {
        var resource = this.javaClass.classLoader.getResource(location)
        return UrlResource(resource)
    }
}