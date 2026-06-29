package Tokenizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.lang.*;
import java.util.Iterator;
import java.util.Scanner;


//TODO: Refactor using RegEx. What was I thinking???
public class Tokenizer {
    private ArrayList<Token> tokens = new ArrayList<>();



    public void readInput(String fileName){
        try{
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            char c = '\0';
            StringBuilder sb = new StringBuilder();
            TokenType currentTokenType = TokenType.UNKNOWN;
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                for(int i = 0; i < line.length(); i++){
                    if(c != '\0'){

                        currentTokenType = determineTokenType(sb.toString());
                        if(fitsTokenType(c, currentTokenType)){
                            sb.append(c);
                        }
                        else{
                            String tokenValue = sb.toString();
                            if(currentTokenType == TokenType.NUMBER){

                                this.tokens.add(new Token(currentTokenType, Long.parseLong(tokenValue)));
                            }
                            else{
                                this.tokens.add(new Token(currentTokenType,tokenValue));
                            }
                            sb = new StringBuilder();
                            sb.append(c);

                        }

                    }
                    c = line.charAt(i);
                }

            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found: " + fileName);
        }

    }

    private TokenType determineTokenType(String s){
        if(s.isEmpty()) return TokenType.UNKNOWN;
        switch(s){
            case " ", "#":
                return TokenType.IGNORE;
            case "(":
                return TokenType.LEFT_PAREN;
            case ")":
                return TokenType.RIGHT_PAREN;
            case "[":
                return TokenType.LEFT_BRACKET;
            case "]":
                return TokenType.RIGHT_BRACKET;
            case "{":
                return TokenType.LEFT_BRACE;
            case "}":
                return TokenType.RIGHT_BRACE;
            case "not":
                return TokenType.NOT;
            case "+", "++":
                return TokenType.PLUS;
            case "-":
                return TokenType.MINUS;
            case "*":
                return TokenType.MULTIPLY;
            case "/":
                return TokenType.DIVIDE;
            case "and":
                return TokenType.AND;
            case "or":
                return TokenType.OR;
            case "return":
                return TokenType.RETURN;
            case ";":
                return TokenType.SEMICOLON;
            case ":":
                return TokenType.COLON;
            case ",":
                return TokenType.COMMA;
            case "if":
                return TokenType.IF;
            case "else":
                return TokenType.ELSE;
            case "while":
                return TokenType.WHILE;
            case ":=":
                return TokenType.ASSIGN;
            case "int", "bool", "char", "string":
                return TokenType.DATA_TYPE;
            default:
                /*
                    This isn't exactly ideal, but I'm too lazy to write a proper check
                 */
                if(Character.isDigit(s.charAt(s.length()-1))){
                    return TokenType.NUMBER;
                }
                else if(Character.isLetter(s.charAt(s.length()-1))){
                    return TokenType.IDENTIFIER;
                }
                else{
                    return TokenType.UNKNOWN;
                }

        }
    }

    private boolean fitsTokenType(char c, TokenType currentTokenType){
        return switch (currentTokenType) {
            case IDENTIFIER -> Character.isLetterOrDigit(c);
            case NUMBER -> Character.isDigit(c);
            case COLON -> c == '=';
            case PLUS -> c == '+';
            default -> false;
        };
    }

    public Iterator<Token> getTokenIterator(){
        return tokens.iterator();
    }

    public void print(){
        for(Token token : tokens){
                System.out.println(token);
        }
    }

    /**
     * Only prints non IGNORE/UNKNOWN tokens and just for fun some stats.
     */
    public void printUsefulTokens(){
        int totalNum = 0;
        int truncatedNum = 0;
        for(Token token : tokens){
            totalNum++;
            if(token.getTokenType() != TokenType.IGNORE && token.getTokenType() != TokenType.UNKNOWN){
                truncatedNum++;
                System.out.println(token);
            }

        }
        System.out.println("Total number of tokens: " + totalNum + ". Actually useful/non-ignored number of tokens: " + truncatedNum);

    }
}
