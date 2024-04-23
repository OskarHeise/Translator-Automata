import java.io.*; 
import java.util.*;

public class Lexer {

    public static int line = 1;
    private char peek = ' ';

    private void readch(BufferedReader br) {
        try {
            peek = (char) br.read();
        } catch (IOException exc) {
            peek = (char) -1; // ERROR
        }
    }

    public Token lexical_scan(BufferedReader br) {
        while (peek == ' ' || peek == '\t' || peek == '\n'  || peek == '\r') {
            if (peek == '\n') line++;
            readch(br);
        }
        
        switch (peek) {
            case '!':
                peek = ' ';
                return Token.not;
            case '(':
                peek = ' ';
                return Token.lpt;
            case ')':
                peek = ' ';
                return Token.rpt;
            case '[':
                peek = ' ';
                return Token.lpq;
            case ']':
                peek = ' ';
                return Token.rpq;
            case '{':
                peek = ' ';
                return Token.lpg;
            case '}':
                peek = ' ';
                return Token.rpg;
            case '+':
                peek = ' ';
                return Token.plus;
            case '-':
                peek = ' ';
                return Token.minus;
            case '*':
                peek = ' ';
                return Token.mult;


            case '/':
                readch(br);
                if(peek == '/'){        //controllo se è un commento del tipo // se sì ignoro fino a \n
                    while(peek != '\n')
                        readch(br);
                }
                else if(peek == '*'){       //controllo se è un commento del tipo /*  se sì ignoro tutto fino a */
                    boolean f=false;
                    do{
                        f=false;
                        readch(br);
                        if(peek=='*') {
                            f = true; 
                            readch(br);
                        }
                    }
                    while( !((peek=='/') && (f==true)) );   
                }
                else {
                    peek = ' ';
                    return Token.div;
                }

            case ';':
                peek = ' ';
                return Token.semicolon;
            case ',':
                peek = ' ';
                return Token.comma;
            case '&':
                readch(br);
                if (peek == '&') {
                    peek = ' ';
                    return Word.and;
                } else {
                    System.err.println("Erroneous character"
                            + " after & : "  + peek );
                    return null;
                }
            case '|':
                readch(br);
                if (peek == '|') {
                    peek = ' ';
                    return Word.or;
                } else {
                    System.err.println("Erroneous character"
                            + " after | : "  + peek );
                    return null;
                }
            case '<':
                readch(br);
                if(peek == '='){
                    peek = ' ';
                    return Word.le;
                }
                else if(peek =='>'){
                    peek = ' ';
                    return Word.ne;
                }
                else return Word.lt;
            case '>':
                readch(br);
                if(peek == '='){
                    peek = ' ';
                    return Word.ge;
                }
                else return Word.gt;
            case '=':
                readch(br);
                if(peek == '='){
                    peek = ' ';
                    return Word.eq;
                }
                else {System.err.println("Erroneous character"+ " after = : "  + peek );
                return null;}

          
            case (char)-1:
                return new Token(Tag.EOF);

            default:
                if (Character.isLetter(peek) || peek == '_') {
                    return toWord(br);
                }

                 else if (Character.isDigit(peek)) {
                        return toNumber(br);
                       
                } 
                else {
                        System.err.println("Erroneous character: " 
                                + peek );
                        return null;
                }
         }
    }
    private Token toWord(BufferedReader br){
        String s = "";
        boolean onlyslash = true;
  
        while(Character.isLetterOrDigit(peek) || peek == '_'){
            s = s+""+peek;
            if(peek != '_') onlyslash = false;
            readch(br);
        }
        
        if(s.charAt(0) == '_' && onlyslash) throw new IllegalArgumentException("Identificatore formato da soli '_' " );
        else if (s.equals("assign")) return Word.assign;
        else if (s.equals("to")) return Word.to;
        else if (s.equals("conditional")) return Word.conditional;
        else if (s.equals("option")) return Word.option;
        else if (s.equals("do")) return Word.dotok;
        else if (s.equals("else")) return Word.elsetok;
        else if (s.equals("while")) return Word.whiletok;
        else if (s.equals("begin")) return Word.begin;
        else if (s.equals("end")) return Word.end;
        else if (s.equals("print")) return Word.print;
        else if (s.equals("read")) return Word.read;

        else return new Word(Tag.ID , s);
    }

    private Token toNumber(BufferedReader br){
        String s = "";
        do{
            s = s+ "" + peek;
            readch(br);
        }
        while(Character.isDigit(peek));
        
        if( (Character.isLetter(peek)) || peek == '_'){
            throw new IllegalArgumentException("Numero in prima posizione dell'identificatore: " );
        } 
        
        return new NumberTok(Tag.NUM , Integer.parseInt(s));
    }
		
    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "test.txt"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Token tok;
            do {
                tok = lex.lexical_scan(br);
                System.out.println("Scan: " + tok);
            } while (tok.tag != Tag.EOF);
            br.close();
        } catch (IOException e) {e.printStackTrace();}    
    }

}
