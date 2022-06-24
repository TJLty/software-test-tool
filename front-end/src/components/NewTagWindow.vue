<template>
	<div class="newTagWindow" @click="quit">
		<div class="mainWindow" @click="childClick">
			<el-form v-model="form" label-width="20px">
				<el-form-item label="tag">
					<el-input v-model="form.tag" />
				</el-form-item>
				<el-form-item label="file" v-if="type=='python'">
					<el-upload class="upload-file" drag :auto-upload="false" :on-change="fileChange">
						<div class="el-upload__text">
							Drop file here or <em>click to upload</em>
						</div>
					</el-upload>
				</el-form-item>
				<el-form-item label="url" v-if="form.type=='api'">
					<el-input v-model="form.url" />
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
		props: ["projectId", "type"],
		emits: ["quit", "success", "type"],
		data() {
			return {
				form: {

				}
			}
		},
		methods: {
			submit() {
				var data = new FormData();
				data.append('project_id', this.projectId);
				data.append('tag', this.form.tag);
				if (this.type == 'python')
					data.append('file', this.form.file.raw);
				else
					data.append('url', this.form.url);
				//console.log(this.form.file);
				var config = {
					method: 'post',
					url: '/tags',
					data: data
				};
				var that = this;
				this.$axios(config)
					.then(function(response) {
						console.log(JSON.stringify(response.data));
						that.$emit("success")
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
	.newTagWindow {
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
		width: 250px;
		height: 275px;
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
