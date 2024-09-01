package hr.algebra.chess.utils;

import hr.algebra.chess.model.GameMove;
import hr.algebra.chess.model.Piece;
import hr.algebra.chess.model.Team;
import hr.algebra.chess.model.pieces.Bishop;
import hr.algebra.chess.model.pieces.King;
import hr.algebra.chess.model.pieces.Knight;
import hr.algebra.chess.model.pieces.Pawn;
import hr.algebra.chess.model.pieces.Queen;
import hr.algebra.chess.model.pieces.Rook;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class XmlUtils {

    private static final String FILENAME = "./xml/game_moves.xml";

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");

    public static void saveGameMoveToXml(GameMove gameMove) {

        List<GameMove> gameMoveList = readGameMovesFromXmlFile();
        gameMoveList.add(gameMove);
        //List<GameMove> gameMoveList = new ArrayList<>();

        try {
            Document document = createDocument("gameMoves");

            for(GameMove gameMoveXmlNode : gameMoveList) {

                Element gameMoveElement = document.createElement("gameMove");
                document.getDocumentElement().appendChild(gameMoveElement);

                gameMoveElement.appendChild(createElement(document, "piece", gameMoveXmlNode.getPiece().toString()));
                gameMoveElement.appendChild(createElement(document, "startLocationX", String.valueOf(gameMoveXmlNode.getPiece().getStartLocationX())));
                gameMoveElement.appendChild(createElement(document, "startLocationY", String.valueOf(gameMoveXmlNode.getPiece().getStartLocationY())));
                gameMoveElement.appendChild(createElement(document, "imgString", gameMoveXmlNode.getPiece().getImgString()));
                gameMoveElement.appendChild(createElement(document, "team", gameMoveXmlNode.getPiece().getTeamColor().toString()));
                gameMoveElement.appendChild(createElement(document, "location", gameMoveXmlNode.getLocation()));
                gameMoveElement.appendChild(createElement(document, "oldLocation", gameMoveXmlNode.getOldLocation()));
                gameMoveElement.appendChild(createElement(document, "dateTime", gameMoveXmlNode.getDateTime().format(formatter)));
            }

            saveDocument(document, FILENAME);
        } catch (ParserConfigurationException | TransformerException ex) {
            ex.printStackTrace();
        }
    }

    private static Document createDocument(String element) throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation domImplementation = builder.getDOMImplementation();
        DocumentType documentType = domImplementation.createDocumentType("DOCTYPE", null, "employees.dtd");
        return domImplementation.createDocument(null, element, documentType);
    }

    private static Node createElement(Document document, String tagName, String data) {
        Element element = document.createElement(tagName);
        Text text = document.createTextNode(data);
        element.appendChild(text);
        return element;
    }

    private static void saveDocument(Document document, String fileName) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, document.getDoctype().getSystemId());
        transformer.transform(new DOMSource(document), new StreamResult(new File(fileName)));
    }

    public static List<GameMove> readGameMovesFromXmlFile() {

        List<GameMove> gameMoveList = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(FILENAME));
            Node node = document.getDocumentElement();

            NodeList childNodes = node.getChildNodes();

            int numberOfNodes = childNodes.getLength();

            for(int n = 0; n < numberOfNodes; n++) {

                Node parentNode = childNodes.item(n);

                if (parentNode.getNodeType() == Node.ELEMENT_NODE) {

                    NodeList gameMoveNodes = parentNode.getChildNodes();

                    String name = "";
                    int startLocationX = 0;
                    int startLocationY = 0;
                    String imgString = "";
                    Team team = Team.White;
                    String location = "";
                    String oldLocation = "";
                    String localDateTimeString = "";

                    for (int i = 0; i < gameMoveNodes.getLength(); i++) {

                        Node moveNode = gameMoveNodes.item(i);

                        if (moveNode.getNodeType() == Node.ELEMENT_NODE) {

                            switch (moveNode.getNodeType()) {
                                case Node.ELEMENT_NODE:
                                    Element nodeElement = (Element) moveNode;
                                    String nodeName = nodeElement.getNodeName();
                                    if (nodeName.equals("piece")) {
                                        name = nodeElement.getTextContent();
                                    }
                                    else if(nodeName.equals("startLocationX")) {
                                        startLocationX = Integer.parseInt(nodeElement.getTextContent());
                                    }
                                    else if(nodeName.equals("startLocationY")) {
                                        startLocationY = Integer.parseInt(nodeElement.getTextContent());
                                    }
                                    else if(nodeName.equals("imgString")) {
                                        imgString = nodeElement.getTextContent();
                                    }
                                    else if(nodeName.equals("team")) {
                                        String nodeValue = nodeElement.getTextContent();
                                        if(nodeValue.equals("Black")) {
                                            team = Team.Black;
                                        }
                                    }
                                    else if(nodeName.equals("location")) {
                                        location = nodeElement.getTextContent();
                                    }
                                    else if(nodeName.equals("oldLocation")) {
                                        oldLocation = nodeElement.getTextContent();
                                    }
                                    else if(nodeName.equals("dateTime")) {
                                        localDateTimeString = nodeElement.getTextContent();
                                    }
                                    break;
                                case Node.TEXT_NODE:
                                    break;
                                case Node.CDATA_SECTION_NODE:
                                    break;
                            }
                        }
                    }

                    Piece piece = null;

                    if(name.equals("Bishop")) {
                        piece = new Bishop(startLocationX, startLocationY, null, imgString, team);
                    }
                    else if(name.equals("King")) {
                        piece = new King(startLocationX, startLocationY, null, imgString, team);
                    }
                    else if(name.equals("Knight")) {
                        piece = new Knight(startLocationX, startLocationY, null, imgString, team);
                    }
                    else if(name.equals("Pawn")) {
                        piece = new Pawn(startLocationX, startLocationY, null, imgString, team);
                    }
                    else if(name.equals("Queen")) {
                        piece = new Queen(startLocationX, startLocationY, null, imgString, team);
                    }
                    else if(name.equals("Rook")) {
                        piece = new Rook(startLocationX, startLocationY, null, imgString, team);
                    }

                    piece.setImage();

                    LocalDateTime dateTime = LocalDateTime.parse(localDateTimeString, formatter);

                    GameMove gameMove = new GameMove(piece, location, oldLocation, dateTime);
                    gameMoveList.add(gameMove);
                }
            }
        }
        catch(ParserConfigurationException | SAXException | IOException ex) {
            ex.printStackTrace();
        }

        return gameMoveList;
    }

}