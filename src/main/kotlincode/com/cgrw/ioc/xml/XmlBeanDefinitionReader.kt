package kotlincode.com.cgrw.ioc.xml

import kotlincode.com.cgrw.ioc.AbstractBeanDefinitionReader
import kotlincode.com.cgrw.ioc.BeanDef
import kotlincode.com.cgrw.ioc.PropertyValue
import kotlincode.com.cgrw.ioc.io.ResourceLoader
import org.w3c.dom.Document
import org.w3c.dom.Element
import java.io.InputStream
import javax.xml.parsers.DocumentBuilder
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
                var ele = node as Element
                processBeanDefinition(ele)
            }
        }
    }

    // 从node解析出bean
    fun processBeanDefinition(ele: Element) {
        var name = ele.getAttribute("id")
        var className = ele.getAttribute("class")
        var beanDef = BeanDef()
        processProperty(ele, beanDef)
        beanDef.beanClassName = className
        registry!!.put(name, beanDef)
    }

    // 解析bean的属性
    fun processProperty(ele: Element, beanDef: BeanDef) {
        var propertyNode = ele.getElementsByTagName("property")
        for (i in 0 until propertyNode.length) {
            var node = propertyNode.item(i)

            if (node is Element) {
                var propertyEle = node as Element
                var name = propertyEle.getAttribute("name")
                var value = propertyEle.getAttribute("value")
                beanDef.propertyValues.addProper(PropertyValue(name, value))
            }
        }
    }
}