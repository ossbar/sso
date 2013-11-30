/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.hihsoft.sso.business.model;

import com.hihsoft.baseclass.model.BaseEntity;
import com.hihframework.core.utils.DateUtils;
import com.hihframework.osplugins.json.JsonUtil;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.*;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company:hihsoft.co.,ltd
 * </p>
 * 
 * @author hihsoft
 * @version 1.0
 */

public class TbusinessOrder extends BaseEntity {

	// alias
	public static final String TABLE_ALIAS = "TbusinessOrder";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_APPLYNAME = "applyname";
	public static final String ALIAS_ORDERID = "orderid";
	public static final String ALIAS_APPOINTMENT_TIME = "appointmentTime";
	public static final String ALIAS_ORDERING_TIME = "orderingTime";
	public static final String ALIAS_UPPERORDERID = "upperorderid";
	public static final String ALIAS_CUSTOM_NAME = "customName";
	public static final String ALIAS_CUSTOM_PHONE = "customPhone";
	public static final String ALIAS_CUSTOM_ADDRESS = "customAddress";
	public static final String ALIAS_FAILURE_PHENOMENON = "failurePhenomenon";
	public static final String ALIAS_PRODUCT_BRAND = "productBrand";
	public static final String ALIAS_PRODUCT_TYPE = "productType";
	public static final String ALIAS_PRODUCT_SUBTYPE = "productSubtype";
	public static final String ALIAS_PRODUCT_MODEL = "productModel";
	public static final String ALIAS_PRODUCT_COLOR = "productColor";
	public static final String ALIAS_PURCHASE_DATE = "purchaseDate";
	public static final String ALIAS_PURCHASE_COUNT = "purchaseCount";
	public static final String ALIAS_REPAIRMAN_ID = "repairmanId";
	public static final String ALIAS_REPAIR_MEASURES = "repairMeasures";
	public static final String ALIAS_REPAIR_MONEY = "repairMoney";
	public static final String ALIAS_REPAIR_PARTS_IMAGES = "repairPartsImages";
	public static final String ALIAS_INVOICE_IMAGES = "invoiceImages";
	public static final String ALIAS_BAR_CODES = "barCodes";
	public static final String ALIAS_REPAIRED_TIME = "repairedTime";
	public static final String ALIAS_PAYMENT_TIME = "paymentTime";
	public static final String ALIAS_CANCEL_PERSON = "cancelPerson";
	public static final String ALIAS_CANCEL_TIME = "cancelTime";
	public static final String ALIAS_EXPORT_PERSON = "exportPerson";
	public static final String ALIAS_EXPORT_TIME = "exportTime";
	public static final String ALIAS_VERIFY_PERSON = "verifyPerson";
	public static final String ALIAS_VERIFY_TIME = "verifyTime";
	public static final String ALIAS_ORDER_STATE = "orderState";
	public static final String ALIAS_USER_SATISFACTION = "userSatisfaction";
	public static final String ALIAS_IS_WARRANTY = "isWarranty";
	public static final String ALIAS_ORDER_TYPE = "orderType";
	public static final String ALIAS_WITHIN24_HOURS = "within24Hours";
	public static final String ALIAS_BASIC_SALARY = "basicSalary";
	public static final String ALIAS_POSITIVE_INCENTIVE = "positiveIncentive";
	public static final String ALIAS_NEGATIVE_INCENTIVE = "negativeIncentive";
	public static final String ALIAS_APPRECIATION_COMPONENTS = "appreciationComponents";
	public static final String ALIAS_APPRECIATION_MONEY = "appreciationMoney";

	// date formats
	public static final String FORMAT_APPOINTMENT_TIME = DateUtils.FM_DATE_AND_TIME;
	public static final String FORMAT_ORDERING_TIME = DateUtils.FM_DATE_AND_TIME;
	public static final String FORMAT_PURCHASE_DATE = DateUtils
			.getDatePattern();
	public static final String FORMAT_REPAIRED_TIME = DateUtils.FM_DATE_AND_TIME;
	public static final String FORMAT_PAYMENT_TIME = DateUtils.FM_DATE_AND_TIME;
	public static final String FORMAT_CANCEL_TIME = DateUtils.FM_DATE_AND_TIME;
	public static final String FORMAT_EXPORT_TIME = DateUtils.FM_DATE_AND_TIME;
	public static final String FORMAT_VERIFY_TIME = DateUtils.FM_DATE_AND_TIME;

