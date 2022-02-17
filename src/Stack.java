import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Stack<E> {
    private ArrayList<E> stackArrayList = new ArrayList<>();

    public Stack() {
    }

    public void push(Stack object){
        stackArrayList.add((E) object);
    }

    public void push(Items object){
        stackArrayList.add((E) object);
    }

    public E pop(){
        E lastElement = stackArrayList.get(stackArrayList.size()-1);
        stackArrayList.remove(lastElement);
        return lastElement;
    }

    public void reverse(){
        for (Object stack : stackArrayList) {
            Stack<Items> reverseStack = (Stack<Items>) stack;
            Collections.reverse(reverseStack.stackArrayList);
        }
    }

    public boolean isEmpty(){
        return stackArrayList.isEmpty();
    }

    public boolean hasParts(String parts) {
        for (Object stack : stackArrayList) {
            Stack<Items> controlStack = (Stack<Items>) stack;
            if (!controlStack.isEmpty()) {
                Items controlItem = (Items) controlStack.stackArrayList.get(0);
                if (controlItem.getType().equals(parts)){
                    return true;
                }
            }
        }
        return false;
    }

    public Stack<Items> findStack(String part) {
        Stack<Items> returnStack = null;
        if(hasParts(part)) {
            for (Object stack : stackArrayList) {
                Stack<Items> controlStack = (Stack<Items>) stack;
                if (!controlStack.isEmpty()) {
                    Items controlItem = controlStack.stackArrayList.get(0);
                    if (controlItem.getType().equals(part)) {
                        returnStack = controlStack;
                        break;
                    }
                }
            }
        }
        else{
            for (Object stack : stackArrayList) {
                Stack<Items> controlStack = (Stack<Items>) stack;
                if (controlStack.isEmpty()) {
                    returnStack = controlStack;
                    break;
                }
            }
        }
        return returnStack;
    }

    public void print() throws IOException {
        for (Object items: stackArrayList){
            Main.outputFile.write(items + "\n");
        }
        if (isEmpty()){
            Main.outputFile.write("\n");
        }
    }
}
