// pages/experiment/experiment.js
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
      {experimentId:"11001",experimentName:"实验一：猴子摘香蕉",color: 'purple'},
      {experimentId:"11002",experimentName:"实验二：猴子摘香蕉",color: 'red'}
    ],
    status:[
      {experimentId:"11001",status:1},
      {experimentId:"11002",status:2},
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
        header: {header}, // 设置请求的 header
        success: function (res) {
          // success
          if (res.ret == 1) {
            that.setData({
              //experimentList=app.parseResult(res)
            });
            let experimentIdList = new Array(experimentList.length);
            for(let i=0;i<experimentList.length;i++){
              experimentIdList[i]=experimentList[i].experimentId;
            }
            this.getExperimentStatus(experimentIdList)
          } else {
            Toast("获取数据失败，请下拉刷新重试！")
          }
        }
      })
  },
  getExperimentStatus:function(experimentIdList){
    var that = this;
    wx.request({
      url: url + "oe/getExperimentStatus",
      data: { 'experimentIdList': experimentIdList },
      method: 'POST', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
      header: {header}, // 设置请求的 header
      success: function (res) {
        // success
        if (res.ret == 1) {
          that.setData({
            status:app.parseResult(res)
          });
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