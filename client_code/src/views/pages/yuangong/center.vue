<template>
	<div class="app-contain" :style='{"width":"80%","padding":"0","margin":"30px auto 50px","position":"relative","borderRadius":"0px","background":"none"}'>
		<div class="section_title">
			{{formName}}
		</div>
		<div class="usersView">
			<div class="usersTabView">
				<div class="usersTab" :class="tabIndex=='center'?'usersTabActive':''" @click="tabClick({tableName:'center'})">个人中心</div>
				<div class="usersTab " :class="tabIndex=='updatepassword'?'usersTabActive':''" @click="tabClick({tableName:'updatepassword'})">修改密码</div>
				<div v-for="(item,index) in menuList" :key="index" class="usersTab" @mouseenter="usersTabHover(index)"
					@mouseleave="usersTabLeave">
					{{item.menu}}
					<el-collapse-transition>
						<div class="usersTabHoverView" v-if="usersTabIndex==index">
							<div class="usersTabHoverTab" v-for="(items,indexs) in item.child" @click="tabClick(items)">
								{{items.menu}}</div>
						</div>
					</el-collapse-transition>
				</div>
			</div>
			<div class="usersBox" v-if="tabIndex=='center'">
				<el-form class="usersForm" ref="userFormRef" :model="userForm" label-width="120px" :rules="rules">
					<el-row>
						<el-col :span="13">
							<el-form-item prop="yuangonggonghao" label="员工工号">
								<el-input class="list_inp" v-model="userForm.yuangonggonghao" placeholder="员工工号" readonly></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="13">
							<el-form-item prop="yuangongmima" label="员工密码">
								<el-input class="list_inp" v-model="userForm.yuangongmima" placeholder="员工密码" type="password"></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="13">
							<el-form-item prop="yuangongxingming" label="员工姓名">
								<el-input class="list_inp" v-model="userForm.yuangongxingming" placeholder="员工姓名" ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="24">
							<el-form-item prop="touxiang" label="头像">
								<uploads
									action="file/upload" 
									tip="请上传头像" 
									:limit="1" 
									style="width: 100%;text-align: left;"
									:fileUrls="userForm.touxiang?userForm.touxiang:''" 
									@change="touxiangUploadSuccess">
								</uploads>
							</el-form-item>
						</el-col>
						<el-col :span="13">
							<el-form-item label="性别" prop="xingbie">
								<el-select
									class="list_sel"
									v-model="userForm.xingbie" 
									placeholder="请选择性别"
									style="width:100%;"
									>
									<el-option v-for="(item,index) in xingbieLists" :label="item" :value="item">
									</el-option>
								</el-select>
							</el-form-item>
						</el-col>
						<el-col :span="13">
							<el-form-item prop="lianxidianhua" label="联系电话">
								<el-input class="list_inp" v-model="userForm.lianxidianhua" placeholder="联系电话" ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="13">
							<el-form-item prop="shenfenzhenghao" label="身份证号">
								<el-input class="list_inp" v-model="userForm.shenfenzhenghao" placeholder="身份证号" ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="13">
							<el-form-item prop="bumen" label="部门">
								<el-input class="list_inp" v-model="userForm.bumen" placeholder="部门" ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="13">
							<el-form-item prop="zhiwei" label="职位">
								<el-input class="list_inp" v-model="userForm.zhiwei" placeholder="职位" ></el-input>
							</el-form-item>
						</el-col>
					</el-row>
					<div class="formModel_btn_box">
						<el-button class="formModel_confirm" @click="updateSession">更新信息</el-button>
						<el-button class="formModel_cancel" @click="loginout" type="danger">退出登录</el-button>
					</div>
				</el-form>
			</div>
			<div class="usersBox" v-if="tabIndex=='updatepassword'">
				<el-form class="usersForm" ref="passwordFormRef" :model="passwordForm" label-width="120px"
					:rules="passwordRules">
					<el-row>
						<el-col :span="13">
							<el-form-item label="原密码" prop="mima">
								<el-input class="list_inp" v-model="passwordForm.mima" placeholder="原密码"
									type="password"></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="13">
							<el-form-item label="新密码" prop="newmima">
								<el-input class="list_inp" v-model="passwordForm.newmima" placeholder="新密码"
									type="password"></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="13">
							<el-form-item label="确认密码" prop="newmima2">
								<el-input class="list_inp" v-model="passwordForm.newmima2" placeholder="确认密码"
									type="password"></el-input>
							</el-form-item>
						</el-col>
					</el-row>
					<div class="formModel_btn_box">
						<el-button class="formModel_confirm" @click="updatePassword">修改密码</el-button>
					</div>
				</el-form>
			</div>
		</div>
	</div>
