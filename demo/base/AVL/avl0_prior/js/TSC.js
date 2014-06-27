var tscTests = new Array();

$(function(){
	loadListTSC();
});

function loadListTSC(){
	tscTests['tests.AVLTest.testInsercao'] = 1;
	tscTests['tests.AVLTest.testInsercaoCondicoesLimite'] = 2;
	tscTests['tests.AVLTest.testAltura'] = 3;
	tscTests['tests.AVLTest.testPesquisa'] = 4;
	tscTests['tests.AVLTest.testPesquisaCondicoesLimite'] = 5;
	tscTests['tests.AVLTest.testEstadoInicial'] = 6;
}

function getOrderTSC(){
	return tscTests;
}

function getTSCPosition(testName){
	return tscTests[testName];
}
