public class NumberTok extends Token {
	private static int numero;
    public String lexeme = "";
    
    public NumberTok(int tag, int s) { 
        super(tag); 
        lexeme = String.valueOf(s) ;
    }
    public String toString() {
        return "<" + tag + ", " + lexeme + ">";
    }

    public static final NumberTok 
    num = new NumberTok( Tag.NUM , numero);
}
