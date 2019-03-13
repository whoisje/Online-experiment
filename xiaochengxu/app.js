//app.js
require("./utils/v-request.js")
App({
  globalData: {
    url: "http://129.204.38.10:8080/",
    header: { 'Cookie': "" },
    studentId: "",
    studentName: "",
  },
  data: {
    studentId: "",
    password: ""
  },
  onLaunch: function () {
    console.log(this.globalData)
    // 展示本地存储能力
    this.checkState()
    this.login()
  },
  checkState: function () {
    var userId = wx.getStorageSync('userId');
    if (!userId) {
      wx.navigateTo({
        url: '/pages/login/login'
      })
    }
  },
  login: function () {//way代表使用token还是账号密码登录 0代表用账号密码，1代表用token登录
    let userId = wx.getStorageSync('userId');
    let password = wx.getStorageSync('password');
    var that = this;
    console.log(that.data.userId)
    wx.request({
      url: this.globalData.url+'oe/login',
      data: { "userId": userId, "password": password },
      method: 'GET', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
      // header: {}, // 设置请求的 header
      success: function (res) {
        // success
        if (res.data.ret == 1) {
          that.globalData.header.Cookie = "JSESSIONID=" + res.data.body.studentId;
          that.globalData.studentId = res.data.body.studentId;
          wx.showToast({
            title: "欢迎回来",
            duration: 2000,
            icon: "success"
          })
          
        } else {
          wx.navigateTo({
            url: '/pages/login/login'
          })
        }
      },fail:function(){
        wx.navigateTo({
          url: '/pages/login/login'
        })
      }
    })
  },
  parseResult(res) {//解析result
    return JSON.parse(res.body);
  }
,
  parseArray(res){
    return res.body.parseJSON()
  }
})