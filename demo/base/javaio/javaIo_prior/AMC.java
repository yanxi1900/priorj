package tests;
import junit.framework.TestCase;
import junit.framework.TestResult;
public class AMC {
	private int failureCount = 0;
	private int runCount = 0;
	private int errorCount = 0;
	public void run(){
		TestCase tcComIoJavaIOTestDeveEncapsularEtapasDeDeserializacaoDoObjetoComXStream = null;
		tcComIoJavaIOTestDeveEncapsularEtapasDeDeserializacaoDoObjetoComXStream = new com.io.JavaIOTest(){
			public void runTest(){
				try{
					deveEncapsularEtapasDeDeserializacaoDoObjetoComXStream();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("deveEncapsularEtapasDeDeserializacaoDoObjetoComXStream",tcComIoJavaIOTestDeveEncapsularEtapasDeDeserializacaoDoObjetoComXStream.run());
		TestCase tcComIoJavaIOTestDeveRenomearArquivoTxtOuXML = null;
		tcComIoJavaIOTestDeveRenomearArquivoTxtOuXML = new com.io.JavaIOTest(){
			public void runTest(){
				try{
					deveRenomearArquivoTxtOuXML();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("deveRenomearArquivoTxtOuXML",tcComIoJavaIOTestDeveRenomearArquivoTxtOuXML.run());
		TestCase tcComIoJavaIOTestDeveAbrirXMLERecuperarDadosOuObjetosSalvos = null;
		tcComIoJavaIOTestDeveAbrirXMLERecuperarDadosOuObjetosSalvos = new com.io.JavaIOTest(){
			public void runTest(){
				try{
					deveAbrirXMLERecuperarDadosOuObjetosSalvos();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("deveAbrirXMLERecuperarDadosOuObjetosSalvos",tcComIoJavaIOTestDeveAbrirXMLERecuperarDadosOuObjetosSalvos.run());
		TestCase tcComIoJavaIOTestShouldCopyFilesWhenDestinationFolderDoesNotExist = null;
		tcComIoJavaIOTestShouldCopyFilesWhenDestinationFolderDoesNotExist = new com.io.JavaIOTest(){
			public void runTest(){
				try{
					shouldCopyFilesWhenDestinationFolderDoesNotExist();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("shouldCopyFilesWhenDestinationFolderDoesNotExist",tcComIoJavaIOTestShouldCopyFilesWhenDestinationFolderDoesNotExist.run());
		TestCase tcComIoJavaIOTestDeveDeletarDiretorioNoProjetoAtualDoWorkspace = null;
		tcComIoJavaIOTestDeveDeletarDiretorioNoProjetoAtualDoWorkspace = new com.io.JavaIOTest(){
			public void runTest(){
				try{
					deveDeletarDiretorioNoProjetoAtualDoWorkspace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("deveDeletarDiretorioNoProjetoAtualDoWorkspace",tcComIoJavaIOTestDeveDeletarDiretorioNoProjetoAtualDoWorkspace.run());
		TestCase tcComIoJavaIOTestDeveMoverUmArquivoDeUmDiretorioParaOutro = null;
		tcComIoJavaIOTestDeveMoverUmArquivoDeUmDiretorioParaOutro = new com.io.JavaIOTest(){
			public void runTest(){
				try{
					deveMoverUmArquivoDeUmDiretorioParaOutro();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("deveMoverUmArquivoDeUmDiretorioParaOutro",tcComIoJavaIOTestDeveMoverUmArquivoDeUmDiretorioParaOutro.run());
		TestCase tcComIoJavaIOTestDeveCopiarArquivosDaOrigemParaUmDestino = null;
		tcComIoJavaIOTestDeveCopiarArquivosDaOrigemParaUmDestino = new com.io.JavaIOTest(){
			public void runTest(){
				try{
					deveCopiarArquivosDaOrigemParaUmDestino();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("deveCopiarArquivosDaOrigemParaUmDestino",tcComIoJavaIOTestDeveCopiarArquivosDaOrigemParaUmDestino.run());
		TestCase tcComIoJavaIOTestDeveAbrirArquivoDeTextoERecuperarConteudo = null;
		tcComIoJavaIOTestDeveAbrirArquivoDeTextoERecuperarConteudo = new com.io.JavaIOTest(){
			public void runTest(){
				try{
					deveAbrirArquivoDeTextoERecuperarConteudo();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("deveAbrirArquivoDeTextoERecuperarConteudo",tcComIoJavaIOTestDeveAbrirArquivoDeTextoERecuperarConteudo.run());
		TestCase tcComIoJavaIOTestDeveNaoSobrescreverArquivoQuandoOpcaoForTRUE = null;
		tcComIoJavaIOTestDeveNaoSobrescreverArquivoQuandoOpcaoForTRUE = new com.io.JavaIOTest(){
			public void runTest(){
				try{
					deveNaoSobrescreverArquivoQuandoOpcaoForTRUE();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("deveNaoSobrescreverArquivoQuandoOpcaoForTRUE",tcComIoJavaIOTestDeveNaoSobrescreverArquivoQuandoOpcaoForTRUE.run());
		TestCase tcComIoJavaIOTestDeveRemoveFolderEmQualquerLocalDoComputador = null;
		tcComIoJavaIOTestDeveRemoveFolderEmQualquerLocalDoComputador = new com.io.JavaIOTest(){
			public void runTest(){
				try{
					deveRemoveFolderEmQualquerLocalDoComputador();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("deveRemoveFolderEmQualquerLocalDoComputador",tcComIoJavaIOTestDeveRemoveFolderEmQualquerLocalDoComputador.run());
		TestCase tcComIoJavaIOTestDeveAdicionarTextoDeModeIncremental = null;
		tcComIoJavaIOTestDeveAdicionarTextoDeModeIncremental = new com.io.JavaIOTest(){
			public void runTest(){
				try{
					deveAdicionarTextoDeModeIncremental();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("deveAdicionarTextoDeModeIncremental",tcComIoJavaIOTestDeveAdicionarTextoDeModeIncremental.run());
		TestCase tcComIoJavaIOTestDeveCriarArquivoDeTexto = null;
		tcComIoJavaIOTestDeveCriarArquivoDeTexto = new com.io.JavaIOTest(){
			public void runTest(){
				try{
					deveCriarArquivoDeTexto();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("deveCriarArquivoDeTexto",tcComIoJavaIOTestDeveCriarArquivoDeTexto.run());
		TestCase tcComIoJavaIOTestDeveSalvarObjetoJavaSerializadoComXStreamNoArquivo = null;
		tcComIoJavaIOTestDeveSalvarObjetoJavaSerializadoComXStreamNoArquivo = new com.io.JavaIOTest(){
			public void runTest(){
				try{
					deveSalvarObjetoJavaSerializadoComXStreamNoArquivo();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("deveSalvarObjetoJavaSerializadoComXStreamNoArquivo",tcComIoJavaIOTestDeveSalvarObjetoJavaSerializadoComXStreamNoArquivo.run());
		TestCase tcComIoJavaIOTestDeveSobrescreverArquivoQuandoOpcaoForFALSE = null;
		tcComIoJavaIOTestDeveSobrescreverArquivoQuandoOpcaoForFALSE = new com.io.JavaIOTest(){
			public void runTest(){
				try{
					deveSobrescreverArquivoQuandoOpcaoForFALSE();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("deveSobrescreverArquivoQuandoOpcaoForFALSE",tcComIoJavaIOTestDeveSobrescreverArquivoQuandoOpcaoForFALSE.run());
		TestCase tcComIoJavaIOTestDeveCriarEGravarNoDiscoArquivosXML = null;
		tcComIoJavaIOTestDeveCriarEGravarNoDiscoArquivosXML = new com.io.JavaIOTest(){
			public void runTest(){
				try{
					deveCriarEGravarNoDiscoArquivosXML();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("deveCriarEGravarNoDiscoArquivosXML",tcComIoJavaIOTestDeveCriarEGravarNoDiscoArquivosXML.run());
		TestCase tcComIoJavaIOTestDeveDeletarArquivos = null;
		tcComIoJavaIOTestDeveDeletarArquivos = new com.io.JavaIOTest(){
			public void runTest(){
				try{
					deveDeletarArquivos();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("deveDeletarArquivos",tcComIoJavaIOTestDeveDeletarArquivos.run());
		TestCase tcComIoJavaIOTestDeveEncapsularEtapasDeSerializacaoDoObjetoComXStream = null;
		tcComIoJavaIOTestDeveEncapsularEtapasDeSerializacaoDoObjetoComXStream = new com.io.JavaIOTest(){
			public void runTest(){
				try{
					deveEncapsularEtapasDeSerializacaoDoObjetoComXStream();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("deveEncapsularEtapasDeSerializacaoDoObjetoComXStream",tcComIoJavaIOTestDeveEncapsularEtapasDeSerializacaoDoObjetoComXStream.run());
		TestCase tcComIoJavaIOTestShouldCopyAllFilesAndFolders = null;
		tcComIoJavaIOTestShouldCopyAllFilesAndFolders = new com.io.JavaIOTest(){
			public void runTest(){
				try{
					shouldCopyAllFilesAndFolders();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("shouldCopyAllFilesAndFolders",tcComIoJavaIOTestShouldCopyAllFilesAndFolders.run());
		TestCase tcComIoJavaIOTestDeveCriarDiretorioNoProjetoAtualDoWorkpace = null;
		tcComIoJavaIOTestDeveCriarDiretorioNoProjetoAtualDoWorkpace = new com.io.JavaIOTest(){
			public void runTest(){
				try{
					deveCriarDiretorioNoProjetoAtualDoWorkpace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("deveCriarDiretorioNoProjetoAtualDoWorkpace",tcComIoJavaIOTestDeveCriarDiretorioNoProjetoAtualDoWorkpace.run());
		TestCase tcComIoJavaIOTestShouldRemoveLastSegment = null;
		tcComIoJavaIOTestShouldRemoveLastSegment = new com.io.JavaIOTest(){
			public void runTest(){
				try{
					shouldRemoveLastSegment();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("shouldRemoveLastSegment",tcComIoJavaIOTestShouldRemoveLastSegment.run());
		TestCase tcComIoJavaIOTestShouldGetLastSegment = null;
		tcComIoJavaIOTestShouldGetLastSegment = new com.io.JavaIOTest(){
			public void runTest(){
				try{
					shouldGetLastSegment();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("shouldGetLastSegment",tcComIoJavaIOTestShouldGetLastSegment.run());
		TestCase tcComIoJavaIOTestDeveCriarArquivoEmQualquerLocalDoComputador = null;
		tcComIoJavaIOTestDeveCriarArquivoEmQualquerLocalDoComputador = new com.io.JavaIOTest(){
			public void runTest(){
				try{
					deveCriarArquivoEmQualquerLocalDoComputador();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("deveCriarArquivoEmQualquerLocalDoComputador",tcComIoJavaIOTestDeveCriarArquivoEmQualquerLocalDoComputador.run());
		TestCase tcComIoJavaIOTestDeveSaberVerificarSeUmArquivoOuDiretorioExiste = null;
		tcComIoJavaIOTestDeveSaberVerificarSeUmArquivoOuDiretorioExiste = new com.io.JavaIOTest(){
			public void runTest(){
				try{
					deveSaberVerificarSeUmArquivoOuDiretorioExiste();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("deveSaberVerificarSeUmArquivoOuDiretorioExiste",tcComIoJavaIOTestDeveSaberVerificarSeUmArquivoOuDiretorioExiste.run());
		TestCase tcComIoJavaIOTestShouldKnowOperatingSystemIsWindowsOrUnixOrMacOrSolaris = null;
		tcComIoJavaIOTestShouldKnowOperatingSystemIsWindowsOrUnixOrMacOrSolaris = new com.io.JavaIOTest(){
			public void runTest(){
				try{
					shouldKnowOperatingSystemIsWindowsOrUnixOrMacOrSolaris();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("shouldKnowOperatingSystemIsWindowsOrUnixOrMacOrSolaris",tcComIoJavaIOTestShouldKnowOperatingSystemIsWindowsOrUnixOrMacOrSolaris.run());
		TestCase tcComIoJavaIOTestShouldGetSegmentsInPath = null;
		tcComIoJavaIOTestShouldGetSegmentsInPath = new com.io.JavaIOTest(){
			public void runTest(){
				try{
					shouldGetSegmentsInPath();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("shouldGetSegmentsInPath",tcComIoJavaIOTestShouldGetSegmentsInPath.run());
		TestCase tcComIoJavaIOTestShouldToListAllFilesInFolder = null;
		tcComIoJavaIOTestShouldToListAllFilesInFolder = new com.io.JavaIOTest(){
			public void runTest(){
				try{
					shouldToListAllFilesInFolder();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("shouldToListAllFilesInFolder",tcComIoJavaIOTestShouldToListAllFilesInFolder.run());
	}
	private void setResult(String testCaseName,TestResult result){
		System.out.println("TestCase: " + testCaseName +" " + getStatus(result));
		runCount += result.runCount();
		failureCount += result.failureCount();
		errorCount += result.errorCount();
	}
	private String getStatus(TestResult result){
		int erroCount = result.errorCount();
		int failureCount = result.failureCount();
		if(erroCount > 0) return "[ERROR]";
		else if(failureCount > 0) return "[FAILURE]";
		else return "[ACCEPTED]";
	}
	public void printResult(){
		System.out.println("=================== Test Suite Prioritized - PriorJ =================");
		System.out.println("Run: " + this.runCount);
		System.out.println("Faults: " + this.failureCount);
		System.out.println("Errors: " + this.errorCount);
	}
	public static void main(String[] args) {
		AMC st = new AMC();
		st.tcComJavaIoJavaIOTestDeveEncapsularEtapasDeDeserializacaoDoObjetoComXStream();
		st.tcComJavaIoJavaIOTestDeveRenomearArquivoTxtOuXML();
		st.tcComJavaIoJavaIOTestDeveAbrirXMLERecuperarDadosOuObjetosSalvos();
		st.tcComJavaIoJavaIOTestShouldCopyFilesWhenDestinationFolderDoesNotExist();
		st.tcComJavaIoJavaIOTestDeveDeletarDiretorioNoProjetoAtualDoWorkspace();
		st.tcComJavaIoJavaIOTestDeveMoverUmArquivoDeUmDiretorioParaOutro();
		st.tcComJavaIoJavaIOTestDeveCopiarArquivosDaOrigemParaUmDestino();
		st.tcComJavaIoJavaIOTestDeveAbrirArquivoDeTextoERecuperarConteudo();
		st.tcComJavaIoJavaIOTestDeveNaoSobrescreverArquivoQuandoOpcaoForTRUE();
		st.tcComJavaIoJavaIOTestDeveRemoveFolderEmQualquerLocalDoComputador();
		st.tcComJavaIoJavaIOTestDeveAdicionarTextoDeModeIncremental();
		st.tcComJavaIoJavaIOTestDeveCriarArquivoDeTexto();
		st.tcComJavaIoJavaIOTestDeveSalvarObjetoJavaSerializadoComXStreamNoArquivo();
		st.tcComJavaIoJavaIOTestDeveSobrescreverArquivoQuandoOpcaoForFALSE();
		st.tcComJavaIoJavaIOTestDeveCriarEGravarNoDiscoArquivosXML();
		st.tcComJavaIoJavaIOTestDeveDeletarArquivos();
		st.tcComJavaIoJavaIOTestDeveEncapsularEtapasDeSerializacaoDoObjetoComXStream();
		st.tcComJavaIoJavaIOTestShouldCopyAllFilesAndFolders();
		st.tcComJavaIoJavaIOTestDeveCriarDiretorioNoProjetoAtualDoWorkpace();
		st.tcComJavaIoJavaIOTestShouldRemoveLastSegment();
		st.tcComJavaIoJavaIOTestShouldGetLastSegment();
		st.tcComJavaIoJavaIOTestDeveCriarArquivoEmQualquerLocalDoComputador();
		st.tcComJavaIoJavaIOTestDeveSaberVerificarSeUmArquivoOuDiretorioExiste();
		st.tcComJavaIoJavaIOTestShouldKnowOperatingSystemIsWindowsOrUnixOrMacOrSolaris();
		st.tcComJavaIoJavaIOTestShouldGetSegmentsInPath();
		st.tcComJavaIoJavaIOTestShouldToListAllFilesInFolder();
		st.printResult();
	}
}
