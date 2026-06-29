package Parser;

import java.util.List;

public record Production(NonTerminal left, List<Symbol> right) {
}
