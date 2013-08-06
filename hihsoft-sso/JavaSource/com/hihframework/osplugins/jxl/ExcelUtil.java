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
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.log4j.Logger;

import com.hihframework.core.utils.ReflectUtil;

/**
 * <p> Title:利用jxl读取和写入Excel的公用方法</p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *  @author hihsoft.co.,ltd
 * @version 1.0
 */
public class ExcelUtil {
	protected final Logger log = Logger.getLogger(this.getClass());

	// ----------------------------读文件方法--------------------------------------

	/**
	 * 创建一个只读的Workbook对象，对excel的任何读操作都要先用此方法定位到某一文件
	 *
	 * @param file 要读取的excel文件对象
	 * @return 只读的Workbook对象
	 */
	public Workbook createWorkbook(File file) throws Exception {
		InputStream is = new FileInputStream(file);// 创建文件输入流
		Workbook wb = Workbook.getWorkbook(is);// 通过文件输入流创建读取Excel的workbook对象
		return wb;
	}

	/**
	 * 获得第一个工作表对象
	 *
	 * @param wb:只读的Workbook对象
	 * @param sheetName:工作表序号，从0开始算第一个
	 * @return 工作表对象
	 * @throws Exception
	 */
	public Sheet getSheet(Workbook wb, int sheetNo) throws Exception {
		// Workbook book = createWorkbook(file);
		Sheet sheet = wb.getSheet(sheetNo);
		return sheet;

	}

	/**
	 * 根据工作表名获取此只读工作表对象
	 *
	 * @param wb        只读的Workbook对象
	 * @param sheetName 工作表名
	 * @return 只读的工作表对象
	 * @throws Exception
	 */
	public Sheet getSheet(Workbook wb, String sheetName) throws Exception {
		Sheet sheet = wb.getSheet(sheetName);
		return sheet;
	}

	/**
	 * 获得第一个工作表对象
	 *
	 * @param file    要读取的excel文件对象
	 * @param sheetNo 工作表序号
	 * @return 工作表对象
	 * @throws Exception
	 */
	public Sheet getSheet(File file, int sheetNo) throws Exception {
		InputStream is = new FileInputStream(file);
		Workbook wb = Workbook.getWorkbook(is);
		Sheet sheet = wb.getSheet(sheetNo);
		return sheet;
	}

	/**
	 * 得到某行某列的单元格内容，返回字符串型 
	 * 如果某个单元格类型为数值型并且内容为空的话，
	 * 那读取出的内容应为0。
	 *
	 * @param sheet  工作表对象
	 * @param column 列数
	 * @param row    行数
	 * @return 单元格内容
	 * @throws Exception
	 */
	public String getContents(Sheet sheet, int column, int row)
			throws Exception {
		// 得到第几列第几行的单元格的单元格内容
		Cell cell = sheet.getCell(column, row);
		String result = cell.getContents();
		return result;
	}

	/**
	 * 取得某一Excel文件的工作表的数量
	 * @param wb Excel文件对象
	 * @return 工作表数量
	 */
	public int getSheetCount(Workbook wb) {
		int sheets = wb.getNumberOfSheets();
		return sheets;
	}

	/**
	 * 得到Excel文件的所有工作表对象
	 *
	 * @param wb Excel文件对象
	 * @return Excel文件的所有工作表对象
	 */
	public Sheet[] getSheet(Workbook wb) {
		Sheet[] sheets = wb.getSheets();
		return sheets;
	}

	/**
	 * 得到某一工作表的名称
	 *
	 * @param sheet 工作表对象
	 * @return 工作表的名称
	 */
	public String getSheetName(Sheet sheet) {
		String sheetName = sheet.getName();
		return sheetName;
	}

	/**
	 * 获取工作表某一行每个单元格的内容，
	 *
	 * @param sheet 工作表对象
	 * @param row   行数
	 * @return 行内容集合类型
	 */
	public ArrayList<String> getRowContentList(Sheet sheet, int row) {
		Cell[] cell = sheet.getRow(row);
		ArrayList<String> rowlist = new ArrayList<String>();
		if (cell.length > 0) {
			for (int i = 0; i < cell.length; i++) {
				String content = cell[i].getContents();
				rowlist.add(content);
			}
		}
		return rowlist;
	}

