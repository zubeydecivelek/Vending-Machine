public class Items {
    private String id;
    private String type;

    public Items(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getType() {
        return type;
    }


    @Override
    public String toString() {
        return id;
    }
}
