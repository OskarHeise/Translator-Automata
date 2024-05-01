public class Es{
    public static boolean scan(String s){
        int i = 0;
        int state = 0;
            while (state>=0  && i<s.length()){
                final char ch = s.charAt(i++);
                switch(state){
                    case 0:
                        if(Character.isDigit(ch))
                            state = -1;
                        else if(ch=='_')
                            state = 5;
                        else if((ch>'A' && ch<'Z') || (ch>'a'&&ch<'z'))
                           state = 1;
                        break;
                    case 5:
                        if(Character.isDigit(ch)||(ch>'A' && ch<'Z') || (ch>'a'&&ch<'z'))
                            state = 1;
                        break;
                }

        } 
        System.out.println(state);
        return state == 1;
    }
    public static void main(String[] args){
        System.out.println(scan("_____2") ? "OK" : "NOPE");
        

    }
}
