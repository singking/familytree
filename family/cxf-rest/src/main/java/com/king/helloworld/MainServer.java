package com.king.helloworld;

import javax.xml.ws.Endpoint;
/**
 * http://cxf.apache.org/docs/a-simple-jax-ws-service.html#AsimpleJAX-WSservice-
 * Publishingyourservice
 *
 * You could leave out the ServiceClass. But it is better to use it so the
 * server and the client are created from the same interface. If you instead
 * only use the implementation class subtle problems may occur.
 * 
 * Pointing your browser at http://localhost:9000/helloWorld?wsdl will display
 * the wsdl for this service
 * 
 * 
 */
public class MainServer {

	public static void main(String[] args) {

		// Publishing your service
		System.out.println("Starting Server");
		HelloWorldImpl implementor = new HelloWorldImpl();
		String address = "http://localhost:9000/helloWorld";
		Endpoint.publish(address, implementor);

//		HelloWorldImpl implementor = new HelloWorldImpl();
//		JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
//		svrFactory.setServiceClass(HelloWorld.class);
//		svrFactory.setAddress("http://localhost:9000/helloWorld");
//		svrFactory.setServiceBean(implementor);
//		svrFactory.getInInterceptors().add(new LoggingInInterceptor());
//		svrFactory.getOutInterceptors().add(new LoggingOutInterceptor());
//		svrFactory.create();
	}

}