	/**
	 * 获取工作表某一列每个单元格的内容。 如果某个单元格类型为数值型并且内容为空的话，那读取出的内容应为0。
	 *
	 * @param sheet 工作表对象
	 * @param row   列数
	 * @return 列内容集合类型
	 */
	public ArrayList<String> getColContentList(Sheet sheet, int column) {
		Cell[] cell = sheet.getColumn(column);
		ArrayList<String> columnlist = new ArrayList<String>();
		if (cell.length > 0) {
			for (int i = 0; i < cell.length; i++) {
				String content = cell[i].getContents();
				columnlist.add(content);
			}
		}
		return columnlist;
	}

	/**
	 * 得到某一工作表的总列数
	 *
	 * @param sheet 工作表对象
	 * @return 总列数
	 */
	public int getSheetColumnsCount(Sheet sheet) {
		int columnCount = sheet.getColumns();
		return columnCount;
	}

	/**
	 * 得到某一工作表的总行数
	 *
	 * @param sheet 工作表对象
	 * @return 总行数
	 */
	public int getSheetRowCount(Sheet sheet) {
		int rowCount = sheet.getRows();
		return rowCount;
	}

	// 关闭可读Workbook对象
	public void closeWorkbook(Workbook wb) {
		try {
			wb.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	// --------------------------------写文件方法-----------------------------------

	/**
	 * 创建一个可写的WritableWorkbook对象
	 *
	 * @param file 要写入的文件对象
	 * @return 可写的excel文件对象
	 */
	public WritableWorkbook createWritableWorkbook(File file) throws Exception {
		// OutputStream os = new FileOutputStream(file);//创建文件输入流
		// WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableWorkbook wwb = Workbook.createWorkbook(file);// 创建可写的Excel的WritableWorkbook对象
		return wwb;
	}

	/**
	 * 创建一个可写入的工作表
	 *
	 * @param wwb:可写的WritableWorkbook对象
	 * @param sheetName:要创建的工作表名
	 * @param sheetNo:要创建的工作表序号
	 * @return 创建过的工作表
	 */
	public WritableSheet createWritableSheet(WritableWorkbook wwb,
			String sheetName, int sheetNo) throws Exception {
		WritableSheet wsheet = wwb.createSheet(sheetName, sheetNo);
		return wsheet;
	}

	/**
	 * 创建一个可写入的工作表，把创建可写的WritableWorkbook对象和工作表对象都集成到此方法中。
	 *
	 * @param file      要创建的excel文件对象
	 * @param sheetName 要创建的工作表名
	 * @param sheetNo   要创建的工作表序号
	 * @return 创建过的工作表
	 */
	public WritableSheet CreateWritableExcel(File file, String sheetName,
			int sheetNo) throws Exception {
		// Method 1：创建可写入的Excel工作薄
		WritableWorkbook wwb = createWritableWorkbook(file);
		// Method 2：将WritableWorkbook直接写入到输出流
		// OutputStream os = new FileOutputStream(file);
		// WritableWorkbook wwb = Workbook.createWorkbook(os);
		// 创建Excel工作表
		WritableSheet ws = wwb.createSheet(sheetName, sheetNo);
		return ws;
	}

	/**
	 * 根据工作表名获取此可读可写工作表对象
	 *
	 * @param wwb       可写的Workbook对象
	 * @param sheetName 工作表名
	 * @return 可写的工作表对象
	 * @throws Exception
	 */
	public WritableSheet getWsheet(WritableWorkbook wwb, String sheetName)
			throws Exception {
		WritableSheet wsheet = wwb.getSheet(sheetName);
		return wsheet;
	}

	/**
	 * 根据工作表序号获取此可读可写工作表对象
	 *
	 * @param wwb       可写的Workbook对象
	 * @param sheetName 工作表序号
	 * @return 可写的工作表对象
	 * @throws Exception
	 */
	public WritableSheet getWsheet(WritableWorkbook wwb, int sheetNo)
			throws Exception {
		WritableSheet wsheet = wwb.getSheet(sheetNo);
		return wsheet;
	}

	public WritableCell getWcell(WritableSheet ws, int column, int row) {
		WritableCell wc = ws.getWritableCell(column, row);
		return wc;
	}

	/**
	 * 在工作表中某个单元格插入一个字符串值
	 *
	 * @param ws     工作表对象
	 * @param column 列号
	 * @param row    行号
	 * @param label  要插入的字符串
	 */
	public void addString(WritableSheet wsheet, int column, int row,
			String label) throws Exception {
		Label labelC = new Label(column, row, label);
		wsheet.addCell(labelC);
	}

	/**
	 * 在工作表中某个单元格插入一个数值
	 *
	 * @param ws     工作表对象
	 * @param column 列号
	 * @param row    行号
	 * @param label  要插入的数值
	 */
	public void addNumber(WritableSheet wsheet, int column, int row,
			double number) {
		jxl.write.Number labelN = new jxl.write.Number(column, row, number);
		try {
			wsheet.addCell(labelN);
		} catch (RowsExceededException ree) {
			ree.printStackTrace();
		} catch (WriteException we) {
			we.printStackTrace();
		}
	}

	/**
	 * 在工作表中某个单元格插入一个布尔型的值
	 *
	 * @param ws     工作表对象
	 * @param column 列号
	 * @param row    行号
	 * @param label  要插入的布尔型的值
	 */
	public void addBoolean(WritableSheet wsheet, int column, int row,
			boolean bolean) {
		try {
			jxl.write.Boolean labelB = new jxl.write.Boolean(column, row,
					bolean);
			wsheet.addCell(labelB);
		} catch (RowsExceededException ree) {
			ree.printStackTrace();
		} catch (WriteException we) {
			we.printStackTrace();
		}
	}

	/**
	 * 在工作表某一单元格中添加日期类型的数据
	 *
	 * @param wsheet 工作表对象
	 * @param column 列号
	 * @param row    行号
	 * @param date   要插入的日期类型的数据
	 */
	public void addDateTime(WritableSheet wsheet, int column, int row, Date date) {
		try {
			jxl.write.DateTime labelDT = new jxl.write.DateTime(column, row,
					date);
			wsheet.addCell(labelDT);
		} catch (RowsExceededException ree) {
			ree.printStackTrace();
		} catch (WriteException we) {
			we.printStackTrace();
		}
	}

	/**
	 * 合并单元格
	 *
	 * @param wsheet      工作表对象
	 * @param beginColumn 开始列
	 * @param beginRow    开始行
	 * @param endColumn   结束列
	 * @param endRow      结束行
	 */
	public void mergeCells(WritableSheet wsheet, int beginColumn, int beginRow,
			int endColumn, int endRow) {
		try {
			wsheet.mergeCells(beginColumn, beginRow, endColumn, endRow);
		} catch (RowsExceededException ree) {
			ree.printStackTrace();
		} catch (WriteException we) {
			we.printStackTrace();
		}
	}

	/**
	 * 设置工作表中某一列的列宽
	 *
	 * @param wsheet   工作表对象
	 * @param columnNo 列号
	 * @param width    列宽
	 */
	public void setColumnWidth(WritableSheet wsheet, int columnNo, int width) {
		wsheet.setColumnView(columnNo, width);
	}

	/**
	 * 设置工作表中某一行的行高
	 *
	 * @param wsheet 工作表对象
	 * @param rowNo  行号
	 * @param height 行高
	 */
	public void setRowHeight(WritableSheet wsheet, int rowNo, int height) {
		try {
			wsheet.setRowView(rowNo, height);
		} catch (RowsExceededException ree) {
			ree.printStackTrace();
		}
	}

	/**
	 * 写入工作表，任何创建或者修改更新的操作最后都要调用此方法。
	 *
	 * @param wwb Excel文档对象
	 */
	public void writeExcel(WritableWorkbook wwb) {
		try {
			wwb.write();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	/**
	 * 关闭可写的Workbook对象
	 *
	 * @param wwb 可写的excel对象
	 */
	public void closeWritableWorkbook(WritableWorkbook wwb) {
		try {
			wwb.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 将数据库中的数据导入到工作表
	 * File file=new File("c:/aaa.xls");
	 * ExcelUtil eu=new ExcelUtil();
	 * eu.batchWriteSheet(file,"bbb",0,0,list);
	 *
	 * @param file        要生成的excel文件
	 * @param sheetName   生成的工作表名称
	 * @param beginColumn 开始列
	 * @param beginRow    开始行
	 * @param datalist    插入工作表的数据列表
	 * @throws Exception
	 */
	public void batchWriteSheet(File file, String sheetName, int beginColumn,
			int beginRow, List<?> datalist) throws Exception {
		WritableWorkbook wwb = createWritableWorkbook(file);
		WritableSheet wsheet = wwb.createSheet(sheetName, 0);
		String str = "";
		if (!datalist.isEmpty()) {
			for (int i = 0; i < datalist.size(); i++) {
				Object obj = datalist.get(i);
				Method[] method = ReflectUtil.getPublicMethods(obj, "get");
				for (int n = 0; n < method.length; n++) {
					Object object = method[n].invoke(obj, new Object[] {});
					if (object == null) {
						str = "";
					} else {
						str = method[n].invoke(obj, new Object[] {}).toString();
					}
					log.info("str=============>>>>>" + str);
					addString(wsheet, beginColumn + n, beginRow, str);
				}
				beginRow++;

			}
		}
		writeExcel(wwb);
		closeWritableWorkbook(wwb);
	}

	public static void main(String[] args) throws Exception {

		// --------------------------------------读文件-------------------------------------
		/*
		 * File file = new File("d:/收入明细表.xls"); ExcelUtil eu = new ExcelUtil();
		 * //创建可读的工作表对象，定位到要读取的excel文件 Workbook workbook =
		 * eu.createWorkbook(file); //读取此文件的第一个工作表，工作表序号从0开始。 Sheet sheet =
		 * eu.getSheet(workbook,0); //也可以通过工作表名称来读取此工作表 //Sheet sheet =
		 * eu.getSheet(workbook,"收入明细表");
		 * System.out.println(eu.getSheetColumnsCount(sheet));//获取工作表列数
		 * System.out.println(eu.getSheetRowCount(sheet));//获取工作表行数
		 * //获取工作表第四列的所有内容，返回集合类型 ArrayList columnlist =
		 * eu.getColContentList(sheet, 4); for (int i = 0; i <
		 * columnlist.size(); i++) { System.out.println(columnlist.get(i)); }
		 * //获取工作表第一行的所有内容，返回集合类型 ArrayList rowlist =
		 * eu.getRowContentList(sheet, 0); for (int ii = 0; ii < rowlist.size();
		 * ii++) { System.out.println(rowlist.get(ii)); }
		 * System.out.println(eu.getSheetName(sheet));//得到此工作表名称 //得到第一列第八行单元格内容
		 * System.out.println(eu.getContents(sheet, 0, 8));
		 */

		// -----------------------------------写文件-------------------------------------
		File infile = new File("d:/aaa.xls");
		// File outfile = new File("d:/bbbaaa.xls");
		ExcelUtil eu = new ExcelUtil();
		// FileInputStream f = new InputStream().
		// 创建可写入的工作表
		WritableWorkbook wwb = eu.createWritableWorkbook(infile);
		// 在此创建的新excel文件创建一工作表
		WritableSheet wsheet = eu.createWritableSheet(wwb, "第一张工作表", 0);
		// 在工作表第三列第三行插入一字符串
		eu.addString(wsheet, 2, 2, "我是一匹来自北方的狼");
		// 得到此字符串内容
		System.out.println(eu.getContents(wsheet, 2, 2));
		// 插入一布尔型的值
		eu.addBoolean(wsheet, 3, 3, false);
		// 插入一日期时间值
		eu.addDateTime(wsheet, 4, 4, new Date());
		// 插入数值
		eu.addNumber(wsheet, 5, 5, 3.1415926);
		eu.addNumber(wsheet, 5, 5, 3.14);// 修改此单元格的值，和创建文件的用法一样。
		eu.writeExcel(wwb);
		eu.closeWritableWorkbook(wwb);
	}

}
