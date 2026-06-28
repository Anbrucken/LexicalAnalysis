//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Tokenizer tokenizer = new Tokenizer();
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