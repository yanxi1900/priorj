/**
* Author : Samuel T. C. Santos
* APFD Calculator
*/
// @see { http://samueltcsantos.github.io/priorj }

var APFD = (function (){

	var n; // the total number of test cases
	var m; // the number of faults contained in the program
	var tfi = []; // the positions of first test cases that exposes fault i

	function computeAPFD(){
		var value = 1 - sumTfis()/(n*m) + 1/(2*n);
		return value.toFixed(2);
	}

	function sumTfis(){
		var total = 0;
		for (var i=0; i<tfi.length; i++){
			total += parseInt(tfi[i]);
		}
		return total;
	}

	return {
		setM : function (faults){
			m = faults;
		},
		setN : function (tests){
			n = tests;
		},
		setTfis : function(tfs){
			tfi = tfs;
		},
		addTfi: function (index){
			tfi.push(index);
		},
		computeValue : function (){
			return computeAPFD();
		}
	};
})();

/** Testing this Module

APFD.setM(10);
APFD.setN(10);
APFD.addTfi(1);
APFD.addTfi(4);
APFD.addTfi(2);
APFD.addTfi(7);
APFD.addTfi(2);
APFD.addTfi(4);
APFD.addTfi(5);
APFD.addTfi(3);
APFD.addTfi(6);
APFD.addTfi(1);

console.log(APFD.computeValue());
*/