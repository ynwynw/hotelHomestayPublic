<template>
  <div class="home_view">
    <div class="projectTitle">欢迎使用 {{ projectName }}</div>
    <div class="count_list">
      <el-collapse-transition v-if="btnAuth('kefangxinxi', '首页总数')">
        <el-card
          v-show="countTypeList.closekefangxinxiCountType"
          class="card_view"
        >
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">客房信息</div>
              <div class="card_head_right">
                <el-icon
                  @click="countTypeClick('hiddenkefangxinxiCountType')"
                  class="showIcons"
                  :class="
                    countTypeList.hiddenkefangxinxiCountType ? 'showIcons1' : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="countTypeClick('closekefangxinxiCountType')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div
              class="count_item"
              v-show="countTypeList.hiddenkefangxinxiCountType"
            >
              <div class="count_title">客房信息总数</div>
              <div class="count_num">{{ kefangxinxiCount }}</div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
      <el-collapse-transition v-if="btnAuth('kefangyuding', '首页总数')">
        <el-card
          v-show="countTypeList.closekefangyudingCountType"
          class="card_view"
        >
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">客房预订</div>
              <div class="card_head_right">
                <el-icon
                  @click="countTypeClick('hiddenkefangyudingCountType')"
                  class="showIcons"
                  :class="
                    countTypeList.hiddenkefangyudingCountType
                      ? 'showIcons1'
                      : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="countTypeClick('closekefangyudingCountType')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div
              class="count_item"
              v-show="countTypeList.hiddenkefangyudingCountType"
            >
              <div class="count_title">客房预订总数</div>
              <div class="count_num">{{ kefangyudingCount }}</div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
      <el-collapse-transition v-if="btnAuth('ruzhuxinxi', '首页总数')">
        <el-card
          v-show="countTypeList.closeruzhuxinxiCountType"
          class="card_view"
        >
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">入住信息</div>
              <div class="card_head_right">
                <el-icon
                  @click="countTypeClick('hiddenruzhuxinxiCountType')"
                  class="showIcons"
                  :class="
                    countTypeList.hiddenruzhuxinxiCountType ? 'showIcons1' : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="countTypeClick('closeruzhuxinxiCountType')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div
              class="count_item"
              v-show="countTypeList.hiddenruzhuxinxiCountType"
            >
              <div class="count_title">入住信息总数</div>
              <div class="count_num">{{ ruzhuxinxiCount }}</div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
      <el-collapse-transition v-if="btnAuth('ruzhulvtongji', '首页总数')">
        <el-card
          v-show="countTypeList.closeruzhulvtongjiCountType"
          class="card_view"
        >
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">入住率统计</div>
              <div class="card_head_right">
                <el-icon
                  @click="countTypeClick('hiddenruzhulvtongjiCountType')"
                  class="showIcons"
                  :class="
                    countTypeList.hiddenruzhulvtongjiCountType
                      ? 'showIcons1'
                      : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="countTypeClick('closeruzhulvtongjiCountType')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div
              class="count_item"
              v-show="countTypeList.hiddenruzhulvtongjiCountType"
            >
              <div class="count_title">入住率统计总数</div>
              <div class="count_num">{{ ruzhulvtongjiCount }}</div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
    </div>
    <div class="card_list">
      <el-collapse-transition v-if="btnAuth('kefangxinxi', '首页统计')">
        <el-card
          v-show="cardTypeList.closekefangxinxiChartType1"
          class="card_view"
        >
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">客房信息</div>
              <div class="card_head_right">
                <el-icon
                  @click="cardTypeClick('hiddenkefangxinxiChartType1')"
                  class="showIcons"
                  :class="
                    cardTypeList.hiddenkefangxinxiChartType1 ? 'showIcons1' : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="cardTypeClick('closekefangxinxiChartType1')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div
              class="card_item"
              v-show="cardTypeList.hiddenkefangxinxiChartType1"
            >
              <div
                id="kefangxinxikefangleixingEchart1"
                style="width: 100%; height: 400px"
              ></div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
      <el-collapse-transition v-if="btnAuth('kefangyuding', '首页统计')">
        <el-card
          v-show="cardTypeList.closekefangyudingChartType1"
          class="card_view"
        >
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">客房预订</div>
              <div class="card_head_right">
                <el-icon
                  @click="cardTypeClick('hiddenkefangyudingChartType1')"
                  class="showIcons"
                  :class="
                    cardTypeList.hiddenkefangyudingChartType1
                      ? 'showIcons1'
                      : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="cardTypeClick('closekefangyudingChartType1')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div
              class="card_item"
              v-show="cardTypeList.hiddenkefangyudingChartType1"
            >
              <div
                id="kefangyudingzongjiageEchart1"
                style="width: 100%; height: 400px"
              ></div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
      <el-collapse-transition v-if="btnAuth('ruzhuxinxi', '首页统计')">
        <el-card
          v-show="cardTypeList.closeruzhuxinxiChartType1"
          class="card_view"
        >
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">入住信息</div>
              <div class="card_head_right">
                <el-icon
                  @click="cardTypeClick('hiddenruzhuxinxiChartType1')"
                  class="showIcons"
                  :class="
                    cardTypeList.hiddenruzhuxinxiChartType1 ? 'showIcons1' : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="cardTypeClick('closeruzhuxinxiChartType1')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div
              class="card_item"
              v-show="cardTypeList.hiddenruzhuxinxiChartType1"
            >
              <div
                id="ruzhuxinxiruzhurenshuEchart1"
                style="width: 100%; height: 400px"
              ></div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
      <el-collapse-transition v-if="btnAuth('ruzhulvtongji', '首页统计')">
        <el-card
          v-show="cardTypeList.closeruzhulvtongjiChartType1"
          class="card_view"
        >
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">入住率统计</div>
              <div class="card_head_right">
                <el-icon
                  @click="cardTypeClick('hiddenruzhulvtongjiChartType1')"
                  class="showIcons"
                  :class="
                    cardTypeList.hiddenruzhulvtongjiChartType1
                      ? 'showIcons1'
                      : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="cardTypeClick('closeruzhulvtongjiChartType1')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div
              class="card_item"
              v-show="cardTypeList.hiddenruzhulvtongjiChartType1"
            >
              <div
                id="ruzhulvtongjiruzhulvEchart1"
                style="width: 100%; height: 400px"
              ></div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
    </div>
  </div>
