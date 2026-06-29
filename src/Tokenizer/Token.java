package Tokenizer;

public class Token {
    private TokenType tokenType = TokenType.UNKNOWN;
    private String charValue = null;
    private long numValue = 0;
    public Token(TokenType tokenType, String charValue){
        this.tokenType = tokenType;
        this.charValue = charValue;
    }
    public Token(TokenType tokenType, String charValue, long numValue){
        this.tokenType = tokenType;
        this.charValue = charValue;
        this.numValue = numValue;
    }
    public Token(TokenType tokenType, long numValue){
        this.tokenType = tokenType;
        this.numValue = numValue;
    }
    public TokenType getTokenType() {
        return tokenType;
    }
    public String getCharValue() {
        return charValue;
    }
    public long getNumValue() {
        return numValue;
    }
    public String toString() {
        return String.format("|%-13s|  |charContent: %-10s|  |numContent: %d|", tokenType.toString(), charValue, numValue);
    }

}

