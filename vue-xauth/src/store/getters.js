const getters = {
	getIsLogin:function(state){
		return state.isLogin
	},
	getIsLogout:function(state){
		return state.isLogout
	},
	getUserInfo:function(state){
		return state.userInfo
	},
	getUserMenu:function(state){
		return state.userMenu
	},
	//=================
	getRoleList:function(state){
		return state.roleList
	},
	getMenuList:function(state){
		return state.menuList
	},
	getUserList:function(state){
		return state.userList
	}
}
export default getters;