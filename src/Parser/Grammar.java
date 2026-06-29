package Parser;

import java.util.List;
import java.util.Set;

public class Grammar {
    private final Set<NonTerminal> nonTerminals;
    private final Set<Terminal> terminals;
    private final List<Production> productions;
    private final NonTerminal startSymbol;

    public Grammar(Set<NonTerminal> nonTerminals, Set<Terminal> terminals, List<Production> productions, NonTerminal startSymbol) {
        this.nonTerminals = nonTerminals;
        this.terminals = terminals;
        this.productions = productions;
        this.startSymbol = startSymbol;
    }

    public List<Production> getProductionsFor(NonTerminal nt) {
        return productions.stream().filter(p -> p.left().equals(nt)).toList();
    }

    public NonTerminal getStartSymbol() {
        return startSymbol;
    }
}
