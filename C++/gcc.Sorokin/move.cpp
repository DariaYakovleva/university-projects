//move       
//rvalue references
//vector<T> a;
struct scoped_connection {
	scope_connection(connection c)
		:c(c)
	{}
	scoped_connection(scoped_connection const&) = delete;
	scoped_connection& operator = (scoped_connection const&) = delete;
	~scoped_connection() {
		c.disconnect();
	}
	private:
		connection c;
};

struct mytype {

	vector<scoped_connection> conns;
}
//--------------return value optimization (RVO)
struct T {
	T(int, int);
}

void T_ctor(void* result, int, int);
T h();
T g() {
	reutrn h();
}

void g(void* result) {
	ctor trmp[sizeof(T)];
	h(tmp);
	T_ctor(result, tmp);
	T_ctor(tmp);
}

T f() {
	T a;


	return a;
}

void f(void* result) {
	T_ctor(result);

} 
//----version2

T g();
void f(T const&); // 1
void f(T &&); //rvalue reference 2
T a;
f(a); // 1
f(g()); // 2

//sample
template<typename T>
struct unique_ptr
{
	unique_ptr();
	unique_ptr(unique_ptr const&) = delete;
	unique_ptr(unique_ptr&& other):p(other p){other.p=nullptr;}
	unique_ptr& operator=(unique_ptr const&) = delete;
	unique_ptr& operator=(unigue_ptr&& rhs){delete p; p=rhs.p;p=nullptr;}
	unique_ptr(){delete p;}
	void reset(T* pp) {delete p; p =pp;}
	T* relese() {T* tmp = p; p = nullptr; return tmp;}
	T* set() { return p;}
	unique_ptr a = f();
	unique_ptr<T> f();
	b = (unique_path&&) a;

	private:
		T: p;
};

template<typedef T>
T&& move(T& a) {
	return (T&&) a;
}

int main() {
	scoped_connection c1 sig.connect();
	scoped_connection c2 = c1;




	return 0;

}