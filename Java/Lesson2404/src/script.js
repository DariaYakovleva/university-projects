function dump() {
	for (var i = 0; i < argument.length; i++) {
		println(arguments[i]);
		}
}

dump(1.2, "hello", 1);
//все аргументы помещаются в массив аргументов
//

function printf() {
	var format = arguments[0];
	var j = 1;
	for (var i = 0; i < format.length; i++) {
		if (format[i] == "%") {
			switch (format[i + 1]) {
				case "d" :
					print(Number(arguments[j++]));
					break;
				case "s":
					print("" + arguments[j++]);
					break;
			}
			i += 1;
		} else {
			print(format[i]); 
		}
	}	
}

printf("%d+%d=%d, 11, 2, 1 + 2);
printf("%d+%d=%s, 11, 2, "1 + 2");



function add() {
	return arguments[0] + arguments[1];
}


function add(a, b) {
	println(typeof(a));
	println(typeof(b));
	println("!!!" + (a + b));

	return a + b;
}

var add = function(a, b) {
	return a + b;
}
var add2 = add; //OK

typeof(add2)


function sort(comparation, a) {
	for (var i = 0; i < a.length; i++) {
		var minJ = i;
		for (var j = i; j < a.length; j++) {
			if (comparator(a[minJ], a[j]) > 0) {
				minJ = j;
			}
		}
		var t = a[i];
		a[i] = a[minJ];
		a[minJ] = t;
	}
	return a;
}
var a = [40, 20, 10, 30];
println(sort(a));
println(a);


//interface comparation<T> {
//	int compare(T a, T b);
//}

var absCompare = function(a, b) {
	return Math.abs(a) - Math.abs(b);

}

println(sort(absCompare, a));



var arrayCompare = function(comparator) {
return function(a, b) {
	for (var i = 0; i < a.ltngth; i++) {
		if (comparator(a[i], b[i]) < 0) {
			return -1;
			} else if (a[i] > b[i]) {
				return 1;
			}
	return a.length - b.length;
}
println(csort(arrayCompare, [[1,2],[-20, 10], [30, -40]]));

var diff = function(dx) {
	return function(f) {
	return function(x) {
		return (f(x + dx) - f(x)) / dx;
	}
}

var carry = function(f) {
	return function(a) {
		return function(b) {
			return f(a, b); 
		}
	}
}


var mCarry = function(f) {
	return function(a) {
		return function() {
			var newArguments = //[a] + arguments;
			return f.apply(null, newArguments); 
		}
	}
}

for (var i = 0; i < 10; i++) {
	//println(i + Math.cos(i) + " " diff(0.001, Math.cos(i)));
	println(i + Math.cos(i) + " " diff(0.001) (Math.cos(i)) (i) );
}


var add = curry(function(a, b) {return a + b});
var zzz = add(10);

//t . g (n) = f(g(n)))
//(n1 -> n2, n -> n1) -> (n -> n2)

var compose = function(f, g) [
	return function(x) {
		return f(g(x));
	}	
}

var c = compose(add(10), arr(20));
