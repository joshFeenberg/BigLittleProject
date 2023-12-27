import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Does the matching using a greedy algorithm then by minimum matching using Kőnig's theorem insperation
 * for more info(https://en.wikipedia.org/wiki/Greedy_algorithm) and
 * (https://en.wikipedia.org/wiki/K%C5%91nig%27s_theorem_(graph_theory))
 */
public class Matchings{
    //file for the big
    public File bigFile;
    // file of littles
    public File littleFile;
    // list of littles
    // reader to read and add students
    public Reader reader;
    // file of bigs
    public static ArrayList<Little> littles;
    // list of Bigs
    public static ArrayList<Big> bigs;
    // list of matchings
    public static ArrayList<Matching> matchings = new ArrayList<>();

    /**
     * constuctor for the matching to begin
     * @param bigPath the file of bigs to parse through and add to bigs arrylist
     * @param littlePath the file of littles to parse through and add to littles arrayList
     */
    public Matchings(String bigPath, String littlePath){
        reader = new Reader();
        bigFile = new File(bigPath);
        littleFile = new File(littlePath);
        littles = reader.loadAndAddLittles(littleFile);
        bigs = reader.loadAndAddBigs(bigFile);
    }
    /**
     * getter for littles
     * @return list of littles
     */
    public static ArrayList<Little> getLittles(){
        return littles;
    }

    /**
     * getter for bigs
     * @return list of bigs
     */
    public static ArrayList<Big> getBigs(){
        return bigs;
    }

    /**
     * adds the matchings using a greedy algorthim and removes them from their lists when added
     * @param littles list of littles
     * @param bigs list of Bigs
     */
    public static void addMatchingsGreedy(ArrayList<Little> littles, ArrayList<Big> bigs) {
        while (!littles.isEmpty() && !bigs.isEmpty()) {
            double minMatchingScore = 1000;
            Little minLittle = null;
            Big minBig = null;
            int littleIndex = 0;
            // while there are littles parse through
            while (littleIndex < littles.size()) {
                Little little = littles.get(littleIndex);
                int bigRank = 0;
                // while there are still bigs left that the little out parse though the bigs
                while (bigRank < little.getBigs().length) {
                    // if a big is found search the big
                    if (little.getBigs()[bigRank] != null) {
                        String bigName = little.getBigs()[bigRank];
                        int bigListIndex = 0;
                        while (bigListIndex < bigs.size()) {
                            // if the big is still found serc h it
                            if (bigs.get(bigListIndex).getName().equals(bigName)) {
                                Big big = bigs.get(bigListIndex);
                                int littleRank = 0;
                                // find if the big ranks the little
                                while (littleRank < big.getLittles().length) {
                                    if (big.getLittles()[littleRank] != null &&
                                            big.getLittles()[littleRank].equals(little.getName())) {
                                        // if the little is found calculate the score
                                        double score = (littleRank) + bigRank * 1.1;
                                        // if a better score is found update the matching
                                        if (score < minMatchingScore) {
                                            minBig = big;
                                            minLittle = little;
                                            minMatchingScore = score;
                                        }
                                    }
                                    // if it is not the little go to next little
                                    littleRank++;
                                }
                            }
                            // if it is not the big go to the next big
                            bigListIndex++;
                        }
                    }
                    // if the big is null it is already in a matching and should be skipped
                    bigRank++;
                }
                // if the index is greater than the allowed to be ranked the next little should be serched
                littleIndex++;
            }
            if (minBig != null && minLittle != null) {
                Matching matchingToAdd = new Matching(minLittle, minBig, minMatchingScore);
                littles.remove(minLittle);
                bigs.remove(minBig);
                matchings.add(matchingToAdd);
            } else {
                break;
            }
        }
    }

    /**
     * Inspired by Kőnig's theorem, find a minimum value maximum matching where there only needs to be one connection ex(Big puts little but little does not put big)
     *
     * @param littles     list of littles not in a matching already
     * @param bigs        list of bigs not in a matching already
     * @param placeToAdd  The place to add (always start at 0)
     */
    public static void addMatchingsPerfect(ArrayList<Little> littles, ArrayList<Big> bigs, int placeToAdd) {
        // base case: place to add is bigger than matches allowed or either list is empty
        if (littles.isEmpty() || bigs.isEmpty() || placeToAdd >= littles.get(0).getBigs().length) {
            return;
        }
        // parse through littles and find if big is found in placeToAdd
        for (int i = 0; i < littles.size(); i++) {
            Little little = littles.get(i);
                // find big
                for (int j = 0; j < bigs.size(); j++) {
                    Big big = bigs.get(j);
                    if (big.getName().equals(little.getBigs()[placeToAdd])) {
                        // add matching
                        double score = 10 + placeToAdd;
                        Matching matching = new Matching(little, big, score);
                        matchings.add(matching);
                        littles.remove(little);
                        bigs.remove(big);
                        addMatchingsPerfect(littles, bigs, placeToAdd);
                    }
                }
        }
        for (int i = 0; i < bigs.size(); i++) {
            Big big = bigs.get(i);
            // find little
            for (int j = 0; j < littles.size(); j++) {
                Little little = littles.get(j);
                if (little.getName().equals(big.getLittles()[placeToAdd])) {
                    // add matching
                    double score = 10 + placeToAdd;
                    Matching matching = new Matching(little, big, score);
                    matchings.add(matching);
                    littles.remove(little);
                    bigs.remove(big);
                    addMatchingsPerfect(littles, bigs, placeToAdd);
                }
            }
        }

        // recursive call for the next placeToAdd
        addMatchingsPerfect(littles, bigs, placeToAdd + 1);
    }
    public static void saveMatchings() {
        try {
            FileWriter myWriter = new FileWriter("Final Matching");
            for (Matching matching : matchings) {
                myWriter.write(matching.toString() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }





    public static void main(String[] args) {
        Matchings newMatchings = new Matchings("bigs.csv", "littles.csv");
        ArrayList<Little> littles = newMatchings.getLittles();
        ArrayList<Big> bigs = newMatchings.getBigs();
        addMatchingsGreedy(littles, bigs);
        addMatchingsPerfect(littles, bigs, 0);
        saveMatchings();
    }
}
