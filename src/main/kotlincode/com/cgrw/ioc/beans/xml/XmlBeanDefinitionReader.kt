package kotlincode.com.cgrw.ioc.beans.xml

import kotlincode.com.cgrw.ioc.beans.AbstractBeanDefinitionReader
import kotlincode.com.cgrw.ioc.beans.BeanDef
import kotlincode.com.cgrw.ioc.beans.BeanReference
import kotlincode.com.cgrw.ioc.beans.PropertyValue
import kotlincode.com.cgrw.ioc.beans.io.ResourceLoader
import org.w3c.dom.Document
import org.w3c.dom.Element
import java.io.InputStream
import javax.xml.parsers.DocumentBuilderFactory

/**
 * @author: cgrw
 **/
class XmlBeanDefinitionReader(resourceLoader: ResourceLoader) : AbstractBeanDefinitionReader(resourceLoader) {

    // 获得inputStream流
    override fun loadBeanDefinitions(location: String) {
        var inputStream = resourceLoader.getResource(location).getInputStream()
        doLoadBeanDefinitions(inputStream)
    }

    // 将流解析为Document
    fun doLoadBeanDefinitions(input: InputStream) {
        var factory = DocumentBuilderFactory.newInstance()
        var docBuilder = factory.newDocumentBuilder()
        var doc  = docBuilder.parse(input)

        registerBeanDefinitions(doc)
        input.close()
    }

    // 获得根对象
    fun registerBeanDefinitions(doc: Document) {
        var root = doc.documentElement
        parseBeanDefinitions(root)
    }

    // 获得根对象下的子node对象
    fun parseBeanDefinitions(root: Element) {
        var nl = root.childNodes

        for (i in 0 until nl.length) {
            var node = nl.item(i)
            if (node is Element) {
                var ele = node
                processBeanDefinition(ele)
            }
        }
    }

    // 从node解析出bean
    fun processBeanDefinition(ele: Element) {
        var id = ele.getAttribute("id")
        var className = ele.getAttribute("class")
        var beanDef = BeanDef()
        processProperty(ele, beanDef)
        beanDef.beanClassName = className
        registry!!.put(id, beanDef)
    }

    // 解析bean的属性
    private fun processProperty(ele: Element, beanDef: BeanDef) {
        var propertyNode = ele.getElementsByTagName("property")
        for (i in 0 until propertyNode.length) {
            var node = propertyNode.item(i)

            if (node is Element) {
                var propertyEle = node as Element
                var name = propertyEle.getAttribute("name")
                var value = propertyEle.getAttribute("value")

                // 如果存在value或者value不为空字符串
                if (value != null && value.isNotEmpty())
                    beanDef.propertyValues.addProper(PropertyValue(name, value))
                // 不存在value时，尝试读取ref
                else {
                    var ref = propertyEle.getAttribute("ref")
                    if (ref == null || ref.isEmpty())
                        throw IllegalArgumentException("配置输入错误")
                    var beanReference = BeanReference(ref)
                    beanDef.propertyValues.addProper(PropertyValue(name, beanReference))
                }
            }
        }
    }
}