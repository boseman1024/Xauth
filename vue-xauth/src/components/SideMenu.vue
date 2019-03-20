<template>
  <div class="sideMenu">
  	<el-menu unique-opened :default-active="$route.path" router :default-openeds="$route.meta.parentid">
      <div class="logoBox"><span class="logo">Kuma2X</span></div>
      <div v-for="menu in userMenu" :key="menu.id">
        <el-menu-item :index="menu.path" v-if="menu.children.length==0&&menu.meta.url==1">
          <i :class="menu.meta.iconcls"></i>
          <span slot="title">{{menu.name}}</span>
        </el-menu-item>
        <el-submenu :index="menu.id.toString()" v-else-if="menu.children.length>0">
          <template slot="title">
            <i :class="menu.meta.iconcls"></i>
            <span>{{menu.name}}</span>
          </template>
          <el-menu-item-group>
            <el-menu-item :index="subMenu.path" v-for="subMenu in menu.children" :key="subMenu.id">{{subMenu.name}}</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </div>
    </el-menu>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'sideMenu',
  data () {
    return {
      
    }
  },
  computed:{
    ...mapGetters({
      userMenu:'getUserMenu'
    })
  }
}
</script>

<style scoped>
.sideMenu{
  height:100vh;
}
.el-menu {
    /*border-right: 0;*/
    height:100%;
    border-right:0;
}
.logoBox{ 
  width: 200px;
  height: 60px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.logo{
  font-family:'SnellRoundhand';
  font-size: 1.5rem;
}
.logoBox:hover{
  transition: all 500ms ease;
  cursor: pointer;
  background: #000;
  color: #fff;
}
</style>
