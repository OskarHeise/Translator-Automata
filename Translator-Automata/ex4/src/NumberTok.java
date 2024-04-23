public class NumberTok extends Token {
	public int value = 0;

    public NumberTok(int tag, int number){
        super(tag);
        value = number;
    }
    public String toString(){
        return "<" + tag + "," + value + ">";
    }
    public static final NumberTok number(int i){
        return new NumberTok(Tag.NUM, i);
    }
}
