var amcTests = new Array();

$(function(){
	loadListAMC();
});

function loadListAMC(){
	amcTests['tests.AVLTest.testInsercao'] = 1;
	amcTests['tests.AVLTest.testInsercaoCondicoesLimite'] = 2;
	amcTests['tests.AVLTest.testAltura'] = 3;
	amcTests['tests.AVLTest.testPesquisaCondicoesLimite'] = 4;
	amcTests['tests.AVLTest.testPesquisa'] = 5;
	amcTests['tests.AVLTest.testEstadoInicial'] = 6;
}

function getOrderAMC(){
	return amcTests;
}

function getAMCPosition(testName){
	return amcTests[testName];
}
