package com.poc.example;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

import com.poc.example.ws.vo.WsOutputPojo;

public class DummyObjectCreator extends AbstractMessageTransformer {

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {

		WsOutputPojo outputPojo = new WsOutputPojo();
		outputPojo.getOutput().setResponse(" XML output based on input is ");
		message.setPayload(outputPojo);
		return message;
	}

}
