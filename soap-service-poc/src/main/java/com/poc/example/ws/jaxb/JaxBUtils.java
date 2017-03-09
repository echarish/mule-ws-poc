package com.poc.example.ws.jaxb;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import com.poc.example.ws.jaxb.wsinput.Wsinput;
import com.poc.example.ws.jaxb.wsoutput.Wsoutput;

public class JaxBUtils {

	static JAXBContext wsinputJaxbContext = null;

	XMLInputFactory XML_INPUT_FACTORY = XMLInputFactory.newInstance();
	static JaxBUtils instance = null;

	private JaxBUtils() {
	}

	public static JaxBUtils getInstance() {
		if (instance == null) {
			instance = new JaxBUtils();
		}
		return instance;
	}

	public Wsinput getWSInputObject(String wsInputString) {
		Wsinput wsObject = null;
		try {
			if (wsinputJaxbContext == null) {
				wsinputJaxbContext = JAXBContext.newInstance(Wsinput.class);
			}
			Unmarshaller wsUnmarshaller = wsinputJaxbContext.createUnmarshaller();
			InputStream wsInputStream = new ByteArrayInputStream(wsInputString.getBytes(StandardCharsets.UTF_8));
			XMLStreamReader wsStreamReader = XML_INPUT_FACTORY.createXMLStreamReader(wsInputStream);
			wsObject = (Wsinput) wsUnmarshaller.unmarshal(wsStreamReader);

		} catch (IllegalAccessError ilA) {
			ilA.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wsObject;
	}

	public String getWSOutputString(Wsoutput wsoutput) {
		String result = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Wsoutput.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(wsoutput, sw);
			result = sw.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
