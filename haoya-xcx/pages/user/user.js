//index.js
//获取应用实例
var app = getApp()
var myData = require('../../utils/data')
var util = require('../../utils/util')

Page({
  data: {
    userInfo: {}
  },
  // 页面初始数据
  // data: {
  //   userData:myData.userData(),
  //   addrDate:util.replacePhone(myData.userData().addrs,true)
  // },
  // 地址编辑
  // editAddr : function(e){
  //   console.log(e)
  //   wx.navigateTo({
  //     url:'../edit_addr/edit_addr?addrid='+e.currentTarget.dataset.addrid
  //   })
  // }
  onLoad: function () {
    console.log('onLoad')
    var that = this
    //调用应用实例的方法获取全局数据
    app.getUserInfo(function (userInfo) {
      //更新数据
      that.setData({
        userInfo: userInfo
      })
    })
  },
  loginFun: function () {
    wx.login({
      success: function (res) {

        console.log("dasda")
        if (res.code) {
          //发起网络请求
          // wx.request({
          //     url: 'https://test.com/onLogin',
          //     data: {
          //         code: res.code
          //     }
          // })
          console.log("发起微信登录请求！");
          console.log(res.code);
        } else {
          console.log('获取用户登录态失败！' + res.errMsg)
        }
      }
    });
  },
  calling: function () {
    wx.makePhoneCall({
      phoneNumber: '12345678900', //此号码并非真实电话号码，仅用于测试
      success: function () {
        console.log("拨打电话成功！")
      },
      fail: function () {
        console.log("拨打电话失败！")
      }
    })
  }

})
