var amcTests = new Array();

$(function(){
	loadListAMC();
});

function loadListAMC(){
	amcTests['com.java.io.JavaIOTest.deveEncapsularEtapasDeDeserializacaoDoObjetoComXStream'] = 1;
	amcTests['com.java.io.JavaIOTest.deveRenomearArquivoTxtOuXML'] = 2;
	amcTests['com.java.io.JavaIOTest.deveAbrirXMLERecuperarDadosOuObjetosSalvos'] = 3;
	amcTests['com.java.io.JavaIOTest.shouldCopyFilesWhenDestinationFolderDoesNotExist'] = 4;
	amcTests['com.java.io.JavaIOTest.deveDeletarDiretorioNoProjetoAtualDoWorkspace'] = 5;
	amcTests['com.java.io.JavaIOTest.deveMoverUmArquivoDeUmDiretorioParaOutro'] = 6;
	amcTests['com.java.io.JavaIOTest.deveCopiarArquivosDaOrigemParaUmDestino'] = 7;
	amcTests['com.java.io.JavaIOTest.deveAbrirArquivoDeTextoERecuperarConteudo'] = 8;
	amcTests['com.java.io.JavaIOTest.deveNaoSobrescreverArquivoQuandoOpcaoForTRUE'] = 9;
	amcTests['com.java.io.JavaIOTest.deveRemoveFolderEmQualquerLocalDoComputador'] = 10;
	amcTests['com.java.io.JavaIOTest.deveAdicionarTextoDeModeIncremental'] = 11;
	amcTests['com.java.io.JavaIOTest.deveCriarArquivoDeTexto'] = 12;
	amcTests['com.java.io.JavaIOTest.deveSalvarObjetoJavaSerializadoComXStreamNoArquivo'] = 13;
	amcTests['com.java.io.JavaIOTest.deveSobrescreverArquivoQuandoOpcaoForFALSE'] = 14;
	amcTests['com.java.io.JavaIOTest.deveCriarEGravarNoDiscoArquivosXML'] = 15;
	amcTests['com.java.io.JavaIOTest.deveDeletarArquivos'] = 16;
	amcTests['com.java.io.JavaIOTest.deveEncapsularEtapasDeSerializacaoDoObjetoComXStream'] = 17;
	amcTests['com.java.io.JavaIOTest.shouldCopyAllFilesAndFolders'] = 18;
	amcTests['com.java.io.JavaIOTest.deveCriarDiretorioNoProjetoAtualDoWorkpace'] = 19;
	amcTests['com.java.io.JavaIOTest.shouldRemoveLastSegment'] = 20;
	amcTests['com.java.io.JavaIOTest.shouldGetLastSegment'] = 21;
	amcTests['com.java.io.JavaIOTest.deveCriarArquivoEmQualquerLocalDoComputador'] = 22;
	amcTests['com.java.io.JavaIOTest.deveSaberVerificarSeUmArquivoOuDiretorioExiste'] = 23;
	amcTests['com.java.io.JavaIOTest.shouldKnowOperatingSystemIsWindowsOrUnixOrMacOrSolaris'] = 24;
	amcTests['com.java.io.JavaIOTest.shouldGetSegmentsInPath'] = 25;
	amcTests['com.java.io.JavaIOTest.shouldToListAllFilesInFolder'] = 26;
}

function getOrderAMC(){
	return amcTests;
}

function getAMCPosition(testName){
	return amcTests[testName];
}
