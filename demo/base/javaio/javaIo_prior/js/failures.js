var failuresList = new Array();

$(function(){
	loadFailuresList();
});

function loadFailuresList(){
	failuresList.push('com.java.io.JavaIOTest.shouldCopyAllFilesAndFolders');
	failuresList.push('com.java.io.JavaIOTest.shouldToListAllFilesInFolder');
}

function getFailures(){
	return failuresList;
}
