import java.math.BigInteger;


public class MyBigInteger extends MyNumber<MyBigInteger>{
	MyBigInteger(BigInteger b) {
		a = b;
	}
	BigInteger a;
	
	BigInteger getValue() {
		return a;
	}
	protected MyBigInteger divide(MyBigInteger b) throws ZeroException {
		if (b.getValue() == BigInteger.valueOf(0)) {
			throw new ZeroException("Division by zero");
		}
		return new MyBigInteger(a.divide(b.getValue()));
	}
	protected MyBigInteger abs() {
		return new MyBigInteger(a.abs());
	}
	protected MyBigInteger add(MyBigInteger b) {
		return new MyBigInteger(a.add(b.getValue()));
	}
	protected MyBigInteger log() throws LogException{
		if (a.intValue() <= 0) {
			throw new LogException("Incorrect argument in Log");
		}
		return new MyBigInteger(BigInteger.ZERO); 
	}
	protected MyBigInteger minus() {
		return new MyBigInteger(BigInteger.valueOf(0).subtract(a));
	}
	protected MyBigInteger mul(MyBigInteger b) {
		return new MyBigInteger(a.multiply(b.getValue()));
	}
	protected MyBigInteger not() {
		return new MyBigInteger(BigInteger.ZERO);
	}
	protected MyBigInteger pow(MyBigInteger b) throws Exception3{
    	MyBigInteger res = new MyBigInteger(BigInteger.ONE);
    	MyBigInteger aa = new MyBigInteger(a);
    	MyBigInteger bb = b;
    	while (bb.getValue().longValue() > 0) {
    	
    		if (bb.getValue().mod(BigInteger.valueOf(2)).equals(BigInteger.ONE)) {
    			res.getValue().multiply(aa.getValue());
    		}
    		aa.getValue().multiply(aa.getValue());
    		bb.divide(new MyBigInteger(BigInteger.valueOf(2)));
    	}
    	return res;
	}
	protected MyBigInteger sub(MyBigInteger b) {
		return new MyBigInteger(a.subtract(b.getValue()));
	}
}
