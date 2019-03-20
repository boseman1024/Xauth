<template>
  <div class="user">
  	<el-button type="text" @click="isShow=true">+Add New User</el-button>
      <el-table :data="userList" style="width: 100%" align="center">
	    <el-table-column label="用户名">
	      <template slot-scope="scope">
	        <el-popover trigger="hover" placement="top">
	          <p>头像: {{ scope.row.avatar }}</p>
	          <p>姓名: {{ scope.row.name }}</p>
	          <p>联系方式: {{ scope.row.phone }}</p>
	          <p>邮箱: {{ scope.row.email }}</p>
	          <div slot="reference">
	            <el-tag size="medium">{{ scope.row.username }}</el-tag>
	          </div>
	        </el-popover>
	      </template>
	    </el-table-column>
	    <el-table-column label="状态">
	    	<template slot-scope="scope">
	    		{{scope.row.enabled==1?'正常':'停用'}}
	    	</template>
	    </el-table-column>
	    <el-table-column label="角色">
	    	<template slot-scope="scope">
	    		<el-tag size="medium" type="success" v-for="role in scope.row.role" :key="role.id">
	    			{{ role.namezh }}
	    		</el-tag>
				<el-button size="mini" @click="toRole(scope.row)">+</el-button>
	    	</template>
	    </el-table-column>
	    <el-table-column label="操作">
	      <template slot-scope="scope">
	        <el-button
	          size="mini"
	          @click="toEdit(scope.row)">编辑</el-button>
	        <el-button size="mini" type="danger" @click="deleteUser(scope.row.username)">删除</el-button>
	      </template>
	    </el-table-column>
	  </el-table>

<el-dialog title="角色" :visible.sync="isRole" center :before-close="close">
	<el-transfer v-model="roleData.haveRole" :data="roleList" :props="{key: 'id',label: 'namezh'}" :titles="['所有', '已拥有']"></el-transfer>
      <span slot="footer" class="dialog-footer">
        <el-button @click="close">取 消</el-button>
        <el-button type="primary" @click="changeRole">保 存</el-button>
      </span>
    </el-dialog>


	  <el-dialog :title="isEdit?'编辑':'新增'" :visible.sync="isShow" center :before-close="close">
      <div>
      	<div v-if="!isEdit">
	        <span >用户名：</span>
	        <el-input placeholder="请输入用户名" v-model="user.username"></el-input>
	        <span>密码：</span>
	        <el-input type="password" placeholder="请输入密码" v-model="user.password"></el-input>
	    </div>
        <span>姓名：</span>
        <el-input placeholder="请输入姓名" v-model="user.name"></el-input>
        <span>联系方式：</span>
        <el-input placeholder="请输入联系方式" v-model="user.phone"></el-input>
        <span>邮箱：</span>
        <el-input placeholder="请输入邮箱" v-model="user.email"></el-input>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="close">取 消</el-button>
        <el-button v-if="isEdit" type="primary" @click="edit">保 存</el-button>
        <el-button v-else type="primary" @click="addUser">新 增</el-button>
      </span>
    </el-dialog>


  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'user',
  data () {
    return {
      isShow:false,
      user:{},
      isEdit:false,
      isRole:false,
      roleData:{
      	uid:'',
      	haveRole:[]
      }
    }
  },
  computed:{
    ...mapGetters({
    	userList : 'getUserList',
    	roleList : 'getRoleList'
    })
  },
  created(){
  	this.$store.dispatch("getAllUser")
  	this.$store.dispatch('getAllRole')
  },
  methods:{
  	close(){
  		this.isShow = false
    	this.user = {}
    	this.isEdit = false
    	this.isRole = false
    	this.roleData={
      		uid:'',
      		haveRole:[]
      	}
    },
    toRole(user){
    	this.roleData.uid = user.id
    	for(let n=0;n<user.role.length;n++){
    		this.roleData.haveRole.push(user.role[n].id)
    	}
    	this.isRole = true
    },
    changeRole(){
    	this.$store.dispatch("changeUserRole",this.roleData).then(res=>{
    		this.roleData = {
	      		uid:'',
	      		haveRole:[]
      		}
      		this.$store.dispatch("getAllUser")
      		this.isRole = false
      		this.$message({
              showClose: true,
              message: '角色修改完成',
              type: 'success'
            })
    	})
    },
    addUser(){
    	this.$store.dispatch("addUser",this.user).then(res=>{
    		if(res>0){
            this.$message({
              showClose: true,
              message: '新增用户成功',
              type: 'success'
            })
            this.$store.dispatch('getAllUser')
            this.isShow=false
            this.user={}
	          }else{
	            this.$message({
	              showClose: true,
	              message: '新增用户失败',
	              type: 'error'
	            })
	          }
    	})
    },
    toEdit(user){
    	this.user = user
    	this.isShow = true
    	this.isEdit = true
    },
    edit(){
    	this.$store.dispatch("updateUser",this.user).then(res=>{
    		if(res>0){
            this.$message({
              showClose: true,
              message: '修改用户成功',
              type: 'success'
            })
            this.$store.dispatch('getAllUser')
            this.isShow=false
            this.user={}
            this.isEdit = false
	          }else{
	            this.$message({
	              showClose: true,
	              message: '修改用户失败',
	              type: 'error'
	            })
	          }
    	})
    },
  	deleteUser(username){
  		this.$confirm('此操作将永久删除该用户：'+username+', 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(()=>{
      	this.$store.dispatch("deleteUser",username).then(res=>{
      		if(res>0){
            this.$message({
              showClose: true,
              message: '删除用户成功',
              type: 'success'
            })
            this.$store.dispatch('getAllUser')
          }else{
            this.$message({
              showClose: true,
              message: '删除用户失败',
              type: 'error'
            })
          }
      	})
      })
  	}
  }
}
</script>

<style scoped>

</style>
