/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihframework.osplugins.funsionchart.model;

import java.util.List;

public class Dataset {
	private String seriesName;

	private String color;

	private String showValue;

	private String lineThickness;

	private String yaxismaxvalue;

	private String anchorSides;

	private String anchorRadius;

	private String anchorAlpha;

	private String parentYAxis;

	private List<Set> set;

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getShowValue() {
		return showValue;
	}

	public void setShowValue(String showValue) {
		this.showValue = showValue;
	}

	public String getLineThickness() {
		return lineThickness;
	}

	public void setLineThickness(String lineThickness) {
		this.lineThickness = lineThickness;
	}

	public String getYaxismaxvalue() {
		return yaxismaxvalue;
	}

	public void setYaxismaxvalue(String yaxismaxvalue) {
		this.yaxismaxvalue = yaxismaxvalue;
	}

	public String getAnchorSides() {
		return anchorSides;
	}

	public void setAnchorSides(String anchorSides) {
		this.anchorSides = anchorSides;
	}

	public String getAnchorRadius() {
		return anchorRadius;
	}

	public void setAnchorRadius(String anchorRadius) {
		this.anchorRadius = anchorRadius;
	}

	public String getAnchorAlpha() {
		return anchorAlpha;
	}

	public void setAnchorAlpha(String anchorAlpha) {
		this.anchorAlpha = anchorAlpha;
	}

	public String getParentYAxis() {
		return parentYAxis;
	}

	public void setParentYAxis(String parentYAxis) {
		this.parentYAxis = parentYAxis;
	}

	public List<Set> getSet() {
		return set;
	}

	public void setSet(List<Set> set) {
		this.set = set;
	}
}
