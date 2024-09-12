import { createRouter, createWebHashHistory } from 'vue-router'
import index from '../views'
import home from '../views/pages/home.vue'
import login from '../views/pages/login.vue'
import yonghuList from '@/views/pages/yonghu/list'
import yonghuDetail from '@/views/pages/yonghu/formModel'
import yonghuAdd from '@/views/pages/yonghu/formAdd'
import yonghuRegister from '@/views/pages/yonghu/register'
import yonghuCenter from '@/views/pages/yonghu/center'
import newsList from '@/views/pages/news/list'
import kefangleixingList from '@/views/pages/kefangleixing/list'
import kefangleixingDetail from '@/views/pages/kefangleixing/formModel'
import kefangleixingAdd from '@/views/pages/kefangleixing/formAdd'
import kefangxinxiList from '@/views/pages/kefangxinxi/list'
import kefangxinxiDetail from '@/views/pages/kefangxinxi/formModel'
import kefangxinxiAdd from '@/views/pages/kefangxinxi/formAdd'
import storeupList from '@/views/pages/storeup/list'
import kefangyudingList from '@/views/pages/kefangyuding/list'
import kefangyudingDetail from '@/views/pages/kefangyuding/formModel'
import kefangyudingAdd from '@/views/pages/kefangyuding/formAdd'
import ruzhuxinxiList from '@/views/pages/ruzhuxinxi/list'
import ruzhuxinxiDetail from '@/views/pages/ruzhuxinxi/formModel'
import ruzhuxinxiAdd from '@/views/pages/ruzhuxinxi/formAdd'
import tuifangxinxiList from '@/views/pages/tuifangxinxi/list'
import tuifangxinxiDetail from '@/views/pages/tuifangxinxi/formModel'
import tuifangxinxiAdd from '@/views/pages/tuifangxinxi/formAdd'
import tousujianyiList from '@/views/pages/tousujianyi/list'
import tousujianyiDetail from '@/views/pages/tousujianyi/formModel'
import tousujianyiAdd from '@/views/pages/tousujianyi/formAdd'
import yuangongList from '@/views/pages/yuangong/list'
import yuangongDetail from '@/views/pages/yuangong/formModel'
import yuangongAdd from '@/views/pages/yuangong/formAdd'
import kefangqingliList from '@/views/pages/kefangqingli/list'
import kefangqingliDetail from '@/views/pages/kefangqingli/formModel'
import kefangqingliAdd from '@/views/pages/kefangqingli/formAdd'
import ruzhulvtongjiList from '@/views/pages/ruzhulvtongji/list'
import ruzhulvtongjiDetail from '@/views/pages/ruzhulvtongji/formModel'
import ruzhulvtongjiAdd from '@/views/pages/ruzhulvtongji/formAdd'

const routes = [{
		path: '/',
		redirect: '/index/home'
	},
	{
		path: '/index',
		component: index,
		children: [{
			path: 'home',
			component: home
		}
		, {
			path: 'yonghuList',
			component: yonghuList
		}, {
			path: 'yonghuDetail',
			component: yonghuDetail
		}, {
			path: 'yonghuAdd',
			component: yonghuAdd
		}
		, {
			path: 'yonghuCenter',
			component: yonghuCenter
		}
		, {
			path: 'newsList',
			component: newsList
		}
		, {
			path: 'kefangleixingList',
			component: kefangleixingList
		}, {
			path: 'kefangleixingDetail',
			component: kefangleixingDetail
		}, {
			path: 'kefangleixingAdd',
			component: kefangleixingAdd
		}
		, {
			path: 'kefangxinxiList',
			component: kefangxinxiList
		}, {
			path: 'kefangxinxiDetail',
			component: kefangxinxiDetail
		}, {
			path: 'kefangxinxiAdd',
			component: kefangxinxiAdd
		}
		, {
			path: 'storeupList',
			component: storeupList
		}
		, {
			path: 'kefangyudingList',
			component: kefangyudingList
		}, {
			path: 'kefangyudingDetail',
			component: kefangyudingDetail
		}, {
			path: 'kefangyudingAdd',
			component: kefangyudingAdd
		}
		, {
			path: 'ruzhuxinxiList',
			component: ruzhuxinxiList
		}, {
			path: 'ruzhuxinxiDetail',
			component: ruzhuxinxiDetail
		}, {
			path: 'ruzhuxinxiAdd',
			component: ruzhuxinxiAdd
		}
		, {
			path: 'tuifangxinxiList',
			component: tuifangxinxiList
		}, {
			path: 'tuifangxinxiDetail',
			component: tuifangxinxiDetail
		}, {
			path: 'tuifangxinxiAdd',
			component: tuifangxinxiAdd
		}
		, {
			path: 'tousujianyiList',
			component: tousujianyiList
		}, {
			path: 'tousujianyiDetail',
			component: tousujianyiDetail
		}, {
			path: 'tousujianyiAdd',
			component: tousujianyiAdd
		}
		, {
			path: 'yuangongList',
			component: yuangongList
		}, {
			path: 'yuangongDetail',
			component: yuangongDetail
		}, {
			path: 'yuangongAdd',
			component: yuangongAdd
		}
		, {
			path: 'kefangqingliList',
			component: kefangqingliList
		}, {
			path: 'kefangqingliDetail',
			component: kefangqingliDetail
		}, {
			path: 'kefangqingliAdd',
			component: kefangqingliAdd
		}
		, {
			path: 'ruzhulvtongjiList',
			component: ruzhulvtongjiList
		}, {
			path: 'ruzhulvtongjiDetail',
			component: ruzhulvtongjiDetail
		}, {
			path: 'ruzhulvtongjiAdd',
			component: ruzhulvtongjiAdd
		}
		]
	},
	{
		path: '/login',
		component: login
	}
	,{
		path: '/yonghuRegister',
		component: yonghuRegister
	}
]

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes
})

export default router
