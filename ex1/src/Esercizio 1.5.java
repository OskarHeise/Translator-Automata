public class Es{
    public static void scan(String s){
        int i = 0;
        int state = 0;
        while(state >= 0 && i<s.length()){
            final char ch = s.charAt(i++);

            switch(state){
                case 0:
                    if(ch>='A' && ch<='K')
                        state = 2;
                    if(ch>='L' && ch<='Z')
                        state = 1;
                break;

                case 1:
                    if(Character.getNumericValue(ch)%2!=0) state = 3;
                    else if(Character.getNumericValue(ch)%2==0) state = 8;
                break;

                case 2:
                    if(Character.getNumericValue(ch)%2==0) state = 4;
                    else if(Character.getNumericValue(ch)%2!=0) state = 9;
                break;

                case 3: 
                    if(Character.getNumericValue(ch)%2==0) state = 8;
                break;

                case 8:
                    if(Character.getNumericValue(ch)%2!=0) state = 3;
                    break;
                
                case 4:
                if(Character.getNumericValue(ch)%2!=0) state = 9;
                    break;
                case 9:
                if(Character.getNumericValue(ch)%2==0) state = 4;
                break;
            }
    }

    if(state == 4)
        System.out.println("Sei un T2, il cognome è tra A e K e l'ultima cifra della matricola è pari");
    else if(state ==3)
        System.out.println("Sei un T3, il cognome è tra L e Z e l'ultima cifra della matricola è dispari");
    
    else System.out.println("Sei o un T1 o un T4");
}
public static void main(String[] args){
    scan("Ada r 983 d872");
}

}
