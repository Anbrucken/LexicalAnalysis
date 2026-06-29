package Parser;
//TODO: figure out how to properly represent grammars
//TODO: define language
//TODO: reminder: small steps!

import Tokenizer.Token;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

import static Tokenizer.TokenType.EOF;


public class Parser {

    private final Iterator<Token> tokens;
    private final Grammar grammar;
    private Stack<Symbol> stack;
    private Token lookahead;

    public Parser(Iterator<Token> tokens, Grammar grammar) {
        this.tokens = tokens;
        this.grammar = grammar;
        this.stack = new Stack<>();
        this.stack.push(new Terminal(new Token(EOF,"$")));
        this.stack.push(grammar.getStartSymbol());
    }

    public boolean analyze() {
        while(this.tokens.hasNext()){
            Token sym = peek();
            Symbol topOfStack = this.stack.peek();
            if(topOfStack instanceof Terminal){
                Terminal t = (Terminal) topOfStack;
                if(t.token().getCharValue().equals("$")){
                    if (peek() == null) {
                        stack.pop();
                        return true;
                    }
                    return false;
                }
                else{
                    if(sym.getCharValue() != null){
                        if(sym.getCharValue().equals(t.token().getCharValue())){
                            advance();
                        }
                        else{
                            this.stack.pop();
                            continue;
                        }
                    }
                    else{
                        if(sym.getNumValue() == t.token().getNumValue()){
                            continue;
                        }
                        else{
                            this.stack.pop();
                            continue;
                        }
                    }


                }
            }
            else if(topOfStack instanceof NonTerminal){
                NonTerminal nt = (NonTerminal) topOfStack;
                List<Production> prods = this.grammar.getProductionsFor(nt);
                if(prods.size() != 1){
                    if(prods.size() > 1) throw new Error("Too many productions for: " + nt + " " + prods.toString());
                    if(prods.size()<1) throw new Error("No productions for: " + nt);
                }
                else{
                    Production p = prods.get(0);
                    this.stack.pop();
                    ListIterator<Symbol> pIt = p.right().listIterator(p.right().size());
                    while(pIt.hasPrevious()){
                        this.stack.push(pIt.previous());
                    }
                    continue;
                }
                advance();
            }
            else{
                throw new Error("Unrecognized token");
            }

        }
        return false;
    }

    private Token peek() {
        if(this.lookahead == null && this.tokens.hasNext()) this.lookahead = this.tokens.next();
        return this.lookahead;
    }

    private Token advance() {
        Token t = peek();
        this.lookahead = null;
        return t;
    }



}
