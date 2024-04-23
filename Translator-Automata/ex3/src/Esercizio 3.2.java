import java.io.*;
    public class Parser2{
        private Lexer lex;
        private BufferedReader pbr;
        private Token look;


        public Parser2(Lexer l, BufferedReader br) {
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
          if (look.tag != Tag.EOF) move();
        } 
        else error("syntax error");
        }   

        /*
         * Variabili: prog , statlist , statlistp , stat , idlist, idlistp, optlist , optlistp , 
         *            optitem , bexpr , expr , exprlist , exprlistp
         * Terminali tolti col match : eof
         */


        public void prog(){
            switch(look.tag){
                case Tag.ASSIGN:
                case Tag.PRINT:
                case Tag.READ:
                case Tag.WHILE:
                case Tag.COND:
                case '{':
                    statlist();
                    match(Tag.EOF);
                    break;
                default: 
                    error("Inizio del programma con una parola non accettata: " + look);
            }
        }
        private void statlist(){
            switch(look.tag){
                case Tag.ASSIGN:
                case Tag.PRINT:
                case Tag.READ:
                case Tag.WHILE:
                case Tag.COND:
                case '{':
                    stat();
                    statlistp();
                    break;
                default: 
                    error("Statlist non accetta: " + look);
            }
        }
        private void statlistp(){
            switch(look.tag){
                case ';':
                    match(';');
                    stat();
                    statlistp();
                    break;
                case '}':
                case Tag.EOF:
                    break;
                case '[':
                    match('[');
                    exprlist();          
                    match(']');
                    break;
                default:
                    error("Statlistp non accetta: " + look);
            }
        }
        private void stat(){
            switch(look.tag){
            case Tag.ASSIGN:
                match(Tag.ASSIGN);
                expr();
                match(Tag.TO);
                idlist();
                break;
            case Tag.PRINT:
                match(Tag.PRINT);
                match('[');
                exprlist();
                match(']');
                break;
            case Tag.READ:
                match(Tag.READ);
                match('[');
                idlist();
                match(']');
                break;
            case Tag.WHILE:
                match(Tag.WHILE);
                match('(');
                bexpr();
                match(')');
                if (look.tag == '{') {
                match('{');
                statlist();
                match('}');
                } else {
                stat();
                }
                stat1();
                break;
            case Tag.COND:
                match(Tag.COND);
                match('[');
                optlist();  
                match(']');     
                stat1();    
                break;
            case '{':
                match('{');
                stat();
                match('}');
                break;
            default:
                error("Stat non riconosce questo simbolo, solo operazioni: " + look);
            }
        }
        private void stat1(){
            switch(look.tag){
                case Tag.ELSE:
                    match(Tag.ELSE);
                    stat();
                case Tag.END:
                    match(Tag.END);
                    break;
            }
        }
        private void idlist(){
            switch(look.tag){
                case Tag.ID:
                    match(Tag.ID);
                    idlistp();
                    break;
                default:
                    error("Non viene accettato in idlist" + look);
            }
        }
        private void idlistp(){
            switch(look.tag){
                case ',':
                    match(',');
                    match(Tag.ID);
                    idlistp();
                    break;
                case '}':
                    match('}');
                    break;
                case ']':
                    match(']');
                    break;
                case Tag.OPTION:
                case Tag.END:
                case Tag.EOF:
                    break;
                case ';':
                    match(';');
                    break;
                case Tag.PRINT:
                    match(Tag.PRINT);  
                    break;
                default:
                    error("Simbolo non accettato in idlistp: " + look);
            }
        }
        private void optlist(){
            switch(look.tag){
                case Tag.OPTION:
                    optitem();
                    optlistp();
                    break;
                default:
                    error("Simbolo non riconosciuto in optlist" + look);
            }
        }
        private void optlistp(){
            switch(look.tag){
                case Tag.OPTION:
                    optitem();
                    optlistp();
                    break;
                case ']':
                    break; 
                default:
                    error("Simbolo non accettato in optlistp:" + look);
            }
        }
        private void optitem(){
            switch(look.tag){
                case Tag.OPTION:
                    match(Tag.OPTION);
                    match('(');
                    bexpr();
                    match(')');
                    match(Tag.DO);
                    stat();
                    break;
                default:
                    error("Simbolo non accettato in optitem : " + look);
            }
        }
        private void bexpr(){
            switch(look.tag){
                case Tag.RELOP:
                    match(Tag.RELOP);
                    expr();
                    expr();
                    break;
                default:
                    error("Simbolo non accettato in bexpr: " + look);
            }
        }
        private void expr(){
            switch(look.tag){
                case '+':
                    match('+');
                    match('(');
                    exprlist();
                    match(')');
                    break;
                case '*':
                    match('*');
                    match('(');
                    exprlist();
                    match(')');
                    break;
                case '-':
                    match('-');
                    expr();
                    expr();
                    break;
                case '/':
                    match('/');
                    expr();
                    expr();
                    break;
                case Tag.NUM:
                    match(Tag.NUM);
                    break;
                case Tag.ID:
                    match(Tag.ID);
                    break;
                default:
                    error("Simbolo non riconosciuto in stat: " + look);
            }
        }
        private void exprlist(){
            switch(look.tag){
                case '+':
                case '*':
                case '-':
                case '/':
                case Tag.NUM:
                case Tag.ID:
                    expr();
                    exprlistp();
                    break;
                default:
                    error("Simbolo non accettato in exprlist: " + look);
            }
        }
        private void exprlistp(){
            switch(look.tag){
                case ',':
                    match(',');
                    expr();
                    exprlistp();
                    break;
                case ')':
                case ']':
                    break;
                default:
                    error("SImbolo non riconosciuto in exprlistp: " + look);
            }
        }





    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "test.txt"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Parser2 parser = new Parser2(lex, br);
            parser.prog();
            System.out.println("Input OK");
            br.close();
            } catch (IOException e) {e.printStackTrace();}
    }

    }
