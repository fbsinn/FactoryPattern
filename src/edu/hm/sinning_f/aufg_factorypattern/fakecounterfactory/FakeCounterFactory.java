package edu.hm.sinning_f.aufg_factorypattern.fakecounterfactory;

import edu.hm.cs.rs.arch.a05_decorator.Counter;
import edu.hm.sinning_f.aufg_factorypattern.counterfactory.CounterFactory;
/**A Factory that produces a Counter.
 * @author sinning fabian
 * */
public class FakeCounterFactory extends CounterFactory{
	/**The Counter of that factory.
	 * */
	private final Counter counter;
	/**Creates a FakeFactory.
	 * */
	public FakeCounterFactory(){
		counter = new Counter() {
			
			@Override
			public Counter tick() {
				return this;
			}
			
			@Override
			public int read() {
				return 0;
			}
		};
	}
	/**Creates always the same Counter independently of the input arguments.
	 * @param typename the Name of the Basiccounter
	 * @param args the input arguments of the Counter
	 * @return a Counter
	 * */
	public Counter make(String typename, int... args) {
		return counter;
	}
	/**Creates always the same Counter independently of the input arguments.
	 * @param other a Counter
	 * @param typename the Name of the Counter
	 * @param arg the input arguments of the counter
	 * @return a Counter
	 * */
	public Counter make(Counter other, String typename, int arg){
		return counter;
	}
}
