import Parser.*;
import Tokenizer.Tokenizer;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Tokenizer tokenizer = new Tokenizer();


        Set<Terminal> terminals = Set.of(

        );
        Set<NonTerminal> nonTerminals = Set.of(

        );









        try{
            if(args.length == 0){
                throw new Exception("no arguments");
            }
            else{
                String fileName = args[0];
                tokenizer.readInput(fileName);
                tokenizer.printUsefulTokens();
            }

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}