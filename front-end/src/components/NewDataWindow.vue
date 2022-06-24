<template>
	<div class="newProjectWindow" @click="quit">
		<div class="mainWindow" @click="childClick">
			<el-form v-model="batch" label-width="60px">
				<template v-for="(item,index) in args">
					<el-form-item :label="item.label">
						<el-input v-model="batch.testData[item.prop]" />
					</el-form-item>
				</template>
				<el-form-item label="answer">
					<el-input v-model="batch['answer']" />
				</el-form-item>
				<el-form-item label="remark">
					<el-input v-model="batch['remark']" />
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="submit">Create</el-button>
					<el-button @click="quit">Cancel</el-button>
				</el-form-item>
			</el-form>
		</div>
	</div>
</template>

<script>
	export default {
		props: ['projectId'],
		emits: ["quit","success"],
		data() {
			return {
				projcet: null,
				batch: {},
				args: [],
				
			}
		},
		methods: {
			async getProject() {
				
				var config = {
					method: 'get',
					url: '/projects/' + this.projectId.toString(),
					headers: {},
				};

				var response = await this.$axios(config);
				//console.log(response)
				this.project = response.data["data"];
				for (var i = 1; i <= this.project.projectBatchSize; i++) {
					var name = "arg[" + i.toString() + "]";
					this.args.push({
						prop: name,
						label: name
					});
				}
			},
			quit() {
				this.$emit("quit");
			},
			childClick(event) {
				//console.log("click")
				event = event || window.event;
				if (event.stopPropagation) { //W3C阻止冒泡方法  
					event.stopPropagation();
				} else {
					event.cancelBubble = true; //IE阻止冒泡方法  
				}
			},
			submit(){
				this.batch['projectId']=this.projectId;
				var data = JSON.stringify(this.batch);
				//console.log(data)
				//console.log(this.batch)
				var config = {
				  method: 'post',
				  url: '/batches',
				  headers: { 
				    'Content-Type': 'application/json'
				  },
				  data : data
				};
				var that=this;
				this.$axios(config)
				.then(function (response) {
				  //console.log(JSON.stringify(response.data));
				  that.$emit("success")
				})
				.catch(function (error) {
				  console.log(error);
				});
			},
			handleAddDataSuccess() {
				this.changeShowNewDataWindow();
				//console.log(this.reFresh);
				this.reFresh=!this.reFresh;
				//console.log(this.reFresh);
			},
		},
		created() {
			this.batch.testData={};
			this.getProject();
		},

	}
</script>

<style scoped>
	.newProjectWindow {
		width: 100%;
		height: 100%;
		z-index: 10;
		position: absolute;
		left: 0;
		top: 0;
		background-color: rgba(248, 248, 255, 0.8);
	}

	.mainWindow {
		padding: 50px;
		background-color: rgb(220, 220, 220);
		width: 360px;
		height: 450px;
		position: relative;
		top: 50%;
		left: 50%;
		border-radius: 10px;
		transform: translate(-50%, -50%);
	}
	.el-button {
		font-family: "Comic Sans MS", cursive, sans-serif;
		font-weight: bold;
	}
</style>
