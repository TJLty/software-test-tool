<template>
	<div class="projectDetail">
		<template v-if="showNewDataWindow">
			<NewDataWindow :projectId="project.projectId"  @quit="changeShowNewDataWindow"
				@success="handleAddDataSuccess"></NewDataWindow>
		</template>
		<el-table :data="dataList" @selection-change="handleSelectionChange" max-height=700px>
			<el-table-column type="selection" width="55" />
			<!-- 			<el-table-column type="index" :index="indexMethod" /> -->
			<el-table-column prop="batchId" label="id"></el-table-column>
			<el-table-column v-for="(item,index) in cols" :key="index" :prop="item.prop" :label="item.label">
				<template #default="scope">
					<div>{{scope.row.testData[item.prop]}}</div>
				</template>
			</el-table-column>
			<el-table-column prop="answer" label="answer"></el-table-column>
			<el-table-column prop="remark" label="remark"></el-table-column>
		</el-table>
		<div style="margin-top: 20px;">
			<el-button type="primary" @click="startTest">Test</el-button>
			<el-button type="warning">Clear</el-button>
			<el-button type="danger" @click="deleteData">Delete</el-button>
			<el-button type="success" @click="changeShowNewDataWindow"> ADD </el-button>
		</div>
	</div>

</template>

<script>
	import NewDataWindow from "./NewDataWindow.vue"
	export default {
		emits: ['testFinish'],
		props: ['projectId','reFresh','version'],
		components:{
			NewDataWindow
		},
		data() {
			return {
				dataList: [],
				project: null,
				cols: [],
				selectionList: [],
				showNewDataWindow: false
			}
		},
		methods: {
			changeShowNewDataWindow() {
				// console.log("Button Clicked")
				this.showNewDataWindow = !this.showNewDataWindow;
			},
			handleAddDataSuccess(){
				this.getTestData();
				this.changeShowNewDataWindow();
			},
			async getTestData() {
				var config = {
					method: 'get',
					url: '/data',
					params: {
						'projectId': this.projectId
					},
					headers: {}
				};
				var response = await this.$axios(config);
				console.log(response)
				this.dataList = response.data["data"];
				console.log("dataList");
				console.log(this.dataList);
			},
			async getProject() {
				var config = {
					method: 'get',
					url: '/projects/' + this.projectId.toString(),
					headers: {},
				};

				var response = await this.$axios(config);
				console.log(response)
				this.project = response.data["data"];
				this.cols=[]
				for (var i = 1; i <= this.project.projectBatchSize; i++) {
					var name = "arg[" + i.toString() + "]";
					this.cols.push({
						prop: name,
						label: name
					});
				}
			},
			startTest() {
				if(this.version==null){
					return
				}
				console.log(this.version)
				var testList = [];
				for (var i in this.selectionList) {
					console.log(this.selectionList[i])
					testList.push(this.selectionList[i].batchId);
				}
				if(testList.length==0){
					return
				}
				var data = JSON.stringify(testList);

				var config = {
					method: 'post',
					url: '/test/python?tag='+this.version.toString(),
					headers: {
						'Content-Type': 'application/json'
					},
					data: data
				};
				var that = this;
				this.$axios(config)
					.then(function(response) {
						console.log(JSON.stringify(response.data));
						that.$emit("testFinish",response.data);
					})
					.catch(function(error) {
						console.log(error);
					});
			},
			deleteData() {
				console.log(this.selectionList)
				var deleteList = [];
				for (var i in this.selectionList) {
					console.log(this.selectionList[i])
					deleteList.push(this.selectionList[i].batchId);
				}
				console.log(deleteList)
				var data = JSON.stringify(deleteList);

				var config = {
					method: 'delete',
					url: '/batches',
					headers: {
						'Content-Type': 'application/json'
					},
					data: data
				};
				var that = this;
				this.$axios(config)
					.then(function(response) {
						console.log(JSON.stringify(response.data));
						that.getTestData();
					})
					.catch(function(error) {
						console.log(error);
					});

			},
			handleSelectionChange(val) {
				console.log(val)
				this.selectionList = val;
			},
			indexMethod(index) {
				return index + 1;
			},
			addVersion(){
				console.log(addVersion)
			}
		},
		mounted() {
			this.getProject();
			this.getTestData();
		},
		watch:{
			reFresh:{
				handler(newV,oldV){
					console.log("-------------new value------------")
					console.log(newV)
					console.log("----------------------------------")
					this.getProject();
					this.getTestData();
					this.$forceUpdate()
				},
				deep:true
			},
			version:{
				handler(newV,oldV){
					console.log("-------------new value------------")
					console.log(newV)
					console.log("----------------------------------")
				},
				deep:true
			}
		}
	}
</script>

<style scoped>
	.projectDetail {
		background-color: deepskyblue;
		width: 98%;
		padding: 1%;
		color: white;
		z-index: 0;
		height: 98%;
	}

	.el-table {
		border-radius: 3px;

	}

	.el-button {
		font-family: "Comic Sans MS", cursive, sans-serif;
		font-weight: bold;
	}
	
	.pj-select{
		display: inline-block;
		display: ;
	}
</style>
