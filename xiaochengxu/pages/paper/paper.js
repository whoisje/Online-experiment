// paper/paper.js
import Toast from '../../dist/toast/toast';
import Dialog from '../../dist/dialog/dialog';

var app = getApp();
var header = app.globalData.header;
var url = app.globalData.url;

Page({

  /**
   * 页面的初始数据
   */
  data: {
    active: 0,
    maxLength: 999,
    steps: [
    ],
    experimentId: 0,
    HasFinished: {
    },
    experimentDetail: {

    },
    args:'',
    saveState: 0,
    submitState: 0,
    runState: 0,
    StatusBar: app.globalData.StatusBar,
    CustomBar: app.globalData.CustomBar,
    TabCur: 0,
    scrollLeft: 0
  },
  pre(e) {
    var active = this.data.active;
    if (active > 0) {
      this.setData({
        active: active - 1
      })
    }
  },
  next(e) {
    var length = this.data.steps.length;
    var active = this.data.active;
    if (active < length - 1) {
      this.setData({
        active: active + 1
      })
    }
  },
  tabSelect(e) {
    console.log(e);
    this.setData({
      TabCur: e.currentTarget.dataset.id,
      scrollLeft: (e.currentTarget.dataset.id - 1) * 60
    })
    console.log(this.data.TabCur)
  },
  argsInput:function(e){

    this.setData({
      args:e.detail
    })
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
      header: { 'Cookie': header.Cookie }, // 设置请求的 header
      success: function (res) {
        // success
        if (res.data.ret == 1) {
          that.setData({
            experimentDetail: res.data.body
          });
          that.selectSteps(experimentId);
          that.getHasFinished(experimentId);
        } else {
          Toast("获取数据失败，请重试！")
        }
      }
    })
  },
  executeCode: function () {
    var that = this;
    var steps = this.data.steps;
    
    var list = new Array(steps.length);

    for (var i = 0; i < steps.length; i++) {
      var stuSteps = {//每次都要创建新对象不然会被覆盖
        "stepId": "",
        "stepCode": "",
      }
      stuSteps.stepId = steps[i].stepId;
      stuSteps.stepCode = steps[i].stepCode;
      list[i] = stuSteps;
    }
    that.setData({
      runState: 1
    })
    wx.request({
      url: url + "oe/executeCode",
      data: { 'list': list,'experimentId':that.data.experimentId,'args':that.data.args },
      method: 'POST', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
      header: { 'Cookie': header.Cookie }, // 设置请求的 header
      success: function (res) {
        // success
        if (res.data.ret == 1) {
          that.setData({
            'HasFinished.codeResult': res.data.body.result+'\n'+res.data.body.error
          });
          console.log(res.data.body)
        } else {
          Toast("执行失败，请重试！")
        }
        that.setData({
          runState: 0
        })
      }
    })
  },
  getHasFinished(experimentId) {
    var that = this;
    that.setData({
      'HasFinished.experimentId': experimentId
    })
    wx.request({
      url: url + "oe/selectHasFinished",
      data: { 'experimentId': experimentId },
      method: 'GET', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
      header: { 'Cookie': header.Cookie }, // 设置请求的 header
      success: function (res) {
        // success
        if (res.data.ret == 1) {
          that.setData({
            HasFinished: res.data.body,

          });
        } else {
          Toast("获取数据失败，请重试！")
        }
      }
    })
  },
  onCodeInput: function (e) {
    var steps = this.data.steps;
    var item = 'steps['+this.data.active+'].stepCode'
    this.setData({
      [item]: e.detail
    });
  
  },
  onFeelingInput: function (e) {
    this.setData({
      'HasFinished.feeling': e.detail
    });
    console.log(e.detail)
  },
  save: function (e) {
    var that = this;
    var status = that.data.HasFinished.status;
    that.setData({
      'HasFinished.status': 1,
      saveState: 1
    })
    that.saveSteps()
    wx.request({
      url: url + "oe/save",
      data: this.data.HasFinished,
      method: 'POST', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
      header: { 'Cookie': header.Cookie }, // 设置请求的 header
      success: function (res) {
        // success
        if (res.data.ret == 1) {
          Toast("保存成功")
          wx.navigateBack({
            delta: 1
          })
        } else {
          Toast("保存失败，请重试！")
          that.setData({
            'HasFinished.status': status,
            saveState: 0
          })
        }
      }
    })
  },
  submit: function () {
    var that = this;
    var status = that.data.HasFinished.status;
    Dialog.confirm({
      title: '提示',
      message: '确定提交？'
    }).then(() => {
      that.saveSteps()
      that.setData({
        'HasFinished.status': 2,
        submitState: 1
      })
      wx.request({
        url: url + "oe/save",
        data: this.data.HasFinished,
        method: 'POST', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
        header: { 'Cookie': header.Cookie }, // 设置请求的 header
        success: function (res) {
          // success
          if (res.data.ret == 1) {
            Toast("提交成功")
            wx.navigateBack({
              delta: 1
            })
          } else {
            Toast("提交失败，请重试！")
            that.setData({
              'HasFinished.status': status,
              submitState: 0
            })
          }
        }
      })
    }
    ).catch(() => {

    });
  },
  selectSteps: function (experimentId) {
    var that = this;
    wx.request({
      url: url + "oe/selectSteps",
      data: { 'experimentId': experimentId },
      method: 'GET', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
      header: { 'Cookie': header.Cookie }, // 设置请求的 header
      success: function (res) {
        // success
        if (res.data.ret == 1) {
          that.setData({
            steps: res.data.body,
            maxLength: res.data.body.length
          })
          that.selectStuSteps();
        } else {
          Toast("请求失败，请重试！")
        }
      }
    })
  },
  selectStuSteps: function () {
    var that = this;
    var steps = this.data.steps;
    var stepIds = new Array(steps.length);
    for (var i = 0; i < steps.length; i++) {
      stepIds[i] = steps[i].stepId;
    }
    wx.request({
      url: url + "oe/selectStuSteps",
      data: { 'stepIds': stepIds },
      method: 'POST', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
      header: { 'Cookie': header.Cookie }, // 设置请求的 header
      success: function (res) {
        // success
        if (res.data.ret == 1) {
          var stuSteps = res.data.body;
          var steps = that.data.steps;
          for (var i = 0; i < stuSteps.length; i++) {
          var item = 'steps['+i+'].stepCode'; 
            that.setData({
              [item]: stuSteps[i].stepCode
            })
            console.log(that.data.steps);
          }
        } else {
          Toast("请求失败，请重试！")
        }
      }
    })
  },
  saveSteps: function () {
    var that = this;
    var steps = this.data.steps;
    
    var list = new Array(steps.length);

    for (var i = 0; i < steps.length; i++) {
      var stuSteps = {//每次都要创建新对象不然会被覆盖
        "stepId": "",
        "stepCode": "",
      }
      stuSteps.stepId = steps[i].stepId;
      stuSteps.stepCode = steps[i].stepCode;
      list[i] = stuSteps;
    }
    wx.request({
      url: url + "oe/saveSteps",
      data: { 'list': list },
      method: 'POST', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
      header: { 'Cookie': header.Cookie }, // 设置请求的 header
      success: function (res) {
        // success
        if (res.data.ret == 1) {
          return true;
        } else {
          return false;
        }
      }
    })
  },

})