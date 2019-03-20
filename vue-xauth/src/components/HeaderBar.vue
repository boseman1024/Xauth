<template>
  <div class="headerBar">
    
    <div class="userInfo" v-if="isLogin">
      <el-popover ref="info" placement="bottom" width="160" trigger="hover">
        <el-tag class="userinfo-role" color="#000" v-for="role in userInfo.role" :key="role.id">{{role.namezh}}</el-tag>
      </el-popover>
      <img v-popover:info class="icon" src="../../static/pic/icon.jpg" @click="toUserInfo">
      <div>{{userInfo.name}}</div>
      <el-button type="text" icon="el-icon-close" @click="logout"></el-button>
    </div>
    
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'headerBar',
  data () {
    return {

    }
  },
  computed:{
    ...mapGetters({
      userInfo:'getUserInfo',
      isLogin:'getIsLogin'
    })
  },
  methods:{
    toUserInfo(){
      this.$router.push('/userinfo')
    },
    logout(){
      this.$store.dispatch('logout').then((res)=>{
        location.reload()
      })
    }
  }
}
</script>

<style scoped>
.headerBar{
  height: 100%;
  display: flex;
  flex-direction:row;
  align-items:center;
  justify-content:space-between;
}
.icon:hover,.username:hover{
  cursor: pointer;
}
.userInfo{
  display: flex;
  margin-right: 70px; 
  align-items:center;
}
.icon{
  margin: 15px;
  height:2.5rem;
  width: 2.5rem;
  border-radius:50%;
  border:2px solid;
  
}
.item {
  cursor: pointer;
  margin-right: 40px;
}
.el-button{
  font-size: 20px;
  padding: 0;
}
.userinfo-role{
  color: #fff;
  margin: 0 5px;
}
</style>
