package com.jieyi.util;

import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 自定义MAP类
 *
 * @author user
 */
public class Mapx extends HashMap<String, Object> {

    private static final long serialVersionUID = -3679879603408358306L;

    private Document document = null;

    private boolean success;

    @Override
    public String toString() {
        String[] arStr = new String[0];
        arStr = this.keySet().toArray(arStr);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arStr.length; i++) {
            if (i > 0)
                sb.append(",");
            sb.append(arStr[i] + " = " + this.get(arStr[i]));
        }
        return sb.toString();
    }

    public String getString(String key) {
        if (this.get(key) == null)
            return "";
        else
            return String.valueOf(this.get(key));
    }

    public int getInt(String key) {
        try {
            return Integer.parseInt(this.getString(key));
        } catch (Exception ex) {
            return 0;
        }
    }

    public short getShort(String key) {
        try {
            return Short.parseShort(this.getString(key));
        } catch (Exception ex) {
            return 0;
        }
    }

    public double getDouble(String key) {
        try {
            return Double.parseDouble(this.getString(key));
        } catch (Exception ex) {
            return 0;
        }

    }

    /**
     * 从XML中获取数据,并转换为MAPX,有重名的节点会覆盖
     *
     * @param xml
     * @return
     */

    public static Mapx fromXml(String xml) {
        Mapx map = new Mapx();
        try {
            map.document = DocumentHelper.parseText(xml);
            Element rootEle = map.document.getRootElement();
            searchXmlNode(rootEle, map);
            map.success = true;
        } catch (Exception ex) {
            map.success = false;
            ex.printStackTrace();
        }
        return map;
    }

    /**
     * 遍历XML节点
     *
     * @param n
     * @param map
     */

    private static void searchXmlNode(Element n, Mapx map) {
        List<Element> childE = n.elements();
        for (Element child : childE) {
            if (child.elements().size() > 0) {
                searchXmlNode(child, map);
            } else {
                map.put(child.getName(), child.getText());
            }
        }
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


}
