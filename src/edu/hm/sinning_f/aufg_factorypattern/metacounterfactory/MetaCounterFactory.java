package edu.hm.sinning_f.aufg_factorypattern.metacounterfactory;

import edu.hm.cs.rs.arch.a05_decorator.Counter;
import edu.hm.sinning_f.aufg_factorypattern.counterfactory.CounterFactory;
import edu.hm.sinning_f.aufg_factorypattern.fakecounterfactory.FakeCounterFactory;
import edu.hm.sinning_f.aufg_factorypattern.switchedcounterfactory.SwitchedCounterFactory;
/**Factory that produces a Counter.
 * @author sinning fabian
 * */
public class MetaCounterFactory extends CounterFactory{
	
	/**Creates a basic Counter.
	 * @param typename the name of the counter
	 * @param args the input arguments of the counter
	 * @return A basic counter
	 * */
	public Counter make(String typename, int... args){
		Counter counter = null;
		System.setProperty("factory.type", "SwitchedCounterFactory");
		try{
			counter = ((SwitchedCounterFactory)get()).make(typename, args);
		}catch(IllegalArgumentException illegalexc){
			System.setProperty("factory.type", "FakeCounterFactory");
			counter = ((FakeCounterFactory)get()).make(typename, args);
		}
		return counter;
	}
	/**Creates a filter counter.
	 * @param other a counter
	 * @param typename the name of the counter
	 * @param arg the input argument of the counter
	 * @return A decorated counter
	 * */
	public Counter make(Counter other, String typename, int arg){
		Counter counter = null;
		System.setProperty("factory.type", "SwitchedCounterFactory");
		try{
			counter = ((SwitchedCounterFactory)get()).make(other, typename, arg);
		}catch(IllegalArgumentException illegalexc){
			System.setProperty("factory.type", "FakeCounterFactory");
			counter = ((FakeCounterFactory)get()).make(other, typename, arg);
		}
		return counter;
	}
}
