<template>
  <div class="menus">
    <el-button type="text" @click="isShow=true">+Add Root Menu</el-button>
    <el-tree :data="menuList" default-expand-all :props="defaultProps">
      <span class="menu" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <el-button type="text" @click="toAppend(data)" v-if="data.meta.parentid==null">
            Append
          </el-button>
          <el-button type="text" @click="toEdit(data)">
            Edit
          </el-button>
          <el-button type="text" @click="remove(data)">
            Delete
          </el-button>
        </span>
      </span>
    </el-tree>

    <el-dialog title="菜单" :visible.sync="isShow" center :before-close="close">
      <div>
        <span>菜单名：</span>
        <el-input placeholder="请输入菜单名" v-model="menu.name"></el-input>
        <span>菜单路径：</span>
        <el-input placeholder="请输入菜单路径" v-model="menu.path"></el-input>
        <span>菜单组件：</span>
        <el-input placeholder="请输入菜单组件" v-model="menu.component"></el-input>
        <span>菜单图标：</span>
        <el-input placeholder="请输入菜单图标" v-model="menu.iconcls"></el-input>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="close">取 消</el-button>
        <el-button v-if="menu.id" type="primary" @click="edit">确 定</el-button>
        <el-button v-else type="primary" @click="addMenu">确 定</el-button>
      </span>
    </el-dialog>





  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'menus',
  data () {
    return {
      isShow:false,
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      menu:{
        name:'',
        path:'',
        component:'',
        iconcls:''
      }
    }
  },
  computed:{
    ...mapGetters({
    	menuList : 'getMenuList'
    })
  },
  created(){
    this.$store.dispatch('getAllMenu')
  },
  methods: {
    close(){
      this.menu = {}
      this.isShow = false
    },
    addMenu(){
      this.$store.dispatch('addMenu',this.menu).then(res=>{
        if(res>0){
          this.$message({
            showClose: true,
            message: '新增菜单成功',
            type: 'success'
          })
          this.menu = {}
          this.$store.dispatch('getAllMenu')
          this.isShow = false
        }else{
          this.$message({
            showClose: true,
            message: '新增菜单失败',
            type: 'error'
          })
        }
      })
    },
    toEdit(data){
      this.menu = {
        id : data.id,
        name : data.name,
        path : data.path,
        component : data.component,
        url : data.meta.url,
        iconcls : data.meta.inconcls,
        parentid : data.meta.parentid
      }
      this.isShow = true
    },
    edit(){
      this.$store.dispatch('updateMenu',this.menu).then(res=>{
        if(res>0){
          this.$message({
            showClose: true,
            message: '修改菜单成功',
            type: 'success'
          })
          this.menu = {}
          this.$store.dispatch('getAllMenu')
          this.isShow = false
        }else{
          this.$message({
            showClose: true,
            message: '修改菜单失败',
            type: 'error'
          })
        }
      })
    },
    toAppend(data){
      this.menu.parentid = data.id
      this.isShow = true
    },
    remove(data){
      this.$confirm('此操作将永久删除该菜单, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$store.dispatch('deleteMenu',data.id).then(res=>{
          if(res>0){
            this.$message({
              showClose: true,
              message: '删除菜单成功',
              type: 'success'
            })
            this.$store.dispatch('getAllMenu')
          }else{
            this.$message({
              showClose: true,
              message: '删除菜单失败',
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
.menu{
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding-right: 20px;
}
</style>
