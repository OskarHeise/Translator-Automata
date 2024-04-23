public class Es{
    public static boolean scan(String s){
        int i = 0;
        int state = 0;
        while(state >= 0 && i<s.length()){
            final char ch = s.charAt(i++);

            switch(state){
                case 0:
                    if(ch == '+' || ch == '-')  state = 1;
                    else if(Character.isDigit(ch)) state = 1;
                    else if(ch == '.') state = 3;
                    else state = -1;
                    break;
                case 1:
                    if(Character.isDigit(ch))  state = 1;
                    else if(ch == 'e') state = 2;
                    else if(ch == '.') state = 2;
                    else state = -1;
                    break;
                case 2:
                    if(Character.isDigit(ch))  state = 2;
                    else if(ch == '.') state = 3;
                    else state = -1;
                    break;
                case 3:
                    if(Character.isDigit(ch))  state = 4;
                    else state = -1;
                case 4:
                    if(Character.isDigit(ch))  state = 4;
                    else state = -1;
            }
    }
    return (state != -1);
}
    public static void main(String[] args) {
      System.out.println(scan("e4")? "OK" : "NOPE");
  }
}
