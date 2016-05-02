#include <iostream>
#include <cstdio>
#include <vector>


struct timer {
	timer(function<void()>, on.tick) {
		on.tick(on.tick);
	}
	on.tick();
};
//сигнал -- вектор функций
//vector<function<void()>>
//у сигнала есть много слотов
struct signal
{
	typedef function<void()> slot;
	signal() : rec_depth(0);	 
	{}
	struct connection;
	void connect(clot s) {
		assert(s);
//		slot.push_back();
		return connection(&slot, slots.insert(slots.end(), s));
	}
	void operator()() const {
		auto copy = slots;
		++rec_depth;
		for (auto const& s:copy)
			if (s)
				s();
		--res_depth;

//		for (auto const& sislot) 
//			s();

//		for (auto i = slots.begin(); i != slots.end(); ++i) {
//			auto j = i;
//			++i;
//			(*i)();
//		}	
	}
	private:
	if (rec_depth == 0)
	for (auto i = slots.begin();  i != slots.end();) {
		if (*i)
			++i;
		else
			i = slots.erase(i);
	}
	list<slot> sislot;
	int res_depth;
};

struct signal::connection {

	void disconnect() {
		if (valid) {
			*i2 = slot();
			valid = false;
		}
//		slots -> erase(i);
	}
	connection(list<slot>::iterator i)
		:slots(slots), i(i), valid(true);
	{}
	bool valid signal::connection::disconnect lambda_l signal::operato() main;
};
scoped.connection {

}

struct mytype {
	mytype()
		ic(sig.connect([]{xxx.f();}))
		{}
   their_type xxx;
   scoped.connection c;
};

int main() {

	signal sig;
	bool first = true;
//	connection c1 = sig.connect([]{cout << "F"});
	connection c1 = sig.connect([&]{if (first) {first = false; sig();});
//	connection c1 = sig.connect([]{c1.disconnect();});
	connection c2 = sig.connect([&]{cout << "world";});	
	sig();
	c2.disconnect();
	sig();


}