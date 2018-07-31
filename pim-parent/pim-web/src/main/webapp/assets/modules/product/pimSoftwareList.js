$(function () {
	isPermission($(".js-add"), "product:pimSoftware:add");
	isPermission($(".js-edit"), "product:pimSoftware:edit");
	isPermission($(".js-delete"), "product:pimSoftware:del");
	isPermission($(".js-search"), "product:pimSoftware:view");

    function isPermission(el, permission) {
        if(!hasEnv(permission)){
            el.css({"display": "none"});
        }
    }
	

	 //test
    var testListApiCtx = codeTempletCtx+"/product/pimSoftware/list";  //test
    var testDeleteApiCtx = codeTempletCtx+"/product/pimSoftware/delete";  //删除
    var testDeleteBatchApiCtx = codeTempletCtx+"/product/pimSoftware/deleteBatch"; //删除多个
    var testGetInitDataApiCtx = codeTempletCtx+"/product/pimSoftware/init"; //获取下拉，单选，复选初始数据

    //请求路径
    var codeGenxhl = {
      "test": {
        "tableList": testListApiCtx,  //表格数据
        "delete": testDeleteApiCtx,  //删除
        "deleteBatch": testDeleteBatchApiCtx,  //删除多个
        "initData": testGetInitDataApiCtx
      }
    };

	//判断权限，如果权限不匹配则隐藏button
	function permit(event,permission) {
		if(!hasEnv(permission)){
			$(event).target.css("display","none");
		}
	}
	
	//顶部查询区域滚动条
  $(".queryAreaArea").niceScroll({
      "cursorcolor": '#999',
      "background": "rgba(0, 0, 0, 0.18)",
      "cursorborder": "0px solid #fff", // CSS方式定义滚动条边框
      "cursorborderradius": "0", // 滚动条圆角（像素）
      "cursorminheight": 1, // 设置滚动条的最小高度 (像素)
      "directionlockdeadzone": 1 // 设定死区，为激活方向锁定（像素）
    }).resize();
	
	 //默认条件查询输入值隐藏
    $(".js-inputValueArea").hide();
	 //获取下拉，单选，复选初始数据
    $.ajax({
        url: codeGenxhl.test.initData,
        type: 'get',
        crossDomain: true == !(document.all),
        contentType: 'application/json',
        success: function(data) {
            if (data.code == 200) {
                var data = data.data;

                //条件查询方法
                queryConditionFun(data);

                //初始化表格
                initFun();
            } else {
                top.layer.msg('生成条件查询失败', {
                    icon: 2,
                    time: 1000
                })
            }
        },
        error: function () {

        }
    });
    
    //表格高度
  function setTableHeight() {
    //设置list页面的高
    var bodyH = $(document.body).outerHeight(true);
    var ptb = parseFloat($(".content-body").css("paddingTop")) * 2; //页面上下padding值40
    var cHeadingH = $('.listPage .content-heading').outerHeight(true);
    var menuBtnRowH = $('.menuBtnRow').outerHeight(true); //按钮行高度
    var mt20 = 20; //table距离顶部的距离
    var dataTables_bottomH = 70;
    var dataTables_scrollHeadH = 58;
    var mcH = bodyH - ptb - cHeadingH - menuBtnRowH - mt20 - dataTables_bottomH - dataTables_scrollHeadH + 10;

    return mcH;
  }
		
		  //表格初始高度
  var nScrollBody = null,
    nTableWrapper = null;

  $(window).resize(function() {
    var windowW = $(window).width();
    nTableWrapper.css({"width": windowW-20});

    nScrollBody.css({
      "height": setTableHeight()
    });

    //滚动条
    nScrollBody.getNiceScroll().resize();

  });
  

    /**
    * 生成表格
    * @param params.url  ajax 请求路径
    * @param params.columns  //行  [{}] 
    **/
    function createTable(params) {
      var tableObj = {
          "columns": params.columns,
          /**
          * 设置列定义初始化属性
          **/
          "columnDefs": [],
          "drawCallback": function( settings ) {
              //渲染完毕后的回调
		        var windowW = $(window).width();
		        nTableWrapper = $(settings.nTableWrapper);
		        nScrollBody = $(settings.nScrollBody);
		
		        nTableWrapper.css({"width": windowW-20});
		        nScrollBody.find(".js-select-all").prop('checked', false).removeClass('checked');
		
		        nScrollBody.css({
		          "height": setTableHeight()
		        });
		
		        nScrollBody.niceScroll({
		          "cursorcolor": '#999',
		          "background": "rgba(0, 0, 0, 0.18)",
		          "cursorborder": "0px solid #fff", // CSS方式定义滚动条边框
		          "cursorborderradius": "0", // 滚动条圆角（像素）
		          "cursorminheight": 1, // 设置滚动条的最小高度 (像素)
		          "directionlockdeadzone": 1 // 设定死区，为激活方向锁定（像素）
		        }).resize();
          },
          "sScrollY": true, //DataTables的高  
          "sScrollX" : true, //DataTables的宽
          "aLengthMenu" : [10, 20, 40, 60], //更改显示记录数选项
          "iDisplayLength" : 10, //默认显示的记录数  
          "bAutoWidth" : true, //是否自适应宽度 
          "bScrollCollapse" : false, //是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变
          "processing": true,
          "serverSide": true,  //是否启动服务器端分页  false客户端分页
          "bInfo" : true, //是否显示页脚信息，DataTables插件左下角显示记录数  
          "pagingType": "simple_numbers", //分页样式：simple,simple_numbers,full,full_numbers 
          "bSort" : false, //是否启动各个字段的排序功能   
          "bFilter" : false, //是否启动过滤、搜索功能
          "bLengthChange": true,   //去掉每页显示多少条数据方法
          "oLanguage": { //国际化配置
        "sProcessing": "正在获取数据，请稍后...",
        "sLengthMenu": "每页显示 _MENU_ 条记录",
        "sZeroRecords": "没有您要搜索的内容",
        // "sInfo": '<span>从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条</span>',
        "sInfo": '总共 _TOTAL_ 条记录',
        "sInfoEmpty": "记录数为0",
        "sInfoFiltered": "(全部记录数 _MAX_ 条)",
        "sInfoPostFix": "",
        "sSearch": "搜索",
        "sUrl": "",
        "oPaginate": {    
          "sFirst" : "第一页",    
          "sPrevious" : "&#xe656;",    
          "sNext" : "&#xe6b3;",    
          "sLast" : "最后一页"    
        } 
          },
          /**
          * 自定义布局
          **/
          "dom": 'rt<"dataTables_bottom"iflp><"clear">'
      };


      if (params.type == "richtext") {  //富文本测试
        tableObj["ajax"] = function(data, callback, settings) {//ajax配置为function,手动调用异步查询

            //封装请求参数
            var listParam = params.getQueryCondition(data);
            var listParamStr = JSON.stringify(listParam);

            $.ajax({
                url: params.url,
                type: 'post',
                crossDomain: true == !(document.all),
                data: listParamStr,
                contentType: 'application/json',
                success: function(result) {
                    var listArr = result.data.list;
                    var listArrLen = listArr.length;
                    //封装返回数据，这里仅演示了修改属性名
                    var returnData = {};

                    if (listArrLen > 0) {
                      for (var i = 0; i < listArrLen; i++) {
                        for (var j in listArr[i]) {
                            if (listArr[i].hasOwnProperty(j)) {
                                //判断对象是否存在某个属性
									if (!listArr[i].hasOwnProperty("createDate")) {
                                    	listArr[i]["createDate"] = "";
                                	}
									if (!listArr[i].hasOwnProperty("updateDate")) {
                                    	listArr[i]["updateDate"] = "";
                                	}

                                if (!listArr[i].hasOwnProperty("operation")) {
                                    listArr[i]["operation"] = "";
                                }

                                if (!listArr[i].hasOwnProperty("chexkbox")) {
                                    listArr[i]["chexkbox"] = "";
                                }

                            }
                          }
                      }

                      returnData.draw = data.draw;//这里直接自行返回了draw计数器,应该由后台返回
                      returnData.recordsTotal = result.data.count;
                      returnData.recordsFiltered = result.data.count;//后台不实现过滤功能，每次查询均视作全部结果
                      returnData.data = result.data.list;
                    }else {
                      returnData.draw = data.draw;//这里直接自行返回了draw计数器,应该由后台返回
                      returnData.recordsTotal = 0;
                      returnData.recordsFiltered = 0;//后台不实现过滤功能，每次查询均视作全部结果
                      returnData.data = [];
                    }

                    callback(returnData);
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    //alert("查询失败");
                    var content = '查询失败';
                    var icon = 2;
                    messageTip(content, icon);
                }
            });

        };




var columnsLen = params.columns.length;
      for (var i = 0; i < columnsLen; i++) {
        if (!params.columns[i].hasOwnProperty("visible") || params.columns[i]["visible"]) {
          if (params.columns[i]["data"] == "checkbox") {
            var checkedObj = {
              "targets": i, //目标列位置
              "data": "checkbox",
              "className": "select-checkbox w60 ac", //样式
              "render": function(data, type, full) { // 返回自定义内容
                return '<label class="option mn">' +
                  '<input class="js-select-one" type="checkbox">' +
                  '<span class="checkbox mn"></span>' +
                  '</label>';
              }
            };

            tableObj["columnDefs"].push(checkedObj);

          }else if (params.columns[i]["data"] == "operation") {
            var operationObj = { // 增加一列，包括删除和修改，同时将我们需要传递的数据传递到链接中
              "targets": i, //目标列位置
              "data": "operation",
              "render": function(data, type, full) { // 返回自定义内容

                return '<a class="btn btn-primary btn-sm js-edit"><i class="fa fa-edit"></i>修改</a>' +
                        '<a class="btn btn-danger btn-sm js-delete"><i class="fa fa-trash"></i>删除</a>';
              }
            };
            
            tableObj["columnDefs"].push(operationObj);

          }
          

        }
            
      }
            
      }

      return tableObj;
    }
	
	
	/**
  * 设置富文本图片，视频展示宽高度样式
  * 预设宽高度固定值为100，如果大于100，则显示100；如果小于100大于0，则按原来大小显示
  * param data 数据
  * param flag 图片，音频标志
  * param obj jq对象
  **/
  function setImgRadio(data, flag, obj) {
    var w1 = 100,
        h1 = 100;  //预设固定值宽高

    var w = Number(obj.attr("width"));
    var h = Number(obj.attr("height"));

    if (!isNaN(w)) {
      if (w > 100 || w <= 0) {
        var regw = new RegExp(' width="' + w + '"');
        data = data.replace(regw, " width=" + w1);
      }
    }

    if (!isNaN(h)) {
      if (h > 100 || h <= 0) {
        var regh = new RegExp(' height="' + h + '"');
        data = data.replace(regh, " height=" + h1);
      }
    }

    //没有宽高
    if (isNaN(w) && isNaN(h)) {
      data = data.replace(flag, flag + " width=" + h1 + " height=" + h1);
    }

    return data;
  }
  
  
  
    var datatables = null;
    //初始化gen table 表格数据
  	var createTableParam = {};

	//初始化所有方法
  	function initFun() {
  	//start
  	var columnsArr = [
            {
                "data": "checkbox"
            },
	        	
	        	
	        	
	        	
	        	
	        	
	        	{ "data": "createDate" },
	        	
	        	{ "data": "updateDate" },
			{ "data": "operation" }
        ];
        
        
         var columnsArrLen = columnsArr.length;

	    for (var i = 0; i < columnsArrLen; i++) {
	    	if (columnsArr[i]["data"] == "checkbox") {
			   columnsArr[i]["width"] = "59px";
			}
	        	if (columnsArr[i]["data"] == "createDate") {
			        columnsArr[i]["width"] = "100px";
			    }
	        	if (columnsArr[i]["data"] == "updateDate") {
			        columnsArr[i]["width"] = "100px";
			    }
	    }
	    
  	//初始化gen table 表格数据
    createTableParam = {
        "url": codeGenxhl.test.tableList,
        "type": "richtext",
        "columns": columnsArr,
        "getQueryCondition" : function(data) {
            var param = {};

            //组装查询参数
            param.query = {};
            //组装分页参数
            param.pageNo = Math.ceil(data.start/data.length)+1;
            param.pageSize = data.length;

            return param;
        }
    };

    datatables = $('#contentTable').dataTable(createTable(createTableParam));//此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象


    //重置
    $(".js-refresh").on("click", function () {

        createTableParam["getQueryCondition"] = function (data) {
            var param = {};

            //组装查询参数
            param.query = {};

            $(".js-queryArea").find("tr").each(function(i, item) {
                    $(item).remove();
                    $(".js-queryConditionMenu").children().each(function(i, item) {
                        if ($(item).hasClass("hide")) {
                            $(item).removeClass("hide").addClass("show");
                        }
                    });
                });

			$(".js-inputValueArea").hide();
            $(".js-queryConditionDropdown").children("button").children("span.text").text('');
            
            //组装分页参数
            param.pageNo = Math.ceil(data.start/data.length)+1;
            param.pageSize = data.length;;

            return param;
        };


        if (datatables) {
            datatables.fnDraw(false);  //false 不会跳转到第一页，保留页面的页码和显示条数
        }else {
            datatables = $('#contentTable').dataTable(createTable(createTableParam));//此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象
        }
    });
    
    
    //全选、全不选
    //将当前页的列表数据全部选中
    $(".js-select-all").on('click',function () {
        var checkbox_selects = $(this).parents(".dataTables_scrollHead").next().find(".js-select-one");
        if (this.checked) {
            checkbox_selects.each(function () {
                this.checked = true;
            });
        }else {
            checkbox_selects.each(function () {
                this.checked = false;
            });
        }
    });

	function reloadPage() {
      datatables.fnDraw(false); //false 不会跳转到第一页，保留页面的页码和显示条数

      nScrollBody.css({
        "height": setTableHeight()
      });

      //滚动条
      nScrollBody.getNiceScroll().resize();
    }

    //添加
    $(".js-add").on("click", function () {
        openDialog('添加数据', staticCtx + '/modules/product/pimSoftwareAddForm.html?type=add&id=null', dialWidth, 'auto', '', reloadPage);

    });

    //修改
    $(".js-edit").on("click", function () {

        var that = $(this);
        var editFlag = false;
        var count = 0;
        $(".js-select-one").each(function (i, item) {

            if (item.checked) {
                editFlag = true;
                count++;
            }
            
        });

        if (editFlag) {  //有勾选

            if (count != 1) {  //多个
                //当前行的数据
               // var parameter = {
               //     "ids": []
               // };

              //  var trs = $("#contentTable tbody input[type=checkbox]:checked").parents('tr');
              //  trs.each(function (i, item) {
              //      var data = $('#contentTable').DataTable().row($(item)).data();
              //      parameter["ids"].push(data.id);
              //  });
                
              //  openDialog('修改数据', staticCtx + '/modules/product/pimSoftwareEditForm.html?type=multiple&id=' + parameter["ids"], dialWidth, 'auto', '', reloadPage);
				selectTableRow();
            }else {  //单个
                //当前行的数据
                var data = $('#contentTable').DataTable().row($("#contentTable tbody input[type=checkbox]:checked").parents('tr')).data();
                openDialog('修改数据', staticCtx + '/modules/product/pimSoftwareEditForm.html?type=single&id=' + data.id, dialWidth, 'auto', '', reloadPage);

            }
            
            
        }else {  //没有勾选
            selectTableRow();
        }

    });

    //删除
    $(".js-delete").on("click", function () {
        var removeFlag = false;
        $(".js-select-one").each(function (i, item) {

            if (item.checked) {
                removeFlag = true;
            }
            
        });

        if (removeFlag) {  //有勾选            
            var rowParam = $("#contentTable tbody input[type=checkbox]:checked").parents('tr');
            var deleteParam = {};
            var jsonParam = {};
            jsonParam["pimSoftwareList"] = [];
            rowParam.each(function (i, item) {
                var data = $('#contentTable').DataTable().row(item).data();
                var obj = {
										"id" : data.id
                };
                jsonParam["pimSoftwareList"].push(obj);
            });
            var jsonParamStr = JSON.stringify(jsonParam);
            deleteParam["rowParam"] = rowParam;
            deleteParam["url"] = codeGenxhl.test.deleteBatch;
            deleteParam["datatables"] = datatables;
            deleteParam["jsonParamStr"] = jsonParamStr;
    
            deleteTableRowMore(deleteParam);
            
        }else {  //没有勾选
            selectTableRow();
        }
        
    });


    //查询
    $(".js-sortOrRefresh").on("click", function () {
    	createTableParam["getQueryCondition"] = function (data) {
            var param = {};

            //组装查询参数
            param.query = {};
            var keyArray = [];
            $(".js-queryArea").find("tr").each(function(i, item) {
                    var key = $(item).attr("data-key");
                    var queryType = $(item).attr("data-queryType");
                    var value = "";
                    switch (queryType) {
                        case "textarea":
                            value = $(item).find("textarea").val();
                            break;
                        case "dateselect":
                            value = $(item).find("input").val();
                            break;
                        case "select":
                            value = $(item).find("select").children("option:selected").attr("data-option");
                            break;
                        case "radiobox":
                            value = $(item).find("input").attr("data-inputkey");;
                            break;
                        default:
                        value = $(item).find("input").val();
                    }
                    if (value != '' && value != undefined) {
                        param.query[key] = value;
                    }
                });

            //组装分页参数
            param.pageNo = Math.ceil(data.start/data.length)+1;
            param.pageSize = data.length;

            return param;
        };
        
        if (datatables) {
            datatables.fnDraw(false);  //false 不会跳转到第一页，保留页面的页码和显示条数
        }else {
            datatables = $('#contentTable').dataTable(createTable(createTableParam));//此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象
        } 
    });

    

 //-----------------------------------------------
    var columnsLen = createTableParam.columns.length;
    var tempColumnsArr = [];
    for (var i = 0; i < columnsLen; i++) {
        if (!createTableParam.columns[i].hasOwnProperty("visible") || createTableParam.columns[i]["visible"]) {
            tempColumnsArr.push(createTableParam.columns[i]);
        }
    }

    var editObjStr = '',
        deleteObjStr = '';
    var tempColumnsArrLen = tempColumnsArr.length;
    for (var j = 0; j < tempColumnsArrLen; j++) {
        if (tempColumnsArr[j]["data"] == "operation") {
            editObjStr = 'tr td:nth-child(' + (j+1) + ') .js-edit';
            deleteObjStr = 'tr td:nth-child(' + (j+1) + ') .js-delete';
        }
    }

    //修改操作
    $("#contentTable tbody").on("click", editObjStr, function () {
        var that = $(this);
        //当前行的数据
        var data = $('#contentTable').DataTable().row(that.parents('tr')).data();
        openDialog('修改数据', staticCtx + '/modules/product/pimSoftwareEditForm.html?type=single&id=' + data.id, dialWidth, 'auto', '', reloadPage);

    });

    //删除操作
    $("#contentTable tbody").on("click", deleteObjStr, function () {


        var rowParam = $(this).parents('tr');
        var data = $('#contentTable').DataTable().row(rowParam).data();
        
        var deleteParam = {};
        var jsonParam = {
						"id" : data.id
        };
        var jsonParamStr = JSON.stringify(jsonParam);
        deleteParam["rowParam"] = rowParam;
        deleteParam["url"] = codeGenxhl.test.delete;
        deleteParam["datatables"] = datatables;
        deleteParam["jsonParamStr"] = jsonParamStr;

        deleteTableRow(deleteParam);
    });


    

    //删除表格每一行
    function deleteTableRow(param) {
      top.layer.confirm("确认要彻底删除数据吗?", {icon: '3', title: '系统提示'}, function (index) {
        //删除
        $.ajax({
            url: param.url,
            type: 'POST',
            data: param.jsonParamStr,
            contentType: 'application/json',
            crossDomain: true == !(document.all),
            success: function (data) {
                if (data.code == 200) {
                  var content = data.message;
                  var icon = 1;
                  messageTip(content, icon);
                  param.datatables.api().row(param.rowParam).remove().draw(false);
                  //滚动条
    				$(".dataTables_scrollBody").getNiceScroll().resize();

                }else {
                  var content = data.message;
                  var icon = 2;
                  messageTip(content, icon);
                }

                top.layer.close(index);
            },
            error: function () {
                var content = '删除错误';
                var icon = 2;
                messageTip(content, icon);
            }
        });

      });
    }
    
    //删除表格多行
    function deleteTableRowMore(param) {
      top.layer.confirm("确认要彻底删除数据吗?", {icon: '3', title: '系统提示'}, function (index) {
        //删除
        $.ajax({
            url: param.url,
            type: 'POST',
            data: param.jsonParamStr,
            contentType: 'application/json',
            crossDomain: true == !(document.all),
            success: function (data) {
                if (data.code == 200) {
                  var content = data.message;
                  var icon = 1;
                  messageTip(content, icon);
                  param.datatables.api().row(param.rowParam).remove().draw(false);
                  //滚动条
    				$(".dataTables_scrollBody").getNiceScroll().resize();

                }else {
                  var content = data.message;
                  var icon = 2;
                  messageTip(content, icon);
                }

                top.layer.close(index);
            },
            error: function () {
                var content = '删除错误';
                var icon = 2;
                messageTip(content, icon);
            }
        });

      });
    }

    //提示
    function selectTableRow() {
      top.layer.alert("请选择一条数据", {icon: '0', title: '警告'});
    }
  	//end
  	}
    


	//成功，错误提示
    function messageTip(content, icon) {
      layer.msg(content, {
        icon: icon,  //1 成功，  2 失败
        time: 2000 //2秒关闭（如果不配置，默认是3秒）
      }, function(){
        //do something

      }); 
    }





function queryConditionFun(data) {
        //动态生成
        var queryTypeArr = [];
        var queryKeyArr = [];
        var queryCommentArr = [];

        var $inputValue = $(".js-inputValue");
        var currentQueryType = ''; //当前下拉菜单选中的类型
        var li = ''; //查询下拉li
        var currentThat = null; //当前下拉菜单选项，obj
        var currentIndex = 0; //当前下拉菜单选中的索引，默认0
        var currentName = ''; //当前下拉菜单选中的内容
        var currentKey = ''; //当前下拉菜单选中的关键字
        var content = $('<div class="dicDial" id="content"><form class="form-horizontal" onsubmit="return false;"><table class="formTable js-queryArea">' +
            '<tbody>' +

            '</<tbody>' +
            '</table></form></div>');
        $("body").append(content);

        //动态生成查询条件下拉框内容
        
        for(var i  = 0;i < queryCommentArr.length; i++){
        	li += '<li class="show"><a href="#">' + queryCommentArr[i] + '</a></li>';
        }
        
        //生成查询条件下拉选项
        $(".js-queryConditionMenu").append(li);
        //给下拉查询菜单添加类型，关键字，索引
        $(".js-queryConditionMenu").children().each(function(i, item) {
            $(item).attr({ "data-queryType": queryTypeArr[i], "data-key": queryKeyArr[i], "data-index": i });
        });

        //点击下拉查询条件选项
        $(".js-queryConditionMenu").on("click", "li", function(event) {
            currentThat = $(event.currentTarget);
            currentIndex = currentThat.attr("data-index");
            currentKey = currentThat.attr("data-key");
            currentName = currentThat.children().text();
            currentQueryType = currentThat.attr("data-queryType");
            $(".js-queryConditionButton").attr("data-queryType", currentQueryType).children("span.text").text(currentName);
            $inputValue.empty();

            switch (currentQueryType) {
                case "textarea":
                    var inputValue = '<div class="field" data-queryType="' + currentQueryType + '">' +
                        '<textarea class="form-control gui-input"></textarea>' +
                        '</div>';
                    $inputValue.append(inputValue);
                    $(".js-inputValueArea").show();
                    break;
                case "dateselect":
                    var inputValue = '<div class="field append-icon" data-queryType="' + currentQueryType + '">' +
                        '<input class="form-control gui-input" value="" readonly="readonly" type="text" onClick="WdatePicker({dateFmt:\'yyyy-MM-dd HH:mm:ss\'})">' +
                        '<label class="field-icon">' +
                        '<i class="fa fa-calendar"></i>' +
                        '</label>' +
                        '</div>';
                    $inputValue.append(inputValue);
                    $(".js-inputValueArea").show();
                    break;
                case "select":
                    var inputValue = '<div class="field select" data-queryType="' + currentQueryType + '">' +
                        '<select class="js-select">' +
                        '<option data-option="" value=""></option>' +
                        '</select>' +
                        '<i class="arrow"></i>' +
                        '</div>';
                    $inputValue.append(inputValue);

                    //给不同下拉菜单,赋不同数据源值
                    for (var key in data) {
                    	switch (currentKey+"List") {
                    	}
                    }
                    $(".js-inputValueArea").show();
                    break;
                case "radiobox":
                    var inputValue = '<div class="field radioGroup js-radiobox" data-queryType="' + currentQueryType + '" style="display: inline-block;">' +
                        '</div>';
                    $inputValue.append(inputValue);

                    //给不同单选，赋值不同数据源值
                    for (var key in data) {
                    	switch (currentKey+"List") {
                    	}
                    }
                    $(".js-inputValueArea").show();
                    break;
                default:
                var inputValue = '<div class="field" data-queryType="' + currentQueryType + '">' +
                        '<input class="form-control gui-input" value="" type="text">' +
                        '</div>';
                    $inputValue.append(inputValue);
                    $(".js-inputValueArea").show();
            }
        });

        //点击增加查询条件
        $(".js-addQueryCondition").on("click", function(event) {
            if ($inputValue.is(":visible")) {
                var areaDiv = $inputValue.children();
                var queryType = areaDiv.attr("data-queryType");
                var contentBody = '';
                var currentValue = '';
                var contentBehind = '</div>' +
                    '</div>' +
                    '</div>' +
                    '</td>' +
                    '<td class="col-sm-4">' +
                    '<div class="form-group operationQuery pull-right col-md-auto">' +
                    '<button class="btn btn-system js-refreshQueryCondition"><i class="fa fa-file-text-o"></i>删除</button>' +
                    '</div>' +
                    '</td>' +
                    '</tr>';

                switch (queryType) {
                    case "textarea":
                        currentValue = areaDiv.children("textarea").val();
                        contentBody = '<tr data-index="' + currentIndex + '" data-key="' + currentKey + '" data-queryType="' + currentQueryType + '">' +
                            '<td class="col-sm-8">' +
                            '<div class="form-group">' +
                            '<label class="col-sm-4 control-label" title="' + currentName + '">' + currentName + ':</label>' +
                            '<div class="col-sm-8">' +
                            '<div class="field">' +
                            '<textarea class="form-control" readonly="readonly">' + currentValue + '</textarea>' +
                            contentBehind;
                        break;
                    case "dateselect":
                        currentValue = areaDiv.children("input").val();
                        contentBody = '<tr data-index="' + currentIndex + '" data-key="' + currentKey + '" data-queryType="' + currentQueryType + '">' +
                            '<td class="col-sm-8">' +
                            '<div class="form-group">' +
                            '<label class="col-sm-4 control-label" title="' + currentName + '">' + currentName + ':</label>' +
                            '<div class="col-sm-8">' +
                            '<div class="field append-icon">' +
                            '<input type="text" class="form-control" readonly="readonly" name="' + currentValue + '" value="' + currentValue + '">' +
                            '<label class="field-icon">' +
                            '<i class="fa fa-calendar"></i>' +
                            '</label>' +
                            contentBehind;

                        break;
                    case "select":
                        currentValue = areaDiv.children("select").children("option:selected").attr("data-option");
                        contentBody = '<tr data-index="' + currentIndex + '" data-key="' + currentKey + '" data-queryType="' + currentQueryType + '">' +
                            '<td class="col-sm-8">' +
                            '<div class="form-group">' +
                            '<label class="col-sm-4 control-label" title="' + currentName + '">' + currentName + ':</label>' +
                            '<div class="col-sm-8">' +
                            '<div class="field select">' +
                            '<select>' +
                            '<option data-option="' + currentValue + '" value="' + currentValue + '">' + currentValue + '</option>' +
                            '</select>' +
                            '<i class="arrow"></i>' +
                            contentBehind;
                        break;
                    case "radiobox":
                        areaDiv.children().each(function(i, item) {
                            if ($(item).children("input").hasClass("checked")) {
                                currentValue += $(item).children("input").attr("data-inputkey");
                            }
                        });
                        contentBody = '<tr data-index="' + currentIndex + '" data-key="' + currentKey + '" data-queryType="' + currentQueryType + '">' +
                            '<td class="col-sm-8">' +
                            '<div class="form-group">' +
                            '<label class="col-sm-4 control-label" title="' + currentName + '">' + currentName + ':</label>' +
                            '<div class="col-sm-8">' +
                            '<div class="field">' +
                            '<label class="option option-primary">' +
                            '<input name="" data-inputkey="' + currentValue + '" value="" class="" type="radio" checked>' +
                            '<span class="radio"></span>' + currentValue +
                            '</label>' +
                            contentBehind;
                        break;
                      default:
                      currentValue = areaDiv.children("input").val();
                        contentBody = '<tr data-index="' + currentIndex + '" data-key="' + currentKey + '" data-queryType="' + currentQueryType + '">' +
                            '<td class="col-sm-8">' +
                            '<div class="form-group">' +
                            '<label class="col-sm-4 control-label" title="' + currentName + '">' + currentName + ':</label>' +
                            '<div class="col-sm-8">' +
                            '<div class="field">' +
                            '<input type="text" class="form-control" readonly="readonly" name="' + currentValue + '" value="' + currentValue + '">' +
                            contentBehind;
                }
                if (currentName != '' && currentValue != '') {
                    $("#content").find("tbody").append($(contentBody));
                    $(".js-inputValueArea").hide();
                    currentThat.parents(".js-queryConditionDropdown").children("button").children("span.text").text('');
                    currentThat.removeClass("show").addClass("hide");
                    top.layer.msg('添加成功', {
                        icon: 1,
                        time: 1000
                    })
                } else {
                    top.layer.msg('请输入查询条件', {
                        icon: 2,
                        time: 1000
                    })
                }

            }
        });

        //点击展示查询条件
        $(".js-showQueryCondition").on("click", function(event) {

            top.layer.open({
                type: 1,
                title: "查询条件列表",
                area: [dialWidth, dialHeight],
                content: $("#content").prop("outerHTML"),
                btn: ['关闭'],
                success: function(layero, index) {
                    //点击删除按钮，删除查询条件,查询列表恢复
                    layero.on("click", ".js-refreshQueryCondition", function (event) {
                        var that = $(event.currentTarget);
                        var tr = that.parents("tr");
                        var index = tr.attr('data-index');
                        var text = that.find("span").text();
                        $(".js-queryConditionMenu").children().each(function(i, item) {
                            if ($(item).attr("data-index") == index) {
                                $(item).removeClass("hide").addClass("show");
                            }
                        });

                        $("#content").find("tbody").find("tr").each(function(i, item) {
                            if ($(item).attr("data-index") == index) {
                                $(item).remove();
                            }
                        });
                        tr.remove();
                        top.layer.msg('删除成功', {
                            icon: 1,
                            time: 1000
                        })
                    });
                },
                yes: function(index) {
                    top.layer.close(index);
                }
            });

        });
    }
    
});