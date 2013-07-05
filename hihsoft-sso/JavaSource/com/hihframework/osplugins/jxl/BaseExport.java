package com.hihframework.osplugins.jxl;

import java.math.BigDecimal;
import java.util.Date;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.log4j.Logger;


/**
 * <p> Title:处理数据导入到excel的基类</p>
 * <p> Description:模板方式导出</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *  @author hihsoft.co.,ltd
 * @version 1.0
 */
public class BaseExport {
	protected final Logger log = Logger.getLogger(this.getClass());

	private WritableWorkbook wbook = null; //操作的工作簿实例

	private WritableSheet wsheet = null; //当前操作页表：工作表

	private WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);//字体设置

	public BaseExport() {
	}

	public BaseExport(WritableWorkbook wbook) {
		this.wbook = wbook;
	}

	public WritableWorkbook getWbook() {
		return wbook;
	}

	public void setWbook(WritableWorkbook wbook) {
		this.wbook = wbook;
	}

	/**
	 * 设置当前操作页表
	 * 
	 * @param sheetno
	 * @throws ExcelExportException
	 */
	protected void setSheet(int sheetno) throws ExcelExportException {
		if (wbook == null)
			throw new ExcelExportException("没指定Exce工作簿!");

		int sheets = wbook.getNumberOfSheets();
		if ((sheetno >= 0) && (sheetno <= sheets)) {
			wsheet = wbook.getSheet(sheetno);
		} else {
			throw new ExcelExportException("指定工作表超出工作簿范围!");
		}

	}

	/**
	 * 设置当前操作页表
	 * 
	 * @param sheetName
	 * @throws ExcelExportException
	 */
	protected void setSheet(String sheetName) throws ExcelExportException {
		if (wbook == null)
			throw new ExcelExportException("没指定Exce工作簿!");

		wsheet = wbook.getSheet(sheetName);
	}

	public boolean writeData;

	/**
	 * 将string数据转换成BigDecimal
	 * 
	 * @param strData
	 * @return BigDecimal
	 */
	protected BigDecimal strToBigDec(String strData) {
		BigDecimal tmp = new BigDecimal(0);
		try {
			tmp = ((strData == null || strData.trim().equals("")) ? new BigDecimal(
					0)
					: new BigDecimal(strData.trim()));
		} catch (NumberFormatException nfe) {
			tmp = new BigDecimal(0);
			log.info(nfe);
		} finally {
			tmp = new BigDecimal(0);
		}
		return tmp;
	}

	/**
	 * 在工作表中某个单元格插入一个字符串值
	 * 
	 * @param column
	 *            列号
	 * @param row
	 *            行号
	 * @param label
	 *            要插入的字符串
	 */
	protected boolean addString(int column, int row, String label)
			throws ExcelExportException {

		if (wsheet != null) {
			try {
				WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
				//设定工作表中单元格的格式
				wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); //线条
				wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); //垂直对齐
				wcf_left.setAlignment(Alignment.CENTRE);
				wcf_left.setWrap(true);

				if (label != null && !label.equalsIgnoreCase("")) {
					Label labelC = new Label(column, row, label, wcf_left);
					wsheet.addCell(labelC);
				} else {
					Label labeld = new Label(column, row, " ", wcf_left);//处理空值、NULL值的数据
					wsheet.addCell(labeld);
				}
			} catch (Exception e) {
				log.info(e);
				return false;
			}
			return true;

		} else
			return false;
	}

	/**
	 * 在工作表中某个单元格插入一个字符串值
	 * 
	 * @param column
	 *            列号
	 * @param row
	 *            行号
	 * @param label
	 *            要插入的字符串
	 */
	public boolean addStringNOBorder(int column, int row, String label)
			throws ExcelExportException {

		if (wsheet != null) {
			try {
				if (label != null) {
					Label labelC = new Label(column, row, label);
					wsheet.addCell(labelC);
				}
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
	 * @param column
	 *            列号
	 * @param row
	 *            行号
	 * @param number
	 *            要插入的数值
	 */
	public boolean addNumber(int column, int row, double number) {

		if (wsheet != null) {
			try {

				WritableCellFormat wcf_right = new WritableCellFormat(
						NormalFont);
				wcf_right.setBorder(Border.ALL, BorderLineStyle.THIN); //线条
				wcf_right.setVerticalAlignment(VerticalAlignment.CENTRE); //垂直对齐
				wcf_right.setAlignment(Alignment.RIGHT);
				jxl.write.Number labelN = new jxl.write.Number(column, row,
						number, wcf_right);
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
	 * 在工作表中某个单元格插入一个数值
	 * 
	 * @param column
	 *            列号
	 * @param row
	 *            行号
	 * @param number
	 *            要插入的数值
	 */
	public boolean addBigDecimal(int column, int row, BigDecimal number) {

		if (wsheet != null) {
			try {
				if (number != null) {
					WritableCellFormat wcf_right = new WritableCellFormat(
							NormalFont);
					wcf_right.setBorder(Border.ALL, BorderLineStyle.THIN); //线条
					wcf_right.setVerticalAlignment(VerticalAlignment.CENTRE); //垂直对齐
					wcf_right.setAlignment(Alignment.RIGHT);

					jxl.write.Number labelN = new jxl.write.Number(column, row,
							number.doubleValue(), wcf_right);
					wsheet.addCell(labelN);
				}
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
	 * @param column
	 *            列号
	 * @param row
	 *            行号
	 * @param bule
	 *            要插入的布尔型的值
	 */
	public boolean addBoolean(int column, int row, boolean bule) {

		if (wsheet != null) {

			try {
				jxl.write.Boolean labelB = new jxl.write.Boolean(column, row,
						bule);
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
	 * @param column
	 *            列号
	 * @param row
	 *            行号
	 * @param date
	 *            要插入的日期类型的数据
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
	 * 添加带有固定格式的日期数据
	 * @param column
	 * @param row
	 * @param date
	 * @return
	 */
	public boolean addFormateDateTime(int column, int row, Date date) {
		if (wsheet != null) {
			try {
				jxl.write.DateFormat df = new jxl.write.DateFormat(
						"dd MM yyyy hh:mm:ss");
				jxl.write.WritableCellFormat wcfDF = new jxl.write.WritableCellFormat(
						df);
				jxl.write.DateTime labelDT = new jxl.write.DateTime(column,
						row, date, wcfDF);
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
	 * 格式化数值数据填入
	 * @param column
	 * @param row
	 * @param db
	 * @return
	 */
	public boolean addFormatLabelNumber(int column, int row, double number) {
		if (wsheet != null) {
			try {
				jxl.write.NumberFormat nf = new jxl.write.NumberFormat("#.##");
				jxl.write.WritableCellFormat wcfN = new jxl.write.WritableCellFormat(
						nf);
				jxl.write.Number labelNT = new jxl.write.Number(column, row,
						number, wcfN);

				wsheet.addCell(labelNT);
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
	 * @param beginColumn
	 *            开始列
	 * @param beginRow
	 *            开始行
	 * @param endColumn
	 *            结束列
	 * @param endRow
	 *            结束行
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
	 * @param columnNo
	 *            列号
	 * @param width
	 *            列宽
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
	 * @param rowNo
	 *            行号
	 * @param height
	 *            行高
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
