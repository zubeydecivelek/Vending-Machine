import java.io.*;
import java.util.ArrayList;


public class Main {
    public static BufferedWriter outputFile;

    public static ArrayList<String> ReadFile(String fileName) throws IOException { //This method reads the input files
        ArrayList<String> arrayList = new ArrayList<>();
        BufferedReader file = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line=file.readLine())!=null){
            arrayList.add(line);
        }
        return arrayList;
    }

    public static void main(String[] args) throws IOException {
        outputFile  = new BufferedWriter(new FileWriter(args[4],true)); //CREATING OUTPUT FILE

        //Reading input files and saving them in ArrayList
        ArrayList<String> itemsList = ReadFile(args[1]);
        ArrayList<String> partsList = ReadFile(args[0]);
        ArrayList<String> tokensList = ReadFile(args[2]);
        ArrayList<String> taskList = ReadFile(args[3]);

        //Creating 2D Stack for Items
        Stack<Stack<Items>> itemStack = new Stack<>();
        for (String parts:partsList){
            Stack<Items> stack = new Stack<>();
            for (String items:itemsList){
                if (parts.equals(items.split(" ")[1])) {
                    Items item = new Items(items.split(" ")[0], items.split(" ")[1]);
                    stack.push(item);
                }
            }
            itemStack.push(stack);
        }

        //Creating Priority Queue for Tokens
        Queue tokenQueue = new Queue();
        for (String token:tokensList){
            String[] splitLine = token.split(" ");
            Tokens tokens = new Tokens(splitLine[0],splitLine[1],Integer.parseInt(splitLine[2]));
            tokenQueue.offer(tokens);
        }

        //Applying the tasks
        for (String task: taskList){
            String[] splitTask = task.split("\t");
            if (splitTask[0].equals("BUY")){
                for (int i = 1;i<splitTask.length;i++){
                    String buyType = splitTask[i].split(",")[0];
                    int buy = Integer.parseInt(splitTask[i].split(",")[1]);
                    Tokens token = null;
                    for (;buy>0;buy--){
                        if (itemStack.hasParts(buyType)){
                            Stack<Items> popStack = itemStack.findStack(buyType);
                            popStack.pop();
                        }
                        if (tokenQueue.hasTokenType(buyType)){
                            token = tokenQueue.findMaxToken(buyType);
                            token.decreaseTokenNumber();
                            tokenQueue.removeIfZero();
                        }
                    }
                    if (tokenQueue.hasToken(token)) {
                        tokenQueue.setIndexToZero(token);
                    }
                }
            }
            else if (splitTask[0].equals("PUT")){
                for (int i = 1;i<splitTask.length;i++){
                    String[] addItems = splitTask[i].split(",");
                    for (int j = 1;j<addItems.length;j++){
                        Items item = new Items(addItems[j],addItems[0]);
                        Stack<Items> putStack = itemStack.findStack(addItems[0]);
                        putStack.push(item);
                    }
                }
            }
        }

        // Writing Vending Machine to output file after all tasks completed
        itemStack.reverse();
        for (String part:partsList){
            if (!partsList.get(0).equals(part)){
                outputFile.write("---------------\n");
            }
            outputFile.write(part+":\n");
            Stack<Items> printStack = itemStack.findStack(part);
            printStack.print();
        }
        outputFile.write("---------------\nToken Box:\n");
        tokenQueue.print();
        outputFile.close();
    }
}
