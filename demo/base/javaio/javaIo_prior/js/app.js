
/**
* Author: Samuel T. C. Santos
*
* Module to Manager APFD Features and APFD Module. 
*
*/
// @see { http://samueltcsantos.github.io/priorj }

var APFDApp = (function(){
	
	var faults = [];
	var allEntries = [];
	var selectedTestCases = [];
	var selectedFaults = [];

	function addAPFDEntry(){
	var n = jQuery(".typeFault:checked").length;
		
		if (n > 0){
            jQuery(".typeFault:checked").each(function(){
                selectedFaults.push($(this).val());
            });
        }

        n = jQuery(".typeTestCase:checked").length;
        if (n > 0){
            jQuery(".typeTestCase:checked").each(function(){
                selectedTestCases.push($(this).val());
            });
        }
		var item = toStringArray(selectedTestCases);		
		var faultName =  selectedFaults[0];

		var copy = doCopy(selectedTestCases);
		allEntries.push({
			fault : faultName,
			tests : copy
		});

		addItemTypeFaultEntry('entryList',faultName, item);
		clear(selectedFaults);
		clear(selectedTestCases);
		removeItem(faultName);
		deleteElement(faults,faultName);
		unselectAll();
	}

	function clear(oneArray){
		while(oneArray.length > 0){
			oneArray.pop();
		}
	}

	function toStringArray(oneArray){
		var stringArray='[';
		for (var i=0; i < oneArray.length; i++){
			stringArray += oneArray[i];
			if (i < oneArray.length-1){
				stringArray += ', ';
			}
		}
		stringArray += ']';
		return stringArray;
	}

	function addItemTypeFaultEntry(list, faultName, item){
		var thisEntry = faultName +':'+item;
		//alert(thisEntry);
		$('#'+list).append('<li class="list-group-item"><input class="typeEntry" type="checkbox" value="' + faultName + '"/> '+thisEntry+'</li>');
	}
	function unselectAll(){
	    $('.typeTestCase').each(function() { //loop through each checkbox
	        this.checked = false;  //select all checkboxes with class "checkbox1"               
	    });
	}

	function listFailures(){
		var failures = getFailures();
		for (var i=0; i < failures.length; i++){
			addItemTypeTestCase('testList', failures[i]);
		}
	}

	function addFault(){
		var faultName = prompt('Fault Name?');
		if (!contains(faults, faultName)){
			if(faultName == '' || faultName == null || faultName == undefined){
				alert('Type a Fault Name!');
			}
			else{
				addItem('faultList', faultName);
				faults.push(faultName);
			}
		}
		else{
			alert('Item alredy added!');
		}
	}

	function removeFaults(){
		var selection = selectedCheckBoxesInFaultList();
		selection.forEach(function(element){
			deleteElement(faults,element);
			removeItem(element);
		});
	}

	function deleteElement(theArray, element){
		var index = indexOf(theArray, element);
		if (index !== undefined){
			delete(theArray[index]);
		}
	}

	function indexOf(theArray, item){
		for(var i=0; i<theArray.length;i++){
			if (theArray[i] == item){
				return i;
			}
		}
	}

	function objectLength( object ){
	  return Object.keys(object).length;
	}

	function contains(theArray, theItem){
		for (var i=0; i < theArray.length; i++){
			if (theArray[i] == theItem){
				return true;
			}
		}
		return false;
	}
	
	function selectedCheckBoxesInFaultList(){
		var n = jQuery(".typeFault:checked").length;
		var selection = [];
		if (n > 0){
            jQuery(".typeFault:checked").each(function(){
                selection.push($(this).val());
            });
        }
        return selection;
	}

	/**
	* Checking if all tests are related with faults.
	*/
	function checkEntries(){
		//failed tests positions
		var allPositions = [];
		//relation added by user 
		var added = [];
		var notContains = [];

		if ( objectLength(faults) > 0){
			alert('List each fault with at least one test case.');
		}
		else{
			allEntries.forEach(function(entry){
				var tests = entry.tests;
				tests.forEach(function(failure){
					if (!contains(added, failure)){
						added.push(failure);
					}
				});
			});

			var failures = getFailures();
			failures.forEach(function(failure){
				if(!contains(added, failure)){
					notContains.push(failure);
				}
			});
			if (notContains.length > 0){
				var message='Add a fault to:\n';
				notContains.forEach(function(test){
					message += test +'\n';
				});
				alert(message);
			}
			else{
				alert('computing apfd value...');
				Helper.setFailures(failures);
				Helper.setAPFDEntries(allEntries);
				Helper.setOrder('amc', getOrderAMC());
				Helper.setOrder('asc', getOrderASC());
				Helper.setOrder('cb', getOrderCB());
				Helper.setOrder('rnd', getOrderRND());
				Helper.setOrder('tmc', getOrderTMC());
				Helper.setOrder('tsc', getOrderTSC());

				var values = [];
				/*var apdf = 0;
				if (Helper.has('amc')){
					apfd = Helper.getAPFDValue('amc') * 100;
					values.push({
						name : 'AMC (' + apfd +'%)',
						data : Helper.getSerie('amc')
					});
				}*/
				addSerieToChart(values, 'amc');
				addSerieToChart(values, 'asc');
				addSerieToChart(values, 'cb');
				addSerieToChart(values, 'rnd');
				addSerieToChart(values, 'tmc');
				addSerieToChart(values, 'tsc');

				APFDChart.setSeries(values);
				var chart = APFDChart.getChart();
				showPanelChart();
				insertChart(chart);
				
				//values.push();
				//values.push(Helper.getAPFDValue('asc') * 100);
				//values.push(Helper.getAPFDValue('cb') * 100);
				//values.push(Helper.getAPFDValue('rnd') * 100);
				//values.push(Helper.getAPFDValue('tmc') * 100);
				//values.push(Helper.getAPFDValue('tsc') * 100);
				//alert(values);
			}
		}
		
	}

	function addSerieToChart(series, technique){
		if (Helper.has(technique)){
			var apfd = (Helper.getAPFDValue(technique) * 100).toFixed(2);
			var title = technique.toUpperCase() + '('+apfd +'%)';
			var serie = Helper.getSerie(technique);
			series.push({
				name : title,
				data : serie
			});
		}
	}

	function insertChart(chart){
		$('#container').highcharts(chart);
	}

	function addItem(list, item){
		$('#'+list).append('<li class="list-group-item" id="'+item+'"><input class="typeFault" type="checkbox" value="' + item + '"/> '+item+'</li>');
	}

	function hidePanelChart(){
		$('#container').hide();
	}

	function showPanelChart(){
		$('#container').show();
	}

	function addItemTypeTestCase(list, item){
		$('#'+list).append('<li class="list-group-item" title="'+item+'"><input class="typeTestCase" type="checkbox" value="'+item+'"/> ' + item + '</li>');
	}

	function removeItem(item){
		$('#'+item).remove();
	}
	
	function doCopy(oneArray){
		var copy = new Array();
		for (var i=0; i<oneArray.length; i++){
			copy.push(oneArray[i]);
		}
		return copy;
	}

	return {
		listFailures : function(){
			listFailures();
		},
		hideChart : function (){
			hidePanelChart();
		},
		showChart : function (){
			showPanelChart();
		},
		insertFault : function(){
			addFault();
		},
		removeFault : function(){
			removeFaults();
		},
		addEntry : function(){
			addAPFDEntry();
		},
		setEntries : function(){
			checkEntries();
		},
		getEntries : function(){
			return allEntries;
		}
	}
})();




