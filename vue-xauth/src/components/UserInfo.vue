<template>
  <div class="userinfo">
    <el-card :body-style="{ padding: '0px'}">

      <el-row :gutter="20">
        <el-col :span="7"><img src="../../static/pic/icon.jpg" class="avatar"></el-col>
        <el-col :span="17">
          <div class="userinfo-name">{{userInfo.name}}</div>
          <el-row :gutter="20">
            <el-col :span="24" class="userinfo-username">@{{userInfo.username}}</el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8" :offset="2">联系方式：</el-col>
            <el-col :span="8" :offset="2">{{userInfo.phone}}</el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8" :offset="2">邮箱：</el-col>
            <el-col :span="8" :offset="2">{{userInfo.email}}</el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8" :offset="2">状态：</el-col>
            <el-col :span="8" :offset="2">{{userInfo.enabled?'正常':'异常'}}</el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8" :offset="2">当前角色：</el-col>
            <el-col :span="8" :offset="2"><el-tag class="userinfo-role" color="#000" v-for="role in userInfo.role" :key="role.id">{{role.namezh}}</el-tag></el-col>
          </el-row>
        </el-col>
      </el-row>

    </el-card>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'userinfo',
  data () {
    return {
      
    }
  },
  computed:{
    ...mapGetters({
      userInfo:'getUserInfo'
    })
  }
}
</script>

<style scoped>
.el-row{
    margin-bottom: 20px;
}
.avatar{
  float: left;
  border-radius: 50%;
  width: 100%;
  margin: 10px;
}
.userinfo-name{
  font-size: 3rem;
}
.userinfo-username{
  color: #AAAAAA;
}
.userinfo-role{
  color: #fff;
  margin: 0 5px;
}
</style>
