<template>
  <div class="role">
    <div class="operation">
  	  <el-input class="opItem" placeholder="请输入角色名" v-model="newRole.name"></el-input>
      <el-input class="opItem" placeholder="请输入中文角色名" v-model="newRole.namezh"></el-input>
      <el-button class="opItem" @click="addRole">新增</el-button>
    </div>
    <el-collapse  accordion> 
  		<el-collapse-item :title="role.name+' | '+role.namezh" :name="role.id" v-for="(role,index) in roleList" :key="role.id">
        <el-tree class="menu" :data="menuList" default-expand-all :props="defaultProps" node-key="id" show-checkbox :default-checked-keys="role.menus" ref="tree">
          <span slot-scope="{node,data}">
            <span>{{data.meta.url==1?'菜单':'列表'}}：{{node.label}}</span>
          </span>
        </el-tree>
        <el-button type="text" @click="saveRole(role,index)">保存</el-button>
        <el-button type="text" @click="showUpdate(role)">修改</el-button>
        <el-button type="text" @click="showDelete(role)">删除</el-button>
  		</el-collapse-item>
  	</el-collapse>

    <el-dialog title="删除" :visible="isDelete" width="30%" center>
      <span>是否删除角色「{{delRole.namezh}}」？</span>
      <span slot="footer">
        <el-button @click="isDelete=false">取 消</el-button>
        <el-button type="primary" @click="deleteRole">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="修改" :visible.sync="isUpdate" width="30%" center>
      <span>角色名：</span>
      <el-input placeholder="请输入角色名" v-model="upRole.name"></el-input>
      <span>中文角色名：</span>
      <el-input placeholder="请输入中文角色名" v-model="upRole.namezh"></el-input>
      <span slot="footer">
        <el-button @click="isUpdate=false">取 消</el-button>
        <el-button type="primary" @click="updateRole">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'role',
  data () {
    return {
      newRole:{
        name:'',
        namezh:''
      },
      isDelete:false,
      delRole:{
        id:'',
        name:'',
        namezh:''
      },
      isUpdate:false,
      upRole:{
        id:'',
        name:'',
        namezh:''
      },
      defaultProps:{
        children: 'children',
        label: 'name'
      },
    }
  },
  computed:{
    ...mapGetters({
    	roleList : 'getRoleList',
      menuList : 'getMenuList'
    })
  },
  created(){
    this.$store.dispatch('getAllRole')
    this.$store.dispatch('getAllMenu')
  },
  methods:{
    addRole(){
      this.$store.dispatch('addRole',this.newRole).then(res=>{
        if(res>0){
          this.$message({
            showClose: true,
            message: '新增角色成功',
            type: 'success'
          })
          this.$store.dispatch('getAllRole')
        }else{
          this.$message({
            showClose: true,
            message: '新增角色失败',
            type: 'error'
          })
        }
      })
    },
    showDelete(role){
      this.isDelete = true
      this.delRole = role
    },
    deleteRole(){
      this.$store.dispatch('deleteRole',this.delRole).then(res=>{
        if(res>0){
          this.$message({
            showClose: true,
            message: '删除角色成功',
            type: 'success'
          })
          this.$store.dispatch('getAllRole')
          this.isDelete = false
        }else{
          this.$message({
            showClose: true,
            message: '删除角色失败',
            type: 'error'
          })
        }
      })
    },
    showUpdate(role){
      this.isUpdate = true
      this.upRole = role
    },
    updateRole(){
      this.$store.dispatch('updateRole',this.upRole).then(res=>{
        if(res>0){
          this.$message({
            showClose: true,
            message: '修改角色成功',
            type: 'success'
          })
          this.$store.dispatch('getAllRole')
          this.isUpdate = false
        }else{
          this.$message({
            showClose: true,
            message: '修改角色失败',
            type: 'error'
          })
        }
      })
    },
    saveRole(role,index){
      let menus = this.$refs.tree[index].getCheckedKeys()
      if(this.$refs.tree[index].getHalfCheckedKeys().length!=0){
        menus.push(this.$refs.tree[index].getHalfCheckedKeys())
      }
      let roleMenu={}
      roleMenu.role = role
      roleMenu.menus = menus
      this.$store.dispatch('updateRoleMenu',roleMenu).then(res=>{
        if(res>0){
          this.$message({
            showClose: true,
            message: '修改角色菜单成功',
            type: 'success'
          })
          this.$store.dispatch('getAllRole')
          this.isUpdate = false
        }
      })
    }
  }
}
</script>

<style scoped>
.operation{
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  margin: 10px;
}
.opItem{
  margin:0 10px; 
}
.menu{
  
}
</style>
