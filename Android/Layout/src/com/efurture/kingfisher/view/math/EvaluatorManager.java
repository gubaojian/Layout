/**
 * 
 */
package com.efurture.kingfisher.view.math;

/**
 * @author gubaojian   email: gubaojian@163.com
 **/
public class EvaluatorManager {

    /**
     *  default math expression evaluator
     * */
    private  static Evaluator  defaultEvaluator;
	
    
     /**
	  * return default math expression evaluator
	  * */
    public static Evaluator getDefaultEvaluator() {
         if (defaultEvaluator == null) {
            synchronized (EvaluatorManager.class) {
           	   if (defaultEvaluator == null) {
           		   defaultEvaluator = new Exp4jEvaluator();
           	   }
            }		
		 }
		 return defaultEvaluator;
	}

	public static void setDefaultEvaluator(Evaluator defaultEvaluator) {
		EvaluatorManager.defaultEvaluator = defaultEvaluator;
	}
}
