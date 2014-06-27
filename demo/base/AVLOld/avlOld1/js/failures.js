var failuresList = new Array();

$(function(){
	loadFailuresList();
});

function loadFailuresList(){
	failuresList.push('tests.AVLTest.testInsercao');
	failuresList.push('tests.AVLTest.testInsercaoCondicoesLimite');
}

function getFailures(){
	return failuresList;
}
