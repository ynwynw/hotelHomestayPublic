<template>
	<div>
		<div class="home_box">
			<!-- 客房信息推荐 -->
			<div class="recomList_view">
				<div class="recomList_title">客房信息推荐</div>
				<div class="recommend_list_two">
					<mySwiper :data="kefangxinxiRecomList" :type="3"
						:loop="false"
						:navigation="true"
						:pagination="false"
						:paginationType="2"
						:scrollbar="false"
						:slidesPerView="3"
						:spaceBetween="20"
						:autoHeight="false"
						:centeredSlides="false"
						:freeMode="true"
						:effectType="2"
						:direction="horizontal"
						:autoplay="true"
						:slidesPerColumn="1">
						<template #default="scope">
							<div class="recommend_item animation_box" @click="detailClick('kefangxinxi',scope.row.id)">
								<div class="recommend_img_box">
									<img class="recommend_img" v-if="isHttp(scope.row.fangjiantupian)" :src="scope.row.fangjiantupian.split(',')[0]" alt="">
									<img class="recommend_img" v-else :src="scope.row.fangjiantupian?$config.url + scope.row.fangjiantupian.split(',')[0]:''" alt="">
								</div>
								<div class="recommend_content">
									<div class='recommend_title'>
										房间号：{{scope.row.fangjianhao}}
									</div>
									<div class='recommend_title'>
										每晚/元：{{scope.row.jiage}}
									</div>
									<div class='recommend_title'>
										房间状态：{{scope.row.fangjianzhuangtai}}
									</div>
								</div>
							</div>
						</template>
					</mySwiper>
				</div>
				<div class="recommend_more_view" @click="moreClick('kefangxinxi')">
					<span class="recommend_more_text">全部产品</span>
					<el-icon><DArrowRight /></el-icon>
				</div>
			</div>
			<!-- 公告信息 -->
			<div class="newsList_view">
				<div class="newsList_title">公告信息</div>
				<div class="news_list_two">
					<mySwiper :data="newsList" :type="3"
						:loop="false"
						:navigation="true"
						:pagination="false"
						:paginationType="1"
						:scrollbar="false"
						:slidesPerView="5"
						:spaceBetween="20"
						:autoHeight="false"
						:centeredSlides="false"
						:freeMode="false"
						:effectType="0"
						:direction="horizontal"
						:autoplay="true"
						:slidesPerColumn="1">
						<template #default="scope">
							<div class="news_item animation_box" @click="newsDetailClick(scope.row.id)">
								<div class='news_img_box'>
									<img class="news_img" v-if="isHttp(scope.row.picture)" :src="scope.row.picture.split(',')[0]" alt="">
									<img class="news_img" v-else :src="scope.row.picture?$config.url + scope.row.picture.split(',')[0]:''" alt="">
								</div>
								<div class="news_content">
									<div class="news_title">{{scope.row.title}}</div>
									<div class="news_text">{{scope.row.introduction}}</div>
									<div class="news_time">{{scope.row.addtime.split(' ')[0]}}</div>
								</div>
							</div>
						</template>
					</mySwiper>
				</div>
				<div class="news_more_view" @click="moreClick('news')">
					<span class="news_more_text">查看更多</span>
					<el-icon><DArrowRight /></el-icon>
				</div>
			</div>
		</div>
		<formModel ref="newsFormModelRef"></formModel>
	</div>
</template>

<script setup>
	import {
		ref,
		getCurrentInstance
	} from 'vue';
	import {
		useRouter
	} from 'vue-router';
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const router = useRouter()
	//客房信息推荐
	const kefangxinxiRecomList = ref([])
	const getkefangxinxiRecomList = () => {
		let autoSortUrl = 'kefangxinxi/autoSort'
		if(context?.$toolUtil.storageGet('frontToken')){
			autoSortUrl = "kefangxinxi/autoSort2"
		}
		context?.$http({
			url: autoSortUrl,
			method: 'get',
			params: {
				page: 1,
				limit: 8
			}
		}).then(res => {
			kefangxinxiRecomList.value = res.data.data.list
		})
	}
	//公告信息弹窗
	import formModel from './news/formModel'
	const newsFormModelRef = ref(null)
	//公告信息
	const newsList = ref([])
	const getNewsList = () => {
		context?.$http({
			url: 'news/list',
			method: 'get',
			params:{
				page:1,
				limit: 12
			}
		}).then(res=>{
			newsList.value = res.data.data.list
		})
	}
	const newsDetailClick = (id=null) => {
		if (id){
			newsFormModelRef.value.init(id)
		}
	}
	//判断图片链接是否带http
	const isHttp = (str) => {
        return str && str.substr(0,4)=='http';
    }
	//跳转详情
	const detailClick = (table,id) => {
		router.push(`/index/${table}Detail?id=${id}`)
	}
	const moreClick = (table) => {
		router.push(`/index/${table}List`)
	}
	const init = () => {
		//客房信息推荐
		getkefangxinxiRecomList()
		//公告信息
		getNewsList()
	}
	init()
