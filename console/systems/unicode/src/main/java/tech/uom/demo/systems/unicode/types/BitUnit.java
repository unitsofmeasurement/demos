/*
 *  Unit-API - Units of Measurement API for Java
 *  Copyright (c) 2005-2015, Jean-Marie Dautelle, Werner Keil, V2COM.
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
 * 3. Neither the name of JSR-363 nor the names of its contributors may be used to endorse or promote products
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
package tech.uom.demo.systems.unicode.types;

import java.util.HashMap;
import java.util.Map;

import javax.measure.Dimension;
import javax.measure.IncommensurableException;
import javax.measure.Prefix;
import javax.measure.Quantity;
import javax.measure.UnconvertibleException;
import javax.measure.Unit;
import javax.measure.UnitConverter;
import javax.measure.spi.ServiceProvider;

import systems.uom.quantity.Information;
import tech.units.indriya.unit.UnitDimension;
import tech.uom.lib.common.util.DescriptiveEnum;
import tech.uom.lib.common.function.DoubleFactorSupplier;

/**
 * Implements a measure of information. The metric system unit for this quantity is "bit".
 * @author  <a href="mailto:units@catmedia.us">Werner Keil</a>
 * @version 1.2, $Date: 2020-09-29 $
 */
public enum BitUnit implements Unit<Information>, DoubleFactorSupplier, DescriptiveEnum<BitUnit> {
	
    BIT(Constants.BIT_NAME, 1.0), // reference Unit
    Byte(Constants.BYTE_NAME, Constants.BYTE_FACTOR),
	kb(Constants.KB_NAME, 1.0e3),
    Mb(Constants.MB_NAME, 1.0e6),
    Gb(Constants.GB_NAME, 1.0e9),
    Tb(Constants.TB_NAME, 1.012),
    Pb(Constants.PB_NAME, 1.015),
    Eb(Constants.EB_NAME, 1.018),
    KB(Constants.KBYTE_NAME, Constants.BYTE_FACTOR * 1.0e3),
    MB(Constants.MBYTE_NAME, Constants.BYTE_FACTOR * 1.0e6),
    GB(Constants.GBYTE_NAME, Constants.BYTE_FACTOR * 1.0e9),
    TB(Constants.TBYTE_NAME, Constants.BYTE_FACTOR * 1.0e12),
    PB(Constants.PBYTE_NAME, Constants.BYTE_FACTOR * 1.0e15),
    EB(Constants.EBYTE_NAME, Constants.BYTE_FACTOR * 1.0e18);

    private final String description;
    private final double multFactor;

    private BitUnit(String name, double multF) {
        this.description = name;
        this.multFactor = multF;
    }

    
    public String getSymbol() {
        return name();
    }

    
    public String getDescription() {
        return description;
    }

    
    public double getFactor() {
        return multFactor;
    }

    
	public Unit<Information> getSystemUnit() {
		return BIT;
    }

	
	public String getName() {
		return name();
	}
	
    public static BitUnit getByName(String symbol) {
        if (kb.name().equals(symbol)) {
            return kb;
        } else if (Mb.name().equals(symbol)) {
            return Mb;
        } else if (Gb.name().equals(symbol)) {
            return Gb;
        } else if (Tb.name().equals(symbol)) {
            return Tb;
        }
        return BIT;
    }

    
    public Map<Unit<?>, Integer> getBaseUnits() {
        Map<Unit<?>, Integer> prodUnits = new HashMap<Unit<?>, Integer>();
        prodUnits.put(kb, Integer.valueOf(3));
        prodUnits.put(Mb, Integer.valueOf(6));
        prodUnits.put(Gb, Integer.valueOf(9));
        prodUnits.put(Tb, Integer.valueOf(12));
        prodUnits.put(Byte, Integer.valueOf((int) Constants.BYTE_FACTOR));
        prodUnits.put(KB, Integer.valueOf(3 * (int) Constants.BYTE_FACTOR));
        prodUnits.put(MB, Integer.valueOf(6 * (int) Constants.BYTE_FACTOR));
        prodUnits.put(GB, Integer.valueOf(9 * (int) Constants.BYTE_FACTOR));
        prodUnits.put(TB, Integer.valueOf(12 * (int) Constants.BYTE_FACTOR));
        return prodUnits;
    }
    
    public Unit<Information> shift(double offset) {
        return this;
    }

    
    public Unit<Information> alternate(String symbol) {
        return this;
    }

    @SuppressWarnings("unchecked")
	
    public <T extends Quantity<T>> Unit<T> asType(Class<T> type)
            throws ClassCastException {
        Unit<T> metricUnit = ServiceProvider.current().getQuantityFactory(type).getSystemUnit();
        if ((metricUnit == null) || metricUnit.isCompatible(this))
         return (Unit<T>) this;
          throw new ClassCastException("The unit: " + this //$NON-NLS-1$
              + " is not of parameterized type " + type); //$NON-NLS-1$
    }

    
    public Unit<Information> divide(double divisor) {
        return this;
    }

    
    public Unit<?> divide(Unit<?> that) {
        return this;
    }

    
    public UnitConverter getConverterTo(Unit<Information> that)
            throws UnconvertibleException {
        // currently unused
        return null;
    }

    
    public UnitConverter getConverterToAny(Unit<?> that)
            throws IncommensurableException, UnconvertibleException {
        // currently unused
        return null;
    }

    
    public Dimension getDimension() {
        return UnitDimension.of(Information.class);
    }

    
    public Unit<?> inverse() {
        return this;
    }

    
    public boolean isCompatible(Unit<?> that) {
        if (that instanceof BitUnit) return true;
        return false;
    }

    
    public Unit<Information> multiply(double factor) {
        return this;
    }

    
    public Unit<?> multiply(Unit<?> that) {
    	if (!(that instanceof BitUnit)) {
    		throw new UnconvertibleException("Incompatible unit");
    	}
//        return new BitUnit(this.getSymbol(), this.longName(), 
//        		this.getMultFactor() * ((BitUnit)that).getMultFactor());
    	return this;
    }

    
    public Unit<?> pow(int n) {
        return this;
    }

    
    public Unit<?> root(int n) {
        return this;
    }

    
    public Unit<Information> transform(UnitConverter operation) {
        return this;
    }

    
    public DescriptiveEnum<BitUnit>[] dValues() {
		return BitUnit.values();
	}


	@Override
	public Unit<Information> prefix(Prefix prefix) {
		 return this;
	}


	@Override
	public Unit<Information> shift(Number offset) {
		 return this;
	}


	@Override
	public Unit<Information> multiply(Number multiplier) {
		 return this;
	}

	@Override
	public Unit<Information> divide(Number divisor) {
		 return this;
	}
	
	@Override
	public boolean isEquivalentTo(Unit<Information> that) {
		return equals(that);
	}
}
