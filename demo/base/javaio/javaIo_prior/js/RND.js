var rndTests = new Array();

$(function(){
	loadListRND();
});

function loadListRND(){
	rndTests['com.java.io.JavaIOTest.deveCriarEGravarNoDiscoArquivosXML'] = 1;
	rndTests['com.java.io.JavaIOTest.deveAdicionarTextoDeModeIncremental'] = 2;
	rndTests['com.java.io.JavaIOTest.shouldKnowOperatingSystemIsWindowsOrUnixOrMacOrSolaris'] = 3;
	rndTests['com.java.io.JavaIOTest.deveNaoSobrescreverArquivoQuandoOpcaoForTRUE'] = 4;
	rndTests['com.java.io.JavaIOTest.shouldGetLastSegment'] = 5;
	rndTests['com.java.io.JavaIOTest.deveSobrescreverArquivoQuandoOpcaoForFALSE'] = 6;
	rndTests['com.java.io.JavaIOTest.deveDeletarDiretorioNoProjetoAtualDoWorkspace'] = 7;
	rndTests['com.java.io.JavaIOTest.deveCriarDiretorioNoProjetoAtualDoWorkpace'] = 8;
	rndTests['com.java.io.JavaIOTest.deveSaberVerificarSeUmArquivoOuDiretorioExiste'] = 9;
	rndTests['com.java.io.JavaIOTest.deveCriarArquivoEmQualquerLocalDoComputador'] = 10;
	rndTests['com.java.io.JavaIOTest.deveSalvarObjetoJavaSerializadoComXStreamNoArquivo'] = 11;
	rndTests['com.java.io.JavaIOTest.deveCriarArquivoDeTexto'] = 12;
	rndTests['com.java.io.JavaIOTest.deveRemoveFolderEmQualquerLocalDoComputador'] = 13;
	rndTests['com.java.io.JavaIOTest.deveCopiarArquivosDaOrigemParaUmDestino'] = 14;
	rndTests['com.java.io.JavaIOTest.deveMoverUmArquivoDeUmDiretorioParaOutro'] = 15;
	rndTests['com.java.io.JavaIOTest.deveEncapsularEtapasDeDeserializacaoDoObjetoComXStream'] = 16;
	rndTests['com.java.io.JavaIOTest.deveDeletarArquivos'] = 17;
	rndTests['com.java.io.JavaIOTest.shouldCopyFilesWhenDestinationFolderDoesNotExist'] = 18;
	rndTests['com.java.io.JavaIOTest.shouldCopyAllFilesAndFolders'] = 19;
	rndTests['com.java.io.JavaIOTest.deveRenomearArquivoTxtOuXML'] = 20;
	rndTests['com.java.io.JavaIOTest.shouldRemoveLastSegment'] = 21;
	rndTests['com.java.io.JavaIOTest.deveAbrirXMLERecuperarDadosOuObjetosSalvos'] = 22;
	rndTests['com.java.io.JavaIOTest.deveEncapsularEtapasDeSerializacaoDoObjetoComXStream'] = 23;
	rndTests['com.java.io.JavaIOTest.deveAbrirArquivoDeTextoERecuperarConteudo'] = 24;
	rndTests['com.java.io.JavaIOTest.shouldGetSegmentsInPath'] = 25;
	rndTests['com.java.io.JavaIOTest.shouldToListAllFilesInFolder'] = 26;
}

function getOrderRND(){
	return rndTests;
}

function getRNDPosition(testName){
	return rndTests[testName];
}
