var ascTests = new Array();

$(function(){
	loadListASC();
});

function loadListASC(){
	ascTests['com.java.io.JavaIOTest.deveEncapsularEtapasDeDeserializacaoDoObjetoComXStream'] = 1;
	ascTests['com.java.io.JavaIOTest.deveAbrirXMLERecuperarDadosOuObjetosSalvos'] = 2;
	ascTests['com.java.io.JavaIOTest.deveMoverUmArquivoDeUmDiretorioParaOutro'] = 3;
	ascTests['com.java.io.JavaIOTest.deveAdicionarTextoDeModeIncremental'] = 4;
	ascTests['com.java.io.JavaIOTest.deveAbrirArquivoDeTextoERecuperarConteudo'] = 5;
	ascTests['com.java.io.JavaIOTest.shouldCopyFilesWhenDestinationFolderDoesNotExist'] = 6;
	ascTests['com.java.io.JavaIOTest.deveNaoSobrescreverArquivoQuandoOpcaoForTRUE'] = 7;
	ascTests['com.java.io.JavaIOTest.deveRenomearArquivoTxtOuXML'] = 8;
	ascTests['com.java.io.JavaIOTest.deveSobrescreverArquivoQuandoOpcaoForFALSE'] = 9;
	ascTests['com.java.io.JavaIOTest.deveCopiarArquivosDaOrigemParaUmDestino'] = 10;
	ascTests['com.java.io.JavaIOTest.shouldCopyAllFilesAndFolders'] = 11;
	ascTests['com.java.io.JavaIOTest.deveEncapsularEtapasDeSerializacaoDoObjetoComXStream'] = 12;
	ascTests['com.java.io.JavaIOTest.deveCriarEGravarNoDiscoArquivosXML'] = 13;
	ascTests['com.java.io.JavaIOTest.deveSalvarObjetoJavaSerializadoComXStreamNoArquivo'] = 14;
	ascTests['com.java.io.JavaIOTest.deveCriarArquivoDeTexto'] = 15;
	ascTests['com.java.io.JavaIOTest.deveDeletarArquivos'] = 16;
	ascTests['com.java.io.JavaIOTest.deveDeletarDiretorioNoProjetoAtualDoWorkspace'] = 17;
	ascTests['com.java.io.JavaIOTest.deveRemoveFolderEmQualquerLocalDoComputador'] = 18;
	ascTests['com.java.io.JavaIOTest.shouldRemoveLastSegment'] = 19;
	ascTests['com.java.io.JavaIOTest.shouldGetSegmentsInPath'] = 20;
	ascTests['com.java.io.JavaIOTest.deveCriarDiretorioNoProjetoAtualDoWorkpace'] = 21;
	ascTests['com.java.io.JavaIOTest.shouldGetLastSegment'] = 22;
	ascTests['com.java.io.JavaIOTest.deveCriarArquivoEmQualquerLocalDoComputador'] = 23;
	ascTests['com.java.io.JavaIOTest.deveSaberVerificarSeUmArquivoOuDiretorioExiste'] = 24;
	ascTests['com.java.io.JavaIOTest.shouldKnowOperatingSystemIsWindowsOrUnixOrMacOrSolaris'] = 25;
	ascTests['com.java.io.JavaIOTest.shouldToListAllFilesInFolder'] = 26;
}

function getOrderASC(){
	return ascTests;
}

function getASCPosition(testName){
	return ascTests[testName];
}
