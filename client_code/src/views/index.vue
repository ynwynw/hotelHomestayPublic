<template>
  <div class="home">
    <div class="index_top">
      <div class="index_top_title">酒店民宿客房管理系统</div>
      <div class="index_top_right">
        <!-- 去掉tempalte显示天气 -->
        <!-- <template> -->
        <div v-if="date && weather.city" class="weather_time_view">
          <div class="weather">
            <div class="city">{{ weather.city }}</div>
            <div class="wea">{{ weather.wea }} | {{ weather.tem }}℃</div>
          </div>
          <div class="time">
            <div class="date">{{ date }}</div>
            <div class="timer">{{ timer }}</div>
          </div>
        </div>
        <!-- </template> -->
        <el-button
          v-if="!Token"
          type="success"
          icon="User"
          circle
          @click="loginClick"
        />
        <el-button
          v-if="Token"
          type="danger"
          icon="SwitchButton"
          circle
          @click="loginOut"
        />
      </div>
    </div>
    <el-scrollbar class="contain_view">
      <el-scrollbar wrap-class="scrollbar-wrapper" class="menu_scrollbar">
        <el-menu
          :default-openeds="[]"
          :unique-opened="false"
          :default-active="menuIndex"
          class="menu_view"
          mode="horizontal"
          :ellipsis="false"
          @select="menuChange"
        >
          <el-menu-item class="first_item" index="0" @click="menuHandler('/')">
            <template #title>
              <span>首页</span>
            </template>
          </el-menu-item>
          <template v-for="(menu, index) in menuList" :key="menu.menu">
            <el-sub-menu
              v-if="menu.child.length > 1"
              :index="index + 2 + ''"
              class="first_item"
            >
              <template #title>
                <span>{{ menu.name }}</span>
              </template>
              <el-menu-item
                class="menu_item_view"
                v-for="(child, sort) in menu.child"
                :key="sort"
                :index="index + 2 + '-' + sort"
                @click="menuHandler(child.url)"
                >{{ child.name }}
              </el-menu-item>
            </el-sub-menu>
            <el-menu-item
              v-else-if="menu.child[0].url == 'chat'"
              :index="index + 2 + ''"
              class="first_item"
              @click="chatClick"
            >
              <template #title>
                <span>{{ menu.name }}</span>
              </template>
            </el-menu-item>
            <el-menu-item
              v-else
              :index="index + 2 + ''"
              class="first_item"
              @click="menuHandler(menu.child[0].url)"
            >
              <template #title>
                <span>{{ menu.child[0].name }}</span>
              </template>
            </el-menu-item>
          </template>
          <el-menu-item
            v-if="Token"
            class="first_item"
            @click="menuHandler('center')"
          >
            <template #title>
              <span>个人中心</span>
            </template>
          </el-menu-item>
        </el-menu>
      </el-scrollbar>
      <div class="rotation_view">
        <mySwiper
          :type="3"
          :data="rotationList"
          :autoHeight="false"
          :autoplay="true"
          :loop="false"
          :navigation="false"
          :pagination="true"
          :paginationType="2"
          :scrollbar="false"
          :slidesPerView="1"
          :spaceBetween="20"
          :centeredSlides="false"
          :freeMode="false"
          :effectType="1"
          :direction="horizontal"
        >
          <template #default="scope">
            <img
              :style="{ width: '100%', height: '600px' }"
              :src="scope.row.value ? $config.url + scope.row.value : ''"
            />
          </template>
        </mySwiper>
      </div>
      <router-view />
      <el-backtop :right="100" :bottom="100" />
      <div class="bottom_view">
        <img
          class="bottom_img"
          src="http://localhost:8080/hotelHomestay/file/7856ba26477849ea828f481fa2773a95.jpg"
          alt=""
        />
        <div class="bottom_company"></div>
        <div class="bottom_record"></div>
        <div class="bottom_desc"></div>
      </div>
    </el-scrollbar>
    <el-dialog
      v-model="chatVisible"
      :title="'客服'"
      width="70%"
      destroy-on-close
      @close="delTimer"
    >
      <el-form ref="formRef" :model="chatForm" class="chat_form">
        <div class="chat-content">
          <div :key="item.id" v-for="item in chatList">
            <div class="chat_time" v-if="item.showTime">{{ item.addtime }}</div>
            <div v-if="item.ask" class="right-content">
              <el-alert
                v-if="!item.img"
                class="text-content"
                :title="item.ask"
                :closable="false"
                type="success"
              ></el-alert>
              <el-image
                v-if="item.img"
                :src="$config.url + item.img"
                class="chat_img"
                fit="scale-down"
                :preview-src-list="[$config.url + item.img]"
              ></el-image>
            </div>
            <div v-else class="left-content">
              <el-alert
                v-if="!item.img"
                class="text-content"
                :title="item.reply"
                :closable="false"
                type="warning"
              ></el-alert>
              <el-image
                v-if="item.img"
                :src="$config.url + item.img"
                class="chat_img"
                fit="scale-down"
                :preview-src-list="[$config.url + item.img]"
              ></el-image>
            </div>
            <div class="clear-float"></div>
          </div>
        </div>
        <div class="input_box">
          <el-input
            class="input"
            v-model="chatForm.ask"
            placeholder="请回复"
            type="textarea"
          />
          <img
            src="http://localhost:8080/hotelHomestay/file/cd9c0bfe819b47b59b97fd8bfec12f0a.png"
            class="inp_add"
            @click="askShow = !askShow"
            alt=""
          />
        </div>
        <div class="upload_box" v-if="askShow">
          <el-upload
            class="upload-demo"
            :action="action"
            :on-success="uploadSuccess"
            :show-file-list="false"
          >
            <el-button class="upload_btn">上传图片</el-button>
          </el-upload>
        </div>
        <div class="save_box">
          <el-button class="save_btn" @click="askSave">发送</el-button>
        </div>
      </el-form>
    </el-dialog>
  </div>
