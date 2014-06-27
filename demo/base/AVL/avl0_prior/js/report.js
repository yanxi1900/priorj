var coverage = [];

var coverageGlobal = {
	suites: 6,
	testcases: 6,
	classes: 2,
	methods: 25,
	statements: 118
};

$(function(){
	loadData();
	fillTable();
});

function loadData(){
	coverage.push({
		testsuite  : 'tests.AVLTest',
		testcase   : 'testPesquisaCondicoesLimite',
		classes    : 2,
		methods    : 18,
		statements : 78
	});
	coverage.push({
		testsuite  : 'tests.AVLTest',
		testcase   : 'testEstadoInicial',
		classes    : 1,
		methods    : 6,
		statements : 8
	});
	coverage.push({
		testsuite  : 'tests.AVLTest',
		testcase   : 'testPesquisa',
		classes    : 2,
		methods    : 18,
		statements : 79
	});
	coverage.push({
		testsuite  : 'tests.AVLTest',
		testcase   : 'testAltura',
		classes    : 2,
		methods    : 20,
		statements : 82
	});
	coverage.push({
		testsuite  : 'tests.AVLTest',
		testcase   : 'testInsercao',
		classes    : 2,
		methods    : 25,
		statements : 114
	});
	coverage.push({
		testsuite  : 'tests.AVLTest',
		testcase   : 'testInsercaoCondicoesLimite',
		classes    : 2,
		methods    : 24,
		statements : 105
	});
}

function fillTable(){
	$('#myTable').append('<thead>');
	var header = '<tr>';
	header += cellHeader('Test Suites (' + coverageGlobal.suites +')');
	header += cellHeader('Test Cases (' + coverageGlobal.testcases + ')');
	header += cellHeader('Classes (' + coverageGlobal.classes + ')');
	header += cellHeader('Methods (' + coverageGlobal.methods + ')');
	header += cellHeader('Statements (' + coverageGlobal.statements+')');
	header += '</tr>';
	$('#myTable').append(header);
	$('#myTable').append('</thead>');
	$('#myTable').append('<tbody>');
	for (var i=0; i < coverage.length; i++){
		var row = '<tr>';		row+= cellBody(coverage[i].testsuite);
		row+= cellBody(coverage[i].testcase);
		var value = coverage[i].classes;
		var total = coverageGlobal.classes;
		var percent = percentage(value,total);
		row+= cellBody(value + ' (' + percent +'%)');
		value = coverage[i].methods;
		total = coverageGlobal.methods;
		percent = percentage(value, total);
		row+= cellBody(value + ' (' + percent +'%)');
		value = coverage[i].statements;
		total = coverageGlobal.statements;
		percent = percentage(value, total);
		row+= cellBody(value + ' (' + percent +'%)');
		row += '</tr>';
		$('#myTable').append(row);
	}
	$('#myTable').append('</tbody>');
}

function cellHeader(title){
		return '<th>' +title +'</th>';
}

function cellBody(content){
	return '<td>' +content +'</td>';
}

function percentage(value, total){
	var percent = value * 100 / total;
	return percent.toFixed(2);
}

function getSuiteSize(){
	return coverage.length;
}
