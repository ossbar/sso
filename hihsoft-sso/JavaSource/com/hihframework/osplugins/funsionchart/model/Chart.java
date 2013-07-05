package com.hihframework.osplugins.funsionchart.model;

import java.util.List;



public class Chart {
	private String numberSuffix;

	private String palette;

	private String lineThickness;

	private String showValues;

	private String labelDisplay;

	private String slantLabels;

	private String areaOverColumns;

	private String useRoundEdges;

	private List<Category> categories;

	private List<Dataset> dataset;

	public String getNumberSuffix() {
		return numberSuffix;
	}

	public void setNumberSuffix(String numberSuffix) {
		this.numberSuffix = numberSuffix;
	}

	public String getPalette() {
		return palette;
	}

	public void setPalette(String palette) {
		this.palette = palette;
	}

	public String getLineThickness() {
		return lineThickness;
	}

	public void setLineThickness(String lineThickness) {
		this.lineThickness = lineThickness;
	}

	public String getShowValues() {
		return showValues;
	}

	public void setShowValues(String showValues) {
		this.showValues = showValues;
	}

	public String getLabelDisplay() {
		return labelDisplay;
	}

	public void setLabelDisplay(String labelDisplay) {
		this.labelDisplay = labelDisplay;
	}

	public String getSlantLabels() {
		return slantLabels;
	}

	public void setSlantLabels(String slantLabels) {
		this.slantLabels = slantLabels;
	}

	public String getAreaOverColumns() {
		return areaOverColumns;
	}

	public void setAreaOverColumns(String areaOverColumns) {
		this.areaOverColumns = areaOverColumns;
	}

	public String getUseRoundEdges() {
		return useRoundEdges;
	}

	public void setUseRoundEdges(String useRoundEdges) {
		this.useRoundEdges = useRoundEdges;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Dataset> getDataset() {
		return dataset;
	}

	public void setDataset(List<Dataset> dataset) {
		this.dataset = dataset;
	}
}
