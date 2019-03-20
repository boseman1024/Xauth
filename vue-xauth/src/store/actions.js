import axios from 'axios'

const baseurl = 'http://localhost:8080/'

const getJsonTree = function(data,parentid){
	let tree=[]
	for(let i=0;i<data.length;i++){ 
		let node=data[i]
		//()=> import('@/components/'+node.component.replace(/\"/g,""))
		if(node.parentid==parentid ){ 
			let newNode={
				id:node.id,
				name:node.name,
				path:node.path, 
				component:node.component,
				meta:{
					url:node.url,
					iconcls:node.iconcls,
					parentid:node.parentid
				},
				children:getJsonTree(data,node.id)
			}
			tree.push(newNode)             
		}
	}
	return tree
}

const actions = {
	async setIsLogin({commit},isLogin){
		commit('setIsLogin',isLogin)
	},
	async login({commit},user){
		let url = baseurl+'auth/login'
		const result = await axios.post(url,user)
		if(result.data.token){
			localStorage.setItem('token',result.data.token)
			localStorage.setItem('username',user.username)
			commit('setIsLogin',true)
			return true
		}else{
			return false
		}
	},
	async logout({commit}){
		localStorage.removeItem('token')
		localStorage.removeItem('username')
		commit('resetState')
	},
	async getUserInfo({commit},username){
		let url = baseurl+'user/userinfo?username='+username
		const result = await axios.get(url)
		if(result.data.user){
			commit('serUser',result.data.user)
		}
	},
	async getUserMenu({commit,rootState},username){
		let url = baseurl+'menu/usermenu?username='+username
		const result = await axios.get(url)
		if(result.data){
			commit('serUserMenu',getJsonTree(result.data))
		}
		return result.data
	},
	//----------ROLE-------------
	async getAllRole({commit}){
		let url = baseurl+'role/getall'
		const result = await axios.get(url)
		if(result.data){
			commit('setRoleList',result.data)
		}
	},
	async addRole({commit},role){
		let url = baseurl+'role/add'
		const result = await axios.post(url,role)
		return result.data
	},
	async deleteRole({commit},role){
		let url = baseurl+'role/delete?id='+role.id
		const result = await axios.get(url)
		return result.data
	},
	async updateRole({commit},role){
		let url = baseurl+'role/update'
		const result = await axios.post(url,role)
		return result.data
	},
	async updateRoleMenu({commit},roleMenu){
		let url = baseurl+'role/updatemenu?id='+roleMenu.role.id+'&menus='+roleMenu.menus
		const result = await axios.get(url)
		return result.data
	},
	//----------MENU-------------
	async getAllMenu({commit}){
		let url = baseurl+'menu/getall'
		const result = await axios.get(url)
		if(result.data){
			commit('setMenuList',getJsonTree(result.data))
		}
	},
	async deleteMenu({commit},id){
		let url = baseurl+'menu/delete?id='+id
		const result = await axios.get(url)
		return result.data
	},
	async addMenu({commit},menu){
		if(menu.parentid==null&&(menu.path==''||menu.path==null)){
			menu.url=0
		}else{
			menu.url=1
		}
		let url = baseurl+'menu/add'
		const result = await axios.post(url,menu)
		return result.data
	},
	async updateMenu({commit},menu){
		if(menu.parentid==null&&(menu.path==''||menu.path==null)){
			menu.url=0
		}else{
			menu.url=1
		}
		let url = baseurl+'menu/update'
		const result = await axios.post(url,menu)
		return result.data
	},
	//---------USER--------
	async getAllUser({commit}){
		let url = baseurl+"user/all"
		const result = await axios.get(url)
		if(result.data){
			commit('setUserList',result.data)
		}
	},
	async deleteUser({commit},username){
		let url = baseurl+'user/delete?username='+username
		const result = await axios.get(url)
		return result.data
	},
	async addUser({commit},user){
		let url = baseurl+'user/add'
		const result = await axios.post(url,user)
		return result.data
	},
	async updateUser({commit},user){
		let url = baseurl+'user/update'
		const result = await axios.post(url,user)
		return result.data
	},
	async changeUserRole({commit,rootState},roleData){
		let url = baseurl+'user/changerole?uid='+roleData.uid+'&roles='+roleData.haveRole
		const result = await axios.get(url)
		return result.data
	}
}

export default actions