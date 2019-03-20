<template>
  <div class="index">
    <el-container v-if="isLogin" style="width:100vw;height:100vh;">
      <el-aside width="200px">
          <side-Menu></side-Menu>
        </el-aside>
      <el-container>
        <el-header style="padding:0;"><header-Bar></header-Bar></el-header>
        <el-main class="main">
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import sideMenu from '@/components/SideMenu'
import headerBar from '@/components/HeaderBar'
import { mapGetters } from 'vuex'

export default {
  name: 'Index',
  data () {
    return {
      
    }
  },
  components:{
    sideMenu,
    headerBar
  },
  computed:{
    ...mapGetters({
      isLogin:'getIsLogin'
    })
  }
}
</script>

<style >
/*滚动条*/
::-webkit-scrollbar{width:4px;height:7px;}
::-webkit-scrollbar-track{background-color:#f5f5f5;-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.3);}
::-webkit-scrollbar-thumb{background-color:#555;}

*{
  margin: 0;
  padding: 0;
}
html,body{
  font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
}
a { text-decoration: none; }
a:hover { text-decoration: none; }

.main{
  text-align: center;
  background-color: #FCFCFC;
}
</style>
