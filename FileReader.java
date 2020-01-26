package commandline;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReader {
	
		public static void main(String[] args) {
			
			
	        File file = new File("StarCitizenDeck.txt");

	        BufferedReader bufferedReader = null;

	        try {
	            java.io.FileReader fileReader = new java.io.FileReader(file);
	            bufferedReader = new BufferedReader(fileReader);

	            String line;
	            
	            while( (line = bufferedReader.readLine()) != null ) {
	                System.out.println(line);
	            }

	        } catch (FileNotFoundException exception) {
	            System.out.println("File not found: " + file.toString());
	        } 
	        catch (IOException exception) {
	            System.out.println("Unable to read file: " + file.toString());
	        }
	        finally {
	         
	        	try {
	                bufferedReader.close();
	            }
	        	catch (IOException e) {
	                System.out.println("Unable to close file: " + file.toString());
	            }
	            catch(NullPointerException exception) {
	            }
	        }
	    }

	}
