/**
 * @author Robert Wilk
 * CSc 135
 * Spring 2015
 * Assignment 4 - Recursive Descent Recognizer
 * May 13, 2015 
 */
var input   = "";
var eos		= '$';
var result	= true;
var index	= 0;
var reason 	= "";

function parse(inString) {
	input = inString;
	result = true;
	index = 0;
	(function exp () {
		(function term () {
			(function factor () { 
				if (current() == '(') {
					match(current());
					exp();
					match(')');
				} else {
					(function digit () { 
						var digits 	= "0123".split('');
						if (eoi()) {
							result = false;
						} else if (digits.indexOf(current()) > - 1) {
							result = result && match(current());
							if(digits.indexOf(current()) > -1) {
								result = false;
							}
						} else {
							result = false;
						}
					})();
				}
			})();
			if (current() == '*' || current() == '/') {
				match(current());
				term();
			}
		})();
		if (current() == '+' || current() == '-') {
			match(current());
			exp();
		}
	})();
	if (eoi()) {
		result = false;
	}
	report();
}

function match(token) {
	if (index < input.length &&
			current() == token) {
		index += 1;
		return true;
	} else {
		return false;
	}
}

function eoi() {
	var done = index >= input.length || input.length == 0;
	if (index == input.length - 1) {
		if (current == eos) {
			done = true;
		}
	}
	return done;
}

function current() {
	return input.charAt(index);
}

function report() {
	var state, end;
	if (result) {
		state = "LEGAL: ";
		end = " was ";
	} else {
		state = "ERROR: ";
		end = " was not ";
	}
	alert(state + input.substring(0, input.length - 1) + end + "accepted by the parser!");
}
