const mutations = {
	resetState:function(state){
		state.userInfo = {}
		state.userMenu = []
		state.isLogin = false
		state.roleList = []
		state.menuList = []
		state.userList = []
	},
	setIsLogin:function(state,isLogin){
		state.isLogin = isLogin
	},
	serUser:function(state,user){
		state.userInfo = user
	},
	serUserMenu:function(state,menus){
		state.userMenu = menus
	},
	setRoleList:function(state,roles){
		state.roleList = roles
	},
	setMenuList:function(state,menus){
		state.menuList = menus
	},
	setUserList:function(state,users){
		state.userList = users
	}
}

export default mutations;
