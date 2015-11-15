/*
 *  Unit-API - Units of Measurement API for Java
 *  Copyright (c) 2005-2015, Jean-Marie Dautelle, Werner Keil, V2COM.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of JSR-363, Unit-API nor the names of their contributors may be used to endorse or promote products derived from this software without specific prior written permission.
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
package tec.uom.demo.se.systems;

import java.time.Duration;
import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.quantity.Speed;
import javax.measure.quantity.Time;

import systems.uom.common.US;
import tec.uom.se.quantity.Quantities;
import tec.uom.se.quantity.time.TemporalQuantity;
import tec.uom.se.quantity.time.TimeQuantities;
import tec.uom.se.unit.Units;

public class AirplaneDemo {

	public static void main(String[] args) {
		Quantity<Length> distance = Quantities.getQuantity(5999, US.MILE);		
		Quantity<Speed> airplaneSpeed = getAirplaneSpeed();
		//Quantity<Time> eta = (Quantity<Time>)distance.divide(airplaneSpeed);
		Quantity<Time> timeToDest = distance.divide(airplaneSpeed).asType(Time.class);
		TemporalQuantity tuqToDest = TimeQuantities.toTemporalSeconds(timeToDest);
		System.out.println("TTD: " + timeToDest.to(Units.HOUR));
		System.out.println("TTD (Duration): " + Duration.from(tuqToDest.getTemporalAmount()));
		//System.out.println("ETA: " + eta.to(Units.HOUR)); // TODO ETA could be done based on current time
	}
	
	private static final Quantity<Speed> getAirplaneSpeed() {
		return Quantities.getQuantity(945, Units.KILOMETRES_PER_HOUR); // Airbus A 380 Cruise speed
	}
}