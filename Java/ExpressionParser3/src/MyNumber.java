public abstract class MyNumber<T> {
	
	protected abstract T divide(T b) throws ZeroException;
	protected abstract T abs();
	protected abstract T add(T b);
	protected abstract T log() throws LogException;
	protected abstract T minus();
	protected abstract T mul(T b);
	protected abstract T not();
	protected abstract T pow(T b) throws Exception3;
	protected abstract T sub(T b);
	protected abstract T function();
	//abstract T getValue();
	
}