</template>
<script setup>
	import {
		ref,
		getCurrentInstance,
		watch,
		onUnmounted,
		onMounted,
	} from 'vue';
	import {
		useRoute,
		useRouter
	} from 'vue-router';
	import menu from '@/utils/menu'
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const route = useRoute()
	const router = useRouter()
	//基础信息
	const tableName = 'yuangong'
	const formName = '个人中心'
	//基础信息
	//个人信息
	const userFormRef = ref(null)
	const userForm = ref({})
	//修改密码
	const passwordFormRef = ref(null)
	const passwordForm = ref({
		mima: '',
		newmima: '',
		newmima2: ''
	})
	const passwordRules = ref({
		mima: [{
			required: true,
			message: '请输入',
			trigger: 'blur'
		}, ],
		newmima: [{
			required: true,
			message: '请输入',
			trigger: 'blur'
		}, ],
		newmima2: [{
			required: true,
			message: '请输入',
			trigger: 'blur'
		}, ],
	})
	//验证规则
	//匹配整数
	const validateIntNumber = (rule, value, callback) => {
		if (!value) {
			callback();
		} else if (!context?.$toolUtil.isIntNumer(value)) {
			callback(new Error("请输入整数"));
		} else {
			callback();
		}
	}
	//匹配数字
	const validateNumber = (rule, value, callback) => {
		if(!value){
			callback();
		} else if (!context?.$toolUtil.isNumber(value)) {
			callback(new Error("请输入数字"));
		} else {
			callback();
		}
	}
	//匹配手机号码
	const validateMobile = (rule, value, callback) => {
		if(!value){
			callback();
		} else if (!context?.$toolUtil.isMobile(value)) {
			callback(new Error("请输入正确的手机号码"));
		} else {
			callback();
		}
	}
	//匹配电话号码
	const validatePhone = (rule, value, callback) => {
		if(!value){
			callback();
		} else if (!context?.$toolUtil.isPhone(value)) {
			callback(new Error("请输入正确的电话号码"));
		} else {
			callback();
		}
	}
	//匹配邮箱
	const validateEmail = (rule, value, callback) => {
		if(!value){
			callback();
		} else if (!context?.$toolUtil.isEmail(value)) {
			callback(new Error("请输入正确的邮箱地址"));
		} else {
			callback();
		}
	}
	//匹配身份证
	const validateIdCard = (rule, value, callback) => {
		if(!value){
			callback();
		} else if (!context?.$toolUtil.checkIdCard(value)) {
			callback(new Error("请输入正确的身份证号码"));
		} else {
			callback();
		}
	}
	//匹配网站地址
	const validateUrl = (rule, value, callback) => {
		if(!value){
			callback();
		} else if (!context?.$toolUtil.isURL(value)) {
			callback(new Error("请输入正确的URL地址"));
		} else {
			callback();
		}
	}
	const rules = ref({
		yuangonggonghao: [
			{required: true,message: '请输入',trigger: 'blur'}, 
		],
		yuangongmima: [
			{required: true,message: '请输入',trigger: 'blur'}, 
		],
		yuangongxingming: [
			{required: true,message: '请输入',trigger: 'blur'}, 
		],
		touxiang: [
		],
		xingbie: [
		],
		lianxidianhua: [
			{ validator: validateMobile, trigger: 'blur' },
		],
		shenfenzhenghao: [
			{ validator: validateIdCard, trigger: 'blur' },
		],
		bumen: [
		],
		zhiwei: [
		],
	})
	const getSession = () =>{
		context?.$http({
			url: `${context?.$toolUtil.storageGet('frontSessionTable')}/session`,
			method:'get'
		}).then(res=>{
			context?.$toolUtil.storageSet('userid',res.data.data.id)
			context?.$toolUtil.storageSet("frontName", res.data.data.yuangonggonghao)
			context?.$toolUtil.storageSet('headportrait',res.data.data.touxiang)
			userForm.value = res.data.data
		})
	}
	//菜单跳转
	const tabIndex = ref('center')
	const tabClick = (item) => {
		if (item.tableName == 'center') {
			tabIndex.value = 'center'
			return false
		}
		if (item.tableName == 'updatepassword') {
			passwordForm.value = {
				mima: '',
				newmima: '',
				newmima2: ''
			}
			tabIndex.value = 'updatepassword'
			return false
		}
		if(item.tableName=='examrecord'&&item.menuJump=='22'){
			router.push(`/index/examfailrecord?centerType=1`)
			return false
		}
		if(item.tableName=='forum'&&item.menuJump=='14'){
			router.push(`/index/forumList?centerType=1&&myType=1`)
			return false
		}
		switch (item.menu) {
			case '我的收藏':
				router.push(`/index/storeupList?centerType=1&&type=1`)
				break;
			default:
				router.push(`/index/${item.tableName}List?centerType=1`)
		}
	}
	// 修改密码
	const updatePassword = async ()=>{
		passwordFormRef.value.validate(async (valid) => {
			if (valid) {
				if(passwordForm.value.mima != userForm.value.yuangongmima){
					context?.$toolUtil.message('原密码不正确', 'error')
					return false
				}
				if(passwordForm.value.newmima != passwordForm.value.newmima2){
					context?.$toolUtil.message('两次密码输入不正确', 'error')
					return false
				}
				userForm.value.yuangongmima = passwordForm.value.newmima
				context?.$http({
					url: `${tableName}/update`,
					method: 'post',
					data: userForm.value
				}).then(res => {
					context?.$toolUtil.message('更新成功', 'success', () => {
						passwordForm.value = {
							mima: '',
							newmima: '',
							newmima2: ''
						}
						getSession()
					})
				})
			}
		})
	}
	//菜单
	const menuList = ref([])
	const role = ref('')
	//头像上传回调
	const touxiangUploadSuccess=(e)=>{
		userForm.value.touxiang = e
	}
	//性别列表
	const xingbieLists = ref([])
	//初始化
	const init = () => {
		const menus = menu.list()
		let arr = []
		let brr = []
		if (menus) {
			menuList.value = menus
		}
		role.value = context?.$toolUtil.storageGet('frontRole')
		for (let i = 0; i < menuList.value.length; i++) {
			if (menuList.value[i].roleName == role.value) {
				arr = menuList.value[i].backMenu
				break;
			}
		}
		for(let x in arr){
			if(arr[x].child){
				if(arr[x].child[0].tableName == 'orders'){
					brr = JSON.parse(JSON.stringify(arr[x].child[0]))
					arr[x].child = [brr]
				}
			}
		}
		menuList.value = arr
		xingbieLists.value = "男,女".split(',')
		getSession()
	}
	//菜单悬浮的显示与隐藏
	const usersTabIndex = ref(-1)
	const usersTabHover = (index) => {
		usersTabIndex.value = index
	}
	const usersTabLeave = () => {
		usersTabIndex.value = -1
	}
	//富文本
	const editorChange = (e,name) =>{
		userForm.value[name] = e
	}
	//保存
	const updateSession = () => {
		userFormRef.value.validate((valid)=>{
			if(valid){
				if(userForm.value.touxiang!=null){
					userForm.value.touxiang = userForm.value.touxiang.replace(new RegExp(context?.$config.url,"g"),"");
				}
				context?.$http({
					url: `${tableName}/update`,
					method: 'post',
					data: userForm.value
				}).then(res=>{
					context?.$toolUtil.message('更新成功','success',()=>{
						getSession()
					})
				})
			}
		})
	}
	//退出登录
	const loginout = () => {
		context?.$toolUtil.message('退出成功', 'success')
		context?.$toolUtil.storageClear()
		router.replace('/index/home')
	}
	init()