</template>

<script setup>
import { inject, nextTick, ref, getCurrentInstance } from 'vue'
const context = getCurrentInstance()?.appContext.config.globalProperties
const projectName = context.$project.projectName
const countTypeList = ref({})
const getCountList = () => {
  countTypeList.value.closekefangxinxiCountType = true
  countTypeList.value.hiddenkefangxinxiCountType = true
  if (btnAuth('kefangxinxi', '首页总数')) {
    getkefangxinxiCount()
  }
  countTypeList.value.closekefangyudingCountType = true
  countTypeList.value.hiddenkefangyudingCountType = true
  if (btnAuth('kefangyuding', '首页总数')) {
    getkefangyudingCount()
  }
  countTypeList.value.closeruzhuxinxiCountType = true
  countTypeList.value.hiddenruzhuxinxiCountType = true
  if (btnAuth('ruzhuxinxi', '首页总数')) {
    getruzhuxinxiCount()
  }
  countTypeList.value.closeruzhulvtongjiCountType = true
  countTypeList.value.hiddenruzhulvtongjiCountType = true
  if (btnAuth('ruzhulvtongji', '首页总数')) {
    getruzhulvtongjiCount()
  }
}
const kefangxinxiCount = ref(0)
const getkefangxinxiCount = () => {
  context
    ?.$http({
      url: 'kefangxinxi/count',
      method: 'get',
    })
    .then((res) => {
      kefangxinxiCount.value = res.data.data
    })
}
const kefangyudingCount = ref(0)
const getkefangyudingCount = () => {
  context
    ?.$http({
      url: 'kefangyuding/count',
      method: 'get',
    })
    .then((res) => {
      kefangyudingCount.value = res.data.data
    })
}
const ruzhuxinxiCount = ref(0)
const getruzhuxinxiCount = () => {
  context
    ?.$http({
      url: 'ruzhuxinxi/count',
      method: 'get',
    })
    .then((res) => {
      ruzhuxinxiCount.value = res.data.data
    })
}
const ruzhulvtongjiCount = ref(0)
const getruzhulvtongjiCount = () => {
  context
    ?.$http({
      url: 'ruzhulvtongji/count',
      method: 'get',
    })
    .then((res) => {
      ruzhulvtongjiCount.value = res.data.data
    })
}
const countTypeClick = (e) => {
  countTypeList.value[e] = !countTypeList.value[e]
}
const init = () => {
  getCountList()
  getCardList()
}
//权限验证
const btnAuth = (e, a) => {
  return context?.$toolUtil.isAuth(e, a)
}
let echarts = inject('echarts')
const cardTypeClick = (e) => {
  cardTypeList.value[e] = !cardTypeList.value[e]
  setTimeout(() => {
    getCardList()
  }, 1000)
}
const cardTypeList = ref({
  closekefangxinxiChartType1: true,
  hiddenkefangxinxiChartType1: true,
  closekefangyudingChartType1: true,
  hiddenkefangyudingChartType1: true,
  closeruzhuxinxiChartType1: true,
  hiddenruzhuxinxiChartType1: true,
  closeruzhulvtongjiChartType1: true,
  hiddenruzhulvtongjiChartType1: true,
})
const getCardList = () => {
  if (btnAuth('kefangxinxi', '首页统计')) {
    getkefangxinxiChart1()
  }
  if (btnAuth('kefangyuding', '首页统计')) {
    getkefangyudingChart1()
  }
  if (btnAuth('ruzhuxinxi', '首页统计')) {
    getruzhuxinxiChart1()
  }
  if (btnAuth('ruzhulvtongji', '首页统计')) {
    getruzhulvtongjiChart1()
  }
}
const getkefangxinxiChart1 = () => {
  nextTick(() => {
    var kefangleixingEchart1 = echarts.init(
      document.getElementById('kefangxinxikefangleixingEchart1'),
      'macarons'
    )
    context
      ?.$http({
        url: 'kefangxinxi/group/kefangleixing',
        method: 'get',
      })
      .then((obj) => {
        let res = obj.data.data
        let xAxis = []
        let yAxis = []
        let pArray = []
        for (let i = 0; i < res.length; i++) {
          xAxis.push(res[i].kefangleixing)
          yAxis.push(parseFloat(res[i].total))
          pArray.push({
            value: parseFloat(res[i].total),
            name: res[i].kefangleixing,
          })
        }
        var option = {}
        option = {
          title: {
            text: '客房类型统计',
            left: 'center',
          },
          legend: {
            orient: 'vertical',
            left: 'left',
          },
          tooltip: {
            trigger: 'item',
            formatter: '{b} : {c} ({d}%)',
          },
          series: [
            {
              type: 'pie',
              radius: '55%',
              center: ['50%', '60%'],
              data: pArray,
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)',
                },
              },
            },
          ],
        }
        kefangleixingEchart1.clear()
        // 使用刚指定的配置项和数据显示图表。
        kefangleixingEchart1.setOption(option)
        //根据窗口的大小变动图表
        kefangleixingEchart1.resize()
      })
  })
}
const getkefangyudingChart1 = () => {
  nextTick(() => {
    var zongjiageEchart1 = echarts.init(
      document.getElementById('kefangyudingzongjiageEchart1'),
      'macarons'
    )
    context
      ?.$http({
        url: `kefangyuding/value/fangjianhao/zongjiage`,
        method: 'get',
      })
      .then((obj) => {
        let res = obj.data.data
        let xAxis = []
        let yAxis = []
        let pArray = []
        for (let i = 0; i < res.length; i++) {
          xAxis.push(res[i].fangjianhao)
          yAxis.push(parseFloat(res[i].total))
          pArray.push({
            value: parseFloat(res[i].total),
            name: res[i].fangjianhao,
          })
        }
        var option = {}
        option = {
          title: {
            text: '销售额统计',
            left: 'center',
          },
          tooltip: {
            trigger: 'item',
            formatter: '{b} : {c}',
          },
          xAxis: {
            type: 'category',
            data: xAxis,
            axisLabel: {
              rotate: 40,
            },
          },
          yAxis: {
            type: 'value',
          },
          series: [
            {
              data: yAxis,
              type: 'bar',
            },
          ],
        }
        zongjiageEchart1.clear()
        // 使用刚指定的配置项和数据显示图表。
        zongjiageEchart1.setOption(option)
        //根据窗口的大小变动图表
        zongjiageEchart1.resize()
      })
  })
}
const getruzhuxinxiChart1 = () => {
  nextTick(() => {
    var ruzhurenshuEchart1 = echarts.init(
      document.getElementById('ruzhuxinxiruzhurenshuEchart1'),
      'macarons'
    )
    context
      ?.$http({
        url: `ruzhuxinxi/value/ruzhushijian/ruzhurenshu/日`,
        method: 'get',
      })
      .then((obj) => {
        let res = obj.data.data
        let xAxis = []
        let yAxis = []
        let pArray = []
        for (let i = 0; i < res.length; i++) {
          xAxis.push(res[i].ruzhushijian)
          yAxis.push(parseFloat(res[i].total))
          pArray.push({
            value: parseFloat(res[i].total),
            name: res[i].ruzhushijian,
          })
        }
        var option = {}
        option = {
          title: {
            text: '入住人数',
            left: 'center',
          },
          tooltip: {
            trigger: 'item',
            formatter: '{b} : {c}',
          },
          xAxis: {
            type: 'category',
            boundaryGap: false,
            data: xAxis,
          },
          yAxis: {
            type: 'value',
          },
          series: [
            {
              data: yAxis,
              type: 'line',
            },
          ],
        }
        ruzhurenshuEchart1.clear()
        // 使用刚指定的配置项和数据显示图表。
        ruzhurenshuEchart1.setOption(option)
        //根据窗口的大小变动图表
        ruzhurenshuEchart1.resize()
      })
  })
}
const getruzhulvtongjiChart1 = () => {
  nextTick(() => {
    var ruzhulvEchart1 = echarts.init(
      document.getElementById('ruzhulvtongjiruzhulvEchart1'),
      'macarons'
    )
    context
      ?.$http({
        url: `ruzhulvtongji/value/riqi/ruzhulv/日`,
        method: 'get',
      })
      .then((obj) => {
        let res = obj.data.data
        let xAxis = []
        let yAxis = []
        let pArray = []
        for (let i = 0; i < res.length; i++) {
          xAxis.push(res[i].riqi)
          yAxis.push(parseFloat(res[i].total))
          pArray.push({
            value: parseFloat(res[i].total),
            name: res[i].riqi,
          })
        }
        var option = {}
        option = {
          title: {
            text: '入住率',
            left: 'center',
          },
          tooltip: {
            trigger: 'item',
            formatter: '{b} : {c}',
          },
          xAxis: {
            type: 'category',
            data: xAxis,
            axisLabel: {
              rotate: 40,
            },
          },
          yAxis: {
            type: 'value',
          },
          series: [
            {
              data: yAxis,
              type: 'bar',
            },
          ],
        }
        ruzhulvEchart1.clear()
        // 使用刚指定的配置项和数据显示图表。
        ruzhulvEchart1.setOption(option)
        //根据窗口的大小变动图表
        ruzhulvEchart1.resize()
      })
  })
}
init()
</script>
<style lang="scss">
.projectTitle {
  padding: 0 0 20px;
  font-weight: bold;
  display: flex;
  width: 100%;
  font-size: 30px;
  justify-content: center;
  align-items: center;
  height: 50%;
}

