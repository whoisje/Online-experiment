//app.js
App({
  globalData: {
    url:"https://www.myweb.com:8080/",
    header:{'Cookie':""},
    student: {
      studentId: "",
      studentName: "",
      class: {
        classId: "",
        className: ""
      }
    }
  },
  onLaunch: function () {
    // 展示本地存储能力
    wx.setStorageSync('tooken', "123445")
    this.checkState()
  },
  checkState: function () {
    var tooken = wx.getStorageSync('tooken');
    if (!tooken) {
      wx.navigateTo({
        url: '/pages/login/login'
      })
    } else {
      this.login(tooken);
    }
  },
  login: function (tooken) {//way代表使用token还是账号密码登录 0代表用账号密码，1代表用token登录
    wx.request({
      url: 'https://www.myweb.com:8080/oe/loginByToken',
      data: {  "tooken":tooken },
      method: 'POST', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
      // header: {}, // 设置请求的 header
      success: function (res) {
        // success
        if(res.ret==1){
          this.globalData.student=this.parseResult(res);
          this.globalData.header.Cookie="StudentId="+res.result.student.studentId;
          wx.showToast({
            title:"欢迎回来",
            duration:2000,
            icon:"success"
          })
        }
      }
    })
  },
  parseResult(res){//解析result
    return "success"//JSON.parse(res.result)
  }
})