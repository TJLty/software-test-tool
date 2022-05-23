<template>
	<div class="home">
		<el-alert title="a new test project is established" type="success" show-icon v-if="uploadSuccess" @close="uploadSuccess=false"/>
		<template v-if="showNewProjectWindow">
			<NewProjectWindow @quit="changeShowNewProjectWindow"
				@success="handleUploadSuccess"></NewProjectWindow>
		</template>
		<FloatButton @click="changeShowNewProjectWindow"></FloatButton>
		<div class="header">
			<div class="title">
				software test tool
			</div>
			<div class="pj-select">
				<el-select v-model="value" class="m-2" placeholder="Select" size="large">
					<el-option class="py-selecter" v-for="item in projectList" :key="item.projectId"
						:label="item.projectName" :value="item.projectId" />
				</el-select>
			</div>
		</div>
	</div>
</template>

<script>
	import FloatButton from "./components/FloatButton.vue"
	import NewProjectWindow from "./components/NewProjectWindow.vue"
	export default {
		components: {
			NewProjectWindow,
			FloatButton
		},
		data() {
			return {
				value: "testProject",
				projectList: [{
					"projectId": 2,
					"projectName": "test_project_2",
					"projectIntro": "test",
					"projectCreateTime": "2022-05-22 12:00:47"
				}],
				project: null,
				items: [{
					name: "New Project",
					iconSlot: "new"
				}],
				showNewProjectWindow: false,
				uploadSuccess: false
			}
		},
		methods: {
			changeShowNewProjectWindow() {
				// console.log("Button Clicked")
				this.showNewProjectWindow = !this.showNewProjectWindow;
			},
			async getProjects() {
				var config = {
					method: 'get',
					url: '/projects',
					headers: {}
				};
				var response = await this.$axios(config);
				console.log(response);
				this.projectList = response.data["data"];
				console.log(this.projectList)
			},
			async initProject() {
				if (localStorage.getItem("projectId") != null) {
					var config = {
						method: 'get',
						url: '/projects/' + localStorage.getItem("projectId"),
						headers: {},
					};

					var response = await this.$axios(config);
					console.log(response)
					this.project = response.data["data"];
					this.value = this.project.projectName;
				} else {
					var config = {
						method: 'get',
						url: '/projects/newest',
						headers: {}
					};

					var response = await this.$axios(config);
					console.log(response)
					this.project = response.data["data"];
					this.value = this.project.projectName;

				}
			},
			handleUploadSuccess(){
				this.getProjects();
				this.initProject();
				this.changeShowNewProjectWindow();
				this.uploadSuccess=true;
			},
		},
		created() {
			this.initProject();
			this.getProjects();
		}
	}
</script>



<style scoped>
	.home {
		width: 100%;
		height: 100%;
		font-family: "Comic Sans MS", cursive, sans-serif;
		text-align: center;
	}

	.header {
		background-color: deepskyblue;
		width: 100vw;
		padding: 1rem 0rem 1rem 0rem;
		color: white;
		border-radius: 0px 0px 15px 15px;
		display: flex;
		position: absolute;
		z-index: 0;
	}

	.header>.title {
		font-family: "Comic Sans MS", cursive, sans-serif;
		font-size: 30px;
		margin-left: 3rem;
		text-align: center;
		display: flex;
		justify-content: flex-start;
		align-items: center;
	}

	.header>.pj-select {
		font-family: "Comic Sans MS", cursive, sans-serif;
		margin-right: 3rem;
		flex: 1;
		justify-content: flex-end;
		display: flex;
		align-items: center;
	}

	/deep/ .el-input__inner {
		font-family: "Comic Sans MS", cursive, sans-serif;
	}

	.el-select-dropdown__item {
		font-family: "Comic Sans MS", cursive, sans-serif;
	}
	.el-alert{
		margin: 10px;
		position: absolute;
		z-index: 4;
		width: 400px;
		left: 50%;
		transform: translate(-50%, 0%);
	}
	
</style>
