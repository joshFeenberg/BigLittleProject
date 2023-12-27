import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Reads in files given to get list of Bigs and Littles
 */
public class Reader {
    /**
     * reads and adds Littles given
     * @param rosterFile file of littles
     */
    public ArrayList<Little> loadAndAddLittles(File rosterFile) {
        ArrayList<Little> littles = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(rosterFile);
            while (myReader.hasNextLine()) {
                String studentString = myReader.nextLine();
                String[] nameAndBigs = studentString.split(",");
                String littleName = nameAndBigs[0];
                String[] bigNames = Arrays.copyOfRange(nameAndBigs, 1, nameAndBigs.length);
                Little little = new Little(littleName,bigNames);
                littles.add(little);

                }
            myReader.close();
    }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        return littles;
    }
    /**
     * reads and adds Bigs given
     * @param rosterFile file of Bigs
     */
    public ArrayList<Big> loadAndAddBigs(File rosterFile) {
        ArrayList<Big> bigs = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(rosterFile);
            while (myReader.hasNextLine()) {
                String studentString = myReader.nextLine();
                String[] nameAndLittles = studentString.split(",");
                String bigName = nameAndLittles[0];
                String[] littleNames = Arrays.copyOfRange(nameAndLittles, 1, nameAndLittles.length);
                Big big = new Big(bigName,littleNames);
                bigs.add(big);
            }
            myReader.close();
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        return bigs;
    }



}
