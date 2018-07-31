var config = {
	//admin config
	copyright: {
		site : "http://www.sinux.com.cn",
		claim: " &copy; 2017-2025"
	},
	admin: {
//		host: "",
		//var host = "http://192.168.3.13:8086";
		title: "config自定义title",
		showModel: 'simple',
		passwordValify: {
            pwdRegStr: '^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,8}$',
            message: '密码必须包含数字、字母或下划线,并且为6-8个字符'
        }
	},
	dms: {
		host:  'http://192.168.3.13:8086/',
        showFiles: 5000,
        trashHash: 'C_'
	},
/*	sync_client:{
        host:''
    },
	sync_server:{
        host:''
    },*/
	portal: {
	    API_URL: 'http://192.168.3.12:8080',
	    copyright: {
	      linkUrl: 'http://www.sinux.com.cn',
	      copyInfo: '&copy;版权归成都中科合迅科技有限公司所有 ',
	    },
	    header:{
	      title:'中科合迅系统模块',
	      logo:'./assets/images/logo.png',
	    }
	  },
	  report: {
		  host:"http://192.168.3.13:8086"
	  }
}