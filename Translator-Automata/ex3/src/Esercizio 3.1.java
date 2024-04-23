import java.io.*;
    public class Parser {
        private Lexer lex;
        private BufferedReader pbr;
        private Token look;




        //Variabili: Start , Expr, exprp, term, termp, fact
        //Terminali: +,-,*,/,(,), numero , EOF
        /*Produzioni: 
            Start -> Expr EOF           S-> E EOF
           
            expr -> term exprp          E-> term Exprp EOF
            exprp   -> + term exprp     term-> fact termp exprp eof
                    -> - term exprp     fact-> 2 termp exprp eof
                    -> epsilon          termp-> 2 epsilon exprp eof
                                        exprp-> 2 + term exprp eof
            term    -> fact termp       term-> 2 + fact termp exprp eof
            termp   -> * fact termp     fact -> 2 + 3 termp exprp eof
                    -> / fact termp     termp -> 2 + 3 * fact termp exprp eof
                    -> epsilon          fact -> 2 + 3 * 5 termp e exprp in epsilon-> 2 + 3 *5 
            
            fact    -> (expr) | numero
            */
        
            //Simbolo Iniziale : Start



/*
        Cerchiamo gli annullabili: 
        NULL:
        exprp - termp

        First:
        First(start) = First(expr EOF) = first(expr) = { '(' , numero} xk expr non è annullabile
        First(expr) = first(term exprp) = { '(' , numero}
        first(exprp) = first(+ termp exprp) U first(- termp exprp) U first(epsilon)= {+ , -}
        First(term) = first(fact termp) = first(fact) = { '(' , numero}
        first(termp) = first(* fact termp ) U first(/  fact termp  ) U first(epsilon) = {* , /}
        First(fact) = first( '(' expr ')') U first(numero) = {'(' , numero}

        USO i first per i casi accettati non annullabili, mi serviranno per gli switch, calcolo il Follow(Exprp) , follow(Termp) per i casi particolari
        di uscita da
 */
      


        public Parser(Lexer l, BufferedReader br) {
            lex = l;
            pbr = br;
            move();
        }
        /*Il metodo move serve dopo il riconoscimento di un token di un simbolo terminale, per scriverlo a schermo e passare al successivo*/
        void move() {
            look = lex.lexical_scan(pbr);
            System.out.println("token = " + look);
        }
        void error(String s) {
             throw new Error("near line " + lex.line + ": " + s);
        }
        /*IL metodo Match viene invocato quando si vuole verificare la presenza di un simbolo terminale in cima allo stack, 
         * Esso controlla la presenza del simbolo, se è presente questo simbolo, e non è uguale a EOF(perchè in tal caso finirebbe il programma) fa una move().
        */
        void match(int t) {
            if (look.tag == t) {
              if (look.tag != Tag.EOF) move();
            } 
            else error("syntax error");
        }
        
        public void start() { // S-> E EOF
                switch(look.tag){  //controllo del simbolo se appartiene all'insieme Guida(Start)
                    case '(':      //1 se il simbolo è un simbolo terminale del tipo ( oppure un numero, questo viene riconosciuto, e si entra nel caso di una expr
                    case Tag.NUM:
                            expr();
                            match(Tag.EOF);//quando arriviamo a questo punto l'espressione dovrebbe essere finita, dunque controlliamo la presenza dell'EOF
                            break;
                    default : 
                        error("Inizio dell'espressione con un simbolo non valido nel Start.");
                }
                    
            }
            private void expr() {
               switch(look.tag){
                    case '(': //un espressione può iniziare per ( oppure per numero
                    case Tag.NUM: //2 quando uno di questi viene riconosciuto si va in term e exprp : E-> term Exprp
                        term();
                        exprp();    //11 ora che sono in una situazione del tipo 3*4 +12, devo analizzare il +, non riconosciuto dalla classe termp
                        break;      //
                    default:
                        error("Simbolo non accettato da expr.");

               }
                             
            }
            private void exprp() {
                switch (look.tag) { //12 ora controllo il + 
                    case '+':
                        match('+');
                        term();     //13 dopo il + mi aspetto o un'altra espressione oppure un terminale numerico: vado in term (facciamo finta che ho il 12 di prima) seguito da EOF
                        exprp();    //term->fact->numero->term->termp->EOF->term break;-> exprp->EOF->break;->expr->break->start match(eof)->FINE
                        break;
                    
                    case '-':
                        match('-');
                        term();
                        exprp();
                        break;

                    case  ')':  //casi non riconosciuti
                    case Tag.EOF:                    
                        break;

                    default:
                        error("Simbolo diverso da +/- in exprp");
                }
            }
            private void term() {
                switch(look.tag){
                    case '(':   
                    case Tag.NUM:          //3 ora abbiamo che E = term exprp, di conseguenza è come se E potesse ora trasformarsi in una piccola    
                            fact();         //3 espressione di moltiplicazione e divisione, infatti term -> fact termp salto in fact
                            termp();        //6 ora che ho un numero controllo l'operazione successiva in termp, salto in termp
                            break;          //10 ho finito il lavoro, torno in expr
                    default : 
                        error("Inizio dell'espressione con un simbolo :" + look + " Non riconosciuto in term");
                }

            }
            private void termp() {
                switch (look.tag) {
                    case '*':
                        match('*'); 
                        fact();     //7 se è una moltiplicazione salto in fact in quanto ci aspettiamo un altro numero, andiamo al simbolo successivo dopo
                        termp();    //8 risaltando nello stesso metodo per controllare i casi del tipo 3*3 *4 comunque la seconda operazione
                        break;
                    
                    case '/':
                        match('/');
                        fact();
                        termp();
                        break;

                    case '+': //casi non riconosciuti
                    case '-':
                    case Tag.EOF:
                    case ')':
                        break;      //9 esco in questi casi tornando a term perchè poi, tornando ancora a expr(da dove siamo partiti) andremo in exprp

                    default:
                        error("Simbolo diverso da * / in termp");
                }
                
                }
            private void fact() {
                switch(look.tag){
                    case '(':           //4 controlliamo se il primo simbolo letto era una parentesi tonda
                        match('(');
                        expr();         //di conseguenza ne leggiamo l'espressione all'interno come se partissimo da start
                        match(')');    //se è avvenuta la lettura della parentesi tonda, controlliamo che siano state ben bilanciate con questo match
                        break;
                    case Tag.NUM:       //4 se invece arriviamo con il primo simbolo che è un numero, verifichiamo che sia un numero con match e andiamo al simbolo succ
                        match(Tag.NUM); //5 torno in term con un numero
                        break;
                }

                }
            
            public static void main(String[] args) {
                Lexer lex = new Lexer();
                String path = "test.txt"; // il percorso del file da leggere
                try {
                    BufferedReader br = new BufferedReader(new FileReader(path));
                    Parser parser = new Parser(lex, br);
                    parser.start();
                    System.out.println("Input OK");
                    br.close();
                    } catch (IOException e) {e.printStackTrace();}
            }
        }

