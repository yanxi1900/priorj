package report;

import java.util.ArrayList;
import java.util.List;

import technique.TechniquesEnum;




/**
 *
 * @author Samuel T. C. Santos
 * @version 1.0
 */
public class ExecutionOrder {

    private List<String> testCases;
    private TechniquesEnum technique;
   

    public ExecutionOrder(TechniquesEnum technique){
        testCases = new ArrayList<String>();
        this.technique = technique;
    }

    /**
     * Adiciona um novo teste na lista de testes.
     * @param testName - nome do teste.
     */
    public void addTest(String testName){
        testCases.add(testName);
    }

    /**
     * Metodo para obter a posicao do caso de teste de nome informado;
     * @param nameTest
     * @return
     */
    public int getPositionTestCase(String nameTest){
        for (int i=0; i< testCases.size(); i++ ){
            if (testCases.get(i).contains(nameTest))
                return i+1;
        }
        //indica que nao localizou o teste!
        return -1;
    }

    /**
     * Retorna a posicao do menor caso de teste
     * numa lista de nomes de casos de testes
     * informada.
     * 
     * @param tests - uma lista de casos de testes.
     * @return - a posicao do menor.
     */
    public int getPositionTestCaseLess(String[] tests){
       int less= getPositionTestCase(tests[0]);
       
       for(String test: tests){
           int position = getPositionTestCase(test);
         
           if (position < less){
               less=position;
           }
       }

       return less;
    }

     public int getPositionTestCaseLess(Object[] tests){

       int less= getPositionTestCase(tests[0].toString());

       for(Object test: tests){
           int position = getPositionTestCase(test.toString());
      
           if (position < less){
               less=position;
           }
       }

       return less;
    }


    /**
     * Get the tests list in order of execution.
     * 
     * @return - a list of tests cases names.
     */
    public List<String> getTestsOrder(){
        return testCases;
    }

}
