package com.poc.example.ws.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "wsinput")
public class WsInputPojo {

	Inputs inputs;

	public Inputs getInputs() {
		return inputs;
	}

	public void setInputs(Inputs inputs) {
		this.inputs = inputs;
	}

	@XmlRootElement(name = "inputs")
	public class Inputs {

		String argumentA;
		String argumentB;

		public String getArgumentA() {
			return argumentA;
		}

		public void setArgumentA(String argumentA) {
			this.argumentA = argumentA;
		}

		public String getArgumentB() {
			return argumentB;
		}

		public void setArgumentB(String argumentB) {
			this.argumentB = argumentB;
		}

	}

}
