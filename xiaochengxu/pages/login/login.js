// login/login.js
import Notify from '../../dist/notify/notify';
var app = getApp();
var url = app.globalData.url;
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
      url: url+'oe/login',
      method: 'GET', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
      data: { "userId": that.data.studentId, "password": that.data.password },
      // header: {}, // 设置请求的 header
      success: function (res) {
        // success
        console.log(res.data.ret)
        if(res.data.ret==1){
          app.globalData.header.Cookie = "JSESSIONID=" + res.data.body.studentId;
          app.globalData.studentId = res.data.body.studentId;
          wx.setStorageSync('userId', that.data.studentId);
          wx.setStorageSync('password',that.data.password)
          wx.showToast({
            title:"欢迎回来",
            duration:2000,
            icon:"success"
          })
          wx.navigateBack({
            delta: 1, // 回退前 delta(默认为1) 页面
          })
        }else{
          that.setData({
            state:0
          })
          Notify("用户名或密码错误！");
        }
      },fail:function(){
        that.setData({
          state:0
        })
        Notify("请求失败！");
      }
    })
  }
})