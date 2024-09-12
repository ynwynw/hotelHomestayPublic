	import {
		createRouter,
		createWebHashHistory
	} from 'vue-router'
	import news from '@/views/news/list'
	import kefangyuding from '@/views/kefangyuding/list'
	import kefangqingli from '@/views/kefangqingli/list'
	import yuangong from '@/views/yuangong/list'
	import ruzhulvtongji from '@/views/ruzhulvtongji/list'
	import kefangxinxi from '@/views/kefangxinxi/list'
	import kefangleixing from '@/views/kefangleixing/list'
	import tuifangxinxi from '@/views/tuifangxinxi/list'
	import ruzhuxinxi from '@/views/ruzhuxinxi/list'
	import tousujianyi from '@/views/tousujianyi/list'
	import discusskefangxinxi from '@/views/discusskefangxinxi/list'
	import yonghu from '@/views/yonghu/list'
	import chat from '@/views/chat/list'
	import config from '@/views/config/list'
	import yuangongCenter from '@/views/yuangong/center'

export const routes = [{
		path: '/login',
		name: 'login',
		component: () => import('../views/login.vue')
	},{
		path: '/',
		name: '首页',
		component: () => import('../views/index'),
		children: [{
			path: '/',
			name: '首页',
			component: () => import('../views/HomeView.vue'),
			meta: {
				affix: true
			}
		}, {
			path: '/updatepassword',
			name: '修改密码',
			component: () => import('../views/updatepassword.vue')
		}
		
		,{
			path: '/yuangongCenter',
			name: '员工个人中心',
			component: yuangongCenter
		}
		,{
			path: '/news',
			name: '公告信息',
			component: news
		}
		,{
			path: '/kefangyuding',
			name: '客房预订',
			component: kefangyuding
		}
		,{
			path: '/kefangqingli',
			name: '客房清理',
			component: kefangqingli
		}
		,{
			path: '/yuangong',
			name: '员工',
			component: yuangong
		}
		,{
			path: '/ruzhulvtongji',
			name: '入住率统计',
			component: ruzhulvtongji
		}
		,{
			path: '/kefangxinxi',
			name: '客房信息',
			component: kefangxinxi
		}
		,{
			path: '/kefangleixing',
			name: '客房类型',
			component: kefangleixing
		}
		,{
			path: '/tuifangxinxi',
			name: '退房信息',
			component: tuifangxinxi
		}
		,{
			path: '/ruzhuxinxi',
			name: '入住信息',
			component: ruzhuxinxi
		}
		,{
			path: '/tousujianyi',
			name: '投诉建议',
			component: tousujianyi
		}
		,{
			path: '/discusskefangxinxi',
			name: '客房信息评论',
			component: discusskefangxinxi
		}
		,{
			path: '/yonghu',
			name: '用户',
			component: yonghu
		}
		,{
			path: '/chat',
			name: '客服聊天',
			component: chat
		}
		,{
			path: '/config',
			name: '轮播图',
			component: config
		}
		]
	},
]

const router = createRouter({
	history: createWebHashHistory(process.env.BASE_URL),
	routes
})

export default router
