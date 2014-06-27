var Util = (function(){
  
  function contains(oneArray, element){
	for(var i=0; i<oneArray.length;i++){
		if (oneArray[i] === element){
			return true;
		}
	}
    return false;
  }
  
  function sum(oneArray){
    var total=0; 
    oneArray.forEach(function(element){
       total += element;
     });
    return total;
  }
  
  return {
    contains : function (oneArray,element){
      return contains(oneArray, element);
    } ,
    sum : function(oneArray){
      return sum(oneArray);
    }
  };
})();