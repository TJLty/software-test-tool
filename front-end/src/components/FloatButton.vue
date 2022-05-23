<template>
	<div class="floatButton" @click="onClick">
		<svg t="1653224188446" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
			p-id="5350" width="48" height="48">
			<path
				d="M524.8 64c4.693333 0 8.533333 3.84 8.533333 8.533333V490.666667h418.133334c4.693333 0 8.533333 3.84 8.533333 8.533333v46.933333a8.533333 8.533333 0 0 1-8.533333 8.533334H533.333333v418.133333a8.533333 8.533333 0 0 1-8.533333 8.533333h-46.933333a8.533333 8.533333 0 0 1-8.533334-8.533333V554.666667H51.2a8.533333 8.533333 0 0 1-8.533333-8.533334v-46.933333c0-4.693333 3.84-8.533333 8.533333-8.533333H469.333333V72.533333c0-4.693333 3.84-8.533333 8.533334-8.533333h46.933333z"
				fill="#ffffff" p-id="5351"></path>
		</svg>
	</div>
</template>

<script>
	export default {
		emits:["click"],
		data() {
			return {};
		},
		methods: {
			onClick(){
				this.$emit("click");
			}
		},
	}
</script>

<style scoped>
	.floatButton {
		z-index: 1;
		position: absolute;
		left: 98%;
		top: 98%;
		transform: translate(-100%, -100%);
		background-color: rgb(0, 191, 255);
		border-radius: 100%;
		padding: 12px;
		width: 48px;
		height: 48px;
		
	}
</style>
