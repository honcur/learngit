app.controller('addFormCtrl', ['$scope', '$rootScope', '$http', '$templateCache', '$compile', '$document', function ($scope, $rootScope, $http, $templateCache, $compile, $document) {

	var testGetSingleDataApiCtx = codeTempletCtx+"/xa/pimRole/get";  //查询单个数据
	var testGetMultipleDataApiCtx = codeTempletCtx+"/xa/pimRole/getByIds"; //查询多个数据
	var testGetInitDataApiCtx = codeTempletCtx+"/xa/pimRole/init"; //获取下拉，单选，复选初始数据
	var testSaveApiCtx = codeTempletCtx+"/xa/pimRole/save";  //保存1个或0个
    var testSaveBatchApiCtx = codeTempletCtx+"/xa/pimRole/saveBatch";  //保存多个


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
			"username" : ""
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
					obj["username"] = data.username
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
					"username" : data.username
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
			"username" : ""
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
                            saveData["pimRoleList"] = [];

                            trs.each(function (i, item) {
                            	var saveDataObj = {};
                            	saveDataObj = saveDataFun($(item));
                                saveData["pimRoleList"].push(saveDataObj);
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
        	username;

        	if (e.find(".js-id").length != 0) {
            	id = e.find(".js-id").val();
        	}
        	if (e.find(".js-username").length != 0) {
            	username = e.find(".js-username").val();
        	}
		
			if (id !=undefined && id != "") {
            	obj["id"] = id;
        	}
			if (username !=undefined && username != "") {
            	obj["username"] = username;
        	}
        return obj;
    }
	

	
}]);