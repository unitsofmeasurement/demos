package tech.uom.demo.systems.common;

import static javax.measure.MetricPrefix.KILO;
import static javax.measure.MetricPrefix.NANO;
import static systems.uom.common.USCustomary.LITER;

import javax.measure.Quantity;
import javax.measure.format.UnitFormat;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Volume;

import systems.uom.common.IndianPrefix;
import tech.units.indriya.format.SimpleUnitFormat;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

public class CommonPrefixDemo {
	public static void main(String... args) {
		Quantity<Length> len = Quantities.getQuantity(10, IndianPrefix.LAKH(Units.METRE));
		System.out.println(len);
		Quantity<Mass> kg = Quantities.getQuantity(50, KILO(Units.GRAM));
		System.out.println(kg);
		
		System.out.println(Quantities.getQuantity(3.3, LITER).toString());
		Quantity<Volume> nl = Quantities.getQuantity(3.3, NANO(LITER));
		System.out.println(nl.toString());
		//UnitFormat format = EBNFUnitFormat.getInstance();
		UnitFormat format = SimpleUnitFormat.getInstance();
		System.out.println(format.format(nl.getUnit()));
	}
}
