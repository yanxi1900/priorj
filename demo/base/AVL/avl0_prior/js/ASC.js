var ascTests = new Array();

$(function(){
	loadListASC();
});

function loadListASC(){
	ascTests['tests.AVLTest.testInsercao'] = 1;
	ascTests['tests.AVLTest.testInsercaoCondicoesLimite'] = 2;
	ascTests['tests.AVLTest.testAltura'] = 3;
	ascTests['tests.AVLTest.testPesquisa'] = 4;
	ascTests['tests.AVLTest.testPesquisaCondicoesLimite'] = 5;
	ascTests['tests.AVLTest.testEstadoInicial'] = 6;
}

function getOrderASC(){
	return ascTests;
}

function getASCPosition(testName){
	return ascTests[testName];
}
