public class Es{
    public static void scan(String s){
        int i = 0;
        int state = 0;
        while(state >= 0 && i<s.length()){
            final char ch = s.charAt(i++);

            switch(state){
                case 0: 
                    if(Character.getNumericValue(ch)%2!=0)
                        state = 1;//q1
                    if(ch==' ')
                        state = 4;

                    if(ch>='A' && ch<='K')
                        state = 2;//q2 stat fin
                    break;
                case 1: 
                    if(Character.getNumericValue(ch)%2==0)
                        state = 0;//q0
                    if(ch==' ')
                        state = 5;
                    if(ch>='L' && ch<='Z')
                        state = 3;//q3 stat fin
                    break;
                
                case 4://matricola pari
                    if(ch>0 && ch<9){
                        state = -1;
                    }
                    else if(ch>='A' && ch<='K') // controllo se t2
                        state = 2;//q2 stat fin
                    
                    break;
                case 5://matricola dispari
                    if(ch>0 && ch<9){
                        state = -1;
                    }
                    else if(ch>='L' && ch<='Z')state = 3;//controllo se t3
                break;
            }
        }
        if(state==2){
            System.out.println(  "E' un T2");
        }
        else if(state==3){
            System.out.println(  "E' un T3");
        }
        else System.out.println(  "E' o un t1 o un t4");
            
    }
    public static void main(String[] args){
        scan("21431 H");
    }
}
