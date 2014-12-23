/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the 
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.temperatureconverter.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.Temperature;

import tec.uom.se.function.ValueSupplier;
import tec.uom.se.util.SI;

import org.jboss.as.quickstarts.temperatureconverter.ejb.TemperatureAmount;
import org.jboss.as.quickstarts.temperatureconverter.ejb.TemperatureConvertEJB;

import com.ibm.icu.text.DecimalFormat;

/**
 * A simple managed bean that is used to invoke the TemperatureConvertEJB and store the response. The response is obtained by
 * invoking temperatureConvertEJB.convert().
 * 
 * Code borrowed and modified from another quickstart written by Paul Robinson
 * 
 * @author Bruce Wolfe
 * @author Werner Keil
 */
@SuppressWarnings("serial")
@Named("temperatureConverter")
@RequestScoped
public class TemperatureConverter implements Serializable, ValueSupplier<String> {

    /*
     * Injected TemperatureConvertEJB client
     */    
    @Inject
    private TemperatureConvertEJB temperatureConvertEJB;
    
    /*
     * Stores the response from the call to temperatureConvertEJB.convert()
     */
    private String temperature;
    
    private String sourceTemperature = "0.0";
    
    private Unit<Temperature> defaultScale = SI.CELSIUS;

    /**
     * Invoke temperatureConvertEJB.convert() and store the temperature
     * 
     * @param sourceTemperature The temperature to be converted
     * @param defaultScale The default source temperature scale
     */
    public void convert() {
        temperature = formatTemperature(temperatureConvertEJB.convert(
        		TemperatureAmount.parse(sourceTemperature, defaultScale)));
    }

    private String formatTemperature(Quantity<Temperature> temperature) {
    	return new DecimalFormat("###.00").format(temperature.getValue()) + " " + 
          String.valueOf(temperature.getUnit());
    }
    
    public String getSourceTemperature() {
        return sourceTemperature;
    }

    public void setSourceTemperature(String sourceTemperature) {
        this.sourceTemperature = sourceTemperature;
    }

    public Unit<Temperature> getDefaultScale() {
        return defaultScale;
    }

    public void setDefaultScale(Unit<Temperature> defaultScale) {
        this.defaultScale = defaultScale;
    }

    public String getValue() {
        return temperature;
    }

}
