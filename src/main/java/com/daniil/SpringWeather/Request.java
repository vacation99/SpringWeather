package com.daniil.SpringWeather;

import com.daniil.SpringWeather.Models.Weather;
import com.daniil.SpringWeather.Repo.WeatherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class Request {

    private ArrayList<String> requestsList = new ArrayList<>();
    private ArrayList<String> arrayList = new ArrayList<>();

    @Autowired
    WeatherRepo weatherRepo;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) {
        return args -> {
            requestsList.add("http://api.openweathermap.org/data/2.5/weather?q=moscow&mode=xml&lang=ru&units=metric&appid=14afcdee100251caff41fa708c4dd4ca");
            requestsList.add("http://api.openweathermap.org/data/2.5/weather?q=London&mode=xml&lang=ru&units=metric&appid=14afcdee100251caff41fa708c4dd4ca");
            requestsList.add("http://api.openweathermap.org/data/2.5/weather?q=Washington&mode=xml&lang=ru&units=metric&appid=14afcdee100251caff41fa708c4dd4ca");
            requestsList.add("http://api.openweathermap.org/data/2.5/weather?q=Yaroslavl&mode=xml&lang=ru&units=metric&appid=14afcdee100251caff41fa708c4dd4ca");

            for (String s : requestsList) {
                String request = restTemplate.getForObject(s, String.class);
                parse(request);
            }
        };
    }

    private void parse(String url) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new ByteArrayInputStream(url.getBytes()));

        NodeList element = document.getElementsByTagName("current");
        Node foundedElement = element.item(0);

        arrayList.clear();

        printInfoAboutChildNodes(foundedElement.getChildNodes());
    }

    private void printInfoAboutChildNodes(NodeList list) {
        for (int i = 0; i < list.getLength(); i++) {

            Node node = list.item(i);

            switch (node.getNodeName()) {
                case "city":
                case "clouds":
                    getObjects("name", node);
                    break;
                case "sun":
                    getObjects(null, node);
                    break;
                case "temperature":
                case "speed":
                    getObjects("unit", node);
                    break;
                case "feels_like":
                case "weather":
                    getObjects("value", node);
                    break;
                case "direction":
                    getObjects("code", node);
                    break;
            }

            if (node.hasChildNodes())
                printInfoAboutChildNodes(node.getChildNodes());
        }
        if (arrayList.size() == 13) {
            Weather weather = new Weather(arrayList.get(0), arrayList.get(1), arrayList.get(2), arrayList.get(3), arrayList.get(4),arrayList.get(5), arrayList.get(6), arrayList.get(7), arrayList.get(8), arrayList.get(9), arrayList.get(10), arrayList.get(11), arrayList.get(12));
            weatherRepo.save(weather);
        }
    }

    private void getObjects(String attributeName, Node node) {
        NamedNodeMap attributes = node.getAttributes();

        if (node.getNodeName().equals("direction")) {
            if (!node.hasAttributes()) {
                arrayList.add("Пусто");
                arrayList.add("0");
            }
        }
        for (int o = 0; o < attributes.getLength(); o++) {
            if (node.getNodeName().equals("sun") || node.getNodeName().equals("temperature") || node.getNodeName().equals("speed") || node.getNodeName().equals("direction")) {
                if (node.getNodeName().equals("sun"))
                    arrayList.add(parseDate(attributes.item(o).getNodeValue()));
                if (node.getNodeName().equals("temperature")) {
                    if (!attributes.item(o).getNodeName().equals(attributeName))
                        arrayList.add(attributes.item(o).getNodeValue());
                }
                if (node.getNodeName().equals("speed")) {
                    if (!attributes.item(o).getNodeName().equals(attributeName))
                        arrayList.add(attributes.item(o).getNodeValue());
                }
                if (node.getNodeName().equals("direction")) {
                    if (!attributes.item(o).getNodeName().equals(attributeName))
                        arrayList.add(attributes.item(o).getNodeValue());
                }
            } else {
                if (attributes.item(o).getNodeName().equals(attributeName))
                    arrayList.add(attributes.item(o).getNodeValue());
            }
        }
    }

    private String parseDate(String date) {
        String line = "";
        char[] chars = date.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'T') {
                for (int o = i + 1; o < chars.length; o++) {
                    line += chars[o];
                }
                line += " GMT";
                break;
            }
        }
        return line;
    }
}
