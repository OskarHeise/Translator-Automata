public class Es{
    public static boolean scan(String s){
        int i = 0;
        int state = 0;
        String nome = "Simona";
        while(state >= 0 && i<s.length()){
            
            final char ch = s.charAt(i);
            final char ch1 = nome.charAt(i);
            i++;

            switch(state){
                case 0:
                    if(ch == ch1)  state = 0;
                    else state = 1;
                    break;
                case 1:
                    if(ch == ch1)  state = 1;
                    else state = 2;
                    break;
            }
    }
    return (state == 0 || state == 1);
}
    public static void main(String[] args) {
      System.out.println(scan("Samona")? "OK" : "NOPE");
  }
}
