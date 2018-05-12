package edu.hm.sinning_f.aufg_factorypattern.switchedcounterfactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import edu.hm.cs.rs.arch.a05_decorator.Counter;
import edu.hm.sinning_f.aufg_factorypattern.counterfactory.CounterFactory;
import edu.hm.sinning_f.elem_zaehler.ClearCounter;
import edu.hm.sinning_f.elem_zaehler.LoopCounter;
import edu.hm.sinning_f.elem_zaehler.NaryCounter;
import edu.hm.sinning_f.elem_zaehler.UCounter;
import edu.hm.sinning_f.filter_zaehler.JumpCounter;
import edu.hm.sinning_f.filter_zaehler.LimitedCounter;
import edu.hm.sinning_f.filter_zaehler.PrintCounter;
import edu.hm.sinning_f.filter_zaehler.ShiftedCounter;
import edu.hm.sinning_f.filter_zaehler.SlowCounter;

/**Factory that creates Basiccounter and Filtercounter.
 * @author sinning fabian
 * */
public class SwitchedCounterFactory extends CounterFactory{
	/**Map that holds all Basiccounter.
	 * */
	private final Map<String, Function<int[], Counter>> mapbasiscounter;
	/**Map that holds all Filtercounter.
	 * */
	private final Map<String, BiFunction<Counter,Integer,Counter>> mapfiltercounter;
	/**Creates a SwitchedCounterFactory.
	 * */
	public SwitchedCounterFactory(){
		mapbasiscounter = new HashMap<String, Function<int[], Counter>>();
		mapfiltercounter = new HashMap<String, BiFunction<Counter,Integer,Counter>>();
		
		mapbasiscounter.put("U", arg-> new UCounter());
		mapbasiscounter.put("UCounter", arg-> new UCounter());
		mapbasiscounter.put("Nary", arg-> new NaryCounter(arg[0]));
		mapbasiscounter.put("NaryCounter", arg-> new NaryCounter(arg[0]));
		mapbasiscounter.put("Clear", arg-> new ClearCounter());
		mapbasiscounter.put("ClearCounter", arg-> new ClearCounter());
		mapbasiscounter.put("Loop", arg-> new LoopCounter(arg));
		mapbasiscounter.put("LoopCounter", arg-> new LoopCounter(arg));
		
		mapfiltercounter.put("Jump", (counter,args) -> new JumpCounter(counter, args));
		mapfiltercounter.put("JumpCounter", (counter,args) -> new JumpCounter(counter, args));
		mapfiltercounter.put("Limited", (counter,args) -> new LimitedCounter(counter, args));
		mapfiltercounter.put("LimitedCounter", (counter,args) -> new LimitedCounter(counter, args));
		mapfiltercounter.put("Print", (counter,args) -> new PrintCounter(counter, (char)args.intValue()));
		mapfiltercounter.put("PrintCounter", (counter,args) -> new PrintCounter(counter, (char)args.intValue()));
		mapfiltercounter.put("Shifted", (counter,args) -> new ShiftedCounter(counter, args));
		mapfiltercounter.put("ShiftedCounter", (counter,args) -> new ShiftedCounter(counter, args));
		mapfiltercounter.put("Slow", (counter,args) -> new SlowCounter(counter, args));
		mapfiltercounter.put("SlowCounter", (counter,args) -> new SlowCounter(counter, args));
	}
	/**Creates a Basiccounter.
	 * @param typename the Name of the Basiccounter
	 * @param args the inputargument of the Basiccounter
	 * @throws IllegalArgumentException if inputarguments are invalid
	 * @return Basiccounter
	 * */
	public Counter make(String typename, int... args) throws IllegalArgumentException{
		if(mapbasiscounter.get(typename) == null){
			throw new IllegalArgumentException();
		}
		return mapbasiscounter.get(typename).apply(args);
	}
	/**Creates a Filtercounter.
	 * @param other a Counter
	 * @param typename the Name of the Counter
	 * @param arg the inputargument of the Counter
	 * @throws IllegalArgumentException if inputarguments are invalid
	 * @return Counter
	 * */
	public Counter make(Counter other, String typename, int arg) throws IllegalArgumentException{
		if(mapfiltercounter.get(typename) == null){
			throw new IllegalArgumentException();
		}
		return mapfiltercounter.get(typename).apply(other, arg);
	}
}