	// columns START
	private java.lang.String id;
	private java.lang.String applyname;
	private java.lang.String orderid;
	private java.util.Date appointmentTime;
	private java.util.Date orderingTime;
	private java.lang.String upperorderid;
	private java.lang.String customName;
	private java.lang.String customPhone;
	private java.lang.String customAddress;
	private java.lang.String failurePhenomenon;
	private java.lang.String productBrand;
	private java.lang.String productType;
	private java.lang.String productSubtype;
	private java.lang.String productModel;
	private java.lang.String productColor;
	private java.util.Date purchaseDate;
	private java.lang.Integer purchaseCount;
	private java.lang.String repairmanId;
	private java.lang.String repairMeasures;
	private java.lang.Integer repairMoney;
	private java.lang.String repairPartsImages;
	private java.lang.String invoiceImages;
	private java.lang.String barCodes;
	private java.util.Date repairedTime;
	private java.lang.String paymentTime;
	private java.lang.String cancelPerson;
	private java.util.Date cancelTime;
	private java.lang.String exportPerson;
	private java.util.Date exportTime;
	private java.lang.String verifyPerson;
	private java.util.Date verifyTime;
	private java.lang.String orderState;
	private java.lang.String userSatisfaction;
	private java.lang.String isWarranty;
	private java.lang.String orderType;
	private java.lang.String within24Hours;
	private java.lang.Double basicSalary;
	private java.lang.Double positiveIncentive;
	private java.lang.Double negativeIncentive;
	private java.lang.Double appreciationComponents;
	private java.lang.Double appreciationMoney;

	// 定义的
	// columns END

	public TbusinessOrder() {
	}

	public TbusinessOrder(java.lang.String id) {
		this.id = id;
	}

