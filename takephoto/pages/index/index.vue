<template>
	<view class="content">
		<image class="logo" src="/static/logo.png"></image>
		<view>
			<view>人脸数量:{{this.number}}</view>
			<button style="margin-top: 10upx;" @click='takephoto' form-type="submit">takePhoto</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: 'Hello',
				number:null
			}
		},
		onLoad() {

		},
		methods: {
			takephoto(){
				uni.chooseImage({
				  	count: 1,
				    sizeType: ['original', 'compressed'],
				    sourceType: ['camera','album'], //这要注意，camera掉拍照，album是打开手机相册
				    success: (res)=> {
						console.log(res);
						const tempFilePaths = res.tempFilePaths;
						uni.uploadFile({
							url: 'http://localhost:8088/check/checkFace', //服务器地址
							fileType:"image",//必填,不然报错
							filePath: tempFilePaths[0],//这个就是我们上面拍照返回或者先中照片返回的数组
							name: 'imgFile',
							success: (uploadFileRes) => {
								console.log(uploadFileRes);
								this.number = uploadFileRes.data
							}
						});
				    }
				});
			}
		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.logo {
		height: 200rpx;
		width: 200rpx;
		margin-top: 200rpx;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 50rpx;
	}

	.text-area {
		display: flex;
		justify-content: center;
	}

	.title {
		font-size: 36rpx;
		color: #8f8f94;
	}
</style>
