var tmcTests = new Array();

$(function(){
	loadListTMC();
});

function loadListTMC(){
	tmcTests['tests.AVLTest.testInsercao'] = 1;
	tmcTests['tests.AVLTest.testInsercaoCondicoesLimite'] = 2;
	tmcTests['tests.AVLTest.testAltura'] = 3;
	tmcTests['tests.AVLTest.testPesquisa'] = 4;
	tmcTests['tests.AVLTest.testPesquisaCondicoesLimite'] = 5;
	tmcTests['tests.AVLTest.testEstadoInicial'] = 6;
}

function getOrderTMC(){
	return tmcTests;
}

function getTMCPosition(testName){
	return tmcTests[testName];
}
