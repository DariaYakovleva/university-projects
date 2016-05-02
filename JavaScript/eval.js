cnst = function(a) {
    return function() {
      return a;
    }
}
var variables = ['x', 'y', 'z'];
function variable(a) {
    return function() {
	for (var i = 0; i < arguments.length; i++) {
	    if (a == variables[i]) {
	        return arguments[i];
	    }
	}
    }
}
function getAns(f) {
    return function() {
        var args = arguments;	
    	return function() {
            var args2 = [];
	    for (var j = 0; j < args.length; j++) {
                args2.push(args[j].apply(null, arguments));
  	    };
            return f.apply(null, args2);
        } 
    }
}
var add = getAns(function(a, b) {return a + b;});
var subtract = getAns(function(a, b) {return a - b;});
var multiply = getAns(function(a, b) {return a * b;});
var divide = getAns(function(a, b) {return a / b;});
var abs = getAns(Math.abs);
var log = getAns(Math.log);
function pi() { return Math.PI };
var sin = getAns(Math.sin);
var map = { '+': [add, 2], '-': [subtract, 2], '*': [multiply, 2], '/' : [divide, 2], 
'abs' : [abs, 1], 'log' : [log, 1] }
var stack = [];
function make() {
    var args = [];
    var tmp = stack.pop();
    for (var i = 1; i < arguments[1]; i++) {
		args.push(stack.pop());
    }
    args.push(tmp);
    stack.push(arguments[0].apply(null, args));  
}
function parse(s) {
    stack = [];
    var arg = s.split(" ");
    for (var i = 0; i < arg.length; i++) {
	if (map[arg[i]] != undefined) {
            make(map[arg[i]][0], map[arg[i]][1]);
	} else if (variables.indexOf(arg[i]) != -1) {
      	    stack.push(variable(arg[i]));
 	} else {
	    stack.push(cnst(Number(arg[i])));
        }
    }
    return stack.pop();
}

println('hello');
var test2 = parse("2093101096 1943850576 /")(0,0,0);
println("test "+test2);


