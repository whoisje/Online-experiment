// paper/paper.js
var app = getApp();
var header = app.globalData.header;
let url = app.globalData.url;

Page({

  /**
   * 页面的初始数据
   */
  data: {
    experimentId: 0,
    HasFinished: {
      code: "",
      feeling: "",
      codeResult: "",
      runStatus: "",
      status: 2
    },
    focus: false,
    disabled: false,
    result: "",
    StatusBar: app.globalData.StatusBar,
    CustomBar: app.globalData.CustomBar,
    TabCur: 0,
    scrollLeft: 0
  },
  tabSelect(e) {
    console.log(e);
    this.setData({
      TabCur: e.currentTarget.dataset.id,
      scrollLeft: (e.currentTarget.dataset.id - 1) * 60
    })
    console.log(this.data.TabCur)
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      experimentId: options.experimentId
    });
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.getExperimentDetail(this.data.experimentId);
  },
  getExperimentDetail(experimentId) {
    var that = this;
    wx.request({
      url: url + "oe/getExperimentDetail",
      data: { 'experimentId': experimentId },
      method: 'GET', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
      header: { header }, // 设置请求的 header
      success: function (res) {
        // success
        if (res.ret == 1) {
          that.setData({
            courseList: app.parseResult(res)
          });
          this.getHasFinished(experimentId);
        } else {
          Toast("获取数据失败，请重试！")
        }
      }
    })
  },
  getHasFinished(experimentId) {
    var that = this;
    wx.request({
      url: url + "oe/getHasFinished",
      data: { 'experimentId': experimentId },
      method: 'GET', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
      header: { header }, // 设置请求的 header
      success: function (res) {
        // success
        if (res.ret == 1) {
          that.setData({
            courseList: app.parseResult(res)
          });
        } else {
          Toast("获取数据失败，请重试！")
        }
      }
    })
  },
  onCodeInput: function (e) {
    this.setData({
      'HasFinished.code': e.detail
    });
    console.log(e.detail);
  },
  onFeelingInput: function (e) {
    this.setData({
      'HasFinished.feeling': e.detail
    });
    console.log(e.detail);
  },
  save: function () {
    var that = this;
    wx.request({
      url: url + "oe/save",
      data: {
        'HasFinished': this.data.HasFinished,
        'experimentId': this.data.experimentId
      },
      method: 'POST', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
      header: { header }, // 设置请求的 header
      success: function (res) {
        // success
        if (res.ret == 1) {
          Toast("保存成功")
          wx.navigateBack({
            delta: 1
          })
        } else {
          Toast("保存失败，请重试！")
         
        }
      }
    })
  },
  submit: function () {
    var that = this;
    wx.request({
      url: url + "oe/submit",
      data: {
        'HasFinished': this.data.HasFinished,
        'experimentId': this.data.experimentId
      },
      method: 'POST', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
      header: { header }, // 设置请求的 header
      success: function (res) {
        // success
        if (res.ret == 1) {
          Toast("提交成功")
          wx.navigateBack({
            delta: 1
          })
        } else {
          Toast("提交失败，请重试！")
        }
      }
    })
  }
})