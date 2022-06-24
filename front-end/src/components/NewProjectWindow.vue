<template>
	<div class="newProjectWindow" @click="quit">
		<div class="mainWindow" @click="childClick">
			<el-form v-model="form" label-width="120px">
				<el-form-item label="project name">
					<el-input v-model="form.name" />
				</el-form-item>
				<el-form-item label="introduction">
					<el-input v-model="form.intro" />
				</el-form-item>
				<el-form-item label="param number">
					<el-input v-model="form.size" />
				</el-form-item>
				<el-form-item label="project type">
					<el-select v-model="form.type" placeholder="select type">
						<el-option label="python" value="python" />
						<el-option label="web api" value="api" />
					</el-select>
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
		emits: ["quit","success"],
		data() {
			return {
				form: {

				}
			}
		},
		methods: {
			submit() {
				var data = new FormData();
				data.append('name', this.form.name);
				data.append('intro', this.form.intro);
				data.append('type', this.form.type);
				data.append('size',this.form.size)
				var config = {
					method: 'post',
					url: '/projects',
					data: data
				};
				let that=this
				this.$axios(config)
					.then(function(response) {
						//console.log(JSON.stringify(response.data));
						that.$emit("success");
					})
					.catch(function(error) {
						console.log(error);
					});
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
			fileChange(file, fileList) {
				//console.log("change")
				this.form.file = file;
			}
		}
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
		width: 300px;
		height: 300px;
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
