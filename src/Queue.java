import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Queue{ //Priority Queue
    private ArrayList<Tokens> queueArrayList = new ArrayList<>();

    public Queue() {
    }

    public void offer(Tokens object){
        queueArrayList.add(0, object);
        Collections.sort(queueArrayList);
    }

    public void setIndexToZero(Tokens token){
        queueArrayList.remove(token);
        offer(token);
    }

    public void removeIfZero(){
        queueArrayList.removeIf(tokens -> tokens.getTokenNumber() == 0);
    }

    public Tokens findMaxToken(String tokenType){
        Tokens returnToken = null;
        for (int i=queueArrayList.size()-1; i>0; i--){
            if (queueArrayList.get(i).getType().equals(tokenType)){
                returnToken = queueArrayList.get(i);
                break;
            }
        }
        return returnToken;
    }

    public boolean hasTokenType(String tokenType){
        for (Tokens tokens:queueArrayList){
            if (tokens.getType().equals(tokenType)){
                return true;
            }
        }
        return false;
    }

    public void print() throws IOException {
        for (Tokens tokens:queueArrayList){
            Main.outputFile.write(tokens + "\n");
        }
    }

    public boolean hasToken(Tokens tokens){
        for (Tokens tokens1:queueArrayList){
            if (tokens1.equals(tokens)){
                return true;
            }
        }
        return false;
    }
}
