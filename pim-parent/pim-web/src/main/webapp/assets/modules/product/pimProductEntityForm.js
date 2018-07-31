app.controller('addFormCtrl', ['$scope', '$rootScope', '$http', '$templateCache', '$compile', '$document', function ($scope, $rootScope, $http, $templateCache, $compile, $document) {

	var testGetSingleDataApiCtx = codeTempletCtx+"/product/pimProductEntity/get";  //查询单个数据
	var testGetMultipleDataApiCtx = codeTempletCtx+"/product/pimProductEntity/getByIds"; //查询多个数据
	var testGetInitDataApiCtx = codeTempletCtx+"/product/pimProductEntity/init"; //获取下拉，单选，复选初始数据
	var testSaveApiCtx = codeTempletCtx+"/product/pimProductEntity/save";  //保存1个或0个
    var testSaveBatchApiCtx = codeTempletCtx+"/product/pimProductEntity/saveBatch";  //保存多个


	//请求路径
    var codeGenxhl = {
      "test": {
        "singleData": testGetSingleDataApiCtx,  //查询单个数据  id
        "save": testSaveApiCtx,  //添加\修改单页保存
        "saveBatch": testSaveBatchApiCtx,  //添加、修改多页保存
        "multipleData": testGetMultipleDataApiCtx,
        "initData": testGetInitDataApiCtx
      }
    };

	var type = getQueryString("type");
	var id = getQueryString("id").split(",");
	
	//富文本配置项
	var getEditorParam = {
	  toolbars: [['fullscreen', 'source', '|', 'paragraph', 'fontfamily', 'fontsize', '|', 'bold', 'italic', 'underline', 'removeformat', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', '|', 'indent', '|', 'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'lineheight', '|', 'inserttable', '|', 'link', '|', 'insertimage', 'insertvideo', '|', 'help']],
	  initialFrameWidth: '100%',
	  initialFrameHeight: 200
	};
	
	//初始化数据
	$scope.presetData = {
	};
	
	//数据
	$scope.selectItem = [];
	
	//获取下拉，单选，复选初始数据
	$http({
		method: 'get',
		url: codeGenxhl.test.initData,
	headers: {
    	'Accept' : 'application/json',
    	'Content-Type': "application/json; charset=utf-8"
   	}
	}).success(function (response) {
		var data = response.data;
		
		if (type == "add") { //添加
			//默认显示一条
			var obj = {
			"isOperation": false,
						"id" : "",
			"entityNumber" : "",
			"entityState" : "",
			"batchNumber" : "",
			"warehouseId" : "",
			"softwareVersionId" : "",
			"productId" : "",
			"productNumber" : "",
			"productName" : "",
			"description" : "",
			"physicalWarehouse" : "",
			"processState" : "",
			"createById" : "",
			"createDate" : "",
			"updateById" : "",
			"updateDate" : "",
			"ownershipId" : "",
			"updateOwnershipDate" : "",
			"isAttached" : ""
		};
		$scope.selectItem.push(obj);
		
		setTimeout(function() {

				//富文本初始化
				//实例化编辑器
				//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
				if($("#editor0").length > 0){
					var ue = UE.getEditor('editor' + 0, getEditorParam);
				}
				
				var $content = $("#inputForm");
                //根据内容，动态改变弹出框高度
                changeTempLayerHeight($content);
			}, 1000);
		}
	});

	if (type == "single") {  //单个
		
		$http.get(codeGenxhl.test.singleData + '/' + id)  
			.success(function(response, status, headers, config){  
			      console.log('添加 成功 data', response);

			      var data = response.data;
			      var obj = {};
				  					obj["id"] = data.id;
					obj["entityNumber"] = data.entityNumber;
					obj["entityState"] = data.entityState;
					obj["batchNumber"] = data.batchNumber;
					obj["warehouseId"] = data.warehouseId;
					obj["softwareVersionId"] = data.softwareVersionId;
					obj["productId"] = data.productId;
					obj["productNumber"] = data.productNumber;
					obj["productName"] = data.productName;
					obj["description"] = data.description;
					obj["physicalWarehouse"] = data.physicalWarehouse;
					obj["processState"] = data.processState;
					obj["createById"] = data.createById;
					obj["createDate"] = data.createDate;
					obj["updateById"] = data.updateById;
					obj["updateDate"] = data.updateDate;
					obj["ownershipId"] = data.ownershipId;
					obj["updateOwnershipDate"] = data.updateOwnershipDate;
					obj["isAttached"] = data.isAttached
			      $scope.selectItem.push(obj);
			      
			      setTimeout(function () {
			      
			      
			      var $content = $("#inputForm");
                //根据内容，动态改变弹出框高度
                changeTempLayerHeight($content);
			      }, 1000);

			});

	}else if (type == "multiple") {  //多个

  		$http({
  			method: 'post',
  			url: codeGenxhl.test.multipleData,
  			data: JSON.stringify(id),
			headers: {
            	'Accept' : 'application/json',
            	'Content-Type': "application/json; charset=utf-8"
           	}
  		}).success(function (response) {
  			var data = response.data; 

  			angular.forEach(data, function (data, index, array) {
  				var obj = {
				    					"id" : data.id,
					"entityNumber" : data.entityNumber,
					"entityState" : data.entityState,
					"batchNumber" : data.batchNumber,
					"warehouseId" : data.warehouseId,
					"softwareVersionId" : data.softwareVersionId,
					"productId" : data.productId,
					"productNumber" : data.productNumber,
					"productName" : data.productName,
					"description" : data.description,
					"physicalWarehouse" : data.physicalWarehouse,
					"processState" : data.processState,
					"createById" : data.createById,
					"createDate" : data.createDate,
					"updateById" : data.updateById,
					"updateDate" : data.updateDate,
					"ownershipId" : data.ownershipId,
					"updateOwnershipDate" : data.updateOwnershipDate,
					"isAttached" : data.isAttached
  				};
  				$scope.selectItem.push(obj);
  			});
  			
  			setTimeout(function () {
  				


			var $content = $("#inputForm");
                //根据内容，动态改变弹出框高度
                changeTempLayerHeight($content);
		    }, 1000);

  		});


	}

	//点击下拉选项
	$scope.clickSelect = function ($event, option, item, $index, outerIndex, type) {
		var that = $($event.currentTarget);
		var index = that.parents(".js-contentDiv").index();
		if($scope.selectItem[index].hasOwnProperty(type)){
			$scope.selectItem[index][type] = option.label;
		}
	};

	//点击增加按钮，添加属性
	$scope.add = function ($event) {
		var that = $($event.currentTarget);
		var index = null;
		var obj = {
			"isOperation": false,
						"id" : "",
			"entityNumber" : "",
			"entityState" : "",
			"batchNumber" : "",
			"warehouseId" : "",
			"softwareVersionId" : "",
			"productId" : "",
			"productNumber" : "",
			"productName" : "",
			"description" : "",
			"physicalWarehouse" : "",
			"processState" : "",
			"createById" : "",
			"createDate" : "",
			"updateById" : "",
			"updateDate" : "",
			"ownershipId" : "",
			"updateOwnershipDate" : "",
			"isAttached" : ""
		};
		$scope.selectItem.push(obj);
		
		setTimeout(function() {
			index = $scope.selectItem.length - 1;
			if (index != null) {
			
				
				//富文本初始化
				//实例化编辑器
				//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
				if($("#editor + index").length > 0){
					var ue = UE.getEditor('editor' + index, getEditorParam);
				}
			}

		var $content = $("#inputForm");
                //根据内容，动态改变弹出框高度
                changeTempLayerHeight($content);
		}, 1000);

	};

	//点击删除按钮，删除属性
	$scope.remove = function () {

		var TempArr = [];  //需要删除的数据索引

		angular.forEach($scope.selectItem, function (data, index, array) {
			if (data.isOperation) {
				TempArr.push(index);
			}
		});

		if (TempArr.length != 0) {
			top.layer.confirm("要删除该属性？", {icon: '3', title: '删除确认'}, function (index) {

				var k = 0;
				for (var i = 0; i < TempArr.length; i++) {
					$scope.selectItem.splice(TempArr[i]-k, 1);
					k++;
				}

				$scope.$digest();

				top.layer.close(index);
			});
		}else {
			top.layer.confirm("请至少选择一条数据", {icon: '0', title: '警告'}, function (index) {
				top.layer.close(index);
			});
		}
	};
	
	
	//----------------------------------
	//先进行验证
	var validateForm;
	$scope.validate = function() {
		validateForm = $("#inputForm").validate({
			rules: {
				beginDate: {
					required: true
				},
				no: {
					required: true
				},
				name: {
					required: true
				}
			},
			messages: {
				beginDate: {
					required: "时间不能为空"
				},
				no: {
					required: "工号不能为空"
				},
				name: {
					required: "姓名不能为空"
				}
			},
			errorClass: "state-error",
			errorElement: "em",
			highlight: function(element, errorClass, validClass) {
				$(element).closest('.field').addClass(errorClass);
			},
			unhighlight: function(element, errorClass, validClass) {
				$(element).closest('.field').removeClass(errorClass);
			},
			errorPlacement: function(error, element) {
				error.insertAfter(element.parent());
			}
		});
	}
	//初始化执行验证
	$scope.validate();
	window.doSubmit = function(index, param) {
		//提交先验证
		//if (!validateForm.form()) {
		//	return Promise.reject('验证失败');
		//}

		var body = $('body');
		
		
                    
                    var trs = body.find(".js-tableRichtext").find(".js-contentDiv");
                        var tableLen = trs.length;
                        var saveData = {};

                        if (tableLen > 1) {  //保存多个
                            saveData["pimProductEntityList"] = [];

                            trs.each(function (i, item) {
                            	var saveDataObj = {};
                            	saveDataObj = saveDataFun($(item));
                                saveData["pimProductEntityList"].push(saveDataObj);
                            });

                            var saveDataStr = JSON.stringify(saveData);
                            
                            var httpPromise = $http.post(codeGenxhl.test.saveBatch, saveDataStr).then(function(xhr) {
								if (xhr.data.code == 200) {
									top.layer.msg(xhr.data.message, {
										icon: 1,
										time: 1000
									});
								} else {
									top.layer.msg(xhr.data.message, {
										icon: 2,
										time: 1000
									});
								}
				
							}, function(xhr) {
								top.layer.msg(xhr.data.message, {
									icon: 2,
									time: 1000
								})
							});
							return httpPromise;

                          

                        }else {  //保存单个
                        	saveData = saveDataFun(body);

                            var saveDataStr = JSON.stringify(saveData);
                            
                            //提交数据
							var httpPromise = $http.post(codeGenxhl.test.save, saveDataStr).then(function(xhr) {
								if (xhr.data.code == 200) {
									top.layer.msg(xhr.data.message, {
										icon: 1,
										time: 1000
									});
								} else {
									top.layer.msg(xhr.data.message, {
										icon: 2,
										time: 1000
									});
								}
				
							}, function(xhr) {
								top.layer.msg(xhr.data.message, {
									icon: 2,
									time: 1000
								})
							});
							return httpPromise;

                            
                        }
                  
	}
	
	
	/**
    * 保存单个，或者多个列表行
    * @param e body $(item)
    **/
    function saveDataFun(e) {
        var obj = {},
        	        	id,
        	entityNumber,
        	entityState,
        	batchNumber,
        	warehouseId,
        	softwareVersionId,
        	productId,
        	productNumber,
        	productName,
        	description,
        	physicalWarehouse,
        	processState,
        	createById,
        	createDate,
        	updateById,
        	updateDate,
        	ownershipId,
        	updateOwnershipDate,
        	isAttached;

        	if (e.find(".js-id").length != 0) {
            	id = e.find(".js-id").val();
        	}
        	if (e.find(".js-entityNumber").length != 0) {
            	entityNumber = e.find(".js-entityNumber").val();
        	}
        	if (e.find(".js-entityState").length != 0) {
            	entityState = e.find(".js-entityState").val();
        	}
        	if (e.find(".js-batchNumber").length != 0) {
            	batchNumber = e.find(".js-batchNumber").val();
        	}
        	if (e.find(".js-warehouseId").length != 0) {
            	warehouseId = e.find(".js-warehouseId").val();
        	}
        	if (e.find(".js-softwareVersionId").length != 0) {
            	softwareVersionId = e.find(".js-softwareVersionId").val();
        	}
        	if (e.find(".js-productId").length != 0) {
            	productId = e.find(".js-productId").val();
        	}
        	if (e.find(".js-productNumber").length != 0) {
            	productNumber = e.find(".js-productNumber").val();
        	}
        	if (e.find(".js-productName").length != 0) {
            	productName = e.find(".js-productName").val();
        	}
        	if (e.find(".js-description").length != 0) {
            	description = e.find(".js-description").val();
        	}
        	if (e.find(".js-physicalWarehouse").length != 0) {
            	physicalWarehouse = e.find(".js-physicalWarehouse").val();
        	}
        	if (e.find(".js-processState").length != 0) {
            	processState = e.find(".js-processState").val();
        	}
        	if (e.find(".js-createById").length != 0) {
            	createById = e.find(".js-createById").val();
        	}
        	if (e.find(".js-createDate").length != 0) {
            	createDate = e.find(".js-createDate").val();
        	}
        	if (e.find(".js-updateById").length != 0) {
            	updateById = e.find(".js-updateById").val();
        	}
        	if (e.find(".js-updateDate").length != 0) {
            	updateDate = e.find(".js-updateDate").val();
        	}
        	if (e.find(".js-ownershipId").length != 0) {
            	ownershipId = e.find(".js-ownershipId").val();
        	}
        	if (e.find(".js-updateOwnershipDate").length != 0) {
            	updateOwnershipDate = e.find(".js-updateOwnershipDate").val();
        	}
        	if (e.find(".js-isAttached").length != 0) {
            	isAttached = e.find(".js-isAttached").val();
        	}
		
			if (id !=undefined && id != "") {
            	obj["id"] = id;
        	}
			if (entityNumber !=undefined && entityNumber != "") {
            	obj["entityNumber"] = entityNumber;
        	}
			if (entityState !=undefined && entityState != "") {
            	obj["entityState"] = entityState;
        	}
			if (batchNumber !=undefined && batchNumber != "") {
            	obj["batchNumber"] = batchNumber;
        	}
			if (warehouseId !=undefined && warehouseId != "") {
            	obj["warehouseId"] = warehouseId;
        	}
			if (softwareVersionId !=undefined && softwareVersionId != "") {
            	obj["softwareVersionId"] = softwareVersionId;
        	}
			if (productId !=undefined && productId != "") {
            	obj["productId"] = productId;
        	}
			if (productNumber !=undefined && productNumber != "") {
            	obj["productNumber"] = productNumber;
        	}
			if (productName !=undefined && productName != "") {
            	obj["productName"] = productName;
        	}
			if (description !=undefined && description != "") {
            	obj["description"] = description;
        	}
			if (physicalWarehouse !=undefined && physicalWarehouse != "") {
            	obj["physicalWarehouse"] = physicalWarehouse;
        	}
			if (processState !=undefined && processState != "") {
            	obj["processState"] = processState;
        	}
			if (createById !=undefined && createById != "") {
            	obj["createById"] = createById;
        	}
			if (createDate !=undefined && createDate != "") {
            	obj["createDate"] = createDate;
        	}
			if (updateById !=undefined && updateById != "") {
            	obj["updateById"] = updateById;
        	}
			if (updateDate !=undefined && updateDate != "") {
            	obj["updateDate"] = updateDate;
        	}
			if (ownershipId !=undefined && ownershipId != "") {
            	obj["ownershipId"] = ownershipId;
        	}
			if (updateOwnershipDate !=undefined && updateOwnershipDate != "") {
            	obj["updateOwnershipDate"] = updateOwnershipDate;
        	}
			if (isAttached !=undefined && isAttached != "") {
            	obj["isAttached"] = isAttached;
        	}
        return obj;
    }
	

	
}]);