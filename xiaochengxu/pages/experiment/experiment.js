// pages/experiment/experiment.js
import Toast from '../../dist/toast/toast';
var app = getApp();
var header = app.globalData.header;
var url = app.globalData.url;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    courseId:"",
    experimentList:[
    ],
    status:[
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      courseId:options.courseId
    });
  },

  onShow: function () {
    this.getExperimentList(this.data.courseId);
  },
  getExperimentList:function(courseId){
      var that = this;
      wx.request({
        url: url + "oe/getExperimentList",
        data: { 'courseId': courseId },
        method: 'GET', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
        header: { 'Cookie':header.Cookie }, // 设置请求的 header
        success: function (res) {
          // success
          if (res.data.ret==1) {
            that.setData({
              experimentList:res.data.body
            });
            var experiment = that.data.experimentList;
            let experimentIdList = new Array(experiment.length);
            for(let i=0;i<experiment.length;i++){
              experimentIdList[i]=experiment[i].experimentId;
            }
            that.getExperimentStatus(experimentIdList)
          } else {
            Toast("获取数据失败，请下拉刷新重试！")
          }
        }
      })
  },
  getExperimentStatus:function(experimentIdList){
    var that = this;
    wx.request({
      url: url + "oe/selectStatusList",
      data: { 'experimentIdList': experimentIdList },
      method: 'POST', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
      header: {'Cookie':header.Cookie}, // 设置请求的 header
      success: function (res) {
        // success
        if (res.data.ret == 1) {
          that.setData({
            status:res.data.body
          });
          console.log(that.data)
        } else {
          Toast("获取数据失败，请下拉刷新重试！")
        }
        wx.hideNavigationBarLoading();
        wx.stopPullDownRefresh();
      }
      
    })
  },
  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    wx.showNavigationBarLoading();
    this.getExperimentList();
  }
})