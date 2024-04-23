import java.io.*;
public class Valutatore {
    private Lexer lex;
    private BufferedReader pbr;
    private Token look;
    
    public Valutatore(Lexer l, BufferedReader br) {
        lex = l;
        pbr = br;
        move();
    }
    
    void move() {
        look = lex.lexical_scan(pbr);
        System.out.println("token = " + look);
    }

    void error(String s) {
        throw new Error("near line " + lex.line + ": " + s);
    }

    void match(int t) {
        if (look.tag == t) {    
             move(); 
        }
        else {
             error("syntax error");
        }
    }

    public void start() {

        int expr_val;
        switch(look.tag){
            case '(': 
            case Tag.NUM:
                expr_val = expr();
                match(Tag.EOF);
                System.out.println(expr_val);
                break;
            default : 
                error("Inizio dell'espressione con un simbolo non valido nel Start.");
        }
    }

    private int expr() {

        int term_val, exprp_val; 
        switch(look.tag){
            case '(': 
            case Tag.NUM:      
                term_val = term();  
                exprp_val = exprp(term_val);
                return exprp_val;          
            case Tag.EOF:  
                return 0;   //come in termp metto a 0 al momento poi vedo se ci sono bug
            default:
                error("Simbolo non accettato da expr.");         
        }
        return 0;
    }

    private int exprp(int exprp_i) {
       

        int term_val, exprp_val;
        switch (look.tag) {  
            case '+':
                match('+');
               // term(); 
                term_val = term();    
                exprp(term_val);
                exprp_val = exprp(exprp_i + term_val);    
                return exprp_val;
            case '-':
                match('-');
                term();
                term_val = term();
                exprp(term_val);
                exprp_val = exprp(exprp_i - term_val);
                return exprp_val;
            case  ')':  //casi non riconosciuti
            case Tag.EOF:
                return exprp_i;
            default:
                error("Simbolo diverso da +/- in exprp");
        }
        return 0;
    }

    private int term() {

        int fact_val , termp_val ;
        switch(look.tag){
            case '(':
            case Tag.NUM:
                fact_val = fact();
                termp_val = termp(fact_val);
                return termp_val;
            case Tag.EOF:
                break;
            default:
            error("Inizio dell'espressione con un simbolo :" + look + " Non riconosciuto in term");    
        }
        return 0;
    }

    private int termp(int termp_i) {

       
        int fact_val , termp_val;
        switch (look.tag) {
            case '*':
                match('*'); 
                fact_val = fact();     
                termp(fact_val);
                termp_val = termp( termp_i * fact_val );   
                return termp_val;
            
            case '/':     
                match('/'); //quando si fa una divisione se il carattere è incollato al segno di diviso succedono problemi perchè avvengono 2 move al posto di uno solo.
                fact_val = fact();
                termp(fact_val);
                termp_val = termp(termp_i / fact_val);
                return termp_val;
            case '+':    
                match('+');
                fact_val = fact();
  
                termp(fact_val);

                termp_val = termp(termp_i + fact_val);

                return termp_val;  
                  
            case '-':        
                match('-');
                fact_val = fact();
                termp(fact_val);
                termp_val = termp(termp_i - fact_val);
                return termp_val; 
            case Tag.EOF:
         
                termp_val = termp_i;
        
                return termp_val;
                
            case ')':
         
           match(')');
               
                return termp_i;     

            default:
                error("Simbolo diverso da * / in termp" + look);
        }
        return 0;
    }

    private int fact() {
  

        int fact_val ;
        switch (look.tag) {
            case '(':          
                move();
                fact_val = expr();
                //match(')'); 
                return fact_val;         
            case Tag.NUM:             
                fact_val = ((NumberTok)look).value;
                //match(Tag.NUM);
                move();
                break;
            default:
                fact_val = 0;
                error("Simbolo non accettato da fact" + look);  
        }
        return fact_val;
    }

    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "test.txt"; // il percorso del file da leggere
        try {
        BufferedReader br = new BufferedReader(new FileReader(path));
        Valutatore valutatore = new Valutatore(lex, br);
        valutatore.start();
        br.close();
        } catch (IOException e) {e.printStackTrace();}
    }
}