</template>
<script setup>
import menu from '@/utils/menu'
import axios from 'axios'
import {
  ref,
  onBeforeUnmount,
  getCurrentInstance,
  nextTick,
  computed,
  watch,
} from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
const router = useRouter()
const context = getCurrentInstance()?.appContext.config.globalProperties
const Token = ref('')
const weather = ref({})
const date = ref('')
const timer = ref('')
const interval = ref(null)
const getWeather = () => {
  axios({
    method: 'get',
    url: 'http://v0.yiketianqi.com/free/day?appid=69475998&appsecret=rldbX1Zl&city=北京',
  }).then((res) => {
    weather.value = res.data
  })
}
watch(
  () => router.currentRoute.value,
  () => {
    Token.value = context?.$toolUtil.storageGet('frontToken')
    // 获取默认菜单index
    menuIndex.value = context?.$toolUtil.storageGet('menuIndex')
      ? context?.$toolUtil.storageGet('menuIndex')
      : '0'
  }
)
onBeforeUnmount(() => {
  clearInterval(interval.value)
  delTimer()
})
// 默认菜单index
const menuIndex = ref('0')
const init = () => {
  // 获取默认菜单index
  menuIndex.value = context?.$toolUtil.storageGet('menuIndex')
    ? context?.$toolUtil.storageGet('menuIndex')
    : '0'
  // 获取菜单
  getMenu()
  // 赋值token
  Token.value = context?.$toolUtil.storageGet('frontToken')
  // 时间
  interval.value = setInterval(() => {
    date.value = context?.$toolUtil.getCurDate()
    timer.value = context?.$toolUtil.getCurDateTime().split(' ')[1]
  }, 1000)
  // 天气
  getWeather()
  // 轮播图
  getRotationList()
  if (Token.value) {
    getSession()
  }
}
// 切换菜单保存index
const menuChange = (e) => {
  context?.$toolUtil.storageSet('menuIndex', e)
}
// 轮播图
const rotationList = ref([])
const baseUrl = context?.$config.url
const getRotationList = () => {
  context
    ?.$http({
      url: 'config/list',
      method: 'get',
      params: {
        page: 1,
        limit: 3,
      },
    })
    .then((res) => {
      rotationList.value = res.data.data.list
    })
}
const menuList = ref([])
const role = ref('')
const getMenu = () => {
  let params = {
    page: 1,
    limit: 1,
    sort: 'id',
  }
  context
    ?.$http({
      url: 'menu/list',
      method: 'get',
      params: params,
    })
    .then((res) => {
      context?.$toolUtil.storageSet('menus', res.data.data.list[0].menujson)
    })
  menuList.value = context?.$config.menuList
}
const loginClick = () => {
  context?.$toolUtil.storageSet('toPath', window.history.state.current)
  router.push('/login')
}
const loginOut = () => {
  context?.$toolUtil.message('退出成功', 'success')
  context?.$toolUtil.storageClear()
  router.replace('/index/home')
  context?.$toolUtil.storageSet('menuIndex', '0')
  Token.value = ''
}
//客服聊天
//客服弹窗
const chatClick = () => {
  getChatList()
  chatVisible.value = true
  intervalTimer.value = setInterval(() => {
    getChatList()
  }, 3000)
}

