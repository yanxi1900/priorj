var rndTests = new Array();

$(function(){
	loadListRND();
});

function loadListRND(){
	rndTests['tests.AVLTest.testPesquisa'] = 1;
	rndTests['tests.AVLTest.testPesquisaCondicoesLimite'] = 2;
	rndTests['tests.AVLTest.testInsercaoCondicoesLimite'] = 3;
	rndTests['tests.AVLTest.testAltura'] = 4;
	rndTests['tests.AVLTest.testEstadoInicial'] = 5;
	rndTests['tests.AVLTest.testInsercao'] = 6;
}

function getOrderRND(){
	return rndTests;
}

function getRNDPosition(testName){
	return rndTests[testName];
}
