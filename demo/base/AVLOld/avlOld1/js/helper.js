// Author : Samuel T. C. Santos
// Helper to Compute APFD Values
// 
// Module Helper
// @see { http://samueltcsantos.github.io/priorj }

var Helper = (function (){
	var failures = [];
	var apdfEntries = [];
	var orderAMC = [];
	var orderASC = [];
	var orderCB = [];
	var orderRND = [];
	var orderTMC = [];
	var orderTSC = [];

	function failuresNumber(){
		return failures.length;
	}

	function faultsNumber(){
		return apfdEntries.length;
	}

	function computeAPFDValue(technique){
		var m = M();
		var n = N();
		var tfis = computeTFiValues(technique);

		APFD.setM(m);
		APFD.setN(n);
		APFD.setTfis(tfis);
		var value = APFD.computeValue();
		return value;
	}

	function computeTFiValues(technique){
		var tfis = [];
		apfdEntries.forEach(function(entry) {
		    tfis.push(minIndexIn(technique, entry.tests));
		});
		return tfis;
	}

	function serieTo(technique){
		var serie = [];
		var y = getIndexAxisY(technique);
		var x = getIndexAxisX(technique);
		
		if (y.length == x.length){
			for (var i=0; i<x.length;i++){
				serie.push([x[i],y[i]]);
			}
		}
		return serie;
	}

	function getIndexAxisY(){
	  n = M();
	  var pa = []; //arithmetical progression
	  if (n>0){
		  var r = parseInt(100 / n);
		  var term = 0;
		  pa.push(term);
		  for (var i=0; i<n; i++){
		     term += r;
		     pa.push(term);
		  }
	  }
	  if (n % 100 !== 0){
	  	pa.push(100);
	  }
	  return pa;
	}

	function getIndexAxisX(technique){
		var n = N();
		var points = [];
		var x = 0;
		points.push(0);
		if(n > 0){
			failures.forEach(function(failure){
				var index = indexIn(technique, failure);
				x = parseInt(index * 100 / n);
				if(!Util.contains(points, x)){
					points.push(x);
				}
			});
		}
		points.push(100);
		//sorting
		points.sort(increase);	
		return points;
	}

	var increase = function(a, b){
	  return a-b;
	}; 

	function M(){
		return Object.keys(apfdEntries).length;
	}

	function N(){
		var n=0;
		if(hasThisTechnique('amc')){
			n = Object.keys(orderAMC).length;
		}
		else if (hasThisTechnique('asc')){
			n = Object.keys(orderASC).length;
		}
		else if (hasThisTechnique('cb')){
			n = Object.keys(orderCB).length;	
		}
		else if (hasThisTechnique('rnd')){
			n = Object.keys(orderRND).length;	
		}
		else if (hasThisTechnique('tmc')){
			n = Object.keys(orderTMC).length;	
		}
		else if (hasThisTechnique('tsc')){
			n = Object.keys(orderTSC).length;	
		}
		return n;
	}

	function minIndexIn(technique, tests){
		var indexMin = indexIn(technique, tests[0]);
		for (var i=1; i < tests.length; i++){
			var currentIndex = indexIn(technique, tests[i]);
			if ( currentIndex < indexMin){
				indexMin = currentIndex;
			}
		}
		return indexMin;
	}

	function indexIn(technique, test){
		switch (technique){
			case 'amc':
				return orderAMC[test];
				break;
			case 'asc':
				return orderASC[test];
				break;
			case 'cb':
				return orderCB[test];
				break;
			case 'rnd':
				return orderRND[test];
				break;
			case 'tmc':
				return orderTMC[test];
				break;
			case 'tsc':
				return orderTSC[test];
				break;		
			default:
				return -1;
				break;
		}
	}

	function setOrderPrior(technique, order){
		switch(technique){
			case 'amc':
				orderAMC = order;
				break;
			case 'asc':
				orderASC = order;
				break;
			case 'cb':
				orderCB = order;
				break;
			case 'rnd':
				orderRND = order;
				break;
			case 'tmc':
				orderTMC = order;
				break;
			case 'tsc':
				orderTSC = order;
				break;
			default:
				break;
		}
	}

	function hasThisTechnique(technique){
		switch(technique){
			case 'amc':
				return Object.keys(orderAMC).length > 0;
				break;
			case 'asc':
				return Object.keys(orderASC).length > 0;
				break;
			case 'cb':
				return Object.keys(orderCB).length > 0;
				break; 
			case 'rnd':
				return Object.keys(orderRND).length > 0;
				break;
			case 'tmc':
				return Object.keys(orderTMC).length > 0;
				break;
			case 'tsc':
				return Object.keys(orderTSC).length > 0;
				break;
			default:
				return false;
				break;
		}
	}

	return {
		setFailures : function (failuresList){
			failures = failuresList;
		},
		setAPFDEntries : function (entries){
			apfdEntries = entries;
		},
		setOrder : function (technique, order){
			setOrderPrior(technique, order);
		},
		getFailuresNumber : function(){
			return failuresNumber();
		},
		getFaultsNumber : function (){
			return faultsNumber();
		},
		has : function (technique){
			return hasThisTechnique(technique);
		},
		getIndex : function(technique, test){
			return indexIn(technique,test);
		},
		getAPFDValue: function(technique) {
			return computeAPFDValue(technique);
		},
		minIndexFrom : function (technique, tests){
			return minIndexIn(technique,tests);
		},
		getTFis : function (technique){
			return computeTFiValues(technique);
		},
		getN : function(){
			return N();
		},
		getM : function (){
			return M();
		},
		getSerie : function (technique){
			return serieTo(technique);
		},
		getAxisX : function (technique){
			return getIndexAxisX(technique);
		},
		getAxisY : function(technique){
			return getIndexAxisY(technique);
		}
	};
})();

