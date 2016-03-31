/**
 * Copyright: Copyright (c) 2011 
 * Company:新启信息技术有限责任公司
 */
package com.xqsight.common.utils.dom4j;

/**
 * 
  * @Description: this is use for 
  * @author xqsight-jerry
  * @date 2016年1月8日 下午7:48:27
 */
public class OutputFormat extends org.dom4j.io.OutputFormat {
    
    private boolean standalone;
    
    private boolean renderStandalone;
    
    /**
     * Returns true if the document type is standalone.
     * The default is false.
     */
    public boolean isStandalone()
    {
        return standalone;
    }


    /**
     * Sets document DTD standalone. The public and system
     * identifiers must be null for the document to be
     * serialized as standalone.
     *
     * @param standalone True if document DTD is standalone
     */
    public void setStandalone(boolean standalone )
    {
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


    public static OutputFormat createCompactFormat() {
        OutputFormat format = new OutputFormat();
        format.setIndent(false);
        format.setNewlines(false);
        format.setTrimText(true);

        return format;
    }

}
