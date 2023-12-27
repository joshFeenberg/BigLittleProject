/**
 * Matching of a Big and a Little
 */
public class Matching {
    //little in matching
    public Little little;
    //big in matching
    public Big big;
    //score of the big little matching (Lower the score the better the matching)
    public double score;

    /**
     * constuctor for the matching
     * @param little little in the matching
     * @param big big in the matching
     * @param score score of the matching
     */
    public Matching(Little little, Big big, double score){
        this.little = little;
        this.big = big;
        this.score = score;
    }
    /**
     * To String for the matching
     * @return string value
     */
    @Override
    public String toString() {
        return "Matching: " + little.getName() + " with " + big.getName() + " Score: " + score;
    }
}
