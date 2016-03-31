/**
 * 新启信息技术有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.common.utils;

import java.io.IOException;
import java.io.StringWriter;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.xqsight.common.utils.dom4j.OutputFormat;
import com.xqsight.common.utils.dom4j.XmlWriter;

/**
 * 为Dom4j而生的XML实用类
 * @author xqsight-jerry
 * @version XmlUtils.java, v 0.1 2015年6月29日 上午8:42:46 xqsight-jerry
 */
public class XmlUtils {
    
    public XmlUtils() {
        
    }
    
    private final static Logger logger = LoggerFactory.getLogger(XmlUtils.class);
    
    private final static OutputFormat ONE_LINE_FORMAT_UTF8 = OutputFormat.createCompactFormat();
    private final static OutputFormat ONE_LINE_FORMAT_GBK = OutputFormat.createCompactFormat();
    private final static OutputFormat ONE_LINE_WiTHOUT_DECLARATION_UTF8 = OutputFormat.createCompactFormat();
    private final static OutputFormat ONE_LINE_WiTHOUT_DECLARATION_GBK = OutputFormat.createCompactFormat();
    
    static {
        ONE_LINE_FORMAT_UTF8.setExpandEmptyElements(true);
        ONE_LINE_FORMAT_UTF8.setNewLineAfterDeclaration(false);
        ONE_LINE_FORMAT_UTF8.setEncoding("UTF-8");
        ONE_LINE_FORMAT_UTF8.setStandalone(false);
        
        ONE_LINE_FORMAT_GBK.setExpandEmptyElements(true);
        ONE_LINE_FORMAT_GBK.setNewLineAfterDeclaration(false);
        ONE_LINE_FORMAT_GBK.setEncoding("GBK");
        ONE_LINE_FORMAT_GBK.setStandalone(false);
        
        ONE_LINE_WiTHOUT_DECLARATION_UTF8.setExpandEmptyElements(true);
        ONE_LINE_WiTHOUT_DECLARATION_UTF8.setSuppressDeclaration(true);
        ONE_LINE_WiTHOUT_DECLARATION_UTF8.setEncoding("UTF-8");
        
        ONE_LINE_WiTHOUT_DECLARATION_GBK.setExpandEmptyElements(true);
        ONE_LINE_WiTHOUT_DECLARATION_GBK.setSuppressDeclaration(true);
        ONE_LINE_WiTHOUT_DECLARATION_GBK.setEncoding("GBK");
    }
    
    /**
     * 从classpath中路径中读取XML文件生成{@linkplain org.dom4j.Document Document}
     * @param resourcePath
     * @return
     */
    public static Document getDocumentFromResourceXml(String resourcePath) {
        Document doc = null;
        SAXReader saxReader = new SAXReader();
        try {
            doc = saxReader.read(XmlUtils.class.getResourceAsStream(resourcePath));
        } catch (Exception e) {
            logger.error("读取Xml文件失败", e);
        }
        return doc;
    }

    /**
     * 格式化{@linkplain org.dom4j.Document Document}
     * 生成单行的XML字符串，且带{@code Declaration}元素和{@code isExpandEmptyElements}为{@code true}
     * @param doc
     * @return
     */
    public static String asOneLineXml(Document doc) {
        return asOneLineXml(doc, "UTF-8", false);
    }
    
    /**
     * 格式化{@linkplain org.dom4j.Document Document}
     * 生成单行的XML字符串，且带{@code Declaration}元素和{@code isExpandEmptyElements}为{@code true}
     * @param doc
     * @param encoding
     * @return
     */
    public static String asOneLineXml(Document doc, String encoding) {
        return asOneLineXml(doc, "UTF-8", false);
    }
    
    /**
     * 格式化{@linkplain org.dom4j.Document Document}
     * 生成单行的XML字符串，且由{@code needDeclaration}控制是否带{@code Declaration}元素和{@code isExpandEmptyElements}为{@code true}
     * @param doc
     * @param encoding
     * @param suppressDeclaration
     * @return
     */
    public static String asOneLineXml(Document doc, String encoding, boolean suppressDeclaration) {
        Assert.notNull(doc);
        Assert.notNull(encoding);
        XmlWriter xmlWriter = null;
        try (StringWriter strWriter = new StringWriter()) {
            switch (encoding) {
                case "UTF8":
                case "UTF-8":
                    xmlWriter = new XmlWriter(strWriter, suppressDeclaration ? ONE_LINE_WiTHOUT_DECLARATION_UTF8 : ONE_LINE_FORMAT_UTF8);
                    break;
                case "GBK":
                    xmlWriter = new XmlWriter(strWriter, suppressDeclaration ? ONE_LINE_WiTHOUT_DECLARATION_GBK : ONE_LINE_FORMAT_GBK);
                    break;
                default:
                    OutputFormat format = OutputFormat.createCompactFormat();
                    format.setExpandEmptyElements(true);
                    format.setNewLineAfterDeclaration(false);
                    format.setEncoding(encoding);
                    format.setSuppressDeclaration(suppressDeclaration);
                    xmlWriter = new XmlWriter(strWriter, format);
                    break;
            }
            
            xmlWriter.write(doc);
            xmlWriter.flush();
            return strWriter.toString();
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            if(xmlWriter != null){
                try {
                    xmlWriter.close();
                } catch (IOException e) {
                    logger.error("", e);
                }
            }
        }
        return null;
    }
    
