/**
 * Company:新启信息技术有限责任公司
 * Copyright: Copyright (c) 2011 
 */
package com.xqsight.common.utils.dom4j;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import org.dom4j.io.XMLWriter;

/**
 * 
  * @Description: this is use for 
  * @author xqsight-jerry
  * @date 2016年1月8日 下午7:49:36
 */
public class XmlWriter extends XMLWriter {

    public XmlWriter(OutputStream out, OutputFormat format) throws UnsupportedEncodingException {
        super(out, format);
    }

    /**
     * @param strWriter
     * @param format
     */
    public XmlWriter(StringWriter strWriter, OutputFormat format) {
        super(strWriter, format);
    }

    @Override
    protected void writeDeclaration() throws IOException {
        String encoding = getOutputFormat().getEncoding();

        // Only print of declaration is not suppressed
        if (!getOutputFormat().isSuppressDeclaration()) {
            // Assume 1.0 version
            if (encoding.equals("UTF8")) {
                writer.write("<?xml version=\"1.0\"");

                if (!getOutputFormat().isOmitEncoding()) {
                    writer.write(" encoding=\"UTF-8\"");
                }
            } else {
                writer.write("<?xml version=\"1.0\"");

                if (!getOutputFormat().isOmitEncoding()) {
                    writer.write(" encoding=\"" + encoding + "\"");
                }
            }

            if (getOutputFormat() instanceof OutputFormat){
                OutputFormat opf = (OutputFormat) getOutputFormat();
                if(opf.isRenderStandalone()) {
                    writer.write(" standalone=\"" + (opf.isStandalone() ? "yes" : "no") + "\"");
                }
                
            }

            writer.write("?>");

            if (getOutputFormat().isNewLineAfterDeclaration()) {
                println();
            }
        }
    }
}
