import Vue from 'vue'
import Router from 'vue-router'
import store from '../store'

import Index from '@/components/Index'
import Login from '@/components/Login'
import Error from '@/components/Error'
import UserInfo from '@/components/UserInfo'

Vue.use(Router)

let routers = {
  routes: [
    {
      path: '/',
      component: Index,
      children: []
    },{
      path:'/login',
      component: Login
    }
  ]
}

const router = new Router(routers)

router.beforeEach((to,from,next)=>{
  const token = localStorage.getItem('token')
  const username = localStorage.getItem('username')
  if((!username||!token)&&to.path!='/login'){
    next({path:'/login',replace: true})
  }
  if(token){
    if(to.path=='/login'){
      next({path:'/',replace:true})
    }
    store.dispatch('setIsLogin',true)
    if(store.state.userInfo.username==null){
      store.dispatch('getUserInfo',username)
    }
    if(store.state.userMenu.length==0){
      store.dispatch('getUserMenu',username).then((res)=>{
        let menus = [{
          path: '/',
          component: Index,
          children: []
        },{
          path:'*',
          component:Error,
        }]
/*        for(let index=0;index<res.length;index++){
          if(res[index].component==null){
            res.splice(index,1)
          }
        }*/
        for(let index=0;index<res.length;index++){
          let component = res[index].component
          res[index].component=(resolve)=>require(['@/components/'+component],resolve)
        }
        menus[0].children=res
        menus[0].children.push({
          path:'/userinfo',
          component:UserInfo
        },{
          path:'*',
          component:Error,
        })
        router.addRoutes(menus)
      })
    }
  }

  next()
})

export default router
