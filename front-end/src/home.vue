<template>
	<div class="home">
		<el-alert title="upload success!" type="success" show-icon v-if="popWindows['UploadSuccessWindow']"
			@close="changeShow('UploadSuccessWindow')" />
		<template v-if="popWindows['AddProjectWindow']">
			<NewProjectWindow @quit="changeShow('AddProjectWindow')" @success="handleUploadSuccess('AddProjectWindow')"></NewProjectWindow>
		</template>
		<template v-if="popWindows['AddTagWindow']">
			<NewTagWindow :projectId="project.projectId" :type="project.projectType" @quit="changeShow('AddTagWindow')" @success="handleUploadSuccess('AddTagWindow')"></NewTagWindow>
		</template>
		

		<FloatButton @click="changeShow('AddProjectWindow')"></FloatButton>
		<div class="header">
			<div class="title">
				software test tool
			</div>
			<div class="pj-select">
				<el-select v-model="value" class="m-2" placeholder="Select" size="large" @change="changeProject">
					<el-option class="py-selecter" v-for="item in projectList" :key="item.projectId"
						:label="item.projectName" :value="item.projectId" />
				</el-select>
			</div>
		</div>

		<template v-if="project">
			<div class="projectView">
				<div class="tag-select">
					<span class="version-tag">
						version:
						<el-select v-model="version" class="m-2" placeholder="Select" size="large" @change="changeTag"
							style="width: 160px;">
							<el-option class="py-selecter" v-for="item in project.tagList" :key="item.tagId"
								:label="item.tag" :value="item.tagId" />
						</el-select>
						<span class="add-version-button" @click="changeShow('AddTagWindow')">add</span>
					</span>
					
				</div>
				<ProjectDetail :projectId="project.projectId" :reFresh="reFresh" :version="version" @testFinish="handleTestFinish" >
				</ProjectDetail>
			</div>
		</template>

		<template v-if="testResult">
			<TestResult :result="testResult" @clear="handleClearResult"></TestResult>
		</template>
	</div>
</template>

<script>
	import FloatButton from "./components/FloatButton.vue"
	import NewProjectWindow from "./components/NewProjectWindow.vue"
	import ProjectDetail from "./components/ProjectDetail.vue"
	import NewDataWindow from "./components/NewDataWindow.vue"
	import TestResult from "./components/TestResult.vue"
	import NewTagWindow from "./components/NewTagWindow.vue"
	export default {
		components: {
			NewProjectWindow,
			NewDataWindow,
			FloatButton,
			ProjectDetail,
			TestResult,
			NewTagWindow
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
				showNewTagWindow: false,
				uploadSuccess: false,
				version: "2",
				addSuccess: false,
				reFresh: true,
				testResult: null,
				popWindows:{
					"AddProjectWindow":false,
					"AddTagWindow":false,
					"UploadSuccessWindow":false
				}
			}
		},
		methods: {
			changeShow(val){
				console.log("click");
				console.log(val);
				this.popWindows[val]=!this.popWindows[val];
			},
			async getProjects() {
				var config = {
					method: 'get',
					url: '/projects',
					headers: {}
				};
				var response = await this.$axios(config);
				// console.log(response);
				this.projectList = response.data["data"];
				console.log(this.projectList)
			},
			async initProject(val) {
				if (val != null) {
					var config = {
						method: 'get',
						url: '/projects/' + val,
						headers: {},
					};

					var response = await this.$axios(config);
					console.log(response)
					this.project = response.data["data"];
					console.log("--------------------tagList---------------------")
					console.log(this.project.tagList)
					this.value = this.project.projectName;
				} else if (localStorage.getItem("projectId") != null) {
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
					this.project = response.data["data"];
					console.log(response.data["data"])
					this.value = this.project.projectName;
					console.log("--------------------tagList---------------------")
					console.log(this.project.tagList)
				}
				if(this.project.tagList.length==0){
					this.version=null
				}else
				this.version = this.project.tagList[this.project.tagList.length-1].tagId;
			},
			handleUploadSuccess(val) {
				if(val=="AddTagWindow"){
					this.initProject(this.project.projectId);
				}else{
					this.getProjects();
					this.initProject();
				}
				this.changeShow(val);
				this.uploadSuccess = true;
			},
			handleAddDataSuccess() {
				this.changeShowNewDataWindow();
				console.log(this.reFresh);
				this.reFresh = !this.reFresh;
				console.log(this.reFresh);
			},
			handleTestFinish(val) {
				console.log("test finish");
				console.log(val);
				this.testResult = val["data"];

			},
			handleClearResult() {
				this.testResult = null;
			},
			changeProject(val) {
				console.log(val);
				this.project = null;
				this.initProject(val);
			},
			changeTag(val){
				console.log("version change");
				this.version=val;
			}
		},
		created() {
			this.initProject(null);

			this.getProjects();
		},

	}
</script>



<style scoped>
	.home {
		width: 100%;
		height: 100%;
		font-family: "Comic Sans MS", cursive, sans-serif;
		text-align: center;
		background-color: deepskyblue;
		user-select:none;
	}

	.header {
		background-color: deepskyblue;
		width: 100%;
		padding: 1rem 0rem 1rem 0rem;
		color: white;
		border-radius: 0px 0px 0px 0px;
		display: flex;
		z-index: 0;
	}

	.header>.title {
		font-family: "Comic Sans MS", cursive, sans-serif;
		font-size: 30px;
		margin-left: 20px;
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

	.el-input__inner {
		font-family: "Comic Sans MS", cursive, sans-serif;
		user-select:none;
	}

	.el-select-dropdown__item {
		font-family: "Comic Sans MS", cursive, sans-serif;
		user-select:none;
	}

	.el-alert {
		margin: 10px;
		position: absolute;
		z-index: 4;
		width: 400px;
		left: 50%;
		transform: translate(-50%, 0%);
	}

	.projectView {
		background-color: deepskyblue;
		text-align: left;
	}

	.el-button {
		margin-left: 20px;
		font-family: "Comic Sans MS", cursive, sans-serif;
		font-weight: bold;
	}

	.tag-select {
		/* 		position: absolute; */
		margin:10px;
	}

	.version-tag {
		color: white;
		background-color: rgb(60, 179, 113);
		padding: 1rem 0.5rem 1rem 0.5rem;
		border-radius: 3px;
		margin-right: 10px;
		
	}

	.add-version-button {
		color: white;
		background-color: rgb(255, 165, 0);
		padding: 0.5rem 0.5rem 0.6rem 0.5rem;
		border-radius: 3px;
		margin-left: 10px;
		cursor: pointer;
	}
</style>
