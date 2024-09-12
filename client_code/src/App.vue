<template>
	<router-view />
</template>
<script>
	const debounce = (fn, delay) => {
		let timer = null;
		return function() {
			let context = this;
			let args = arguments;
			clearTimeout(timer);
			timer = setTimeout(function() {
				fn.apply(context, args);
			}, delay);
		}
	}

	const _ResizeObserver = window.ResizeObserver;
	window.ResizeObserver = class ResizeObserver extends _ResizeObserver {
		constructor(callback) {
			callback = debounce(callback, 16);
			super(callback);
		}
	}
</script>
<style lang="scss">
	body {
		margin: 0;
	}
	* {
		box-sizing: border-box;
	}
	.app-contain {
		width: 100%;
		// padding: 0 8%;
		// box-sizing: border-box;
	}
	.section_title {
		border-radius: 0;
		padding: 20px 30px;
		margin: 0 auto 20px;
		clip-path: polygon(98% 0, 100% 50%, 98% 100%, 0% 100%, 1% 52%, 0% 0%);
		color: #fff;
		background: linear-gradient(270deg, rgba(161,185,152,1) 0%, rgba(131,154,122,1) 50%, rgba(161,185,152,1) 100%);
		font-weight: 500;
		font-size: 22px;
		border-color: #839a7a;
		border-width: 0px;
		border-style: solid;
		text-align: center;
	}

	#app {
		font-family: Avenir, Helvetica, Arial, sans-serif;
		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;
		// text-align: center;
		color: #2c3e50;
	}

	nav {
		padding: 30px;

		a {
			font-weight: bold;
			color: #2c3e50;

			&.router-link-exact-active {
				color: #42b983;
			}
		}
	}
</style>