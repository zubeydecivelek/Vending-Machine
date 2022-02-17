public class Tokens implements Comparable<Tokens>{
    private String id;
    private String type;
    private int tokenNumber;

    public String getType() {
        return type;
    }

    public Tokens(String id, String type, int tokenNumber) {
        this.id = id;
        this.type = type;
        this.tokenNumber = tokenNumber;
    }

    public void setTokenNumber(int tokenNumber) {
        this.tokenNumber = tokenNumber;
    }

    public int getTokenNumber() {
        return tokenNumber;
    }

    public void decreaseTokenNumber(){
        tokenNumber--;
    }

    @Override
    public int compareTo(Tokens o) {
        if (this.tokenNumber>o.tokenNumber){
            return 1;
        }
        else if (this.tokenNumber<o.tokenNumber){
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return id +" "+ type + " " + tokenNumber;
    }

}
