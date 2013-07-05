/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihframework.osplugins.jxl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * <p> Title:用于处理Excel文件的数据导入</p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *  @author hihsoft.co.,ltd
 * @version 1.0
 */
public abstract class ExcelImport {
	private String excelFileName;

	private boolean DataFormat = false;//true:数据流，false文件名

	private InputStream is;

	private Workbook wb;

	private WritableWorkbook wwb;

	private Sheet sheet;

	private WritableSheet wsheet;

	public ExcelImport(String excelFileName) {
		wb = null;
		wwb = null;
		sheet = null;
		wsheet = null;
		DataFormat = false;
		this.excelFileName = excelFileName;
	}

	public ExcelImport(InputStream isFile) {
		wb = null;
		wwb = null;
		sheet = null;
		wsheet = null;
		DataFormat = true;
		this.is = isFile;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}

	/**
	 * 创建一个只读的Workbook对象，对excel的任何读操作都要先用此方法定位到某一文件
	 *
	 * @param
	 * @return 只读的Workbook对象
	 */
	public Workbook open() throws Exception {
		WorkbookSettings setting = new WorkbookSettings();
		if (DataFormat) { //true:数据流，false文件名
			try {

				java.util.Locale locale = new java.util.Locale("zh", "CN");
				setting.setLocale(locale);
				
				wb = Workbook.getWorkbook(is);//通过文件输入流创建读取Excel的workbook对象
				return wb;

			} catch (IOException ie) {
				throw new Exception("文件流错误或工作表IO错误", ie);
			}

		} else {
			if (excelFileName == null)
				throw new Exception("Excel文件没指定");
			try {
				is = new FileInputStream(excelFileName);//创建文件输入流
				wb = Workbook.getWorkbook(is, setting);//通过文件输入流创建读取Excel的workbook对象
				return wb;

			} catch (IOException ie) {
				throw new Exception("文件不存在或工作表IO错误", ie);
			}
		}
	}

	/**
	 * 创建一个可写的WritableWorkbook对象
	 *
	 * @param 要写入的文件对象
	 * @return 可写的excel文件对象
	 */
	public WritableWorkbook openWrite() throws Exception {
		if (DataFormat) { //true:数据流，false文件名
			//wwb = Workbook.createWorkbook(file);
			return wwb;
		} else {

			if (excelFileName == null)
				throw new Exception("Excel文件没指定");

			try {
				File file = new File(excelFileName);
				wwb = Workbook.createWorkbook(file);//创建可写的Excel的WritableWorkbook对象
				return wwb;

			} catch (IOException ie) {
				throw new Exception("文件不存在或工作表IO错误", ie);
			}
		}
	}

	public boolean Import;

