
function createPanel(title, content){
	var panel = '<div class="panel panel-default">';
	panel += '<div class="panel-heading">';
	panel += '<h3 class="panel-title"><strong>Project:</strong>' + title +'</h3>';
	panel += '<span class="pull-right clickable"><i class="glyphicon glyphicon-minus"></i></span>';
	panel += '</div>'
	panel += '<div class="panel-body">';
	panel += '<ul class="list-group">';
	content.forEach(function(version){
		panel += '<li class="list-group-item"><a href="../../'+title+'/'+version+'/report.html">'+version+'</a></li>';
	});
	panel += '</ul>';
	panel += '</div>';
	panel += '</div>';
	return panel;
}

$(function(){
	var bgRowFluid = '<div class="row-fluid>"';
	var bgCol = '<div class="col-md-12">';

	var endDiv = "</div>";

	$('#mainpanel').append(bgRowFluid); //fluid row

	$('#mainpanel').append(bgCol); //left col
	links.forEach(function(proj){
		$('#mainpanel').append(createPanel(proj.name, proj.versions));	
	});
	//$('#mainpanel').append('here is a text in left side.');
	$('#mainpanel').append(endDiv); // end-left-col

	$('#mainpanel').append(endDiv); // end-fluid-row
});



