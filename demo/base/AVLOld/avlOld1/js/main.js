// Author : Samuel T. C. Santos
//  main.js - application to view reports
//
// @see { http://samueltcsantos.github.io/priorj }

$(function(){

  APFDApp.hideChart();
  APFDApp.listFailures();
  
  $('#addFault').click(function(){
    APFDApp.insertFault();
  });

  $('#removeFault').click(function(){
    APFDApp.removeFault();
  });
  //"input[type='checkbox']"
  $('#addEntry').click(function(){
    APFDApp.addEntry();
  });

  $('#computeAPFD').click(function(){
    APFDApp.setEntries();
  });

  $('#openChart').click(function(){
    APFDApp.showChart();
  });

});

