<template>
    <view>
        <!--标题和返回-->
		<cu-custom :bgColor="NavBarColor" isBack :backRouterName="backRouteName">
			<block slot="backText">返回</block>
			<block slot="content">客户接待统计</block>
		</cu-custom>
		 <!--表单区域-->
		<view>
			<form>
              <my-date label="来访日期：" fields="day" v-model="model.visitDate" placeholder="请输入来访日期"></my-date>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">来访单位全称：</text></view>
                  <input  placeholder="请输入来访单位全称" v-model="model.visitOrgName"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">来访主要领导及职务：</text></view>
                  <input  placeholder="请输入来访主要领导及职务" v-model="model.leadersAndPositions"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">我司参加接待领导及职务：</text></view>
                  <input  placeholder="请输入我司参加接待领导及职务" v-model="model.ourLeadersAndPositions"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">来访目的：</text></view>
                  <input  placeholder="请输入来访目的" v-model="model.visitPurpose"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">实际效果（简述）：</text></view>
                  <input  placeholder="请输入实际效果（简述）" v-model="model.actualEffect"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">备注：</text></view>
                  <input  placeholder="请输入备注" v-model="model.note"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">接待照片1：</text></view>
                  <input  placeholder="请输入接待照片1" v-model="model.img1"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">接待照片2：</text></view>
                  <input  placeholder="请输入接待照片2" v-model="model.img2"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">接待照片3：</text></view>
                  <input  placeholder="请输入接待照片3" v-model="model.img3"/>
                </view>
              </view>
				<view class="padding">
					<button class="cu-btn block bg-blue margin-tb-sm lg" @click="onSubmit">
						<text v-if="loading" class="cuIcon-loading2 cuIconfont-spin"></text>提交
					</button>
				</view>
			</form>
		</view>
    </view>
</template>

<script>
    import myDate from '@/components/my-componets/my-date.vue'

    export default {
        name: "ExternalReceptionForm",
        components:{ myDate },
        props:{
          formData:{
              type:Object,
              default:()=>{},
              required:false
          }
        },
        data(){
            return {
				CustomBar: this.CustomBar,
				NavBarColor: this.NavBarColor,
				loading:false,
                model: {},
                backRouteName:'index',
                url: {
                  queryById: "/smartcity/externalReception/queryById",
                  add: "/smartcity/externalReception/add",
                  edit: "/smartcity/externalReception/edit",
                },
            }
        },
        created(){
             this.initFormData();
        },
        methods:{
           initFormData(){
               if(this.formData){
                    let dataId = this.formData.dataId;
                    this.$http.get(this.url.queryById,{params:{id:dataId}}).then((res)=>{
                        if(res.data.success){
                            console.log("表单数据",res);
                            this.model = res.data.result;
                        }
                    })
                }
            },
            onSubmit() {
                let myForm = {...this.model};
                this.loading = true;
                let url = myForm.id?this.url.edit:this.url.add;
				this.$http.post(url,myForm).then(res=>{
				   console.log("res",res)
				   this.loading = false
				   this.$Router.push({name:this.backRouteName})
				}).catch(()=>{
					this.loading = false
				});
            }
        }
    }
</script>
