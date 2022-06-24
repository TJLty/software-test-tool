<template>
	<div class="TestResult">
		<div class="close" @click="$emit('clear')">
			<svg t="1653382828409" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
				p-id="2947" width="32" height="32">
				<path
					d="M512 128C300.8 128 128 300.8 128 512s172.8 384 384 384 384-172.8 384-384S723.2 128 512 128zM512 832c-179.2 0-320-140.8-320-320s140.8-320 320-320 320 140.8 320 320S691.2 832 512 832z"
					p-id="2948"></path>
				<path
					d="M672 352c-12.8-12.8-32-12.8-44.8 0L512 467.2 396.8 352C384 339.2 364.8 339.2 352 352S339.2 384 352 396.8L467.2 512 352 627.2c-12.8 12.8-12.8 32 0 44.8s32 12.8 44.8 0L512 556.8l115.2 115.2c12.8 12.8 32 12.8 44.8 0s12.8-32 0-44.8L556.8 512l115.2-115.2C684.8 384 684.8 364.8 672 352z"
					p-id="2949"></path>
			</svg>
		</div>
		<div id="myChart"></div>
		<div class="testDetail">
			<el-table :data="result.testDetail" max-height="375px">
				<el-table-column prop="batchId" label="id"></el-table-column>
				<el-table-column prop="expectAnswer" label="expect"></el-table-column>
				<el-table-column prop="answer" label="test"></el-table-column>
				<el-table-column prop="result" label="result"></el-table-column>
			</el-table>
		</div>
	</div>
</template>

<script>
	import * as echarts from "echarts";
	export default {
		emits: ['clear'],
		props: ['result'],
		data() {
			return {
				data: this.result
			}
		},
		methods: {
			drawLine() {
				// console.log("----------------")
				// console.log(this.result)
				// console.log(this.result.correctNumber)
				// console.log("----------------")
				// 基于准备好的dom，初始化echarts实例
				let myChart = echarts.init(document.getElementById('myChart'), null, {
					width: 400,
					height: 400,
				})

				var option = {
					title: {
						text: 'Test Summary',
						left: 'center',
						top: 'center'
					},
					legend: {
						orient: 'vertical',
						x: 'right',
						data: ['correct', 'error']
					},
					series: [{
						type: 'pie',
						color: [
							'#90EE90',
							'#ff0000'
						],
						label: {
							show: false
						},
						data: [{
								value: this.result.correctNumber,
								name: 'correct'
							},
							{
								value: this.result.totalNumber - this.result.correctNumber,
								name: 'error'
							},
						],
						radius: ['40%', '60%']
					}]
				};
				// 绘制图表
				myChart.setOption(option,true);
			}
		},
		watch: {
			result:{
				handler(newV,oldV){
					console.log("-------------new value------------")
					console.log(newV)
					console.log("----------------------------------")
					echarts.init(document.getElementById('myChart')).dispose();
					this.drawLine()
				},
				deep:true
			}
		},
		mounted() {
			this.drawLine();
		},
	}
</script>

<style>
	.TestResult {
		width: 100%;
		height: 100%;
		padding-top: 25px;
		background-color: whitesmoke;
		font-family: "Comic Sans MS", cursive, sans-serif;
		display: flex;
		position: relative;
	}

	.testDetail {
		width: 100%;
		margin-left: 20px;
		margin-right: 20px;
		display: inline-block;
	}

	.close {
		position: absolute;
		top: 0;
		left: 0;
		cursor: pointer;
	}
</style>
