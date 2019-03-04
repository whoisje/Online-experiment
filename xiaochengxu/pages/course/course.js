// pages/course/course.js
import Toast from '../../dist/toast/toast';
var app = getApp();
var classId = app.globalData.student.class.classId;
var header = app.globalData.header;
var url = app.globalData.url;
Component({
  /**
   * 组件的属性列表
   */
  pageLifetimes: {
    show() {
      app.checkState();
      if (typeof this.getTabBar === 'function' &&
        this.getTabBar()) {
        this.getTabBar().setData({
          selected: 1
        })
      }
      this.getCourseList();
    },
    
    pullDownRefresh: function () {
      wx.showNavigationBarLoading();
      this.methods.getCourseList();
    }
  },
  properties: {

  },

  /**
   * 组件的初始数据
   */
  data: {
    courseList: [
      { courseName: '人工智能', courseId: '10001', color: 'purple' },
      { courseName: '机器学习', courseId: '10002', color: 'red' }
    ],
  },

  /**
   * 组件的方法列表
   */
  methods: {
    getCourseList() {
      var that = this;
      wx.request({
        url: url + "oe/getCourseList",
        data: { 'classId': classId },
        method: 'GET', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
        header: {header}, // 设置请求的 header
        success: function (res) {
          // success
          if (res.ret == 1) {
            that.setData({
              courseList:app.parseResult(res)
            });
          } else {
            Toast("获取数据失败，请下拉刷新重试！")
          }
          wx.hideNavigationBarLoading();
          wx.stopPullDownRefresh();
        }
      })
    }
  }
})