    public static String asOneLineXml(Document doc, Config config) {
        Assert.notNull(doc);
        Assert.notNull(config.getEncoding());
        XmlWriter xmlWriter = null;
        try (StringWriter strWriter = new StringWriter()) {
            OutputFormat format = OutputFormat.createCompactFormat();
            format.setExpandEmptyElements(config.isExpandEmptyElements());
            format.setNewLineAfterDeclaration(config.isNewLineAfterDeclaration());
            format.setEncoding(config.getEncoding());
            format.setSuppressDeclaration(config.suppressDeclaration);
            format.setStandalone(config.standalone);
            format.setRenderStandalone(config.isRenderStandalone());
            xmlWriter = new XmlWriter(strWriter, format);
            xmlWriter.write(doc);
            xmlWriter.flush();
            return strWriter.toString();
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            if(xmlWriter != null){
                try {
                    xmlWriter.close();
                } catch (IOException e) {
                    logger.error("", e);
                }
            }
        }
        return null;
    }
    
    /**
     * 通过XML字符串生成对象
     * @param xml
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromXml(String xml, Class<T> clazz){
        XStream xstream = new XStream(new DomDriver());
        xstream.processAnnotations(clazz);
        return (T) xstream.fromXML(xml);
    }
    
    public static class Config {
        private boolean expandEmptyElements = false;
        private boolean newLineAfterDeclaration = true;
        private String encoding = "UTF-8";
        private boolean suppressDeclaration = false;
        private boolean standalone = false;
        private boolean renderStandalone = false;
        /**
         * Getter method for property <tt>expandEmptyElements</tt>.
         * 
         * @return property value of expandEmptyElements
         */
        public boolean isExpandEmptyElements() {
            return expandEmptyElements;
        }
        /**
         * Setter method for property <tt>expandEmptyElements</tt>.
         * 
         * @param expandEmptyElements value to be assigned to property expandEmptyElements
         */
        public void setExpandEmptyElements(boolean expandEmptyElements) {
            this.expandEmptyElements = expandEmptyElements;
        }
        /**
         * Getter method for property <tt>newLineAfterDeclaration</tt>.
         * 
         * @return property value of newLineAfterDeclaration
         */
        public boolean isNewLineAfterDeclaration() {
            return newLineAfterDeclaration;
        }
        /**
         * Setter method for property <tt>newLineAfterDeclaration</tt>.
         * 
         * @param newLineAfterDeclaration value to be assigned to property newLineAfterDeclaration
         */
        public void setNewLineAfterDeclaration(boolean newLineAfterDeclaration) {
            this.newLineAfterDeclaration = newLineAfterDeclaration;
        }
        /**
         * Getter method for property <tt>encoding</tt>.
         * 
         * @return property value of encoding
         */
        public String getEncoding() {
            return encoding;
        }
        /**
         * Setter method for property <tt>encoding</tt>.
         * 
         * @param encoding value to be assigned to property encoding
         */
        public void setEncoding(String encoding) {
            this.encoding = encoding;
        }
        /**
         * Getter method for property <tt>suppressDeclaration</tt>.
         * 
         * @return property value of suppressDeclaration
         */
        public boolean isSuppressDeclaration() {
            return suppressDeclaration;
        }
        /**
         * Setter method for property <tt>suppressDeclaration</tt>.
         * 
         * @param suppressDeclaration value to be assigned to property suppressDeclaration
         */
        public void setSuppressDeclaration(boolean suppressDeclaration) {
            this.suppressDeclaration = suppressDeclaration;
        }
        /**
         * Getter method for property <tt>standalone</tt>.
         * 
         * @return property value of standalone
         */
        public boolean isStandalone() {
            return standalone;
        }
        /**
         * Setter method for property <tt>standalone</tt>.
         * 
         * @param standalone value to be assigned to property standalone
         */
        public void setStandalone(boolean standalone) {
            this.standalone = standalone;
        }
        /**
         * Getter method for property <tt>renderStandalone</tt>.
         * 
         * @return property value of renderStandalone
         */
        public boolean isRenderStandalone() {
            return renderStandalone;
        }
        /**
         * Setter method for property <tt>renderStandalone</tt>.
         * 
         * @param renderStandalone value to be assigned to property renderStandalone
         */
        public void setRenderStandalone(boolean renderStandalone) {
            this.renderStandalone = renderStandalone;
        }
    }
}
