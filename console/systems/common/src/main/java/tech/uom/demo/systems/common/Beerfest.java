/*
 *  Units of Measurement Console Demos
 *  Copyright (c) 2005-2021, Werner Keil and others.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions
 *    and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the names of JSR-363, Units of Measurement nor the names of its contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package tech.uom.demo.systems.common;

import static tech.uom.demo.systems.common.types.BeerUnits.*;

import javax.measure.Quantity;
import javax.measure.quantity.Volume;

import systems.uom.common.Imperial;
import systems.uom.common.USCustomary;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

public class Beerfest {

	public static void main(String[] args) {
		System.out.println("Ozapft is!");
		Quantity<Volume> v = Quantities.getQuantity(1, Units.LITRE);
		System.out.println(v);
		
		System.out.println();
		System.out.println("Very British");
		System.out.println(v.to(Imperial.PINT)); 
		
		System.out.println();
		System.out.println("Für die Amis");
		System.out.println(v.to(USCustomary.FLUID_OUNCE)); 
		System.out.println(v.to(USCustomary.PINT));
		
		System.out.println();
		System.out.println("Oktoberfest 1900");		
		v = Quantities.getQuantity(1, MASS_HISTORIC);
		System.out.println(v);
		System.out.println(v.to(SCHOPPEN_BAYERN));
		System.out.println(v.to(Units.LITRE));
	}

}
