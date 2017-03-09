package com.poc.example.ws;

import javax.jws.WebService;

import com.poc.example.ws.vo.CustomObjectA;
import com.poc.example.ws.vo.CustomObjectB;

@WebService
public interface IHelloWorld {

	String sayHi(String text);

	String multiInputCustom(CustomObjectA customObjectA, CustomObjectB customObjectB);
	
	String xmlInputService(String wsInputXML);
	
	String xmlInputServiceNoJaxB(String wsInputXML);
	
	String webserviceA(String text);
	
	String webserviceB(String text);
}
