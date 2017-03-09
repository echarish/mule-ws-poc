package com.poc.example.ws.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "wsoutput")
public class WsOutputPojo {

	Output output=new Output();

	public Output getOutput() {
		return output;
	}

	public void setOutput(Output output) {
		this.output = output;
	}

	@XmlRootElement(name = "output")
	public class Output {

		String response;

		public String getResponse() {
			return response;
		}

		public void setResponse(String response) {
			this.response = response;
		}

	}

}
