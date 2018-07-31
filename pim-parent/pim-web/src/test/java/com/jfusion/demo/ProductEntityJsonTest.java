package com.jfusion.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.sinux.modules.product.entity.PimProductEntity;
import com.sinux.modules.product.entity.ext.PimInWarehouseOrderExt;
import com.sinux.modules.product.entity.ext.PimProductEntityExt;
import com.sinux.modules.product.entity.ext.PimSoftwareInstallRecordExt;
import com.sinux.pim.common.entity.CommonContent;

public class ProductEntityJsonTest {

	@Test
	public void toJson() {
		PimProductEntity entity = new PimProductEntity();
		entity.setBatchNumber("BTH10001");
		entity.setEntityNumber("EN11110");
		entity.setDescription("电脑很重要不是卡");
		entity.setIsAttached(CommonContent.BOOLEAN_FLASE);
		entity.setProductId("001");
		entity.setProductName("电脑");
		entity.setProductNumber("PN10001");
		String str = JSON.toJSONString(entity);
		System.out.println(str);
	}
	
	@Test
	public void testJson1() {
		PimInWarehouseOrderExt ext = new PimInWarehouseOrderExt();
		ext.setDescription("订单生产");
		ext.setInTime(new Date());
		ext.setOrderNumber("IN20180730000002");
		ext.setOrderType(1);
		ext.setOwnerUserId("1");
		ext.setWarehouseId("1");
		List<PimProductEntityExt> productEntitys = new ArrayList<>();
		PimProductEntityExt entity = new PimProductEntityExt();
		entity.setBatchNumber("BTH10001");
		entity.setEntityNumber("EN11110");
		entity.setDescription("电脑很重要不是卡");
		entity.setIsAttached(CommonContent.BOOLEAN_FLASE);
		entity.setProductId("001");
		entity.setProductName("电脑");
		entity.setProductNumber("PN10001");
		ext.setProductEntitys(productEntitys);
		List<PimSoftwareInstallRecordExt> softwareInstallRecords = new ArrayList<>();
		entity.setSoftwareInstallRecords(softwareInstallRecords);
		PimSoftwareInstallRecordExt installRecordExt = new PimSoftwareInstallRecordExt();
		installRecordExt.setDescription("软件安装描述");
		installRecordExt.setDetectionDescription("检测描述");
		installRecordExt.setOperationDate(new Date());
		installRecordExt.setSoftwareId("1");
		installRecordExt.setSoftwareNumber("V1.212");
		installRecordExt.setSoftwateName("JDK 1.7");
		softwareInstallRecords.add(installRecordExt);
		productEntitys.add(entity);
		String str = JSON.toJSONString(ext);
		System.out.println(str);
	}
}