</script>

<style lang="scss">
	.home_box {
		padding: 0;
		margin: 0 auto;
		background: #fff;
		display: flex;
		width: 100%;
		justify-content: space-between;
		align-items: flex-start;
		flex-wrap: wrap;
	}
	
	// 推荐
	.recomList_view {
		padding: 40px 0 60px;
		margin: 0 auto;
		background: none;
		width: 100%;
		position: relative;
		order: 0;
		.recomList_title {
			padding: 0;
			margin: 0px 0 0;
			color: #667C5E;
			font-weight: 500;
			width: auto;
			font-size: 30px;
			border-color: #2da065;
			border-width: 0 0 0px;
			border-style: solid;
			text-align: center;
		}
		// list
		.recommend_list_two {
			margin: 40px 0 0;
			background: none;
			display: flex;
			width: 100%;
			flex-wrap: wrap;
			.recommend_item {
				cursor: pointer;
				margin: 0;
				background: none;
				width: 100%;
				.recommend_img_box {
					margin: 0 0 6px;
					width: 100%;
					height: 400px;
					.recommend_img {
						object-fit: cover;
						width: 100%;
						height: 100%;
					}
				}
				.recommend_content {
					margin: 0px 0 0;
					position: relative;
					.recommend_title {
						overflow: hidden;
						color: #333;
						white-space: nowrap;
						background: none;
						font-weight: 500;
						width: 100%;
						font-size: 14px;
						line-height: 30px;
						text-overflow: ellipsis;
						text-align: center;
					}
					.recommend_price {
						color: #c00;
						font-weight: 500;
						display: none;
						width: 100%;
						font-size: 14px;
						line-height: 1;
						text-align: right;
					}
				}
			}
		}
		// list
		// animation
		.animation_box {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
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
		// 更多
		.recommend_more_view {
			cursor: pointer;
			border: 0px solid #ddd;
			border-radius: 0px;
			padding: 0;
			margin: 30px auto;
			color: #fff;
			background: none;
			display: none;
			width: 120px;
			line-height: 34px;
			text-align: center;
			.recommend_more_text {
				padding: 0px;
				color: #333;
				background: none;
				font-size: 16px;
			}
			.el-icon {
				color: #333;
			}
		}
	}
	// 推荐
	// 新闻资讯
	.newsList_view {
		padding: 40px 10% 60px;
		margin: 0 auto;
		background: #fff;
		display: block;
		width: 100%;
		position: relative;
		order: 3;
		height: auto;

		.newsList_title {
			padding: 0 0 8px;
			margin: 0 auto 20px;
			color: #667C5E;
			background: none;
			font-weight: 500;
			width: auto;
			font-size: 30px;
			border-color: #999;
			border-width: 0 0 0px;
			border-style: dashed;
			text-align: left;
		}
		// list
		.news_list_two {
			display: flex;
			width: 100%;
			flex-wrap: wrap;
			.news_item {
				margin: 0 10px;
				background: #fff;
				width: 100%;
				.news_img_box {
					width: 100%;
					height: 300px;
					.news_img {
						object-fit: cover;
						width: 100%;
						height: 100%;
					}
				}
				.news_content {
					padding: 10px;
					background: #B5D0AC30;
					.news_title {
						overflow: hidden;
						color: #000;
						white-space: nowrap;
						font-weight: 600;
						width: 100%;
						font-size: 14px;
						line-height: 40px;
						text-overflow: ellipsis;
						text-align: left;
					}
					.news_text {
						font-size: 14px;
						line-height: 1.5;
					}
					.news_time {
						color: #666;
						width: 100%;
						text-align: right;
					}
				}
			}
		}
		// list
		// animation
		.animation_box {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, -10px, 0px);
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
		// 更多
		.news_more_view {
			cursor: pointer;
			border: 0px solid #ddd;
			border-radius: 0px;
			padding: 0px 0;
			margin: 20px auto;
			top: 26px;
			background: none;
			width: auto;
			line-height: 34px;
			position: absolute;
			right: 10%;
			text-align: center;
			.news_more_text {
				color: #667C5E;
				display: inline-block;
				font-size: 16px;
			}
			.el-icon {
				color: #667C5E;
				display: inline-block;
			}
		}
	}
	// 新闻资讯
	// 首页展示
	.homeList_view {
		padding: 30px 10% 10px;
		margin: 60px 0 0;
		background: #73968310;
		width: 100%;
		position: relative;
		text-align: center;
		order: 2;

		.homeList_title {
			padding: 0px;
			margin: 0;
			color: #739683;
			background: none;
			font-weight: 500;
			width: 100%;
			font-size: 30px;
			border-color: #eee;
			border-width: 0px;
			border-style: solid;
			text-align: center;
		}
		// list
		.home_list_five {
			margin: 30px 0 0;
			display: flex;
			width: 100%;
			align-items: flex-start;
			flex-wrap: wrap;
			.home_list_five_item1 {
				margin: 0 30px 20px 0;
				width: calc(25% - 30px);
				.img_box {
					width: 100%;
					height: 380px;
					.home_img {
						object-fit: cover;
						width: 100%;
						height: 100%;
					}
				}
				.homeList_content {
					padding: 5px 0 0;
					background: none;
					display: flex;
					flex-wrap: wrap;
					.homeList_title1 {
						width: 100%;
						text-align: left;
					}
					.homeList_title2 {
						width: 100%;
						text-align: left;
					}
					.homeList_title3 {
						width: 100%;
						text-align: left;
					}
					.homeList_title4 {
						width: 100%;
						text-align: left;
					}
					.homeList_title5 {
						width: 100%;
						text-align: left;
					}
				}
			}
			.home_list_five_item1:hover {
				.img_box {
					.home_img {
					}
				}
				.homeList_content {
					.homeList_title1 {
					}
					.homeList_title2 {
					}
					.homeList_title3 {
					}
					.homeList_title4 {
					}
					.homeList_title5 {
					}
				}
			}
			.home_list_five_item2 {
				margin: 0 30px 20px 0;
				width: calc(25% - 30px);
				.img_box {
					width: 100%;
					height: 380px;
					.home_img {
						object-fit: cover;
						width: 100%;
						height: 100%;
					}
				}
				.homeList_content {
					padding: 5px 0 0;
					background: none;
					display: flex;
					flex-wrap: wrap;
					.homeList_title1 {
						width: 100%;
						text-align: left;
					}
					.homeList_title2 {
						width: 100%;
						text-align: left;
					}
					.homeList_title3 {
						width: 100%;
						text-align: left;
					}
					.homeList_title4 {
						width: 100%;
						text-align: left;
					}
					.homeList_title5 {
						width: 100%;
						text-align: left;
					}
				}
			}
			.home_list_five_item2:hover {
				.img_box {
					.home_img {
					}
				}
				.homeList_content {
					.homeList_title1 {
					}
					.homeList_title2 {
					}
					.homeList_title3 {
					}
					.homeList_title4 {
					}
					.homeList_title5 {
					}
				}
			}
			.home_list_five_item3 {
				margin: 0 30px 20px 0;
				width: calc(25% - 30px);
				.img_box {
					width: 100%;
					height: 380px;
					.home_img {
						object-fit: cover;
						width: 100%;
						height: 100%;
					}
				}
				.homeList_content {
					padding: 5px 0 0;
					background: none;
					display: flex;
					flex-wrap: wrap;
					.homeList_title1 {
						width: 100%;
						text-align: left;
					}
					.homeList_title2 {
						width: 100%;
						text-align: left;
					}
					.homeList_title3 {
						width: 100%;
						text-align: left;
					}
					.homeList_title4 {
						width: 100%;
						text-align: left;
					}
					.homeList_title5 {
						width: 100%;
						text-align: left;
					}
				}
			}
			.home_list_five_item3:hover {
				.img_box {
					.home_img {
					}
				}
				.homeList_content {
					.homeList_title1 {
					}
					.homeList_title2 {
					}
					.homeList_title3 {
					}
					.homeList_title4 {
					}
					.homeList_title5 {
					}
				}
			}
			.home_list_five_item4 {
				margin: 0 0 20px 0;
				width: calc(25% - 0px);
				.img_box {
					width: 100%;
					height: 380px;
					.home_img {
						object-fit: cover;
						width: 100%;
						height: 100%;
					}
				}
				.homeList_content {
					padding: 5px 0 0;
					background: none;
					display: flex;
					flex-wrap: wrap;
					.homeList_title1 {
						width: 100%;
						text-align: left;
					}
					.homeList_title2 {
						width: 100%;
						text-align: left;
					}
					.homeList_title3 {
						width: 100%;
						text-align: left;
					}
					.homeList_title4 {
						width: 100%;
						text-align: left;
					}
					.homeList_title5 {
						width: 100%;
						text-align: left;
					}
				}
			}
			.home_list_five_item4:hover {
				.img_box {
					.home_img {
					}
				}
				.homeList_content {
					.homeList_title1 {
					}
					.homeList_title2 {
					}
					.homeList_title3 {
					}
					.homeList_title4 {
					}
					.homeList_title5 {
					}
				}
			}
			.home_list_five_item5 {
				margin: 0 30px 20px 0;
				width: calc(25% - 30px);
				.img_box {
					width: 100%;
					height: 380px;
					.home_img {
						object-fit: cover;
						width: 100%;
						height: 100%;
					}
				}
				.homeList_content {
					padding: 5px 0 0;
					background: none;
					display: flex;
					flex-wrap: wrap;
					text-align: left;
					.homeList_title1 {
						width: 100%;
					}
					.homeList_title2 {
						width: 100%;
					}
					.homeList_title3 {
						width: 100%;
					}
					.homeList_title4 {
						width: 100%;
					}
					.homeList_title5 {
						width: 100%;
					}
				}
			}
			.home_list_five_item5:hover {
				.img_box {
					.home_img {
					}
				}
				.homeList_content {
					.homeList_title1 {
					}
					.homeList_title2 {
					}
					.homeList_title3 {
					}
					.homeList_title4 {
					}
					.homeList_title5 {
					}
				}
			}
			.home_list_five_item6 {
				margin: 0 30px 20px 0;
				width: calc(25% - 30px);
				.img_box {
					width: 100%;
					height: 380px;
					.home_img {
						object-fit: cover;
						width: 100%;
						height: 100%;
					}
				}
				.homeList_content {
					padding: 5px 0 0;
					background: none;
					display: flex;
					flex-wrap: wrap;
					text-align: left;
					.homeList_title1 {
						width: 100%;
					}
					.homeList_title2 {
						width: 100%;
					}
					.homeList_title3 {
						width: 100%;
					}
					.homeList_title4 {
						width: 100%;
					}
					.homeList_title5 {
						width: 100%;
					}
				}
			}
			.home_list_five_item6:hover {
				.img_box {
					.home_img {
					}
				}
				.homeList_content {
					.homeList_title1 {
					}
					.homeList_title2 {
					}
					.homeList_title3 {
					}
					.homeList_title4 {
					}
					.homeList_title5 {
					}
				}
			}
			.home_list_five_item7 {
				margin: 0 30px 20px 0;
				width: calc(25% - 30px);
				.img_box {
					width: 100%;
					height: 380px;
					.home_img {
						object-fit: cover;
						width: 100%;
						height: 100%;
					}
				}
				.homeList_content {
					padding: 5px 0 0;
					background: none;
					display: flex;
					flex-wrap: wrap;
					text-align: left;
					.homeList_title1 {
						width: 100%;
					}
					.homeList_title2 {
						width: 100%;
					}
					.homeList_title3 {
						width: 100%;
					}
					.homeList_title4 {
						width: 100%;
					}
					.homeList_title5 {
						width: 100%;
					}
				}
			}
			.home_list_five_item7:hover {
				.img_box {
					.home_img {
					}
				}
				.homeList_content {
					.homeList_title1 {
					}
					.homeList_title2 {
					}
					.homeList_title3 {
					}
					.homeList_title4 {
					}
					.homeList_title5 {
					}
				}
			}
			.home_list_five_item8 {
				margin: 0 0px 20px 0;
				width: calc(25% - 0px);
				.img_box {
					width: 100%;
					height: 380px;
					.home_img {
						object-fit: cover;
						width: 100%;
						height: 100%;
					}
				}
				.homeList_content {
					padding: 5px 0 0;
					background: none;
					display: flex;
					flex-wrap: wrap;
					text-align: left;
					.homeList_title1 {
						width: 100%;
					}
					.homeList_title2 {
						width: 100%;
					}
					.homeList_title3 {
						width: 100%;
					}
					.homeList_title4 {
						width: 100%;
					}
					.homeList_title5 {
						width: 100%;
					}
				}
			}
			.home_list_five_item8:hover {
				.img_box {
					.home_img {
					}
				}
				.homeList_content {
					.homeList_title1 {
					}
					.homeList_title2 {
					}
					.homeList_title3 {
					}
					.homeList_title4 {
					}
					.homeList_title5 {
					}
				}
			}
		}
		// list
		// animation
		.animation_box {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
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
		// 更多
		.homeList_more_view {
			cursor: pointer;
			padding: 0;
			margin: 0 auto;
			top: 80px;
			background: none;
			display: inline-block;
			width: auto;
			position: absolute;
			right: 10%;
			text-align: center;
			.homeList_more_text {
				padding: 0px;
				color: #fff;
				display: inline-block;
				font-size: 16px;
			}
			.el-icon {
				color: #fff;
				display: inline-block;
			}
		}
	}
	// 首页展示
</style>