var coverage = [];

var coverageGlobal = {
	suites: 1,
	testcases: 26,
	classes: 2,
	methods: 29,
	statements: 105
};

$(function(){
	loadData();
	fillTable();
});

function loadData(){
	coverage.push({
		testsuite  : 'com.java.io.JavaIOTest',
		testcase   : 'deveCriarArquivoDeTexto',
		classes    : 1,
		methods    : 5,
		statements : 18
	});
	coverage.push({
		testsuite  : 'com.java.io.JavaIOTest',
		testcase   : 'deveSobrescreverArquivoQuandoOpcaoForFALSE',
		classes    : 1,
		methods    : 5,
		statements : 27
	});
	coverage.push({
		testsuite  : 'com.java.io.JavaIOTest',
		testcase   : 'shouldCopyAllFilesAndFolders',
		classes    : 1,
		methods    : 4,
		statements : 21
	});
	coverage.push({
		testsuite  : 'com.java.io.JavaIOTest',
		testcase   : 'deveAbrirXMLERecuperarDadosOuObjetosSalvos',
		classes    : 2,
		methods    : 9,
		statements : 34
	});
	coverage.push({
		testsuite  : 'com.java.io.JavaIOTest',
		testcase   : 'deveAdicionarTextoDeModeIncremental',
		classes    : 1,
		methods    : 6,
		statements : 31
	});
	coverage.push({
		testsuite  : 'com.java.io.JavaIOTest',
		testcase   : 'deveCriarDiretorioNoProjetoAtualDoWorkpace',
		classes    : 1,
		methods    : 4,
		statements : 6
	});
	coverage.push({
		testsuite  : 'com.java.io.JavaIOTest',
		testcase   : 'deveRemoveFolderEmQualquerLocalDoComputador',
		classes    : 1,
		methods    : 6,
		statements : 14
	});
	coverage.push({
		testsuite  : 'com.java.io.JavaIOTest',
		testcase   : 'deveAbrirArquivoDeTextoERecuperarConteudo',
		classes    : 1,
		methods    : 6,
		statements : 31
	});
	coverage.push({
		testsuite  : 'com.java.io.JavaIOTest',
		testcase   : 'deveSalvarObjetoJavaSerializadoComXStreamNoArquivo',
		classes    : 1,
		methods    : 5,
		statements : 18
	});
	coverage.push({
		testsuite  : 'com.java.io.JavaIOTest',
		testcase   : 'deveCriarEGravarNoDiscoArquivosXML',
		classes    : 1,
		methods    : 5,
		statements : 18
	});
	coverage.push({
		testsuite  : 'com.java.io.JavaIOTest',
		testcase   : 'shouldKnowOperatingSystemIsWindowsOrUnixOrMacOrSolaris',
		classes    : 1,
		methods    : 2,
		statements : 4
	});
	coverage.push({
		testsuite  : 'com.java.io.JavaIOTest',
		testcase   : 'shouldToListAllFilesInFolder',
		classes    : 1,
		methods    : 2,
		statements : 4
	});
	coverage.push({
		testsuite  : 'com.java.io.JavaIOTest',
		testcase   : 'deveNaoSobrescreverArquivoQuandoOpcaoForTRUE',
		classes    : 1,
		methods    : 6,
		statements : 30
	});
	coverage.push({
		testsuite  : 'com.java.io.JavaIOTest',
		testcase   : 'deveMoverUmArquivoDeUmDiretorioParaOutro',
		classes    : 1,
		methods    : 7,
		statements : 34
	});
	coverage.push({
		testsuite  : 'com.java.io.JavaIOTest',
		testcase   : 'deveRenomearArquivoTxtOuXML',
		classes    : 1,
		methods    : 9,
		statements : 29
	});
	coverage.push({
		testsuite  : 'com.java.io.JavaIOTest',
		testcase   : 'shouldRemoveLastSegment',
		classes    : 1,
		methods    : 4,
		statements : 8
	});
	coverage.push({
		testsuite  : 'com.java.io.JavaIOTest',
		testcase   : 'shouldGetSegmentsInPath',
		classes    : 1,
		methods    : 2,
		statements : 6
	});
	coverage.push({
		testsuite  : 'com.java.io.JavaIOTest',
		testcase   : 'deveEncapsularEtapasDeSerializacaoDoObjetoComXStream',
		classes    : 1,
		methods    : 5,
		statements : 19
	});
	coverage.push({
		testsuite  : 'com.java.io.JavaIOTest',
		testcase   : 'deveDeletarDiretorioNoProjetoAtualDoWorkspace',
		classes    : 1,
		methods    : 8,
		statements : 16
	});
	coverage.push({
		testsuite  : 'com.java.io.JavaIOTest',
		testcase   : 'deveEncapsularEtapasDeDeserializacaoDoObjetoComXStream',
		classes    : 2,
		methods    : 10,
		statements : 38
	});
	coverage.push({
		testsuite  : 'com.java.io.JavaIOTest',
		testcase   : 'deveCopiarArquivosDaOrigemParaUmDestino',
		classes    : 1,
		methods    : 7,
		statements : 27
	});
	coverage.push({
		testsuite  : 'com.java.io.JavaIOTest',
		testcase   : 'shouldCopyFilesWhenDestinationFolderDoesNotExist',
		classes    : 1,
		methods    : 8,
		statements : 31
	});
	coverage.push({
		testsuite  : 'com.java.io.JavaIOTest',
		testcase   : 'deveCriarArquivoEmQualquerLocalDoComputador',
		classes    : 1,
		methods    : 3,
		statements : 5
	});
	coverage.push({
		testsuite  : 'com.java.io.JavaIOTest',
		testcase   : 'deveSaberVerificarSeUmArquivoOuDiretorioExiste',
		classes    : 1,
		methods    : 3,
		statements : 5
	});
	coverage.push({
		testsuite  : 'com.java.io.JavaIOTest',
		testcase   : 'deveDeletarArquivos',
		classes    : 1,
		methods    : 5,
		statements : 17
	});
	coverage.push({
		testsuite  : 'com.java.io.JavaIOTest',
		testcase   : 'shouldGetLastSegment',
		classes    : 1,
		methods    : 3,
		statements : 6
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
