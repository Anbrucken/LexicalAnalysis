package Parser;

import Tokenizer.Token;

public record Terminal(Token token) implements Symbol {
}
