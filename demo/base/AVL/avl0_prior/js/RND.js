var rndTests = new Array();

$(function(){
	loadListRND();
});

function loadListRND(){
	rndTests['tests.AVLTest.testInsercao'] = 1;
	rndTests['tests.AVLTest.testInsercaoCondicoesLimite'] = 2;
	rndTests['tests.AVLTest.testEstadoInicial'] = 3;
	rndTests['tests.AVLTest.testPesquisaCondicoesLimite'] = 4;
	rndTests['tests.AVLTest.testPesquisa'] = 5;
	rndTests['tests.AVLTest.testAltura'] = 6;
}

function getOrderRND(){
	return rndTests;
}

function getRNDPosition(testName){
	return rndTests[testName];
}
