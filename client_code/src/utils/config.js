const config = {
  get() {
    return {
      url:
        process.env.VUE_APP_BASE_API_URL + process.env.VUE_APP_BASE_API + '/',
      name: process.env.VUE_APP_BASE_API,
      menuList: [
        {
          name: '在线咨询',
          icon: '${frontMenu.fontClass}',
          child: [
            {
              name: '客服聊天',
              url: 'chat',
            },
          ],
        },
        {
          name: '资讯信息',
          icon: '${frontMenu.fontClass}',
          child: [
            {
              name: '公告信息',
              url: '/index/newsList',
            },
          ],
        },
        {
          name: '客房信息管理',
          icon: '${frontMenu.fontClass}',
          child: [
            {
              name: '客房信息',
              url: '/index/kefangxinxiList',
            },
          ],
        },
      ],
    }
  },
  getProjectName() {
    return {
      projectName: '酒店民宿客房管理系统',
    }
  },
}
export default config