	/**
	 * 创建一个只读的Workbook对象，对excel的任何读操作都要先用此方法定位到某一文件
	 *
	 * @return 只读的Workbook对象
	 */
	public void close() {
		if (wb != null)
			wb.close();

		if (wwb != null) {
			try {
				wwb.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public Workbook getWb() {
		return wb;
	}

	public void setWb(Workbook wb) {
		this.wb = wb;
	}

	public Sheet getSheet() {
		return sheet;
	}

	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	public WritableSheet getWsheet() {
		return wsheet;
	}

	public void setWsheet(WritableSheet wsheet) {
		this.wsheet = wsheet;
	}

	public WritableWorkbook getWwb() {
		return wwb;
	}

	public void setWwb(WritableWorkbook wwb) {
		this.wwb = wwb;
	}

	/**
	 * 写入工作表，任何创建或者修改更新的操作最后都要调用此方法。
	 */
	public void writeExcel() {

		try {
			if (wwb != null)
				wwb.write();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	/**
	 * 获取此只读工作表对象
	 *
	 * @param sheetName 工作表名
	 * @return 只读的工作表对象
	 */
	public Sheet getSheet(String sheetName) throws Exception {
		if (wb != null) {
			sheet = wb.getSheet(sheetName);
			return sheet;
		} else
			return null;
	}

	/**
	 * 获得第n个工作表对象
	 *
	 * @param sheetNo 工作表序号，从0开始算第一个
	 * @return 工作表对象
	 */
	public Sheet getSheet(int sheetNo) throws Exception {
		if (wb != null) {
			sheet = wb.getSheet(sheetNo);
			return sheet;
		} else
			return null;
	}

	/**
	 * 得到某行某列的单元格内容，返回字符串型 如果某个单元格类型为数值型并且内容为空的话，那读取出的内容应为0。
	 *
	 * @param col 列数
	 * @param row 行数
	 * @return 单元格内容
	 */
	public String getContents(int col, int row) {
		//得到第几列第几行的单元格的单元格内容
		if (sheet != null) {

			Cell cell = sheet.getCell(col, row);
			return cell.getContents();
			//            if (cell.getType() == CellType.NUMBER) {
			//                NumberCell numcell = (NumberCell) cell;
			//
			//                return Double.toString(Math.round(numcell.getValue()));
			//
			//            } else {
			//                return cell.getContents();
			//            }
		} else {
			return "";
		}
	}

	/**
	 * 得到某行某列的单元格内容，返回数值 如果单元格类型不是数值型,那读取出的内容应为0。
	 *
	 * @param col 列数
	 * @param row 行数
	 * @return 单元格内容
	 */
	public double getNumber(int col, int row) {
		//得到第几列第几行的单元格的单元格内容
		if (sheet != null) {

			Cell cell = sheet.getCell(col, row);
			double Number = 0;
			if (cell.getType() == CellType.NUMBER) {
				NumberCell numcell = (NumberCell) cell;
				Number = numcell.getValue();
			}

			return Number;

		} else {
			return 0;
		}
	}

	/**
	 * 得到某行某列的单元格内容，返回数值 如果单元格类型不是数值型,那读取出的内容应为0。
	 *
	 * @param col 列数
	 * @param row 行数
	 * @return 单元格内容
	 */
	public BigDecimal getBigDecimal(int col, int row) {
		//得到第几列第几行的单元格的单元格内容
		if (sheet != null) {

			Cell cell = sheet.getCell(col, row);
			String data = cell.getContents();
			BigDecimal value = new BigDecimal(0);
			try {
				value = new BigDecimal(data);
			} catch (Exception e) {
				value = new BigDecimal(0);
			}
			return value;

			//            if (cell.getType() == CellType.NUMBER) {
			//                String data = cell.getContents();
			//                BigDecimal value = new BigDecimal(0);
			//                try {
			//                    value = new BigDecimal(data);
			//                } catch (Exception e) {
			//                    value = new BigDecimal(0);
			//                }
			//
			//                return value;
			//            } else {
			//
			//                return new BigDecimal("0");
			//            }

		} else {
			return new BigDecimal(0);
		}
	}

	/**
	 * 得到某行某列的单元格内容，返回日期 如果单元格类型不是日期型,那读取出的内容应为null。
	 *
	 * @param col 列数
	 * @param row 行数
	 * @return 单元格内容
	 */
	public Date getDate(int col, int row) {
		//得到第几列第几行的单元格的单元格内容
		if (sheet != null) {
			Cell cell = sheet.getCell(col, row);
			Date date = null;
			if (cell.getType() == CellType.DATE
					|| cell.getType() == CellType.DATE_FORMULA) {
				DateCell var = (DateCell) cell;
				date = var.getDate();

			}
			return date;

		} else {
			return null;
		}
	}

	/**
	 * 得到某一工作表的名称
	 *
	 * @return 工作表的名称
	 */
	public String getSheetName() {

		if (sheet != null) {
			String sheetName = sheet.getName();
			return sheetName;
		} else {
			return "";
		}
	}

	/**
	 * 取得某一Excel文件的工作表的数量
	 *
	 * @return 工作表数量
	 */
	public int getSheetCount() {
		if (wb != null) {
			int sheets = wb.getNumberOfSheets();
			return sheets;
		} else {
			return 0;
		}
	}

	/**
	 * 得到某一工作表的总列数
	 *
	 * @param 工作表对象名称
	 *  * @return 总列数
	 */
	public int getSheetColumnsCount(String sheetName) {
		if (wb != null) {
			sheet = wb.getSheet(sheetName);

		} else
			sheet = null;

		if (sheet != null) {
			int columnCount = sheet.getColumns();
			return columnCount;
		} else {
			return 0;
		}

	}

	/**
	 * 得到某一工作表的总行数
	 *
	 * @param sheetName 工作表对象名称
	 * @return 总行数
	 */
	public int getSheetRowCount(String sheetName) {
		if (wb != null) {
			sheet = wb.getSheet(sheetName);

		} else
			sheet = null;
		if (sheet != null) {
			int rowCount = sheet.getRows();
			return rowCount;
		} else {
			return 0;
		}

	}

	/**
	 * 得到某一工作表的总列数
	 *
	 * @param 工作表对象
	 * @return 总列数
	 */
	public int getSheetColumnsCount() {

		if (sheet != null) {
			int columnCount = sheet.getColumns();
			return columnCount;
		} else {
			return 0;
		}

	}

	/**
	 * 得到某一工作表的总行数
	 *
	 * @param 工作表对象
	 * @return 总行数
	 */
	public int getSheetRowCount() {

		if (sheet != null) {
			int rowCount = sheet.getRows();
			return rowCount;
		} else {
			return 0;
		}

	}

	/**
	 * 创建一个可写入的工作表
	 *
	 * @param 可写的WritableWorkbook对象
	 * @param sheetName             要创建的工作表名
	 * @param sheetNo               要创建的工作表序号
	 * @return 创建过的工作表
	 */
	public WritableSheet getWritableSheet(String sheetName, int sheetNo)
			throws Exception {

		if (wwb != null) {
			wsheet = wwb.createSheet(sheetName, sheetNo);
			return wsheet;
		} else
			return null;
	}

	/**
	 * 根据工作表名获取此可读可写工作表对象
	 *
	 * @param sheetName 工作表名
	 * @return 可写的工作表对象
	 * @throws Exception
	 */
	public WritableSheet getWsheet(String sheetName) throws Exception {

		if (wwb != null) {

			wsheet = wwb.getSheet(sheetName);

			return wsheet;
		} else {
			return null;
		}
	}

	/**
	 * 根据工作表序号获取此可读可写工作表对象
	 *
	 * @param 工作表序号
	 * @return 可写的工作表对象
	 * @throws Exception
	 */
	public WritableSheet getWsheet(int sheetNo) throws Exception {
		if (wwb != null) {
			wsheet = wwb.getSheet(sheetNo);
			return wsheet;
		} else {
			return null;
		}
	}

	/**
	 * 在工作表中某个单元格插入一个字符串值
	 *
	 * @param column 列号
	 * @param row    行号
	 * @param label  要插入的字符串
	 */
	public boolean addString(int column, int row, String label)
			throws Exception {

		if (wsheet != null) {
			try {
				Label labelC = new Label(column, row, label);
				wsheet.addCell(labelC);
			} catch (Exception e) {
				return false;
			}
			return true;

		} else
			return false;
	}

	/**
	 * 在工作表中某个单元格插入一个数值
	 *
	 * @param column 列号
	 * @param row    行号
	 * @param 要插入的数值
	 */
	public boolean addNumber(int column, int row, double number) {

		if (wsheet != null) {
			try {
				jxl.write.Number labelN = new jxl.write.Number(column, row,
						number);
				wsheet.addCell(labelN);
			} catch (RowsExceededException ree) {
				ree.printStackTrace();
				return false;
			} catch (WriteException we) {
				we.printStackTrace();
				return false;
			}
			return true;

		} else
			return false;
	}

	/**
	 * 在工作表中某个单元格插入一个布尔型的值
	 *
	 * @param column    列号
	 * @param row       行号
	 * @param 要插入的布尔型的值
	 */
	public boolean addBoolean(int column, int row, boolean bolean) {

		if (wsheet != null) {

			try {
				jxl.write.Boolean labelB = new jxl.write.Boolean(column, row,
						bolean);
				wsheet.addCell(labelB);
			} catch (RowsExceededException ree) {
				ree.printStackTrace();
				return false;
			} catch (WriteException we) {
				we.printStackTrace();
				return false;
			}
			return true;

		} else
			return false;
	}

	/**
	 * 在工作表某一单元格中添加日期类型的数据
	 *
	 * @param column 列号
	 * @param row    行号
	 * @param date   要插入的日期类型的数据
	 */
	public boolean addDateTime(int column, int row, Date date) {

		if (wsheet != null) {
			try {
				jxl.write.DateTime labelDT = new jxl.write.DateTime(column,
						row, date);
				wsheet.addCell(labelDT);
			} catch (RowsExceededException ree) {
				ree.printStackTrace();
				return false;
			} catch (WriteException we) {
				we.printStackTrace();
				return false;
			}

			return true;

		} else
			return false;

	}

	/**
	 * 合并单元格
	 *
	 * @param beginColumn 开始列
	 * @param beginRow    开始行
	 * @param endColumn   结束列
	 * @param endRow      结束行
	 */
	public boolean mergeCells(int beginColumn, int beginRow, int endColumn,
			int endRow) {

		if (wsheet != null) {
			try {
				wsheet.mergeCells(beginColumn, beginRow, endColumn, endRow);
			} catch (RowsExceededException ree) {
				ree.printStackTrace();
				return false;
			} catch (WriteException we) {
				we.printStackTrace();
				return false;
			}
			return true;
		} else
			return false;
	}

	/**
	 * 设置工作表中某一列的列宽
	 *
	 * @param columnNo 列号
	 * @param width    列宽
	 */
	public boolean setColumnWidth(int columnNo, int width) {

		if (wsheet != null) {
			wsheet.setColumnView(columnNo, width);
			return true;
		} else
			return false;
	}

	/**
	 * 设置工作表中某一行的行高
	 *
	 * @param 工作表对象
	 * @param rowNo  行号
	 * @param height 行高
	 */
	public boolean setRowHeight(int rowNo, int height) {

		if (wsheet != null) {
			try {
				wsheet.setRowView(rowNo, height);
			} catch (RowsExceededException ree) {
				ree.printStackTrace();
				return false;
			}
			return true;
		} else
			return false;

	}

}
