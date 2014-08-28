package com.example.rest.example.clients;

import java.net.HttpURLConnection;
import java.net.URL;

// XML Parsing
// import javax.xml.parsers.DocumentBuilder;
// import javax.xml.parsers.DocumentBuilderFactory;
// import org.w3c.dom.Document;

/** An example client that grabs an item from a service endpoint. */
public class Client {
	public static String ADDRESS = 
			"http://localhost:8080/rest/messages/";

    public static void main(String[] args) {
        try {
            URL u = new URL( ADDRESS );
            HttpURLConnection uc = (HttpURLConnection) u.openConnection();

            // XML document parsing.
            // DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Document doc = docBuilder.parse(uc.getInputStream());
            // System.out.println(
            // 	doc.getDocumentElement().getFirstChild().getFirstChild().getNodeValue());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}