const chatVisible = ref(false)
const chatForm = ref({})
const chatList = ref([])
//客服聊天列表
const getChatList = () => {
  context
    ?.$http({
      url: 'chat/page',
      method: 'get',
      params: {
        limit: 1000,
        sort: 'addtime',
        order: 'asc',
        userid: context?.$toolUtil.storageGet('userid'),
      },
    })
    .then((res) => {
      for (let x in res.data.data.list) {
        if (res.data.data.list[x].ask) {
          if (res.data.data.list[x].ask.split('/').length > 1) {
            if (res.data.data.list[x].ask.split('/')[0] == 'file') {
              res.data.data.list[x].img = res.data.data.list[x].ask
            }
          }
        }
        if (res.data.data.list[x].reply) {
          if (res.data.data.list[x].reply.split('/').length > 1) {
            if (res.data.data.list[x].reply.split('/')[0] == 'file') {
              res.data.data.list[x].img = res.data.data.list[x].reply
            }
          }
        }
        if (x == 0) {
          res.data.data.list[x].showTime = 1
        } else {
          let jian =
            new Date(res.data.data.list[x].addtime).getTime() -
            new Date(res.data.data.list[x - 1].addtime).getTime()
          if (jian > 18000) {
            res.data.data.list[x].showTime = 1
          } else {
            res.data.data.list[x].showTime = 0
          }
        }
      }
      chatList.value = res.data.data.list
      nextTick(() => {
        let div = document.getElementsByClassName('chat-content')[0]
        setTimeout(() => {
          if (div) div.scrollTop = div.scrollHeight
        }, 100)
      })
    })
}
//定时器
const intervalTimer = ref(null)
const delTimer = () => {
  if (intervalTimer.value) {
    clearInterval(intervalTimer.value)
  }
}
//图片上传
const action = ref(`${context?.$config.name}/file/upload`)
const uploadSuccess = (e) => {
  let params = {
    ask: 'file/' + e.file,
    userid: context?.$toolUtil.storageGet('userid'),
  }
  context
    ?.$http({
      url: 'chat/add',
      method: 'post',
      data: params,
    })
    .then((res) => {
      context?.$toolUtil.message('发送成功', 'success', () => {
        getChatList()
        chatForm.value.ask = ''
      })
    })
}
//操作显示
const askShow = ref(false)
//发送客服信息
const askSave = () => {
  let params = JSON.parse(JSON.stringify(chatForm.value))
  params.userid = context?.$toolUtil.storageGet('userid')
  context
    ?.$http({
      url: 'chat/add',
      method: 'post',
      data: params,
    })
    .then((res) => {
      context?.$toolUtil.message('发送成功', 'success')
      chatForm.value.ask = ''
      getChatList()
    })
}
//菜单跳转
const menuHandler = (name) => {
  if (name == 'center') {
    name = `${context?.$toolUtil.storageGet('frontSessionTable')}Center`
  }
  router.push(name)
}
// 获取用户信息
const getSession = () => {
  context
    ?.$http({
      url: `${context?.$toolUtil.storageGet('frontSessionTable')}/session`,
      method: 'get',
    })
    .then((res) => {
      context?.$toolUtil.storageSet('userid', res.data.data.id)
      if (context?.$toolUtil.storageGet('frontSessionTable') == 'yonghu') {
        context?.$toolUtil.storageSet('frontName', res.data.data.yonghuzhanghao)
      }
      if (context?.$toolUtil.storageGet('frontSessionTable') == 'yonghu') {
        context?.$toolUtil.storageSet('headportrait', res.data.data.touxiang)
      }
      if (context?.$toolUtil.storageGet('frontSessionTable') == 'yuangong') {
        context?.$toolUtil.storageSet(
          'frontName',
          res.data.data.yuangonggonghao
        )
      }
      if (context?.$toolUtil.storageGet('frontSessionTable') == 'yuangong') {
        context?.$toolUtil.storageSet('headportrait', res.data.data.touxiang)
      }
    })
}
init()
</script>
<style lang="scss" scoped>
/* 头部 */
.index_top {
  padding: 0px 10%;
  z-index: 1002;
  color: #022c4e;
  background: #fff;
  display: block;
  width: 100%;
  justify-content: space-between;
  position: fixed;
  box-sizing: border-box;
  height: 100px;
  .index_top_title {
    padding: 0px;
    color: #667c5e;
    font-weight: 500;
    display: flex;
    font-size: 22px;
    line-height: 26px;
    top: 10px;
    background: none;
    width: 24%;
    align-items: center;
    position: absolute;
    text-align: left;
    height: 78px;
  }
  .index_top_right {
    margin: 26px 0 0;
    display: flex;
    align-items: flex-start;
    float: right;
    // 天气时间盒子
    .weather_time_view {
      margin: 16px 20px 0;
      //display: none;
      // 天气城市盒子
      .weather {
        border: 0px solid #fff;
        padding: 0 20px;
        flex-direction: row;
        display: flex;
        font-size: 14px;
        border-width: 0 0px 0 0;
        line-height: 1;
        justify-content: center;
        align-items: center;
        // 城市
        .city {
          padding: 0 0 0px;
        }
        // 天气
        .wea {
        }
      }
      // 时间盒子
      .time {
        padding: 0 20px;
        flex-direction: row;
        display: flex;
        font-size: 14px;
        line-height: 1;
        justify-content: center;
        align-items: center;
        // 日期
        .date {
          padding: 0px;
          margin: 0 10px 0 0;
        }
        // 时间
        .timer {
        }
      }
    }
  }
  // 登录按钮
  :deep(.el-button--success) {
    border: 0px solid #ffffff;
    border-radius: 100%;
    padding: 0px;
    margin: 8px 10px 0 0;
    color: #fff;
    background: #667c5e;
    width: 26px;
    font-size: 16px;
    height: 26px;
  }
  :deep(.el-button--success:hover) {
    background: #82987a;
  }
  // 登出按钮
  :deep(.el-button--danger) {
    border: 0px solid #999;
    border-radius: 100%;
    padding: 0px;
    margin: 8px 0 0;
    color: #fff;
    background: #999;
    width: 26px;
    font-size: 16px;
    height: 26px;
  }
  :deep(.el-button--danger:hover) {
    background: #aaa;
  }
}
// 轮播盒子
.rotation_view {
  border: 0px solid #fff;
  border-radius: 0px;
  margin: 100px auto 0;
  background: none;
  display: block;
  width: 100%;
  height: 600px;
}
// 底部
.bottom_view {
  padding: 20px 7% 20px;
  flex-direction: column;
  color: #91bfd6;
  background: #062018;
  display: flex;
  width: 100%;
  font-size: 14px;
  min-height: 100px;
  line-height: 1.5;
  justify-content: center;
  align-items: center;
  text-align: center;
  // 图片
  .bottom_img {
    border-radius: 100%;
    object-fit: cover;
    display: none;
    width: 44px;
    height: 44px;
  }
  // 公司
  .bottom_company {
    margin: 10px 0 0;
    color: inherit;
    font-size: inherit;
    line-height: 1;
  }
  // 备案号
  .bottom_record {
    margin: 10px 0 0;
    color: inherit;
    font-size: inherit;
    line-height: 1;
  }
  // 联系方式
  .bottom_desc {
    margin: 10px 0 0;
    color: inherit;
    font-size: inherit;
    line-height: 1;
  }
}

