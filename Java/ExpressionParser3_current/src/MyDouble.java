
public class MyDouble extends MyNumber<MyDouble>{
	MyDouble(Double b) {
		a = b;
	}
	Double a;
	
	Double getValue() {
		return a;
	}
	
	
	protected MyDouble divide(MyDouble b) {
	//	if (b.getValue() == 0) {
		//	throw new ZeroException("Division by zero");
	//	}
		return new MyDouble(a / b.getValue());
	}
	protected MyDouble abs() {
		return new MyDouble(Math.abs(a));
	}
	protected MyDouble add(MyDouble b) {
		return new MyDouble(a + b.getValue());
	}
	protected MyDouble log() throws LogException{
		if (a <= 0) {
			throw new LogException("Incorrect argument in Log");
		}
		return new MyDouble((double)(Math.floor(Math.log(a) / Math.log(2)))); 
	}
	protected MyDouble minus() {
		return new MyDouble(-a);
	}
	protected MyDouble mul(MyDouble b) {
		return new MyDouble(a * b.getValue());
	}
	protected MyDouble not() {
		return new MyDouble(a);
	}
	protected MyDouble pow(MyDouble b) throws Exception3{
		if (b.getValue() < 0) {
    		throw new PowerException("Incorrect argument in Power");
    	}
    	double res = 1;
    	double aa = (double) a;
    	double bb = (double) b.getValue();
    	while (bb > 0) {
    		
    		if (bb % 2 == 1) {
    			res *= aa;
    		}
    		aa *= aa;
    		bb /= 2;
    	}
    	return new MyDouble((double) res);
	}
	
	protected MyDouble sub(MyDouble b) {
		return new MyDouble(a - b.getValue());
	}
	
	protected MyDouble parse(String s) {
		return new MyDouble(Double.parseDouble(s));
	}
}
