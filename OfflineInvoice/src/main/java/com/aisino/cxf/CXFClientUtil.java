package com.aisino.cxf;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.dynamic.DynamicClientFactory;
import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class CXFClientUtil {

    public static Object getJaxWsProxy(Class<?> serviceClass, String url) {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setAddress(url);
        factory.setServiceClass(serviceClass);
        return factory.create();
    }

    public static Object getClientProxy(Class<?> serviceClass, String url) {
        ClientProxyFactoryBean factory = new ClientProxyFactoryBean();
        factory.setServiceClass(serviceClass);
        factory.setAddress(url);
        return factory.create();
    }

    public static Client getJaxWsDynamicClient(String wsdlUrl) {
        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
        return factory.createClient(wsdlUrl);
    }

    public static Client getDynamicClient(String wsdlUrl) {
        DynamicClientFactory factory = DynamicClientFactory.newInstance();
        return factory.createClient(wsdlUrl);
    }

}