.contain_view {
  margin: 0;
  background: #fff;
  position: inherit;
  height: 100vh;
}
.el-aside {
  transition: width 0.15s;
  -webkit-transition: width 0.15s;
  -moz-transition: width 0.15s;
  -webkit-transition: width 0.15s;
  -o-transition: width 0.15s;
}
.el-sub-menu__hide-arrow {
  display: block !important;
}
.menu_scrollbar {
  padding: 0;
  margin: 0px auto 0;
  z-index: 1002;
  border-color: #efefef;
  border-radius: 0px;
  top: 24px;
  left: 32%;
  background: none;
  width: auto;
  border-width: 0 0 0px 0;
  position: absolute;
  border-style: solid;
  height: auto;
}
// 总盒子
.menu_view {
  border: 0;
  border-radius: 8px;
  background: none;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  // 一级菜单
  :deep(.first_item) {
    border: 0;
    cursor: pointer;
    padding: 0 8px;
    margin: 0 0px 0 0;
    color: #333;
    white-space: nowrap;
    font-weight: 500;
    font-size: 16px;
    line-height: 50px;
    background: none;
    list-style: none;
    text-align: left;
    height: 50px;

    // 图标

    // 标题
    .el-sub-menu__title,
    span {
      color: inherit !important;
      vertical-align: middle !important;
      font-size: 16px !important;
    }

    //箭头
    .el-sub-menu__icon-arrow {
      margin: -3px 0 0 8px;
      color: inherit;
      vertical-align: middle;
      font-size: 12px;
      position: static;
    }
  }
  // 选中
  :deep(.is-active) {
    color: #667c5e !important;
    background: none !important;
    font-weight: 600 !important;
    border-color: #667c5e !important;
    border-width: 0 0 2px !important;
    border-style: solid !important;
  }

  // 悬浮
  :deep(.first_item:hover) {
    color: #667c5e !important;
    background: none !important;
    font-weight: 600 !important;
    border-color: #667c5e !important;
    border-width: 0 0 2px !important;
    border-style: solid !important;
  }
}
// 聊天总盒子
.chat_form {
  border-radius: 0px;
  padding: 0px;
  margin: 20px auto 0;
  flex-direction: column;
  background: none;
  display: flex;
  width: 80%;
  border-color: #27bacc10 #27bacc20 #27bacc30;
  border-width: 0px;
  border-style: solid;
  // 聊天内容盒子
  .chat-content {
    border: 1px solid #ddd;
    border-radius: 0px;
    padding: 20px 20px 30px;
    margin: 0 0 30px 0;
    background: #fff;
    overflow-y: scroll;
    height: 300px;
    // 聊天时间
    .chat_time {
      padding: 6px 0;
      color: #999;
      width: 100%;
      font-size: 14px;
      text-align: center;
    }
    // 提问盒子
    .left-content {
      padding: 10px;
      margin: 0 0 10px;
      width: auto;
      border-color: #ddd;
      border-width: 0px;
      float: left;
      border-style: solid;
      // 文字样式
      :deep(.el-alert--success) {
        border-radius: 0;
        background-color: #f0f9eb;
        color: #67c23a;
        .el-alert__title {
          font-size: inherit;
        }
      }
      // 聊天图片
      .chat_img {
        margin: 10px;
        max-width: 200px;
        width: auto;
      }
    }
    // 回复盒子
    .right-content {
      padding: 10px;
      margin: 0 0 10px;
      width: auto;
      border-color: #ddd;
      border-width: 0px;
      float: right;
      border-style: solid;
      text-align: right;
      // 文字样式
      :deep(.el-alert--warning) {
        background-color: #fdf6ec;
        border-radius: 0;
        color: #e6a23c;
        display: flex;
        width: 100%;
        justify-content: flex-end;
        flex-wrap: wrap;
        text-align: right;
        .el-alert__title {
          font-size: inherit;
        }
      }
      // 聊天图片
      .chat_img {
        margin: 10px;
        max-width: 200px;
        width: auto;
      }
    }
  }
  // 输入内容盒子
  .input_box {
    display: flex;
    align-items: center;
    // 输入框
    :deep(.el-textarea__inner) {
      border: 1px solid #ddd;
      border-radius: 0;
      padding: 12px;
      color: #333;
      background: #fff;
      width: 100%;
      font-size: 14px;
      min-height: 150px;
    }
    // 操作按钮
    .inp_add {
      cursor: pointer;
      margin: 0 20px 0 20px;
      width: 32px;
    }
  }
  // 操作盒子
  .upload_box {
    margin: 30px 0 0;
    display: flex;
    width: 100%;
    align-items: center;
    :deep(.upload-demo) {
      // 上传按钮
      .upload_btn {
        border: 0px solid #ccc;
        cursor: pointer;
        border-radius: 0px;
        padding: 0 20px;
        margin: 0 10px 0 0;
        color: #333;
        background: #667c5e30;
        width: auto;
        font-size: 14px;
        transition: all 0.3s;
        min-width: 100px;
        height: 36px;
      }
      // 上传按钮-悬浮
      .upload_btn:hover {
      }
    }
  }
  // 发送盒子
  .save_box {
    margin: 30px 0 20px;
    display: flex;
    width: 100%;
    justify-content: center;
    // 发送按钮
    .save_btn {
      border: 0px solid #ccc;
      cursor: pointer;
      padding: 0 40px;
      margin: 0 10px 0 0;
      color: #fff;
      letter-spacing: 2px;
      font-size: 14px;
      transition: all 0.3s;
      border-radius: 0px;
      outline: none;
      background: #667c5e;
      width: auto;
      min-width: 150px;
      height: 40px;
    }
    // 发送按钮-悬浮
    .save_btn:hover {
    }
  }
  .clear-float {
    clear: both;
  }
}
</style>
<style lang="scss">
// 二级
.menu_popper {
  border: none !important;
  background: none !important;

  // 二级总盒子
  .el-menu--popup {
    border: 0px solid #eee !important;
    border-radius: 0 !important;
    box-shadow: 0px 0px 0px 0px #999 !important;
    padding: 0 !important;
    margin: 0 !important;
    left: -50px !important;
    background: #296aa290 !important;
    width: auto !important;
    position: absolute !important;
  }

  // 二级菜单
  .menu_item_view {
    border-top: 1px solid #fff !important;
    padding: 0 12px !important;
    color: #fff !important;
    background: none !important;
    line-height: 48px !important;
    text-align: center !important;
    height: 48px !important;
  }

  // 选中
  .is-active {
    padding: 0 12px !important;
    color: #fff !important;
    background: #337ab7 !important;
    line-height: 48px !important;
    height: 48px !important;
  }

  // 悬浮
  .menu_item_view:hover {
    padding: 0 12px !important;
    color: #fff !important;
    background: #337ab7 !important;
    line-height: 48px !important;
    height: 48px !important;
  }
}
</style>
