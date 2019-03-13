// pages/course/course.js
import Toast from '../../dist/toast/toast';
var app = getApp();
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
    ],
  },

  /**
   * 组件的方法列表
   */
  methods: {
    getCourseList() {
      var header = app.globalData.header;
      var url = app.globalData.url;
      var that = this;
      wx.request({
        url: url + "oe/getCourseList",
        method: 'GET', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
        header: { 'Cookie':header.Cookie }, // 设置请求的 header
        success: function (res) {
          // success
          if ( res.data.ret == 1) {
            that.setData({
              courseList: res.data.body
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
