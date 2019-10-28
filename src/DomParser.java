
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.File;

import java.util.Scanner;

public class DomParser {

	private Document doc;
	private Scanner input;
	private String fragment;
	private Element root;
	private String filename;

	DomParser(){

		try {

			input = new Scanner(System.in);

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			doc = builder.parse(getFile());
			root = doc.getDocumentElement();

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public File getFile(){
		System.out.println("Enter file name or nothing for default: Shakespeare/hamlet.xml");
		String userInput = input.nextLine();
		File file;
		if (userInput.equals(""))
			file = new File("Shakespeare/hamlet.xml");
		else
			file = new File(userInput);
		return file;
	}

	// Calculates and prints the total number of different characters in the play
	public void findPersona() {
		NodeList personaList = root.getElementsByTagName("PERSONA");
		System.out.println("Number of Characters: " + personaList.getLength() + "\n");
	}


	// Calculates and prints number of times a requested character speaks
	public void findNumberOfSpeaker() {

		System.out.println("Enter a character name in all caps" + " to learn how many times they speak or nothing for default: HAMLET");
		Scanner input = new Scanner(System.in);
		String character = input.nextLine();
		if (character.equals(""))
			character = "HAMLET";

		NodeList speakerList = root.getElementsByTagName("SPEAKER");
		int count = 0;

		Element speakerElement;

		for (int i = 0; i < speakerList.getLength(); i++) {

			speakerElement = (Element) speakerList.item(i);

			if (speakerElement.getTextContent().equals(character)) {
				count++;
			}
		}
		System.out.println("The character " + character + " speaks " + count + " times.\n");
	}

	//Find line of text from user input
	public Node[] findText() {

		System.out.println("Insert fragment to be found or nothing for default: 'To be, or not to be':");
		fragment = input.nextLine();

		if (fragment.equals(""))
			fragment = "To be, or not to be";

		int numberOfSentences = 0;
		boolean isFound = false;
		NodeList lineList = root.getElementsByTagName("LINE");
		Element lineElement;
		Node[] lineNodeArray = new Node[lineList.getLength()];

		long lStartTime = System.nanoTime();
		for (int i = 0; i < lineList.getLength(); i++) {
			lineElement = (Element) lineList.item(i);

			if (lineElement.getTextContent().contains(fragment)) {
				lineNodeArray[numberOfSentences] = lineList.item(i);
				numberOfSentences++;
				isFound = true;
			}
		}

		long lEndTime = System.nanoTime();
		if (!isFound)
			System.out.println("Sorry, fragment not found.");
		System.out.println("Search performed in " + ((lEndTime - lStartTime) / 1000000000.0) + " seconds");
		if (isFound) {
			System.out.println("The fragment has been found in the following sentence(s):");
			for (int i = 0; i < numberOfSentences; i++)
				System.out.println((i+1) + ") '" + lineNodeArray[i].getTextContent() + "'");
			System.out.println("Do you want to replace it? (Y/N)");
		}
		String answer = input.nextLine();
		if (answer.equals("Y") || answer.equals("y")) {
			replaceText(lineNodeArray);
			return lineNodeArray;
		}
		else
			return lineNodeArray;
	}


	//Input text to replace line
	public void replaceText(Node[] lineNodeArray){

		System.out.println("Enter number of the line you want to replace");
		int lineNumber = input.nextInt();
		input.nextLine();
		System.out.println("Enter the new fragment");
		String replacement = input.nextLine();
		String sentence = lineNodeArray[lineNumber - 1].getTextContent().replace(fragment, replacement);

		lineNodeArray[lineNumber - 1].setTextContent(sentence);
		System.out.println("The sentence has been replaced as follows:\n" +
				lineNodeArray[lineNumber - 1].getTextContent() + "\nDo you want to save changes? (Y/N)");
		String answer = input.nextLine();
		if (answer.equals("Y") || answer.equals("y")) {
			overwriteFile();
		}
	}

	public boolean saveFile(String newFilename) {
		boolean success = true;

		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			StreamResult output = new StreamResult(new File(newFilename));
			DOMSource input = new DOMSource(this.doc);

			transformer.transform(input, output);

		} catch (TransformerException e) {
			e.printStackTrace();
			success = false;
		}

		// Change the current filename if Save As... works properly
		if (success) {
			this.filename = newFilename;
			System.out.println("File saved successfully\n");
		}

		return success;
	}

	public void overwriteFile(){
		System.out.println("Enter the name of the file you would like to save it as");
		this.filename = input.nextLine();
		if (this.filename.equals("Shakespeare/hamlet.xml")) {
			System.out.println("Do you want to overwrite the file hamlet.xml? (Y/N)");
			String answer = input.nextLine();
			if (answer.equals("Y") || answer.equals("y")) {
				saveFile(this.filename);
			}
		}
		else
			saveFile(this.filename);
	}

}


