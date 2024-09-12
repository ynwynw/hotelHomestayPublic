<template>
	<div class="app-contain" :style='{"minHeight":"100vh","padding":"0 0 40px","margin":"0 auto","overflow":"hidden","borderRadius":"4px","background":"#fff","width":"80%","position":"relative"}'>
		<div class="bread_view">
			<el-breadcrumb separator=">" class="breadcrumb">
				<el-breadcrumb-item class="first_breadcrumb" :to="{ path: '/' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item class="second_breadcrumb" v-for="(item,index) in breadList" :key="index">{{item.name}}</el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div class="back_view" v-if="centerType">
			<el-button class="back_btn" @click="backClick" type="primary">返回</el-button>
		</div>
		<el-form :inline="true" :model="searchQuery" class="list_search">
			<div class="search_view">
				<div class="search_label">
					用户账号：
				</div>
				<div class="search_box">
					<el-input class="search_inp" v-model="searchQuery.yonghuzhanghao" placeholder="用户账号"
						clearable>
					</el-input>
				</div>
			</div>
			<div class="search_btn_view">
				<el-button class="search_btn" type="primary" @click="searchClick">搜索</el-button>
				<el-button class="add_btn" type="success" v-if="btnAuth('yonghu','新增')" @click="addClick">新增</el-button>
			</div>
		</el-form>
		<div class="list_bottom">
			<div class="data_box">
				<div class="data_view">
					<mySwiper :data="list" :type="3"
						:loop="false"
						:navigation="true"
						:pagination="false"
						:paginationType="1"
						:scrollbar="false"
						:slidesPerView="4"
						:spaceBetween="20"
						:autoHeight="false"
						:centeredSlides="false"
						:freeMode="false"
						:effectType="0"
						:direction="horizontal"
						:autoplay="true"
						:slidesPerColumn="2">
						<template #default="scope">
							<div class="data_item animation_box" @click.stop="detailClick(scope.row.id)" >
								<div class="data_img_box" v-if="scope.row.touxiang && scope.row.touxiang.substr(0,4)=='http'" @click.stop="preViewClick(scope.row.touxiang)">
									<el-image class="data_img" :src="scope.row.touxiang" fit="cover"></el-image>
								</div>
								<div class="data_img_box" v-else @click.stop="preViewClick($config.url+scope.row.touxiang.split(',')[0])">
									<el-image class="data_img" :src="scope.row.touxiang?$config.url + scope.row.touxiang.split(',')[0]:''"
										fit="cover"></el-image>
								</div>
								<div class="data_content">
									<div class="data_price" v-if="scope.row.price">￥{{scope.row.price}}</div>
								</div>
							</div>
						</template>
					</mySwiper>
				</div>
				<el-pagination
					background 
					:layout="layouts.join(',')"
					:total="total" 
					:page-size="listQuery.limit"
					prev-text="上一页"
					next-text="下一页"
					:hide-on-single-page="false"
					:style='{"border":"0px solid #eee","padding":"4px 0","margin":"10px 0 20px","whiteSpace":"nowrap","color":"#333","textAlign":"center","flexWrap":"wrap","background":"none","display":"flex","width":"100%","fontWeight":"500","justifyContent":"center"}'
					@size-change="sizeChange"
					@current-change="currentChange" 
					@prev-click="prevClick"
					@next-click="nextClick"  />
			</div>
		</div>
		<el-dialog v-model="preViewVisible" :title="'查看大图'" width="60%" destroy-on-close>
			<img :src="preViewUrl" style="width: 100%;" alt="">
		</el-dialog>
	</div>
</template>

