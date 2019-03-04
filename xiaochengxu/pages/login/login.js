// login/login.js
import Notify from '../../dist/notify/notify';
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data:{
    state:0,
    studentId:"",
    password:""
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  onClickIcon: function () {
    Notify("密码为身份证号后六位！");

  },
  onInputStudentId:function(e){
    this.setData({
      studentId:e.detail
    })
  },
  onInputPassword:function(e){
    this.setData({
      password:e.detail
    })
  },
  login: function () {//way代表使用token还是账号密码登录 0代表用账号密码，1代表用token登录
    this.setData({
      state:1
    })
    var that = this;
    wx.request({
      url: 'https://www.myweb.com:8080/oe/loginByPassword',
      data: {  "studentId":this.data.studentId,"password":this.data.password },
      method: 'POST', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
      // header: {}, // 设置请求的 header
      success: function (res) {
        // success
        if(res.ret==1){
          app.globalData.student=app.parseResult(res);
          wx.showToast({
            title:"欢迎回来",
            duration:2000,
            icon:"success"
          })
          wx.navigateBack({
            delta: 1 // 回退前 delta(默认为1) 页面
      
          })
        }
      }
    })
  }
})