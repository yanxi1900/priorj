
// Author : Samuel T. C. Santos
// graph.js
//
//  A project template for using arbor.js
//

 $(document).ready(function(){
    var sys = arbor.ParticleSystem(1000, 600, 0.5) // create the system with sensible repulsion/stiffness/friction
    sys.parameters({gravity:true}) // use center-gravity to make the graph settle nicely (ymmv)
    sys.renderer = Renderer("#viewport") // our newly created renderer will have its .init() method called shortly by sys...

    // add some nodes to the graph and watch it go...

    GraphData.load();
    var suites = GraphData.getData();

    console.log(suites)
    suites.forEach(function(node){

      var suiteconfig = {'color':'red','shape':'box','label':node.name};
      var suite = sys.addNode(node.name, suiteconfig);
      
      var testconfig = {'color':'gray','shape':'box','label':node.testcase.name};
      var test = sys.addNode(node.testcase.name, testconfig);

      var classcodeconfig = {'color':'orange','shape':'box','label':node.testcase.classcode.name};
      var classcode = sys.addNode(node.testcase.classcode.name, classcodeconfig); 

      var methodconfig = {'color':'green','shape':'box','label':node.testcase.classcode.method.name};
      var method = sys.addNode(node.testcase.classcode.method.name, methodconfig);

      var lines = node.testcase.classcode.method.lines;

      sys.addEdge(suite,test);
      sys.addEdge(test, classcode);
      sys.addEdge(classcode, method);

      lines.forEach(function (line){
        var lineconfig =  {'color':'black','shape':'dot','label':line};
        var nodeline = sys.addNode(line, lineconfig);
        sys.addEdge(method, nodeline);         
      });
      
    });
    
});