<script setup>
	import {
		ref,
		getCurrentInstance,
		nextTick,
	} from 'vue';
	import {
		useRoute,
		useRouter
	} from 'vue-router';
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const router = useRouter()
	const route = useRoute()
	//基础信息
	const tableName = 'yonghu'
	const formName = '用户'
	//基础信息
	const breadList = ref([{
		name: formName
	}])
	const list = ref([])
	const listQuery = ref({
		page: 1,
		limit: Number(8)
	})
	const total = ref(0)
	const listLoading = ref(false)
	//权限验证
	const btnAuth = (e,a)=>{
		if(centerType.value){
			return context?.$toolUtil.isBackAuth(e,a)
		}else{
			return context?.$toolUtil.isAuth(e,a)
		}
	}
	const addClick = () => {
		router.push('/index/yonghuAdd')
	}
	//判断是否从个人中心跳转
	const centerType = ref(false)
	//返回
	const backClick = () => {
		router.push(`/index/${context?.$toolUtil.storageGet('frontSessionTable')}Center`)
	}
	const init = () => {
		if(route.query.centerType){
			centerType.value = true
		}
		getList()
	}
	//搜索
	const searchQuery = ref({})
	//下拉列表
	const searchClick = () => {
		listQuery.value.page = 1
		getList()
	}
	//分页
	const layouts = ref(["prev","pager","next","total","jumper"])
	const sizeChange = (size) => {
		listQuery.value.limit = size
		getList()
	}
	const currentChange = (page) => {
		listQuery.value.page = page
		getList()
	}
	const prevClick = () => {
		listQuery.value.page = listQuery.value.page - 1
		getList()
	}
	const nextClick = () => {
		listQuery.value.page = listQuery.value.page + 1
		getList()
	}
	//分页
	//列表
	const getList = () => {
		listLoading.value = true
		let params = JSON.parse(JSON.stringify(listQuery.value))
		if(searchQuery.value.yonghuzhanghao&&searchQuery.value.yonghuzhanghao!=''){
			params.yonghuzhanghao = '%' + searchQuery.value.yonghuzhanghao + '%'
		}
		context?.$http({
			url: `${tableName}/${centerType.value?'page':'list'}`,
			method: 'get',
			params: params
		}).then(res => {
			listLoading.value = false
			list.value = res.data.data.list
			total.value = Number(res.data.data.total)
		})
	}
	const detailClick = (id) => {
		router.push('yonghuDetail?id=' + id + (centerType.value?'&&centerType=1':''))
	}
	//下载文件
	const download = (file) =>{
		if(!file){
			context?.$toolUtil.message('文件不存在','error')
		}
		const a = document.createElement('a');
		a.style.display = 'none';
		a.setAttribute('target', '_blank');
		file && a.setAttribute('download', file);
		a.href = context?.$config.url + file;
		document.body.appendChild(a);
		a.click();
		document.body.removeChild(a);
	}
	// 查看大图
	const preViewUrl = ref('')
	const preViewVisible = ref(false)
	const preViewClick = (url) =>{
		preViewUrl.value = url
		preViewVisible.value = true
	}
	init()
