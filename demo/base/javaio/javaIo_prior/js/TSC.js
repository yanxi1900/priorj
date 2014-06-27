var tscTests = new Array();

$(function(){
	loadListTSC();
});

function loadListTSC(){
	tscTests['com.java.io.JavaIOTest.deveEncapsularEtapasDeDeserializacaoDoObjetoComXStream'] = 1;
	tscTests['com.java.io.JavaIOTest.deveMoverUmArquivoDeUmDiretorioParaOutro'] = 2;
	tscTests['com.java.io.JavaIOTest.deveAbrirXMLERecuperarDadosOuObjetosSalvos'] = 3;
	tscTests['com.java.io.JavaIOTest.shouldCopyFilesWhenDestinationFolderDoesNotExist'] = 4;
	tscTests['com.java.io.JavaIOTest.deveAbrirArquivoDeTextoERecuperarConteudo'] = 5;
	tscTests['com.java.io.JavaIOTest.deveAdicionarTextoDeModeIncremental'] = 6;
	tscTests['com.java.io.JavaIOTest.deveNaoSobrescreverArquivoQuandoOpcaoForTRUE'] = 7;
	tscTests['com.java.io.JavaIOTest.deveRenomearArquivoTxtOuXML'] = 8;
	tscTests['com.java.io.JavaIOTest.deveSobrescreverArquivoQuandoOpcaoForFALSE'] = 9;
	tscTests['com.java.io.JavaIOTest.deveCopiarArquivosDaOrigemParaUmDestino'] = 10;
	tscTests['com.java.io.JavaIOTest.shouldCopyAllFilesAndFolders'] = 11;
	tscTests['com.java.io.JavaIOTest.deveEncapsularEtapasDeSerializacaoDoObjetoComXStream'] = 12;
	tscTests['com.java.io.JavaIOTest.deveSalvarObjetoJavaSerializadoComXStreamNoArquivo'] = 13;
	tscTests['com.java.io.JavaIOTest.deveCriarEGravarNoDiscoArquivosXML'] = 14;
	tscTests['com.java.io.JavaIOTest.deveCriarArquivoDeTexto'] = 15;
	tscTests['com.java.io.JavaIOTest.deveDeletarArquivos'] = 16;
	tscTests['com.java.io.JavaIOTest.deveDeletarDiretorioNoProjetoAtualDoWorkspace'] = 17;
	tscTests['com.java.io.JavaIOTest.deveRemoveFolderEmQualquerLocalDoComputador'] = 18;
	tscTests['com.java.io.JavaIOTest.shouldRemoveLastSegment'] = 19;
	tscTests['com.java.io.JavaIOTest.shouldGetSegmentsInPath'] = 20;
	tscTests['com.java.io.JavaIOTest.deveCriarDiretorioNoProjetoAtualDoWorkpace'] = 21;
	tscTests['com.java.io.JavaIOTest.shouldGetLastSegment'] = 22;
	tscTests['com.java.io.JavaIOTest.deveSaberVerificarSeUmArquivoOuDiretorioExiste'] = 23;
	tscTests['com.java.io.JavaIOTest.deveCriarArquivoEmQualquerLocalDoComputador'] = 24;
	tscTests['com.java.io.JavaIOTest.shouldToListAllFilesInFolder'] = 25;
	tscTests['com.java.io.JavaIOTest.shouldKnowOperatingSystemIsWindowsOrUnixOrMacOrSolaris'] = 26;
}

function getOrderTSC(){
	return tscTests;
}

function getTSCPosition(testName){
	return tscTests[testName];
}