	public void setId(java.lang.String value) {
		this.id = value;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setApplyname(java.lang.String value) {
		this.applyname = value;
	}

	public java.lang.String getApplyname() {
		return this.applyname;
	}

	public void setOrderid(java.lang.String value) {
		this.orderid = value;
	}

	public java.lang.String getOrderid() {
		return this.orderid;
	}

	public String getAppointmentTimeString() {
		if (getAppointmentTime() != null)
			return DateUtils.format(getAppointmentTime(),
					FORMAT_APPOINTMENT_TIME);
		else
			return null;
	}

	public void setAppointmentTimeString(String value) {
		setAppointmentTime(DateUtils.getStringDateToDate(value));
	}

	public void setAppointmentTime(java.util.Date value) {
		this.appointmentTime = value;
	}

	public java.util.Date getAppointmentTime() {
		return this.appointmentTime;
	}

	public String getOrderingTimeString() {
		if (getOrderingTime() != null)
			return DateUtils.format(getOrderingTime(), FORMAT_ORDERING_TIME);
		else
			return null;
	}

	public void setOrderingTimeString(String value) {
		setOrderingTime(DateUtils.getStringDateToDate(value));
	}

	public void setOrderingTime(java.util.Date value) {
		this.orderingTime = value;
	}

	public java.util.Date getOrderingTime() {
		return this.orderingTime;
	}

	public void setUpperorderid(java.lang.String value) {
		this.upperorderid = value;
	}

	public java.lang.String getUpperorderid() {
		return this.upperorderid;
	}

	public void setCustomName(java.lang.String value) {
		this.customName = value;
	}

	public java.lang.String getCustomName() {
		return this.customName;
	}

	public void setCustomPhone(java.lang.String value) {
		this.customPhone = value;
	}

	public java.lang.String getCustomPhone() {
		return this.customPhone;
	}

	public void setCustomAddress(java.lang.String value) {
		this.customAddress = value;
	}

	public java.lang.String getCustomAddress() {
		return this.customAddress;
	}

	public void setFailurePhenomenon(java.lang.String value) {
		this.failurePhenomenon = value;
	}

	public java.lang.String getFailurePhenomenon() {
		return this.failurePhenomenon;
	}

	public void setProductBrand(java.lang.String value) {
		this.productBrand = value;
	}

	public java.lang.String getProductBrand() {
		return this.productBrand;
	}

	public void setProductType(java.lang.String value) {
		this.productType = value;
	}

	public java.lang.String getProductType() {
		return this.productType;
	}

	public void setProductSubtype(java.lang.String value) {
		this.productSubtype = value;
	}

	public java.lang.String getProductSubtype() {
		return this.productSubtype;
	}

	public void setProductModel(java.lang.String value) {
		this.productModel = value;
	}

	public java.lang.String getProductModel() {
		return this.productModel;
	}

	public void setProductColor(java.lang.String value) {
		this.productColor = value;
	}

	public java.lang.String getProductColor() {
		return this.productColor;
	}

	public String getPurchaseDateString() {
		if (getPurchaseDate() != null)
			return DateUtils.format(getPurchaseDate(), FORMAT_PURCHASE_DATE);
		else
			return null;
	}

	public void setPurchaseDateString(String value) {
		setPurchaseDate(DateUtils.getStringDateToDate(value));
	}

	public void setPurchaseDate(java.util.Date value) {
		this.purchaseDate = value;
	}

	public java.util.Date getPurchaseDate() {
		return this.purchaseDate;
	}

	public void setPurchaseCount(java.lang.Integer value) {
		this.purchaseCount = value;
	}

	public java.lang.Integer getPurchaseCount() {
		return this.purchaseCount;
	}

	public void setRepairmanId(java.lang.String value) {
		this.repairmanId = value;
	}

	public java.lang.String getRepairmanId() {
		return this.repairmanId;
	}

	public void setRepairMeasures(java.lang.String value) {
		this.repairMeasures = value;
	}

	public java.lang.String getRepairMeasures() {
		return this.repairMeasures;
	}

	public void setRepairMoney(java.lang.Integer value) {
		this.repairMoney = value;
	}

	public java.lang.Integer getRepairMoney() {
		return this.repairMoney;
	}

	public void setRepairPartsImages(java.lang.String value) {
		this.repairPartsImages = value;
	}

	public java.lang.String getRepairPartsImages() {
		return this.repairPartsImages;
	}

	public void setInvoiceImages(java.lang.String value) {
		this.invoiceImages = value;
	}

	public java.lang.String getInvoiceImages() {
		return this.invoiceImages;
	}

	public void setBarCodes(java.lang.String value) {
		this.barCodes = value;
	}

	public java.lang.String getBarCodes() {
		return this.barCodes;
	}

	public String getRepairedTimeString() {
		if (getRepairedTime() != null)
			return DateUtils.format(getRepairedTime(), FORMAT_REPAIRED_TIME);
		else
			return null;
	}

	public void setRepairedTimeString(String value) {
		setRepairedTime(DateUtils.getStringDateToDate(value));
	}

	public void setRepairedTime(java.util.Date value) {
		this.repairedTime = value;
	}

	public java.util.Date getRepairedTime() {
		return this.repairedTime;
	}

	public void setPaymentTime(java.lang.String value) {
		this.paymentTime = value;
	}

	public java.lang.String getPaymentTime() {
		return this.paymentTime;
	}

	public void setCancelPerson(java.lang.String value) {
		this.cancelPerson = value;
	}

	public java.lang.String getCancelPerson() {
		return this.cancelPerson;
	}

	public String getCancelTimeString() {
		if (getCancelTime() != null)
			return DateUtils.format(getCancelTime(), FORMAT_CANCEL_TIME);
		else
			return null;
	}

	public void setCancelTimeString(String value) {
		setCancelTime(DateUtils.getStringDateToDate(value));
	}

	public void setCancelTime(java.util.Date value) {
		this.cancelTime = value;
	}

	public java.util.Date getCancelTime() {
		return this.cancelTime;
	}

	public void setExportPerson(java.lang.String value) {
		this.exportPerson = value;
	}

	public java.lang.String getExportPerson() {
		return this.exportPerson;
	}

	public String getExportTimeString() {
		if (getExportTime() != null)
			return DateUtils.format(getExportTime(), FORMAT_EXPORT_TIME);
		else
			return null;
	}

	public void setExportTimeString(String value) {
		setExportTime(DateUtils.getStringDateToDate(value));
	}

	public void setExportTime(java.util.Date value) {
		this.exportTime = value;
	}

	public java.util.Date getExportTime() {
		return this.exportTime;
	}

	public void setVerifyPerson(java.lang.String value) {
		this.verifyPerson = value;
	}

	public java.lang.String getVerifyPerson() {
		return this.verifyPerson;
	}

	public String getVerifyTimeString() {
		if (getVerifyTime() != null)
			return DateUtils.format(getVerifyTime(), FORMAT_VERIFY_TIME);
		else
			return null;
	}

	public void setVerifyTimeString(String value) {
		setVerifyTime(DateUtils.getStringDateToDate(value));
	}

	public void setVerifyTime(java.util.Date value) {
		this.verifyTime = value;
	}

	public java.util.Date getVerifyTime() {
		return this.verifyTime;
	}

	public void setOrderState(java.lang.String value) {
		this.orderState = value;
	}

	public java.lang.String getOrderState() {
		return this.orderState;
	}

	public void setUserSatisfaction(java.lang.String value) {
		this.userSatisfaction = value;
	}

	public java.lang.String getUserSatisfaction() {
		return this.userSatisfaction;
	}

	public void setIsWarranty(java.lang.String value) {
		this.isWarranty = value;
	}

	public java.lang.String getIsWarranty() {
		return this.isWarranty;
	}

	public void setOrderType(java.lang.String value) {
		this.orderType = value;
	}

	public java.lang.String getOrderType() {
		return this.orderType;
	}

	public void setWithin24Hours(java.lang.String value) {
		this.within24Hours = value;
	}

	public java.lang.String getWithin24Hours() {
		return this.within24Hours;
	}

	public void setBasicSalary(java.lang.Double value) {
		this.basicSalary = value;
	}

	public java.lang.Double getBasicSalary() {
		return this.basicSalary;
	}

	public void setPositiveIncentive(java.lang.Double value) {
		this.positiveIncentive = value;
	}

	public java.lang.Double getPositiveIncentive() {
		return this.positiveIncentive;
	}

	public void setNegativeIncentive(java.lang.Double value) {
		this.negativeIncentive = value;
	}

	public java.lang.Double getNegativeIncentive() {
		return this.negativeIncentive;
	}

	public void setAppreciationComponents(java.lang.Double value) {
		this.appreciationComponents = value;
	}

	public java.lang.Double getAppreciationComponents() {
		return this.appreciationComponents;
	}

	public void setAppreciationMoney(java.lang.Double value) {
		this.appreciationMoney = value;
	}

	public java.lang.Double getAppreciationMoney() {
		return this.appreciationMoney;
	}

	public static void main(String[] args) {
		TbusinessOrder to = new TbusinessOrder();
		to.setApplyname("test");
		String x = JsonUtil.toString(to);
		System.out.println(x);
	}

	public String toString() {
		return new ToStringBuilder(this).append("Id", getId())
				.append("Applyname", getApplyname())
				.append("Orderid", getOrderid())
				.append("AppointmentTime", getAppointmentTime())
				.append("OrderingTime", getOrderingTime())
				.append("Upperorderid", getUpperorderid())
				.append("CustomName", getCustomName())
				.append("CustomPhone", getCustomPhone())
				.append("CustomAddress", getCustomAddress())
				.append("FailurePhenomenon", getFailurePhenomenon())
				.append("ProductBrand", getProductBrand())
				.append("ProductType", getProductType())
				.append("ProductSubtype", getProductSubtype())
				.append("ProductModel", getProductModel())
				.append("ProductColor", getProductColor())
				.append("PurchaseDate", getPurchaseDate())
				.append("PurchaseCount", getPurchaseCount())
				.append("RepairmanId", getRepairmanId())
				.append("RepairMeasures", getRepairMeasures())
				.append("RepairMoney", getRepairMoney())
				.append("RepairPartsImages", getRepairPartsImages())
				.append("InvoiceImages", getInvoiceImages())
				.append("BarCodes", getBarCodes())
				.append("RepairedTime", getRepairedTime())
				.append("PaymentTime", getPaymentTime())
				.append("CancelPerson", getCancelPerson())
				.append("CancelTime", getCancelTime())
				.append("ExportPerson", getExportPerson())
				.append("ExportTime", getExportTime())
				.append("VerifyPerson", getVerifyPerson())
				.append("VerifyTime", getVerifyTime())
				.append("OrderState", getOrderState())
				.append("UserSatisfaction", getUserSatisfaction())
				.append("IsWarranty", getIsWarranty())
				.append("OrderType", getOrderType())
				.append("Within24Hours", getWithin24Hours())
				.append("BasicSalary", getBasicSalary())
				.append("PositiveIncentive", getPositiveIncentive())
				.append("NegativeIncentive", getNegativeIncentive())
				.append("AppreciationComponents", getAppreciationComponents())
				.append("AppreciationMoney", getAppreciationMoney()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).append(getApplyname())
				.append(getOrderid()).append(getAppointmentTime())
				.append(getOrderingTime()).append(getUpperorderid())
				.append(getCustomName()).append(getCustomPhone())
				.append(getCustomAddress()).append(getFailurePhenomenon())
				.append(getProductBrand()).append(getProductType())
				.append(getProductSubtype()).append(getProductModel())
				.append(getProductColor()).append(getPurchaseDate())
				.append(getPurchaseCount()).append(getRepairmanId())
				.append(getRepairMeasures()).append(getRepairMoney())
				.append(getRepairPartsImages()).append(getInvoiceImages())
				.append(getBarCodes()).append(getRepairedTime())
				.append(getPaymentTime()).append(getCancelPerson())
				.append(getCancelTime()).append(getExportPerson())
				.append(getExportTime()).append(getVerifyPerson())
				.append(getVerifyTime()).append(getOrderState())
				.append(getUserSatisfaction()).append(getIsWarranty())
				.append(getOrderType()).append(getWithin24Hours())
				.append(getBasicSalary()).append(getPositiveIncentive())
				.append(getNegativeIncentive())
				.append(getAppreciationComponents())
				.append(getAppreciationMoney()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof TbusinessOrder == false)
			return false;
		if (this == obj)
			return true;
		TbusinessOrder other = (TbusinessOrder) obj;
		return new EqualsBuilder()
				.append(getId(), other.getId())
				.append(getApplyname(), other.getApplyname())
				.append(getOrderid(), other.getOrderid())
				.append(getAppointmentTime(), other.getAppointmentTime())
				.append(getOrderingTime(), other.getOrderingTime())
				.append(getUpperorderid(), other.getUpperorderid())
				.append(getCustomName(), other.getCustomName())
				.append(getCustomPhone(), other.getCustomPhone())
				.append(getCustomAddress(), other.getCustomAddress())
				.append(getFailurePhenomenon(), other.getFailurePhenomenon())
				.append(getProductBrand(), other.getProductBrand())
				.append(getProductType(), other.getProductType())
				.append(getProductSubtype(), other.getProductSubtype())
				.append(getProductModel(), other.getProductModel())
				.append(getProductColor(), other.getProductColor())
				.append(getPurchaseDate(), other.getPurchaseDate())
				.append(getPurchaseCount(), other.getPurchaseCount())
				.append(getRepairmanId(), other.getRepairmanId())
				.append(getRepairMeasures(), other.getRepairMeasures())
				.append(getRepairMoney(), other.getRepairMoney())
				.append(getRepairPartsImages(), other.getRepairPartsImages())
				.append(getInvoiceImages(), other.getInvoiceImages())
				.append(getBarCodes(), other.getBarCodes())
				.append(getRepairedTime(), other.getRepairedTime())
				.append(getPaymentTime(), other.getPaymentTime())
				.append(getCancelPerson(), other.getCancelPerson())
				.append(getCancelTime(), other.getCancelTime())
				.append(getExportPerson(), other.getExportPerson())
				.append(getExportTime(), other.getExportTime())
				.append(getVerifyPerson(), other.getVerifyPerson())
				.append(getVerifyTime(), other.getVerifyTime())
				.append(getOrderState(), other.getOrderState())
				.append(getUserSatisfaction(), other.getUserSatisfaction())
				.append(getIsWarranty(), other.getIsWarranty())
				.append(getOrderType(), other.getOrderType())
				.append(getWithin24Hours(), other.getWithin24Hours())
				.append(getBasicSalary(), other.getBasicSalary())
				.append(getPositiveIncentive(), other.getPositiveIncentive())
				.append(getNegativeIncentive(), other.getNegativeIncentive())
				.append(getAppreciationComponents(),
						other.getAppreciationComponents())
				.append(getAppreciationMoney(), other.getAppreciationMoney())
				.isEquals();
	}

}

// uncap_first表示首字母小写；cap_first首字母大写；lower_case将字符串转为小写upper_case将字符串转为大写