</script>
<style lang="scss" scoped>
	// 返回盒子
	.back_view {
		border-radius: 0px;
		padding: 0 20px;
		margin: 0 auto 20px;
		background: none;
		display: block;
		width: 100%;
		text-align: right;
		// 返回按钮
		.back_btn {
			border: 1px solid #ddd;
			cursor: pointer;
			border-radius: 0px;
			padding: 0 30px;
			outline: none;
			color: #666;
			background: #f9f9f9;
			width: auto;
			font-size: 14px;
			height: 32px;
		}
		// 返回按钮-悬浮
		.back_btn:hover {
		}
	}
	.bread_view {
		border-radius: 0px;
		padding: 12px 20px;
		margin: 0px auto;
		background: none;
		width: 100%;
		border-color: #eee;
		border-width: 0 0 0px;
		position: relative;
		border-style: solid;
		:deep(.breadcrumb) {
			font-size: 14px;
			line-height: 1;
			.el-breadcrumb__separator {
				margin: 0 9px;
				color: #999;
				font-weight: 500;
			}
			.first_breadcrumb {
				.el-breadcrumb__inner {
					color: #333;
					display: inline-block;
				}
			}
			.second_breadcrumb {
				.el-breadcrumb__inner {
					color: #999;
					display: inline-block;
				}
			}
		}
	}
	// 分类盒子
	.category_view {
		border: 0px solid #eee;
		padding: 10px 10px 0;
		margin: 20px auto;
		background: none;
		display: block;
		width: calc(100% - 0px);
		flex-wrap: wrap;
		text-align: center;
		// 分类item
		.category {
			cursor: pointer;
			padding: 10px 20px;
			margin: 0px 20px 10px 0;
			color: #333;
			display: inline-block;
			font-size: 15px;
			border-color: #c9c9c9;
			box-sizing: border-box;
			transition: all 0.3s;
			border-radius: 0px;
			background: #667C5E30;
			width: auto;
			border-width: 0px;
			border-style: solid;
			text-align: center;
			min-width: 80px;
		}
		// item-悬浮
		.category:hover {
			color: #fff;
			background: #667C5E;
			border-color: #113961;
			border-width: 0px;
			opacity: 1;
			border-style: solid;
		}
		// item-选中
		.categoryActive {
			color: #fff;
			background: #667C5E;
			font-size: 14px;
			border-color: #113961;
			border-width: 0px;
			opacity: 1;
			border-style: solid;
		}
	}

	//搜索
	.list_search {
		border: 0px solid #ddd;
		padding: 20px 0 0 20px;
		margin: 0 auto;
		background: none;
		display: flex;
		width: calc(100% - 0px);
		justify-content: center;
		align-items: center;
		.search_view {
			margin: 0 10px 0 0;
			display: flex;
			align-items: center;
			.search_label {
				margin: 0 10px 0 0;
				color: #333;
				font-weight: 500;
				display: inline-block;
				font-size: 14px;
				line-height: 40px;
				height: 40px;
			}
			.search_box {
				display: flex;
				width: calc(100% - 100px);
				// 输入框
				:deep(.search_inp) {
					padding: 0 10px;
					border-color: #eee;
					outline-offset: 0px;
					line-height: 36px;
					box-sizing: border-box;
					border-radius: 0px;
					box-shadow: 0 0 0px rgba(85, 255, 255, 0.5);
					outline: 0px solid #efefef;
					background: #667C5E10;
					width: 100%;
					border-width: 1px;
					border-style: solid;
					min-width: 200px;
					height: 36px;
					//去掉默认样式
					.el-input__wrapper{
						border: none;
						box-shadow: none;
						background: none;
						border-radius: 0;
						height: 100%;
						padding: 0;
					}
					.is-focus {
						box-shadow: none !important;
					}
				}
			}
		}
		.search_btn_view {
			margin: 20px 0;
			display: flex;
			// 搜索按钮
			.search_btn {
				border: 0;
				cursor: pointer;
				border-radius: 0px;
				padding: 0 20px;
				outline: none;
				margin: 0 10px 0 0;
				color: #fff;
				background: #667C5E;
				width: auto;
				font-size: 14px;
				transition: all 0.3s;
				height: 36px;
			}
			// 搜索按钮-悬浮
			.search_btn:hover {
			}
			// 新增按钮
			.add_btn {
				border: 0px solid #333;
				cursor: pointer;
				border-radius: 0px;
				padding: 0 20px;
				margin: 0 10px 0 0;
				outline: none;
				color: #333;
				background: #667C5E30;
				width: auto;
				font-size: 14px;
				transition: all 0.3s;
				height: 36px;
			}
			// 新增按钮-悬浮
			.add_btn:hover {
			}
		}
	}

	// 数据盒子
	.list_bottom {
		border-radius: 0;
		padding: 20px 0 0;
		margin: 20px 0 0;
		background: none;
		display: flex;
		width: calc(100% - 0px);
		align-items: flex-start;
		flex-wrap: wrap;
		//列表
		.data_box {
			border: 0px solid #ddd;
			padding: 0;
			background: none;
			width: 100%;
			.data_view {
				border: 0px solid #eee;
				padding: 0 0 0 20px;
				overflow: hidden;
				background: none;
				display: flex;
				width: 100%;
				flex-wrap: wrap;
				.data_item {
					border: 1px solid #eee;
					cursor: pointer;
					padding: 10px;
					box-shadow: 0px 0px 0px -16px #eee;
					margin: 0 10px 20px;
					background: #fff;
					width: 100%;
					// 图片盒子
					.data_img_box {
						width: 100%;
						height: 240px;
						// 图片
						.data_img {
							object-fit: cover;
							width: 100%;
							height: 100%;
						}
					}
					// 内容盒子
					.data_content {
						padding: 0px;
						color: inherit;
						background: none;
						display: flex;
						justify-content: space-between;
						flex-wrap: wrap;
						// 价格
						.data_price {
							padding: 10px 0px 0 0;
							color: #d46f6f;
							display: inline-block;
							width: auto;
							font-size: 16px;
							text-align: right;
							order: 2;
						}
						// 标题
						.data_title {
							margin: 0;
							color: #333;
							background: #667C5E20;
							font-weight: 500;
							width: 100%;
							font-size: 14px;
							line-height: 40px;
							text-align: center;
						}
						// 底部栏
						.data_operate_box {
							padding: 10px 0 0 0px;
							color: #999;
							display: flex;
							width: 75%;
							align-items: center;
							box-sizing: border-box;
							// 点赞数
							.data_like {
								margin: 0 10px 0 0;
								color: inherit;
								display: flex;
								font-size: 16px;
								align-items: center;
								.like_icon {
									margin: 0 4px 0 0;
									color: #0266b5;
								}
								.like_num {
									color: inherit;
								}
							}
							// 收藏数
							.data_collect {
								margin: 0 10px 0 0;
								color: inherit;
								display: flex;
								font-size: 16px;
								align-items: center;
								.el-icon {
									margin: 0 4px 0 0;
									color: #ee7810;
								}
								.collect_num {
									color: inherit;
								}
							}
							// 点击数
							.data_clickNum {
								margin: 0 10px 0 0;
								color: inherit;
								display: flex;
								font-size: 16px;
								align-items: center;
								.el-icon {
									margin: 0 4px 0 0;
									color: #4aac26;
								}
								.clickNum_num {
									color: inherit;
								}
							}
						}
					}
				}
			}
		}
	}

	// animation
	.animation_box {
		transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
		z-index: initial;
	}
	.animation_box:hover {
		transform: rotate(0deg) scale(0.99) skew(0deg, 0deg) translate3d(0px, 1px, 0px);
		-webkit-perspective: 1000px;
		perspective: 1000px;
		transition: 0.3s;
	}
	.animation_box img {
		transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
		z-index: initial;
	}
	.animation_box img:hover {
		transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
		-webkit-perspective: 1000px;
		perspective: 1000px;
		transition: 0.3s;
	}
	// animation

	// 分页器
	.el-pagination {
		// 总页码
		:deep(.el-pagination__total) {
			margin: 0 10px 0;
			color: #666;
			font-weight: 400;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 28px;
			height: 28px;
		}
		// 上一页
		:deep(.btn-prev) {
			border: 1px solid #ddd;
			border-radius: 2px;
			padding: 0 4px;
			margin: 0 2px;
			color: #666;
			background: #fff;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 28px;
			min-width: 28px;
			height: 28px;
		}
		// 下一页
		:deep(.btn-next) {
			border: 1px solid #ddd;
			border-radius: 2px;
			padding: 0 4px;
			margin: 0 2px;
			color: #666;
			background: #fff;
			display: inline-block;
			vertical-align: top;
			font-size: 14px;
			line-height: 28px;
			min-width: 28px;
			height: 28px;
		}
		// 上一页禁用
		:deep(.btn-prev:disabled) {
			border: 1px solid #ddd;
			cursor: not-allowed;
			padding: 0 4px;
			margin: 0 2px;
			color: #C0C4CC;
			display: inline-block;
			vertical-align: top;
			font-size: 14px;
			line-height: 28px;
			border-radius: 2px;
			background: none;
			min-width: 28px;
			height: 28px;
		}
		// 下一页禁用
		:deep(.btn-next:disabled) {
			border: 1px solid #ddd;
			cursor: not-allowed;
			padding: 0 4px;
			margin: 0 2px;
			color: #C0C4CC;
			display: inline-block;
			vertical-align: top;
			font-size: 14px;
			line-height: 28px;
			border-radius: 2px;
			background: none;
			min-width: 28px;
			height: 28px;
		}
		// 页码
		:deep(.el-pager) {
			padding: 0;
			margin: 0;
			display: inline-block;
			vertical-align: top;
			// 数字
			.number {
				cursor: pointer;
				padding: 0 4px;
				margin: 0 5px;
				color: #666;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				border-radius: 2px;
				background: #f4f4f5;
				text-align: center;
				min-width: 28px;
				height: 28px;
			}
			// 数字悬浮
			.number:hover {
				cursor: pointer;
				padding: 0 4px;
				margin: 0 5px;
				color: #fff;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				border-radius: 2px;
				background: #667C5E;
				text-align: center;
				min-width: 28px;
				height: 28px;
			}
			// 选中
			.number.is-active {
				cursor: default;
				padding: 0 4px;
				margin: 0 5px;
				color: #fff;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				border-radius: 2px;
				background: #667C5E;
				text-align: center;
				min-width: 28px;
				height: 28px;
			}
		}
		// sizes
		:deep(.el-pagination__sizes) {
			box-shadow: none;
			margin: 0 0 0 5px;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 28px;
			height: 28px;
			.el-select {
				border: 0px solid #DCDFE6;
				cursor: pointer;
				padding: 0;
				color: #606266;
				display: inline-block;
				font-size: 13px;
				line-height: 28px;
				border-radius: 2px;
				outline: 0;
				background: #f4f4f5;
				width: 100%;
				text-align: center;
				height: 28px;
				//去掉默认样式
				.select-trigger{
					height: 100%;
					.el-input{
						height: 100%;
						.el-input__wrapper{
							border: none;
							box-shadow: none;
							background: none;
							border-radius: 0;
							height: 100%;
							padding: 0;
						}
						.is-focus {
							box-shadow: none !important;
						}
					}
				}
			}
		}
		// 跳页
		:deep(.el-pagination__jump) {
			margin: 0 0 0 24px;
			color: #606266;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 28px;
			height: 28px;
			// 输入框
			.el-input {
				border: 1px solid #ddd;
				cursor: pointer;
				padding: 0 3px;
				margin: 0 6px;
				color: #606266;
				display: inline-block;
				font-size: 14px;
				line-height: 28px;
				border-radius: 3px;
				outline: 0;
				background: #fff;
				width: 38px;
				text-align: center;
				height: 28px;
				//去掉默认样式
				.el-input__wrapper{
					border: none;
					box-shadow: none;
					background: none;
					border-radius: 0;
					height: 100%;
					padding: 0;
				}
				.is-focus {
					box-shadow: none !important;
				}
			}
		}
	}
	
	// 热门信息盒子
	.hot_view {
		border: 0px solid #ddd;
		padding: 0;
		margin: 0px auto;
		background: none;
		width: 100%;
		// 标题
		.hot_title {
			padding: 4px 30px 8px;
			margin: 20px auto;
			color: #fff;
			background: #739683;
			font-weight: 500;
			width: calc(100% - 0px);
			font-size: 22px;
			border-color: #113961;
			border-width: 0px;
			border-style: solid;
			text-align: center;
		}

		.hot_list {
			padding: 0 0 0 2%;
			margin: 30px 0 0;
			display: flex;
			width: 100%;
			flex-wrap: wrap;
			// item
			.hot {
				cursor: pointer;
				border: 0px solid #eee;
				padding: 10px 0px 0;
				margin: 0 2% 20px 0;
				background: #fff;
				width: 23%;
				box-sizing: border-box;
				//图片盒子
				.hot_img_view {
					margin: 0 0 2px;
					width: 100%;
					font-size: 0;
					height: 180px;
					// 图片
					.hot_img {
						border: 0px solid #2da065;
						padding: 0px;
						object-fit: cover;
						width: 100%;
						height: 100%;
					}
				}
				// 内容盒子
				.hot_content {
					// 名称
					.hot_text {
					padding: 0 16px;
					margin: 0 0 0px;
					overflow: hidden;
					color: #333;
					white-space: nowrap;
					background: none;
					font-size: 14px;
					line-height: 30px;
					text-overflow: ellipsis;
					text-align: center;
					height: 30px;
					}
				}
			}
		}
	}
</style>