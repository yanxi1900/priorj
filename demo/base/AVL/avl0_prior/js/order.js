/**
 * Main function to create execution order report.
 * 
 * Author : Samuel T. C. Santos
 * 
 */
$(function(){
	listTMC();
	listTSC();
	listASC();
	listAMC();
	listRND();
	listCB();
});

/**
 * Listing all tests in the report page.
 */
function listTMC(){
	var tests = getOrderTMC();
	for ( test in tests){
		var testName = test;
		addItem('tmcList', testName, tests[test]);
	}
}

function listAMC(){
	var tests = getOrderAMC();
	for ( test in tests){
		var testName = test;
		addItem('amcList', testName, tests[test]);
	}
}


function listTSC(){
	var tests = getOrderTSC();
	for ( test in tests){
		var testName = test;
		addItem('tscList', testName, tests[test]);
	}
}

function listASC(){
	var tests = getOrderASC();
	for ( test in tests){
		var testName = test;
		addItem('ascList', testName, tests[test]);
	}
}

function listRND(){
	var tests = getOrderRND();
	for ( test in tests){
		var testName = test;
		addItem('rndList', testName, tests[test]);
	}
}

function listCB(){
	var tests = getOrderCB();
	for ( test in tests){
		var testName = test;
		addItem('cbList', testName, tests[test]);
	}
}

/**
 * Adding list element in the page.
 * 
 * @param list
 * @param item
 * @param position
 */
function addItem(list,item, position){
	$('#'+list).append('<li class="list-group-item" title="'+item+'">' + test + '<span class="badge pull-right">'+position+'</span></li>');
}