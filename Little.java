/**
 * instance of a little
 */
public class Little {
    // name of little
    private String name;
    // List of bigs they want in order
    private String[] Bigs;

    /**
     * Makes the little
     * @param name name  in orderof little
     * @param Bigs list of bigs they want
     */
    public Little(String name, String[] Bigs){
        this.name = name;
        this.Bigs = Bigs;
    }
    /**
     * getter for name
     * @return name of little
     */
    public String getName() {
        return name;
    }

    /**
     * getter for Bigs
     * @return bigs
     */
    public String[] getBigs() {
        return Bigs;
    }
    /**
     * New to string for Littles
     * @return new string
     */
    @Override
    public String toString() {
        String s = "";
        s += "Little: " + name + "\n";
        s += "Bigs: " + "\n";
        for (int i = 0; i < Bigs.length; i++){
            s += i+1 + ". " + Bigs[i] + "\n";
        }
        return s;
    }
}
