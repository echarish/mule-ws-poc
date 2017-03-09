package com.poc.example.ws;

import javax.jws.WebService;

import org.mule.DefaultMuleEvent;
import org.mule.DefaultMuleMessage;
import org.mule.MessageExchangePattern;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.context.MuleContextAware;
import org.mule.api.processor.MessageProcessor;
import org.mule.construct.Flow;
import org.mule.session.DefaultMuleSession;

import com.poc.example.ws.jaxb.JaxBUtils;
import com.poc.example.ws.jaxb.wsinput.Wsinput;
import com.poc.example.ws.jaxb.wsoutput.Wsoutput;
import com.poc.example.ws.vo.CustomObjectA;
import com.poc.example.ws.vo.CustomObjectB;
import com.poc.example.ws.vo.WsInputPojo;
import com.poc.example.ws.vo.WsOutputPojo;

@WebService(endpointInterface = "com.poc.example.ws.IHelloWorld", serviceName = "HelloWorld")
public class HelloWorldWSImpl implements IHelloWorld, MuleContextAware {

	MuleContext muleContext;

	@Override
	public String sayHi(String inputText) {
		System.out.println("Webservice sayHi executing for input " + inputText);
		return "Hello " + inputText + " ,Welcome to my WS";
	}

	@Override
	public String multiInputCustom(CustomObjectA customObjectA, CustomObjectB customObjectB) {
		System.out.println("Webservice multiInputCustom executing for input " + customObjectA + " " + customObjectB);
		return "You just called MultiInputCustom with " + customObjectA.getCustomPropertyA() + " and "
				+ customObjectB.getCustomPropertyB();
	}

	@Override
	public String xmlInputService(String wsInputXML) {
		System.out.println("Webservice xmlInputService executing for input " + wsInputXML);
		JaxBUtils jaxBUtils = JaxBUtils.getInstance();
		Wsinput wsinput = jaxBUtils.getWSInputObject(wsInputXML);
		Wsoutput wsoutput = new Wsoutput();
		Wsoutput.Output output = new Wsoutput.Output();
		output.setResponse(" XML output based on input is " + wsinput.getInputs().getArgumentA() + " "
				+ wsinput.getInputs().getArgumentB());
		wsoutput.setOutput(output);
		return jaxBUtils.getWSOutputString(wsoutput);
	}

	@Override
	public String xmlInputServiceNoJaxB(String wsInputXML) {
		WsInputPojo wsInputPojo = (WsInputPojo) triggerMuleFlow("input-xml-to-object-convertion", true, wsInputXML,
				new WsInputPojo());
		WsOutputPojo outputPojo = new WsOutputPojo();
		outputPojo.getOutput().setResponse(" XML output based on input is " + wsInputPojo.getInputs().getArgumentA()
				+ " " + wsInputPojo.getInputs().getArgumentB());
		String responseXML = (String) triggerMuleFlow("output-object-to-xml-converstion", true, outputPojo,
				new String());
		// there is a bug in respnse xml <outer-class reference="../.."/> is
		// being added need to check how to remove it.
		return responseXML;
	}

	@SuppressWarnings("deprecation")
	public Object triggerMuleFlow(String flowName, boolean isSubFlow, Object inputData, Object returnClass) {
		try {
			if (!isSubFlow) {
				Flow flow = (Flow) muleContext.getRegistry().lookupFlowConstruct(flowName);
				// Can be implemented in same way as below
			} else {
				MessageProcessor subFlow = muleContext.getRegistry().lookupObject(flowName);
				MuleMessage muleMessage = new DefaultMuleMessage(inputData, muleContext);

				MuleEvent inputEvent = new DefaultMuleEvent(muleMessage, MessageExchangePattern.REQUEST_RESPONSE,
						new DefaultMuleSession());
				MuleEvent result = subFlow.process(inputEvent);
				returnClass = result.getMessage().getPayload();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnClass;
	}

	@Override
	public void setMuleContext(MuleContext context) {
		muleContext = context;
	}

	@Override
	public String webserviceA(String inputText) {
		System.out.println("Webservice A executing for input " + inputText);
		return "Hello " + inputText + " Welcome to my WS A";
	}

	@Override
	public String webserviceB(String inputText) {
		System.out.println("Webservice B executing for input " + inputText);
		return "Hello " + inputText + " Welcome to my WS B";
	}

}
