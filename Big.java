/**
 * instance of a Big
 */
public class Big {
    // name of Big
    private String name;
    // List of littles they want in order
    private String[] littles;

    /**
     * Makes the little
     * @param name name of the Big
     * @param littles list of littles they want
     */
    public Big(String name, String[] littles){
        this.name = name;
        this.littles = littles;
    }
    /**
     * getter for name
     * @return name of Big
     */
    public String getName() {
        return name;
    }

    /**
     * getter for littles
     * @return littles
     */
    public String[] getLittles() {
        return littles;
    }

    /**
     * To string for Big
     * @return new string
     */
    @Override
    public String toString() {
        String s = "";
        s += "Big: " + name + "\n";
        s += "Littles: " + "\n";
        for (int i = 0; i < littles.length; i++){
            s += i+1 + ". " + littles[i] + "\n";
        }
        return s;
    }

}
