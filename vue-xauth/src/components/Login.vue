<template>
  <div class="login">
  	<h1>登录</h1>
  	<el-form label-position="right" label-width="40px" :model="user">
  		<el-form-item label="账号">
    		<el-input v-model="user.username"></el-input>
  		</el-form-item>
  		<el-form-item label="密码">
    		<el-input v-model="user.password" type="password"></el-input>
  		</el-form-item>
  		<el-form-item size="large">
    		<el-button type="primary" @click="login">登录</el-button>
    		<el-button>注册</el-button>
  		</el-form-item>
	</el-form>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Login',
  data () {
    return {
    	user:{
    		username:'',
    		password:''
    	}
    }
  },
  computed:{
    ...mapGetters({
      
    })
  },
  created() {

  },
  methods:{
  	login(){
  		this.$store.dispatch('login',this.user).then(res=>{
  			if(res){
          this.$message({
            showClose: true,
            message: '登录成功',
            type: 'success'
          })
  				this.$router.replace('/home')
  			}
  		}).catch(error=>{
        if(error.response.status==403){
          this.$message({
            showClose: true,
            message: '登录失败',
            type: 'error'
          })
        }
      })
  	}
  }
}
</script>

<style scoped>
.login{
	margin: 0 auto;
}
</style>
