public class Es6{
    public static boolean scan(String s){
        int i = 0;
        int state = 0;
        while(state >= 0 && i<s.length()){
            final char ch = s.charAt(i++);

            switch(state){
                case 0:
                    if(ch =='a')    state = 1;
                    break;
                case 1:
                    if(ch=='b')  state=2;
                    break;
                case 2:
                    if(ch =='b')    state =3;
                    
                    else state =1;
                    break;
                case 3:
                    if(ch =='b')    state = 0;
                    else state = 1;
                    break;
            }
    }
    return (state==1 ||state == 2||state ==3);
}
    public static void main(String[] args) {
      System.out.println(scan("abb")? "OK" : "NOPE");
  }
}
