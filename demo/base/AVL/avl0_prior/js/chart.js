//
// Author: Samuel T. C. Santos
// Creating APFD chart
//
// Module Chart
// @see { http://samueltcsantos.github.io/priorj }

var APFDChart = (function (){

    var chart = {
        type : 'area'
    };

    var title = {
        text : 'APFD Chart'
    };

    var credits = {
        enabled: false
    }

    var series = [];

    function Chart(chart, title, credits, series){
        this.chart = chart;
        this.title = title;
        this.credits = credits;
        this.series = series;
    }

    function getInstance(){
        var instance = new Chart(chart, title, credits, series);
        return instance;
    }

    return {
        setSeries : function (allseries){
            series = allseries;
        },
        getChart : function (){
            return getInstance();
        } 
    };
})();