</script>

<style lang="scss" scoped>
	.usersView {
		padding: 0 60px;
		margin: 0px 0;
		display: block;
		border-color: #B5D0AC30;
		flex-wrap: wrap;
		border-radius: 0 0 0px 0px;
		overflow: hidden;
		background: #B5D0AC10;
		width: 100%;
		justify-content: space-between;
		border-width: 1px;
		align-items: flex-start;
		border-style: solid;

		.usersTabView {
			border: 0px solid #ddd;
			padding: 30px 0px 0;
			margin: 20px auto;
			background: none;
			display: block;
			width: 220px;
			box-sizing: border-box;
			float: right;
			flex-wrap: wrap;
			text-align: center;

			.usersTab {
				cursor: pointer;
				padding: 10px 20px;
				margin: 0px 10px 10px 0;
				color: #313030;
				display: inline-block;
				font-size: 15px;
				border-color: #b5b5b5;
				transition: all 0.3s;
				border-radius: 0px;
				background: #667C5E30;
				width: 100%;
				border-width: 0px;
				position: relative;
				border-style: solid;
				text-align: center;

				.usersTabHoverView {
					border: 1px solid #ddd;
					padding: 0;
					z-index: 2000;
					right: 100%;
					box-sizing: border-box;
					transition: all 0s;
					border-radius: 0px;
					box-shadow: 0 0px 0px rgba(0, 0, 0, .1);
					top: calc(0% + 0px);
					background: #fff;
					width: auto;
					position: absolute;
					min-width: 100%;
					.usersTabHoverTab {
						border: 0px solid #eee;
						border-radius: 4px;
						padding: 0 10px;
						margin: 0 0 0px;
						color: #666;
						background: #fff;
						font-size: 14px;
						line-height: 36px;
						text-align: left;
					}
					.usersTabHoverTab:hover {
						border-radius: 0;
						color: #333;
						background: #667C5E30;
					}
				}
			}
			.usersTab:hover {
				color: #fff;
				background: #667C5E;
				border-color: #113961;
				border-width: 0px;
				border-style: solid;
			}
			.usersTabActive {
				border-radius: 0px;
				padding: 10px 20px;
				margin: 0 10px 10px 0;
				clip-path: polygon(100% 0, 95% 51%, 100% 100%, 0% 100%, 5% 51%, 0% 0%);
				color: #fff;
				background: #667C5E;
				display: inline-block;
				width: 100%;
				border-color: #113961;
				border-width: 0px;
				border-style: solid;
				text-align: center;
			}
		}

		.usersBox {
			border: 0px solid #ddd;
			border-radius: 0px;
			padding: 40px;
			margin: 0px;
			background: none;
			flex: 1;
			width: calc(100% - 220px);
			box-sizing: border-box;
			float: left;
			.usersForm {
				border: 0px solid #eee;
				border-radius: 0px;
				padding: 0;
				background: none;
				// form item
				:deep(.el-form-item) {
					border: 0px solid #eee;
					border-radius: 4px;
					padding: 6px 0;
					margin: 0 2% 20px 0;
					background: none;
					display: flex;
					width: 100%;
					flex-wrap: wrap;
					//label
					.el-form-item__label {
						background: none;
						display: block;
						width: auto;
						min-width: 150px;
						text-align: right;
					}
					// 内容盒子
					.el-form-item__content {
						display: flex;
						width: calc(100% - 150px);
						justify-content: flex-start;
						align-items: center;
						flex-wrap: wrap;
						// 输入框
						.list_inp {
							padding: 0 10px;
							background: #fff;
							width: auto;
							border-color: #ccc;
							border-width: 1px;
							line-height: 36px;
							box-sizing: border-box;
							border-style: solid;
							min-width: 100%;
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
						// 下拉框
						.list_sel {
							border-radius: 0;
							padding: 0 10px;
							background: #fff;
							width: auto;
							border-color: #ccc;
							border-width: 1px;
							line-height: 36px;
							box-sizing: border-box;
							border-style: solid;
							min-width: 100%;
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
						//图片上传样式
						.el-upload-list  {
							//提示语
							.el-upload__tip {
								margin: 7px 0 0;
								color: #999;
								display: flex;
								font-size: 14px;
								justify-content: flex-start;
								align-items: center;
							}
							//外部盒子
							.el-upload--picture-card {
								border: 1px solid #ccc;
								cursor: pointer;
								border-radius: 0px;
								background: #fff;
								display: flex;
								width: 150px;
								line-height: 90px;
								justify-content: center;
								align-items: center;
								text-align: center;
								height: 80px;
								//图标
								.el-icon{
									color: #999;
									font-size: 24px;
								}
							}
							.el-upload-list__item {
								border: 1px solid #ccc;
								cursor: pointer;
								border-radius: 0px;
								background: #fff;
								display: flex;
								width: 150px;
								line-height: 90px;
								justify-content: center;
								align-items: center;
								text-align: center;
								height: 80px;
							}
						}
					}
				}
			}
		}
		// 按钮盒子
		.formModel_btn_box {
				padding: 0 150px;
				display: flex;
				width: 100%;
				align-items: center;
			.formModel_cancel {
				border: 0px solid #ddd;
				cursor: pointer;
				border-radius: 0px;
				padding: 0 30px;
				margin: 0 10px 0 0;
				outline: none;
				color: #333;
				background: #667C5E50;
				width: auto;
				font-size: 14px;
				transition: all 0.3s;
				height: 40px;
			}
			.formModel_cancel:hover {
			}
		
			.formModel_confirm {
				border: 0;
				cursor: pointer;
				border-radius: 0px;
				padding: 0 30px;
				margin: 0 20px 0 0;
				outline: none;
				color: #fff;
				background: #667C5E;
				width: auto;
				font-size: 14px;
				transition: all 0.3s;
				height: 40px;
			}
			.formModel_confirm:hover {
			}
		}
	}
</style>