.showIcons {
  transition: transform 0.3s;
  margin-right: 10px;
}

.showIcons1 {
  transform: rotate(-180deg);
}

// 总数盒子
.count_list {
  padding: 0 0 20px;
  display: flex;
  width: 100%;
  justify-content: center;
  align-items: flex-start;
  flex-wrap: wrap;
  // 总数card
  .card_view {
    border: 1px solid #e4e7ed;
    box-shadow: 0px 0px 12px rgba(0, 0, 0, 0.12);
    padding: 0 0 50px;
    margin: 0 10px 10px;
    flex: 1;
    background: rgba(0, 123, 213, 1);
    width: 100%;
    position: relative;
    box-sizing: border-box;
    height: auto;
    // card头部
    .el-card__header {
      border: 1px solid #e4e7ed;
      padding: 10px;
      left: 0;
      bottom: 0;
      width: 100%;
      border-width: 1px 0 0;
      position: absolute;
      // 头部盒子
      .index_card_head {
        display: flex;
        width: 100%;
        justify-content: space-between;
        align-items: center;
        // 标题
        .card_head_title {
          color: #fff;
          font-size: 14px;
        }
        // 按钮盒子
        .card_head_right {
          display: flex;
          align-items: center;
          // 按钮
          .el-icon {
            cursor: pointer;
            color: #fff;
            font-size: 20px;
          }
        }
      }
    }
    // body
    .el-card__body {
      padding: 0;
      // body盒子
      .count_item {
        padding: 30px;
        display: flex;
        align-items: center;
        text-align: center;
        // 总数标题
        .count_title {
          padding: 0 10px 0 0;
          color: #fff;
          font-weight: bold;
          font-size: 20px;
          line-height: 3;
        }
        // 总数数字
        .count_num {
          color: #fff;
          font-weight: bold;
          font-size: 24px;
          line-height: 3;
        }
      }
    }
  }
}
// 首页盒子
.home_view {
  padding: 30px 0;
  background: url(http://localhost:8080/hotelHomestay/file/b89de8d5b5894c14925e21e6f1cbfff7.png)
    no-repeat center top / 100% 100% !important;
  width: 100%;
  min-height: 100vh;
  height: auto;
}
// 统计图盒子
.card_list {
  padding: 0 0 20px;
  display: flex;
  width: 100%;
  flex-wrap: wrap;
  // 统计图card
  .card_view {
    border: 0px solid #e4e7ed;
    box-shadow: none;
    margin: 0 10px 20px;
    background: #fff;
    width: 48%;
    box-sizing: border-box;
    height: auto;
    // 头部
    .el-card__header {
      border: 1px solid #e4e7ed;
      display: none;
      width: 100%;
      border-width: 0 0 1px;
      // 头部盒子
      .index_card_head {
        display: flex;
        width: 100%;
        justify-content: space-between;
        align-items: center;
        // 标题
        .card_head_title {
          color: #f00;
          font-size: 14px;
        }
        // 按钮盒子
        .card_head_right {
          display: flex;
          align-items: center;
          // 按钮
          .el-icon {
            cursor: pointer;
            color: #aaa;
            font-size: 20px;
          }
        }
      }
    }
    // body
    .el-card__body {
      padding: 0;
      width: 100%;
      // body盒子
      .card_item {
        padding: 30px;
        text-align: center;
      }
    }
  }
}
</style>
