package edu.hm.sinning_f.aufg_factorypattern.counterfactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.hm.sinning_f.aufg_factorypattern.fakecounterfactory.FakeCounterFactory;
import edu.hm.sinning_f.aufg_factorypattern.switchedcounterfactory.SwitchedCounterFactory;
/**A Factory that creates other factories.
 * @author sinning fabian
 * */
public abstract class CounterFactory {
	/**A list that contains the factories.
	 * @author sinning fabian
	 * */
	private static final List<CounterFactory> FACTORIES = new ArrayList<>(Arrays.asList(new CounterFactory[]{new FakeCounterFactory(),new SwitchedCounterFactory()}));
	/**Getter that returns a Factory.
	 * @throws IllegalArgumentException if the system property was invalid
	 * @return A CounterFactory
	 * */
	public static CounterFactory get() throws IllegalArgumentException{
		CounterFactory factory = null;
		
		switch(System.getProperty("factory.type")){
			case "Fake" :
			case "FakeCounterFactory" : factory = FACTORIES.get(0);
										break;
			case "Switched" :
			case "SwitchedCounterFactory" :  factory = FACTORIES.get(1);
										break;
			default : throw new IllegalArgumentException();
		}
		return factory;
	}
}
