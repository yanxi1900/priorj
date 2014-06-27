var tmcTests = new Array();

$(function(){
	loadListTMC();
});

function loadListTMC(){
	tmcTests['com.java.io.JavaIOTest.deveEncapsularEtapasDeDeserializacaoDoObjetoComXStream'] = 1;
	tmcTests['com.java.io.JavaIOTest.deveRenomearArquivoTxtOuXML'] = 2;
	tmcTests['com.java.io.JavaIOTest.deveAbrirXMLERecuperarDadosOuObjetosSalvos'] = 3;
	tmcTests['com.java.io.JavaIOTest.shouldCopyFilesWhenDestinationFolderDoesNotExist'] = 4;
	tmcTests['com.java.io.JavaIOTest.deveDeletarDiretorioNoProjetoAtualDoWorkspace'] = 5;
	tmcTests['com.java.io.JavaIOTest.deveCopiarArquivosDaOrigemParaUmDestino'] = 6;
	tmcTests['com.java.io.JavaIOTest.deveMoverUmArquivoDeUmDiretorioParaOutro'] = 7;
	tmcTests['com.java.io.JavaIOTest.deveRemoveFolderEmQualquerLocalDoComputador'] = 8;
	tmcTests['com.java.io.JavaIOTest.deveAdicionarTextoDeModeIncremental'] = 9;
	tmcTests['com.java.io.JavaIOTest.deveAbrirArquivoDeTextoERecuperarConteudo'] = 10;
	tmcTests['com.java.io.JavaIOTest.deveNaoSobrescreverArquivoQuandoOpcaoForTRUE'] = 11;
	tmcTests['com.java.io.JavaIOTest.deveCriarArquivoDeTexto'] = 12;
	tmcTests['com.java.io.JavaIOTest.deveEncapsularEtapasDeSerializacaoDoObjetoComXStream'] = 13;
	tmcTests['com.java.io.JavaIOTest.deveDeletarArquivos'] = 14;
	tmcTests['com.java.io.JavaIOTest.deveSalvarObjetoJavaSerializadoComXStreamNoArquivo'] = 15;
	tmcTests['com.java.io.JavaIOTest.deveCriarEGravarNoDiscoArquivosXML'] = 16;
	tmcTests['com.java.io.JavaIOTest.deveSobrescreverArquivoQuandoOpcaoForFALSE'] = 17;
	tmcTests['com.java.io.JavaIOTest.deveCriarDiretorioNoProjetoAtualDoWorkpace'] = 18;
	tmcTests['com.java.io.JavaIOTest.shouldRemoveLastSegment'] = 19;
	tmcTests['com.java.io.JavaIOTest.shouldCopyAllFilesAndFolders'] = 20;
	tmcTests['com.java.io.JavaIOTest.deveCriarArquivoEmQualquerLocalDoComputador'] = 21;
	tmcTests['com.java.io.JavaIOTest.shouldGetLastSegment'] = 22;
	tmcTests['com.java.io.JavaIOTest.deveSaberVerificarSeUmArquivoOuDiretorioExiste'] = 23;
	tmcTests['com.java.io.JavaIOTest.shouldGetSegmentsInPath'] = 24;
	tmcTests['com.java.io.JavaIOTest.shouldToListAllFilesInFolder'] = 25;
	tmcTests['com.java.io.JavaIOTest.shouldKnowOperatingSystemIsWindowsOrUnixOrMacOrSolaris'] = 26;
}

function getOrderTMC(){
	return tmcTests;
}

function getTMCPosition(testName){
	return tmcTests[testName];
}
