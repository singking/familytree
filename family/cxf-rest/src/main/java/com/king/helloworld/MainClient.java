package com.king.helloworld;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;


/**
 * For the client there is also the alternative approach that gives you more
 * flexibility. Of course like above the logging interceptors are optional but
 * they help a lot when starting:
 *
 * 
 */
public class MainClient {

	public static void main(String[] args) {

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
//		factory.getInInterceptors().add(new LoggingInInterceptor());
//		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(HelloWorld.class);
		factory.setAddress("http://localhost:9000/helloWorld");
		HelloWorld client = (HelloWorld) factory.create();

		String reply = client.sayHi("HI");
		System.out.println("Server said: " + reply);
		System.exit(0);
	}

}
