
public class MyInteger extends MyNumber<MyInteger>{
	MyInteger(Integer b) {
		a = b;
	}
	
	Integer a;

	Integer getValue() {
		return a;
	}
	
	
	protected MyInteger divide(MyInteger b) throws ZeroException{
		if (b.getValue() == 0) {
			throw new ZeroException("Division by zero");
		}
		return new MyInteger(a / b.getValue());
	}
	protected MyInteger abs() {
		return new MyInteger(Math.abs(a));
	}
	protected MyInteger add(MyInteger b) {
		return new MyInteger(a + b.getValue());
	}
	protected MyInteger log() throws LogException{
		if (a <= 0) {
			throw new LogException("Incorrect argument in Log");
		}
		return new MyInteger((int)(Math.floor(Math.log(a) / Math.log(2)))); 
	}
	protected MyInteger minus() {
		return new MyInteger(-a);
	}
	protected MyInteger mul(MyInteger b) {
		return new MyInteger(a * b.getValue());
	}
	protected MyInteger not() {
		return new MyInteger(~a);
	}
	protected MyInteger pow(MyInteger b) throws Exception3{
		if (b.getValue() < 0) {
    		throw new PowerException("Incorrect argument in Power");
    	}
    	long res = 1;
    	long aa = (long) a;
    	long bb = (long) b.getValue();
    	while (bb > 0) {
    		
    		if (bb % 2 == 1) {
    			res *= aa;
    		}
    		aa *= aa;
    		bb >>= 1;
    	}
    	return new MyInteger((int) res);
	}
	protected MyInteger sub(MyInteger b) {
		return new MyInteger(a - b.getValue());
	}
	
	protected MyInteger parse(String s) {
		return new MyInteger(Integer.parseInt(s));
	}
	
}
